package com.moonsworth.lunar.client.util.raytrace;

import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import lombok.Generated;

public class RaySegment {
   private final Vec3Bridge field1;
   private final Vec3Bridge field2;

   @Generated
   public Vec3Bridge method1() {
      return this.field1;
   }

   @Generated
   public Vec3Bridge method2() {
      return this.field2;
   }

   @Generated
   private RaySegment(Vec3Bridge horsestats151, Vec3Bridge horsestats152) {
      this.field1 = horsestats151;
      this.field2 = horsestats152;
   }

   @Generated
   public static RaySegment method3(Vec3Bridge horsestats150, Vec3Bridge horsestats151) {
      return new RaySegment(horsestats150, horsestats151);
   }
}
