package com.moonsworth.lunar.client.render.particle;

public class Trunc extends NNFunction {
   public Trunc(IValue[] items1, String text) {
      super(items1, text);
   }

   @Override
   public int getRequiredArguments() {
      return 1;
   }

   @Override
   public double doubleValue() {
      double value1 = this.method2(0).doubleValue();
      return value1 < 0.0 ? Math.ceil(value1) : Math.floor(value1);
   }
}
