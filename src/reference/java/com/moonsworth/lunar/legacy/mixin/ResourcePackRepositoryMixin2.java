package com.moonsworth.lunar.legacy.mixin;

import com.google.common.util.concurrent.ListenableFuture;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.gui.ServerResourcePackUpdateEvent;
import com.moonsworth.lunar.client.event.mixin.gui.ServerResourcePackRemoveEvent;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import java.io.File;
import java.util.List;
import net.minecraft.client.resources.ResourcePackRepository;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ResourcePackRepository.class)
public abstract class ResourcePackRepositoryMixin2 {
   @Annotation2(1)
   @Inject(
      method = "setResourcePackInstance$v1_8",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/Minecraft;scheduleResourcesRefresh$v1_8()Lcom/google/common/util/concurrent/ListenableFuture;"
      )
   )
   private void lunar$onSetServerResourcePack(File var1, CallbackInfoReturnable<ListenableFuture<Object>> callbackInfoReturnable) {
      ClientEventBus.method29().method12(ServerResourcePackUpdateEvent.class, () -> new ServerResourcePackUpdateEvent(List.of(var1)));
   }

   @Annotation2(0)
   @Inject(method = "func_148529_f$v1_7", at = @At("HEAD"))
   private void lunar$onRemoveServerResourcePack$v1_7(CallbackInfo var1) {
      ThreadModuleDump63.method3().bridge$submit(() -> ClientEventBus.method29().method12(ServerResourcePackRemoveEvent.class, ServerResourcePackRemoveEvent::new));
   }

   @Annotation2(1)
   @Inject(method = "clearResourcePack$v1_8", at = @At("HEAD"))
   private void lunar$onRemoveServerResourcePack$v1_8(CallbackInfo var1) {
      ThreadModuleDump63.method3().bridge$submit(() -> ClientEventBus.method29().method12(ServerResourcePackRemoveEvent.class, ServerResourcePackRemoveEvent::new));
   }
}
