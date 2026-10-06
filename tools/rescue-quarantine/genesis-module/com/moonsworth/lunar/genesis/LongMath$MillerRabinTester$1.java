package com.moonsworth.lunar.genesis;

enum LongMath$MillerRabinTester$1 {
   ;
   LongMath$MillerRabinTester$1() {
   }

   long mulMod(long number1, long number3, long number5) {
      return number1 * number3 % number5;
   }

   long squareMod(long number1, long number3) {
      return number1 * number1 % number3;
   }
}
