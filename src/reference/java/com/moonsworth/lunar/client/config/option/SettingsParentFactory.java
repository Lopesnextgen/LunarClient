package com.moonsworth.lunar.client.config.option;

import com.moonsworth.lunar.client.config.option.SettingsSectionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsParent;

@FunctionalInterface
public interface SettingsParentFactory<Setting extends SettingsSectionBuilder<Setting>, Parent extends SettingsParent<Setting, Parent>> {
   Parent apply(SettingsBuilder<Setting, Parent> lighting_51, SettingsSectionBuilder<?> threadmoduledump43extension2);
}
