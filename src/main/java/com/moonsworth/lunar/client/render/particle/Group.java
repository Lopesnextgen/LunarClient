package com.moonsworth.lunar.client.render.particle;

public class Group implements IValue {
   private IValue field1;

   public Group(IValue glintcolorizer2_41) {
      this.field1 = glintcolorizer2_41;
   }

   @Override
   public IValue method1() {
      return this.field1.method1();
   }

   @Override
   public boolean isNumber() {
      return this.field1.isNumber();
   }

   @Override
   public void set(double value) {
      this.field1.set(value);
   }

   @Override
   public void set(String text) {
      this.field1.set(text);
   }

   @Override
   public double doubleValue() {
      return this.field1.doubleValue();
   }

   @Override
   public boolean method2() {
      return this.field1.method2();
   }

   @Override
   public String stringValue() {
      return this.field1.stringValue();
   }

   @Override
   public String toString() {
      return "(" + this.field1.toString() + ")";
   }
}
