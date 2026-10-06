package com.moonsworth.lunar.network.mixin;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class MixinHelper104 implements MixinHelper10 {
   private final String scheme;
   private Supplier<String> field1;

   public MixinHelper104(String var1) {
      this.scheme = var1;
   }

   public String method1() {
      return this.field1.get();
   }

   public void method2(String var1) {
      this.field1 = () -> var1;
   }

   public void method3(Supplier<String> var1) {
      this.field1 = var1;
   }

   @Override
   public void method1(List<MixinHelper8> var1, Map<String, String> map2, Map<String, String> map3, String text, String text2, URI uRI) {
      String var7 = Optional.ofNullable(this.field1).map(Supplier::get).orElse(null);
      if (var7 != null) {
         map2.put("Authorization", (this.scheme != null ? method5(this.scheme) + " " : "") + var7);
      }
   }

   private static String method5(String text) {
      return "bearer".equalsIgnoreCase(text) ? "Bearer" : text;
   }
}
