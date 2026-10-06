package com.moonsworth.lunar.genesis;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.eventbus.Subscribe;
import com.google.common.base.Preconditions;

final class FarmHashFingerprint64 extends MixinHelper524 {
   static final HashFunction field1 = new FarmHashFingerprint64();
   private static final long field2 = -4348849565147123417L;
   private static final long field3 = -5435081209227447693L;
   private static final long field4 = -7286425919675154353L;

   FarmHashFingerprint64() {
   }

   public HashCode method6(byte[] items1, int number2, int number3) {
      Preconditions.checkPositionIndexes(number2, number2 + number3, items1.length);
      return HashCode.method3(fingerprint(items1, number2, number3));
   }

   public int bits() {
      return 64;
   }

   public String toString() {
      return "Hashing.farmHashFingerprint64()";
   }

   @Subscribe
   static long fingerprint(byte[] items0, int number1, int number2) {
      if (number2 <= 32) {
         return number2 <= 16 ? hashLength0to16(items0, number1, number2) : hashLength17to32(items0, number1, number2);
      } else {
         return number2 <= 64 ? hashLength33To64(items0, number1, number2) : hashLength65Plus(items0, number1, number2);
      }
   }

   private static long shiftMix(long number0) {
      return number0 ^ number0 >>> 47;
   }

   private static long hashLength16(long number0, long number2, long number4) {
      long number6 = (number0 ^ number2) * number4;
      number6 ^= number6 >>> 47;
      long number8 = (number2 ^ number6) * number4;
      number8 ^= number8 >>> 47;
      return number8 * number4;
   }

   private static void weakHashLength32WithSeeds(byte[] items0, int number1, long number2, long number4, long[] items6) {
      long number7 = MixinHelper2_13.load64(items0, number1);
      long number9 = MixinHelper2_13.load64(items0, number1 + 8);
      long number11 = MixinHelper2_13.load64(items0, number1 + 16);
      long number13 = MixinHelper2_13.load64(items0, number1 + 24);
      number2 += number7;
      number4 = Long.rotateRight(number4 + number2 + number13, 21);
      long number15 = number2;
      number2 += number9;
      number2 += number11;
      number4 += Long.rotateRight(number2, 44);
      items6[0] = number2 + number13;
      items6[1] = number4 + number15;
   }

   private static long hashLength0to16(byte[] items0, int index1, int number2) {
      if (number2 >= 8) {
         long number14 = -7286425919675154353L + number2 * 2;
         long number16 = MixinHelper2_13.load64(items0, index1) + -7286425919675154353L;
         long number17 = MixinHelper2_13.load64(items0, index1 + number2 - 8);
         long number9 = Long.rotateRight(number17, 37) * number14 + number16;
         long number11 = (Long.rotateRight(number16, 25) + number17) * number14;
         return hashLength16(number9, number11, number14);
      } else if (number2 >= 4) {
         long number13 = -7286425919675154353L + number2 * 2;
         long number15 = MixinHelper2_13.load32(items0, index1) & 4294967295L;
         return hashLength16(number2 + (number15 << 3), MixinHelper2_13.load32(items0, index1 + number2 - 4) & 4294967295L, number13);
      } else if (number2 > 0) {
         byte number3 = items0[index1];
         byte number4 = items0[index1 + (number2 >> 1)];
         byte number5 = items0[index1 + (number2 - 1)];
         int number6 = (number3 & 255) + ((number4 & 255) << 8);
         int number7 = number2 + ((number5 & 255) << 2);
         return shiftMix(number6 * -7286425919675154353L ^ number7 * -4348849565147123417L) * -7286425919675154353L;
      } else {
         return -7286425919675154353L;
      }
   }

   private static long hashLength17to32(byte[] items0, int number1, int number2) {
      long number3 = -7286425919675154353L + number2 * 2;
      long number5 = MixinHelper2_13.load64(items0, number1) * -5435081209227447693L;
      long number7 = MixinHelper2_13.load64(items0, number1 + 8);
      long number9 = MixinHelper2_13.load64(items0, number1 + number2 - 8) * number3;
      long number11 = MixinHelper2_13.load64(items0, number1 + number2 - 16) * -7286425919675154353L;
      return hashLength16(
         Long.rotateRight(number5 + number7, 43) + Long.rotateRight(number9, 30) + number11, number5 + Long.rotateRight(number7 + -7286425919675154353L, 18) + number9, number3
      );
   }

