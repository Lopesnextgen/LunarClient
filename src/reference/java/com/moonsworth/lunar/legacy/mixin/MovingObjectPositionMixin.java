package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.bridge.horsestats.MovingObjectPositionHitResult;
import com.moonsworth.lunar.bridge.horsestats.MovingObjectHitType;
import com.moonsworth.lunar.bridge.horsestats.HorsestatsType_2;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.util.math.RayTraceResult.MovingObjectType;
import org.joml.Vector3i;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MovingObjectPosition.class)
public abstract class MovingObjectPositionMixin implements MovingObjectPositionHitResult {
   @Shadow
   public Entity entityHit;
   @Shadow
   public BlockPos blockPos;
   @Shadow
   public int blockX$v1_7;
   @Shadow
   public int blockY$v1_7;
   @Shadow
   public int blockZ$v1_7;
   @Shadow
   public int sideHit$v1_7;
   @Shadow
   public EnumFacing sideHit;
   @Shadow
   public MovingObjectType typeOfHit;
   @Shadow
   public Vec3 hitVec;

   @Override
   public Vec3Bridge bridge$getHitLocation() {
      return (Vec3Bridge)this.hitVec;
   }

   @Override
   public BridgeExtension bridge$getEntityHit() {
      return (BridgeExtension)this.entityHit;
   }

   @Override
   public Horsestats20Extension2 bridge$getBlockPosition() {
      return ThreadModuleDump63.MC_VERSION >= 1
         ? (Horsestats20Extension2)this.blockPos
         : (Horsestats20Extension2)(new Vector3i(this.blockX$v1_7, this.blockY$v1_7, this.blockZ$v1_7));
   }

   @Override
   public boolean bridge$isTypeOfHit(MovingObjectHitType var1) {
      return var1.ordinal() == this.typeOfHit.ordinal();
   }

   @Override
   public HorsestatsType_2 bridge$getSideHit() {
      return ThreadModuleDump63.MC_VERSION == 0 ? HorsestatsType_2.byId(this.sideHit$v1_7) : HorsestatsType_2.byId(this.sideHit.index);
   }
}
