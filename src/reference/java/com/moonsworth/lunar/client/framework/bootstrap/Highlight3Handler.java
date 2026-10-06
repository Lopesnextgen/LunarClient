package com.moonsworth.lunar.client.framework.bootstrap;

import com.moonsworth.lunar.client.event.EventRegistrar;
import com.moonsworth.lunar.client.event.screen.ScreenChangeEvent;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;

public class Highlight3Handler implements EventRegistrar {
   private boolean field1 = false;

   public Highlight3Handler() {
      this.handle(ScreenChangeEvent.class, var1 -> this.field1 = false);
      this.handle(EventClientTick.class, var1 -> {
         if (!this.field1) {
            this.field1 = true;
         }
      });
      this.handle(com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventInitialScreenOpenLegacy.class, var1 -> var1.setCancelled(!this.field1));
   }
}
