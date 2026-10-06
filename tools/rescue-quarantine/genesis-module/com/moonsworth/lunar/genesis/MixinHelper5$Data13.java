package com.moonsworth.lunar.genesis;

import java.util.Arrays;

final class MixinHelper5$Data13 {
   private static final byte[] field1;

   private MixinHelper5$Data13() {
   }

   static int digit(char var0) {
      return var0 < 128 ? field1[var0] : -1;
   }

   static {
      byte[] var0 = new byte[128];
      Arrays.fill(var0, (byte)-1);

      for (int var1 = 0; var1 < 10; var1++) {
         var0[48 + var1] = (byte)var1;
      }

      for (int var2 = 0; var2 < 26; var2++) {
         var0[65 + var2] = (byte)(10 + var2);
         var0[97 + var2] = (byte)(10 + var2);
      }

      field1 = var0;
   }
}
