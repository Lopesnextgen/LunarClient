#!/usr/bin/env python3
"""clean_locals.py -- give Vineflower's ``varN`` locals and parameters readable names.

The decompiled sources under ``tools/work/staging/decompiled`` were produced
without debug info, so Vineflower invented ``var0``, ``var1``, ... for every
local variable and parameter (fields and methods keep their obfuscated names).
This tool renames those identifiers based on their *declared type*:

    String var3        -> text3
    boolean var1       -> flag1
    int var2           -> number2      (or index2 when used as a subscript)
    double var4        -> value4
    String[] var0      -> items0       (args0 for main(String[]))
    List<Foo> var5     -> list5
    Map<K,V> var6      -> map6
    Iterator var7      -> iterator7
    EntityPlayer var1  -> player1
    Minecraft var0     -> minecraft0
    <anything else>    -> lowercased simple name, e.g. thread8

The original ``varN`` number is kept as a suffix, which is unique per method
(Vineflower resets numbering per method), so no per-method counter is needed.
Vineflower's disambiguated siblings ``var1x``/``var1xx`` keep their ``x``
suffix (``text1x``) so they stay distinct from ``var1``.

How it works
------------
The source is tokenized (strings, chars and comments are opaque tokens and are
never rewritten).  A single pass tracks brace-delimited scopes and recognises
declarations token-wise:

* parameters of methods/constructors (seeded into the body scope),
* local declarations, enhanced-for variables, catch parameters (including
  multi-catch ``A | B var``) and ``instanceof`` pattern variables,
* bare lambda parameters (``var0 ->`` / ``(var0, var1) ->``) named ``argN``,
  including lambdas in field initializers.

Every other occurrence of a name is resolved through the scope stack, so
``this.var1`` (member access), method calls named ``var1(...)`` and field or
label names are left untouched.  Class-body declarations that are not method
parameters (i.e. fields) are deliberately not renamed.  ``varN`` identifiers
with no visible declaration (a known Vineflower artifact) are left as-is
rather than guessed at.

Validation
----------
``--check`` re-tokenizes the tree and reports
  * unbalanced braces/parens/brackets,
  * remaining ``varN`` identifiers (listed with ``--verbose``; fatal with
    ``--strict``),
  * with ``--compare ORIG_DIR``: the token stream must be identical to the
    source tree apart from identifier text,
  * with ``--javac``: javac syntax errors, plus "mis-renames" -- generated
    names that javac reports as undeclared (missing-dependency errors are
    ignored, but when ``--compare`` is given the generated names are known
    from the original file, so any unresolved generated name is a bug).

Usage
-----
    tools/clean_locals.py                       # dry run, default source root
    tools/clean_locals.py --apply               # rewrite in place
    tools/clean_locals.py --check --strict src/main/java/com/moonsworth
    tools/clean_locals.py --check --compare tools/work/staging/decompiled/com/moonsworth src/main/java/com/moonsworth
    tools/clean_locals.py --check --compare ORIG --javac --javac-limit 200 NEW
"""

from __future__ import annotations

import argparse
import os
import re
import shutil
import subprocess
import sys
import tempfile
from collections import Counter

# --------------------------------------------------------------------------
# vocabulary
# --------------------------------------------------------------------------

VAR_RE = re.compile(r"^var(\d+)(x*)$")
VAR_TOKEN_RE = re.compile(r"\bvar\d+x*\b")

KEYWORDS = {
    "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char",
    "class", "const", "continue", "default", "do", "double", "else", "enum",
    "extends", "final", "finally", "float", "for", "goto", "if", "implements",
    "import", "instanceof", "int", "interface", "long", "native", "new",
    "package", "private", "protected", "public", "return", "short", "static",
    "strictfp", "super", "switch", "synchronized", "this", "throw", "throws",
    "transient", "try", "void", "volatile", "while", "var", "record",
    "true", "false", "null",
}

MODIFIERS = {
    "public", "protected", "private", "static", "final", "abstract",
    "synchronized", "native", "transient", "volatile", "strictfp", "default",
}

CONTROL_PAREN_OWNERS = {
    "if", "for", "while", "switch", "catch", "synchronized", "try", "return",
    "throw", "assert", "new", "do", "else", "finally", "case", "super",
    "this", "instanceof",
}

SINGLE_LETTER_GENERICS = set("T E K V R N C A B D U P S I O".split())

INT_TYPES = {
    "int", "long", "short", "byte", "Integer", "Long", "Short", "Byte",
    "Number", "AtomicInteger", "AtomicLong", "BigInteger", "size_t",
}

PRIMITIVE_BASE = {
    "int": "number", "long": "number", "short": "number", "byte": "number",
    "boolean": "flag", "double": "value", "float": "value",
    "char": "character", "void": "value",
}

# Methods whose (integer) argument is normally a subscript / position.
INDEX_METHODS = {
    "get", "getInt", "getFloat", "getDouble", "getLong", "getShort",
    "getByte", "getBoolean", "charAt", "codePointAt", "set", "setInt",
    "setFloat", "setDouble", "setLong", "setShort", "setByte", "remove",
    "add", "insert", "substring", "subList", "elementAt", "indexOf",
    "lastIndexOf", "deleteCharAt", "delete", "read", "readInt", "readByte",
    "readChar", "readLong", "readShort", "readFloat", "readDouble", "skip",
}

