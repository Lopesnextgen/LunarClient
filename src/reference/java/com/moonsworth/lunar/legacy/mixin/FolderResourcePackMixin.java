package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.util.io.ResourcePackUtils;
import net.minecraft.client.resources.FileResourcePack;
import net.minecraft.client.resources.FolderResourcePack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({FolderResourcePack.class, FileResourcePack.class})
public abstract class FolderResourcePackMixin {
   public FolderResourcePackMixin() {
   }

   @Inject(method = "hasResourceName", at = @At("HEAD"), cancellable = true)
   private void lunar$hasResourceName(String text, CallbackInfoReturnable<Boolean> callbackinforeturnable2) {
      if (!ResourcePackUtils.method1(text)) {
         callbackinforeturnable2.setReturnValue(false);
      }
   }
}
