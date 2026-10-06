package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.hitcolor.HitcolorExtension;
import com.moonsworth.lunar.client.framework.feature.pkg.Pkg6;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer_v1_7;
import net.minecraft.tileentity.TileEntitySkull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Annotation2(max = 0)
@Mixin(TileEntitySkullRenderer_v1_7.class)
public class TileEntitySkullRenderer_v1_7Mixin2 {
   @Inject(method = "renderTileEntityAt(Lnet/minecraft/tileentity/TileEntitySkull;DDDF)V", at = @At("HEAD"))
   private void lunar$renderTileEntityAt(TileEntitySkull var1, double var2, double var4, double var6, float var8, CallbackInfo var9) {
      Pkg6.method1((HitcolorExtension)var1, var1.func_152108_a$v1_7());
   }
}
