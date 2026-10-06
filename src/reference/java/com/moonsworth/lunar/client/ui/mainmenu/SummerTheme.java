package com.moonsworth.lunar.client.ui.mainmenu;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;

public class SummerTheme implements MainMenuTheme {
   @Override
   public ResourceLocationBridge method1() {
      return ResourceLocationBridge.create("lunar", "backgrounds/spring/splash.png");
   }

   @Override
   public ResourceLocationBridge method2() {
      return ResourceLocationBridge.create("lunar", "backgrounds/spring/panorama");
   }

   @Override
   public String name() {
      return "summer";
   }
}
