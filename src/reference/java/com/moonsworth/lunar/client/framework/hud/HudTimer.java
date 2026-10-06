package com.moonsworth.lunar.client.framework.hud;

import com.moonsworth.lunar.client.framework.listener.HudTimerTicker;
import java.text.DecimalFormat;
import lombok.Generated;
import com.moonsworth.lunar.client.framework.Ref;

public class HudTimer {
   private static final DecimalFormat field1 = new DecimalFormat("0.00");
   private final long field2 = Ref.method3().bridge$getSystemTime();
   private long lastUpdate = Ref.method3().bridge$getSystemTime();
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

      long number1 = Ref.method3().bridge$getSystemTime();
      long number3 = number1 - this.lastUpdate;
      this.lastUpdate = number1;
      this.field5 = this.field5 + (this.field3 ? number3 - this.field9 : -number3 + this.field9);
      if (!this.field4) {
         return this.field5;
      }

      if (this.field8 == -1L) {
         return this.field5;
      }

      long number5 = 50L;
      long number7 = Math.min(number1 - this.field8, number5);
      long number9 = number5 * this.steps + number7 + this.field7;
      this.field5 = this.field3 ? this.field6 + number9 - this.field9 : this.field6 - number9 + this.field9;
      return this.field5;
   }

   public HudTimer method2() {
      long number1 = this.field5;
      this.paused = false;
      this.get();
      this.field9 = this.field9 + Math.abs(this.field5 - number1);
      this.field5 = number1;
      return this;
   }

   public void stop() {
      this.paused = true;
   }

   public void destroy() {
      this.stop();
      HudTimerTicker.field1.remove(this);
   }

   private HudTimer(HudTimer.Data data) {
      this.field3 = data.field1;
      this.field4 = data.field2;
      this.field5 = data.field4;
      this.field6 = this.field5;
      HudTimerTicker.field1.add(this);
      if (data.field3) {
         Ref.method4().method40().method82().method223().add(this);
      }
   }

   public void method3() {
      long number1 = Ref.method3().bridge$getSystemTime();
      this.steps++;
      if (this.field7 == -1L) {
         this.field7 = number1 - this.field2;
      }

      this.field8 = number1;
   }

   @Generated
   public void method4(boolean flag1) {
      this.field4 = flag1;
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

      public Data() {
      }

      public static HudTimer.Data method1() {
         return new HudTimer.Data();
      }

      public HudTimer.Data method2() {
         this.field3 = true;
         return this;
      }

      public HudTimer.Data method3() {
         this.field1 = true;
         return this;
      }

      public HudTimer.Data method4() {
         this.field1 = false;
         return this;
      }

      public HudTimer.Data method5(long number1) {
         this.field4 = number1;
         return this;
      }

      public HudTimer.Data method6(boolean flag1) {
         this.field2 = flag1;
         return this;
      }

      public HudTimer method7() {
         if (this.field3) {
            this.field2 = (Boolean)Ref.method4().method40().method82().method35().get();
         }

         return new HudTimer(this);
      }
   }
}
