package com.moonsworth.lunar.client.render.particle;

public class Constant implements IValue {
   private double doubleValue;
   private String field1;

   public Constant(double value1) {
      this.doubleValue = value1;
   }

   public Constant(String text1) {
      this.field1 = text1;
   }

   @Override
   public IValue method1() {
      return this;
   }

   @Override
   public boolean isNumber() {
      return this.field1 == null;
   }

   @Override
   public void set(double value1) {
      this.doubleValue = value1;
      this.field1 = null;
   }

   @Override
   public void set(String text1) {
      this.doubleValue = 0.0;
      this.field1 = text1;
   }

   @Override
   public double doubleValue() {
      return this.doubleValue;
   }

   @Override
   public boolean method2() {
      return this.isNumber() ? Operation.isTrue(this.doubleValue) : this.field1.equalsIgnoreCase("true");
   }

   @Override
   public String stringValue() {
      return this.field1;
   }

   @Override
   public String toString() {
      return this.field1 == null ? String.valueOf(this.doubleValue) : "\"" + this.field1 + "\"";
   }
}
