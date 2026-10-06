package com.moonsworth.lunar.client.replay.replaymod.mixin;

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
public abstract class AbstractGuiTimelineTimeMixin {
   public AbstractGuiTimelineTimeMixin() {
   }

   @Redirect(
      method = "drawTime",
      at = @At(
         value = "INVOKE",
         target = "Lcom/replaymod/lib/de/johni0702/minecraft/gui/GuiRenderer;drawCenteredString(IILcom/replaymod/lib/de/johni0702/minecraft/gui/utils/lwjgl/ReadableColor;Ljava/lang/String;Z)I"
      )
   )
   public int ichor$draw$bindTexture(GuiRenderer guirenderer1, int value, int value2, ReadableColor readablecolor4, String text, boolean flag) {
      Boolean flag7 = (Boolean)Client.method109().method40().method64().method14().get();
      if (flag7) {
         BridgeExtension3_5 bridgeextension3_58 = BridgeExtension3_5.method32();
         float value9 = 0.0F;
         float value10 = 0.0F;
         if (guirenderer1 instanceof OffsetGuiRenderer) {
            value9 += guirenderer1.getOpenGlOffset().getX();
            value10 += guirenderer1.getOpenGlOffset().getY();
         }

         FontRegistry.method17().method7(bridgeextension3_58, text, value + value9, value10, -1);
         return 0;
      } else {
         return guirenderer1.drawCenteredString(value, value2, readablecolor4, text, flag);
      }
   }
}
