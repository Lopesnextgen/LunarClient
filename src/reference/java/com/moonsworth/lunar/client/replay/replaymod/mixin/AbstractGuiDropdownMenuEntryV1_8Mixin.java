package com.moonsworth.lunar.client.replay.replaymod.mixin;

import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.replaymod.lib.de.johni0702.minecraft.gui.GuiRenderer;
import com.replaymod.lib.de.johni0702.minecraft.gui.element.AbstractGuiClickable;
import com.replaymod.lib.de.johni0702.minecraft.gui.utils.lwjgl.ReadableColor;
import net.minecraft.client.renderer.GlStateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "com.replaymod.lib.de.johni0702.minecraft.gui.element.advanced.AbstractGuiDropdownMenu$DropdownEntry")
public abstract class AbstractGuiDropdownMenuEntryV1_8Mixin extends AbstractGuiClickable {
   public AbstractGuiDropdownMenuEntryV1_8Mixin() {
   }

   @Redirect(
      method = "draw",
      at = @At(
         value = "INVOKE",
         target = "Lcom/replaymod/lib/de/johni0702/minecraft/gui/GuiRenderer;drawRect(IIIILcom/replaymod/lib/de/johni0702/minecraft/gui/utils/lwjgl/ReadableColor;)V"
      )
   )
   public void ichor$draw$rect(GuiRenderer guirenderer1, int number2, int number3, int value, int value2, ReadableColor readablecolor6) {
      BridgeExtension3_5 bridgeextension3_57 = BridgeExtension3_5.method32();
      boolean flag8 = (Boolean)Client.method109().method40().method64().method14().get();
      if (!flag8) {
         guirenderer1.drawRect(number2, number3, value, value2, readablecolor6);
      } else {
         if (readablecolor6 == ReadableColor.BLACK) {
            LcuiScreen.method28(bridgeextension3_57, guirenderer1.getOpenGlOffset().getX(), guirenderer1.getOpenGlOffset().getY(), value + 2, value2 + 1, 4.0F, -553648128);
         }
      }
   }

   @Redirect(
      method = "draw",
      at = @At(
         value = "INVOKE",
         target = "Lcom/replaymod/lib/de/johni0702/minecraft/gui/GuiRenderer;drawString(IILcom/replaymod/lib/de/johni0702/minecraft/gui/utils/lwjgl/ReadableColor;Ljava/lang/String;)I"
      )
   )
   public int ichor$draw$string(GuiRenderer guirenderer1, int number2, int number3, ReadableColor readablecolor4, String text) {
      BridgeExtension3_5 bridgeextension3_56 = BridgeExtension3_5.method32();
      boolean flag7 = (Boolean)Client.method109().method40().method64().method14().get();
      if (!flag7) {
         return guirenderer1.drawString(number2, number3, readablecolor4, text);
      }

      FontRegistry.method9().method3(bridgeextension3_56, text, guirenderer1.getOpenGlOffset().getX() + 3.0F, guirenderer1.getOpenGlOffset().getY() + 3.0F, -1);
      GlStateManager.enableBlend();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.blendFunc(770, 771);
      return 0;
   }
}
