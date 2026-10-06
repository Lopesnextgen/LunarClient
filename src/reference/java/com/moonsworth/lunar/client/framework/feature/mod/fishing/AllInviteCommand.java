package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import java.util.List;

public class AllInviteCommand extends SkyBlockChatCommand {
   public AllInviteCommand() {
   }

   @Override
   public String getCommand() {
      return "allinvite";
   }

   @Override
   public List<String> getAliases() {
      return List.of("allinv");
   }

   @Override
   public boolean method2() {
      return true;
   }

   @Override
   public void method6(String text, String text2, String[] items3, FishingChatType fishingChatType) {
      this.method7("/p settings allinvite");
   }
}
