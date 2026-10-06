package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.coordinates;

import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import java.util.List;

public abstract class GuiIterator extends com.moonsworth.lunar.client.driver.core.gui.GuiIterator {
   public GuiIterator(List<GuiIterator> var1) {
      var1.add(this);
   }

   public abstract void refresh(RewindHandlers var1);
}
