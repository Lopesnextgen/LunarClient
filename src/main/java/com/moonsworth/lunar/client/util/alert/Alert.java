package com.moonsworth.lunar.client.util.alert;

import lombok.Generated;

public class Alert {
   private static final int MAX_IDLE_MILLIS = 60000;
   private static final int MIN_IDLE_MILLIS = 15000;
   private static final long MAX_IDLE_NANOS = 60000000000L;
   private static final long MIN_IDLE_NANOS = 15000000000L;
   private int residentSize = 0;
   private long lastUseNanos;

   public Alert() {
      this.reset();
   }

   public void reset() {
      this.lastUseNanos = System.nanoTime();
   }

   public boolean isIdle(long var1) {
      return var1 - this.lastUseNanos >= 60000000000L;
   }

   public float getIdleFactor(long var1) {
      long var3 = var1 - this.lastUseNanos;
      if (var3 > 60000000000L) {
         return 1.0F;
      }

      if (var3 < 15000000000L) {
         return 0.0F;
      }

      float var5 = (int)(var3 / 1000000L);
      return var5 / 45000.0F;
   }

   @Generated
   public void setResidentSize(int var1) {
      this.residentSize = var1;
   }

   @Generated
   public int getResidentSize() {
      return this.residentSize;
   }
}
