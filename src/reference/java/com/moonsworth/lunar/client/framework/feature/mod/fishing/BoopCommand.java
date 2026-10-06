package com.moonsworth.lunar.client.framework.feature.mod.fishing;

public class BoopCommand extends SkyBlockChatCommand {
   public BoopCommand() {
   }

   @Override
   public String getCommand() {
      return "boop";
   }

   @Override
   public boolean isEnabledByDefault() {
      return false;
   }

   @Override
   public void method6(String text, String text2, String[] items3, FishingChatType fishingChatType) {
      String text5 = items3.length >= 1 ? items3[0] : text2;
      this.method7("/boop " + text5);
   }
}
