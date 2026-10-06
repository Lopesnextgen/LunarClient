package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import lombok.Generated;

public class MixinHelper {
   private final ResourceLocationBridge field1;
   private final double field2;
   private final double field3;
   private final double field4;

   @Generated
   public ResourceLocationBridge method1() {
      return this.field1;
   }

   @Generated
   public double method2() {
      return this.field2;
   }

   @Generated
   public double method3() {
      return this.field3;
   }

   @Generated
   public double method4() {
      return this.field4;
   }

   @Generated
   public MixinHelper(ResourceLocationBridge resourceLocationBridge, double value, double value2, double value3) {
      this.field1 = resourceLocationBridge;
      this.field2 = value;
      this.field3 = value2;
      this.field4 = value3;
   }
}
