package com.moonsworth.lunar.genesis;

import java.util.Arrays;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.hash.HashCode;
import com.google.common.base.Preconditions;

final class MixinHelper9$Data21 extends MixinHelper529 {
   private MixinHelper9$Data21(MixinHelper5_8... var1) {
      super(var1);

      for (MixinHelper5_8 var5 : var1) {
         Preconditions.checkArgument(var5.bits() % 8 == 0, "the number of bits (%s) in hashFunction (%s) must be divisible by 8", var5.bits(), var5);
      }
   }

   @Override
   HashCode method1(MixinHelper42_2[] var1) {
      byte[] var2 = new byte[this.bits() / 8];
      int var3 = 0;

      for (MixinHelper42_2 var7 : var1) {
         HashCode var8 = var7.method15();
         var3 += var8.writeBytesTo(var2, var3, var8.bits() / 8);
      }

      return HashCode.method5(var2);
   }

   @Override
   public int bits() {
      int var1 = 0;

      for (MixinHelper5_8 var5 : this.field1) {
         var1 += var5.bits();
      }

      return var1;
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (var1 instanceof MixinHelper9$Data21) {
         MixinHelper9$Data21 var2 = (MixinHelper9$Data21)var1;
         return Arrays.equals(this.field1, var2.field1);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Arrays.hashCode(this.field1);
   }
}
