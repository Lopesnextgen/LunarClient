package com.moonsworth.lunar.client.config.option;

import com.moonsworth.lunar.client.config.option.SettingsSectionBuilder;

@FunctionalInterface
public interface SettingsParentCreator<Setting extends SettingsSectionBuilder<Setting>, Parent extends SettingsParent<Setting, Parent>> {
   Parent apply(SettingsComposer<Setting, Parent> var1, SettingsSectionBuilder<?> var2);
}
