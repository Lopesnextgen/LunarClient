package com.moonsworth.lunar.client.util;

import com.moonsworth.lunar.client.ui.LcuiScreen;
import java.util.ArrayList;
import java.util.List;

public class ThreadModuleDump7 {
   private final ThreadModuleDump70 field1;
   private List<ThreadModuleDump70> nodes = null;
   private boolean field2 = false;
   private ThreadModuleDump7[] children = null;

   public ThreadModuleDump7(int var1, int var2, int var3, int var4) {
      this.bounds = ThreadModuleDump70.of(var1, var2, var3, var4);
   }

   public boolean intersects(ThreadModuleDump70 var1) {
      if (!this.bounds.method4(var1)) {
         return false;
      }

      if (this.covered) {
         return true;
      }

      if (this.nodes != null) {
         for (ThreadModuleDump70 var3 : this.nodes) {
            if (var3.method4(var1)) {
               return true;
            }
         }
      }

      if (this.children != null) {
         for (ThreadModuleDump7 var5 : this.children) {
            if (var5.method1(var1)) {
               return true;
            }
         }
      }

      return false;
   }

   public void add(ThreadModuleDump70 var1) {
      if (!this.covered) {
         if (var1.contains(this.bounds)) {
            this.children = null;
            this.covered = true;
            this.nodes = null;
         } else if (this.children == null) {
            if (this.nodes == null) {
               this.nodes = new ArrayList<>(5);
            }

            this.nodes.add(var1);
            if (this.nodes.size() > 5 && (this.bounds.method1() > 5 || this.bounds.method2() > 5)) {
               this.split();
            }
         } else {
            this.insertIntoChildren(var1);
         }
      }
   }

   private void split() {
      int var1 = (int)Math.ceil(this.bounds.method1() / 2.0);
      int var2 = (int)Math.ceil(this.bounds.method2() / 2.0);
      int var3 = this.bounds.method1() - var1;
      int var4 = this.bounds.method2() - var2;
      this.children = new ThreadModuleDump7[]{
         new ThreadModuleDump7(this.bounds.method10(), this.bounds.method11(), var1, var2),
         new ThreadModuleDump7(this.bounds.method10() + var1, this.bounds.method11(), var3, var2),
         new ThreadModuleDump7(this.bounds.method10(), this.bounds.method11() + var2, var1, var4),
         new ThreadModuleDump7(this.bounds.method10() + var3, this.bounds.method11() + var4, var3, var4)
      };
      List var5 = this.nodes;
      this.nodes = null;

      for (ThreadModuleDump70 var7 : var5) {
         this.insertIntoChildren(var7);
      }
   }

   private void insertIntoChildren(ThreadModuleDump70 var1) {
      boolean var2 = this.children[0].field1.intersects(var1);
      boolean var3 = this.children[1].field1.intersects(var1);
      boolean var4 = this.children[2].field1.intersects(var1);
      boolean var5 = this.children[3].field1.intersects(var1);
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
            this.children[0].method2(var1);
         }

         if (var3) {
            this.children[1].method2(var1);
         }

         if (var4) {
            this.children[2].method2(var1);
         }

         if (var5) {
            this.children[3].method2(var1);
         }
      }
   }

   public static class Data {
      private final ThreadModuleDump7 field1;
      private final List<ThreadModuleDump70> field2 = new ArrayList<>();

      public Data() {
         ThreadModuleDump71 var1 = LcuiScreen.method151();
         if (var1 == null) {
            this.bounds = new ThreadModuleDump7(0, 0, 1920, 1080);
         } else {
            this.bounds = new ThreadModuleDump7(0, 0, LcuiScreen.method151().getScaledWidth(), LcuiScreen.method151().getScaledHeight());
         }
      }

      public void intersects(ThreadModuleDump70 var1) {
         if (this.bounds.field1.intersects(var1)) {
            this.bounds.method2(var1);
         }

         if (!this.bounds.field1.method5(var1)) {
            this.covered.add(var1);
         }
      }

      public boolean add(ThreadModuleDump70 var1) {
         if (this.bounds.method1(var1)) {
            return true;
         }

         if (!this.bounds.field1.method5(var1)) {
            for (ThreadModuleDump70 var3 : this.covered) {
               if (var3.method4(var1)) {
                  return true;
               }
            }
         }

         return false;
      }
   }
}
