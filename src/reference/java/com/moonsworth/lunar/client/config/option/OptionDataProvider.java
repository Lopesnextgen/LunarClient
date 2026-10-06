package com.moonsworth.lunar.client.config.option;

import com.google.gson.JsonElement;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.OptionCategory;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.bridge.DriverDataProvider;
import com.moonsworth.lunar.client.driver.bridge.JsonProvider;
import org.jspecify.annotations.Nullable;

public interface OptionDataProvider extends DriverDataProvider, JsonProvider {
   default boolean method2() {
      return true;
   }

   default void method2(OptionCategory lightingtype21) {
      this.method3(lightingtype21, null);
   }

   default void method3(OptionCategory lightingtype21, String text2) {
      DriverViewportLegacy.method3(lightingtype21, text2, this.provide());
   }

   default void method4(OptionCategory lightingtype21, @Nullable String text2) {
   }

   default void method5() {
   }

   JsonElement provide();

   default @Nullable JsonElement method128() {
      return this.provide();
   }

   default OptionDataProvider method7(ClientOption<?> lightingextension1) {
      return this;
   }
}
