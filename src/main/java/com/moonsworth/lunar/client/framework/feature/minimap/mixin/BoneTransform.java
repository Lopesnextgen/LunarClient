package com.moonsworth.lunar.client.framework.feature.minimap.mixin;

import org.joml.Vector3f;

public class BoneTransform {
   private final Vector3f field1;
   private final float field2;
   private final boolean field3;

   public BoneTransform(Vector3f vector3f1, float value2) {
      this(vector3f1, value2, false);
   }

   public BoneTransform(Vector3f vector3f1, float value2, boolean flag) {
      this.field1 = vector3f1;
      this.field2 = value2;
      this.field3 = flag;
   }

   public Vector3f method1() {
      return this.field1;
   }

   public float scale() {
      return this.field2;
   }

   public boolean method2() {
      return this.field3;
   }
}
