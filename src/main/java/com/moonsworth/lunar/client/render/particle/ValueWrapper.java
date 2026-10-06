package com.moonsworth.lunar.client.render.particle;

public abstract class ValueWrapper implements IValue {
   public IValue field1;
   protected IValue field2 = new Constant(0.0);

   public ValueWrapper(IValue glintcolorizer2_41) {
      this.field1 = glintcolorizer2_41;
   }

   @Override
   public IValue method1() {
      this.method3();
      return this.field2;
   }

   protected abstract void method3();

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
}
