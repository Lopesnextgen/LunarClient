package com.moonsworth.lunar.client.event.mixin.gui;

import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;
import net.kyori.adventure.text.Component;

public class EventBossBarUpdate extends LunarEvent {
   private final Component name;

   @Generated
   public Component getName() {
      return this.name;
   }

   @Generated
   public EventBossBarUpdate(Component component1) {
      this.name = component1;
   }
}
