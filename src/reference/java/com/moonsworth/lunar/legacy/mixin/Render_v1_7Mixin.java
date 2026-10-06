package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.lunarclient.apollo.module.coloredfire.ColoredFireModule;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.client.Highlight3Iterator27;
import com.moonsworth.lunar.client.network.apollo.ApolloModuleHandler;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.rewindhandlersCore.RewindHandlers3Impl6;
import com.moonsworth.lunar.client.mod.misc.rewind.Rewind;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.function.Function;
import net.minecraft.client.renderer.entity.Render_v1_7;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Annotation2(max = 0)
@Mixin(Render_v1_7.class)
public class Render_v1_7Mixin {
   @Unique
   private static float apollo$extractColor(float var0, Function<Integer, Float> var1, Entity var2) {
      return ThreadModuleDump63.method4()
         .method84()
         .<ApolloModuleHandler>method3(ColoredFireModule.class)
         .map(var1x -> var2 != null ? ((Highlight3Iterator27)var1x).method5((BridgeExtension)var2) : -1)
         .filter(var0x -> var0x != 1)
         .<Float>map(var1)
         .orElse(var0);
   }

   @Inject(method = "renderEntityOnFire", at = @At("HEAD"), cancellable = true)
   private void apollo$renderEntityOnFire(
      Entity var1, double var2, double var4, double var6, float var8, CallbackInfo var9, @Share("entity") LocalRef<Entity> var10
   ) {
      var10.set(var1);
      if (ThreadModuleDump63.method4().method40().method84().method36()) {
         var9.cancel();
      }
   }

   @ModifyConstant(method = "renderEntityOnFire", constant = @Constant(floatValue = 1.0F, ordinal = 1))
   private float apollo$renderEntityOnFire$red(float var1, @Share("entity") LocalRef<Entity> var2) {
      return apollo$extractColor(var1, ThreadModuleDump23::method5, (Entity)var2.get());
   }

   @ModifyConstant(method = "renderEntityOnFire", constant = @Constant(floatValue = 1.0F, ordinal = 2))
   private float apollo$renderEntityOnFire$green(float var1, @Share("entity") LocalRef<Entity> var2) {
      return apollo$extractColor(var1, ThreadModuleDump23::greenFloat, (Entity)var2.get());
   }

   @ModifyConstant(method = "renderEntityOnFire", constant = @Constant(floatValue = 1.0F, ordinal = 3))
   private float apollo$renderEntityOnFire$blue(float var1, @Share("entity") LocalRef<Entity> var2) {
      return apollo$extractColor(var1, ThreadModuleDump23::method7, (Entity)var2.get());
   }

   @Inject(method = "renderEntityOnFire", at = @At("TAIL"))
   private void apollo$renderEntityOnFire$tail(
      Entity var1, double var2, double var4, double var6, float var8, CallbackInfo var9, @Share("entity") LocalRef<Entity> var10
   ) {
      var10.set(null);
   }

   @Redirect(
      method = "doRenderShadowAndFire",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/Render_v1_7;renderShadow(Lnet/minecraft/entity/Entity;DDDFF)V")
   )
   public void lunar$doRenderShadowAndFire(Render_v1_7 var1, Entity var2, double var3, double var5, double var7, float var9, float var10) {
      if (ThreadModuleDump63.method4().method40().method84().method37() && var1.getWorldFromRenderManager() != null) {
         var1.renderShadow(var2, var3, var5, var7, var9, var10);
      }
   }

   @Inject(method = "renderLivingLabel", at = @At("HEAD"), cancellable = true)
   private void lunar$rewindRenderNametags(Entity var1, String var2, double var3, double var5, double var7, int var9, CallbackInfo var10) {
      Rewind var11 = ThreadModuleDump63.method4().method40().method85();
      if (var11.method19()) {
         RewindHandlers3Impl6 var12 = var11.method35().method52();
         if (var12.method14().get() && !var12.method24().get()) {
            var10.cancel();
         }
      }
   }
}
