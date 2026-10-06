package com.moonsworth.lunar.replaymod.mixin;

import com.moonsworth.lunar.client.fishing.Fishing;
import com.moonsworth.lunar.client.replay.replaymod.forge.v1_8.mixin.EventRegistrationsHandler;
import com.replaymod.core.ReplayModBackend;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ReplayModBackend.class)
public class ReplayModBackendMixin_v1_8 {
   @Inject(method = "init(Lnet/minecraftforge/fml/common/event/FMLPreInitializationEvent;)V", at = @At("HEAD"))
   private void onInitializeClient(CallbackInfo var1) {
      Fishing.method1(new EventRegistrationsHandler());
      System.out.println("ReplayModBackendMixin_v1_8.init: initializing ReplayMod");
   }
}
