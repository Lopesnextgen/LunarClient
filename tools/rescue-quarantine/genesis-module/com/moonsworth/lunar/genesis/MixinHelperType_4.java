package com.moonsworth.lunar.genesis;
import com.google.common.hash.Funnel;
import com.google.common.primitives.Longs;
import com.google.common.hash.Hashing;

enum MixinHelperType_4 implements NumberBase2$Extension {
   MURMUR128_MITZ_32 {
      @Override
      public <T> boolean put(T var1, Funnel<? super T> var2, int var3, MixinHelperType$Data2 var4) {
         long var5 = var4.bitSize();
         long var7 = Hashing.method5().method10(var1, var2).asLong();
         int var9 = (int)var7;
         int var10 = (int)(var7 >>> 32);
         boolean var11 = false;

         for (int var12 = 1; var12 <= var3; var12++) {
            int var13 = var9 + var12 * var10;
            if (var13 < 0) {
               var13 = ~var13;
            }

            var11 |= var4.set(var13 % var5);
         }

         return var11;
      }

      @Override
      public <T> boolean mightContain(T var1, Funnel<? super T> var2, int var3, MixinHelperType$Data2 var4) {
         long var5 = var4.bitSize();
         long var7 = Hashing.method5().method10(var1, var2).asLong();
         int var9 = (int)var7;
         int var10 = (int)(var7 >>> 32);

         for (int var11 = 1; var11 <= var3; var11++) {
            int var12 = var9 + var11 * var10;
            if (var12 < 0) {
               var12 = ~var12;
            }

            if (!var4.get(var12 % var5)) {
               return false;
            }
         }

         return true;
      }
   },
   MURMUR128_MITZ_64 {
      @Override
      public <T> boolean put(T var1, Funnel<? super T> var2, int var3, MixinHelperType$Data2 var4) {
         long var5 = var4.bitSize();
         byte[] var7 = Hashing.method5().method10(var1, var2).getBytesInternal();
         long var8 = this.lowerEight(var7);
         long var10 = this.upperEight(var7);
         boolean var12 = false;
         long var13 = var8;

         for (int var15 = 0; var15 < var3; var15++) {
            var12 |= var4.set((var13 & Long.MAX_VALUE) % var5);
            var13 += var10;
         }

         return var12;
      }

      @Override
      public <T> boolean mightContain(T var1, Funnel<? super T> var2, int var3, MixinHelperType$Data2 var4) {
         long var5 = var4.bitSize();
         byte[] var7 = Hashing.method5().method10(var1, var2).getBytesInternal();
         long var8 = this.lowerEight(var7);
         long var10 = this.upperEight(var7);
         long var12 = var8;

         for (int var14 = 0; var14 < var3; var14++) {
            if (!var4.get((var12 & Long.MAX_VALUE) % var5)) {
               return false;
            }

            var12 += var10;
         }

         return true;
      }

      private long lowerEight(byte[] var1) {
         return Longs.fromBytes(var1[7], var1[6], var1[5], var1[4], var1[3], var1[2], var1[1], var1[0]);
      }

      private long upperEight(byte[] var1) {
         return Longs.fromBytes(var1[15], var1[14], var1[13], var1[12], var1[11], var1[10], var1[9], var1[8]);
      }
   };

   MixinHelperType_4() {
   }
}
