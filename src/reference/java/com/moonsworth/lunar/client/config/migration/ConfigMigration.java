package com.moonsworth.lunar.client.config.migration;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.config.option.ClientOption;
import javax.annotation.Nullable;

public interface ConfigMigration {
   void method1(ConfigIdResolver killsounds1, Object obj2, JsonObject json3);

   default String method2(String text1, @Nullable Framework7Extension framework7extension2) {
      return text1;
   }

   default String[] method3(String text1, @Nullable Framework7Extension framework7extension2) {
      return new String[]{text1};
   }

   default String method4(String text1, @Nullable ClientOption<?> lightingextension2) {
      return text1;
   }

   default String[] method5(String text1, @Nullable ClientOption<?> lightingextension2) {
      return new String[]{text1};
   }

   default String method6(String text1, String text2, @Nullable ClientOption<?> lightingextension3) {
      return text2;
   }

   default String method7(String text1, String text2, @Nullable ClientOption<?> lightingextension3) {
      return text2;
   }
}
