package com.moonsworth.lunar.client.config.migration;

import com.google.gson.JsonPrimitive;
import com.moonsworth.lunar.config.Config;

public interface OptionMigration {
   boolean method1(Config config1);

   JsonPrimitive method2(String text1, JsonPrimitive json2);
}
