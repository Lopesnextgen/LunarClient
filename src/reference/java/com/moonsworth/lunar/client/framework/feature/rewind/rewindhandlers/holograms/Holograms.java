package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.holograms;

import com.moonsworth.lunar.client.framework.loading.LoadingStageImpl;
import com.moonsworth.lunar.client.framework.feature.rewind.Rewind2_3;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_3;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.Rewindhandlers;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates.Coordinates;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates.GuiIterator;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;

public class Holograms extends GuiIterator {
   public Holograms(List<GuiIterator> var1) {
      super(var1);
   }

   @Override
   public void method1(RewindHandlers var1) {
      Rewind2_3 var2 = var1.method40();
      Highlight_3 var3 = var2.method37();
      if (var3 != null) {
         this.method3("playhead", Coordinates.isDragging() && Coordinates.getScrubFrame() >= 0 ? Coordinates.getScrubFrame() : var3.method15());
         this.method3("paused", var3.isPaused());
         this.method3("dragging", Coordinates.isDragging());
         this.method3("ruleOfThirdsOverlay", var3.method18());
         this.method3("quartersOverlay", var3.method20());
      }

      this.method3("previewMode", var1.method44());
      this.method3("rendering", var1.method57().method25());
      this.method3("quickView", var2.method31());
      LoadingStageImpl var4 = ThreadModuleDump63.method4().method90();
      this.method3("panelX", var4.method16().get());
      this.method3("panelY", var4.method17().get());
      Rewindhandlers var5 = var1.method41();
      if (var5.isSkipping() && var5.getSkipDurationMs() > 20000L) {
         this.method3("progress", var5.getSkipProgress());
      } else {
         this.method3("progress", 1.0F);
      }
   }
}
