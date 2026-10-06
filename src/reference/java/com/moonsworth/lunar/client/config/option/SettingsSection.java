package com.moonsworth.lunar.client.config.option;

import com.moonsworth.lunar.client.config.option.ClientOption;
import java.util.List;
import java.util.function.BooleanSupplier;

public interface SettingsSection<Self> extends SettingsSectionBuilder<Self> {
   List<ClientOption<?>> method1();

   List<ClientOption<?>> method2();

   BooleanSupplier method3();

   boolean method4();

   boolean method5();

   Self method6(int number1);
}
