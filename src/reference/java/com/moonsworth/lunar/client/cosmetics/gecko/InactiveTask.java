package com.moonsworth.lunar.client.cosmetics.gecko;

import com.moonsworth.lunar.client.render.turbo.PathFilter;
import com.moonsworth.lunar.client.cosmetics.gecko.EmoteDefinition;

public interface InactiveTask {
   default boolean isCancellable() {
      return false;
   }

   boolean method1(EmoteDefinition var1, PathFilter var2);

   boolean method2(EmoteDefinition var1, PathFilter var2);

   void method3(EmoteDefinition var1, PathFilter var2);

   default int method4() {
      return 0;
   }

   default int getDuration() {
      return 0;
   }

   void method6(EmoteDefinition var1, PathFilter var2);
}
