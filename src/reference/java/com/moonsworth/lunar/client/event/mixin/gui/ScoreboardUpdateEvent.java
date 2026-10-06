package com.moonsworth.lunar.client.event.mixin.gui;

import com.moonsworth.lunar.bridge.lighting.Lighting4;
import com.moonsworth.lunar.client.guiRewindhandlers.Annotation3;
import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler28;
import com.moonsworth.lunar.client.guiRewindhandlers.nameplate.Nameplate2;
import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

@Annotation3(GuiRewindhandlersHandler28.class)
public class ScoreboardUpdateEvent extends Highlight implements Nameplate2 {
   private final Lighting4 field1;

   @Generated
   public Lighting4 method1() {
      return this.field1;
   }

   @Generated
   public ScoreboardUpdateEvent(Lighting4 lighting4) {
      this.field1 = lighting4;
   }
}
