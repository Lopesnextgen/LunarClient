import org.objectweb.asm.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;
import java.util.zip.*;

/**
 * Rewrites readable member references inside Mixin annotation strings to the
 * runtime names, using a JSON table produced by tools/kin_members.py.
 *
 * Usage: MemberRemap <in.jar> <members.json> <out.jar>
 */
public class MemberRemap {
    static Map<String, String> CLASS_OBF = new HashMap<>();
    static Map<String, Map<String, String>> MEMBERS = new HashMap<>();
    static final Pattern DECL = Pattern.compile("L?([A-Za-z0-9_/$]+);([A-Za-z0-9_$<>]+)");
    static final Pattern SUFFIX = Pattern.compile("\\$v1_(?:7|8|12)");
    static int rewritten = 0;

    public static void main(String[] args) throws Exception {
        parse(new String(Files.readAllBytes(Paths.get(args[1])), "UTF-8"));
        System.err.println("class entries: " + MEMBERS.size());
        try (ZipFile zip = new ZipFile(args[0]);
             ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(args[2]))) {
            Enumeration<? extends ZipEntry> en = zip.entries();
            while (en.hasMoreElements()) {
                ZipEntry e = en.nextElement();
                byte[] data = zip.getInputStream(e).readAllBytes();
                if (e.getName().endsWith(".class")) {
                    try {
                        ClassReader cr = new ClassReader(data);
                        ClassWriter cw = new ClassWriter(0);
                        ClassVisitor cv = new ClassVisitor(Opcodes.ASM9, cw) {
                            public MethodVisitor visitMethod(int a, String n, String d, String s, String[] ex) {
                                MethodVisitor mv = super.visitMethod(a, n, d, s, ex);
                                if (mv == null) return null;
                                return new MethodVisitor(Opcodes.ASM9, mv) {
                                    public AnnotationVisitor visitAnnotation(String dd, boolean vis) {
                                        return wrap(super.visitAnnotation(dd, vis));
                                    }
                                };
                            }
                            public FieldVisitor visitField(int a, String n, String d, String s, Object v) {
                                FieldVisitor fv = super.visitField(a, n, d, s, v);
                                if (fv == null) return null;
                                return new FieldVisitor(Opcodes.ASM9, fv) {
                                    public AnnotationVisitor visitAnnotation(String dd, boolean vis) {
                                        return wrap(super.visitAnnotation(dd, vis));
                                    }
                                };
                            }
                        };
                        cr.accept(cv, 0);
                        data = cw.toByteArray();
                    } catch (Exception ex) { }
                }
                zout.putNextEntry(new ZipEntry(e.getName()));
                zout.write(data);
                zout.closeEntry();
            }
        }
        System.err.println("strings rewritten: " + rewritten);
    }

    static AnnotationVisitor wrap(AnnotationVisitor av) {
        if (av == null) return null;
        return new AnnotationVisitor(Opcodes.ASM9, av) {
            public void visit(String name, Object value) {
                if (value instanceof String) value = sub((String) value);
                super.visit(name, value);
            }
            public AnnotationVisitor visitAnnotation(String name, String d) {
                return wrap(super.visitAnnotation(name, d));
            }
            public AnnotationVisitor visitArray(String name) {
                return wrap(super.visitArray(name));
            }
        };
    }

    static String sub(String s) {
        if (s.indexOf(';') < 0 && s.indexOf('$') < 0) return s;
        Matcher m = DECL.matcher(s);
        StringBuffer sb = new StringBuffer();
        boolean changed = false;
        while (m.find()) {
            String owner = m.group(1);
            String member = m.group(2);
            String stripped = SUFFIX.matcher(member).replaceAll("");
            String cls = CLASS_OBF.getOrDefault(owner, owner);
            Map<String, String> mem = MEMBERS.get(owner);
            if (mem == null) mem = MEMBERS.get(cls);
            String runtime = mem != null ? mem.get(member) : null;
            if (runtime == null && mem != null) runtime = mem.get(stripped);
            if (runtime == null) runtime = member;
            String rep = "L" + cls + ";" + runtime;
            if (!rep.equals(m.group(0).startsWith("L") ? m.group(0) : "L" + m.group(0))) changed = true;
            m.appendReplacement(sb, Matcher.quoteReplacement(rep));
        }
        m.appendTail(sb);
        String out = sb.toString();
        if (!out.equals(s)) rewritten++;
        return out;
    }

    // ---- minimal JSON parser -------------------------------------------------

    static String src;
    static int pos;

    static void parse(String text) {
        src = text;
        pos = 0;
        skipWs();
        expect('{');
        while (true) {
            skipWs();
            if (peek() == '}') { pos++; return; }
            String key = stringLit();
            skipWs(); expect(':');
            skipWs();
            if (key.equals("classes")) {
                expect('{');
                while (true) {
                    skipWs();
                    if (peek() == '}') { pos++; break; }
                    String cls = stringLit();
                    skipWs(); expect(':');
                    parseClass(cls);
                    skipWs();
                    if (peek() == ',') { pos++; continue; }
                }
            } else {
                skipValue();
            }
            skipWs();
            if (peek() == ',') { pos++; continue; }
            if (peek() == '}') { pos++; return; }
        }
    }

    static void parseClass(String cls) {
        expect('{');
        while (true) {
            skipWs();
            if (peek() == '}') { pos++; return; }
            String key = stringLit();
            skipWs(); expect(':');
            skipWs();
            if (key.equals("obf")) {
                CLASS_OBF.put(cls, stringLit());
            } else if (key.equals("members")) {
                Map<String, String> map = MEMBERS.computeIfAbsent(cls, k -> new HashMap<>());
                expect('{');
                while (true) {
                    skipWs();
                    if (peek() == '}') { pos++; break; }
                    String name = stringLit();
                    skipWs(); expect(':');
                    map.put(name, stringLit());
                    skipWs();
                    if (peek() == ',') { pos++; continue; }
                }
            } else {
                skipValue();
            }
            skipWs();
            if (peek() == ',') { pos++; continue; }
            if (peek() == '}') { pos++; return; }
        }
    }

    static void skipValue() {
        char c = peek();
        if (c == '{' || c == '[') {
            char open = c, close = c == '{' ? '}' : ']';
            int depth = 0;
            while (pos < src.length()) {
                char ch = src.charAt(pos++);
                if (ch == open) depth++;
                else if (ch == close) { depth--; if (depth == 0) return; }
                else if (ch == '"') pos = skipString(pos);
            }
        } else if (c == '"') {
            pos = skipString(pos);
        } else {
            while (pos < src.length() && ",}]".indexOf(src.charAt(pos)) < 0) pos++;
        }
    }

    static int skipString(int start) {
        int i = start + 1;
        while (i < src.length()) {
            char c = src.charAt(i++);
            if (c == '\\') i++;
            else if (c == '"') return i;
        }
        return i;
    }

    static char peek() { return pos < src.length() ? src.charAt(pos) : 0; }
    static void expect(char c) { skipWs(); if (peek() == c) pos++; }
    static void skipWs() { while (pos < src.length() && Character.isWhitespace(src.charAt(pos))) pos++; }

    static String stringLit() {
        skipWs();
        if (peek() != '"') return "";
        pos++;
        StringBuilder sb = new StringBuilder();
        while (pos < src.length()) {
            char c = src.charAt(pos++);
            if (c == '\\') {
                char n = src.charAt(pos++);
                switch (n) {
                    case 'n': sb.append('\n'); break;
                    case 't': sb.append('\t'); break;
                    case 'u': sb.append((char) Integer.parseInt(src.substring(pos, pos + 4), 16)); pos += 4; break;
                    default: sb.append(n);
                }
            } else if (c == '"') return sb.toString();
            else sb.append(c);
        }
        return sb.toString();
    }
}
