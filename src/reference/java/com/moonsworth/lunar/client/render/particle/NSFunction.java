package com.moonsworth.lunar.client.render.particle;

public abstract class NSFunction extends MathFunction {
   public NSFunction(IValue[] items1, String text2) {
      super(items1, text2);

      for (IValue glintcolorizer2_46 : items1) {
         if (!glintcolorizer2_46.isNumber()) {
            throw new IllegalStateException("Function " + text2 + " cannot receive string arguments!");
         }
      }
   }

   @Override
   protected void method1(int number1, IValue glintcolorizer2_42) {
      if (!glintcolorizer2_42.isNumber()) {
         throw new IllegalStateException("Function " + this.name + " cannot receive string arguments!");
      }
   }

   @Override
   public IValue method1() {
      this.COCRCHHOCCOCRCCORIIRHOOIHROORR.set(this.stringValue());
      return this.COCRCHHOCCOCRCCORIIRHOOIHROORR;
   }

   @Override
   public boolean isNumber() {
      return false;
   }

   @Override
   public double doubleValue() {
      return 0.0;
   }

   @Override
   public boolean method2() {
      return this.stringValue().equalsIgnoreCase("true");
   }
}
