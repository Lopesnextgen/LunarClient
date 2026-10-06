package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler2;

public class GuiHandler11 implements Gui_2 {
   private static final String field1 = "\ue068 ";
   private static final String field2 = "STARRED_";
   private String name;
   private boolean field3;
   private String field4;

   @Override
   public boolean method1(String var1, GuiRewindhandlersHandler2 var2) {
      this.field3 = var1.startsWith("\ue068 ");
      this.name = this.field3 ? var1.substring("\ue068 ".length()) : var1;
      this.field4 = Gui2.field5.get(this.name);
      if (this.field4 == null) {
         return false;
      } else {
         return this.field4.startsWith("STARRED_")
            ? Gui2.field3.containsKey(this.field4.substring("STARRED_".length()))
            : Gui2.field3.containsKey("STARRED_" + this.field4);
      }
   }

   @Override
   public Gui method2(GuiRewindhandlersHandler2 var1) {
      String var2 = this.getId();
      GuiRewindhandlersHandler2.Data2 var3 = var1.method5(var2);
      if (var3 != null) {
         return Gui.method2((int)var3.method4().method2());
      }

      GuiRewindhandlersHandler2.Data3 var4 = var1.method4(var2);
      return var4 == null ? Gui.method1() : Gui.method2((int)var4.method1());
   }

   @Override
   public void cleanup() {
      this.name = null;
      this.field3 = false;
      this.field4 = null;
   }

   private String getId() {
      String var1 = this.field4.startsWith("STARRED_") ? this.field4.substring("STARRED_".length()) : this.field4;
      return this.field3 ? "STARRED_" + var1 : var1;
   }
}
