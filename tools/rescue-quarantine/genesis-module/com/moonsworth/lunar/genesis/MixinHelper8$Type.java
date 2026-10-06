package com.moonsworth.lunar.genesis;

enum MixinHelper8$Type {
   SMALL,
   LARGE;

   MixinHelper8$Type() {
   }

   static boolean test(long number0, long number2) {
      return (number2 <= 3037000499L ? SMALL : LARGE).testWitness(number0, number2);
   }

   abstract long mulMod(long number1, long number3, long number5);

   abstract long squareMod(long number1, long number3);

   private long powMod(long number1, long number3, long number5) {
      long number7 = 1L;

      while (number3 != 0L) {
         if ((number3 & 1L) != 0L) {
            number7 = this.mulMod(number7, number1, number5);
         }

         number1 = this.squareMod(number1, number5);
         number3 >>= 1;
      }

      return number7;
   }

   private boolean testWitness(long number1, long number3) {
      int number5 = Long.numberOfTrailingZeros(number3 - 1L);
      long number6 = number3 - 1L >> number5;
      number1 %= number3;
      if (number1 == 0L) {
         return true;
      }

      long number8 = this.powMod(number1, number6, number3);
      if (number8 == 1L) {
         return true;
      }

      int number10 = 0;

      while (number8 != number3 - 1L) {
         if (++number10 == number5) {
            return false;
         }

         number8 = this.squareMod(number8, number3);
      }

      return true;
   }
}
