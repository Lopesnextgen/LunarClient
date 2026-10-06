package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.lunarclient.apollo.module.entity.EntityModule;
import com.moonsworth.lunar.client.Highlight3Iterator12;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleHandler;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.client.renderer.entity.RenderSheep_v1_7;
import net.minecraft.entity.passive.EntitySheep;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Annotation2(max = 0)
@Mixin(RenderSheep_v1_7.class)
public class RenderSheep_v1_7Mixin {
   @Redirect(
      method = "shouldRenderPass(Lnet/minecraft/entity/passive/EntitySheep;IF)I",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/EntitySheep;hasCustomNameTag()Z")
   )
   private boolean apollo$shouldRenderPass$customNametag(EntitySheep var1, @Share("sheep") LocalRef<EntitySheep> var2) {
      var2.set(var1);
      return ThreadModuleDump63.method4()
            .method84()
            .<ApolloModuleHandler>method3(EntityModule.class)
            .filter(var1x -> ((Highlight3Iterator12)var1x).method5(var1.getEntityId()))
            .isPresent()
         || var1.hasCustomNameTag$v1_7();
   }

   @Redirect(
      method = "shouldRenderPass(Lnet/minecraft/entity/passive/EntitySheep;IF)I",
      at = @At(value = "INVOKE", target = "Ljava/lang/String;equals(Ljava/lang/Object;)Z")
   )
   private boolean apollo$shouldRenderPass$rainbowTag(String var1, Object var2, @Share("sheep") LocalRef<EntitySheep> var3) {
      EntitySheep var4 = (EntitySheep)var3.get();
      return var4 == null
         ? var1.equals(var2)
         : ThreadModuleDump63.method4()
               .method84()
               .<ApolloModuleHandler>method3(EntityModule.class)
               .filter(var1x -> ((Highlight3Iterator12)var1x).method5(var4.getEntityId()))
               .isPresent()
            || var1.equals(var2);
   }
}
