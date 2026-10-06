package com.moonsworth.lunar.genesis;
import com.google.common.hash.HashCode;
import com.google.common.base.Preconditions;

final class MixinHelper5242 extends MixinHelper524 {
   static final MixinHelper5_8 field1 = new MixinHelper5242();
   private static final long field2 = -4348849565147123417L;
   private static final long field3 = -5435081209227447693L;
   private static final long field4 = -7286425919675154353L;

   @Override
   public HashCode method6(byte[] var1, int var2, int var3) {
      Preconditions.checkPositionIndexes(var2, var2 + var3, var1.length);
      return HashCode.method3(fingerprint(var1, var2, var3));
   }

   @Override
   public int bits() {
      return 64;
   }

   @Override
   public String toString() {
      return "Hashing.farmHashFingerprint64()";
   }

   @Annotation4
   static long fingerprint(byte[] var0, int var1, int var2) {
      if (var2 <= 32) {
         return var2 <= 16 ? hashLength0to16(var0, var1, var2) : hashLength17to32(var0, var1, var2);
      } else {
         return var2 <= 64 ? hashLength33To64(var0, var1, var2) : hashLength65Plus(var0, var1, var2);
      }
   }

   private static long shiftMix(long var0) {
      return var0 ^ var0 >>> 47;
   }

   private static long hashLength16(long var0, long var2, long var4) {
      long var6 = (var0 ^ var2) * var4;
      var6 ^= var6 >>> 47;
      long var8 = (var2 ^ var6) * var4;
      var8 ^= var8 >>> 47;
      return var8 * var4;
   }

   private static void weakHashLength32WithSeeds(byte[] var0, int var1, long var2, long var4, long[] var6) {
      long var7 = MixinHelper2_13.load64(var0, var1);
      long var9 = MixinHelper2_13.load64(var0, var1 + 8);
      long var11 = MixinHelper2_13.load64(var0, var1 + 16);
      long var13 = MixinHelper2_13.load64(var0, var1 + 24);
      var2 += var7;
      var4 = Long.rotateRight(var4 + var2 + var13, 21);
      long var15 = var2;
      var2 += var9;
      var2 += var11;
      var4 += Long.rotateRight(var2, 44);
      var6[0] = var2 + var13;
      var6[1] = var4 + var15;
   }

   private static long hashLength0to16(byte[] var0, int var1, int var2) {
      if (var2 >= 8) {
         long var14 = -7286425919675154353L + var2 * 2;
         long var16 = MixinHelper2_13.load64(var0, var1) + -7286425919675154353L;
         long var17 = MixinHelper2_13.load64(var0, var1 + var2 - 8);
         long var9 = Long.rotateRight(var17, 37) * var14 + var16;
         long var11 = (Long.rotateRight(var16, 25) + var17) * var14;
         return hashLength16(var9, var11, var14);
      } else if (var2 >= 4) {
         long var13 = -7286425919675154353L + var2 * 2;
         long var15 = MixinHelper2_13.load32(var0, var1) & 4294967295L;
         return hashLength16(var2 + (var15 << 3), MixinHelper2_13.load32(var0, var1 + var2 - 4) & 4294967295L, var13);
      } else if (var2 > 0) {
         byte var3 = var0[var1];
         byte var4 = var0[var1 + (var2 >> 1)];
         byte var5 = var0[var1 + (var2 - 1)];
         int var6 = (var3 & 255) + ((var4 & 255) << 8);
         int var7 = var2 + ((var5 & 255) << 2);
         return shiftMix(var6 * -7286425919675154353L ^ var7 * -4348849565147123417L) * -7286425919675154353L;
      } else {
         return -7286425919675154353L;
      }
   }

   private static long hashLength17to32(byte[] var0, int var1, int var2) {
      long var3 = -7286425919675154353L + var2 * 2;
      long var5 = MixinHelper2_13.load64(var0, var1) * -5435081209227447693L;
      long var7 = MixinHelper2_13.load64(var0, var1 + 8);
      long var9 = MixinHelper2_13.load64(var0, var1 + var2 - 8) * var3;
      long var11 = MixinHelper2_13.load64(var0, var1 + var2 - 16) * -7286425919675154353L;
      return hashLength16(
         Long.rotateRight(var5 + var7, 43) + Long.rotateRight(var9, 30) + var11, var5 + Long.rotateRight(var7 + -7286425919675154353L, 18) + var9, var3
      );
   }

