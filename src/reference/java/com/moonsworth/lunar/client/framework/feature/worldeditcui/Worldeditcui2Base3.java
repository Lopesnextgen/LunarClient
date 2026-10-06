package com.moonsworth.lunar.client.framework.feature.worldeditcui;

public abstract class Worldeditcui2Base3 extends Worldeditcui2Base implements Worldeditcui_2 {
   private long field4;

   public Worldeditcui2Base3 method2(long value) {
      this.field4 = value;
      return this;
   }

   @Override
   public long method4() {
      return this.field4;
   }
}
