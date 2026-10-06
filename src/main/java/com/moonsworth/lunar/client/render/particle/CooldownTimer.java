package com.moonsworth.lunar.client.render.particle;

public class CooldownTimer {
   public boolean enabled;
   public long time;
   public long duration;

   public CooldownTimer(long number1) {
      this.duration = number1;
   }

   public long getRemaining() {
      return this.time - System.currentTimeMillis();
   }

   public void mark() {
      this.mark(this.duration);
   }

   public void mark(long number1) {
      this.enabled = true;
      this.time = System.currentTimeMillis() + number1;
   }

   public void reset() {
      this.enabled = false;
   }

   public boolean checkReset() {
      boolean flag1 = this.check();
      if (flag1) {
         this.reset();
      }

      return flag1;
   }

   public boolean check() {
      return this.enabled && this.isTime();
   }

   public boolean isTime() {
      return System.currentTimeMillis() >= this.time;
   }

   public boolean checkRepeat() {
      if (!this.enabled) {
         this.mark();
      }

      return this.checkReset();
   }
}
