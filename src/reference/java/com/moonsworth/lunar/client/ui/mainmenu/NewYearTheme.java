package com.moonsworth.lunar.client.ui.mainmenu;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.List;

public class NewYearTheme implements MainMenuTheme {
   @Override
   public ResourceLocationBridge method1() {
      return ResourceLocationBridge.create("lunar", "backgrounds/new_year/splash.png");
   }

   @Override
   public ResourceLocationBridge method2() {
      return ResourceLocationBridge.create("lunar", "backgrounds/new_year/panorama");
   }

   @Override
   public String name() {
      return "new_year";
   }

   @Override
   public List<String> additions() {
      return List.of("fireworks", "countdown");
   }
}
