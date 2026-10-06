package com.moonsworth.lunar.forge.mixin;

import com.moonsworth.lunar.forge.lib.guava.eventbus.SubscriberExceptionContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Pseudo;

@Pseudo
@Mixin(targets = "com.moonsworth.lunar.forge.lib.guava.eventbus.EventBus$LoggingSubscriberExceptionHandler")
public class LoggingSubscriberExceptionHandlerMixin {
   public LoggingSubscriberExceptionHandlerMixin() {
   }

   @Overwrite
   public void handleException(Throwable exception1, SubscriberExceptionContext subscriberexceptioncontext2) {
      System.err.println("Could not dispatch event: " + subscriberexceptioncontext2.getSubscriber() + " to " + subscriberexceptioncontext2.getSubscriberMethod() + "\n" + exception1.getCause());
      exception1.printStackTrace();
   }
}