# tokens allowed between a type's '<' and '>' (used to distinguish generics
# from comparison expressions when scanning backwards)
TYPE_ARG_TOKENS = {"<", ">", ">>", ">>>", ",", ".", "?", "@", "&", "[", "]"}
TYPE_ARG_KEYWORDS = {"extends", "super"}

# simple type name -> readable base
TYPE_BASE = {
    # JDK / java.lang
    "String": "text", "CharSequence": "text", "StringBuilder": "builder",
    "StringBuffer": "builder", "Boolean": "flag", "Integer": "number",
    "Long": "number", "Short": "number", "Byte": "number", "Number": "number",
    "AtomicInteger": "number", "AtomicLong": "number", "BigInteger": "number",
    "Double": "value", "Float": "value", "BigDecimal": "value",
    "Character": "character", "Object": "obj", "Class": "clazz",
    "Throwable": "exception", "Exception": "exception",
    "RuntimeException": "exception", "IOException": "exception",
    "Error": "error", "Optional": "optional", "Iterator": "iterator",
    "ListIterator": "iterator",
    # collections
    "List": "list", "ArrayList": "list", "LinkedList": "list",
    "Collection": "list", "Iterable": "list", "Queue": "list",
    "Deque": "list", "Vector": "list", "ImmutableList": "list",
    "Map": "map", "HashMap": "map", "LinkedHashMap": "map", "TreeMap": "map",
    "ConcurrentHashMap": "map", "IdentityHashMap": "map", "EnumMap": "map",
    "ImmutableMap": "map", "Properties": "map",
    "Set": "set", "HashSet": "set", "TreeSet": "set", "LinkedHashSet": "set",
    "EnumSet": "set", "ImmutableSet": "set",
    # functional / misc jdk
    "Consumer": "consumer", "Supplier": "supplier", "Function": "function",
    "BiFunction": "function", "Predicate": "predicate", "Runnable": "runnable",
    "Callable": "callable", "Comparator": "comparator", "File": "file",
    "Path": "path", "URL": "url", "URI": "uri", "UUID": "uuid",
    "Random": "random", "Date": "date", "Calendar": "calendar",
    "Locale": "locale", "Pattern": "pattern", "Matcher": "matcher",
    "Entry": "entry", "InputStream": "input", "OutputStream": "output",
    "Reader": "reader", "Writer": "writer", "BufferedReader": "reader",
    "BufferedWriter": "writer", "PrintWriter": "writer",
    "PrintStream": "stream", "ByteBuffer": "buffer", "CharBuffer": "buffer",
    "ByteBuf": "buffer", "FileInputStream": "stream",
    "FileOutputStream": "stream", "InputStreamReader": "reader",
    "OutputStreamWriter": "writer", "StringReader": "reader",
    "StringWriter": "writer", "DataInputStream": "input",
    "DataOutputStream": "output", "Socket": "socket", "Thread": "thread",
    # gson
    "JsonObject": "json", "JsonArray": "array", "JsonElement": "element",
    "JsonPrimitive": "json", "JsonNull": "json",
    # minecraft / forge / mixin / asm
    "EntityPlayer": "player", "EntityPlayerSP": "player",
    "EntityPlayerMP": "player", "EntityOtherPlayerMP": "player",
    "AbstractClientPlayer": "player", "EntityClientPlayerMP": "player",
    "Entity": "entity", "EntityLivingBase": "entity", "EntityLiving": "entity",
    "EntityCreature": "entity", "World": "world", "WorldClient": "world",
    "WorldServer": "world", "ItemStack": "stack", "Item": "item",
    "Block": "block", "BlockPos": "pos", "Vec3": "vec", "Vec3i": "vec",
    "AxisAlignedBB": "box", "Minecraft": "minecraft",
    "MinecraftServer": "server", "ResourceLocation": "location",
    "NBTTagCompound": "compound", "NBTTagList": "list", "NBTBase": "nbt",
    "IBlockState": "state", "EnumFacing": "facing", "EnumHand": "hand",
    "Container": "container", "Slot": "slot", "GuiScreen": "screen",
    "Gui": "gui", "FontRenderer": "font", "EntityRenderer": "renderer",
    "MobEffectInstance": "effect", "PotionEffect": "effect",
    "DamageSource": "source", "MovingObjectPosition": "hit",
    "IChatComponent": "text", "ChatComponentText": "text",
    "TextComponent": "text", "ChatStyle": "style", "CallbackInfo": "callback",
    "Operation": "operation", "ClassNode": "node", "MethodNode": "method",
    "FieldNode": "field", "AnnotationNode": "annotation",
    "AbstractInsnNode": "instruction", "InsnList": "instructions",
}

JAVAC_SYNTAX_ERROR = re.compile(
    r"error: (?:"
    r"';' expected|'\)' expected|'\(' expected|'\{' expected|"
    r"'\}' expected|'\]' expected|'<' expected|'>' expected|"
    r"illegal start of .*|reached end of file while parsing|"
    r"not a statement|<identifier> expected|"
    r"class, interface, enum, or record expected|"
    r"unclosed .*|illegal character|malformed|orphaned|"
    r"'else' without 'if'|'catch' without 'try'|'try' without 'catch'|"
    r"'finally' without 'try'|variable .* is already defined|"
    r"duplicate (?:class|method)|modifier .* not allowed here"
    r")")

