package com.moonsworth.lunar.client.framework.feature.worldeditcui;

import lombok.Generated;
import org.joml.Vector3d;

public final class CylinderSelection extends BoundedSelection {
   private Vector3d field4;
   private double field5;
   private double field6;

   public CylinderSelection() {
   }

   public CylinderSelection method2(int value, int value2) {
      this.field5 = value;
      this.field6 = value2;
      return this;
   }

   public CylinderSelection method3(double value, double value2, double value3) {
      this.field4 = new Vector3d(value, value2, value3);
      return this;
   }

   @Generated
   public Vector3d method4() {
      return this.field4;
   }

   @Generated
   public double method5() {
      return this.field5;
   }

   @Generated
   public double method6() {
      return this.field6;
   }
}
