package com.moonsworth.lunar.client.ui.mainmenu;

import java.util.Random;
import java.util.Set;
import java.util.function.BiPredicate;
import lombok.Generated;

public class MainMenuThemeId {
   private final String field1;

   public void method1(Set<String> var1, Random var2) {
      var1.add(this.field1);
   }

   public static MainMenuThemeId method2(String var0) {
      return new MainMenuThemeId(var0);
   }

   public static MainMenuThemeId method3(String var0, BiPredicate<Random, Set<String>> var1) {
      return new ThemeCondition(var0, var1);
   }

   @Generated
   private MainMenuThemeId(String var1) {
      this.field1 = var1;
   }
}
