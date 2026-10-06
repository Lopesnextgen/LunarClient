package com.moonsworth.lunar.client.replay.replaymod.mixin;

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
public abstract class AbstractGuiButtonMixin<T extends AbstractGuiButton<T>> extends AbstractGuiClickable<T> implements IGuiButton<T>, Clickable {
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

   public AbstractGuiButtonMixin() {
   }

   @Inject(method = "draw", at = @At("HEAD"), cancellable = true)
   public void draw(GuiRenderer guirenderer1, ReadableDimension readabledimension2, RenderInfo renderinfo3, CallbackInfo callback4) {
      BridgeExtension3_5 bridgeextension3_55 = BridgeExtension3_5.method32();
      boolean flag6 = (Boolean)Client.method109().method40().method64().method14().get();
      if (flag6 && renderinfo3.layer != 1) {
         boolean flag7 = this.isMouseHovering(new Point(renderinfo3.mouseX, renderinfo3.mouseY));
         int number8 = Integer.MAX_VALUE;
         if (!this.isEnabled()) {
            number8 = 8421504;
         } else if (flag7) {
            number8 = 16777120;
         }

         super.draw(guirenderer1, readabledimension2, renderinfo3);
         float value9 = 0.0F;
         float value10 = 0.0F;
         if (guirenderer1 instanceof OffsetGuiRenderer) {
            value9 += guirenderer1.getOpenGlOffset().getX();
            value10 += guirenderer1.getOpenGlOffset().getY();
         }

         GlStateManager.pushMatrix();
         int number11 = this.borderColor.method2(this.isEnabled() && flag7);
         int number12 = this.backgroundColor.method2(this.isEnabled() && flag7);
         LcuiScreen.method55(bridgeextension3_55, value9 + 1.0F, value10 + 1.0F, readabledimension2.getWidth() - 2, readabledimension2.getHeight() - 2, 4.0F, number11, 553648127, number12);
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GlStateManager.blendFunc(770, 771);
         if (this.texture != null) {
            guirenderer1.bindTexture((ResourceLocation)ReplayMod.field9);
            if (this.spriteUV != null && this.textureSize != null) {
               ReadableDimension readabledimension13 = this.spriteSize != null ? this.spriteSize : this.getMinSize();
               guirenderer1.drawTexturedRect(
                  0,
                  0,
                  this.spriteUV.getX(),
                  this.spriteUV.getY(),
                  readabledimension2.getWidth(),
                  readabledimension2.getHeight(),
                  readabledimension13.getWidth(),
                  readabledimension13.getHeight(),
                  this.textureSize.getWidth(),
                  this.textureSize.getHeight()
               );
            } else {
               guirenderer1.drawTexturedRect(0, 0, 0, 0, readabledimension2.getWidth(), readabledimension2.getHeight());
            }
         }

         if (this.label != null) {
            CachedFontImpl fishing2impl14 = FontRegistry.method11();
            fishing2impl14.method5(
               bridgeextension3_55,
               this.label.toUpperCase(),
               value9 + readabledimension2.getWidth() / 2.0F + 1.0F - fishing2impl14.method4(this.label.toUpperCase()) / 2.0F,
               value10 + readabledimension2.getHeight() / 2.0F - fishing2impl14.getHeight() + 1.0F,
               536870912
            );
            fishing2impl14.method5(
               bridgeextension3_55,
               this.label.toUpperCase(),
               value9 + readabledimension2.getWidth() / 2.0F - fishing2impl14.method4(this.label.toUpperCase()) / 2.0F,
               value10 + readabledimension2.getHeight() / 2.0F - fishing2impl14.getHeight(),
               number8
            );
         }

         GlStateManager.popMatrix();
         callback4.cancel();
      }
   }
}
