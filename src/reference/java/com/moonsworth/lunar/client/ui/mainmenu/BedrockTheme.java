package com.moonsworth.lunar.client.ui.mainmenu;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;

public class BedrockTheme implements MainMenuTheme {
   @Override
   public ResourceLocationBridge method1() {
      return ResourceLocationBridge.create("lunar", "backgrounds/bedrock/splash.png");
   }

   @Override
   public ResourceLocationBridge method2() {
      return ResourceLocationBridge.create("lunar", "backgrounds/bedrock/panorama");
   }

   @Override
   public String name() {
      return "bedrock";
   }

   @Override
   public boolean method3() {
      return true;
   }

   @Override
   public boolean method4() {
      return false;
   }
}
