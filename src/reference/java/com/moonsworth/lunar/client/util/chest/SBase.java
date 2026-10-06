package com.moonsworth.lunar.client.util.chest;

import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats_3;
import java.util.function.Supplier;
import lombok.Generated;

public abstract class SBase<T, O, I, H extends Horsestats_3<H, ? extends H>, S extends com.moonsworth.lunar.client.util.chest.mixin.Chest>
   implements SExtension<T, I, H, S> {
   private final SImpl<T, O, I, H, S> config;
   private final Supplier<Chest> segmentSupplier;
   private Vec3Bridge start;
   private Vec3Bridge end;

   @Override
   public void refresh() {
      Chest var1 = this.segmentSupplier.get();
      this.start = var1.getStart();
      this.end = var1.getEnd();
   }

   @Generated
   @Override
   public SImpl<T, O, I, H, S> getConfig() {
      return this.config;
   }

   @Generated
   public Supplier<Chest> getSegmentSupplier() {
      return this.segmentSupplier;
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
   public SBase(SImpl<T, O, I, H, S> var1, Supplier<Chest> supplier) {
      this.config = var1;
      this.segmentSupplier = supplier;
   }
}
