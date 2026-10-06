package com.moonsworth.lunar.network.mixin;

import java.net.URI;
import java.util.List;
import java.util.Map;

public class MixinHelper102 implements MixinHelper10 {
   private final String field1;
   private final String field2;
   private String field3;
   private String field4;

   public MixinHelper102(String var1, String var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   public String getLocation() {
      return this.field1;
   }

   public String method1() {
      return this.field2;
   }

   public String method2() {
      return this.field3;
   }

   public void method3(String var1) {
      this.field3 = var1;
   }

   public String method4() {
      return this.field4;
   }

   public void method5(String var1) {
      this.field4 = var1;
   }

   @Override
   public void method1(List<MixinHelper8> var1, Map<String, String> var2, Map<String, String> map, String text, String text2, URI uRI) {
      if (this.field3 != null) {
         String var7;
         if (this.field4 != null) {
            var7 = this.field4 + " " + this.field3;
         } else {
            var7 = this.field3;
         }

         if ("query".equals(this.field1)) {
            var1.add(new MixinHelper8(this.field2, var7));
         } else if ("header".equals(this.field1)) {
            var2.put(this.field2, var7);
         } else if ("cookie".equals(this.field1)) {
            map.put(this.field2, var7);
         }
      }
   }
}
