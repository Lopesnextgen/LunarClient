package com.moonsworth.lunar.client.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ThreadModuleDump25 {
   private float field1;
   private float field2;
   private float field3;
   private Stack<ThreadModuleDump25.Data> field4;
   private Stack<ThreadModuleDump25.Data> field5;
   private static Map<String, ThreadModuleDump25> instances = new HashMap<>();

   public ThreadModuleDump25(float var1) {
      if (var1 > 0.0F) {
         this.field2 = 0.0F;
         this.field3 = var1;
         this.field1 = var1;
      } else if (var1 < 0.0F) {
         this.field2 = var1;
         this.field3 = 0.0F;
         this.field1 = var1;
      } else {
         this.field2 = -10.0F;
         this.field3 = 10.0F;
         this.field1 = 0.0F;
      }

      this.field4 = new Stack<>();
      this.field5 = new Stack<>();
   }

   public float method1(boolean var1) {
      this.field4.push(new ThreadModuleDump25.Data(this.field1, this.field2, this.field3));
      this.field5.clear();
      if (var1) {
         this.field2 = this.field1;
         this.field1 = (this.field1 + this.field3) / 2.0F;
      } else {
         this.field3 = this.field1;
         this.field1 = (this.field2 + this.field1) / 2.0F;
      }

      return this.field1;
   }

   public float method2() {
      if (!this.field4.isEmpty()) {
         this.field5.push(new ThreadModuleDump25.Data(this.field1, this.field2, this.field3));
         ThreadModuleDump25.Data var1 = this.field4.pop();
         this.field1 = var1.field1;
         this.field2 = var1.field2;
         this.field3 = var1.field3;
      }

      return this.field1;
   }

   public float method3() {
      if (!this.field5.isEmpty()) {
         this.field4.push(new ThreadModuleDump25.Data(this.field1, this.field2, this.field3));
         ThreadModuleDump25.Data var1 = this.field5.pop();
         this.field1 = var1.field1;
         this.field2 = var1.field2;
         this.field3 = var1.field3;
      }

      return this.field1;
   }

   public float getCurrent() {
      return this.field1;
   }

   public static ThreadModuleDump25 method4(String text) {
      if (!instances.containsKey(text)) {
         instances.put(text, new ThreadModuleDump25(0.0F));
      }

      return instances.get(text);
   }

   private static class Data {
      float field1;
      float field2;
      float field3;

      Data(float var1, float value, float value2) {
         this.field1 = var1;
         this.field2 = value;
         this.field3 = value2;
      }
   }
}
