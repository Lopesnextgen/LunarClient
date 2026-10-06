package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.client.framework.mod.Nameplate3;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.config.option.trait.TraitType;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public interface AlertExtension extends com.moonsworth.lunar.client.config.override.AlertExtension<Framework7Extension> {
   default TraitType<AlertExtension> method1() {
      return Framework.field5;
   }

   Map<SettingsPage, List<Framework7Extension>> method2();

   static AlertExtension method3() {
      return new Nameplate3(null);
   }

   static AlertExtension method5(Predicate<String> predicate) {
      return new Nameplate3(predicate);
   }
}
