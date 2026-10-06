package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.moonsworth.lunar.ichor.VersionGate;
import com.moonsworth.lunar.legacy.wrapper.RenderBackgroundExtension;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@VersionGate(min = 1)
@Mixin(GuiSlot.class)
public class GuiSlotBackgroundMixin implements RenderBackgroundExtension {
   @Unique
   private boolean lunar$renderBackground = true;

   public GuiSlotBackgroundMixin() {
   }

   public void ext$setRenderBackgrounds(boolean flag) {
      this.lunar$renderBackground = flag;
   }

   @WrapOperation(method = "drawScreen", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiSlot;drawBackground()V"))
   private void lunar$cancelBackgroundDraw(GuiSlot guislot1, Operation<Void> operation2) {
      if (this.lunar$renderBackground) {
         operation2.call(new Object[]{guislot1});
      }
   }

   @Inject(method = "overlayBackground", at = @At("HEAD"), cancellable = true)
   private void lunar$cancelOverlayBackground(int value, int value2, int value3, int value4, CallbackInfo callback5) {
      if (!this.lunar$renderBackground) {
         callback5.cancel();
      }
   }

   @WrapOperation(
      method = "drawScreen",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiSlot;drawContainerBackground$v1_8(Lnet/minecraft/client/renderer/Tessellator;)V")
   )
   private void lunar$hideDirtBackground(GuiSlot guislot1, Tessellator tessellator2, Operation<Void> operation3) {
      if (!this.lunar$renderBackground) {
         GlStateManager.colorMask(false, false, false, false);
      }

      operation3.call(new Object[]{guislot1, tessellator2});
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
   private void lunar$hideBackgroundElements(Tessellator tessellator1, Operation<Void> operation2) {
      if (!this.lunar$renderBackground) {
         GlStateManager.colorMask(false, false, false, false);
      }

      operation2.call(new Object[]{tessellator1});
      if (!this.lunar$renderBackground) {
         GlStateManager.colorMask(true, true, true, true);
      }
   }
}
