package com.moonsworth.lunar.client.framework.feature.mod.impl.ultrasequencer;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.HighlightTypeListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.SkyblockMenuType;

public abstract class ExperimentSolverModule extends com.moonsworth.lunar.client.framework.mod.AbstractFeature {
   private final HighlightTypeListener field8 = (HighlightTypeListener)this.method63(HighlightTypeListener.class);
   private final SkyblockMenuType field9;

   protected ExperimentSolverModule(SkyblockMenuType highlighttype1) {
      super(true);
      this.field9 = highlighttype1;
   }

   public boolean isInGui() {
      return this.field9 == this.field8.method7();
   }
}
