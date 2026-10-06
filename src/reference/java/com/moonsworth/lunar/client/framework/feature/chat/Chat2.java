package com.moonsworth.lunar.client.framework.feature.chat;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.adventure.pattern.ComponentPattern;
import com.lunarclient.adventure.transform.ComponentTransform;
import com.lunarclient.adventure.transform.ComponentTransformer;
import com.lunarclient.adventure.utils.ComponentType;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.Generated;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent.Builder;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.ShadowColor;
import org.jetbrains.annotations.Nullable;

public class Chat2 {
   private static final Pattern field1 = Pattern.compile(":([A-Za-z0-9_+-]+):");
   private static final String field2 = "/assets/lunar/";
   private static final String field3 = "font/emoji.json";
   private static final int field4 = 20;
   private static boolean field5;
   private Map<String, String> field6;
   private Map<String, String> field7;
   private Map<String, String> field8;
   private Map<String, String> field9;
   private Map<String, String> field10;
   private Set<String> field11;
   private ComponentTransformer field12;
   private ComponentTransformer field13;

   public void method1(EventChatMessageLegacy var1) {
      Component var2 = var1.method2();
      Component var3 = this.method8() ? this.method9().transform(var2) : var2;
      ComponentTransformer var4 = this.method7() ? null : this.method10();
      if (var4 != null) {
         var3 = var4.transform(var3);
      }

      if (!var3.equals(var2)) {
         var1.method1(var3);
      }
   }

   public String method2(String var1) {
      if (var1.indexOf(58) == -1) {
         return var1;
      }

      Matcher var2 = field1.matcher(var1);
      StringBuilder var3 = null;

      while (var2.find()) {
         String var4 = this.method12().get(var2.group(1).toLowerCase(Locale.ROOT));
         String var5 = var4 == null ? null : this.method6(var4);
         if (var5 != null) {
            if (var3 == null) {
               var3 = new StringBuilder();
            }

            var2.appendReplacement(var3, Matcher.quoteReplacement(var5));
         }
      }

      return var3 == null ? var1 : var2.appendTail(var3).toString();
   }

   public String method3(String var1) {
      int var2 = Math.max(256, var1.length());
      StringBuilder var3 = null;

      for (int var4 = 0; var4 < var1.length(); var4++) {
         char var5 = var1.charAt(var4);
         String var6 = this.method17().get(String.valueOf(var5));
         if (var6 != null) {
            if (var3 == null) {
               var3 = new StringBuilder(var1.substring(0, var4));
            }

            String var7 = this.method14().get(String.valueOf(var5));
            int var8 = var1.length() - var4 - 1;
            if (var7 == null || var7.length() > 1 && var3.length() + var7.length() + var8 > var2) {
               if (var7 == null && var3.length() + var6.length() + 2 + var8 <= var2) {
                  var3.append(':').append(var6).append(':');
               } else {
                  var3.append(var5);
               }
            } else {
               var3.append(var7);
            }
         } else if (var3 != null) {
            var3.append(var5);
         }
      }

      return var3 == null ? var1 : var3.toString();
   }

   public String method4(String var1) {
      StringBuilder var2 = null;

      for (int var3 = 0; var3 < var1.length(); var3++) {
         char var4 = var1.charAt(var3);
         String var5 = this.method17().get(String.valueOf(var4));
         if (var5 != null) {
            if (var2 == null) {
               var2 = new StringBuilder(var1.substring(0, var3));
            }

            var2.append(':').append(var5).append(':');
         } else if (var2 != null) {
            var2.append(var4);
         }
      }

      return var2 == null ? var1 : var2.toString();
   }

   public Map<String, String> method5(String var1) {
      String var2 = var1.toLowerCase(Locale.ROOT);
      LinkedHashMap var3 = new LinkedHashMap();

      for (Entry var5 : this.method12().entrySet()) {
         if (((String)var5.getKey()).startsWith(var2)) {
            String var6 = this.method6((String)var5.getValue());
            if (var6 != null) {
               var3.put(":" + (String)var5.getKey() + ":", var6);
               if (var3.size() >= 20) {
                  break;
               }
            }
         }
      }

      return var3;
   }

   private String method6(String var1) {
      String var2 = this.method13().get(var1);
      if (this.method7()) {
         return var2;
      } else {
         return var2 != null && this.method16().contains(var1) ? var2 : var1;
      }
   }

   private boolean method7() {
      return (Boolean)ThreadModuleDump63.method4().method40().method47().method78().get();
   }

   private boolean method8() {
      return (Boolean)ThreadModuleDump63.method4().method40().method47().method79().get();
   }

   private ComponentTransformer method9() {
      if (this.field12 == null) {
         this.field12 = this.method11(field1, var1 -> this.method12().get(var1.group(1).toLowerCase(Locale.ROOT)));
      }

      return this.field12;
   }

