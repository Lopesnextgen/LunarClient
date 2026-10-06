package com.moonsworth.lunar.legacy.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.moonsworth.lunar.client.framework.Ref;
import net.optifine.shaders.Shaders;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;

@Pseudo
@Mixin(Shaders.class)
public abstract class ShadersMixin {
   public ShadersMixin() {
   }

   @WrapOperation(method = "beginRender", at = @At(value = "INVOKE", target = "Ljava/lang/System;currentTimeMillis()J"))
   private static long lunar$rewindSystemTime(Operation<Long> operation0) {
      return Ref.method4().method40().method85().method19()
         ? Ref.method3().bridge$getSystemTime()
         : (Long)operation0.call(new Object[0]);
   }
}
