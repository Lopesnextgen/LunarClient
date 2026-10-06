package com.moonsworth.lunar.genesis;

class MixinHelper6$Data29 extends MixinHelper33_3 {
   private final char[][] field3;
   private final int field4;

   MixinHelper6$Data29(char[][] var1) {
      this.field3 = var1;
      this.field4 = var1.length;
   }

   @Override
   public String escape(String var1) {
      int var2 = var1.length();

      for (int var3 = 0; var3 < var2; var3++) {
         char var4 = var1.charAt(var3);
         if (var4 < this.field3.length && this.field3[var4] != null) {
            return this.method1(var1, var3);
         }
      }

      return var1;
   }

   @Override
   protected char[] escape(char var1) {
      return var1 < this.field4 ? this.field3[var1] : null;
   }
}
