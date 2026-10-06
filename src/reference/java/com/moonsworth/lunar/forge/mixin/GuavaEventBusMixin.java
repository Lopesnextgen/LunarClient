package com.moonsworth.lunar.forge.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(com.moonsworth.lunar.forge.lib.guava.eventbus.EventBus.class)
public abstract class GuavaEventBusMixin {
   public GuavaEventBusMixin() {
   }

   @WrapMethod(method = "post")
   private void ichor$post(Object obj1, Operation<Void> operation2) {
      try {
         operation2.call(new Object[]{obj1});
      } catch (Throwable exception4) {
         exception4.printStackTrace();
      }
   }
}
