package com.moonsworth.lunar.client.framework.feature.mod.fishing.click;

import com.moonsworth.lunar.client.util.math.Direction2D;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;

public class AnimatedValue {
   private final AnimatedValue.Type type;
   private double value;
   private long endTime = 0L;
   private double startValue = 0.0;
   private long duration = 0L;
   private boolean enabled = true;

   public AnimatedValue(double value1, AnimatedValue.Type type3) {
      this.value = value1;
      this.type = type3;
   }

   public double getValue() {
      long number1 = Ref.method3().bridge$getSystemTime();
      if (this.enabled && number1 < this.endTime) {
         double value3 = 1.0 - ((double)this.endTime - number1) / this.duration;

         return this.startValue + switch (this.type) {
            case LINEAR -> {
            }
            case SIN_OUT -> Math.sin((Math.PI / 2) * value3);
            case SIN_IN -> Math.sin((Math.PI / 2) * value3 - (Math.PI / 2)) + 1.0;
            case SIN_IN_OUT -> (Math.sin(Math.PI * value3 - (Math.PI / 2)) + 1.0) / 2.0;
            case EASE_IN -> Math.pow(value3, 2.0);
            default -> throw new IllegalStateException();
         } * (this.value - this.startValue);
      } else {
         return this.value;
      }
   }

   public float getFloatValue() {
      return (float)this.getValue();
   }

   public void animateTo(double value1, long number3) {
      if (this.value != value1) {
         this.animate(this.getValue(), value1, number3);
      }
   }

   public void animate(double value1, double value3, long number5) {
      this.startValue = value1;
      this.duration = number5;
      this.endTime = Ref.method3().bridge$getSystemTime() + number5;
      this.value = value3;
   }

   public boolean isAnimating() {
      long number1 = Ref.method3().bridge$getSystemTime();
      return number1 < this.endTime;
   }

   public Direction2D getDirection() {
      return this.value > this.startValue ? Direction2D.UP : Direction2D.DOWN;
   }

   public double getValue2() {
      return this.value;
   }

   @Generated
   public void setEnabled(boolean flag1) {
      this.enabled = flag1;
   }

   public enum Type {
      SIN_IN_OUT,
      SIN_IN,
      SIN_OUT,
      EASE_IN,
      LINEAR;

      Type() {
      }
   }
}
