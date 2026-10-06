package com.moonsworth.lunar.client.framework.feature.worldeditcui;

import lombok.Generated;
import org.joml.Vector3d;

public final class Worldeditcui2Impl extends Worldeditcui2Handler {
   private Vector3d field2;
   private Vector3d field3;

   public Worldeditcui2Impl method1(Vector3d var1) {
      this.field2 = var1;
      return this;
   }

   public Worldeditcui2Impl method2(Vector3d var1) {
      this.field3 = var1;
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
