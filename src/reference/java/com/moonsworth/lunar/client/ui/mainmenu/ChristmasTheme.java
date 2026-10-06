package com.moonsworth.lunar.client.ui.mainmenu;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.List;

public class ChristmasTheme implements MainMenuTheme {
   @Override
   public ResourceLocationBridge method1() {
      return ResourceLocationBridge.create("lunar", "backgrounds/christmas/splash.png");
   }

   @Override
   public ResourceLocationBridge method2() {
      return ResourceLocationBridge.create("lunar", "backgrounds/christmas/panorama");
   }

   @Override
   public String name() {
      return "christmas";
   }

   @Override
   public List<String> additions() {
      return List.of("santaHat", "snow", "radio");
   }
}
