package com.moonsworth.lunar.client.driver.gui;

import com.google.gson.JsonElement;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import org.jetbrains.annotations.Nullable;

public class FeatureDataProviderLegacy extends AbstractDataProviderLegacy {
   private final MixinCore9Extension field1;

   public FeatureDataProviderLegacy(String text) {
      this.field1 = (MixinCore9Extension)this.method2(text).method1(Framework.field1);
   }

   @Nullable
   @Override
   public JsonElement method128() {
      return this.field1.method4();
   }

   @Override
   public JsonElement provide() {
      return this.field1.method4();
   }
}
