package com.moonsworth.lunar.client.framework.feature.chat;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.adventure.pattern.ComponentPattern;
import com.lunarclient.adventure.transform.ComponentTransform;
import com.lunarclient.adventure.transform.ComponentTransformer;
import com.lunarclient.adventure.utils.ComponentType;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.event.mixin.EventChatMessage;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
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

public class ChatEmojiTransformer {
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

   public ChatEmojiTransformer() {
   }

   public void method1(EventChatMessage highlightimpl1) {
      Component component2 = highlightimpl1.method2();
      Component component3 = this.method8() ? this.method9().transform(component2) : component2;
      ComponentTransformer componenttransformer4 = this.method7() ? null : this.method10();
      if (componenttransformer4 != null) {
         component3 = componenttransformer4.transform(component3);
      }

      if (!component3.equals(component2)) {
         highlightimpl1.method1(component3);
      }
   }

   public String method2(String text1) {
      if (text1.indexOf(58) == -1) {
         return text1;
      }

      Matcher matcher2 = field1.matcher(text1);
      StringBuilder builder3 = null;

      while (matcher2.find()) {
         String text4 = this.method12().get(matcher2.group(1).toLowerCase(Locale.ROOT));
         String text5 = text4 == null ? null : this.method6(text4);
         if (text5 != null) {
            if (builder3 == null) {
               builder3 = new StringBuilder();
            }

            matcher2.appendReplacement(builder3, Matcher.quoteReplacement(text5));
         }
      }

      return builder3 == null ? text1 : matcher2.appendTail(builder3).toString();
   }

   public String method3(String text1) {
      int number2 = Math.max(256, text1.length());
      StringBuilder builder3 = null;

      for (int index4 = 0; index4 < text1.length(); index4++) {
         char character5 = text1.charAt(index4);
         String text6 = this.method17().get(String.valueOf(character5));
         if (text6 != null) {
            if (builder3 == null) {
               builder3 = new StringBuilder(text1.substring(0, index4));
            }

            String text7 = this.method14().get(String.valueOf(character5));
            int number8 = text1.length() - index4 - 1;
            if (text7 == null || text7.length() > 1 && builder3.length() + text7.length() + number8 > number2) {
               if (text7 == null && builder3.length() + text6.length() + 2 + number8 <= number2) {
                  builder3.append(':').append(text6).append(':');
               } else {
                  builder3.append(character5);
               }
            } else {
               builder3.append(text7);
            }
         } else if (builder3 != null) {
            builder3.append(character5);
         }
      }

      return builder3 == null ? text1 : builder3.toString();
   }

   public String method4(String text1) {
      StringBuilder builder2 = null;

      for (int index3 = 0; index3 < text1.length(); index3++) {
         char character4 = text1.charAt(index3);
         String text5 = this.method17().get(String.valueOf(character4));
         if (text5 != null) {
            if (builder2 == null) {
               builder2 = new StringBuilder(text1.substring(0, index3));
            }

            builder2.append(':').append(text5).append(':');
         } else if (builder2 != null) {
            builder2.append(character4);
         }
      }

      return builder2 == null ? text1 : builder2.toString();
   }

   public Map<String, String> method5(String text1) {
      String text2 = text1.toLowerCase(Locale.ROOT);
      LinkedHashMap map3 = new LinkedHashMap();

      for (Entry entry5 : this.method12().entrySet()) {
         if (((String)entry5.getKey()).startsWith(text2)) {
            String text6 = this.method6((String)entry5.getValue());
            if (text6 != null) {
               map3.put(":" + (String)entry5.getKey() + ":", text6);
               if (map3.size() >= 20) {
                  break;
               }
            }
         }
      }

      return map3;
   }

   private String method6(String text1) {
      String text2 = this.method13().get(text1);
      if (this.method7()) {
         return text2;
      } else {
         return text2 != null && this.method16().contains(text1) ? text2 : text1;
      }
   }

   private boolean method7() {
      return (Boolean)Ref.method4().method40().method47().method78().get();
   }

   private boolean method8() {
      return (Boolean)Ref.method4().method40().method47().method79().get();
   }

   private ComponentTransformer method9() {
      if (this.field12 == null) {
         this.field12 = this.method11(field1, arg1 -> this.method12().get(arg1.group(1).toLowerCase(Locale.ROOT)));
      }

      return this.field12;
   }

