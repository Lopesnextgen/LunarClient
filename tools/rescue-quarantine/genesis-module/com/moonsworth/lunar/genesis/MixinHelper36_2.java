package com.moonsworth.lunar.genesis;

import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
final class MixinHelper36_2 {
   private static final long field1 = -862048943L;
   private static final long field2 = 461845907L;
   private static final int field3 = 1073741824;

   private MixinHelper36_2() {
   }

   static int smear(int var0) {
      return (int)(461845907L * Integer.rotateLeft((int)(var0 * -862048943L), 15));
   }

   static int smearedHash(@Nullable Object var0) {
      return smear(var0 == null ? 0 : var0.hashCode());
   }

   static int closedTableSize(int var0, double var1) {
      var0 = Math.max(var0, 2);
      int var3 = Integer.highestOneBit(var0);
      if (var0 > (int)(var1 * var3)) {
         var3 <<= 1;
         return var3 > 0 ? var3 : 1073741824;
      } else {
         return var3;
      }
   }

   static boolean needsResizing(int var0, int var1, double var2) {
      return var0 > var2 * var1 && var1 < 1073741824;
   }
}
