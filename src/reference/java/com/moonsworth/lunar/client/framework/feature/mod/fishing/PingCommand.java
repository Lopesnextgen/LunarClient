package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import com.moonsworth.lunar.bridge.ServerDataBridge;
import com.moonsworth.lunar.client.framework.Ref;

public class PingCommand extends SkyBlockChatCommand {
   public PingCommand() {
   }

   @Override
   public String getCommand() {
      return "ping";
   }

   @Override
   public boolean isEnabledByDefault() {
      return false;
   }

   @Override
   public void method6(String text1, String text2, String[] items3, FishingChatType gui2extension4) {
      ServerDataBridge bridge3_195 = Ref.method3().bridge$getCurrentServerData();
      if (bridge3_195 != null) {
         this.method6(gui2extension4, "Ping: " + bridge3_195.bridge$getPingToServer() + "ms");
      }
   }
}
