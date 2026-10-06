package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import java.util.Set;

public class ModCategorySet implements ModCategories {
   private final Set<SettingsPage> field1;

   public ModCategorySet(SettingsPage rewindhandlersType) {
      this.field1 = Set.of(rewindhandlersType);
   }

   public ModCategorySet(SettingsPage[] items1) {
      this.field1 = Set.of(items1);
   }

   @Override
   public Set<SettingsPage> method1() {
      return this.field1;
   }
}
