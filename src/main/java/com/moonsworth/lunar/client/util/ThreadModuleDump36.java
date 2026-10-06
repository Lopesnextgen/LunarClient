package com.moonsworth.lunar.client.util;

import java.util.UUID;
import lombok.Generated;

public final class ThreadModuleDump36 {
   public static String method1(String var0) {
      return var0.contains("-")
         ? var0
         : var0.replaceAll("-", "").replaceFirst("(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)", "$1-$2-$3-$4-$5");
   }

   public static UUID method2(String var0) {
      return UUID.fromString(var0.replaceFirst("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})", "$1-$2-$3-$4-$5"));
   }

   public static String method3(UUID var0) {
      return var0.toString().replace("-", "");
   }

   public static UUID method4(int[] var0) {
      return new UUID((long)var0[0] << 32 | var0[1] & 4294967295L, (long)var0[2] << 32 | var0[3] & 4294967295L);
   }

   public static int[] method5(UUID var0) {
      long var1 = var0.getMostSignificantBits();
      long var3 = var0.getLeastSignificantBits();
      return method6(var1, var3);
   }

   public static int[] method6(long var0, long value) {
      return new int[]{(int)(var0 >> 32), (int)var0, (int)(value >> 32), (int)value};
   }

   @Generated
   private ThreadModuleDump36() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
