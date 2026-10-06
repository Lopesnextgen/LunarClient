package com.moonsworth.lunar.client.render.particle;

public class Pow extends NNFunction {
   public Pow(IValue[] items1, String text) {
      super(items1, text);
   }

   @Override
   public int getRequiredArguments() {
      return 2;
   }

   @Override
   public double doubleValue() {
      return Math.pow(this.method2(0).doubleValue(), this.method2(1).doubleValue());
   }
}
