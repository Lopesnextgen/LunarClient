package com.moonsworth.lunar.network.mixin;

import java.util.List;
import java.util.Map;

public class ApiException extends RuntimeException {
   private static final long field1 = 1L;
   private int code = 0;
   private Map<String, List<String>> field2 = null;
   private String field3 = null;

   public ApiException() {
   }

   public ApiException(Throwable exception1) {
      super(exception1);
   }

   public ApiException(String text1) {
      super(text1);
   }

   public ApiException(String text1, Throwable exception2, int number3, Map<String, List<String>> map4, String text) {
      super(text1, exception2);
      this.code = number3;
      this.field2 = map4;
      this.field3 = text;
   }

   public ApiException(String text1, int value, Map<String, List<String>> map3, String text4) {
      this(text1, (Throwable)null, value, map3, text4);
   }

   public ApiException(String text1, Throwable exception2, int number3, Map<String, List<String>> map4) {
      this(text1, exception2, number3, map4, null);
   }

   public ApiException(int number1, Map<String, List<String>> map, String text) {
      this("Response Code: " + number1 + " Response Body: " + text, (Throwable)null, number1, map, text);
   }

   public ApiException(int number1, String text2) {
      super(text2);
      this.code = number1;
   }

   public ApiException(int number1, String text2, Map<String, List<String>> map3, String text4) {
      this(number1, text2);
      this.field2 = map3;
      this.field3 = text4;
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
