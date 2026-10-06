package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.cosmetics.PlayerModelPartMap;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Annotation2(min = 1)
@Mixin(LayerHeldItem.class)
public class LayerHeldItemGeckoLibMixin {
   @Annotation2(min = 5)
   @Inject(method = "renderHeldItem$v1_12", at = @At("HEAD"), cancellable = true)
   private void lunar$geckolibCancelItemRendering$v1_12(EntityLivingBase var1, ItemStack var2, TransformType var3, EnumHandSide var4, CallbackInfo var5) {
      if (var1 instanceof EntityPlayerBridge) {
         boolean var6 = PlayerModelPartMap.method39((EntityPlayerBridge)var1, (ItemStackBridge)var2);
         if (var6) {
            var5.cancel();
         }
      }
   }

   @Annotation2(max = 1)
   @Inject(method = "doRenderLayer", at = @At("HEAD"), cancellable = true)
   private void lunar$geckolibCancelItemRendering$v1_8(
      EntityLivingBase var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8, CallbackInfo var9
   ) {
      if (var1 instanceof EntityPlayerBridge) {
         boolean var10 = PlayerModelPartMap.method39((EntityPlayerBridge)var1, (ItemStackBridge)var1.getHeldItem());
         if (var10) {
            var9.cancel();
         }
      }
   }
}
