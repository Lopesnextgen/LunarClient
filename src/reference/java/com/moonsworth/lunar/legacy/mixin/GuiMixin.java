package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.framework.feature.overlay.HudColorOverride;
import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class GuiMixin {
   public GuiMixin() {
   }

   @Inject(
      method = {
            "drawTexturedModalRect(IIIIII)V",
            "drawTexturedModalRect$v1_8(FFIIII)V",
            "drawTexturedModalRect$v1_8(IILnet/minecraft/client/renderer/texture/TextureAtlasSprite;II)V"
      },
      at = @At("HEAD")
   )
   private void lunar$applyGuiTint(CallbackInfo callback1) {
      HudColorOverride.method6();
   }

   @Inject(
      method = {
            "drawTexturedModalRect(IIIIII)V",
            "drawTexturedModalRect$v1_8(FFIIII)V",
            "drawTexturedModalRect$v1_8(IILnet/minecraft/client/renderer/texture/TextureAtlasSprite;II)V"
      },
      at = @At("RETURN")
   )
   private void lunar$clearGuiTint(CallbackInfo callback1) {
      HudColorOverride.method7();
   }
}