   @Nullable
   private ComponentTransformer method10() {
      if (this.field13 == null) {
         Map var1 = this.method15();
         if (var1.isEmpty()) {
            return null;
         }

         int[] var2 = var1.keySet().stream().mapToInt(var0 -> var0.codePointAt(0)).sorted().toArray();
         StringBuilder var3 = new StringBuilder("[");

         for (int var4 = 0; var4 < var2.length; var4++) {
            int var5 = var2[var4];

            while (var4 + 1 < var2.length && var2[var4 + 1] == var2[var4] + 1) {
               var4++;
            }

            var3.appendCodePoint(var5);
            if (var2[var4] > var5) {
               var3.append('-').appendCodePoint(var2[var4]);
            }
         }

         var3.append("]\\uFE0F?[");
         var3.appendCodePoint(127995).append('-').appendCodePoint(127999);
         Pattern var6 = Pattern.compile(var3.append("]?").toString());
         this.field13 = this.method11(var6, var1x -> {
            String var2x = var1x.group();

            while (!var2x.isEmpty()) {
               int var3x = var2x.codePointBefore(var2x.length());
               if (var3x != 65039 && (var3x < 127995 || var3x > 127999)) {
                  break;
               }

               var2x = var2x.substring(0, var2x.length() - Character.charCount(var3x));
            }

            return this.method15().get(var2x);
         });
      }

      return this.field13;
   }

   private ComponentTransformer method11(Pattern var1, Function<MatchResult, String> var2) {
      Key var3 = (Key)ResourceLocationBridge.create("lunar", "emoji");
      return ComponentTransformer.of(
         ComponentPattern.pattern(var1),
         ComponentTransform.builder()
            .functor(
               ComponentType.TEXT,
               (var3x, var4) -> {
                  String var5 = var3x == null ? null : (String)var2.apply(var3x);
                  String var6 = var5 == null ? null : this.method6(var5);
                  if (var6 == null) {
                     return var4;
                  } else {
                     return !var6.equals(var5)
                        ? var4.content(var6)
                        : (Builder)var4.content("")
                           .append(Component.text(var5).style(var1xx -> var1xx.font(var3).color(NamedTextColor.WHITE).shadowColor(ShadowColor.none())));
                  }
               }
            )
            .build()
      );
   }

   private Map<String, String> method12() {
      if (this.field6 == null) {
         this.loadMappings();
      }

      return this.field6;
   }

   private Map<String, String> method13() {
      if (this.field8 == null) {
         this.loadMappings();
      }

      return this.field8;
   }

   private Map<String, String> method14() {
      if (this.field9 == null) {
         this.loadMappings();
      }

      return this.field9;
   }

   private Map<String, String> method15() {
      if (this.field10 == null) {
         this.loadMappings();
      }

      return this.field10;
   }

   private Set<String> method16() {
      if (this.field11 == null) {
         this.loadMappings();
      }

      return this.field11;
   }

   private Map<String, String> method17() {
      if (this.field7 == null) {
         LinkedHashMap var1 = new LinkedHashMap();

         for (Entry var3 : this.method12().entrySet()) {
            var1.putIfAbsent((String)var3.getValue(), (String)var3.getKey());
         }

         this.field7 = var1;
      }

      return this.field7;
   }

   @Nullable
   public static File method18() {
      try {
         Path var0 = ThreadModuleDump48.field7.resolve("emoji-font");
         if (!method19("/assets/lunar/font/emoji.json", "assets/lunar/font/emoji.json", var0)) {
            return null;
         }

         int var1 = 0;

         while (method19("/assets/lunar/textures/font/emoji_" + var1 + ".png", "assets/lunar/textures/font/emoji_" + var1 + ".png", var0)) {
            var1++;
         }

         if (var1 == 0) {
            return null;
         }

         Files.deleteIfExists(var0.resolve("assets/minecraft/font/default.json"));
         Files.deleteIfExists(var0.resolve("assets/minecraft/font/uniform.json"));
         field5 = true;
         return var0.toFile();
      } catch (IOException var2) {
         Inventorymod2.method5(var2, "Extracting chat emoji font");
         return null;
      }
   }

   private static boolean method19(String var0, String var1, Path var2) {
      try (InputStream var3 = Chat2.class.getResourceAsStream(var0)) {
         if (var3 == null) {
            return false;
         }

         Path var4 = var2.resolve(var1);
         Files.createDirectories(var4.getParent());
         Files.copy(var3, var4, StandardCopyOption.REPLACE_EXISTING);
         return true;
      }
   }

   private void loadMappings() {
      try (InputStreamReader var1 = new InputStreamReader(Chat2.class.getResourceAsStream("/assets/lunar/emoji/shortcodes.json"), StandardCharsets.UTF_8)) {
         JsonObject var2 = (JsonObject)new Gson().fromJson(var1, JsonObject.class);
         this.field6 = method20(var2, "shortcodes");
         this.field8 = method20(var2, ThreadModuleDump63.MC_VERSION >= 17 ? "unicodeModern" : "unicode");
         this.field9 = method20(var2, "unicodeAll");
         this.field10 = method20(var2, "characters");
         this.field11 = var2.get("textSymbols").getAsString().chars().mapToObj(var0 -> String.valueOf((char)var0)).collect(Collectors.toUnmodifiableSet());
      } catch (Exception var6) {
         Inventorymod2.method5(var6, "Loading chat emoji shortcodes");
         this.field6 = Map.of();
         this.field8 = Map.of();
         this.field9 = Map.of();
         this.field10 = Map.of();
         this.field11 = Set.of();
      }
   }

   private static Map<String, String> method20(JsonObject var0, String var1) {
      LinkedHashMap var2 = new LinkedHashMap();

      for (Entry var4 : var0.getAsJsonObject(var1).entrySet()) {
         var2.put((String)var4.getKey(), ((JsonElement)var4.getValue()).getAsString());
      }

      return var2;
   }

   @Generated
   public static boolean method21() {
      return field5;
   }
}
