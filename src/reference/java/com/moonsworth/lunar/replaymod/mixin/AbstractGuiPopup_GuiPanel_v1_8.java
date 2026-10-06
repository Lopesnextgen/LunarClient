package com.moonsworth.lunar.replaymod.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.mod.misc.replaymod.ReplayMod;
import com.replaymod.lib.de.johni0702.minecraft.gui.GuiRenderer;
import com.replaymod.lib.de.johni0702.minecraft.gui.container.AbstractGuiContainer;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "com.replaymod.lib.de.johni0702.minecraft.gui.popup.AbstractGuiPopup$2")
public abstract class AbstractGuiPopup_GuiPanel_v1_8 extends AbstractGuiContainer {
   @Redirect(
      method = "draw",
      at = @At(value = "INVOKE", target = "Lcom/replaymod/lib/de/johni0702/minecraft/gui/GuiRenderer;bindTexture(Lnet/minecraft/util/ResourceLocation;)V")
   )
   public void ichor$draw$bindTexture(GuiRenderer renderer, ResourceLocation resourceLocation) {
      Boolean var3 = Client.method109().method40().method64().method14().get();
      Bridge.method42().method7(1.0F, 1.0F, 1.0F, 1.0F);
      Bridge.method42().method34();
      Bridge.method42().method18(770, 771, 1, 0);
      Bridge.method42().method38();
      Bridge.method42().method16(516, 0.0F);
      if (var3) {
         renderer.bindTexture((ResourceLocation)ReplayMod.field8);
      } else {
         renderer.bindTexture(TEXTURE);
      }
   }
}
