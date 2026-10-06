package com.moonsworth.lunar.client.util.raytrace;

import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats_3;
import java.util.function.Supplier;
import lombok.Generated;

public abstract class DynamicRay<T, O, I, H extends Horsestats_3<H, ? extends H>, S extends com.moonsworth.lunar.client.util.raytrace.RaycastContext>
   implements Ray<T, I, H, S> {
   private final Raycaster<T, O, I, H, S> field1;
   private final Supplier<RaySegment> field2;
   private Vec3Bridge field3;
   private Vec3Bridge field4;

   @Override
   public void method1() {
      RaySegment chest1 = this.field2.get();
      this.field3 = chest1.method1();
      this.field4 = chest1.method2();
   }

   @Generated
   @Override
   public Raycaster<T, O, I, H, S> method2() {
      return this.field1;
   }

   @Generated
   public Supplier<RaySegment> method8() {
      return this.field2;
   }

   @Generated
   @Override
   public Vec3Bridge method4() {
      return this.field3;
   }

   @Generated
   @Override
   public Vec3Bridge method5() {
      return this.field4;
   }

   @Generated
   public DynamicRay(Raycaster<T, O, I, H, S> raycaster, Supplier<RaySegment> supplier2) {
      this.field1 = raycaster;
      this.field2 = supplier2;
   }
}
