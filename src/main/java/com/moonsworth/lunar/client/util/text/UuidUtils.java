package com.moonsworth.lunar.client.util.text;

import java.util.UUID;
import lombok.Generated;

public final class UuidUtils {
   public static String method1(String text0) {
      return text0.contains("-")
         ? text0
         : text0.replaceAll("-", "").replaceFirst("(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)", "$1-$2-$3-$4-$5");
   }

   public static UUID method2(String text0) {
      return UUID.fromString(text0.replaceFirst("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})", "$1-$2-$3-$4-$5"));
   }

   public static String method3(UUID uuid0) {
      return uuid0.toString().replace("-", "");
   }

   public static UUID method4(int[] items0) {
      return new UUID((long)items0[0] << 32 | items0[1] & 4294967295L, (long)items0[2] << 32 | items0[3] & 4294967295L);
   }

   public static int[] method5(UUID uuid0) {
      long number1 = uuid0.getMostSignificantBits();
      long number3 = uuid0.getLeastSignificantBits();
      return method6(number1, number3);
   }

   public static int[] method6(long value, long value2) {
      return new int[]{(int)(value >> 32), (int)value, (int)(value2 >> 32), (int)value2};
   }

   @Generated
   private UuidUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
