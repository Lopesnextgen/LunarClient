package com.moonsworth.lunar.client.config.migration;

import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.config.option.ClientOption;
import java.util.function.Predicate;
import javax.annotation.Nullable;

public interface ConfigIdResolver {
   String method1(Framework7Extension framework7extension1);

   String method2(String text1, @Nullable Framework7Extension framework7extension2, Predicate<String> predicate3);

   String method3(ClientOption<?> lightingextension1);

   String method4(String text1, @Nullable ClientOption<?> lightingextension2, Predicate<String> predicate3);
}
