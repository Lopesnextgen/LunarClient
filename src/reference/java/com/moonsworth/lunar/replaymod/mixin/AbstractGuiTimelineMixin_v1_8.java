package com.moonsworth.lunar.replaymod.mixin;

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
public abstract class AbstractGuiTimelineMixin_v1_8<T extends AbstractGuiTimeline<T>> extends AbstractGuiElement<T> {
   @Redirect(
      method = "draw",
      at = @At(value = "INVOKE", target = "Lcom/replaymod/lib/de/johni0702/minecraft/gui/GuiRenderer;bindTexture(Lnet/minecraft/util/ResourceLocation;)V")
   )
   public void ichor$draw$bindTexture(GuiRenderer var1, ResourceLocation var2) {
      Boolean var3 = Client.method109().method40().method64().method14().get();
      if (var3) {
         Bridge.method42().method7(1.0F, 1.0F, 1.0F, 1.0F);
         var1.bindTexture((ResourceLocation)ReplayMod.field8);
      } else {
         var1.bindTexture(TEXTURE);
      }
   }

   @Redirect(
      method = "draw",
      at = @At(
         value = "INVOKE",
         target = "Lcom/replaymod/lib/de/johni0702/minecraft/gui/utils/Utils;drawDynamicRect(Lcom/replaymod/lib/de/johni0702/minecraft/gui/GuiRenderer;IIIIIIIIII)V"
      )
   )
   public void ichor$draw$dynamicRect(GuiRenderer var1, int var2, int var3, int value, int value2, int value3, int value4, int value5, int value6, int value7, int value8) {
      Boolean var12 = Client.method109().method40().method64().method14().get();
      if (var12) {
         var2 += 3;
         value4 *= 2;
         value5 += 2;
         value6 = 0;
         value7 += 2;
         value8 += 6;
      }

      Utils.drawDynamicRect(var1, var2, var3, value, value2, value3, value4, value5, value6, value7, value8);
   }

   @Redirect(
      method = "drawTimelineCursor",
      at = @At(value = "INVOKE", target = "Lcom/replaymod/lib/de/johni0702/minecraft/gui/GuiRenderer;bindTexture(Lnet/minecraft/util/ResourceLocation;)V")
   )
   public void ichor$draw$Timeline(GuiRenderer var1, ResourceLocation var2) {
      Boolean var3 = Client.method109().method40().method64().method14().get();
      if (var3) {
         var1.bindTexture((ResourceLocation)ReplayMod.field8);
      } else {
         var1.bindTexture(TEXTURE);
      }
   }
}
