package com.moonsworth.lunar.client.framework.feature.mod.impl.ultrasequencer;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler22;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.HighlightType;

public abstract class Framework7Extension2 extends com.moonsworth.lunar.client.framework.mod.AbstractFeature {
   private final GuiRewindhandlersHandler22 field8 = (GuiRewindhandlersHandler22)this.method19(GuiRewindhandlersHandler22.class);
   private final HighlightType field9;

   protected Framework7Extension2(HighlightType highlightType) {
      super(true);
      this.field9 = highlightType;
   }

   public boolean isInGui() {
      return this.field9 == this.field8.method7();
   }
}
