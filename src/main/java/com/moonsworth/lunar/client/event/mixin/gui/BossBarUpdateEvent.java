package com.moonsworth.lunar.client.event.mixin.gui;

import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;
import net.kyori.adventure.text.Component;

public class BossBarUpdateEvent extends Highlight {
   private final Component name;

   @Generated
   public Component getName() {
      return this.name;
   }

   @Generated
   public BossBarUpdateEvent(Component component) {
      this.name = component;
   }
}
