package com.moonsworth.lunar.client.framework.feature.rewind.highlight.nameplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import lombok.Generated;

public class Nameplate2 {
   private final Stack<Nameplate2.Data3> field1 = new Stack<>();
   private final Stack<Nameplate2.Data3> field2 = new Stack<>();
   private List<Nameplate2.Data3> field3 = null;
   private boolean running = false;

   public void method1() {
      if (!this.running) {
         this.field3 = new ArrayList<>();
      }
   }

   public void endBatch() {
      if (!this.running) {
         if (this.field3 != null) {
            if (!this.field3.isEmpty()) {
               this.field1.push(new Nameplate2.Data2(this.field3));
               this.field2.clear();
            }

            this.field3 = null;
         }
      }
   }

   public void method2() {
      if (!this.running && this.method3()) {
         Nameplate2.Data2 var1 = new Nameplate2.Data2(this.field3);
         this.field3 = null;
         this.running = true;

         try {
            var1.field1.run();
         } finally {
            this.running = false;
         }
      }
   }

   public boolean method3() {
      return this.field3 != null;
   }

   public void method4(Runnable var1, Runnable var2) {
      if (!this.running) {
         Nameplate2.Data3 var3 = new Nameplate2.Data3(var1, var2);
         if (this.method3()) {
            this.field3.add(var3);
         } else {
            this.field1.push(var3);
            this.field2.clear();
         }

         if (this.field1.size() > 50) {
            this.field1.remove(0);
         }
      }
   }

   public void undo() {
      if (!this.field1.isEmpty()) {
         Nameplate2.Data3 var1 = this.field1.pop();
         this.running = true;
         var1.field1.run();
         this.running = false;
         this.field2.push(var1);
      }
   }

   public void redo() {
      if (!this.field2.isEmpty()) {
         Nameplate2.Data3 var1 = this.field2.pop();
         this.running = true;
         var1.field2.run();
         this.running = false;
         this.field1.push(var1);
      }
   }

   public void clear() {
      this.field1.clear();
      this.field2.clear();
   }

   public boolean canUndo() {
      return !this.field1.isEmpty();
   }

   public boolean canRedo() {
      return !this.field2.isEmpty();
   }

   @Generated
   public void setRunning(boolean var1) {
      this.running = var1;
   }

   public static class Data2 extends Nameplate2.Data3 {
      public Data2(List<Nameplate2.Data3> var1) {
         super(() -> {
            for (int var1x = var1.size() - 1; var1x >= 0; var1x--) {
               ((Nameplate2.Data3)var1.get(var1x)).field1.run();
            }
         }, () -> {
            for (Nameplate2.Data3 var2 : var1) {
               var2.field2.run();
            }
         });
      }
   }

   public static class Data3 {
      private final Runnable field1;
      private final Runnable field2;

      @Generated
      public Data3(Runnable var1, Runnable var2) {
         this.field1 = var1;
         this.field2 = var2;
      }
   }
}
