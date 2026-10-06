package com.moonsworth.lunar.client.driver.core.gui.mixin;

import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.util.UUID;

public class OutfitBridgeLegacy implements DriverGuiExtensionLegacy, GuiIterator.Extension {
   @Override
   public GuiIterator getProvider() {
      return ThreadModuleDump63.method4().method55().method16();
   }

   @CallbackJS("add")
   public static void add(String var0) {
      ThreadModuleDump63.method4().method55().method7(var0);
   }

   @CallbackJS("delete")
   public static void method2(UUID var0) {
      ThreadModuleDump63.method4().method55().method8(var0);
   }

   @CallbackJS("setDefault")
   public static void method3(UUID var0) {
      ThreadModuleDump63.method4().method55().method9(var0);
   }

   @CallbackJS("setFavorite")
   public static void method4(UUID var0) {
      ThreadModuleDump63.method4().method55().method13(var0);
   }

   @CallbackJS("rename")
   public static void method5(UUID var0, String text) {
      ThreadModuleDump63.method4().method55().method12(var0, text);
   }
}