   private static long hashLength33To64(byte[] items0, int number1, int number2) {
      long number3 = -7286425919675154353L + number2 * 2;
      long number5 = MixinHelper2_13.load64(items0, number1) * -7286425919675154353L;
      long number7 = MixinHelper2_13.load64(items0, number1 + 8);
      long number9 = MixinHelper2_13.load64(items0, number1 + number2 - 8) * number3;
      long number11 = MixinHelper2_13.load64(items0, number1 + number2 - 16) * -7286425919675154353L;
      long number13 = Long.rotateRight(number5 + number7, 43) + Long.rotateRight(number9, 30) + number11;
      long number15 = hashLength16(number13, number5 + Long.rotateRight(number7 + -7286425919675154353L, 18) + number9, number3);
      long number17 = MixinHelper2_13.load64(items0, number1 + 16) * number3;
      long number19 = MixinHelper2_13.load64(items0, number1 + 24);
      long number21 = (number13 + MixinHelper2_13.load64(items0, number1 + number2 - 32)) * number3;
      long number23 = (number15 + MixinHelper2_13.load64(items0, number1 + number2 - 24)) * number3;
      return hashLength16(Long.rotateRight(number17 + number19, 43) + Long.rotateRight(number21, 30) + number23, number17 + Long.rotateRight(number19 + number5, 18) + number21, number3);
   }

   private static long hashLength65Plus(byte[] items0, int number1, int number2) {
      byte number3 = 81;
      long number4 = 81L;
      long number6 = 2480279821605975764L;
      long number8 = shiftMix(number6 * -7286425919675154353L + 113L) * -7286425919675154353L;
      long[] items10 = new long[2];
      long[] items11 = new long[2];
      number4 = number4 * -7286425919675154353L + MixinHelper2_13.load64(items0, number1);
      int number12 = number1 + (number2 - 1) / 64 * 64;
      int number13 = number12 + (number2 - 1 & 63) - 63;

      do {
         number4 = Long.rotateRight(number4 + number6 + items10[0] + MixinHelper2_13.load64(items0, number1 + 8), 37) * -5435081209227447693L;
         long number22 = Long.rotateRight(number6 + items10[1] + MixinHelper2_13.load64(items0, number1 + 48), 42) * -5435081209227447693L;
         number4 ^= items11[1];
         number6 = number22 + items10[0] + MixinHelper2_13.load64(items0, number1 + 40);
         number8 = Long.rotateRight(number8 + items11[0], 33) * -5435081209227447693L;
         weakHashLength32WithSeeds(items0, number1, items10[1] * -5435081209227447693L, number4 + items11[0], items10);
         weakHashLength32WithSeeds(items0, number1 + 32, number8 + items11[1], number6 + MixinHelper2_13.load64(items0, number1 + 16), items11);
         long number14 = number4;
         number4 = number8;
         number8 = number14;
         number1 += 64;
      } while (number1 != number12);

      long number27 = -5435081209227447693L + ((number8 & 255L) << 1);
      number1 = number13;
      items11[0] += number2 - 1 & 63;
      items10[0] += items11[0];
      items11[0] += items10[0];
      number4 = Long.rotateRight(number4 + number6 + items10[0] + MixinHelper2_13.load64(items0, number1 + 8), 37) * number27;
      number6 = Long.rotateRight(number6 + items10[1] + MixinHelper2_13.load64(items0, number1 + 48), 42) * number27;
      number4 ^= items11[1] * 9L;
      number6 += items10[0] * 9L + MixinHelper2_13.load64(items0, number1 + 40);
      number8 = Long.rotateRight(number8 + items11[0], 33) * number27;
      weakHashLength32WithSeeds(items0, number1, items10[1] * number27, number4 + items11[0], items10);
      weakHashLength32WithSeeds(items0, number1 + 32, number8 + items11[1], number6 + MixinHelper2_13.load64(items0, number1 + 16), items11);
      return hashLength16(
         hashLength16(items10[0], items11[0], number27) + shiftMix(number6) * -4348849565147123417L + number4, hashLength16(items10[1], items11[1], number27) + number8, number27
      );
   }
}
