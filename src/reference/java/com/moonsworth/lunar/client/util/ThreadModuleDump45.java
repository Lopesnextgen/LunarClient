package com.moonsworth.lunar.client.util;

import com.moonsworth.lunar.client.guiRewindhandlers.mixin.GuiRewindhandlers4;
import java.text.DecimalFormat;
import lombok.Generated;

public class ThreadModuleDump45 {
   private static final DecimalFormat field1 = new DecimalFormat("0.00");
   private final long field2 = ThreadModuleDump63.method3().bridge$getSystemTime();
   private long lastUpdate = ThreadModuleDump63.method3().bridge$getSystemTime();
   private final boolean field3;
   private boolean field4;
   private long field5;
   private final long field6;
   private int steps;
   private long field7 = -1L;
   private long field8 = -1L;
   private long field9 = 0L;
   private boolean paused = true;

   public String method1() {
      return field1.format((float)this.get() / 1000.0F) + "s";
   }

   public long get() {
      if (this.paused) {
         return this.field5;
      }

      long var1 = ThreadModuleDump63.method3().bridge$getSystemTime();
      long var3 = var1 - this.lastUpdate;
      this.lastUpdate = var1;
      this.field5 = this.field5 + (this.field3 ? var3 - this.field9 : -var3 + this.field9);
      if (!this.field4) {
         return this.field5;
      }

      if (this.field8 == -1L) {
         return this.field5;
      }

      long var5 = 50L;
      long var7 = Math.min(var1 - this.field8, var5);
      long var9 = var5 * this.steps + var7 + this.field7;
      this.field5 = this.field3 ? this.field6 + var9 - this.field9 : this.field6 - var9 + this.field9;
      return this.field5;
   }

   public ThreadModuleDump45 method2() {
      long var1 = this.field5;
      this.paused = false;
      this.get();
      this.field9 = this.field9 + Math.abs(this.field5 - var1);
      this.field5 = var1;
      return this;
   }

   public void stop() {
      this.paused = true;
   }

   public void destroy() {
      this.stop();
      GuiRewindhandlers4.field1.remove(this);
   }

   private ThreadModuleDump45(ThreadModuleDump45.Data var1) {
      this.field3 = var1.field1;
      this.field4 = var1.field2;
      this.field5 = var1.field4;
      this.field6 = this.field5;
      GuiRewindhandlers4.field1.add(this);
      if (var1.field3) {
         ThreadModuleDump63.method4().method40().method82().method223().add(this);
      }
   }

   public void method3() {
      long var1 = ThreadModuleDump63.method3().bridge$getSystemTime();
      this.steps++;
      if (this.field7 == -1L) {
         this.field7 = var1 - this.field2;
      }

      this.field8 = var1;
   }

   @Generated
   public void method4(boolean var1) {
      this.field4 = var1;
   }

   @Generated
   public boolean isPaused() {
      return this.paused;
   }

   public static class Data {
      private boolean field1 = false;
      private boolean field2 = true;
      private boolean field3 = false;
      private long field4 = 0L;

      public static ThreadModuleDump45.Data method1() {
         return new ThreadModuleDump45.Data();
      }

      public ThreadModuleDump45.Data method2() {
         this.field3 = true;
         return this;
      }

      public ThreadModuleDump45.Data method3() {
         this.field1 = true;
         return this;
      }

      public ThreadModuleDump45.Data method4() {
         this.field1 = false;
         return this;
      }

      public ThreadModuleDump45.Data method5(long var1) {
         this.field4 = var1;
         return this;
      }

      public ThreadModuleDump45.Data method6(boolean var1) {
         this.field2 = var1;
         return this;
      }

      public ThreadModuleDump45 method7() {
         if (this.field3) {
            this.field2 = ThreadModuleDump63.method4().method40().method82().method35().get();
         }

         return new ThreadModuleDump45(this);
      }
   }
}
