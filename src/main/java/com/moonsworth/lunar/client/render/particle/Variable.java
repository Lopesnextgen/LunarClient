package com.moonsworth.lunar.client.render.particle;

public class Variable extends Constant {
   private String name;

   public Variable(String text1, double value) {
      super(value);
      this.name = text1;
   }

   public Variable(String text1, String text) {
      super(text);
      this.name = text1;
   }

   public String getName() {
      return this.name;
   }

   @Override
   public String toString() {
      return this.name;
   }
}
