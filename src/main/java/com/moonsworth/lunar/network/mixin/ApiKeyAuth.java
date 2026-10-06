package com.moonsworth.lunar.network.mixin;

import java.net.URI;
import java.util.List;
import java.util.Map;

public class ApiKeyAuth implements Authentication {
   private final String field1;
   private final String field2;
   private String field3;
   private String field4;

   public ApiKeyAuth(String text1, String text) {
      this.field1 = text1;
      this.field2 = text;
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

   public void method3(String text1) {
      this.field3 = text1;
   }

   public String method4() {
      return this.field4;
   }

   public void method5(String text1) {
      this.field4 = text1;
   }

   @Override
   public void method1(List<Pair> list, Map<String, String> map, Map<String, String> map2, String text, String text2, URI uri6) {
      if (this.field3 != null) {
         String text7;
         if (this.field4 != null) {
            text7 = this.field4 + " " + this.field3;
         } else {
            text7 = this.field3;
         }

         if ("query".equals(this.field1)) {
            list.add(new Pair(this.field2, text7));
         } else if ("header".equals(this.field1)) {
            map.put(this.field2, text7);
         } else if ("cookie".equals(this.field1)) {
            map2.put(this.field2, text7);
         }
      }
   }
}
