package com.moonsworth.lunar.v1_7.mixin;

import com.moonsworth.lunar.client.framework.Ref;
import net.minecraft.client.renderer.EntityRenderer;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public abstract class EntityRendererMixin {
   public EntityRendererMixin() {
   }

   @Redirect(
      method = "Lnet/minecraft/client/renderer/EntityRenderer;getFOVModifier(FZ)F",
      at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;isKeyDown(I)Z")
   )
   public boolean impl$isKeyDown(int number1) {
      return Ref.method4().method40().method50().isEnabled() ? false : Keyboard.isKeyDown(number1);
   }

   @Redirect(
      method = "Lnet/minecraft/client/renderer/EntityRenderer;getFOVModifier(FZ)F",
      at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;isButtonDown(I)Z")
   )
   public boolean impl$isMouseDown(int number1) {
      return Ref.method4().method40().method50().isEnabled() ? false : Mouse.isButtonDown(number1);
   }

   @Inject(method = "showLagometer", at = @At("HEAD"), cancellable = true)
   private void lunar$showLagometer(long number1, long value, CallbackInfo callback5) {
      if (Ref.method4().method40().method95().isEnabled()) {
         callback5.cancel();
      }
   }
}
