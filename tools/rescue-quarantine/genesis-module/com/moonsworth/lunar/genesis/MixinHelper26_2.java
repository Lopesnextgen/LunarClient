package com.moonsworth.lunar.genesis;

import java.util.BitSet;
import com.google.common.base.CharMatcher;

@Annotation3
final class MixinHelper26_2 extends CharMatcher.Data6 {
   static final int field3 = 1023;
   private final char[] field4;
   private final boolean field5;
   private final long field6;
   private static final int field7 = -862048943;
   private static final int field8 = 461845907;
   private static final double field9 = 0.5;

   private MixinHelper26_2(char[] var1, long var2, boolean var4, String var5) {
      super(var5);
      this.field4 = var1;
      this.field6 = var2;
      this.field5 = var4;
   }

   static int smear(int var0) {
      return 461845907 * Integer.rotateLeft(var0 * -862048943, 15);
   }

   private boolean checkFilter(int var1) {
      return 1L == (1L & this.field6 >> var1);
   }

   @Annotation4
   static int chooseTableSize(int var0) {
      if (var0 == 1) {
         return 2;
      }

      int var1 = Integer.highestOneBit(var0 - 1) << 1;

      while (var1 * 0.5 < var0) {
         var1 <<= 1;
      }

      return var1;
   }

   static CharMatcher method1(BitSet var0, String var1) {
      long var2 = 0L;
      int var4 = var0.cardinality();
      boolean var5 = var0.get(0);
      char[] var6 = new char[chooseTableSize(var4)];
      int var7 = var6.length - 1;

      for (int var8 = var0.nextSetBit(0); var8 != -1; var8 = var0.nextSetBit(var8 + 1)) {
         var2 |= 1L << var8;
         int var9 = smear(var8) & var7;

         while (var6[var9] != 0) {
            var9 = var9 + 1 & var7;
         }

         var6[var9] = (char)var8;
      }

      return new MixinHelper26_2(var6, var2, var5, var1);
   }

   @Override
   public boolean matches(char var1) {
      if (var1 == 0) {
         return this.field5;
      }

      if (!this.checkFilter(var1)) {
         return false;
      }

      int var2 = this.field4.length - 1;
      int var3 = smear(var1) & var2;
      int var4 = var3;

      while (this.field4[var4] != 0) {
         if (this.field4[var4] == var1) {
            return true;
         }

         var4 = var4 + 1 & var2;
         if (var4 == var3) {
            return false;
         }
      }

      return false;
   }

   @Override
   void setBits(BitSet var1) {
      if (this.field5) {
         var1.set(0);
      }

      for (char var5 : this.field4) {
         if (var5 != 0) {
            var1.set(var5);
         }
      }
   }
}
