package com.moonsworth.lunar.genesis;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
final class MixinHelper15_2 {
   final String field1;
   final int field2;

   private MixinHelper15_2(String var1, int var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   static MixinHelper15_2 method1(String var0) {
      if (var0.length() == 0) {
         throw new NumberFormatException("empty string");
      }

      char var3 = var0.charAt(0);
      String var1;
      byte var2;
      if (var0.startsWith("0x") || var0.startsWith("0X")) {
         var1 = var0.substring(2);
         var2 = 16;
      } else if (var3 == '#') {
         var1 = var0.substring(1);
         var2 = 16;
      } else if (var3 == '0' && var0.length() > 1) {
         var1 = var0.substring(1);
         var2 = 8;
      } else {
         var1 = var0;
         var2 = 10;
      }

      return new MixinHelper15_2(var1, var2);
   }
}
