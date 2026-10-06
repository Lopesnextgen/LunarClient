package com.moonsworth.lunar.genesis;
import com.google.common.primitives.UnsignedLongs;

enum LongMath$MillerRabinTester$2 {
   ;
   LongMath$MillerRabinTester$2() {
   }

   private long plusMod(long number1, long number3, long number5) {
      return number1 >= number5 - number3 ? number1 + number3 - number5 : number1 + number3;
   }

   private long times2ToThe32Mod(long number1, long number3) {
      int number5 = 32;

      do {
         int number6 = Math.min(number5, Long.numberOfLeadingZeros(number1));
         number1 = UnsignedLongs.remainder(number1 << number6, number3);
         number5 -= number6;
      } while (number5 > 0);

      return number1;
   }

   long mulMod(long number1, long number3, long number5) {
      long number7 = number1 >>> 32;
      long number9 = number3 >>> 32;
      long number11 = number1 & 4294967295L;
      long number13 = number3 & 4294967295L;
      long number15 = this.times2ToThe32Mod(number7 * number9, number5);
      number15 += number7 * number13;
      if (number15 < 0L) {
         number15 = UnsignedLongs.remainder(number15, number5);
      }

      number15 += number11 * number9;
      number15 = this.times2ToThe32Mod(number15, number5);
      return this.plusMod(number15, UnsignedLongs.remainder(number11 * number13, number5), number5);
   }

   long squareMod(long number1, long number3) {
      long number5 = number1 >>> 32;
      long number7 = number1 & 4294967295L;
      long number9 = this.times2ToThe32Mod(number5 * number5, number3);
      long number11 = number5 * number7 * 2L;
      if (number11 < 0L) {
         number11 = UnsignedLongs.remainder(number11, number3);
      }

      number9 += number11;
      number9 = this.times2ToThe32Mod(number9, number3);
      return this.plusMod(number9, UnsignedLongs.remainder(number7 * number7, number3), number3);
   }
}
