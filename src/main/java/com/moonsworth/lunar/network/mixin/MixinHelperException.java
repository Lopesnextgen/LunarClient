package com.moonsworth.lunar.network.mixin;

import java.util.List;
import java.util.Map;

public class MixinHelperException extends RuntimeException {
   private static final long field1 = 1L;
   private int code = 0;
   private Map<String, List<String>> field2 = null;
   private String field3 = null;

   public MixinHelperException() {
   }

   public MixinHelperException(Throwable var1) {
      super(var1);
   }

   public MixinHelperException(String var1) {
      super(var1);
   }

   public MixinHelperException(String var1, Throwable var2, int var3, Map<String, List<String>> var4, String text) {
      super(var1, var2);
      this.code = var3;
      this.field2 = var4;
      this.field3 = text;
   }

   public MixinHelperException(String var1, int var2, Map<String, List<String>> var3, String var4) {
      this(var1, (Throwable)null, var2, var3, var4);
   }

   public MixinHelperException(String var1, Throwable var2, int var3, Map<String, List<String>> var4) {
      this(var1, var2, var3, var4, null);
   }

   public MixinHelperException(int var1, Map<String, List<String>> var2, String var3) {
      this("Response Code: " + var1 + " Response Body: " + var3, (Throwable)null, var1, var2, var3);
   }

   public MixinHelperException(int var1, String var2) {
      super(var2);
      this.code = var1;
   }

   public MixinHelperException(int var1, String var2, Map<String, List<String>> var3, String var4) {
      this(var1, var2);
      this.field2 = var3;
      this.field3 = var4;
   }

   public int getCode() {
      return this.code;
   }

   public Map<String, List<String>> method1() {
      return this.field2;
   }

   public String method2() {
      return this.field3;
   }

   @Override
   public String getMessage() {
      return String.format(
         "Message: %s%nHTTP response code: %s%nHTTP response body: %s%nHTTP response headers: %s",
         super.getMessage(),
         this.getCode(),
         this.method2(),
         this.method1()
      );
   }
}
