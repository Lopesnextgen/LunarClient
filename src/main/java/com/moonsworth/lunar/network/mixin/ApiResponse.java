package com.moonsworth.lunar.network.mixin;

import java.util.List;
import java.util.Map;

public class ApiResponse<T> {
   private final int field1;
   private final Map<String, List<String>> headers;
   private final T data;

   public ApiResponse(int number1, Map<String, List<String>> map2) {
      this(number1, map2, null);
   }

   public ApiResponse(int number1, Map<String, List<String>> map2, T t) {
      this.field1 = number1;
      this.headers = map2;
      this.data = (T)t;
   }

   public int method1() {
      return this.field1;
   }

   public Map<String, List<String>> getHeaders() {
      return this.headers;
   }

   public T getData() {
      return this.data;
   }
}
