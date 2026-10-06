package com.moonsworth.lunar.client.framework.bootstrap;

import com.moonsworth.lunar.client.event.EventBusAccess;
import com.moonsworth.lunar.client.event.screen.EventScreenChange;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;

public class InitialScreenGuard implements EventBusAccess {
   private boolean field1 = false;

   public InitialScreenGuard() {
      this.handle(EventScreenChange.class, arg1 -> this.field1 = false);
      this.handle(EventTick.class, arg1 -> {
         if (!this.field1) {
            this.field1 = true;
         }
      });
      this.handle(com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventInitialScreenOpen.class, arg1 -> arg1.setCancelled(!this.field1));
   }
}
