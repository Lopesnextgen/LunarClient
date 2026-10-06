package com.moonsworth.lunar.client.replay.replaymod.mixin;

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
public abstract class AbstractGuiSliderV1_8Mixin extends AbstractGuiElement {
   @Shadow
   public String text;
   @Shadow
   public int value;
   @Shadow
   public int steps;

   public AbstractGuiSliderV1_8Mixin() {
   }

   @Inject(method = "draw", at = @At("HEAD"), cancellable = true)
   public void draw(GuiRenderer guirenderer1, ReadableDimension readabledimension2, RenderInfo renderinfo3, CallbackInfo callback4) {
      BridgeExtension3_5 bridgeextension3_55 = BridgeExtension3_5.method32();
      boolean flag6 = (Boolean)Client.method109().method40().method64().method14().get();
      if (flag6) {
         super.draw(guirenderer1, readabledimension2, renderinfo3);
         int number7 = readabledimension2.getWidth();
         int number8 = readabledimension2.getHeight();
         float value9 = 0.0F;
         float value10 = 0.0F;
         int number11 = (number7 - 8) * this.value / this.steps;
         if (guirenderer1 instanceof OffsetGuiRenderer) {
            value9 = guirenderer1.getOpenGlOffset().getX();
            value10 = guirenderer1.getOpenGlOffset().getY();
         }

         LcuiScreen.method28(bridgeextension3_55, value9, value10, readabledimension2.getWidth(), readabledimension2.getHeight(), 4.0F, -1728053248);
         FontRegistry.method9().method7(bridgeextension3_55, this.text, value9 + number7 / 2.0F, value10 + 1.0F, -1);
         LcuiScreen.method97(bridgeextension3_55, value9 + 3.0F, value10 + number8 - 7.5F, number7 - 6.0F, 3.0F, -1357243621);
         LcuiScreen.method77(bridgeextension3_55, value9 + number11 + 4.0F, value10 + number8 - 6.0F, 4.0, -11561732);
         Bridge.method42().method7(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GlStateManager.blendFunc(770, 771);
         callback4.cancel();
      }
   }
}
