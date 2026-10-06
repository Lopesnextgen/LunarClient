package com.moonsworth.lunar.client.render.particle;

public class Cos extends NNFunction {
   public Cos(IValue[] items1, String text) {
      super(items1, text);
   }

   @Override
   public int getRequiredArguments() {
      return 1;
   }

   @Override
   public double doubleValue() {
      return Math.cos(this.method2(0).doubleValue());
   }
}
