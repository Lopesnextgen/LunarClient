package com.moonsworth.lunar.genesis;

import java.util.Arrays;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hasher;
import com.google.common.hash.HashFunction;
import com.google.common.base.Preconditions;

final class Hashing$ConcatenatedHashFunction extends AbstractCompositeHashFunction {
   private Hashing$ConcatenatedHashFunction(HashFunction... items1) {
      super(items1);

      for (HashFunction mixinhelper5_85 : items1) {
         Preconditions.checkArgument(mixinhelper5_85.bits() % 8 == 0, "the number of bits (%s) in hashFunction (%s) must be divisible by 8", mixinhelper5_85.bits(), mixinhelper5_85);
      }
   }

   HashCode method1(Hasher[] items1) {
      byte[] items2 = new byte[this.bits() / 8];
      int number3 = 0;

      for (Hasher mixinhelper42_27 : items1) {
         HashCode mixinhelper8_98 = mixinhelper42_27.method15();
         number3 += mixinhelper8_98.writeBytesTo(items2, number3, mixinhelper8_98.bits() / 8);
      }

      return HashCode.method5(items2);
   }

   public int bits() {
      int number1 = 0;

      for (HashFunction mixinhelper5_85 : this.IRHHRIHIRCCCOROORRRCCIIOOCCCIH) {
         number1 += mixinhelper5_85.bits();
      }

      return number1;
   }

   public boolean equals(@Nullable Object obj1) {
      if (obj1 instanceof Hashing$ConcatenatedHashFunction) {
         Hashing$ConcatenatedHashFunction mixinhelper9$data212 = (Hashing$ConcatenatedHashFunction)obj1;
         return Arrays.equals(this.IRHHRIHIRCCCOROORRRCCIIOOCCCIH, mixinhelper9$data212.IRHHRIHIRCCCOROORRRCCIIOOCCCIH);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Arrays.hashCode(this.IRHHRIHIRCCCOROORRRCCIIOOCCCIH);
   }
}
