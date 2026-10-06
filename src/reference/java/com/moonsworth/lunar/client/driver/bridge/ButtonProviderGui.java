package com.moonsworth.lunar.client.driver.bridge;

import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import java.util.LinkedHashMap;

public abstract class ButtonProviderGui implements DriverGuiExtension {
   protected final LinkedHashMap<String, ButtonProvider> field1 = new LinkedHashMap<>();

   public ButtonProviderGui() {
   }

   protected void method1(ButtonProvider gui2task1) {
      this.field1.put(gui2task1.getId(), gui2task1);
   }

   @Override
   public void method1(String text) {
      DriverGuiExtension.super.method1(text);
      if (this.field1.containsKey(text)) {
         this.field1.get(text).method11().run();
      }
   }
}
