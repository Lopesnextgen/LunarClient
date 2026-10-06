package com.moonsworth.lunar.client.replay.replaymod.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.mod.misc.replaymod.ReplayMod;
import com.replaymod.lib.de.johni0702.minecraft.gui.GuiRenderer;
import com.replaymod.lib.de.johni0702.minecraft.gui.element.AbstractGuiElement;
import com.replaymod.lib.de.johni0702.minecraft.gui.element.advanced.AbstractGuiTimeline;
import com.replaymod.lib.de.johni0702.minecraft.gui.utils.Utils;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractGuiTimeline.class)
public abstract class AbstractGuiTimelineMixin<T extends AbstractGuiTimeline<T>> extends AbstractGuiElement<T> {
   public AbstractGuiTimelineMixin() {
   }

   @Redirect(
      method = "draw",
      at = @At(value = "INVOKE", target = "Lcom/replaymod/lib/de/johni0702/minecraft/gui/GuiRenderer;bindTexture(Lnet/minecraft/util/ResourceLocation;)V")
   )
   public void ichor$draw$bindTexture(GuiRenderer guirenderer1, ResourceLocation location2) {
      Boolean flag3 = (Boolean)Client.method109().method40().method64().method14().get();
      if (flag3) {
         Bridge.method42().method7(1.0F, 1.0F, 1.0F, 1.0F);
         guirenderer1.bindTexture((ResourceLocation)ReplayMod.field8);
      } else {
         guirenderer1.bindTexture(TEXTURE);
      }
   }

   @Redirect(
      method = "draw",
      at = @At(
         value = "INVOKE",
         target = "Lcom/replaymod/lib/de/johni0702/minecraft/gui/utils/Utils;drawDynamicRect(Lcom/replaymod/lib/de/johni0702/minecraft/gui/GuiRenderer;IIIIIIIIII)V"
      )
   )
   public void ichor$draw$dynamicRect(GuiRenderer guirenderer1, int value, int value2, int value3, int value4, int value5, int value6, int value7, int value8, int value9, int value10) {
      Boolean flag12 = (Boolean)Client.method109().method40().method64().method14().get();
      if (flag12) {
         value += 3;
         value6 *= 2;
         value7 += 2;
         value8 = 0;
         value9 += 2;
         value10 += 6;
      }

      Utils.drawDynamicRect(guirenderer1, value, value2, value3, value4, value5, value6, value7, value8, value9, value10);
   }

   @Redirect(
      method = "drawTimelineCursor",
      at = @At(value = "INVOKE", target = "Lcom/replaymod/lib/de/johni0702/minecraft/gui/GuiRenderer;bindTexture(Lnet/minecraft/util/ResourceLocation;)V")
   )
   public void ichor$draw$Timeline(GuiRenderer guirenderer1, ResourceLocation location2) {
      Boolean flag3 = (Boolean)Client.method109().method40().method64().method14().get();
      if (flag3) {
         guirenderer1.bindTexture((ResourceLocation)ReplayMod.field8);
      } else {
         guirenderer1.bindTexture(TEXTURE);
      }
   }
}
