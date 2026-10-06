package com.moonsworth.lunar.replaymod.mixin;

import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.render.font.CachedFontImpl;
import com.moonsworth.lunar.client.ui.AnimatedValue;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.mod.misc.replaymod.ReplayMod;
import com.replaymod.lib.de.johni0702.minecraft.gui.GuiRenderer;
import com.replaymod.lib.de.johni0702.minecraft.gui.OffsetGuiRenderer;
import com.replaymod.lib.de.johni0702.minecraft.gui.RenderInfo;
import com.replaymod.lib.de.johni0702.minecraft.gui.element.AbstractGuiButton;
import com.replaymod.lib.de.johni0702.minecraft.gui.element.AbstractGuiClickable;
import com.replaymod.lib.de.johni0702.minecraft.gui.element.IGuiButton;
import com.replaymod.lib.de.johni0702.minecraft.gui.function.Clickable;
import com.replaymod.lib.de.johni0702.minecraft.gui.utils.lwjgl.Point;
import com.replaymod.lib.de.johni0702.minecraft.gui.utils.lwjgl.ReadableDimension;
import com.replaymod.lib.de.johni0702.minecraft.gui.utils.lwjgl.ReadablePoint;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractGuiButton.class)
public abstract class AbstractGuiButtonMixin_v1_8<T extends AbstractGuiButton<T>> extends AbstractGuiClickable<T> implements IGuiButton<T>, Clickable {
   private final AnimatedValue borderColor = new AnimatedValue(1076176165, 1076176165);
   private final AnimatedValue backgroundColor = new AnimatedValue(553648127, 1174405119);
   @Shadow
   private ReadablePoint spriteUV;
   @Shadow
   private ReadableDimension spriteSize;
   @Shadow
   private ReadableDimension textureSize;
   @Shadow
   private String label;
   @Shadow
   private ResourceLocation texture;

   @Inject(method = "draw", at = @At("HEAD"), cancellable = true)
   public void draw(GuiRenderer renderer, ReadableDimension readableDimension, RenderInfo renderInfo, CallbackInfo callbackInfo) {
      BridgeExtension3_5 var5 = BridgeExtension3_5.method32();
      boolean var6 = Client.method109().method40().method64().method14().get();
      if (var6 && renderInfo.layer != 1) {
         boolean var7 = this.isMouseHovering(new Point(renderInfo.mouseX, renderInfo.mouseY));
         int var8 = Integer.MAX_VALUE;
         if (!this.isEnabled()) {
            var8 = 8421504;
         } else if (var7) {
            var8 = 16777120;
         }

         super.draw(renderer, readableDimension, renderInfo);
         float var9 = 0.0F;
         float var10 = 0.0F;
         if (renderer instanceof OffsetGuiRenderer) {
            var9 += renderer.getOpenGlOffset().getX();
            var10 += renderer.getOpenGlOffset().getY();
         }

         GlStateManager.pushMatrix();
         int var11 = this.borderColor.method2(this.isEnabled() && var7);
         int var12 = this.backgroundColor.method2(this.isEnabled() && var7);
         LcuiScreen.method55(var5, var9 + 1.0F, var10 + 1.0F, readableDimension.getWidth() - 2, readableDimension.getHeight() - 2, 4.0F, var11, 553648127, var12);
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GlStateManager.blendFunc(770, 771);
         if (this.texture != null) {
            renderer.bindTexture((ResourceLocation)ReplayMod.field9);
            if (this.spriteUV != null && this.textureSize != null) {
               ReadableDimension var13 = this.spriteSize != null ? this.spriteSize : this.getMinSize();
               renderer.drawTexturedRect(
                  0,
                  0,
                  this.spriteUV.getX(),
                  this.spriteUV.getY(),
                  readableDimension.getWidth(),
                  readableDimension.getHeight(),
                  var13.getWidth(),
                  var13.getHeight(),
                  this.textureSize.getWidth(),
                  this.textureSize.getHeight()
               );
            } else {
               renderer.drawTexturedRect(0, 0, 0, 0, readableDimension.getWidth(), readableDimension.getHeight());
            }
         }

         if (this.label != null) {
            CachedFontImpl var14 = FontRegistry.method11();
            var14.method5(
               var5,
               this.label.toUpperCase(),
               var9 + readableDimension.getWidth() / 2.0F + 1.0F - var14.method4(this.label.toUpperCase()) / 2.0F,
               var10 + readableDimension.getHeight() / 2.0F - var14.getHeight() + 1.0F,
               536870912
            );
            var14.method5(
               var5,
               this.label.toUpperCase(),
               var9 + readableDimension.getWidth() / 2.0F - var14.method4(this.label.toUpperCase()) / 2.0F,
               var10 + readableDimension.getHeight() / 2.0F - var14.getHeight(),
               var8
            );
         }

         GlStateManager.popMatrix();
         callbackInfo.cancel();
      }
   }
}
