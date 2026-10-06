package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.PlayerCapabilitiesBridge;
import net.minecraft.entity.player.PlayerCapabilities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PlayerCapabilities.class)
public abstract class PlayerCapabilitiesMixin implements PlayerCapabilitiesBridge {
   @Shadow
   public boolean isFlying;
   @Shadow
   public boolean isCreativeMode;
   @Shadow
   public float flySpeed;
   @Shadow
   public boolean allowFlying;

   public PlayerCapabilitiesMixin() {
   }

   @Shadow
   public abstract float getWalkSpeed();

   public boolean bridge$isFlying() {
      return this.isFlying;
   }

   public boolean bridge$isCreativeMode() {
      return this.isCreativeMode;
   }

   public float bridge$getFlySpeed() {
      return this.flySpeed;
   }

   public void bridge$setFlySpeed(float value) {
      this.flySpeed = value;
   }

   public float bridge$getWalkSpeed() {
      return this.getWalkSpeed();
   }

   public boolean bridge$isAllowFlying() {
      return this.allowFlying;
   }

   public void bridge$setFlying(boolean flag) {
      this.isFlying = flag;
   }
}
