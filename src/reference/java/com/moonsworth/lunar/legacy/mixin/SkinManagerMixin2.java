package com.moonsworth.lunar.legacy.mixin;

import com.google.common.util.concurrent.Futures;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import net.minecraft.client.resources.SkinManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(SkinManager.class)
public class SkinManagerMixin2 {
   @ModifyVariable(method = {"loadProfileTextures$v1_8", "func_152790_a$v1_7"}, at = @At("HEAD"), argsOnly = true)
   private boolean lunar$unsecureHostedWorldSkins(boolean var1) {
      if (!var1) {
         return false;
      } else {
         return ThreadModuleDump63.method4() != null && ThreadModuleDump63.method4().method81() != null
            ? !ThreadModuleDump63.method4().method81().method20() && !ThreadModuleDump63.method4().method81().method26()
            : true;
      }
   }

   @WrapOperation(
      method = {"loadProfileTextures$v1_8", "func_152790_a$v1_7"},
      at = @At(value = "INVOKE", target = "Ljava/util/concurrent/ExecutorService;submit(Ljava/lang/Runnable;)Ljava/util/concurrent/Future;")
   )
   private Future<?> lunar$rewindNonThreadedSkinsLoading(ExecutorService var1, Runnable runnable, Operation<Future<?>> operation) {
      if (ThreadModuleDump63.method4() != null
         && ThreadModuleDump63.method4().method40() != null
         && ThreadModuleDump63.method4().method40().method85().method19()) {
         runnable.run();
         return Futures.immediateFuture(null);
      } else {
         return (Future<?>)operation.call(new Object[]{var1, runnable});
      }
   }
}
