package com.moonsworth.lunar.client.ui;

import lombok.Generated;

public abstract class AnimationTimer {
   protected long startTime;
   protected long field1;
   protected long durationMs;
   protected boolean active = true;
   protected float field2;
   protected long field3;
   protected final float field4;
   private boolean field5;
   private int field6 = 1;
   private int field7 = 1;
   private boolean field8;
   private boolean field9;

   public AnimationTimer(long var1, float value) {
      this.durationMs = var1;
      this.field4 = value;
   }

   protected abstract float method1();

   protected abstract long method2(float var1);

   public void start() {
      this.startTime = System.currentTimeMillis();
      this.active = true;
   }

   public void method3(long var1) {
      this.startTime = System.currentTimeMillis() - var1;
      this.active = true;
   }

   public void method4() {
      this.field5 = true;
   }

   public boolean method5() {
      return this.startTime != 0L;
   }

   public boolean method6() {
      return this.method13() <= 0L && this.active;
   }

   public void stop() {
      this.startTime = 0L;
      this.field6 = 1;
      this.active = false;
   }

   public boolean method7() {
      return this.startTime != 0L && this.method13() > 0L;
   }

   private float method8() {
      if (this.startTime == 0L) {
         return 0.0F;
      } else {
         return this.method13() <= 0L ? 1.0F : this.method1();
      }
   }

   public float method9() {
      if (this.startTime == 0L) {
         return 0.0F;
      }

      if (!this.method6()) {
         return this.active ? this.method1() : this.field2;
      }

      if (this.field5 || this.field7 >= 1 && this.field6 < this.field7) {
         this.start();
         this.field6++;
      }

      return this.field8 ? 1.0F - this.field4 : this.field4;
   }

   public void pause() {
      this.active = false;
      this.field2 = this.method1();
      this.field3 = System.currentTimeMillis() - this.startTime;
   }

   public void method10() {
      this.startTime = System.currentTimeMillis() - this.field3;
      this.active = true;
   }

   public long method11() {
      long var1;
      if (this.active) {
         var1 = this.method13();
      } else {
         var1 = System.currentTimeMillis() - this.field3 + this.durationMs - System.currentTimeMillis();
      }

      return Math.min(this.durationMs, Math.max(0L, var1));
   }

   public long method12() {
      return this.durationMs - this.method11();
   }

   protected long method13() {
      return this.startTime + this.durationMs - System.currentTimeMillis();
   }

   @Generated
   public long getStartTime() {
      return this.startTime;
   }

   @Generated
   public long method14() {
      return this.field1;
   }

   @Generated
   public long getDurationMs() {
      return this.durationMs;
   }

   @Generated
   public void setDurationMs(long var1) {
      this.durationMs = var1;
   }

   @Generated
   public boolean isActive() {
      return this.active;
   }

   @Generated
   public float method15() {
      return this.field2;
   }

   @Generated
   public long method16() {
      return this.field3;
   }

   @Generated
   public float method17() {
      return this.field4;
   }

   @Generated
   public boolean method18() {
      return this.field5;
   }

   @Generated
   public void method19(int var1) {
      this.field7 = var1;
   }

   @Generated
   public void method20(boolean var1) {
      this.field8 = var1;
   }

   @Generated
   public boolean method21() {
      return this.field8;
   }

   @Generated
   public boolean method22() {
      return this.field9;
   }
}
