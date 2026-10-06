package com.moonsworth.lunar.client.render.particle;

import com.moonsworth.lunar.client.render.particle.NNFunction;
import com.moonsworth.lunar.client.render.particle.IValue;
import com.moonsworth.lunar.client.render.particle.Interpolations;

public class Lerp extends NNFunction {
   public Lerp(IValue[] items1, String text) {
      super(items1, text);
   }

   @Override
   public int getRequiredArguments() {
      return 3;
   }

   @Override
   public double doubleValue() {
      return Interpolations.lerp(
         this.method2(0).doubleValue(),
         this.method2(1).doubleValue(),
         this.method2(2).doubleValue()
      );
   }
}
