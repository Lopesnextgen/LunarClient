package com.moonsworth.lunar.genesis;
import com.google.common.primitives.Longs;

enum LittleEndianByteArray$JavaLittleEndianBytes$1 {
   ;
   LittleEndianByteArray$JavaLittleEndianBytes$1() {
   }

   public long getLongLittleEndian(byte[] items1, int index2) {
      return Longs.fromBytes(
         items1[index2 + 7], items1[index2 + 6], items1[index2 + 5], items1[index2 + 4], items1[index2 + 3], items1[index2 + 2], items1[index2 + 1], items1[index2]
      );
   }

   public void putLongLittleEndian(byte[] items1, int index2, long number3) {
      long number5 = 255L;

      for (int index7 = 0; index7 < 8; index7++) {
         items1[index2 + index7] = (byte)((number3 & number5) >> index7 * 8);
         number5 <<= 8;
      }
   }
}
