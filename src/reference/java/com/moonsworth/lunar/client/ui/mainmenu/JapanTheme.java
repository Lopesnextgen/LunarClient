package com.moonsworth.lunar.client.ui.mainmenu;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.List;

public class JapanTheme implements MainMenuTheme {
   @Override
   public ResourceLocationBridge method1() {
      return ResourceLocationBridge.create("lunar", "backgrounds/japan/splash.png");
   }

   @Override
   public ResourceLocationBridge method2() {
      return ResourceLocationBridge.create("lunar", "backgrounds/japan/panorama");
   }

   @Override
   public String name() {
      return "japan";
   }

   @Override
   public List<String> additions() {
      return List.of("cherry-blossom");
   }
}
