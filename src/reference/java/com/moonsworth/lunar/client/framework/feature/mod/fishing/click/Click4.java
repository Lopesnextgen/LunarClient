package com.moonsworth.lunar.client.framework.feature.mod.fishing.click;

import com.moonsworth.lunar.client.cosmetics.emote.Direction2D;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;

public class Click4 {
   private final Click4.Type type;
   private double value;
   private long endTime = 0L;
   private double startValue = 0.0;
   private long duration = 0L;
   private boolean enabled = true;

   public Click4(double var1, Click4.Type var3) {
      this.value = var1;
      this.type = var3;
   }

   public double getValue() {
      long var1 = ThreadModuleDump63.method3().bridge$getSystemTime();
      if (this.enabled && var1 < this.endTime) {
         double var3 = 1.0 - ((double)this.endTime - var1) / this.duration;

         return this.startValue + switch (this.type) {
            case LINEAR -> {
            }
            case SIN_OUT -> Math.sin((Math.PI / 2) * var3);
            case SIN_IN -> Math.sin((Math.PI / 2) * var3 - (Math.PI / 2)) + 1.0;
            case SIN_IN_OUT -> (Math.sin(Math.PI * var3 - (Math.PI / 2)) + 1.0) / 2.0;
            case EASE_IN -> Math.pow(var3, 2.0);
            default -> throw new IllegalStateException();
         } * (this.value - this.startValue);
      } else {
         return this.value;
      }
   }

   public float getFloatValue() {
      return (float)this.getValue();
   }

   public void animateTo(double var1, long var3) {
      if (this.value != var1) {
         this.animate(this.getValue(), var1, var3);
      }
   }

   public void animate(double var1, double var3, long var5) {
      this.startValue = var1;
      this.duration = var5;
      this.endTime = ThreadModuleDump63.method3().bridge$getSystemTime() + var5;
      this.value = var3;
   }

   public boolean isAnimating() {
      long var1 = ThreadModuleDump63.method3().bridge$getSystemTime();
      return var1 < this.endTime;
   }

   public Direction2D getDirection() {
      return this.value > this.startValue ? Direction2D.UP : Direction2D.DOWN;
   }

   public double getValue2() {
      return this.value;
   }

   @Generated
   public void setEnabled(boolean var1) {
      this.enabled = var1;
   }

   public enum Type {
      SIN_IN_OUT,
      SIN_IN,
      SIN_OUT,
      EASE_IN,
      LINEAR;
   }
}
