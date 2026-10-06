package com.moonsworth.lunar.client.config.option;

import com.moonsworth.lunar.client.config.option.SettingsSectionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import org.jspecify.annotations.Nullable;

public class RootSettingsAssembler extends SettingsTreeContainer<SettingsSectionImpl, RootSettingsAssembler.Data> implements OptionBakerProvider<FeatureOptionBaker> {
   public RootSettingsAssembler(SettingsGroupCreator<SettingsSectionImpl> var1) {
      super(var1, RootSettingsAssembler.Data::new);
   }

   public FeatureOptionBaker method2(@Nullable String var1, boolean var2) {
      return var2 ? new FeatureOptionBaker(var1) : new DevelopmentOptionBaker(var1);
   }

   public static class Data extends SettingsParent<SettingsSectionImpl, RootSettingsAssembler.Data> {
      public Data(SettingsComposer<SettingsSectionImpl, RootSettingsAssembler.Data> var1, SettingsSectionBuilder<?> var2) {
         super(var1, var2);
      }
   }
}
