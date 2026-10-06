package com.moonsworth.lunar.client.ui.mainmenu;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.event.EventRegistrar;
import java.util.List;

public interface MainMenuTheme extends EventRegistrar {
   default ResourceLocationBridge method1() {
      return null;
   }

   default ResourceLocationBridge method2() {
      return null;
   }

   default boolean method3() {
      return false;
   }

   default boolean method4() {
      return true;
   }

   default boolean method5() {
      return false;
   }

   String name();

   default List<String> additions() {
      return List.of();
   }
}
