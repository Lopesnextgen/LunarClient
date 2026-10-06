package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import com.google.common.primitives.UnsignedInts;

final class HashCode$IntHashCode extends MixinHelper8_9 implements Serializable {
   final int field2;
   private static final long field3 = 0L;

   HashCode$IntHashCode(int number1) {
      this.field2 = number1;
   }

   public int bits() {
      return 32;
   }

   public byte[] asBytes() {
      return new byte[]{(byte)this.field2, (byte)(this.field2 >> 8), (byte)(this.field2 >> 16), (byte)(this.field2 >> 24)};
   }

   public int asInt() {
      return this.field2;
   }

   public long asLong() {
      throw new IllegalStateException("this HashCode only has 32 bits; cannot create a long");
   }

   public long padToLong() {
      return UnsignedInts.toLong(this.field2);
   }

   void writeBytesToImpl(byte[] items1, int index2, int number3) {
      for (int index4 = 0; index4 < number3; index4++) {
         items1[index2 + index4] = (byte)(this.field2 >> index4 * 8);
      }
   }

   boolean method1(MixinHelper8_9 mixinhelper8_91) {
      return this.field2 == mixinhelper8_91.asInt();
   }
}