   private static long hashLength33To64(byte[] var0, int var1, int var2) {
      long var3 = -7286425919675154353L + var2 * 2;
      long var5 = MixinHelper2_13.load64(var0, var1) * -7286425919675154353L;
      long var7 = MixinHelper2_13.load64(var0, var1 + 8);
      long var9 = MixinHelper2_13.load64(var0, var1 + var2 - 8) * var3;
      long var11 = MixinHelper2_13.load64(var0, var1 + var2 - 16) * -7286425919675154353L;
      long var13 = Long.rotateRight(var5 + var7, 43) + Long.rotateRight(var9, 30) + var11;
      long var15 = hashLength16(var13, var5 + Long.rotateRight(var7 + -7286425919675154353L, 18) + var9, var3);
      long var17 = MixinHelper2_13.load64(var0, var1 + 16) * var3;
      long var19 = MixinHelper2_13.load64(var0, var1 + 24);
      long var21 = (var13 + MixinHelper2_13.load64(var0, var1 + var2 - 32)) * var3;
      long var23 = (var15 + MixinHelper2_13.load64(var0, var1 + var2 - 24)) * var3;
      return hashLength16(Long.rotateRight(var17 + var19, 43) + Long.rotateRight(var21, 30) + var23, var17 + Long.rotateRight(var19 + var5, 18) + var21, var3);
   }

   private static long hashLength65Plus(byte[] var0, int var1, int var2) {
      byte var3 = 81;
      long var4 = 81L;
      long var6 = 2480279821605975764L;
      long var8 = shiftMix(var6 * -7286425919675154353L + 113L) * -7286425919675154353L;
      long[] var10 = new long[2];
      long[] var11 = new long[2];
      var4 = var4 * -7286425919675154353L + MixinHelper2_13.load64(var0, var1);
      int var12 = var1 + (var2 - 1) / 64 * 64;
      int var13 = var12 + (var2 - 1 & 63) - 63;

      do {
         var4 = Long.rotateRight(var4 + var6 + var10[0] + MixinHelper2_13.load64(var0, var1 + 8), 37) * -5435081209227447693L;
         long var22 = Long.rotateRight(var6 + var10[1] + MixinHelper2_13.load64(var0, var1 + 48), 42) * -5435081209227447693L;
         var4 ^= var11[1];
         var6 = var22 + var10[0] + MixinHelper2_13.load64(var0, var1 + 40);
         var8 = Long.rotateRight(var8 + var11[0], 33) * -5435081209227447693L;
         weakHashLength32WithSeeds(var0, var1, var10[1] * -5435081209227447693L, var4 + var11[0], var10);
         weakHashLength32WithSeeds(var0, var1 + 32, var8 + var11[1], var6 + MixinHelper2_13.load64(var0, var1 + 16), var11);
         long var14 = var4;
         var4 = var8;
         var8 = var14;
         var1 += 64;
      } while (var1 != var12);

      long var27 = -5435081209227447693L + ((var8 & 255L) << 1);
      var1 = var13;
      var11[0] += var2 - 1 & 63;
      var10[0] += var11[0];
      var11[0] += var10[0];
      var4 = Long.rotateRight(var4 + var6 + var10[0] + MixinHelper2_13.load64(var0, var1 + 8), 37) * var27;
      var6 = Long.rotateRight(var6 + var10[1] + MixinHelper2_13.load64(var0, var1 + 48), 42) * var27;
      var4 ^= var11[1] * 9L;
      var6 += var10[0] * 9L + MixinHelper2_13.load64(var0, var1 + 40);
      var8 = Long.rotateRight(var8 + var11[0], 33) * var27;
      weakHashLength32WithSeeds(var0, var1, var10[1] * var27, var4 + var11[0], var10);
      weakHashLength32WithSeeds(var0, var1 + 32, var8 + var11[1], var6 + MixinHelper2_13.load64(var0, var1 + 16), var11);
      return hashLength16(
         hashLength16(var10[0], var11[0], var27) + shiftMix(var6) * -4348849565147123417L + var4, hashLength16(var10[1], var11[1], var27) + var8, var27
      );
   }
}
