package com.moonsworth.lunar.client.ui.mainmenu;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;

public class ClassicTheme implements MainMenuTheme {
   @Override
   public ResourceLocationBridge method1() {
      return ResourceLocationBridge.create("lunar", "backgrounds/classic/splash.png");
   }

   @Override
   public ResourceLocationBridge method2() {
      return ResourceLocationBridge.create("lunar", "backgrounds/classic/panorama");
   }

   @Override
   public String name() {
      return "classic";
   }
}