SYMBOL_VAR_RE = re.compile(r"symbol:\s+variable\s+([A-Za-z_$][\w$]*)")

# --------------------------------------------------------------------------
# tokenizer
# --------------------------------------------------------------------------

_PUNCT3 = ("...", ">>>=", ">>>", "<<=", ">>=", "->", "::", "++", "--", "==",
           "!=", "<=", ">=", "&&", "||", "+=", "-=", "*=", "/=", "%=", "&=",
           "|=", "^=", "<<", ">>")

PAIRS = {"(": ")", "[": "]", "{": "}"}
CLOSERS = {v: k for k, v in PAIRS.items()}


def tokenize(src: str):
    """Return significant tokens as ``(kind, text, start, end)``.

    kind is one of ``id`` (identifier *or* keyword), ``num``, ``str``,
    ``char``, ``punct``.  Comments and whitespace are dropped.
    """
    tokens = []
    i = 0
    n = len(src)
    while i < n:
        c = src[i]
        if c in " \t\r\n\f":
            i += 1
            continue
        if c == "/" and i + 1 < n and src[i + 1] == "/":
            j = src.find("\n", i)
            i = n if j < 0 else j + 1
            continue
        if c == "/" and i + 1 < n and src[i + 1] == "*":
            j = src.find("*/", i + 2)
            i = n if j < 0 else j + 2
            continue
        if c == '"':
            start = i
            if src.startswith('"""', i):
                j = src.find('"""', i + 3)
                i = n if j < 0 else j + 3
            else:
                i += 1
                while i < n:
                    if src[i] == "\\":
                        i += 2
                        continue
                    if src[i] == '"':
                        i += 1
                        break
                    i += 1
            tokens.append(("str", src[start:i], start, i))
            continue
        if c == "'":
            start = i
            i += 1
            while i < n:
                if src[i] == "\\":
                    i += 2
                    continue
                if src[i] == "'":
                    i += 1
                    break
                i += 1
            tokens.append(("char", src[start:i], start, i))
            continue
        if c.isdigit() or (c == "." and i + 1 < n and src[i + 1].isdigit()):
            start = i
            while i < n:
                ch = src[i]
                if ch.isalnum() or ch in "._":
                    i += 1
                elif ch in "+-" and i > start and src[i - 1] in "eE":
                    i += 1
                else:
                    break
            tokens.append(("num", src[start:i], start, i))
            continue
        if c.isalpha() or c in "_$":
            start = i
            while i < n and (src[i].isalnum() or src[i] in "_$"):
                i += 1
            tokens.append(("id", src[start:i], start, i))
            continue
        for p in _PUNCT3:
            if src.startswith(p, i):
                tokens.append(("punct", p, i, i + len(p)))
                i += len(p)
                break
        else:
            tokens.append(("punct", c, i, i + 1))
            i += 1
    return tokens


def compute_matches(tokens):
    """Map open delimiter index -> close index and vice versa."""
    open_to_close, close_to_open = {}, {}
    stacks = {k: [] for k in PAIRS}
    for i, tok in enumerate(tokens):
        if tok[0] != "punct":
            continue
        t = tok[1]
        if t in PAIRS:
            stacks[t].append(i)
        elif t in CLOSERS:
            stack = stacks[CLOSERS[t]]
            if stack:
                j = stack.pop()
                open_to_close[j] = i
                close_to_open[i] = j
    return open_to_close, close_to_open


# --------------------------------------------------------------------------
# token helpers
# --------------------------------------------------------------------------

def annotation_end(tokens, j, close_to_open):
    """If tokens[j] ends an annotation use (``@Foo``, ``@Foo(...)`` or
    ``@a.b.Foo(...)``), return the index of its ``@`` token, else None."""
    if j < 0:
        return None
    k = j
    if tokens[k][1] == ")":
        op = close_to_open.get(k)
        if op is None or op == 0:
            return None
        k = op - 1
    if k < 0 or tokens[k][0] != "id":
        return None
    while k - 2 >= 0 and tokens[k - 1][1] == "." and tokens[k - 2][0] == "id":
        k -= 2
    if k - 1 >= 0 and tokens[k - 1][1] == "@":
        return k - 1
    return None


def looks_like_type_args(tokens, lt_idx, gt_idx):
    """True when the tokens between ``<`` and ``>`` could be type arguments.

    This rejects comparison expressions such as ``i < list.size()`` that a
    naive backwards scan would otherwise mistake for ``Foo<Bar>``."""
    for k in range(lt_idx + 1, gt_idx):
        tok = tokens[k]
        if tok[0] == "id":
            if tok[1] in KEYWORDS and tok[1] not in TYPE_ARG_KEYWORDS \
                    and tok[1] not in PRIMITIVE_BASE:
                return False
            continue
        if tok[0] == "punct" and tok[1] in TYPE_ARG_TOKENS:
            continue
        return False
    return True


def skip_annotations_backward(tokens, i, close_to_open):
    """Step over ``@Foo`` / ``@Foo(...)`` annotations ending at token *i*."""
    while i >= 0:
        a = annotation_end(tokens, i, close_to_open)
        if a is None:
            break
        i = a - 1
    return i


