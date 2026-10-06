package com.moonsworth.lunar.genesis;
import com.google.common.annotations.GwtCompatible;
import com.google.common.escape.Escaper;
import com.google.common.base.Preconditions;

@Annotation2
@GwtCompatible
public abstract class MixinHelper33_3 extends Escaper {
   private static final int field2 = 2;

   protected MixinHelper33_3() {
   }

   @Override
   public String escape(String var1) {
      Preconditions.checkNotNull(var1);
      int var2 = var1.length();

      for (int var3 = 0; var3 < var2; var3++) {
         if (this.escape(var1.charAt(var3)) != null) {
            return this.method1(var1, var3);
         }
      }

      return var1;
   }

   protected abstract char[] escape(char var1);

   protected final String method1(String var1, int var2) {
      int var3 = var1.length();
      char[] var4 = MixinHelper5_2.charBufferFromThreadLocal();
      int var5 = var4.length;
      int var6 = 0;
      int var7 = 0;

      while (var2 < var3) {
         char[] var8 = this.escape(var1.charAt(var2));
         if (var8 != null) {
            int var9 = var8.length;
            int var10 = var2 - var7;
            int var11 = var6 + var10 + var9;
            if (var5 < var11) {
               var5 = var11 + 2 * (var3 - var2);
               var4 = growBuffer(var4, var6, var5);
            }

            if (var10 > 0) {
               var1.getChars(var7, var2, var4, var6);
               var6 += var10;
            }

            if (var9 > 0) {
               System.arraycopy(var8, 0, var4, var6, var9);
               var6 += var9;
            }

            var7 = var2 + 1;
         }

         var2++;
      }

      int var12 = var3 - var7;
      if (var12 > 0) {
         int var13 = var6 + var12;
         if (var5 < var13) {
            var4 = growBuffer(var4, var6, var13);
         }

         var1.getChars(var7, var3, var4, var6);
         var6 = var13;
      }

      return new String(var4, 0, var6);
   }

   private static char[] growBuffer(char[] var0, int var1, int var2) {
      if (var2 < 0) {
         throw new AssertionError("Cannot increase internal buffer any further");
      }

      char[] var3 = new char[var2];
      if (var1 > 0) {
         System.arraycopy(var0, 0, var3, 0, var1);
      }

      return var3;
   }
}
