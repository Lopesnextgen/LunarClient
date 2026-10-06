package com.moonsworth.lunar.client.config.option;

import com.moonsworth.lunar.client.config.option.SettingsSectionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import org.jspecify.annotations.Nullable;

public class SettingsTreeAssembler extends SettingsTreeContainer<SettingsSectionImpl, SettingsTreeAssembler.Data> implements OptionBakerProvider<FeatureOptionBaker> {
   public SettingsTreeAssembler(SettingsGroupCreator<SettingsSectionImpl> var1) {
      super(var1, SettingsTreeAssembler.Data::new);
   }

   public FeatureOptionBaker method2(@Nullable String var1, boolean var2) {
      return var2 ? new FeatureOptionBaker(var1) : new DevelopmentOptionBaker(var1);
   }

   public static class Data extends SettingsParent<SettingsSectionImpl, SettingsTreeAssembler.Data> {
      public Data(SettingsComposer<SettingsSectionImpl, SettingsTreeAssembler.Data> var1, SettingsSectionBuilder<?> var2) {
         super(var1, var2);
      }
   }
}
