package com.moonsworth.lunar.client.render.particle;

public abstract class NNFunction extends MathFunction {
   public NNFunction(IValue[] items1, String text2) {
      super(items1, text2);
   }

   @Override
   protected void method1(int number1, IValue glintcolorizer2_42) {
      if (!glintcolorizer2_42.isNumber()) {
         throw new IllegalStateException("Function " + this.name + " cannot receive string arguments!");
      }
   }

   @Override
   public IValue method1() {
      this.COCRCHHOCCOCRCCORIIRHOOIHROORR.set(this.doubleValue());
      return this.COCRCHHOCCOCRCCORIIRHOOIHROORR;
   }

   @Override
   public boolean isNumber() {
      return true;
   }

   @Override
   public boolean method2() {
      return Operation.isTrue(this.doubleValue());
   }

   @Override
   public String stringValue() {
      return "";
   }
}
