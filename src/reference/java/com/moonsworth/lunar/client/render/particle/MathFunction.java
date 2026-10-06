package com.moonsworth.lunar.client.render.particle;

public abstract class MathFunction implements IValue {
   protected IValue[] field1;
   protected String name;
   protected IValue field2 = new Constant(0.0);

   public MathFunction(IValue[] items1, String text2) {
      if (items1.length < this.getRequiredArguments()) {
         String text4 = String.format("Function '%s' requires at least %s arguments. %s are given!", this.getName(), this.getRequiredArguments(), items1.length);
         throw new Exception(text4);
      }

      for (int index3 = 0; index3 < items1.length; index3++) {
         this.method1(index3, items1[index3]);
      }

      this.field1 = items1;
      this.name = text2;
   }

   protected void method1(int number1, IValue glintcolorizer2_42) {
   }

   @Override
   public void set(double value1) {
   }

   @Override
   public void set(String text1) {
   }

   public IValue method2(int index1) {
      if (index1 >= 0 && index1 < this.field1.length) {
         return this.field1[index1].method1();
      } else {
         throw new IllegalStateException("Index should be within the argument's length range! Given " + index1 + ", arguments length: " + this.field1.length);
      }
   }

   @Override
   public String toString() {
      String text1 = "";

      for (int index2 = 0; index2 < this.field1.length; index2++) {
         text1 = text1 + this.field1[index2].toString();
         if (index2 < this.field1.length - 1) {
            text1 = text1 + ", ";
         }
      }

      return this.getName() + "(" + text1 + ")";
   }

   public String getName() {
      return this.name;
   }

   public int getRequiredArguments() {
      return 0;
   }
}
