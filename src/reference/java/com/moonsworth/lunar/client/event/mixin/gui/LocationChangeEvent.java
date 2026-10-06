package com.moonsworth.lunar.client.event.mixin.gui;

import com.moonsworth.lunar.client.guiRewindhandlers.Annotation3;
import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler23;
import com.moonsworth.lunar.client.guiRewindhandlers.nameplate.Nameplate2;
import com.moonsworth.lunar.client.guiRewindhandlers.rewindhandlers.Rewindhandlers2;
import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

@Annotation3(GuiRewindhandlersHandler23.class)
public class LocationChangeEvent extends Highlight implements Nameplate2 {
   private final Rewindhandlers2 field1;
   private final Rewindhandlers2 field2;

   @Generated
   public Rewindhandlers2 method1() {
      return this.field1;
   }

   @Generated
   public Rewindhandlers2 method2() {
      return this.field2;
   }

   @Generated
   public LocationChangeEvent(Rewindhandlers2 rewindhandlers2, Rewindhandlers2 rewindhandlers22) {
      this.field1 = rewindhandlers2;
      this.field2 = rewindhandlers22;
   }
}