def parse_type_back(tokens, i, close_to_open):
    """Parse one type whose last token is at *i*.

    Returns ``(start_idx, simple_name, is_array)`` or ``(None, None, None)``.
    Handles varargs, C-style arrays, generics, type annotations and qualified
    names.
    """
    is_array = False
    if i >= 0 and tokens[i][1] == "...":
        is_array = True
        i -= 1
        i = skip_annotations_backward(tokens, i, close_to_open)
    while i >= 1 and tokens[i][1] == "]" and tokens[i - 1][1] == "[":
        is_array = True
        i -= 2
        i = skip_annotations_backward(tokens, i, close_to_open)
    if i >= 0 and tokens[i][1] in (">", ">>", ">>>"):
        depth = 0
        j = i
        while j >= 0:
            t = tokens[j][1]
            if t in (">", ">>", ">>>"):
                depth += len(t)
            elif t == "<":
                depth -= 1
                if depth == 0:
                    break
            j -= 1
        if j < 0 or not looks_like_type_args(tokens, j, i):
            return None, None, None
        i = j - 1

    if i < 0 or tokens[i][0] != "id":
        return None, None, None
    simple = tokens[i][1]
    if VAR_RE.match(simple):
        return None, None, None          # cannot be a real type here
    if simple in KEYWORDS and simple not in PRIMITIVE_BASE:
        return None, None, None          # return/throw/new/... are not types
    type_start = i

    # qualified names with possible type annotations and generics:
    # A.@Nullable B, TypeToken<T>.TypeSet
    while True:
        k = i - 1
        if k < 0:
            break
        a = annotation_end(tokens, k, close_to_open)
        if a is not None:
            k = a - 1
        if k >= 1 and tokens[k][1] == ".":
            prev_tok = tokens[k - 1]
            if prev_tok[0] == "id":
                i = k - 1
                type_start = i
                continue
            if prev_tok[1] in (">", ">>", ">>>"):
                j = k - 1
                depth = 0
                while j >= 0:
                    t = tokens[j][1]
                    if t in (">", ">>", ">>>"):
                        depth += len(t)
                    elif t == "<":
                        depth -= 1
                        if depth == 0:
                            break
                    j -= 1
                if j < 1 or not looks_like_type_args(tokens, j, k - 1):
                    break
                i = j - 1
                type_start = i
                continue
        break
    return type_start, simple, is_array


def scan_type_back(tokens, name_idx, close_to_open):
    """Decide whether the token at ``name_idx`` starts a local/parameter
    declaration and, if so, return info about its declared type.

    Returns a dict with ``simple`` (simple type name), ``array`` (bool),
    ``param_paren`` (index of the ``(`` when this is a formal parameter),
    ``for_header`` (declared in a ``for (...)`` header) and ``instanceof``
    (pattern variable), or None.
    """
    if name_idx < 1:
        return None
    type_start, simple, is_array = parse_type_back(tokens, name_idx - 1,
                                                   close_to_open)
    if type_start is None:
        return None

    # walk over modifiers and union types (multi-catch: A | B var)
    b = type_start - 1
    while True:
        b = skip_annotations_backward(tokens, b, close_to_open)
        if b >= 0 and tokens[b][0] == "id" and tokens[b][1] in MODIFIERS:
            b -= 1
            continue
        if b >= 0 and tokens[b][1] == "|":
            nstart, nsimple, _ = parse_type_back(tokens, b - 1, close_to_open)
            if nstart is None:
                return None
            simple = nsimple
            type_start = nstart
            b = type_start - 1
            continue
        break
    if b < 0:
        return None
    btok = tokens[b][1]
    is_instanceof = btok == "instanceof"
    if btok not in ("(", ",", ";", "{", "}", ":") and not is_instanceof:
        return None

    # what follows the name must be declaration-like
    nxt = tokens[name_idx + 1][1] if name_idx + 1 < len(tokens) else ""
    if is_instanceof:
        if nxt not in ("&&", "||", ")", ";", "?", ":", ","):
            return None
    elif nxt not in ("=", ";", ",", ")", "[", ":", "->"):
        return None

    param_paren = b if btok == "(" else None
    for_header = (param_paren is not None and b >= 1
                  and tokens[b - 1][1] == "for")
    return {
        "simple": simple,
        "array": is_array,
        "param_paren": param_paren,
        "for_header": for_header,
        "instanceof": is_instanceof,
    }


def is_method_param_paren(tokens, paren_idx, open_to_close=None):
    """True when ``paren_idx`` opens a method/constructor parameter list."""
    if paren_idx is None or paren_idx < 1:
        return False
    owner = tokens[paren_idx - 1]
    if owner[0] != "id" or owner[1] in CONTROL_PAREN_OWNERS:
        return False
    if owner[1] in KEYWORDS and owner[1] not in ("this", "super"):
        return False
    # a method declaration is never qualified: obj.foo(...) is a call
    if paren_idx >= 2 and tokens[paren_idx - 2][1] == ".":
        return False
    if open_to_close is not None:
        close = open_to_close.get(paren_idx)
        if close is None or close + 1 >= len(tokens):
            return False
        after = tokens[close + 1][1]
        if after not in ("{", ";", "throws", "default"):
            return False
    return True


