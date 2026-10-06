package com.moonsworth.lunar.genesis;

import java.util.Arrays;

final class Longs$AsciiDigits {
   private static final byte[] field1;

   private Longs$AsciiDigits() {
   }

   static int digit(char character0) {
      return character0 < 128 ? field1[character0] : -1;
   }

   static {
      byte[] items0 = new byte[128];
      Arrays.fill(items0, (byte)-1);

      for (int index1 = 0; index1 < 10; index1++) {
         items0[48 + index1] = (byte)index1;
      }

      for (int index2 = 0; index2 < 26; index2++) {
         items0[65 + index2] = (byte)(10 + index2);
         items0[97 + index2] = (byte)(10 + index2);
      }

      field1 = items0;
   }
}
