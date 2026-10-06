package com.moonsworth.lunar.network.mixin;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class HttpBearerAuth implements Authentication {
   private final String scheme;
   private Supplier<String> field1;

   public HttpBearerAuth(String text1) {
      this.scheme = text1;
   }

   public String method1() {
      return this.field1.get();
   }

   public void method2(String text1) {
      this.field1 = () -> text1;
   }

   public void method3(Supplier<String> supplier1) {
      this.field1 = supplier1;
   }

   @Override
   public void method1(List<Pair> list, Map<String, String> map2, Map<String, String> map3, String text, String text2, URI uri6) {
      String text7 = Optional.ofNullable(this.field1).map(Supplier::get).orElse(null);
      if (text7 != null) {
         map2.put("Authorization", (this.scheme != null ? method5(this.scheme) + " " : "") + text7);
      }
   }

   private static String method5(String text) {
      return "bearer".equalsIgnoreCase(text) ? "Bearer" : text;
   }
}
