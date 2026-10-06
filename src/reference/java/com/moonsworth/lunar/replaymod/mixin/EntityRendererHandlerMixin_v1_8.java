package com.moonsworth.lunar.replaymod.mixin;

import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventRenderTickPhase.EventRenderTickBegin;
import com.moonsworth.lunar.client.event.mixin.fishing.EventRenderTickPhase.EventRenderTickFinish;
import com.replaymod.render.hooks.EntityRendererHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRendererHandler.class)
public class EntityRendererHandlerMixin_v1_8 {
   @Inject(method = "renderWorld(FJ)V", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/fml/common/FMLCommonHandler;onRenderTickStart(F)V"))
   private void ichor$renderWorld(float var1, long var2, CallbackInfo var4) {
      ClientEventBus.method29().method12(EventRenderTickBegin.class, () -> new EventRenderTickBegin(var1));
   }

   @Inject(method = "renderWorld(FJ)V", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/fml/common/FMLCommonHandler;onRenderTickEnd(F)V"))
   private void ichor$renderWorld$post(float var1, long var2, CallbackInfo var4) {
      ClientEventBus.method29().method12(EventRenderTickFinish.class, () -> new EventRenderTickFinish(var1));
   }
}
