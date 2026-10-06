package com.moonsworth.lunar.client.cosmetics.emote;

import org.joml.Vector3f;

public class DistanceConstraint {
   private final PhysicsPoint field1;
   private final PhysicsPoint field2;
   private final float field3;

   public DistanceConstraint(PhysicsPoint fov21, PhysicsPoint fov22, float value) {
      this.field1 = fov21;
      this.field2 = fov22;
      this.field3 = value;
   }

   public void method1() {
      Vector3f vector3f1 = this.field1.method7();
      Vector3f vector3f2 = this.field2.method7();
      Vector3f vector3f3 = new Vector3f(vector3f2).sub(vector3f1);
      float value4 = vector3f3.length();
      if (value4 != 0.0F) {
         float value5 = (value4 - this.field3) / value4;
         float value6 = 1.0F;
         Vector3f vector3f7 = new Vector3f(vector3f3).mul(0.5F * value5 * value6);
         if (!this.field1.isLocked()) {
            vector3f1.add(vector3f7);
         }

         if (!this.field2.isLocked()) {
            vector3f2.sub(vector3f7);
         }
      }
   }
}
