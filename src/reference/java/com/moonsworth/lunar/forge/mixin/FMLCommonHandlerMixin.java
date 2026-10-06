package com.moonsworth.lunar.forge.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FMLCommonHandler.class)
public class FMLCommonHandlerMixin {
   public FMLCommonHandlerMixin() {
   }

   @Inject(method = "handleServerHandshake", at = @At("HEAD"), cancellable = true)
   public void ichor$processHandler(CallbackInfoReturnable<Boolean> callbackinforeturnable1) {
      callbackinforeturnable1.setReturnValue(true);
   }

   @Redirect(
      method = "handleServerStopped",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraftforge/fml/common/ObfuscationReflectionHelper;setPrivateValue(Ljava/lang/Class;Ljava/lang/Object;Ljava/lang/Object;[Ljava/lang/String;)V"
      )
   )
   private void lunar$fixFieldName(Class clazz1, Object obj2, Object obj3, String[] items4) {
      ((MinecraftServer)obj2).serverStopped = false;
   }
}
