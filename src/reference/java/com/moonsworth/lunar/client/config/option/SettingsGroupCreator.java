package com.moonsworth.lunar.client.config.option;

import com.moonsworth.lunar.client.config.option.SettingsSectionBuilder;
import java.util.function.Function;

@FunctionalInterface
public interface SettingsGroupCreator<Builder extends SettingsSectionBuilder<Builder>> extends Function<ClientOption<?>[], Builder> {
   Builder apply(ClientOption<?>... var1);
}
