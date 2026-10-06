package com.moonsworth.lunar.client.driver.core.gui.mixin;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.framework.feature.markers.Markers2.Type;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.driver.DriverRouteRegistryLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.webosr.javascript.CallbackJS;

public class Gui implements DriverGuiExtensionLegacy, GuiIterator.Extension {
   private static KeyCode field1 = null;

   @Override
   public GuiIterator getProvider() {
      return Client.method109().method40().method87().method14();
   }

   @Override
   public void method2(KeyCode var1, int var2, int var3, int var4, int var5) {
      this.method4(var1, var4);
   }

   @Override
   public void method3(KeyCode var1, int var2, int var3, int var4, MarkerModel.Data5 var5) {
      this.method4(var1, var3);
   }

   private void method4(KeyCode var1, int var2) {
      field1 = null;
      if (DriverViewportLegacy.method50().method63() == DriverRouteRegistryLegacy.field22
         && ThreadModuleDump63.method4().method40().method87().method15().method8() == var1
         && var2 == 0) {
         field1 = var1;
         ThreadModuleDump63.method3().bridge$displayScreen(null);
      }
   }

   @CallbackJS("mark")
   public static void method5(Type type, Boolean var1) {
      if (type != null && ThreadModuleDump63.method8() != null) {
         ThreadModuleDump63.method3().bridge$displayScreen(null);
         if (var1 || field1 == ThreadModuleDump63.method4().method40().method87().method15().method8()) {
            ThreadModuleDump63.method4().method40().method87().method2(type);
            field1 = null;
         }
      }
   }
}
