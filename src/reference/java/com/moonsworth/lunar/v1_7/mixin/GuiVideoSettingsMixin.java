package com.moonsworth.lunar.v1_7.mixin;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiVideoSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import shadersmod.client.GuiShaderOptions;
import shadersmod.client.GuiShaders;

@Mixin({GuiVideoSettings.class, GuiShaderOptions.class, GuiShaders.class})
public class GuiVideoSettingsMixin extends GuiScreen {
   @Shadow(remap = false)
   public FontRenderer fontRendererObj;

   @Inject(method = "initGui", at = @At("TAIL"))
   public void impl$fixFontRenderer(CallbackInfo var1) {
      this.fontRendererObj = super.fontRendererObj;
   }
}
