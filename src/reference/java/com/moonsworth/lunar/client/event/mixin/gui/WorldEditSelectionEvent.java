package com.moonsworth.lunar.client.event.mixin.gui;

import com.moonsworth.lunar.client.framework.feature.worldeditcui.Worldeditcui2;
import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

public class WorldEditSelectionEvent extends Highlight {
   private Worldeditcui2 field1;

   @Generated
   public WorldEditSelectionEvent() {
   }

   @Generated
   public WorldEditSelectionEvent(Worldeditcui2 world) {
      this.field1 = world;
   }

   @Generated
   public Worldeditcui2 method1() {
      return this.field1;
   }
}
