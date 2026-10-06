package com.moonsworth.lunar.client.ui.mainmenu;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;

public class VanillaTheme implements MainMenuTheme {
   private static final ResourceLocationBridge field1 = ResourceLocationBridge.create("minecraft", "textures/gui/title/background/panorama");
   private static final ResourceLocationBridge field2 = ResourceLocationBridge.create("lunar", "backgrounds/splash.png");

   @Override
   public ResourceLocationBridge method1() {
      return field2;
   }

   @Override
   public ResourceLocationBridge method2() {
      return field1;
   }

   @Override
   public String name() {
      return "vanilla";
   }

   @Override
   public boolean method5() {
      return true;
   }
}
