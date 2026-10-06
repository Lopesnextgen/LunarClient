package com.moonsworth.lunar.ichor.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public final class FatalIchorError7 {
   private FatalIchorError7() {
      throw new IllegalStateException("This class can not be instantiated!");
   }

   public static long copy(InputStream var0, OutputStream var1) {
      byte[] var2 = new byte[4096];
      long var3 = 0L;

      while (true) {
         int var5 = var0.read(var2);
         if (var5 == -1) {
            return var3;
         }

         var1.write(var2, 0, var5);
         var3 += var5;
      }
   }

   public static byte[] toByteArray(InputStream var0) {
      return method1(var0, true);
   }

   public static byte[] method1(InputStream var0, boolean var1) {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      copy(var0, var2);
      if (var1) {
         var0.close();
      }

      return var2.toByteArray();
   }
}
