package com.moonsworth.lunar.genesis;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import com.google.common.primitives.UnsignedBytes;
import com.google.common.hash.HashCode;

final class Murmur3_128HashFunction$Murmur3_128Hasher extends AbstractStreamingHasher {
   private static final int field4 = 16;
   private static final long field5 = -8663945395140668459L;
   private static final long field6 = 5545529020109919103L;
   private long h1;
   private long h2;
   private int length;

   Murmur3_128HashFunction$Murmur3_128Hasher(int number1) {
      super(16);
      this.h1 = number1;
      this.h2 = number1;
      this.length = 0;
   }

   protected void process(ByteBuffer buffer1) {
      long number2 = buffer1.getLong();
      long number4 = buffer1.getLong();
      this.bmix64(number2, number4);
      this.length += 16;
   }

   private void bmix64(long number1, long number3) {
      this.h1 = this.h1 ^ mixK1(number1);
      this.h1 = Long.rotateLeft(this.h1, 27);
      this.h1 = this.h1 + this.h2;
      this.h1 = this.h1 * 5L + 1390208809L;
      this.h2 = this.h2 ^ mixK2(number3);
      this.h2 = Long.rotateLeft(this.h2, 31);
      this.h2 = this.h2 + this.h1;
      this.h2 = this.h2 * 5L + 944331445L;
   }

   protected void processRemaining(ByteBuffer buffer1) {
      long number2 = 0L;
      long number4 = 0L;
      this.length = this.length + buffer1.remaining();
      switch (buffer1.remaining()) {
         case 7:
            number2 ^= (long)UnsignedBytes.toInt(buffer1.get(6)) << 48;
         case 6:
            number2 ^= (long)UnsignedBytes.toInt(buffer1.get(5)) << 40;
         case 5:
            number2 ^= (long)UnsignedBytes.toInt(buffer1.get(4)) << 32;
         case 4:
            number2 ^= (long)UnsignedBytes.toInt(buffer1.get(3)) << 24;
         case 3:
            number2 ^= (long)UnsignedBytes.toInt(buffer1.get(2)) << 16;
         case 2:
            number2 ^= (long)UnsignedBytes.toInt(buffer1.get(1)) << 8;
         case 1:
            number2 ^= UnsignedBytes.toInt(buffer1.get(0));
            break;
         case 15:
            number4 ^= (long)UnsignedBytes.toInt(buffer1.get(14)) << 48;
         case 14:
            number4 ^= (long)UnsignedBytes.toInt(buffer1.get(13)) << 40;
         case 13:
            number4 ^= (long)UnsignedBytes.toInt(buffer1.get(12)) << 32;
         case 12:
            number4 ^= (long)UnsignedBytes.toInt(buffer1.get(11)) << 24;
         case 11:
            number4 ^= (long)UnsignedBytes.toInt(buffer1.get(10)) << 16;
         case 10:
            number4 ^= (long)UnsignedBytes.toInt(buffer1.get(9)) << 8;
         case 9:
            number4 ^= UnsignedBytes.toInt(buffer1.get(8));
         case 8:
            number2 ^= buffer1.getLong();
            break;
         default:
            throw new AssertionError("Should never get here.");
      }

      this.h1 = this.h1 ^ mixK1(number2);
      this.h2 = this.h2 ^ mixK2(number4);
   }

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

   private static long fmix64(long number0) {
      number0 ^= number0 >>> 33;
      number0 *= -49064778989728563L;
      number0 ^= number0 >>> 33;
      number0 *= -4265267296055464877L;
      return number0 ^ number0 >>> 33;
   }

   private static long mixK1(long number0) {
      number0 *= -8663945395140668459L;
      number0 = Long.rotateLeft(number0, 31);
      return number0 * 5545529020109919103L;
   }

   private static long mixK2(long number0) {
      number0 *= 5545529020109919103L;
      number0 = Long.rotateLeft(number0, 33);
      return number0 * -8663945395140668459L;
   }
}
