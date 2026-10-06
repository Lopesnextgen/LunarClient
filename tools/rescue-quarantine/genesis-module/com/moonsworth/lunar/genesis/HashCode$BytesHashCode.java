package com.moonsworth.lunar.genesis;

import java.io.Serializable;
import com.google.common.base.Preconditions;

final class HashCode$BytesHashCode extends MixinHelper8_9 implements Serializable {
   final byte[] field2;
   private static final long field3 = 0L;

   HashCode$BytesHashCode(byte[] items1) {
      this.field2 = Preconditions.checkNotNull(items1);
   }

   public int bits() {
      return this.field2.length * 8;
   }

   public byte[] asBytes() {
      return (byte[])this.field2.clone();
   }

   public int asInt() {
      Preconditions.checkState(this.field2.length >= 4, "HashCode#asInt() requires >= 4 bytes (it only has %s bytes).", this.field2.length);
      return this.field2[0] & 0xFF | (this.field2[1] & 0xFF) << 8 | (this.field2[2] & 0xFF) << 16 | (this.field2[3] & 0xFF) << 24;
   }

   public long asLong() {
      Preconditions.checkState(this.field2.length >= 8, "HashCode#asLong() requires >= 8 bytes (it only has %s bytes).", this.field2.length);
      return this.padToLong();
   }

   public long padToLong() {
      long number1 = this.field2[0] & 0xFF;

      for (int index3 = 1; index3 < Math.min(this.field2.length, 8); index3++) {
         number1 |= (this.field2[index3] & 255L) << index3 * 8;
      }

      return number1;
   }

   void writeBytesToImpl(byte[] items1, int number2, int number3) {
      System.arraycopy(this.field2, 0, items1, number2, number3);
   }

   byte[] getBytesInternal() {
      return this.field2;
   }

   boolean method1(MixinHelper8_9 mixinhelper8_91) {
      if (this.field2.length != mixinhelper8_91.getBytesInternal().length) {
         return false;
      }

      boolean flag2 = true;

      for (int index3 = 0; index3 < this.field2.length; index3++) {
         flag2 &= this.field2[index3] == mixinhelper8_91.getBytesInternal()[index3];
      }

      return flag2;
   }
}
