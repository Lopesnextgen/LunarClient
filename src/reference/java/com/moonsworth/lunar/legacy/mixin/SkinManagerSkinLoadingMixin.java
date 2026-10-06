package com.moonsworth.lunar.legacy.mixin;

import com.google.common.util.concurrent.Futures;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import net.minecraft.client.resources.SkinManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(SkinManager.class)
public class SkinManagerSkinLoadingMixin {
   public SkinManagerSkinLoadingMixin() {
   }

   @ModifyVariable(method = {"loadProfileTextures$v1_8", "func_152790_a$v1_7"}, at = @At("HEAD"), argsOnly = true)
   private boolean lunar$unsecureHostedWorldSkins(boolean flag) {
      if (!flag) {
         return false;
      } else {
         return Ref.method4() != null && Ref.method4().method81() != null
            ? !Ref.method4().method81().method20() && !Ref.method4().method81().method26()
            : true;
      }
   }

   @WrapOperation(
      method = {"loadProfileTextures$v1_8", "func_152790_a$v1_7"},
      at = @At(value = "INVOKE", target = "Ljava/util/concurrent/ExecutorService;submit(Ljava/lang/Runnable;)Ljava/util/concurrent/Future;")
   )
   private Future<?> lunar$rewindNonThreadedSkinsLoading(ExecutorService executorservice1, Runnable runnable2, Operation<Future<?>> operation3) {
      if (Ref.method4() != null
         && Ref.method4().method40() != null
         && Ref.method4().method40().method85().method19()) {
         runnable2.run();
         return Futures.immediateFuture(null);
      } else {
         return (Future<?>)operation3.call(new Object[]{executorservice1, runnable2});
      }
   }
}
