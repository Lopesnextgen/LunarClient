package com.moonsworth.lunar.client.framework.feature.nickhider;

import com.moonsworth.lunar.bridge.LegacyFormattingSerializer;
import com.moonsworth.lunar.bridge.StyledCharSink;
import com.moonsworth.lunar.bridge.MixinHelper_8;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.kyori.adventure.text.format.Style;

public class NickhiderImpl implements StyledCharSink {
   private List<Object> field1 = new ArrayList<>();
   private StringBuilder field2 = new StringBuilder();

   public NickhiderImpl() {
   }

   public NickhiderImpl(Consumer<StyledCharSink> consumer1) {
      consumer1.accept(this);
   }

   public static NickhiderImpl method1(String text0) {
      return new NickhiderImpl(arg1 -> new MixinHelper_8(arg1).accept(text0));
   }

   public void method2(Object obj1, int number2) {
      this.field1.add(obj1);
      this.field2.appendCodePoint(number2);
      if (!Character.isBmpCodePoint(number2)) {
         this.field1.add(obj1);
      }
   }

   public boolean method1(int number1, Style style2, int number3) {
      this.method2(style2, number3);
      return true;
   }

   public void method4(Pattern pattern1, String text2) {
      Matcher matcher3 = pattern1.matcher(this.field2);
      if (matcher3.find()) {
         int number4 = text2.length();
         int number5 = 0;
         ArrayList list6 = new ArrayList();
         StringBuilder builder7 = new StringBuilder();

         do {
            int number8 = matcher3.start();
            int number9 = matcher3.end();

            for (int index10 = number5; index10 < number8; index10++) {
               list6.add(this.field1.get(index10));
               builder7.append(this.field2.charAt(index10));
            }

            int number16 = number9 - number8;
            float value11 = (float)number16 / number4;
            float value12 = number8;

            for (int index13 = 0; index13 < number4; index13++) {
               builder7.append(text2.charAt(index13));
               int index14 = Math.min((int)value12, number9);
               list6.add(this.field1.get(index14));
               value12 += value11;
            }

            number5 = number9;
         } while (matcher3.find());

         for (int index15 = number5; index15 < this.field1.size(); index15++) {
            list6.add(this.field1.get(index15));
            builder7.append(this.field2.charAt(index15));
         }

         if (list6.size() != builder7.length()) {
            throw new IllegalStateException(
               "Internal inconsistency while processing " + pattern1 + " -> " + text2 + " on " + this.field2 + " with styles " + this.field1
            );
         }

         this.field1 = list6;
         this.field2 = builder7;
      }
   }

   public void method5(NickhiderImpl.Extension extension1) {
      if (this.field1.size() != this.field2.length()) {
         throw new IllegalStateException();
      }

      for (int index2 = 0; index2 < this.field2.length(); index2++) {
         char character3 = this.field2.charAt(index2);
         int number4 = character3;
         if (Character.isHighSurrogate(character3) && index2 + 1 < this.field2.length()) {
            number4 = Character.toCodePoint(character3, this.field2.charAt(++index2));
         }

         extension1.accept(index2, this.field1.get(index2), number4);
      }
   }

   public void method6(StyledCharSink mixinhelper2_111) {
      this.method5((arg1x, arg2, arg3) -> mixinhelper2_111.method1(arg1x, (Style)arg2, arg3));
   }

   public String method7() {
      LegacyFormattingSerializer mixinhelper22_21 = new LegacyFormattingSerializer();
      this.method6(mixinhelper22_21);
      return mixinhelper22_21.getResult();
   }

   public interface Extension {
      void accept(int number1, Object obj2, int number3);
   }
}
