package com.moonsworth.lunar.client.render.particle;

public class Negate extends ValueWrapper {
   public Negate(IValue glintcolorizer2_41) {
      super(glintcolorizer2_41);
   }

   @Override
   protected void method3() {
      this.COCRCHHOCCOCRCCORIIRHOOIHROORR.set(this.doubleValue());
   }

   @Override
   public double doubleValue() {
      return this.method2() ? 1.0 : 0.0;
   }

   @Override
   public boolean method2() {
      return !this.ROCCCIIHORIRIROCHRIHOCOCCHIICR.method2();
   }

   @Override
   public String toString() {
      return "!" + this.ROCCCIIHORIRIROCHRIHOCOCCHIICR.toString();
   }
}
