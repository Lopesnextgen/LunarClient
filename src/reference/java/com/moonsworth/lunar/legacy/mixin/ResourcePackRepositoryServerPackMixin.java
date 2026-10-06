package com.moonsworth.lunar.legacy.mixin;

import com.google.common.util.concurrent.ListenableFuture;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerResourcePackUpdate;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerResourcePackRemove;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.io.File;
import java.util.List;
import net.minecraft.client.resources.ResourcePackRepository;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ResourcePackRepository.class)
public abstract class ResourcePackRepositoryServerPackMixin {
   public ResourcePackRepositoryServerPackMixin() {
   }

   @VersionGate(1)
   @Inject(
      method = "setResourcePackInstance$v1_8",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/Minecraft;scheduleResourcesRefresh$v1_8()Lcom/google/common/util/concurrent/ListenableFuture;"
      )
   )
   private void lunar$onSetServerResourcePack(File file1, CallbackInfoReturnable<ListenableFuture<Object>> callbackinforeturnable2) {
      LunarEventBus.method29().method12(EventServerResourcePackUpdate.class, () -> new EventServerResourcePackUpdate(List.of(file1)));
   }

   @VersionGate(0)
   @Inject(method = "func_148529_f$v1_7", at = @At("HEAD"))
   private void lunar$onRemoveServerResourcePack$v1_7(CallbackInfo callback1) {
      Ref.method3().bridge$submit(() -> LunarEventBus.method29().method12(EventServerResourcePackRemove.class, EventServerResourcePackRemove::new));
   }

   @VersionGate(1)
   @Inject(method = "clearResourcePack$v1_8", at = @At("HEAD"))
   private void lunar$onRemoveServerResourcePack$v1_8(CallbackInfo callback1) {
      Ref.method3().bridge$submit(() -> LunarEventBus.method29().method12(EventServerResourcePackRemove.class, EventServerResourcePackRemove::new));
   }
}
