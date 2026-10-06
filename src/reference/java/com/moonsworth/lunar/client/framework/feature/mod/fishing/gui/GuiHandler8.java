package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.GuiRewindhandlersHandler2;

public class GuiHandler8 implements Gui_2 {
   private String id;

   @Override
   public boolean method1(String var1, GuiRewindhandlersHandler2 var2) {
      this.id = Gui2.field5.get(var1);
      return this.id != null;
   }

   @Override
   public Gui method2(GuiRewindhandlersHandler2 var1) {
      GuiRewindhandlersHandler2.Data2 var2 = var1.method5(this.id);
      GuiRewindhandlersHandler2.Data3 var3 = var1.method4(this.id);
      if (var2 != null) {
         return Gui.method2((int)var2.method4().method2());
      } else {
         return var3 != null ? Gui.method2((int)var3.method1()) : Gui.method1();
      }
   }

   @Override
   public void cleanup() {
      this.id = null;
   }
}
