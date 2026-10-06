package com.moonsworth.lunar.genesis;

import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.hash.Hashing;
import com.google.common.primitives.UnsignedBytes;
import com.google.common.hash.HashCode;

@Immutable
final class MixinHelper525 extends MixinHelper52 implements Serializable {
   static final MixinHelper5_8 field1 = new MixinHelper525(0);
   static final MixinHelper5_8 field2 = new MixinHelper525(Hashing.field1);
   private final int field3;
   private static final long field4 = 0L;

   MixinHelper525(int var1) {
      this.field3 = var1;
   }

   @Override
   public int bits() {
      return 128;
   }

   @Override
   public MixinHelper42_2 method1() {
      return new MixinHelper525.Data(this.field3);
   }

   @Override
   public String toString() {
      return "Hashing.murmur3_128(" + this.field3 + ")";
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (var1 instanceof MixinHelper525) {
         MixinHelper525 var2 = (MixinHelper525)var1;
         return this.field3 == var2.field3;
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return this.getClass().hashCode() ^ this.field3;
   }

   private static final class Data extends MixinHelper4222 {
      private static final int field4 = 16;
      private static final long field5 = -8663945395140668459L;
      private static final long field6 = 5545529020109919103L;
      private long h1;
      private long h2;
      private int length;

      Data(int var1) {
         super(16);
         this.h1 = var1;
         this.h2 = var1;
         this.length = 0;
      }

      @Override
      protected void process(ByteBuffer var1) {
         long var2 = var1.getLong();
         long var4 = var1.getLong();
         this.bmix64(var2, var4);
         this.length += 16;
      }

      private void bmix64(long var1, long var3) {
         this.h1 = this.h1 ^ mixK1(var1);
         this.h1 = Long.rotateLeft(this.h1, 27);
         this.h1 = this.h1 + this.h2;
         this.h1 = this.h1 * 5L + 1390208809L;
         this.h2 = this.h2 ^ mixK2(var3);
         this.h2 = Long.rotateLeft(this.h2, 31);
         this.h2 = this.h2 + this.h1;
         this.h2 = this.h2 * 5L + 944331445L;
      }

      @Override
      protected void processRemaining(ByteBuffer var1) {
         long var2 = 0L;
         long var4 = 0L;
         this.length = this.length + var1.remaining();
         switch (var1.remaining()) {
            case 7:
               var2 ^= (long)UnsignedBytes.toInt(var1.get(6)) << 48;
            case 6:
               var2 ^= (long)UnsignedBytes.toInt(var1.get(5)) << 40;
            case 5:
               var2 ^= (long)UnsignedBytes.toInt(var1.get(4)) << 32;
            case 4:
               var2 ^= (long)UnsignedBytes.toInt(var1.get(3)) << 24;
            case 3:
               var2 ^= (long)UnsignedBytes.toInt(var1.get(2)) << 16;
            case 2:
               var2 ^= (long)UnsignedBytes.toInt(var1.get(1)) << 8;
            case 1:
               var2 ^= UnsignedBytes.toInt(var1.get(0));
               break;
            case 15:
               var4 ^= (long)UnsignedBytes.toInt(var1.get(14)) << 48;
            case 14:
               var4 ^= (long)UnsignedBytes.toInt(var1.get(13)) << 40;
            case 13:
               var4 ^= (long)UnsignedBytes.toInt(var1.get(12)) << 32;
            case 12:
               var4 ^= (long)UnsignedBytes.toInt(var1.get(11)) << 24;
            case 11:
               var4 ^= (long)UnsignedBytes.toInt(var1.get(10)) << 16;
            case 10:
               var4 ^= (long)UnsignedBytes.toInt(var1.get(9)) << 8;
            case 9:
               var4 ^= UnsignedBytes.toInt(var1.get(8));
            case 8:
               var2 ^= var1.getLong();
               break;
            default:
               throw new AssertionError("Should never get here.");
         }

         this.h1 = this.h1 ^ mixK1(var2);
         this.h2 = this.h2 ^ mixK2(var4);
      }

      @Override
      protected HashCode method10() {
         this.h1 = this.h1 ^ this.length;
         this.h2 = this.h2 ^ this.length;
         this.h1 = this.h1 + this.h2;
         this.h2 = this.h2 + this.h1;
         this.h1 = fmix64(this.h1);
         this.h2 = fmix64(this.h2);
         this.h1 = this.h1 + this.h2;
         this.h2 = this.h2 + this.h1;
         return HashCode.method5(ByteBuffer.wrap(new byte[16]).order(ByteOrder.LITTLE_ENDIAN).putLong(this.h1).putLong(this.h2).array());
      }

      private static long fmix64(long var0) {
         var0 ^= var0 >>> 33;
         var0 *= -49064778989728563L;
         var0 ^= var0 >>> 33;
         var0 *= -4265267296055464877L;
         return var0 ^ var0 >>> 33;
      }

      private static long mixK1(long var0) {
         var0 *= -8663945395140668459L;
         var0 = Long.rotateLeft(var0, 31);
         return var0 * 5545529020109919103L;
      }

      private static long mixK2(long var0) {
         var0 *= 5545529020109919103L;
         var0 = Long.rotateLeft(var0, 33);
         return var0 * -8663945395140668459L;
      }
   }
}
