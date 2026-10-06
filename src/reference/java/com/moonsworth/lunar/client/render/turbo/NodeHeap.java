package com.moonsworth.lunar.client.render.turbo;

import java.util.Arrays;
import lombok.Generated;

public class NodeHeap {
   public PathNode[] field1 = new PathNode[128];
   public int size;

   public static NodeHeap method1() {
      return new NodeHeap();
   }

   public PathNode method2(PathNode var1) {
      if (var1.field1 >= 0) {
         throw new IllegalStateException("Failed to insert node because it is already in the heap");
      }

      if (this.size == this.field1.length) {
         PathNode[] var2 = new PathNode[this.size << 1];
         System.arraycopy(this.field1, 0, var2, 0, this.size);
         this.field1 = var2;
      }

      this.field1[this.size] = var1;
      var1.field1 = this.size;
      this.method5(this.size++);
      return var1;
   }

   public void clear() {
      this.size = 0;
   }

   public PathNode method3() {
      PathNode var1 = this.field1[0];
      this.field1[0] = this.field1[--this.size];
      this.field1[this.size] = null;
      if (this.size > 0) {
         this.downHeap(0);
      }

      var1.field1 = -1;
      return var1;
   }

   public void method4(PathNode var1, float var2) {
      float var3 = var1.field4;
      var1.field4 = var2;
      if (var2 < var3) {
         this.method5(var1.field1);
      } else {
         this.downHeap(var1.field1);
      }
   }

   public void method5(int var1) {
      PathNode var2 = this.field1[var1];
      float var4 = var2.field4;

      while (var1 > 0) {
         int var3 = var1 - 1 >> 1;
         PathNode var5 = this.field1[var3];
         if (!(var4 < var5.field4)) {
            break;
         }

         this.field1[var1] = var5;
         var5.field1 = var1;
         var1 = var3;
      }

      this.field1[var1] = var2;
      var2.field1 = var1;
   }

   public void downHeap(int var1) {
      PathNode var2 = this.field1[var1];
      float var3 = var2.field4;

      while (true) {
         int var4 = 1 + (var1 << 1);
         int var5 = var4 + 1;
         if (var4 >= this.size) {
            break;
         }

         PathNode var6 = this.field1[var4];
         float var7 = var6.field4;
         PathNode var8;
         float var9;
         if (var5 >= this.size) {
            var8 = null;
            var9 = Float.POSITIVE_INFINITY;
         } else {
            var8 = this.field1[var5];
            var9 = var8.field4;
         }

         if (var7 < var9) {
            if (!(var7 < var3)) {
               break;
            }

            this.field1[var1] = var6;
            var6.field1 = var1;
            var1 = var4;
         } else {
            if (!(var9 < var3)) {
               break;
            }

            this.field1[var1] = var8;
            var8.field1 = var1;
            var1 = var5;
         }
      }

      this.field1[var1] = var2;
      var2.field1 = var1;
   }

   public boolean isEmpty() {
      return this.size == 0;
   }

   public PathNode method6() {
      return this.field1[0];
   }

   public void method7(PathNode var1) {
      this.field1[var1.field1] = this.field1[--this.size];
      this.field1[this.size] = null;
      if (this.size > var1.field1) {
         if (this.field1[var1.field1].field4 < var1.field4) {
            this.method5(var1.field1);
         } else {
            this.downHeap(var1.field1);
         }
      }

      var1.field1 = -1;
   }

   public int size() {
      return this.size;
   }

   public PathNode[] method8() {
      return Arrays.copyOf(this.field1, this.size);
   }
}
