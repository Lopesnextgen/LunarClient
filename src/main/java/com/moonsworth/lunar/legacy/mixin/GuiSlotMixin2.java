package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.moonsworth.lunar.ichor.Annotation2;
import com.moonsworth.lunar.legacy.wrapper.RenderBackgroundToggle;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Annotation2(min = 1)
@Mixin(GuiSlot.class)
public class GuiSlotMixin2 implements RenderBackgroundToggle {
   @Unique
   private boolean lunar$renderBackground = true;

   @Override
   public void ext$setRenderBackgrounds(boolean var1) {
      this.lunar$renderBackground = var1;
   }

   @WrapOperation(method = "drawScreen", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiSlot;drawBackground()V"))
   private void lunar$cancelBackgroundDraw(GuiSlot var1, Operation<Void> var2) {
      if (this.lunar$renderBackground) {
         var2.call(new Object[]{var1});
      }
   }

   @Inject(method = "overlayBackground", at = @At("HEAD"), cancellable = true)
   private void lunar$cancelOverlayBackground(int var1, int var2, int var3, int value, CallbackInfo callbackInfo) {
      if (!this.lunar$renderBackground) {
         callbackInfo.cancel();
      }
   }

   @WrapOperation(
      method = "drawScreen",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiSlot;drawContainerBackground$v1_8(Lnet/minecraft/client/renderer/Tessellator;)V")
   )
   private void lunar$hideDirtBackground(GuiSlot var1, Tessellator var2, Operation<Void> var3) {
      if (!this.lunar$renderBackground) {
         GlStateManager.colorMask(false, false, false, false);
      }

      var3.call(new Object[]{var1, var2});
      if (!this.lunar$renderBackground) {
         GlStateManager.colorMask(true, true, true, true);
      }
   }

   @WrapOperation(
      method = "drawScreen",
      at = {
            @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;draw$v1_8()V", ordinal = 0),
            @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;draw$v1_8()V", ordinal = 1)
      }
   )
   private void lunar$hideBackgroundElements(Tessellator var1, Operation<Void> var2) {
      if (!this.lunar$renderBackground) {
         GlStateManager.colorMask(false, false, false, false);
      }

      var2.call(new Object[]{var1});
      if (!this.lunar$renderBackground) {
         GlStateManager.colorMask(true, true, true, true);
      }
   }
}
