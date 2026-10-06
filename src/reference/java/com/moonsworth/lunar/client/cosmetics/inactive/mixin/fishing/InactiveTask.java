package com.moonsworth.lunar.client.cosmetics.inactive.mixin.fishing;

import com.moonsworth.lunar.client.render.turbo.PathFilter;
import com.moonsworth.lunar.client.cosmetics.gecko.EmoteDefinition;

public interface InactiveTask {
   default boolean isCancellable() {
      return false;
   }

   boolean method1(EmoteDefinition inactive31, PathFilter holograms3handler2);

   boolean method2(EmoteDefinition inactive31, PathFilter holograms3handler2);

   void method3(EmoteDefinition inactive31, PathFilter holograms3handler2);

   default int method4() {
      return 0;
   }

   default int getDuration() {
      return 0;
   }

   void method6(EmoteDefinition inactive31, PathFilter holograms3handler2);
}
