package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.BlockStateBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension2;
import com.moonsworth.lunar.client.framework.feature.hypixelbedwars.mixin.BedwarsBedModelFactory;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.IBakedModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@VersionGate(1)
@Mixin(BlockModelShapes.class)
public class BlockModelShapesMixin {
   public BlockModelShapesMixin() {
   }

   @Inject(method = "getTexture", at = @At("HEAD"), cancellable = true)
   private void lunar$onGetTexture(IBlockState state1, CallbackInfoReturnable<TextureAtlasSprite> callbackinforeturnable2) {
      if (Minecraft.getMinecraft().thePlayer != null && Minecraft.getMinecraft().thePlayer.getPosition() != null) {
         IBakedModel ibakedmodel3 = (IBakedModel)BedwarsBedModelFactory.method2(
            (Horsestats20Extension2)Minecraft.getMinecraft().thePlayer.getPosition(), (BlockStateBridge)state1, null
         );
         if (ibakedmodel3 != null) {
            callbackinforeturnable2.setReturnValue(ibakedmodel3.getParticleTexture());
         }
      }
   }

   @Inject(method = "reloadModels", at = @At("HEAD"))
   private void lunar$onReloadModels(CallbackInfo callback1) {
      BedwarsBedModelFactory.method1();
   }
}