def block_kind(tokens, open_idx, close_to_open):
    """Classify the block opened at ``open_idx`` as ``class`` (type/member
    scope) or ``local`` (method body, lambda, initializer, control block)."""
    # lambda bodies are always local scopes
    if open_idx >= 1 and tokens[open_idx - 1][1] == "->":
        return "local"
    j = open_idx - 1
    while j >= 0:
        t = tokens[j][1]
        if t in ("{", "}", ";"):
            return "local"
        if t in ("class", "interface", "enum"):
            if j >= 1 and tokens[j - 1][1] == ".":
                j -= 2                      # Foo.class literal
                continue
            return "class"
        if t == "record":
            if j >= 1 and tokens[j - 1][1] == ".":
                j -= 2
                continue
            if j + 2 < len(tokens) and tokens[j + 1][0] == "id" \
                    and tokens[j + 2][1] == "(":
                return "class"
        if t == "new":
            return "class"
        if t == ")":
            op = close_to_open.get(j)
            if op is None:
                return "local"
            j = op - 1
            continue
        if t == "]":
            op = close_to_open.get(j)
            if op is None:
                return "local"
            j = op - 1
            continue
        if t == "(":
            return "local"
        j -= 1
    return "local"


def header_paren(tokens, open_idx, close_to_open):
    """Index of the parameter-list ``(`` that belongs to the block opened at
    ``open_idx`` (handles ``throws`` clauses and lambdas), or None."""
    j = open_idx - 1
    while j >= 0 and tokens[j][1] not in ("{", "}", ";"):
        if tokens[j][1] == ")":
            return close_to_open.get(j)
        j -= 1
    return None


def method_body_open(tokens, open_to_close, close_to_open, paren_idx):
    """Body ``{`` of the method whose parameter list opens at ``paren_idx``,
    or None for abstract/interface method declarations."""
    close = open_to_close.get(paren_idx)
    if close is None:
        return None
    j = close + 1
    while j < len(tokens):
        t = tokens[j][1]
        if t == "{":
            return j
        if t in (";", "}", "="):
            return None
        j += 1
    return None


def apply_replacements(src, replacements):
    if not replacements:
        return src
    replacements.sort(key=lambda r: r[0])
    out = []
    pos = 0
    for start, end, text in replacements:
        if start < pos:          # defensive: overlapping edits
            continue
        out.append(src[pos:start])
        out.append(text)
        pos = end
    out.append(src[pos:])
    return "".join(out)


# --------------------------------------------------------------------------
# renamer
# --------------------------------------------------------------------------

