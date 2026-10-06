package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.renderer.tileentity.TileEntityMobSpawnerRenderer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@VersionGate(min = 1)
@Mixin(TileEntityMobSpawnerRenderer.class)
public class TileEntityMobSpawnerRendererMixin {
   public TileEntityMobSpawnerRendererMixin() {
   }

   @ModifyExpressionValue(
      method = "renderMob",
      at = {
            @At(value = "INVOKE", target = "Lnet/minecraft/tileentity/MobSpawnerBaseLogic;getCachedEntity$v1_12()Lnet/minecraft/entity/Entity;"),
            @At(
               value = "INVOKE",
               target = "Lnet/minecraft/tileentity/MobSpawnerBaseLogic;func_180612_a(Lnet/minecraft/world/World;)Lnet/minecraft/entity/Entity;"
            )
      }
   )
   private static Entity lunar$renderTileEntityAt(
      Entity entity0,
      @Local(ordinal = 0, argsOnly = true) double value1,
      @Local(ordinal = 1, argsOnly = true) double value3,
      @Local(ordinal = 2, argsOnly = true) double value5
   ) {
      return !Client.method109().method41().method7().method3((BridgeExtension)entity0, value1, value3, value5) ? null : entity0;
   }
}
