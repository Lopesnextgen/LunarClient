package com.moonsworth.lunar.genesis;
import com.google.common.annotations.GwtCompatible;
import com.google.common.escape.Escaper;

@Annotation2
@GwtCompatible
public class MixinHelper2_2 {
   private static final char field1 = '\u0000';
   private static final char field2 = '\u001f';
   private static final Escaper field3;
   private static final Escaper field4;
   private static final Escaper field5;

   private MixinHelper2_2() {
   }

   public static Escaper method1() {
      return field4;
   }

   public static Escaper method2() {
      return field5;
   }

   static {
      MixinHelper4$Data21 var0 = MixinHelper4_4.method2();
      var0.method1('\u0000', '�');
      var0.method2("�");

      for (char var1 = 0; var1 <= 31; var1++) {
         if (var1 != '\t' && var1 != '\n' && var1 != '\r') {
            var0.method3(var1, "�");
         }
      }

      var0.method3('&', "&amp;");
      var0.method3('<', "&lt;");
      var0.method3('>', "&gt;");
      field4 = var0.method4();
      var0.method3('\'', "&apos;");
      var0.method3('"', "&quot;");
      field3 = var0.method4();
      var0.method3('\t', "&#x9;");
      var0.method3('\n', "&#xA;");
      var0.method3('\r', "&#xD;");
      field5 = var0.method4();
   }
}
