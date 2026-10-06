package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler2;

public class NamedItemValueParser implements ItemValueParser {
   private String id;

   public NamedItemValueParser() {
   }

   @Override
   public boolean method1(String text, GuiRewindhandlersHandler2 handler) {
      this.id = SkyblockItemRegistry.field5.get(text);
      return this.id != null;
   }

   @Override
   public ItemValueResponse method2(GuiRewindhandlersHandler2 handler) {
      GuiRewindhandlersHandler2.Data2 data22 = handler.method5(this.id);
      GuiRewindhandlersHandler2.Data3 data33 = handler.method4(this.id);
      if (data22 != null) {
         return ItemValueResponse.method2((int)data22.method4().method2());
      } else {
         return data33 != null ? ItemValueResponse.method2((int)data33.method1()) : ItemValueResponse.method1();
      }
   }

   @Override
   public void cleanup() {
      this.id = null;
   }
}
