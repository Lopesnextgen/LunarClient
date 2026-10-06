package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.client.framework.mod.ModCategorySet;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import java.util.Set;

public interface ModCategories {
   Set<SettingsPage> method1();

   static ModCategories method2(SettingsPage rewindhandlersType) {
      return new ModCategorySet(rewindhandlersType);
   }

   static ModCategories method3(SettingsPage... items0) {
      return new ModCategorySet(items0);
   }
}