class Renamer:
    def __init__(self, src: str):
        self.src = src
        self.tokens = tokenize(src)
        self.open_to_close, self.close_to_open = compute_matches(self.tokens)
        # identifiers that must not be collided with (fields, classes, ...);
        # varN names are excluded because they are the ones being renamed
        self.orig_idents = {t[1] for t in self.tokens
                            if t[0] == "id" and not VAR_RE.match(t[1])}
        self.replacements = []
        self.scopes = []            # {'kind', 'open', 'bindings'}
        self.parens = []            # currently open '(' indices
        self.param_bindings = {}    # param '(' idx -> {varN: newname}
        self.lambda_pending = {}    # '->' idx -> {varN: newname} (block lambda)
        self.temp_bindings = []     # [(depth, scope, varN, newname)]
        self.generated_names = set()
        self.stats = Counter()
        self.base_counts = Counter()

    # -- naming ------------------------------------------------------------

    def _name_taken(self, candidate, extra):
        if candidate in self.orig_idents or candidate in extra:
            return True
        for scope in self.scopes:
            if candidate in scope["bindings"].values():
                return True
        return False

    def make_name(self, base, num, suffix="", extra=()):
        candidate = f"{base}{num}{suffix}"
        while self._name_taken(candidate, extra):
            candidate += "_"
        self.generated_names.add(candidate)
        self.base_counts[base] += 1
        return candidate

    def base_for(self, decl, name_idx, param_paren=None, region_open=None):
        simple = decl["simple"]
        base = None
        if decl["array"]:
            if (simple == "String" and param_paren is not None
                    and param_paren >= 1
                    and self.tokens[param_paren - 1][1] == "main"):
                base = "args"
            else:
                base = "items"
        elif simple in PRIMITIVE_BASE:
            base = PRIMITIVE_BASE[simple]
        elif simple in TYPE_BASE:
            base = TYPE_BASE[simple]
        elif simple in SINGLE_LETTER_GENERICS:
            base = "value"
        else:
            low = simple.lower()
            if low in KEYWORDS:
                base = "clazz" if low == "class" else "value"
            elif simple.isupper() and len(simple) >= 6:
                base = "obj"          # obfuscated class name
            else:
                base = low
        if simple in INT_TYPES and not decl["array"]:
            if decl["for_header"] or self.uses_as_index(name_idx, region_open):
                base = "index"
        return base

    def uses_as_index(self, name_idx, region_open=None):
        """Heuristic: is this integer local used as an array/collection
        subscript within its block (or method body)?"""
        tokens = self.tokens
        end = len(tokens)
        if region_open is None and self.scopes:
            region_open = self.scopes[-1]["open"]
        if region_open is not None:
            end = self.open_to_close.get(region_open, end)
        name = tokens[name_idx][1]
        i = name_idx + 1
        while i < end:
            tok = tokens[i]
            if tok[0] == "id" and tok[1] == name:
                prv = tokens[i - 1][1] if i > 0 else ""
                nxt = tokens[i + 1][1] if i + 1 < len(tokens) else ""
                if prv == "[" or nxt == "]" or nxt in ("++", "--"):
                    return True
                # nearest enclosing '(' must be an index-style call
                k = i - 1
                depth = 0
                while k >= 0:
                    tt = tokens[k][1]
                    if tt == ")":
                        depth += 1
                    elif tt == "(":
                        if depth == 0:
                            owner = tokens[k - 1][1] if k > 0 else ""
                            if owner in INDEX_METHODS:
                                return True
                            break
                        depth -= 1
                    elif tt in (";", "{", "}", "=") and depth == 0:
                        break
                    k -= 1
            i += 1
        return False

    # -- pass --------------------------------------------------------------

    def run(self):
        tokens = self.tokens
        for i, tok in enumerate(tokens):
            text = tok[1]
            if tok[0] == "punct":
                if text == "{":
                    self.push_scope(i)
                elif text == "}":
                    self.pop_scope()
                elif text == "(":
                    self.parens.append(i)
                elif text == ")":
                    if self.parens:
                        self.parens.pop()
                elif text == ";":
                    self.drop_temp_bindings(len(self.scopes))
                elif text == "->":
                    self.handle_lambda_params(i)
                continue
            if tok[0] != "id":
                continue
            m = VAR_RE.match(text)
            if m:
                self.handle_var(i, text, int(m.group(1)), m.group(2))
        self.drop_temp_bindings(0)
        return apply_replacements(self.src, self.replacements)

    def drop_temp_bindings(self, depth):
        """Remove expression-lambda bindings whose statement has ended."""
        while self.temp_bindings and self.temp_bindings[-1][0] >= depth:
            _, scope, name, new = self.temp_bindings.pop()
            if scope["bindings"].get(name) == new:
                del scope["bindings"][name]

    def push_scope(self, open_idx):
        kind = block_kind(self.tokens, open_idx, self.close_to_open)
        bindings = {}
        op = header_paren(self.tokens, open_idx, self.close_to_open)
        if op is not None and op in self.param_bindings:
            bindings.update(self.param_bindings[op])
        if open_idx >= 1 and self.tokens[open_idx - 1][1] == "->":
            bindings.update(self.lambda_pending.pop(open_idx - 1, {}))
        self.scopes.append({"kind": kind, "open": open_idx,
                            "bindings": bindings})

    def pop_scope(self):
        if not self.scopes:
            return
        scope = self.scopes.pop()
        self.temp_bindings = [tb for tb in self.temp_bindings
                              if tb[1] is not scope]

    def handle_var(self, idx, name, num, suffix):
        tokens = self.tokens
        prev = tokens[idx - 1][1] if idx > 0 else ""
        nxt = tokens[idx + 1][1] if idx + 1 < len(tokens) else ""
        if prev in (".", "::"):
            return                       # member access / method reference
        if nxt == "(":
            return                       # call (or method declaration)

        decl = scan_type_back(tokens, idx, self.close_to_open)
        if decl is not None:
            if not self.scopes:
                return
            cur_paren = self.parens[-1] if self.parens else None
            cur = self.scopes[-1]
            if cur["kind"] == "class":
                if not is_method_param_paren(tokens, cur_paren,
                                             self.open_to_close):
                    return               # field / enum constant: leave alone
                pending = self.param_bindings.setdefault(cur_paren, {})
                region = method_body_open(tokens, self.open_to_close,
                                          self.close_to_open, cur_paren)
                base = self.base_for(decl, idx, cur_paren, region)
                new = self.make_name(base, num, suffix,
                                     extra=set(pending.values()))
                pending[name] = new
                self.replacements.append((tokens[idx][2], tokens[idx][3], new))
                self.stats["parameters"] += 1
                return
            new = self.make_name(self.base_for(decl, idx, cur_paren), num,
                                 suffix)
            cur["bindings"][name] = new
            self.replacements.append((tokens[idx][2], tokens[idx][3], new))
            self.stats["locals"] += 1
            return

        for scope in reversed(self.scopes):
            if name in scope["bindings"]:
                self.replacements.append(
                    (tokens[idx][2], tokens[idx][3], scope["bindings"][name]))
                self.stats["uses"] += 1
                return
        self.stats["leftover"] += 1

    def handle_lambda_params(self, arrow_idx):
        """Rename bare lambda parameters such as ``var0 ->`` or
        ``(var0, var1) ->`` to ``argN``.  Block-bodied lambdas declared in a
        field initializer get their own scope; expression-bodied ones only
        live until the end of the enclosing statement."""
        tokens = self.tokens
        params = []
        prev = tokens[arrow_idx - 1]
        if prev[0] == "id" and VAR_RE.match(prev[1]):
            params.append(arrow_idx - 1)
        elif prev[1] == ")":
            op = self.close_to_open.get(arrow_idx - 1)
            if op is None:
                return
            j = op + 1
            depth = 0
            while j < arrow_idx - 1:
                t = tokens[j][1]
                if t in ("(", "[", "{"):
                    depth += 1
                elif t in (")", "]", "}"):
                    depth -= 1
                if depth == 0 and tokens[j][0] == "id" and VAR_RE.match(t):
                    nxt = (tokens[j + 1][1]
                           if j + 1 < len(tokens) else "")
                    prv = tokens[j - 1][1] if j - 1 >= 0 else ""
                    if nxt in (",", ")") and prv in ("(", ","):
                        params.append(j)
                j += 1
        if not params or not self.scopes:
            return

        def bindings_for():
            result = {}
            for idx in params:
                pname = tokens[idx][1]
                if any(pname in sc["bindings"] for sc in self.scopes):
                    continue
                m = VAR_RE.match(pname)
                new = self.make_name("arg", int(m.group(1)), m.group(2))
                result[idx] = (pname, new)
            return result

        if self.scopes[-1]["kind"] == "local":
            for idx, (pname, new) in bindings_for().items():
                self.scopes[-1]["bindings"][pname] = new
                self.replacements.append(
                    (tokens[idx][2], tokens[idx][3], new))
                self.stats["lambda_params"] += 1
            return

        # class-level lambda (field initializer)
        bindings = bindings_for()
        if not bindings:
            return
        for idx, (pname, new) in bindings.items():
            self.replacements.append((tokens[idx][2], tokens[idx][3], new))
            self.stats["lambda_params"] += 1
        block = (arrow_idx + 1 < len(tokens)
                 and tokens[arrow_idx + 1][1] == "{")
        if block:
            self.lambda_pending[arrow_idx] = {
                pname: new for pname, new in bindings.values()}
        else:
            scope = self.scopes[-1]
            depth = len(self.scopes)
            for pname, new in bindings.values():
                scope["bindings"][pname] = new
                self.temp_bindings.append((depth, scope, pname, new))


