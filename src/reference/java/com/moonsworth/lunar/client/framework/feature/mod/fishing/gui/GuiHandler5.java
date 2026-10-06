package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler2;

public class GuiHandler5 implements Gui_2 {
   private int value = -1;

   @Override
   public boolean method1(String var1, GuiRewindhandlersHandler2 handler) {
      this.value = handler.method2(var1).getValue();
      return this.value != -1;
   }

   @Override
   public Gui method2(GuiRewindhandlersHandler2 var1) {
      return Gui.method2(this.value);
   }

   @Override
   public void cleanup() {
      this.value = -1;
   }
}
