package com.moonsworth.lunar.client.driver.gui;

import com.google.gson.JsonElement;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import org.jetbrains.annotations.Nullable;

public class MarkersDataProviderLegacy extends AbstractDataProviderLegacy {
   private final GuiIterator field1 = new GuiIterator();

   public MarkersDataProviderLegacy(String text) {
      MixinCore9Extension var2 = (MixinCore9Extension)this.method2(text).method1(Framework.field1);
      this.field1.method3("metadata", var2);
   }

   @Nullable
   @Override
   public JsonElement method128() {
      return this.field1.method128();
   }

   @Override
   public JsonElement provide() {
      return this.field1.method128();
   }
}
