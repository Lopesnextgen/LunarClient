package com.moonsworth.lunar.client.config.migration;

import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.config.option.ClientOption;
import java.util.function.Predicate;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class VersionedIdResolver implements ConfigIdResolver {
   private int field1;

   public VersionedIdResolver() {
   }

   @Override
   public String method1(Framework7Extension framework7) {
      return this.method2(framework7.getId(), framework7, arg0 -> true);
   }

   @Override
   public String method2(String text1, @Nullable Framework7Extension framework7extension2, Predicate<String> predicate3) {
      return ConfigMigrator.method7(text1, framework7extension2, this.field1, predicate3);
   }

   @Override
   public String method3(ClientOption<?> option) {
      return this.method4(option.getId(), option, arg0 -> true);
   }

   @Override
   public String method4(String text1, @Nullable ClientOption<?> lightingextension2, Predicate<String> predicate3) {
      return ConfigMigrator.method10(text1, lightingextension2, this.field1, predicate3);
   }

   @Generated
   public int method5() {
      return this.field1;
   }

   @Generated
   public void method6(int value) {
      this.field1 = value;
   }
}
