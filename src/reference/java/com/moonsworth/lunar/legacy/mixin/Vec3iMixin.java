package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.client.framework.Ref;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3i;
import org.joml.Vector3i;
import org.joml.Vector3ic;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Vec3i.class)
public abstract class Vec3iMixin implements Vec3iBridge {
   @Final
   @Shadow
   public int chunkPosX$v1_7;
   @Final
   @Shadow
   public int chunkPosY$v1_7;
   @Final
   @Shadow
   public int chunkPosZ$v1_7;

   public Vec3iMixin() {
   }

   @Shadow
   public abstract int getX();

   @Shadow
   public abstract int getY();

   @Shadow
   public abstract int getZ();

   public Vec3iBridge bridge$add(Vector3ic vector3ic1) {
      return Ref.MC_VERSION >= 1
         ? (Vec3iBridge)(new BlockPos(this.bridge$getX() + vector3ic1.x(), this.bridge$getY() + vector3ic1.y(), this.bridge$getZ() + vector3ic1.z()))
         : (Vec3iBridge)(new Vector3i(this.bridge$getX() + vector3ic1.x(), this.bridge$getY() + vector3ic1.y(), this.bridge$getZ() + vector3ic1.z()));
   }

   public int bridge$getX() {
      return Ref.MC_VERSION >= 1 ? this.getX() : this.chunkPosX$v1_7;
   }

   public int bridge$getY() {
      return Ref.MC_VERSION >= 1 ? this.getY() : this.chunkPosY$v1_7;
   }

   public int bridge$getZ() {
      return Ref.MC_VERSION >= 1 ? this.getZ() : this.chunkPosZ$v1_7;
   }
}
