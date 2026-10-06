package com.moonsworth.lunar.bridge.particle;

public class SimpleParticleData implements ParticleData {
   private final String field1;
   private final int field2;

   public SimpleParticleData(String text, int value) {
      this.field1 = text;
      this.field2 = value;
   }

   @Override
   public String getName() {
      return this.field1;
   }

   @Override
   public int getId() {
      return this.field2;
   }

   public String method1() {
      return this.field1;
   }

   public int method2() {
      return this.field2;
   }
}
