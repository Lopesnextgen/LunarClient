package com.moonsworth.lunar.client.render.particle;

import com.moonsworth.lunar.client.render.particle.NNFunction;
import com.moonsworth.lunar.client.render.particle.IValue;

public class SinDegrees extends NNFunction {
   public SinDegrees(IValue[] items1, String text) {
      super(items1, text);
   }

   @Override
   public int getRequiredArguments() {
      return 1;
   }

   @Override
   public double doubleValue() {
      return Math.sin(this.method2(0).doubleValue() / 180.0 * Math.PI);
   }
}
