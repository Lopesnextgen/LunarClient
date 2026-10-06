package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import java.util.List;

public class PartyWarpCommand extends SkyBlockChatCommand {
   public PartyWarpCommand() {
   }

   @Override
   public String getCommand() {
      return "warp";
   }

   @Override
   public List<String> getAliases() {
      return List.of("w");
   }

   @Override
   public boolean method2() {
      return true;
   }

   @Override
   public void method6(String text, String text2, String[] items3, FishingChatType fishingChatType) {
      this.method7("/p warp");
   }
}
