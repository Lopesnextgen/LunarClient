package com.moonsworth.lunar.client.driver.gui;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;

public abstract class AbstractDataProviderLegacy implements com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy {
   protected Framework7Extension method2(String var1) {
      return Client.method109().method40().method1().stream().filter(var1x -> var1x.getId().equals(var1)).findAny().orElse(null);
   }
}
