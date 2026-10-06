package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.base.Preconditions;
import com.google.common.hash.Hashing;
import com.google.common.primitives.UnsignedBytes;
import com.google.common.hash.HashCode;

@Immutable
final class MixinHelper5210 extends MixinHelper52 implements Serializable {
   static final MixinHelper5_8 field1 = new MixinHelper5210(0);
   static final MixinHelper5_8 field2 = new MixinHelper5210(Hashing.field1);
   private static final int field3 = 4;
   private static final int field4 = -862048943;
   private static final int field5 = 461845907;
   private final int field6;
   private static final long field7 = 0L;

   MixinHelper5210(int var1) {
      this.field6 = var1;
   }

   @Override
   public int bits() {
      return 32;
   }

   @Override
   public MixinHelper42_2 method1() {
      return new MixinHelper5210.Data(this.field6);
   }

   @Override
   public String toString() {
      return "Hashing.murmur3_32(" + this.field6 + ")";
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (var1 instanceof MixinHelper5210) {
         MixinHelper5210 var2 = (MixinHelper5210)var1;
         return this.field6 == var2.field6;
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return this.getClass().hashCode() ^ this.field6;
   }

   @Override
   public HashCode method3(int var1) {
      int var2 = mixK1(var1);
      int var3 = mixH1(this.field6, var2);
      return method7(var3, 4);
   }

   @Override
   public HashCode method4(long var1) {
      int var3 = (int)var1;
      int var4 = (int)(var1 >>> 32);
      int var5 = mixK1(var3);
      int var6 = mixH1(this.field6, var5);
      var5 = mixK1(var4);
      var6 = mixH1(var6, var5);
      return method7(var6, 8);
   }

   @Override
   public HashCode method8(CharSequence var1) {
      int var2 = this.field6;

      for (byte var3 = 1; var3 < var1.length(); var3 += 2) {
         int var4 = var1.charAt(var3 - 1) | var1.charAt(var3) << 16;
         var4 = mixK1(var4);
         var2 = mixH1(var2, var4);
      }

      if ((var1.length() & 1) == 1) {
         int var5 = var1.charAt(var1.length() - 1);
         var5 = mixK1(var5);
         var2 ^= var5;
      }

      return method7(var2, 2 * var1.length());
   }

   @Override
   public HashCode method9(CharSequence var1, Charset var2) {
      if (!MixinHelper18_2.field3.equals(var2)) {
         return this.method5(var1.toString().getBytes(var2));
      }

      int var3 = var1.length();
      int var4 = this.field6;
      int var5 = 0;

      int var6;
      for (var6 = 0; var5 + 4 <= var3; var6 += 4) {
         char var7 = var1.charAt(var5);
         char var8 = var1.charAt(var5 + 1);
         char var9 = var1.charAt(var5 + 2);
         char var10 = var1.charAt(var5 + 3);
         if (var7 >= 128 || var8 >= 128 || var9 >= 128 || var10 >= 128) {
            break;
         }

         int var11 = var7 | var8 << '\b' | var9 << 16 | var10 << 24;
         var11 = mixK1(var11);
         var4 = mixH1(var4, var11);
         var5 += 4;
      }

      long var13 = 0L;
      byte var14 = 0;

      while (var5 < var3) {
         char var15 = var1.charAt(var5);
         if (var15 < 128) {
            var13 |= (long)var15 << var14;
            var14 += 8;
            var6++;
         } else if (var15 < 2048) {
            var13 |= charToTwoUtf8Bytes(var15) << var14;
            var14 += 16;
            var6 += 2;
         } else if (var15 >= '\ud800' && var15 <= '\udfff') {
            int var18 = Character.codePointAt(var1, var5);
            if (var18 == var15) {
               return this.method5(var1.toString().getBytes(var2));
            }

            var5++;
            var13 |= codePointToFourUtf8Bytes(var18) << var14;
            var6 += 4;
         } else {
            var13 |= charToThreeUtf8Bytes(var15) << var14;
            var14 += 24;
            var6 += 3;
         }

         if (var14 >= 32) {
            int var19 = mixK1((int)var13);
            var4 = mixH1(var4, var19);
            var13 >>>= 32;
            var14 -= 32;
         }

         var5++;
      }

      int var16 = mixK1((int)var13);
      var4 ^= var16;
      return method7(var4, var6);
   }

   @Override
   public HashCode method6(byte[] var1, int var2, int var3) {
      Preconditions.checkPositionIndexes(var2, var2 + var3, var1.length);
      int var4 = this.field6;

      int var5;
      for (var5 = 0; var5 + 4 <= var3; var5 += 4) {
         int var6 = mixK1(getIntLittleEndian(var1, var2 + var5));
         var4 = mixH1(var4, var6);
      }

      int var9 = 0;

      for (byte var7 = 0; var5 < var3; var7 += 8) {
         var9 ^= UnsignedBytes.toInt(var1[var2 + var5]) << var7;
         var5++;
      }

      var4 ^= mixK1(var9);
      return method7(var4, var3);
   }

   private static int getIntLittleEndian(byte[] var0, int var1) {
      return MixinHelper122.fromBytes(var0[var1 + 3], var0[var1 + 2], var0[var1 + 1], var0[var1]);
   }

   private static int mixK1(int var0) {
      var0 *= -862048943;
      var0 = Integer.rotateLeft(var0, 15);
      return var0 * 461845907;
   }

   private static int mixH1(int var0, int var1) {
      var0 ^= var1;
      var0 = Integer.rotateLeft(var0, 13);
      return var0 * 5 + -430675100;
   }

   private static HashCode method7(int var0, int var1) {
      var0 ^= var1;
      var0 ^= var0 >>> 16;
      var0 *= -2048144789;
      var0 ^= var0 >>> 13;
      var0 *= -1028477387;
      var0 ^= var0 >>> 16;
      return HashCode.method2(var0);
   }

   private static long codePointToFourUtf8Bytes(int var0) {
      return (240L | var0 >>> 18) & 255L | (128L | 63 & var0 >>> 12) << 8 | (128L | 63 & var0 >>> 6) << 16 | (128L | 63 & var0) << 24;
   }

   private static long charToThreeUtf8Bytes(char var0) {
      return (480 | var0 >>> '\f') & 0xFF | (128 | 63 & var0 >>> 6) << 8 | (128 | '?' & var0) << 16;
   }

   private static long charToTwoUtf8Bytes(char var0) {
      return (960 | var0 >>> 6) & 0xFF | (128 | '?' & var0) << 8;
   }

   @CanIgnoreReturnValue
   private static final class Data extends MixinHelper422_2 {
      private int h1;
      private long buffer;
      private int shift;
      private int length;
      private boolean isDone;

      Data(int var1) {
         this.h1 = var1;
         this.length = 0;
         this.isDone = false;
      }

      private void update(int var1, long var2) {
         this.buffer = this.buffer | (var2 & 4294967295L) << this.shift;
         this.shift += var1 * 8;
         this.length += var1;
         if (this.shift >= 32) {
            this.h1 = MixinHelper5210.mixH1(this.h1, MixinHelper5210.mixK1((int)this.buffer));
            this.buffer >>>= 32;
            this.shift -= 32;
         }
      }

      @Override
      public MixinHelper42_2 method2(byte var1) {
         this.update(1, var1 & 0xFF);
         return this;
      }

      @Override
      public MixinHelper42_2 method4(byte[] var1, int var2, int var3) {
         Preconditions.checkPositionIndexes(var2, var2 + var3, var1.length);

         int var4;
         for (var4 = 0; var4 + 4 <= var3; var4 += 4) {
            this.update(4, MixinHelper5210.getIntLittleEndian(var1, var2 + var4));
         }

         while (var4 < var3) {
            this.method2(var1[var2 + var4]);
            var4++;
         }

         return this;
      }

      @Override
      public MixinHelper42_2 method5(ByteBuffer var1) {
         ByteOrder var2 = var1.order();
         var1.order(ByteOrder.LITTLE_ENDIAN);

         while (var1.remaining() >= 4) {
            this.method7(var1.getInt());
         }

         while (var1.hasRemaining()) {
            this.method2(var1.get());
         }

         var1.order(var2);
         return this;
      }

      @Override
      public MixinHelper42_2 method7(int var1) {
         this.update(4, var1);
         return this;
      }

      @Override
      public MixinHelper42_2 method8(long var1) {
         this.update(4, (int)var1);
         this.update(4, var1 >>> 32);
         return this;
      }

      @Override
      public MixinHelper42_2 method12(char var1) {
         this.update(2, var1);
         return this;
      }

      @Override
      public MixinHelper42_2 method14(CharSequence var1, Charset var2) {
         if (!MixinHelper18_2.field3.equals(var2)) {
            return super.method14(var1, var2);
         }

         int var3 = var1.length();

         int var4;
         for (var4 = 0; var4 + 4 <= var3; var4 += 4) {
            char var5 = var1.charAt(var4);
            char var6 = var1.charAt(var4 + 1);
            char var7 = var1.charAt(var4 + 2);
            char var8 = var1.charAt(var4 + 3);
            if (var5 >= 128 || var6 >= 128 || var7 >= 128 || var8 >= 128) {
               break;
            }

            this.update(4, var5 | var6 << '\b' | var7 << 16 | var8 << 24);
         }

         for (; var4 < var3; var4++) {
            char var9 = var1.charAt(var4);
            if (var9 < 128) {
               this.update(1, var9);
            } else if (var9 < 2048) {
               this.update(2, MixinHelper5210.charToTwoUtf8Bytes(var9));
            } else if (var9 >= '\ud800' && var9 <= '\udfff') {
               int var10 = Character.codePointAt(var1, var4);
               if (var10 == var9) {
                  this.RIOOHRCHIRCIICRIRCOCHIRHHCOIRO(var1.subSequence(var4, var3).toString().getBytes(var2));
                  return this;
               }

               var4++;
               this.update(4, MixinHelper5210.codePointToFourUtf8Bytes(var10));
            } else {
               this.update(3, MixinHelper5210.charToThreeUtf8Bytes(var9));
            }
         }

         return this;
      }

      @Override
      public HashCode method15() {
         Preconditions.checkState(!this.isDone);
         this.isDone = true;
         this.h1 = this.h1 ^ MixinHelper5210.mixK1((int)this.buffer);
         return MixinHelper5210.method7(this.h1, this.length);
      }
   }
}
