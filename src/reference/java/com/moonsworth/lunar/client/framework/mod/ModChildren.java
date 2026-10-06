package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.client.framework.mod.FeatureChildren;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.config.option.trait.TraitType;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public interface ModChildren extends com.moonsworth.lunar.client.config.override.AlertExtension<Framework7Extension> {
   default TraitType<ModChildren> method1() {
      return ModTraits.field5;
   }

   Map<SettingsPage, List<Framework7Extension>> method2();

   static ModChildren method3() {
      return new FeatureChildren(null);
   }

   static ModChildren method5(Predicate<String> predicate0) {
      return new FeatureChildren(predicate0);
   }
}
