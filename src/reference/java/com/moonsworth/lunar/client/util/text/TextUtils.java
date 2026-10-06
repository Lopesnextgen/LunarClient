package com.moonsworth.lunar.client.util.text;

import com.google.common.base.CaseFormat;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import org.apache.commons.lang3.text.WordUtils;
import org.intellij.lang.annotations.Subst;

public final class TextUtils {
   public static List<String> wrapText(String text0, float value, boolean flag2) {
      value -= flag2 ? 15.5F : 0.0F;
      if (!text0.contains(" ")) {
         return FontRegistry.method17().method26(text0, value);
      }

      ArrayList list3 = new ArrayList();
      StringBuilder builder4 = new StringBuilder();
      float value5 = 0.0F;

      for (String text9 : text0.split(" ")) {
         float value10 = value5 + FontRegistry.method17().method4(text9);
         if (value10 < value) {
            builder4.append(text9).append(" ");
            value5 = value10 + 1.0F;
         } else if (value5 == 0.0F) {
            list3.addAll(wrapText(text9, value, false));
         } else {
            list3.add(builder4.toString().charAt(builder4.length() - 1) == ' ' ? builder4.toString().substring(0, builder4.toString().length() - 1) : builder4.toString());
            value5 = FontRegistry.method17().method4(text9) + 1.0F;
            builder4 = new StringBuilder(text9).append(" ");
         }
      }

      list3.add(builder4.toString().charAt(builder4.length() - 1) == ' ' ? builder4.toString().substring(0, builder4.toString().length() - 1) : builder4.toString());
      return list3;
   }

   public static boolean isValidPlayerName(String text0) {
      return text0.length() <= 16 && text0.chars().filter(arg0x -> arg0x <= 32 || arg0x >= 127).findAny().isEmpty();
   }

   public static String capitalizeFirstLetter(String text0) {
      return text0.isEmpty() ? " " : text0.substring(0, 1).toUpperCase();
   }

   public static String capitalize(String text0) {
      String text1 = text0.replaceAll("_", " ");
      return WordUtils.capitalize(text1);
   }

   @Subst("UPPER_SNAKE")
   public static String toUpperSnakeCase(String text0) {
      text0 = text0.replace("-", "_");
      StringBuilder builder1 = new StringBuilder(String.valueOf(Character.toUpperCase(text0.charAt(0))));
      boolean flag2 = false;

      for (int index3 = 1; index3 < text0.length(); index3++) {
         char character4 = text0.charAt(index3);
         if (character4 == '_') {
            flag2 = true;
            builder1.append(character4);
         } else if (Character.isLowerCase(character4)) {
            flag2 = false;
            builder1.append(Character.toUpperCase(character4));
         } else {
            if (!flag2) {
               builder1.append("_");
            }

            flag2 = true;
            builder1.append(character4);
         }
      }

      return builder1.toString();
   }

   @Subst("CamelCase")
   public static String toCamelCase(String text0, boolean flag) {
      text0 = text0.replace("-", "_");
      StringBuilder builder2 = new StringBuilder();
      boolean flag3 = !flag;

      for (int index4 = 0; index4 < text0.length(); index4++) {
         char character5 = text0.charAt(index4);
         if (character5 == '_') {
            flag3 = false;
         } else if (flag3) {
            builder2.append(Character.toLowerCase(character5));
         } else {
            builder2.append(Character.toUpperCase(character5));
            flag3 = true;
         }
      }

      return builder2.toString();
   }

   @Subst("kebab-case")
   public static String toKebabCase(String text0) {
      return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, text0);
   }

   @Generated
   private TextUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
