package com.moonsworth.lunar.client.render.particle;

public class Sqrt extends NNFunction {
   public Sqrt(IValue[] items1, String text) {
      super(items1, text);
   }

   @Override
   public int getRequiredArguments() {
      return 1;
   }

   @Override
   public double doubleValue() {
      return Math.sqrt(this.method2(0).doubleValue());
   }
}
