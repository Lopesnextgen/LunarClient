package com.moonsworth.lunar.client.ui.mainmenu;

import java.util.Random;
import java.util.Set;
import java.util.function.BiPredicate;

public class ThemeCondition extends MainMenuThemeId {
   private final BiPredicate<Random, Set<String>> field2;

   private ThemeCondition(String var1, BiPredicate<Random, Set<String>> var2) {
      super(var1);
      this.field2 = var2;
   }

   @Override
   public void method1(Set<String> var1, Random var2) {
      if (this.field2.test(var2, var1)) {
         super.method1(var1, var2);
      }
   }
}
