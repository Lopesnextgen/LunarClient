package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.AnimationFrameBridge;
import net.minecraft.client.resources.data.AnimationFrame;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(AnimationFrame.class)
public abstract class AnimationFrameMixin implements AnimationFrameBridge {
   @Final
   @Shadow
   public int frameTime;
   @Final
   @Shadow
   public int frameIndex;

   public AnimationFrameMixin() {
   }

   @Shadow
   public abstract boolean hasNoTime();

   @Override
   public boolean bridge$hasNoTime() {
      return this.hasNoTime();
   }

   @Override
   public int bridge$getFrameTime() {
      return this.frameTime;
   }

   @Override
   public int bridge$getFrameIndex() {
      return this.frameIndex;
   }
}
