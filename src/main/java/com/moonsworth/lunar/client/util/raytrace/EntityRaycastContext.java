package com.moonsworth.lunar.client.util.raytrace;

import lombok.Generated;

public class EntityRaycastContext implements RaycastContext {
   private final boolean field1;
   private float field2 = 0.0F;
   private float field3 = 0.0F;

   @Generated
   public boolean method1() {
      return this.field1;
   }

   @Generated
   public float method2() {
      return this.field2;
   }

   @Generated
   public float method3() {
      return this.field3;
   }

   @Generated
   public EntityRaycastContext(boolean flag1) {
      this.field1 = flag1;
   }

   @Generated
   public EntityRaycastContext(boolean flag1, float value, float value2) {
      this.field1 = flag1;
      this.field2 = value;
      this.field3 = value2;
   }
}
