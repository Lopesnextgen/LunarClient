package com.moonsworth.lunar.forge.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(com.moonsworth.lunar.forge.lib.guava.eventbus.EventBus.class)
public abstract class EventBusMixin22 {
   @WrapMethod(method = "post")
   private void ichor$post(Object var1, Operation<Void> var2) {
      try {
         var2.call(new Object[]{var1});
      } catch (Throwable var4) {
         var4.printStackTrace();
      }
   }
}
