package com.moonsworth.lunar.genesis;

import java.io.Serializable;

final class HashCode$LongHashCode extends MixinHelper8_9 implements Serializable {
   final long field2;
   private static final long field3 = 0L;

   HashCode$LongHashCode(long number1) {
      this.field2 = number1;
   }

   public int bits() {
      return 64;
   }

   public byte[] asBytes() {
      return new byte[]{
         (byte)this.field2,
         (byte)(this.field2 >> 8),
         (byte)(this.field2 >> 16),
         (byte)(this.field2 >> 24),
         (byte)(this.field2 >> 32),
         (byte)(this.field2 >> 40),
         (byte)(this.field2 >> 48),
         (byte)(this.field2 >> 56)
      };
   }

   public int asInt() {
      return (int)this.field2;
   }

   public long asLong() {
      return this.field2;
   }

   public long padToLong() {
      return this.field2;
   }

   void writeBytesToImpl(byte[] items1, int index2, int number3) {
      for (int index4 = 0; index4 < number3; index4++) {
         items1[index2 + index4] = (byte)(this.field2 >> index4 * 8);
      }
   }

   boolean method1(MixinHelper8_9 mixinhelper8_91) {
      return this.field2 == mixinhelper8_91.asLong();
   }
}
