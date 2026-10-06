package com.moonsworth.lunar.client.framework.feature.worldeditcui;

public abstract class Worldeditcui2Base2 extends Worldeditcui2Handler implements Worldeditcui_2 {
   private long field2;

   public Worldeditcui2Base2 method2(long value) {
      this.field2 = value;
      return this;
   }

   @Override
   public long method4() {
      return this.field2;
   }
}
