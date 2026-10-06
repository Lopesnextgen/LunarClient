package com.moonsworth.lunar.forge.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FMLCommonHandler.class)
public class FMLCommonHandlerMixin2 {
   @Inject(method = "handleServerHandshake", at = @At("HEAD"), cancellable = true)
   public void ichor$processHandler(CallbackInfoReturnable<Boolean> var1) {
      var1.setReturnValue(true);
   }

   @Redirect(
      method = "handleServerStopped",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraftforge/fml/common/ObfuscationReflectionHelper;setPrivateValue(Ljava/lang/Class;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/String;)V"
      )
   )
   private void lunar$fixFieldName(Class var1, Object var2, Object var3, String var4) {
      ((MinecraftServer)var2).serverStopped = false;
   }
}
