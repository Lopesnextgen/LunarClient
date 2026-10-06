package com.moonsworth.lunar.client.driver.bridge;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.framework.feature.markers.Markers2.Type;
import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.driver.DriverRouteRegistry;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.webosr.javascript.CallbackJS;

public class MarkersBridge implements DriverGuiExtension, GuiIterator.Extension {
   private static KeyCode field1 = null;

   public MarkersBridge() {
   }

   @Override
   public GuiIterator getProvider() {
      return Client.method109().method40().method87().method14();
   }

   @Override
   public void method2(KeyCode bridgetype_81, int number2, int number3, int number4, int number5) {
      this.method4(bridgetype_81, number4);
   }

   @Override
   public void method3(KeyCode bridgetype_81, int number2, int number3, int number4, MarkerModel.Data5 data55) {
      this.method4(bridgetype_81, number3);
   }

   private void method4(KeyCode bridgetype_81, int number2) {
      field1 = null;
      if (DriverViewportLegacy.method50().method63() == DriverRouteRegistry.field22
         && Ref.method4().method40().method87().method15().method8() == bridgetype_81
         && number2 == 0) {
         field1 = bridgetype_81;
         Ref.method3().bridge$displayScreen(null);
      }
   }

   @CallbackJS("mark")
   public static void method5(Type type0, Boolean flag1) {
      if (type0 != null && Ref.method8() != null) {
         Ref.method3().bridge$displayScreen(null);
         if (flag1 || field1 == Ref.method4().method40().method87().method15().method8()) {
            Ref.method4().method40().method87().method2(type0);
            field1 = null;
         }
      }
   }
}
