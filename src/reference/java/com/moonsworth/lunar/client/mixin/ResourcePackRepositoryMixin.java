package com.moonsworth.lunar.client.mixin;

import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerResourcePackUpdate;
import java.io.File;
import java.util.List;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net.minecraft.client.resources.ResourcePackRepository$2")
public abstract class ResourcePackRepositoryMixin {
   public ResourcePackRepositoryMixin() {
   }

   @Inject(method = "onDownloadComplete", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;scheduleResourcesRefresh()V"))
   private void lunar$onSetServerResourcePack(File file1, CallbackInfo callback2) {
      LunarEventBus.method29().method12(EventServerResourcePackUpdate.class, () -> new EventServerResourcePackUpdate(List.of(file1)));
   }
}
