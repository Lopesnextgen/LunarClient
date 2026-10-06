package com.moonsworth.lunar.client.config.option;

import com.moonsworth.lunar.client.config.option.SettingsSectionBuilder;
import java.util.function.Function;
import com.moonsworth.lunar.client.config.option.ClientOption;

@FunctionalInterface
public interface SettingsGroupFactory<Builder extends SettingsSectionBuilder<Builder>> extends Function<ClientOption<?>[], Builder> {
   Builder apply(ClientOption<?>... items1);
}
