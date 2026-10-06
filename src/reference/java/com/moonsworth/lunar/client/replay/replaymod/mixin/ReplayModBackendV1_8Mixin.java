package com.moonsworth.lunar.client.replay.replaymod.mixin;

import com.moonsworth.lunar.client.ui.external.ExternalLinkRegistry;
import com.moonsworth.lunar.client.replay.replaymod.forge.v1_8.mixin.EventRegistrationsHandler;
import com.replaymod.core.ReplayModBackend;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.moonsworth.lunar.replaymod.mixin.ReplayModBackendMixin_v1_8;

@Mixin(ReplayModBackend.class)
public class ReplayModBackendV1_8Mixin {
   public ReplayModBackendV1_8Mixin() {
   }

   @Inject(method = "init(Lnet/minecraftforge/fml/common/event/FMLPreInitializationEvent;)V", at = @At("HEAD"))
   private void onInitializeClient(CallbackInfo callback1) {
      ExternalLinkRegistry.method1(new EventRegistrationsHandler());
      System.out.println("ReplayModBackendMixin_v1_8.init: initializing ReplayMod");
   }
}
