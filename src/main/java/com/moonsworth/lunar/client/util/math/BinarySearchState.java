package com.moonsworth.lunar.client.util.math;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BinarySearchState {
   private float field1;
   private float field2;
   private float field3;
   private Stack<BinarySearchState.Data> field4;
   private Stack<BinarySearchState.Data> field5;
   private static Map<String, BinarySearchState> instances = new HashMap<>();

   public BinarySearchState(float value1) {
      if (value1 > 0.0F) {
         this.field2 = 0.0F;
         this.field3 = value1;
         this.field1 = value1;
      } else if (value1 < 0.0F) {
         this.field2 = value1;
         this.field3 = 0.0F;
         this.field1 = value1;
      } else {
         this.field2 = -10.0F;
         this.field3 = 10.0F;
         this.field1 = 0.0F;
      }

      this.field4 = new Stack<>();
      this.field5 = new Stack<>();
   }

   public float method1(boolean flag) {
      this.field4.push(new BinarySearchState.Data(this.field1, this.field2, this.field3));
      this.field5.clear();
      if (flag) {
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
         this.field5.push(new BinarySearchState.Data(this.field1, this.field2, this.field3));
         BinarySearchState.Data data1 = this.field4.pop();
         this.field1 = data1.field1;
         this.field2 = data1.field2;
         this.field3 = data1.field3;
      }

      return this.field1;
   }

   public float method3() {
      if (!this.field5.isEmpty()) {
         this.field4.push(new BinarySearchState.Data(this.field1, this.field2, this.field3));
         BinarySearchState.Data data1 = this.field5.pop();
         this.field1 = data1.field1;
         this.field2 = data1.field2;
         this.field3 = data1.field3;
      }

      return this.field1;
   }

   public float getCurrent() {
      return this.field1;
   }

   public static BinarySearchState method4(String text) {
      if (!instances.containsKey(text)) {
         instances.put(text, new BinarySearchState(0.0F));
      }

      return instances.get(text);
   }

   private static class Data {
      float field1;
      float field2;
      float field3;

      Data(float value1, float value, float value2) {
         this.field1 = value1;
         this.field2 = value;
         this.field3 = value2;
      }
   }
}
