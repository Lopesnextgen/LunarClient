package com.moonsworth.lunar.replaymod.mixin;

import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.replaymod.lib.de.johni0702.minecraft.gui.GuiRenderer;
import com.replaymod.lib.de.johni0702.minecraft.gui.OffsetGuiRenderer;
import com.replaymod.lib.de.johni0702.minecraft.gui.element.advanced.AbstractGuiTimelineTime;
import com.replaymod.lib.de.johni0702.minecraft.gui.utils.lwjgl.ReadableColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractGuiTimelineTime.class)
public abstract class AbstractGuiTimelineTimeMixin_v1_8 {
   @Redirect(
      method = "drawTime",
      at = @At(
         value = "INVOKE",
         target = "Lcom/replaymod/lib/de/johni0702/minecraft/gui/GuiRenderer;drawCenteredString(IILcom/replaymod/lib/de/johni0702/minecraft/gui/utils/lwjgl/ReadableColor;Ljava/lang/String;Z)I"
      )
   )
   public int ichor$draw$bindTexture(GuiRenderer renderer, int value, int value2, ReadableColor readableColor, String text, boolean flag) {
      Boolean var7 = Client.method109().method40().method64().method14().get();
      if (var7) {
         BridgeExtension3_5 var8 = BridgeExtension3_5.method32();
         float var9 = 0.0F;
         float var10 = 0.0F;
         if (renderer instanceof OffsetGuiRenderer) {
            var9 += renderer.getOpenGlOffset().getX();
            var10 += renderer.getOpenGlOffset().getY();
         }

         FontRegistry.method17().method7(var8, text, value + var9, var10, -1);
         return 0;
      } else {
         return renderer.drawCenteredString(value, value2, readableColor, text, flag);
      }
   }
}
