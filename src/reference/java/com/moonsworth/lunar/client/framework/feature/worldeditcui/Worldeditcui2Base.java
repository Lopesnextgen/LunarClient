package com.moonsworth.lunar.client.framework.feature.worldeditcui;

public abstract class Worldeditcui2Base extends Worldeditcui2Handler {
   private int field2;
   private int field3;

   public Worldeditcui2Base method1(int value, int value2) {
      this.field2 = value;
      this.field3 = value2;
      return this;
   }

   public int method2() {
      return this.field3;
   }

   public int method3() {
      return this.field2;
   }
}
