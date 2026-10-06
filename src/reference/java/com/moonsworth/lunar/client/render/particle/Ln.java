package com.moonsworth.lunar.client.render.particle;

public class Ln extends NNFunction {
   public Ln(IValue[] items1, String text) {
      super(items1, text);
   }

   @Override
   public int getRequiredArguments() {
      return 1;
   }

   @Override
   public double doubleValue() {
      return Math.log(this.method2(0).doubleValue());
   }
}