   @Nullable
   private ComponentTransformer method10() {
      if (this.field13 == null) {
         Map map1 = this.method15();
         if (map1.isEmpty()) {
            return null;
         }

         int[] items2 = map1.keySet().stream().mapToInt(arg0 -> arg0.codePointAt(0)).sorted().toArray();
         StringBuilder builder3 = new StringBuilder("[");

         for (int index4 = 0; index4 < items2.length; index4++) {
            int number5 = items2[index4];

            while (index4 + 1 < items2.length && items2[index4 + 1] == items2[index4] + 1) {
               index4++;
            }

            builder3.appendCodePoint(number5);
            if (items2[index4] > number5) {
               builder3.append('-').appendCodePoint(items2[index4]);
            }
         }

         builder3.append("]\\uFE0F?[");
         builder3.appendCodePoint(127995).append('-').appendCodePoint(127999);
         Pattern pattern6 = Pattern.compile(builder3.append("]?").toString());
         this.field13 = this.method11(pattern6, arg1x -> {
            String text2x = arg1x.group();

            while (!text2x.isEmpty()) {
               int number3x = text2x.codePointBefore(text2x.length());
               if (number3x != 65039 && (number3x < 127995 || number3x > 127999)) {
                  break;
               }

               text2x = text2x.substring(0, text2x.length() - Character.charCount(number3x));
            }

            return this.method15().get(text2x);
         });
      }

      return this.field13;
   }

   private ComponentTransformer method11(Pattern pattern1, Function<MatchResult, String> function2) {
      Key key3 = (Key)ResourceLocationBridge.create("lunar", "emoji");
      return ComponentTransformer.of(
         ComponentPattern.pattern(pattern1),
         ComponentTransform.builder()
            .functor(
               ComponentType.TEXT,
               (arg3x, arg4) -> {
                  String text5 = arg3x == null ? null : (String)function2.apply(arg3x);
                  String text6 = text5 == null ? null : this.method6(text5);
                  if (text6 == null) {
                     return arg4;
                  } else {
                     return !text6.equals(text5)
                        ? arg4.content(text6)
                        : (Builder)arg4.content("")
                           .append(Component.text(text5).style(arg1xx -> arg1xx.font(key3).color(NamedTextColor.WHITE).shadowColor(ShadowColor.none())));
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
         LinkedHashMap map1 = new LinkedHashMap();

         for (Entry entry3 : this.method12().entrySet()) {
            map1.putIfAbsent((String)entry3.getValue(), (String)entry3.getKey());
         }

         this.field7 = map1;
      }

      return this.field7;
   }

   @Nullable
   public static File method18() {
      try {
         Path path0 = LunarConstants.field7.resolve("emoji-font");
         if (!method19("/assets/lunar/font/emoji.json", "assets/lunar/font/emoji.json", path0)) {
            return null;
         }

         int index1 = 0;

         while (method19("/assets/lunar/textures/font/emoji_" + index1 + ".png", "assets/lunar/textures/font/emoji_" + index1 + ".png", path0)) {
            index1++;
         }

         if (index1 == 0) {
            return null;
         }

         Files.deleteIfExists(path0.resolve("assets/minecraft/font/default.json"));
         Files.deleteIfExists(path0.resolve("assets/minecraft/font/uniform.json"));
         field5 = true;
         return path0.toFile();
      } catch (IOException exception2) {
         CrashReporter.method5(exception2, "Extracting chat emoji font");
         return null;
      }
   }

   private static boolean method19(String text0, String text1, Path path2) {
      try (InputStream input3 = ChatEmojiTransformer.class.getResourceAsStream(text0)) {
         if (input3 == null) {
            return false;
         }

         Path path4 = path2.resolve(text1);
         Files.createDirectories(path4.getParent());
         Files.copy(input3, path4, StandardCopyOption.REPLACE_EXISTING);
         return true;
      }
   }

   private void loadMappings() {
      try (InputStreamReader reader1 = new InputStreamReader(ChatEmojiTransformer.class.getResourceAsStream("/assets/lunar/emoji/shortcodes.json"), StandardCharsets.UTF_8)) {
         JsonObject json2 = (JsonObject)new Gson().fromJson(reader1, JsonObject.class);
         this.field6 = method20(json2, "shortcodes");
         this.field8 = method20(json2, Ref.MC_VERSION >= 17 ? "unicodeModern" : "unicode");
         this.field9 = method20(json2, "unicodeAll");
         this.field10 = method20(json2, "characters");
         this.field11 = json2.get("textSymbols").getAsString().chars().mapToObj(arg0 -> String.valueOf((char)arg0)).collect(Collectors.toUnmodifiableSet());
      } catch (Exception exception6) {
         CrashReporter.method5(exception6, "Loading chat emoji shortcodes");
         this.field6 = Map.of();
         this.field8 = Map.of();
         this.field9 = Map.of();
         this.field10 = Map.of();
         this.field11 = Set.of();
      }
   }

   private static Map<String, String> method20(JsonObject json0, String text1) {
      LinkedHashMap map2 = new LinkedHashMap();

      for (Entry entry4 : json0.getAsJsonObject(text1).entrySet()) {
         map2.put((String)entry4.getKey(), ((JsonElement)entry4.getValue()).getAsString());
      }

      return map2;
   }

   @Generated
   public static boolean method21() {
      return field5;
   }
}
