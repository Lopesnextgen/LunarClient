package com.moonsworth.lunar.client.driver.core.gui;

import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import java.util.LinkedHashMap;

public abstract class GuiExtension implements DriverGuiExtensionLegacy {
   protected final LinkedHashMap<String, Gui2Task> field1 = new LinkedHashMap<>();

   protected void method1(Gui2Task var1) {
      this.field1.put(var1.getId(), var1);
   }

   @Override
   public void method1(String var1) {
      DriverGuiExtensionLegacy.super.method1(var1);
      if (this.field1.containsKey(var1)) {
         this.field1.get(var1).method11().run();
      }
   }
}
