package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.chat.translation.Translatable;
import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(I18n.class)
public abstract class I18nMixin implements Translatable {
   public I18nMixin() {
   }

   @Inject(method = "format", at = @At("HEAD"), cancellable = true)
   private static void impl$onFormat(String text, Object[] items1, CallbackInfoReturnable<String> callbackinforeturnable2) {
      Client client3 = Client.method109();
      if (client3 != null && client3.method67() != null) {
         String text4 = client3.method67().method19(text, items1, false);
         if (text4 != null) {
            callbackinforeturnable2.setReturnValue(String.format(text4));
         }
      }
   }

   public String getLanguagePath() {
      return null;
   }
}
