package com.moonsworth.lunar.client.render.particle;

public class Round extends NNFunction {
   public Round(IValue[] items1, String text) {
      super(items1, text);
   }

   @Override
   public int getRequiredArguments() {
      return 1;
   }

   @Override
   public double doubleValue() {
      return Math.round(this.method2(0).doubleValue());
   }
}
