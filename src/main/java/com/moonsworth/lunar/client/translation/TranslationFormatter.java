package com.moonsworth.lunar.client.translation;

import com.moonsworth.lunar.client.chat.translation.TranslationVariable;
import com.moonsworth.lunar.client.chat.translation.CachedReplacement;
import com.moonsworth.lunar.client.chat.translation.CachedReplacement.Data;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;

public final class TranslationFormatter {
   private static final Pattern field1 = Pattern.compile("(?<raw>[$%](?<id>\\d+)?(?<i18n>\\$[sdf])?(\\{(?<name>[a-zA-Z0-9]+)})?)");

   public static String method1(String text0, Object... items1) {
      String text2 = text0;
      if (text2.contains("$")) {
         Matcher matcher3 = field1.matcher(text2);

         while (matcher3.find()) {
            String text4 = matcher3.group("raw");
            String text5 = matcher3.group("id");
            String text6 = matcher3.group("name");
            String text7 = matcher3.group("i18n");
            boolean flag8 = text7 != null;
            boolean flag9 = text6 != null;
            if (flag9) {
               text2 = text2.replace(text4, Objects.requireNonNull(TranslationVariable.getReplacement(text6)));
            } else if (text5 != null) {
               try {
                  int index10 = Integer.parseInt(text5) - (flag8 ? 1 : 0);
                  text2 = text2.replace(text4, items1[index10].toString());
               } catch (Exception exception11) {
               }
            }
         }
      }

      return text2;
   }

   public static CachedReplacement method2(String text0, Object... items1) {
      Data data2 = CachedReplacement.method5();
      Matcher matcher3 = field1.matcher(text0);

      int index4;
      for (index4 = 0; matcher3.find(); index4 = matcher3.end()) {
         String text5 = matcher3.group("id");
         String text6 = matcher3.group("name");
         String text7 = matcher3.group("i18n");
         data2.method1(text0.substring(index4, matcher3.start()));
         if (text6 != null) {
            TranslationVariable calculatortype213 = TranslationVariable.getVariable(text6);
            if (calculatortype213 != null) {
               if (calculatortype213.isDynamic()) {
                  data2.method2(calculatortype213.getReplacement());
               } else {
                  data2.method1(calculatortype213.getReplacement().get().toString());
               }
            } else {
               data2.method1("null");
            }
         } else if (text5 != null) {
            try {
               int number8 = Integer.parseInt(text5);
               int index9 = number8 - (text7 != null ? 1 : 0);
               if (index9 >= 0 && index9 < items1.length) {
                  Object obj10 = items1[index9];
                  if (obj10 instanceof Supplier supplier11) {
                     data2.method2(supplier11);
                  } else {
                     data2.method1(obj10.toString());
                  }
               } else {
                  data2.method1("null");
               }
            } catch (NumberFormatException numberformatexception12) {
            }
         }
      }

      data2.method1(text0.substring(index4));
      return data2.method5();
   }

   public static CachedReplacement method3(String text0) {
      Data data1 = CachedReplacement.method5();
      Matcher matcher2 = field1.matcher(text0);

      int index3;
      for (index3 = 0; matcher2.find(); index3 = matcher2.end()) {
         String text4 = matcher2.group("id");
         String text5 = matcher2.group("name");
         data1.method1(text0.substring(index3, matcher2.start()));
         if (text5 != null) {
            TranslationVariable calculatortype26 = TranslationVariable.getVariable(text5);
            if (calculatortype26 != null) {
               if (calculatortype26.isDynamic()) {
                  data1.method2(calculatortype26.getReplacement());
               } else {
                  data1.method1(calculatortype26.getReplacement().get().toString());
               }
            } else {
               data1.method1("null");
            }
         } else if (text4 != null) {
            data1.method3();
         }
      }

      data1.method1(text0.substring(index3));
      return data1.method5();
   }

   public static String method4(String text0) {
      return Normalizer.normalize(text0, Form.NFD).replaceAll("[^A-Za-z0-9.]", "").toLowerCase();
   }

   @Generated
   private TranslationFormatter() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
