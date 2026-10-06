package com.moonsworth.lunar.network.mixin;

public class MixinHelper8 {
   private String name = "";
   private String value = "";

   public MixinHelper8(String var1, String text) {
      this.setName(var1);
      this.setValue(text);
   }

   private void setName(String var1) {
      if (this.method1(var1)) {
         this.name = var1;
      }
   }

   private void setValue(String var1) {
      if (this.method1(var1)) {
         this.value = var1;
      }
   }

   public String getName() {
      return this.name;
   }

   public String getValue() {
      return this.value;
   }

   private boolean method1(String var1) {
      return var1 != null;
   }
}
