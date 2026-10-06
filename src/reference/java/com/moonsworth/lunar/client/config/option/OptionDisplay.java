package com.moonsworth.lunar.client.config.option;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.driver.DriverFieldType;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.Nullable;

public interface OptionDisplay {
   DriverFieldType method1();

   @Nullable String method2();

   @Nullable DriverFieldType icon();

   @Nullable Runnable method3();

   boolean method4(ClientOption<?> lightingextension1);

   boolean method5();

   void method6(ClientOption<?> lightingextension1, JsonObject json2);

   @Contract("_->new")
   OptionDisplay method7(BooleanSupplier booleansupplier1);

   static OptionDisplay method8() {
      return new DisplaySpec(null, null, null, null, null, false);
   }

   static OptionDisplay method9(@Nullable DriverFieldType markerstype0) {
      return new DisplaySpec(markerstype0, null, null, null, null, false);
   }

   static OptionDisplay method10(@Nullable BooleanSupplier booleansupplier0) {
      return new DisplaySpec(null, null, null, booleansupplier0, null, false);
   }

   static OptionDisplay method11(
      @Nullable DriverFieldType markerstype0, @Nullable Supplier<String> supplier1, @Nullable DriverFieldType markerstype2, @Nullable BooleanSupplier booleansupplier3, boolean flag
   ) {
      return new DisplaySpec(markerstype0, supplier1, markerstype2, booleansupplier3, null, flag);
   }
}
