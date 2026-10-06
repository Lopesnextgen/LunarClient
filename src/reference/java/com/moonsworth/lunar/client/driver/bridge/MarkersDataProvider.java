package com.moonsworth.lunar.client.driver.bridge;

import com.google.gson.JsonElement;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import org.jetbrains.annotations.Nullable;

public class MarkersDataProvider extends AbstractDataProvider {
   private final GuiIterator field1 = new GuiIterator();

   public MarkersDataProvider(String text1) {
      MixinCore9Extension mixincore9extension2 = (MixinCore9Extension)this.method2(text1).RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field1);
      this.field1.method3("metadata", mixincore9extension2);
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
