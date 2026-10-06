package com.moonsworth.lunar.client.config.option;

import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.OptionProvider;
import com.moonsworth.lunar.client.config.option.ConfigRangeBuilder;
import java.util.function.BooleanSupplier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.TestOnly;

public interface SettingsSectionBuilder<Self> extends ConfigRangeBuilder<Self> {
   @Contract("_ -> this")
   Self method1(ClientOption<?>... items1);

   @Contract("_ -> this")
   default Self method2(OptionProvider<?, ?>... items1) {
      return this.method1(OptionProvider.method2(items1));
   }

   @Contract("_ -> this")
   Self method3(@NotNull BooleanSupplier booleansupplier1);

   @Contract("_ -> this")
   Self method4(@NotNull BooleanSupplier booleansupplier1);

   @Contract("_ -> this")
   Self method5(boolean flag1);

   @TestOnly
   void method6(boolean flag1);
}
