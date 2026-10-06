package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.moonsworth.lunar.ichor.VersionGate;
import com.moonsworth.lunar.ichor.MixinCondition;
import com.moonsworth.lunar.legacy.wrapper.RenderBackgroundExtension;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@MixinCondition(absent = {"forge", "optifine"}, available = @VersionGate(min = 1))
@Mixin(GuiSlot.class)
public class GuiSlotMixin implements RenderBackgroundExtension {
   @Unique
   private boolean lunar$renderBackground = true;

   public GuiSlotMixin() {
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
   private void lunar$cancelOverlayBackground(int value, int value2, int number3, int value3, CallbackInfo callback5) {
      if (!this.lunar$renderBackground) {
         callback5.cancel();
      }
   }

   @VersionGate(min = 1)
   @WrapOperation(
      method = "drawScreen",
      at = {
            @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;draw$v1_8()V", ordinal = 0),
            @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;draw$v1_8()V", ordinal = 1),
            @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;draw$v1_8()V", ordinal = 2)
      }
   )
   private void lunar$hideBackgroundElements$v1_8(Tessellator tessellator1, Operation<Void> operation2) {
      if (!this.lunar$renderBackground) {
         GlStateManager.colorMask(false, false, false, false);
      }

      operation2.call(new Object[]{tessellator1});
      if (!this.lunar$renderBackground) {
         GlStateManager.colorMask(true, true, true, true);
      }
   }

   @VersionGate(0)
   @WrapOperation(
      method = "drawScreen",
      at = {
            @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;draw$v1_7()I", ordinal = 0),
            @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;draw$v1_7()I", ordinal = 1),
            @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/Tessellator;draw$v1_7()I", ordinal = 2)
      }
   )
   @Dynamic
   private int lunar$hideBackgroundElements$v1_7(Tessellator tessellator1, Operation<Integer> operation2) {
      if (!this.lunar$renderBackground) {
         GL11.glColorMask(false, false, false, false);
      }

      int number3 = (Integer)operation2.call(new Object[]{tessellator1});
      if (!this.lunar$renderBackground) {
         GL11.glColorMask(true, true, true, true);
      }

      return number3;
   }
}
