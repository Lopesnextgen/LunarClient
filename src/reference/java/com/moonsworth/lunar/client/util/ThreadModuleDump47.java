package com.moonsworth.lunar.client.util;

import com.moonsworth.lunar.client.ui.LcuiScreen;
import java.util.ArrayList;
import java.util.List;

public class ThreadModuleDump47 {
   private final ThreadModuleDump47.Data2 field1;
   private List<ThreadModuleDump47.Data2> nodes = null;
   private float field2;
   private ThreadModuleDump47[] field3 = null;

   public ThreadModuleDump47(int var1, int var2, int var3, int var4, float var5) {
      this.field2 = var5;
      this.field1 = new ThreadModuleDump47.Data2(ThreadModuleDump70.of(var1, var2, var3, var4), var5);
   }

   public float method1(ThreadModuleDump70 var1) {
      if (!this.field1.field1.method4(var1)) {
         return Float.MIN_VALUE;
      }

      if (var1.contains(this.field1.field1)) {
         return this.field2;
      }

      float var2 = this.field1.field2;
      if (this.nodes != null) {
         for (ThreadModuleDump47.Data2 var4 : this.nodes) {
            if (var4.field1.method4(var1)) {
               var2 = Math.max(var2, var4.field2);
            }
         }
      }

      if (this.field3 != null) {
         for (ThreadModuleDump47 var6 : this.field3) {
            var2 = Math.max(var2, var6.method1(var1));
         }
      }

      return var2;
   }

   private void method2(ThreadModuleDump47.Data2 var1) {
      this.field2 = Math.max(this.field2, var1.field2);
      if (this.field3 == null) {
         if (this.nodes == null) {
            this.nodes = new ArrayList<>(5);
         }

         this.nodes.add(var1);
         if (this.nodes.size() > 5 && (this.field1.field1.method1() > 5 || this.field1.field1.method2() > 5)) {
            this.method3();
         }
      } else {
         this.method4(var1);
      }
   }

   private void method3() {
      int var1 = (int)Math.ceil(this.field1.field1.method1() / 2.0);
      int var2 = (int)Math.ceil(this.field1.field1.method2() / 2.0);
      int var3 = this.field1.field1.method1() - var1;
      int var4 = this.field1.field1.method2() - var2;
      this.field3 = new ThreadModuleDump47[]{
         new ThreadModuleDump47(this.field1.field1.method10(), this.field1.field1.method11(), var1, var2, this.field1.field2),
         new ThreadModuleDump47(this.field1.field1.method10() + var1, this.field1.field1.method11(), var3, var2, this.field1.field2),
         new ThreadModuleDump47(this.field1.field1.method10(), this.field1.field1.method11() + var2, var1, var4, this.field1.field2),
         new ThreadModuleDump47(this.field1.field1.method10() + var3, this.field1.field1.method11() + var4, var3, var4, this.field1.field2)
      };
      List var5 = this.nodes;
      this.nodes = null;

      for (ThreadModuleDump47.Data2 var7 : var5) {
         this.method4(var7);
      }
   }

   private void method4(ThreadModuleDump47.Data2 var1) {
      boolean var2 = this.field3[0].field1.field1.method4(var1.field1);
      boolean var3 = this.field3[1].field1.field1.method4(var1.field1);
      boolean var4 = this.field3[2].field1.field1.method4(var1.field1);
      boolean var5 = this.field3[3].field1.field1.method4(var1.field1);
      int var6 = 0;
      if (var2) {
         var6++;
      }

      if (var3) {
         var6++;
      }

      if (var4) {
         var6++;
      }

      if (var5) {
         var6++;
      }

      if (var6 >= 2) {
         if (this.nodes == null) {
            this.nodes = new ArrayList<>();
         }

         this.nodes.add(var1);
      } else {
         if (var2) {
            this.field3[0].method2(var1);
         }

         if (var3) {
            this.field3[1].method2(var1);
         }

         if (var4) {
            this.field3[2].method2(var1);
         }

         if (var5) {
            this.field3[3].method2(var1);
         }
      }
   }

   public static class Data {
      private final ThreadModuleDump47 field1;
      private final List<ThreadModuleDump47.Data2> field2 = new ArrayList<>();
      private final float field3;
      private float field4;

      public Data(float var1) {
         this.field3 = var1;
         this.field4 = var1;
         ThreadModuleDump71 var2 = LcuiScreen.method151();
         if (var2 == null) {
            this.field1 = new ThreadModuleDump47(0, 0, 1920, 1080, var1);
         } else {
            this.field1 = new ThreadModuleDump47(0, 0, LcuiScreen.method151().getScaledWidth(), LcuiScreen.method151().getScaledHeight(), var1);
         }
      }

      public void method1(ThreadModuleDump70 var1, float var2) {
         ThreadModuleDump47.Data2 var3 = new ThreadModuleDump47.Data2(var1, var2);
         if (this.field1.field1.field1.method4(var3.field1)) {
            this.field1.method2(var3);
         }

         if (!this.field1.field1.field1.method5(var3.field1)) {
            this.field2.add(var3);
         }

         this.field4 = Math.max(this.field4, var3.field2);
      }

      public float method2(ThreadModuleDump70 var1) {
         float var2 = this.field3;
         var2 = Math.max(var2, this.field1.method1(var1));
         if (!this.field1.field1.field1.method5(var1)) {
            for (ThreadModuleDump47.Data2 var4 : this.field2) {
               if (var4.field1.method4(var1)) {
                  var2 = Math.max(var2, var4.field2);
               }
            }
         }

         return var2;
      }

      public float max() {
         return this.field4;
      }
   }

   private class Data2 {
      private final ThreadModuleDump70 field1;
      private final float field2;

      private Data2(ThreadModuleDump70 var1, float var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      public ThreadModuleDump70 method1() {
         return this.field1;
      }

      public float value() {
         return this.field2;
      }
   }
}