def generated_names(src):
    """All readable names the renamer would invent for *src*."""
    renamer = Renamer(src)
    renamer.run()
    return renamer.generated_names


def count_remaining(text):
    """Number of varN / varNx identifiers left in code (not strings/comments)."""
    return sum(1 for t in tokenize(text) if t[0] == "id" and VAR_RE.match(t[1]))


def clean_text(src):
    """Rename all ``varN`` local/parameter identifiers in *src*.
    Returns ``(new_src, stats, base_counts)``."""
    renamer = Renamer(src)
    out = renamer.run()
    renamer.stats["leftover"] = count_remaining(out)
    return out, renamer.stats, renamer.base_counts


# --------------------------------------------------------------------------
# file walking / modes
# --------------------------------------------------------------------------

def iter_java_files(root):
    for dirpath, dirnames, filenames in os.walk(root):
        dirnames.sort()
        for fn in sorted(filenames):
            if fn.endswith(".java"):
                yield os.path.join(dirpath, fn)


def read_text(path):
    with open(path, "rb") as f:
        return f.read().decode("utf-8", "surrogateescape")


def write_text(path, text):
    with open(path, "wb") as f:
        f.write(text.encode("utf-8", "surrogateescape"))


def jdk_javac():
    javac = shutil.which("javac")
    if javac:
        return javac
    home = os.path.expanduser("~/.sdkman/candidates/java")
    for name in ("21.0.12-amzn", "current"):
        cand = os.path.join(home, name, "bin", "javac")
        if os.path.exists(cand):
            return cand
    return None


def token_stream_equal(orig, new):
    """Check that *new* differs from *orig* only in identifier text."""
    a = tokenize(orig)
    b = tokenize(new)
    if len(a) != len(b):
        return False, f"token count {len(a)} -> {len(b)}"
    for ta, tb in zip(a, b):
        if ta[1] == tb[1]:
            continue
        if ta[0] == "id" and tb[0] == "id":
            continue
        return False, f"non-identifier token changed: {ta[1]!r} -> {tb[1]!r}"
    return True, ""


def run_rename(args):
    root = args.root
    stats = Counter()
    base_counts = Counter()
    changed = 0
    files = 0
    for path in iter_java_files(root):
        if args.limit and files >= args.limit:
            break
        files += 1
        try:
            src = read_text(path)
        except OSError as exc:
            print(f"!! cannot read {path}: {exc}", file=sys.stderr)
            continue
        if not VAR_TOKEN_RE.search(src):
            continue
        new, st, bc = clean_text(src)
        stats.update(st)
        base_counts.update(bc)
        if new != src:
            changed += 1
            if args.verbose:
                print(f"  {os.path.relpath(path, root)}: "
                      f"{sum(st.values())} edits")
            if args.apply:
                write_text(path, new)
    mode = "rewrote" if args.apply else "would rewrite"
    print(f"[clean_locals] {files} files scanned, {mode} {changed}")
    print(f"[clean_locals] renamed: {stats['locals']} locals, "
          f"{stats['parameters']} parameters, "
          f"{stats['lambda_params']} lambda parameters; "
          f"{stats['uses']} use-sites rewritten; "
          f"{stats['leftover']} varN identifiers left untouched")
    if base_counts:
        top = ", ".join(f"{k}:{v}" for k, v in base_counts.most_common(12))
        print(f"[clean_locals] name bases: {top}")
    return 0


