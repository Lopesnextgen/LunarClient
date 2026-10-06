package com.moonsworth.lunar.client.cosmetics.emote;

import lombok.Generated;
import org.joml.Vector3f;
import com.moonsworth.lunar.client.cosmetics.ClothCloakSolver;

public class PhysicsPoint {
   private final ClothCloakSolver field1;
   private final int field2;
   private final int field3;
   private final Vector3f field4;
   private final Vector3f field5;
   private final Vector3f field6 = new Vector3f(0.0F, 0.0F, 1.0F);
   private boolean locked = false;

   public PhysicsPoint(ClothCloakSolver fov31, Vector3f vector3f2, int value, int value2) {
      this.field1 = fov31;
      this.field4 = new Vector3f(vector3f2);
      this.field5 = new Vector3f(vector3f2);
      this.field2 = value;
      this.field3 = value2;
   }

   public void method1(Vector3f vector3f1) {
      if (!this.locked) {
         this.field4.add(vector3f1);
      }
   }

   public void method2(float value1) {
      if (!this.locked) {
         Vector3f vector3f2 = new Vector3f(this.field4).sub(this.field5).mul(value1);
         Vector3f vector3f3 = new Vector3f(this.field4);
         this.field4.add(vector3f2);
         this.field5.set(vector3f3);
      }
   }

   public void method3(PhysicsPoint fov21, PhysicsPoint fov22, boolean flag) {
      Vector3f vector3f4 = new Vector3f(fov21.method7()).sub(this.field4);
      Vector3f vector3f5 = new Vector3f(fov22.method7()).sub(this.field4);
      Vector3f vector3f6 = vector3f5.cross(vector3f4);
      if (flag) {
         vector3f6.negate();
      }

      if (vector3f6.lengthSquared() < 1.0E-6F) {
         vector3f6.set(0.0F, 0.0F, 1.0F);
      }

      this.field6.set(vector3f6.normalize());
   }

   public void method4(boolean flag) {
      if (!this.locked) {
         this.method5(-0.29F, 0.29F, -1.8F, flag ? 0.11F : 1.8F, -0.3F, -0.016F);
         this.method5(-0.35F, 0.35F, -1.5F, -0.5F, -0.15F, 0.15F);
      }
   }

   private void method5(float value1, float value, float value2, float value3, float value4, float value5) {
      if (this.field4.x >= value1 && this.field4.x <= value && this.field4.y >= value2 && this.field4.y <= value3 && this.field4.z >= value4 && this.field4.z <= value5) {
         float value7 = this.field4.x - value1;
         float value8 = value - this.field4.x;
         float value9 = this.field4.y - value2;
         float value10 = value3 - this.field4.y;
         float value11 = this.field4.z - value4;
         float value12 = value5 - this.field4.z;
         float value13 = value7;
         byte number14 = 0;
         if (value8 < value13) {
            value13 = value8;
            number14 = 1;
         }

         if (value9 < value13) {
            value13 = value9;
            number14 = 2;
         }

         if (value10 < value13) {
            value13 = value10;
            number14 = 3;
         }

         if (value11 < value13) {
            value13 = value11;
            number14 = 4;
         }

         if (value12 < value13) {
            number14 = 5;
         }

         switch (number14) {
            case 0:
               this.field4.x = value1;
               break;
            case 1:
               this.field4.x = value;
               break;
            case 2:
               this.field4.y = value2;
               break;
            case 3:
               this.field4.y = value3;
               break;
            case 4:
               this.field4.z = value4;
               break;
            case 5:
               this.field4.z = value5;
         }
      }
   }

   public void lock() {
      this.locked = true;
   }

   @Generated
   public ClothCloakSolver method6() {
      return this.field1;
   }

   @Generated
   public int getX() {
      return this.field2;
   }

   @Generated
   public int getY() {
      return this.field3;
   }

   @Generated
   public Vector3f method7() {
      return this.field4;
   }

   @Generated
   public Vector3f method8() {
      return this.field5;
   }

   @Generated
   public Vector3f method9() {
      return this.field6;
   }

   @Generated
   public boolean isLocked() {
      return this.locked;
   }
}
