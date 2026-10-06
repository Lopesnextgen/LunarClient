package com.moonsworth.lunar.client.driver.bridge;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;

public abstract class AbstractDataProvider implements com.moonsworth.lunar.client.driver.DriverGuiExtension {
   public AbstractDataProvider() {
   }

   protected Framework7Extension method2(String text1) {
      return Client.method109().method40().IIORHHIRHIORHRCCCOICCRCHRRCCRH().stream().filter(arg1x -> arg1x.getId().equals(text1)).findAny().orElse(null);
   }
}
