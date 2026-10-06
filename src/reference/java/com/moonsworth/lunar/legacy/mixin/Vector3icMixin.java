package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import org.joml.Vector3i;
import org.joml.Vector3ic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Vector3ic.class)
public interface Vector3icMixin extends Horsestats20Extension, Horsestats20Extension2, Vec3iBridge, Vector3ic {
   @Shadow
   Vector3i add(Vector3ic vector3ic1, Vector3i vector3i2);

   @Shadow
   int x();

   @Shadow
   int y();

   @Shadow
   int z();

   default int bridge$getX() {
      return this.x();
   }

   default int bridge$getY() {
      return this.y();
   }

   default int bridge$getZ() {
      return this.z();
   }

   default Horsestats20Extension2 bridge$add(Vector3ic vector3ic1) {
      return (Horsestats20Extension2)(new Vector3i(this.x() + vector3ic1.x(), this.y() + vector3ic1.y(), this.z() + vector3ic1.z()));
   }

   default long bridge$asLong() {
      return this.hashCode();
   }
}
