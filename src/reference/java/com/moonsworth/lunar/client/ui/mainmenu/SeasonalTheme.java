package com.moonsworth.lunar.client.ui.mainmenu;

import java.time.LocalDateTime;
import java.time.Month;

public interface SeasonalTheme<T extends MainMenuTheme> {
   default boolean method1(LocalDateTime var1, Month var2) {
      return true;
   }

   default boolean method2() {
      LocalDateTime var1 = LocalDateTime.now();
      Month var2 = var1.getMonth();
      return this.method1(var1, var2);
   }

   T method3();
}
