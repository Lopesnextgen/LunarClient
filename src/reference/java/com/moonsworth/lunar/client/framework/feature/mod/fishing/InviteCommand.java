package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import java.util.List;

public class InviteCommand extends SkyBlockChatCommand {
   public InviteCommand() {
   }

   @Override
   public String getCommand() {
      return "invite";
   }

   @Override
   public List<String> getAliases() {
      return List.of("inv");
   }

   @Override
   public boolean method2() {
      return true;
   }

   @Override
   public void method6(String text, String text2, String[] items3, FishingChatType fishingChatType) {
      String text5 = items3.length >= 1 ? items3[0] : text2;
      this.method7("/p invite " + text5);
   }
}
