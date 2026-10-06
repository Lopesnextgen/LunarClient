package com.moonsworth.lunar.client.util.chest;

import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats_3;
import lombok.Generated;

public abstract class SBase2<T, O, I, H extends Horsestats_3<H, ? extends H>, S extends com.moonsworth.lunar.client.util.chest.mixin.Chest>
   implements SExtension<T, I, H, S> {
   private final SImpl<T, O, I, H, S> config;
   private final Vec3Bridge start;
   private final Vec3Bridge end;

   @Generated
   @Override
   public SImpl<T, O, I, H, S> getConfig() {
      return this.config;
   }

   @Generated
   @Override
   public Vec3Bridge getStart() {
      return this.start;
   }

   @Generated
   @Override
   public Vec3Bridge getEnd() {
      return this.end;
   }

   @Generated
   public SBase2(SImpl<T, O, I, H, S> sImpl, Vec3Bridge vec3Bridge, Vec3Bridge vec3Bridge2) {
      this.config = sImpl;
      this.start = vec3Bridge;
      this.end = vec3Bridge2;
   }
}
