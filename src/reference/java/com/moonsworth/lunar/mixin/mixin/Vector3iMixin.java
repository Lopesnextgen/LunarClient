package com.moonsworth.lunar.mixin.mixin;

import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge.Extension;
import org.joml.Vector3i;
import org.joml.Vector3ic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Vector3i.class)
public abstract class Vector3iMixin implements Horsestats20Extension2, Extension, Vector3ic {
   public Vector3iMixin() {
   }

   @Shadow
   public abstract Vector3i add(Vector3ic vector3ic1);

   @Shadow
   public abstract Vector3i set(int index1, int index2, int index3);

   public int bridge$getX() {
      return this.x();
   }

   public int bridge$getY() {
      return this.y();
   }

   public int bridge$getZ() {
      return this.z();
   }

   public void bridge$setPos(int index1, int index2, int index3) {
      this.set(index1, index2, index3);
   }

   public Horsestats20Extension2 bridge$add(Vector3ic vector3ic1) {
      return (Horsestats20Extension2)this.add(vector3ic1);
   }

   public Horsestats20Extension2 bridge$below() {
      return super.bridge$below();
   }

   public Horsestats20Extension2 bridge$above() {
      return super.bridge$above();
   }

   public long bridge$asLong() {
      return this.hashCode();
   }

   public Vector3i bridge$toJoml() {
      return (Vector3i)this;
   }
}
