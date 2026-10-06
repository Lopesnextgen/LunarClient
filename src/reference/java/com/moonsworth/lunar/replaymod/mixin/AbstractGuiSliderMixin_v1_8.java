package com.moonsworth.lunar.replaymod.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.replaymod.lib.de.johni0702.minecraft.gui.GuiRenderer;
import com.replaymod.lib.de.johni0702.minecraft.gui.OffsetGuiRenderer;
import com.replaymod.lib.de.johni0702.minecraft.gui.RenderInfo;
import com.replaymod.lib.de.johni0702.minecraft.gui.element.AbstractGuiElement;
import com.replaymod.lib.de.johni0702.minecraft.gui.element.AbstractGuiSlider;
import com.replaymod.lib.de.johni0702.minecraft.gui.utils.lwjgl.ReadableDimension;
import net.minecraft.client.renderer.GlStateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractGuiSlider.class)
public abstract class AbstractGuiSliderMixin_v1_8 extends AbstractGuiElement {
   @Shadow
   public String text;
   @Shadow
   public int value;
   @Shadow
   public int steps;

   @Inject(method = "draw", at = @At("HEAD"), cancellable = true)
   public void draw(GuiRenderer renderer, ReadableDimension readableDimension, RenderInfo renderInfo, CallbackInfo callbackInfo) {
      BridgeExtension3_5 var5 = BridgeExtension3_5.method32();
      boolean var6 = Client.method109().method40().method64().method14().get();
      if (var6) {
         super.draw(renderer, readableDimension, renderInfo);
         int var7 = readableDimension.getWidth();
         int var8 = readableDimension.getHeight();
         float var9 = 0.0F;
         float var10 = 0.0F;
         int var11 = (var7 - 8) * this.value / this.steps;
         if (renderer instanceof OffsetGuiRenderer) {
            var9 = renderer.getOpenGlOffset().getX();
            var10 = renderer.getOpenGlOffset().getY();
         }

         LcuiScreen.method28(var5, var9, var10, readableDimension.getWidth(), readableDimension.getHeight(), 4.0F, -1728053248);
         FontRegistry.method9().method7(var5, this.text, var9 + var7 / 2.0F, var10 + 1.0F, -1);
         LcuiScreen.method97(var5, var9 + 3.0F, var10 + var8 - 7.5F, var7 - 6.0F, 3.0F, -1357243621);
         LcuiScreen.method77(var5, var9 + var11 + 4.0F, var10 + var8 - 6.0F, 4.0, -11561732);
         Bridge.method42().method7(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GlStateManager.blendFunc(770, 771);
         callbackInfo.cancel();
      }
   }
}
