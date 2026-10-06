package com.moonsworth.lunar.client.render.particle;

public class Operator implements IValue {
   public static boolean DEBUG = false;
   public Operation field1;
   public IValue field2;
   public IValue field3;
   private IValue field4 = new Constant(0.0);

   public Operator(Operation operation, IValue glintcolorizer2_42, IValue glintcolorizer2_43) {
      this.field1 = operation;
      this.field2 = glintcolorizer2_42;
      this.field3 = glintcolorizer2_43;
   }

   @Override
   public IValue method1() {
      if (!this.isNumber() && this.field1 == Operation.ADD) {
         this.field4.set(this.stringValue());
      } else {
         this.field4.set(this.doubleValue());
      }

      return this.field4;
   }

   @Override
   public boolean isNumber() {
      return this.field2.isNumber() || this.field3.isNumber();
   }

   @Override
   public void set(double value) {
   }

   @Override
   public void set(String text) {
   }

   @Override
   public double doubleValue() {
      if (!this.isNumber() && this.field1 == Operation.EQUALS) {
         return this.field2.stringValue().equals(this.field3.stringValue()) ? 1.0 : 0.0;
      } else {
         return this.field1.calculate(this.field2.doubleValue(), this.field3.doubleValue());
      }
   }

   @Override
   public boolean method2() {
      return Operation.isTrue(this.doubleValue());
   }

   @Override
   public String stringValue() {
      return this.field1 == Operation.ADD ? this.field2.stringValue() + this.field3.stringValue() : this.field2.stringValue();
   }

   @Override
   public String toString() {
      return DEBUG
         ? "(" + this.field2.toString() + " " + this.field1.sign + " " + this.field3.toString() + ")"
         : this.field2.toString() + " " + this.field1.sign + " " + this.field3.toString();
   }
}
