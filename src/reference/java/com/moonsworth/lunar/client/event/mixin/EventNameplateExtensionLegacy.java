package com.moonsworth.lunar.client.event.mixin;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.rewindhandlers.GuiRewindhandlersHandler2;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.rewindhandlers.Rewindhandlers.Extension;
import com.moonsworth.lunar.client.guiRewindhandlers.Annotation3;
import com.moonsworth.lunar.client.guiRewindhandlers.nameplate.Nameplate2;
import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

@Annotation3(GuiRewindhandlersHandler2.class)
public final class EventNameplateExtensionLegacy extends Highlight implements Nameplate2 {
   private final Extension field1;

   @Generated
   public Extension method1() {
      return this.field1;
   }

   @Generated
   public EventNameplateExtensionLegacy(Extension extension) {
      this.field1 = extension;
   }
}
