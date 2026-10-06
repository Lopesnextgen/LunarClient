package com.moonsworth.lunar.client.util.raytrace;

import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats_3;
import lombok.Generated;

public abstract class FixedRay<T, O, I, H extends Horsestats_3<H, ? extends H>, S extends com.moonsworth.lunar.client.util.raytrace.RaycastContext>
   implements Ray<T, I, H, S> {
   private final Raycaster<T, O, I, H, S> field1;
   private final Vec3Bridge field2;
   private final Vec3Bridge field3;

   @Generated
   @Override
   public Raycaster<T, O, I, H, S> method2() {
      return this.field1;
   }

   @Generated
   @Override
   public Vec3Bridge method4() {
      return this.field2;
   }

   @Generated
   @Override
   public Vec3Bridge method5() {
      return this.field3;
   }

   @Generated
   public FixedRay(Raycaster<T, O, I, H, S> raycaster, Vec3Bridge horsestats152, Vec3Bridge horsestats153) {
      this.field1 = raycaster;
      this.field2 = horsestats152;
      this.field3 = horsestats153;
   }
}
