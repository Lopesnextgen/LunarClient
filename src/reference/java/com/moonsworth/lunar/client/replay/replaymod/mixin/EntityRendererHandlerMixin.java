package com.moonsworth.lunar.client.replay.replaymod.mixin;

import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventRenderTick.EventRenderTickStart;
import com.moonsworth.lunar.client.event.mixin.fishing.EventRenderTick.EventRenderTickEnd;
import com.replaymod.render.hooks.EntityRendererHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRendererHandler.class)
public class EntityRendererHandlerMixin {
   public EntityRendererHandlerMixin() {
   }

   @Inject(method = "renderWorld(FJ)V", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/fml/common/FMLCommonHandler;onRenderTickStart(F)V"))
   private void ichor$renderWorld(float value1, long number2, CallbackInfo callback4) {
      LunarEventBus.method29().method12(EventRenderTickStart.class, () -> new EventRenderTickStart(value1));
   }

   @Inject(method = "renderWorld(FJ)V", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/fml/common/FMLCommonHandler;onRenderTickEnd(F)V"))
   private void ichor$renderWorld$post(float value1, long number2, CallbackInfo callback4) {
      LunarEventBus.method29().method12(EventRenderTickEnd.class, () -> new EventRenderTickEnd(value1));
   }
}
