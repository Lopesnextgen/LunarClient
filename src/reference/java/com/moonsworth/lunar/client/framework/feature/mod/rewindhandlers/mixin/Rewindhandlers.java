package com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.mixin;

import com.moonsworth.lunar.client.framework.feature.mod.highlight.HighlightType3;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.nameplate.GuiRewindhandlersHandler2;
import com.moonsworth.lunar.client.guiRewindhandlers.Annotation3;
import com.moonsworth.lunar.client.guiRewindhandlers.nameplate.Nameplate2;
import com.moonsworth.lunar.client.highlight.Highlight;
import lombok.Generated;

@Annotation3(GuiRewindhandlersHandler2.class)
public class Rewindhandlers extends Highlight implements Nameplate2 {
   private final HighlightType3 field1;

   @Generated
   public Rewindhandlers(HighlightType3 highlightType3) {
      this.field1 = highlightType3;
   }

   @Generated
   public HighlightType3 getCrop() {
      return this.field1;
   }
}
