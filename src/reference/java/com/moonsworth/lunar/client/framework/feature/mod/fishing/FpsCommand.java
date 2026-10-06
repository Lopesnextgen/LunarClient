package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import com.moonsworth.lunar.client.framework.Ref;

public class FpsCommand extends SkyBlockChatCommand {
   public FpsCommand() {
   }

   @Override
   public String getCommand() {
      return "fps";
   }

   @Override
   public boolean isEnabledByDefault() {
      return false;
   }

   @Override
   public void method6(String text1, String text2, String[] items3, FishingChatType gui2extension4) {
      this.method6(gui2extension4, "FPS: " + Ref.method3().bridge$getDebugFPS());
   }
}
