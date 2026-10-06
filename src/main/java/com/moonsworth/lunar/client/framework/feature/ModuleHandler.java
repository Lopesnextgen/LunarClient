package com.moonsworth.lunar.client.framework.feature;

import lombok.Generated;

public class ModuleHandler implements Module {
   private final float field1;
   private final float field2;
   private final float field3;
   private final float field4;
   private final float field5;

   public ModuleHandler(float var1, float var2, float var3, float var4) {
      this(var1, var2, var3, var4, 1.0F);
   }

   public ModuleHandler(float var1, float var2, float var3, float var4, float value) {
      this.field4 = var1;
      this.field5 = var2;
      this.field1 = var3;
      this.field2 = var4;
      this.field3 = value;
   }

   @Generated
   @Override
   public float getWidth() {
      return this.field1;
   }

   @Generated
   @Override
   public float getHeight() {
      return this.field2;
   }

   @Generated
   @Override
   public float getScale() {
      return this.field3;
   }

   @Generated
   @Override
   public float method1() {
      return this.field4;
   }

   @Generated
   @Override
   public float method2() {
      return this.field5;
   }
}
