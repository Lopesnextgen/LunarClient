package com.moonsworth.lunar.client.config.option;

import com.moonsworth.lunar.client.config.option.SettingsSectionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import org.jspecify.annotations.Nullable;
import com.moonsworth.lunar.client.config.option.SettingsTreeContainer;
import com.moonsworth.lunar.client.config.option.SettingsParent;

public class RootSettingsBuilder extends SettingsTreeContainer<SettingsSectionImpl, RootSettingsBuilder.Data> implements OptionBakerFactory<OptionBaker> {
   public RootSettingsBuilder(SettingsGroupFactory<SettingsSectionImpl> lightingextension2$extension1) {
      super(lightingextension2$extension1, RootSettingsBuilder.Data::new);
   }

   public OptionBaker method2(@Nullable String text1, boolean flag2) {
      return flag2 ? new OptionBaker(text1) : new DevOptionBaker(text1);
   }

   public static class Data extends SettingsParent<SettingsSectionImpl, RootSettingsBuilder.Data> {
      public Data(SettingsBuilder<SettingsSectionImpl, RootSettingsBuilder.Data> lighting_51, SettingsSectionBuilder<?> threadmoduledump43extension2) {
         super(lighting_51, threadmoduledump43extension2);
      }
   }
}
