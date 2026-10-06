package com.moonsworth.lunar.replaymod.mixin;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.mod.misc.replaymod.ReplayMod;
import com.replaymod.lib.de.johni0702.minecraft.gui.GuiRenderer;
import com.replaymod.lib.de.johni0702.minecraft.gui.element.AbstractGuiElement;
import com.replaymod.lib.de.johni0702.minecraft.gui.element.AbstractGuiHorizontalScrollbar;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractGuiHorizontalScrollbar.class)
public abstract class AbstractGuiHorizontalScrollbarMixin_v1_8 extends AbstractGuiElement {
   @Redirect(
      method = "draw",
      at = @At(value = "INVOKE", target = "Lcom/replaymod/lib/de/johni0702/minecraft/gui/GuiRenderer;bindTexture(Lnet/minecraft/util/ResourceLocation;)V")
   )
   public void ichor$draw(GuiRenderer renderer, ResourceLocation resourceLocation) {
      renderer.bindTexture(Client.method109().method40().method64().method14().get() ? (ResourceLocation)ReplayMod.field8 : TEXTURE);
   }
}
