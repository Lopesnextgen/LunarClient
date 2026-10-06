package com.moonsworth.lunar.client.driver.bridge;

import com.google.gson.JsonElement;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import org.jetbrains.annotations.Nullable;

public class FeatureDataProvider extends AbstractDataProvider {
   private final MixinCore9Extension field1;

   public FeatureDataProvider(String text1) {
      this.field1 = (MixinCore9Extension)this.method2(text1).RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field1);
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
