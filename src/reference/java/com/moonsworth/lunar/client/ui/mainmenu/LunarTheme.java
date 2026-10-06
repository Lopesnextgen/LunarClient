package com.moonsworth.lunar.client.ui.mainmenu;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;

public class LunarTheme implements MainMenuTheme {
   private static final ResourceLocationBridge field1 = ResourceLocationBridge.create("lunar", "backgrounds/splash.png");
   private static final ResourceLocationBridge field2 = ResourceLocationBridge.create("lunar", "backgrounds/panorama");

   @Override
   public ResourceLocationBridge method1() {
      return field1;
   }

   @Override
   public ResourceLocationBridge method2() {
      return field2;
   }

   @Override
   public String name() {
      return "lunar";
   }
}
