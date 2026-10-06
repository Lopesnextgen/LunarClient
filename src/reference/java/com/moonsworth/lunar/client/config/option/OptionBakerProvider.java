package com.moonsworth.lunar.client.config.option;

import org.jspecify.annotations.Nullable;

public interface OptionBakerProvider<B extends OptionTreeMapper<?, ?, ?>> {
   B method1(@Nullable String var1, boolean var2);
}
