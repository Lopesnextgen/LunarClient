package com.moonsworth.lunar.client.event.mixin.gui;

import com.moonsworth.lunar.client.framework.feature.worldeditcui.WorldeditSelection;
import com.moonsworth.lunar.client.event.LunarEvent;
import lombok.Generated;

public class EventWorldEditSelection extends LunarEvent {
   private WorldeditSelection field1;

   @Generated
   public EventWorldEditSelection() {
   }

   @Generated
   public EventWorldEditSelection(WorldeditSelection worldeditcui21) {
      this.field1 = worldeditcui21;
   }

   @Generated
   public WorldeditSelection method1() {
      return this.field1;
   }
}
