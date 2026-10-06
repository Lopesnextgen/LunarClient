package com.moonsworth.lunar.client.framework.feature.scrollabletooltips;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.AnimatedValue;
import com.moonsworth.lunar.client.util.math.MathUtils;
import lombok.Generated;

public class Scrollabletooltips {
   private final AnimatedValue field1 = new AnimatedValue(0.0, AnimatedValue.Type.LINEAR);
   private final AnimatedValue field2 = new AnimatedValue(0.0, AnimatedValue.Type.LINEAR);
   private final AnimatedValue field3 = new AnimatedValue(1.0, AnimatedValue.Type.LINEAR);

   public Scrollabletooltips() {
   }

   public void method1(int value, int value2) {
      this.field1.animateTo(value, 0L);
      this.field2.animateTo(value2, 0L);
      this.field3.animateTo(1.0, 0L);
   }

   public void method2(double value1) {
      this.field1.animateTo(this.field1.getValue2() + value1, 100L);
   }

   public void method3(double value1) {
      this.field2.animateTo(this.field2.getValue2() + value1, 100L);
   }

   public void method4(double value1) {
      double value3 = this.field3.getValue2() * value1;
      this.field3.animateTo(value3, 100L);
   }

   public double method5(double value1, double value3, double value) {
      double value7 = this.method6();
      double value9 = value7;
      double value11 = value - value3 - value7;
      return MathUtils.method11(value1, Math.min(value9, value11), Math.max(value9, value11));
   }

   public double method6() {
      return 6.0 * this.field3.getValue();
   }

   @Generated
   public AnimatedValue method7() {
      return this.field1;
   }

   @Generated
   public AnimatedValue method8() {
      return this.field2;
   }

   @Generated
   public AnimatedValue method9() {
      return this.field3;
   }
}
