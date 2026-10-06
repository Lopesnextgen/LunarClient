package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.lunarclient.apollo.module.entity.EntityModule;
import com.moonsworth.lunar.client.network.apollo.EntityApolloHandler;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.renderer.entity.layers.LayerSheepWool;
import net.minecraft.entity.passive.EntitySheep;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@VersionGate(min = 1)
@Mixin(LayerSheepWool.class)
public class LayerSheepWoolMixin {
   public LayerSheepWoolMixin() {
   }

   @Redirect(
      method = "doRenderLayer(Lnet/minecraft/entity/passive/EntitySheep;FFFFFFF)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/EntitySheep;hasCustomName()Z")
   )
   private boolean apollo$shouldMakeSheepRainbow_1(EntitySheep entitysheep1, @Share("sheep") LocalRef<EntitySheep> localref2) {
      localref2.set(entitysheep1);
      return Ref.method4()
            .method84()
            .method3(EntityModule.class)
            .map(arg0 -> (EntityApolloHandler)arg0)
            .filter(arg1x -> arg1x.method3() ? arg1x.method4(entitysheep1.getUniqueID()) : arg1x.method5(entitysheep1.getEntityId()))
            .isPresent()
         || entitysheep1.hasCustomName();
   }

   @Redirect(
      method = "doRenderLayer(Lnet/minecraft/entity/passive/EntitySheep;FFFFFFF)V",
      at = @At(value = "INVOKE", target = "Ljava/lang/String;equals(Ljava/lang/Object;)Z")
   )
   private boolean apollo$shouldMakeSheepRainbow(String text1, Object obj2, @Share("sheep") LocalRef<EntitySheep> localref3) {
      EntitySheep entitysheep4 = (EntitySheep)localref3.get();
      return entitysheep4 == null
         ? text1.equals(obj2)
         : Ref.method4()
               .method84()
               .method3(EntityModule.class)
               .map(arg0 -> (EntityApolloHandler)arg0)
               .filter(arg1x -> arg1x.method3() ? arg1x.method4(entitysheep4.getUniqueID()) : arg1x.method5(entitysheep4.getEntityId()))
               .isPresent()
            || text1.equals(obj2);
   }
}
