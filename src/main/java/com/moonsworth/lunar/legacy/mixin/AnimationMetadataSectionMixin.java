package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.AnimationMetadataSectionBridge;
import java.util.Set;
import net.minecraft.client.resources.data.AnimationFrame;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(AnimationMetadataSection.class)
public abstract class AnimationMetadataSectionMixin implements AnimationMetadataSectionBridge {
   @Final
   @Shadow
   public int frameHeight;
   @Final
   @Shadow
   public int frameWidth;
   @Final
   @Shadow
   public int frameTime;

   public AnimationMetadataSectionMixin() {
   }

   @Shadow
   public abstract int getFrameCount();

   @Shadow
   public abstract AnimationFrame getAnimationFrame(int number1);

   @Shadow
   public abstract int getFrameTimeSingle(int number1);

   @Shadow
   public abstract Set<Integer> getFrameIndexSet();

   @Shadow
   public abstract boolean frameHasTime(int number1);

   @Shadow
   public abstract int getFrameIndex(int number1);

   @Shadow
   public abstract boolean isInterpolate();

   public int bridge$getFrameHeight() {
      return this.frameHeight;
   }

   public int bridge$getFrameWidth() {
      return this.frameWidth;
   }

   public int bridge$getFrameCount() {
      return this.getFrameCount();
   }

   public int bridge$getFrameTime() {
      return this.frameTime;
   }

   public int bridge$getFrameTimeSingle(int number1) {
      return this.getFrameTimeSingle(number1);
   }

   public boolean bridge$hasTime(int number1) {
      return this.frameHasTime(number1);
   }

   public int bridge$getFrameIndex(int number1) {
      return this.getFrameIndex(number1);
   }
}
