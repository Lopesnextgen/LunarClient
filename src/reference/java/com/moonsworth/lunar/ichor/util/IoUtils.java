package com.moonsworth.lunar.ichor.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public final class IoUtils {
   private IoUtils() {
      throw new IllegalStateException("This class can not be instantiated!");
   }

   public static long copy(InputStream input0, OutputStream output1) {
      byte[] items2 = new byte[4096];
      long number3 = 0L;

      while (true) {
         int number5 = input0.read(items2);
         if (number5 == -1) {
            return number3;
         }

         output1.write(items2, 0, number5);
         number3 += number5;
      }
   }

   public static byte[] toByteArray(InputStream input0) {
      return method1(input0, true);
   }

   public static byte[] method1(InputStream input0, boolean flag1) {
      ByteArrayOutputStream bytearrayoutputstream2 = new ByteArrayOutputStream();
      copy(input0, bytearrayoutputstream2);
      if (flag1) {
         input0.close();
      }

      return bytearrayoutputstream2.toByteArray();
   }
}
