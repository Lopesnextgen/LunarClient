package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler2;

public class StarredItemValueParser implements ItemValueParser {
   private static final String field1 = "\ue068 ";
   private static final String field2 = "STARRED_";
   private String name;
   private boolean field3;
   private String field4;

   public StarredItemValueParser() {
   }

   @Override
   public boolean method1(String text1, GuiRewindhandlersHandler2 handler) {
      this.field3 = text1.startsWith("\ue068 ");
      this.name = this.field3 ? text1.substring("\ue068 ".length()) : text1;
      this.field4 = SkyblockItemRegistry.field5.get(this.name);
      if (this.field4 == null) {
         return false;
      } else {
         return this.field4.startsWith("STARRED_")
            ? SkyblockItemRegistry.field3.containsKey(this.field4.substring("STARRED_".length()))
            : SkyblockItemRegistry.field3.containsKey("STARRED_" + this.field4);
      }
   }

   @Override
   public ItemValueResponse method2(GuiRewindhandlersHandler2 handler) {
      String text2 = this.getId();
      GuiRewindhandlersHandler2.Data2 data23 = handler.method5(text2);
      if (data23 != null) {
         return ItemValueResponse.method2((int)data23.method4().method2());
      }

      GuiRewindhandlersHandler2.Data3 data34 = handler.method4(text2);
      return data34 == null ? ItemValueResponse.method1() : ItemValueResponse.method2((int)data34.method1());
   }

   @Override
   public void cleanup() {
      this.name = null;
      this.field3 = false;
      this.field4 = null;
   }

   private String getId() {
      String text1 = this.field4.startsWith("STARRED_") ? this.field4.substring("STARRED_".length()) : this.field4;
      return this.field3 ? "STARRED_" + text1 : text1;
   }
}
