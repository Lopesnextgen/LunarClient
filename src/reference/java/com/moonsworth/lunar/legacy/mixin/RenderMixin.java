package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.lunarclient.apollo.module.coloredfire.ColoredFireModule;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.client.network.apollo.ColoredFireApolloHandler;
import com.moonsworth.lunar.client.replay.render.WorldRenderHandler;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindMod;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.function.Function;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@VersionGate(min = 1)
@Mixin(Render.class)
public class RenderMixin {
   public RenderMixin() {
   }

   @Unique
   private static float apollo$extractColor(float value0, Function<Integer, Float> function1, Entity entity2) {
      return Ref.method4()
         .method84()
         .method3(ColoredFireModule.class)
         .map(arg1x -> entity2 != null ? ((ColoredFireApolloHandler)arg1x).method5((BridgeExtension)entity2) : -1)
         .filter(arg0x -> arg0x != 1)
         .<Float>map(function1)
         .orElse(value0);
   }

   @Inject(method = "renderEntityOnFire", at = @At("HEAD"), cancellable = true)
   private void apollo$renderEntityOnFire(
      Entity entity1, double value2, double value4, double value6, float value8, CallbackInfo callback9, @Share("entity") LocalRef<Entity> localref10
   ) {
      localref10.set(entity1);
      if (Ref.method4().method40().method84().method36()) {
         callback9.cancel();
      }
   }

   @ModifyConstant(method = "renderEntityOnFire", constant = @Constant(floatValue = 1.0F, ordinal = 1))
   private float apollo$renderEntityOnFire$red(float value1, @Share("entity") LocalRef<Entity> localref2) {
      return apollo$extractColor(value1, ColorUtils::method5, (Entity)localref2.get());
   }

   @ModifyConstant(method = "renderEntityOnFire", constant = @Constant(floatValue = 1.0F, ordinal = 2))
   private float apollo$renderEntityOnFire$green(float value1, @Share("entity") LocalRef<Entity> localref2) {
      return apollo$extractColor(value1, ColorUtils::method6, (Entity)localref2.get());
   }

   @ModifyConstant(method = "renderEntityOnFire", constant = @Constant(floatValue = 1.0F, ordinal = 3))
   private float apollo$renderEntityOnFire$blue(float value1, @Share("entity") LocalRef<Entity> localref2) {
      return apollo$extractColor(value1, ColorUtils::method7, (Entity)localref2.get());
   }

   @Inject(method = "renderEntityOnFire", at = @At("TAIL"))
   private void apollo$renderEntityOnFire$tail(
      Entity entity1, double value2, double value4, double value6, float value8, CallbackInfo callback9, @Share("entity") LocalRef<Entity> localref10
   ) {
      localref10.set(null);
   }

   @Redirect(
      method = "doRenderShadowAndFire",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/Render_v1_8;renderShadow(Lnet/minecraft/entity/Entity;DDDFF)V")
   )
   public void lunar$doRenderShadowAndFire(Render<?> render1, Entity entity2, double value3, double value5, double value7, float value9, float value10) {
      if (Ref.method4().method40().method84().method37() && render1.getWorldFromRenderManager() != null) {
         render1.renderShadow(entity2, value3, value5, value7, value9, value10);
      }
   }

   @Inject(method = "renderLivingLabel", at = @At("HEAD"), cancellable = true)
   private void lunar$rewindRenderNametags(Entity entity1, String text2, double value3, double value5, double value7, int number9, CallbackInfo callback10) {
      RewindMod rewind11 = Ref.method4().method40().method85();
      if (rewind11.method19()) {
         WorldRenderHandler rewindhandlers3impl612 = rewind11.method35().method52();
         if ((Boolean)rewindhandlers3impl612.method14().get() && !(Boolean)rewindhandlers3impl612.method24().get()) {
            callback10.cancel();
         }
      }
   }
}
