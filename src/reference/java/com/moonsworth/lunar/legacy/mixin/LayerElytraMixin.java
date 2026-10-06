package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.moonsworth.lunar.bridge.BridgeExtension2_5;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.cosmetics.ElytraTextureLoader;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.client.model.ModelElytra;
import net.minecraft.client.renderer.entity.layers.LayerElytra;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.mutable.MutableObject;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Annotation2(min = 5)
@Mixin(LayerElytra.class)
public class LayerElytraMixin {
   @Final
   @Shadow
   public ModelElytra modelElytra;

   @ModifyArg(
      method = "doRenderLayer",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/RenderLivingBase;bindTexture(Lnet/minecraft/util/ResourceLocation;)V")
   )
   private ResourceLocation lunar$replaceElytraTexture(ResourceLocation var1, @Local EntityLivingBase var2) {
      MutableObject var3 = new MutableObject();
      return (ResourceLocation)ElytraTextureLoader.method3((ResourceLocationBridge)var1, (BridgeExtension2_5)var2, var3::setValue);
   }
}
