package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.FrustumBridge;
import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.util.AxisAlignedBB;
import org.joml.Vector3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Frustum.class)
public abstract class FrustumMixin implements FrustumBridge {
   @Shadow
   public double x;
   @Shadow
   public double y;
   @Shadow
   public double z;

   public FrustumMixin() {
   }

   @Shadow
   public abstract boolean isBoundingBoxInFrustum(AxisAlignedBB box1);

   public Vector3d bridge$getPosition() {
      return new Vector3d(this.x, this.y, this.z);
   }

   public boolean bridge$isBoundingBoxInFrustum(AxisAlignedBBBridge horsestats121) {
      return this.isBoundingBoxInFrustum((AxisAlignedBB)horsestats121);
   }
}
