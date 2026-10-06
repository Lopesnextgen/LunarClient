package com.moonsworth.lunar.client.replay.gui;

import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import java.util.List;

public abstract class RewindPropertyProvider extends com.moonsworth.lunar.client.driver.core.gui.GuiIterator {
   public RewindPropertyProvider(List<RewindPropertyProvider> list) {
      list.add(this);
   }

   public abstract void method1(RewindHandlers rewindhandlers1);
}
