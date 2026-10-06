package com.moonsworth.lunar.client.framework.feature.worldeditcui;

import lombok.Generated;
import org.joml.Vector3d;

public final class EllipsoidSelection extends WorldEditSelectionBase {
   private Vector3d field2;
   private Vector3d field3;

   public EllipsoidSelection() {
   }

   public EllipsoidSelection method1(Vector3d vector3d1) {
      this.field2 = vector3d1;
      return this;
   }

   public EllipsoidSelection method2(Vector3d vector3d1) {
      this.field3 = vector3d1;
      return this;
   }

   @Generated
   public Vector3d method3() {
      return this.field2;
   }

   @Generated
   public Vector3d method4() {
      return this.field3;
   }
}
