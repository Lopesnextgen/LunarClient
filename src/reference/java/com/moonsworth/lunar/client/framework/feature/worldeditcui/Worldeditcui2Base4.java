package com.moonsworth.lunar.client.framework.feature.worldeditcui;

import lombok.Generated;
import org.joml.Vector3d;

public final class Worldeditcui2Base4 extends Worldeditcui2Base {
   private Vector3d field4;
   private double field5;
   private double field6;

   public Worldeditcui2Base4 method2(int var1, int value) {
      this.field5 = var1;
      this.field6 = value;
      return this;
   }

   public Worldeditcui2Base4 method3(double var1, double value, double value2) {
      this.field4 = new Vector3d(var1, value, value2);
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
