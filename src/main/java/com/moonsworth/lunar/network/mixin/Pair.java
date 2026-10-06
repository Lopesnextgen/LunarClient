package com.moonsworth.lunar.network.mixin;

public class Pair {
   private String name = "";
   private String value = "";

   public Pair(String text1, String text) {
      this.setName(text1);
      this.setValue(text);
   }

   private void setName(String text1) {
      if (this.method1(text1)) {
         this.name = text1;
      }
   }

   private void setValue(String text1) {
      if (this.method1(text1)) {
         this.value = text1;
      }
   }

   public String getName() {
      return this.name;
   }

   public String getValue() {
      return this.value;
   }

   private boolean method1(String text1) {
      return text1 != null;
   }
}
