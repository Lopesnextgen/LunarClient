package com.moonsworth.lunar.client.framework.feature.minimap.mixin;

import org.joml.Vector3f;

public class Minimap {
   private final Vector3f field1;
   private final float field2;
   private final boolean field3;

   public Minimap(Vector3f var1, float var2) {
      this(var1, var2, false);
   }

   public Minimap(Vector3f var1, float var2, boolean flag) {
      this.field1 = var1;
      this.field2 = var2;
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
