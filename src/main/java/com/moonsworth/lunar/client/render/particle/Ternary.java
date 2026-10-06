package com.moonsworth.lunar.client.render.particle;

public class Ternary implements IValue {
   public IValue field1;
   public IValue field2;
   public IValue field3;
   private IValue field4 = new Constant(0.0);

   public Ternary(IValue glintcolorizer2_41, IValue glintcolorizer2_42, IValue glintcolorizer2_43) {
      this.field1 = glintcolorizer2_41;
      this.field2 = glintcolorizer2_42;
      this.field3 = glintcolorizer2_43;
   }

   @Override
   public IValue method1() {
      if (this.isNumber()) {
         this.field4.set(this.doubleValue());
      } else {
         this.field4.set(this.stringValue());
      }

      return this.field4;
   }

   @Override
   public boolean isNumber() {
      return this.field3.isNumber() || this.field2.isNumber();
   }

   @Override
   public void set(double value) {
   }

   @Override
   public void set(String text) {
   }

   @Override
   public double doubleValue() {
      return Operation.isTrue(this.field1.doubleValue()) ? this.field2.doubleValue() : this.field3.doubleValue();
   }

   @Override
   public boolean method2() {
      return Operation.isTrue(this.doubleValue());
   }

   @Override
   public String stringValue() {
      return Operation.isTrue(this.field1.doubleValue()) ? this.field2.stringValue() : this.field3.stringValue();
   }

   @Override
   public String toString() {
      return this.field1.toString() + " ? " + this.field2.toString() + " : " + this.field3.toString();
   }
}
