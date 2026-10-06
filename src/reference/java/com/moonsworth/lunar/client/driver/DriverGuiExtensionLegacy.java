package com.moonsworth.lunar.client.driver;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.driver.core.gui.Gui;
import com.moonsworth.lunar.client.driver.core.gui.JsonProviderLegacy;
import java.nio.file.Path;
import java.util.List;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;

public interface DriverGuiExtensionLegacy extends Gui, JsonProviderLegacy {
   default void method1(String var1) {
   }

   default void method2(KeyCode var1, int var2, int var3, int var4, int var5) {
   }

   default void method3(KeyCode var1, int var2, int var3, int var4, MarkerModel.Data5 var5) {
   }

   default void method4(double var1, double var3) {
   }

   default void method5(List<Path> var1) {
   }

   default boolean method6() {
      return false;
   }
}
