package com.moonsworth.lunar.client.render.particle;

public class Negative extends ValueWrapper {
   public Negative(IValue glintcolorizer2_41) {
      super(glintcolorizer2_41);
   }

   @Override
   protected void method3() {
      this.COCRCHHOCCOCRCCORIIRHOOIHROORR.set(this.doubleValue());
   }

   @Override
   public double doubleValue() {
      return -this.ROCCCIIHORIRIROCHRIHOCOCCHIICR.doubleValue();
   }

   @Override
   public boolean method2() {
      return Operation.isTrue(this.doubleValue());
   }

   @Override
   public String toString() {
      return "-" + this.ROCCCIIHORIRIROCHRIHOCOCCHIICR.toString();
   }
}
