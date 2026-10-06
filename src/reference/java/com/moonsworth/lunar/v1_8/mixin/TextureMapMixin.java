package com.moonsworth.lunar.v1_8.mixin;

import net.minecraft.client.renderer.texture.IIconCreator;
import net.minecraft.client.renderer.texture.TextureMap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TextureMap.class)
public class TextureMapMixin {
   public TextureMapMixin() {
   }

   @Inject(method = "<init>(Ljava/lang/String;Lnet/minecraft/client/renderer/texture/IIconCreator;Z)V", at = @At("TAIL"))
   private void ichor$init(String text1, IIconCreator iiconcreator2, boolean flag3, CallbackInfo callback4) {
      ((TextureMap)this).skipFirst = false;
   }
}
