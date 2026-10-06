package com.moonsworth.lunar.client.render.particle;

import com.moonsworth.lunar.client.render.particle.NNFunction;
import com.moonsworth.lunar.client.render.particle.IValue;

public class HermiteBlend extends NNFunction {
   public HermiteBlend(IValue[] items1, String text) {
      super(items1, text);
   }

   @Override
   public int getRequiredArguments() {
      return 1;
   }

   @Override
   public double doubleValue() {
      double value1 = this.method2(0).doubleValue();
      return 3.0 * value1 * value1 - 2.0 * value1 * value1 * value1;
   }
}