def run_check(args):
    root = args.root
    javac = jdk_javac() if args.javac else None
    problems = 0
    checked = 0
    leftover_total = 0
    leftover_files = 0
    unbalanced = 0
    compared = 0
    compare_bad = 0
    javac_fail = 0
    javac_done = 0
    misrename = 0
    for path in iter_java_files(root):
        if args.limit and checked >= args.limit:
            break
        checked += 1
        src = read_text(path)
        rel = os.path.relpath(path, root)

        tokens = tokenize(src)
        open_to_close, _ = compute_matches(tokens)
        opens = sum(1 for t in tokens if t[0] == "punct" and t[1] in PAIRS)
        closes = sum(1 for t in tokens if t[0] == "punct" and t[1] in CLOSERS)
        if opens != closes or len(open_to_close) != opens:
            unbalanced += 1
            problems += 1
            print(f"[check] UNBALANCED delimiters: {rel}")

        leftover = sum(1 for t in tokens
                       if t[0] == "id" and VAR_RE.match(t[1]))
        if leftover:
            leftover_total += leftover
            leftover_files += 1
            if args.verbose:
                names = Counter(t[1] for t in tokens
                                if t[0] == "id" and VAR_RE.match(t[1]))
                print(f"[check] {rel}: {leftover} varN left "
                      f"({', '.join(sorted(names)[:6])})")

        orig_src = None
        if args.compare:
            orig_path = os.path.join(args.compare, rel)
            if os.path.exists(orig_path):
                orig_src = read_text(orig_path)
                ok, why = token_stream_equal(orig_src, src)
                compared += 1
                if not ok:
                    compare_bad += 1
                    problems += 1
                    print(f"[check] TOKEN MISMATCH vs origin: {rel}: {why}")

        if javac and (args.javac_limit == 0 or javac_done < args.javac_limit):
            javac_done += 1
            syntax, missing_vars = compile_probe(javac, path)
            if syntax is not None:
                javac_fail += 1
                problems += 1
                print(f"[check] JAVAC SYNTAX ERROR: {rel}\n    {syntax}")
            if orig_src is not None and missing_vars:
                bad = missing_vars & generated_names(orig_src)
                if bad:
                    misrename += 1
                    problems += 1
                    print(f"[check] MIS-RENAME (generated names not "
                          f"declared): {rel}: {', '.join(sorted(bad)[:8])}")

    print(f"[check] scanned {checked} files")
    print(f"[check] unbalanced delimiter files: {unbalanced}")
    print(f"[check] files with remaining varN: {leftover_files} "
          f"({leftover_total} identifiers)")
    if args.compare:
        print(f"[check] compared against origin: {compared} files, "
              f"{compare_bad} mismatches")
    if javac:
        print(f"[check] javac probed {javac_done} files, {javac_fail} "
              f"syntax failures, {misrename} mis-renames")
    if args.strict and leftover_total:
        problems += 1
    print(f"[check] result: {'FAIL' if problems else 'ok'}")
    return 1 if problems else 0


def compile_probe(javac, path):
    """Run javac on one file.

    Returns ``(syntax_error, missing_variables)``: the first syntax-shaped
    error line (or None), and every name javac reports as an unresolved
    ``variable`` (package/type errors from missing dependencies are ignored).
    """
    with tempfile.TemporaryDirectory(prefix="clean_locals_javac_") as tmp:
        cmd = [javac, "-nowarn", "-proc:none", "-Xmaxerrs", "100000",
               "-d", tmp, path]
        try:
            proc = subprocess.run(cmd, capture_output=True, text=True,
                                  timeout=120)
        except (OSError, subprocess.TimeoutExpired) as exc:
            return f"javac could not run: {exc}", set()
    out = (proc.stdout or "") + (proc.stderr or "")
    syntax = None
    missing = set()
    for line in out.splitlines():
        if syntax is None and JAVAC_SYNTAX_ERROR.search(line):
            syntax = line.strip()
        m = SYMBOL_VAR_RE.search(line)
        if m:
            missing.add(m.group(1))
    return syntax, missing


# --------------------------------------------------------------------------
# cli
# --------------------------------------------------------------------------

def default_root():
    here = os.path.dirname(os.path.abspath(__file__))
    cand = os.path.join(here, os.pardir, "src", "main", "java", "com",
                        "moonsworth")
    if os.path.isdir(cand):
        return os.path.normpath(cand)
    return "src/main/java/com/moonsworth"


def main(argv=None):
    parser = argparse.ArgumentParser(
        description="Give Vineflower varN locals/parameters readable names.")
    parser.add_argument("root", nargs="?", default=None,
                        help="source root (default: src/main/java/com/moonsworth)")
    parser.add_argument("--apply", action="store_true",
                        help="write changes (default is a dry run)")
    parser.add_argument("--check", action="store_true",
                        help="only validate the tree (no rewriting)")
    parser.add_argument("--strict", action="store_true",
                        help="with --check: also fail when varN identifiers "
                             "remain in code")
    parser.add_argument("--compare", metavar="ORIG_DIR",
                        help="with --check: compare token streams against an "
                             "unmodified tree with the same relative paths")
    parser.add_argument("--javac", action="store_true",
                        help="with --check: probe files with javac for syntax "
                             "errors (missing-dependency errors are ignored)")
    parser.add_argument("--javac-limit", type=int, default=25,
                        help="max files to probe with --javac (0 = all)")
    parser.add_argument("--limit", type=int, default=0,
                        help="only process the first N .java files")
    parser.add_argument("--verbose", action="store_true")
    args = parser.parse_args(argv)

    root = args.root or default_root()
    if not os.path.isdir(root):
        parser.error(f"source root not found: {root}")

    if args.check:
        return run_check(args)
    return run_rename(args)


if __name__ == "__main__":
    sys.exit(main())
