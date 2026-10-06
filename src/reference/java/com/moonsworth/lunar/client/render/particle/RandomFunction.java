package com.moonsworth.lunar.client.render.particle;

import com.moonsworth.lunar.client.render.particle.NNFunction;
import com.moonsworth.lunar.client.render.particle.IValue;
import java.util.Random;

public class RandomFunction extends NNFunction {
   public RandomFunction random = new RandomFunction();

   public RandomFunction(IValue[] items1, String text2) {
      super(items1, text2);
   }

   @Override
   public double doubleValue() {
      double value1;
      if (this.CRHCCIOCHIHRRIOHHCCHCCIIHRCIRC.length >= 3) {
         this.random.setSeed((long)this.method2(2).doubleValue());
         value1 = this.random.nextDouble();
      } else {
         value1 = Math.random();
      }

      if (this.CRHCCIOCHIHRRIOHHCCHCCIIHRCIRC.length >= 2) {
         double value3 = this.method2(0).doubleValue();
         double value5 = this.method2(1).doubleValue();
         double value7 = Math.min(value3, value5);
         double value9 = Math.max(value3, value5);
         value1 = value1 * (value9 - value7) + value7;
      } else if (this.CRHCCIOCHIHRRIOHHCCHCCIIHRCIRC.length >= 1) {
         value1 *= this.method2(0).doubleValue();
      }

      return value1;
   }
}
