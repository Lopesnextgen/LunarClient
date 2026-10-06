package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.MovementInputBridge;
import net.minecraft.util.MovementInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MovementInput.class)
public abstract class MovementInputMixin implements MovementInputBridge {
   @Shadow
   public float moveStrafe;
   @Shadow
   public float moveForward;
   @Shadow
   public boolean sneak;
   @Shadow
   public boolean jump;

   public MovementInputMixin() {
   }

   public float bridge$getStrafeSpeed() {
      return this.moveStrafe;
   }

   public float bridge$getForwardSpeed() {
      return this.moveForward;
   }

   public boolean bridge$isSneaking() {
      return this.sneak;
   }

   public boolean bridge$isJumping() {
      return this.jump;
   }

   public void bridge$setMoveStrafe(float value1) {
      this.moveStrafe = value1;
   }

   public void bridge$setMoveForward(float value1) {
      this.moveForward = value1;
   }

   public void bridge$setJump(boolean flag1) {
      this.jump = flag1;
   }

   public void bridge$setSneak(boolean flag1) {
      this.sneak = flag1;
   }
}
