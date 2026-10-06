package com.moonsworth.lunar.client.config.option;

import org.jspecify.annotations.Nullable;

public interface OptionBakerFactory<B extends SettingsTreeMapper<?, ?, ?>> {
   B method1(@Nullable String text1, boolean flag2);
}
