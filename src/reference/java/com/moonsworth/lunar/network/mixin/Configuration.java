package com.moonsworth.lunar.network.mixin;

public class Configuration {
   public static final String field1 = "0.1.0-SNAPSHOT";
   private static ApiClient field2 = new ApiClient();

   public Configuration() {
   }

   public static ApiClient method1() {
      return field2;
   }

   public static void method2(ApiClient mixinhelper0) {
      field2 = mixinhelper0;
   }
}
