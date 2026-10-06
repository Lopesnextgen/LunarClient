package com.moonsworth.lunar.network.mixin;

import java.util.List;
import java.util.Map;

public class MixinHelper4<T> {
   private final int field1;
   private final Map<String, List<String>> headers;
   private final T data;

   public MixinHelper4(int var1, Map<String, List<String>> var2) {
      this(var1, var2, null);
   }

   public MixinHelper4(int var1, Map<String, List<String>> var2, T t) {
      this.field1 = var1;
      this.headers = var2;
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
