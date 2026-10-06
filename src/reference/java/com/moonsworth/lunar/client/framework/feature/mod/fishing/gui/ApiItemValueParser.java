package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler2;

public class ApiItemValueParser implements ItemValueParser {
   private int value = -1;

   public ApiItemValueParser() {
   }

   @Override
   public boolean method1(String text, GuiRewindhandlersHandler2 handler) {
      this.value = handler.method2(text).getValue();
      return this.value != -1;
   }

   @Override
   public ItemValueResponse method2(GuiRewindhandlersHandler2 handler) {
      return ItemValueResponse.method2(this.value);
   }

   @Override
   public void cleanup() {
      this.value = -1;
   }
}
