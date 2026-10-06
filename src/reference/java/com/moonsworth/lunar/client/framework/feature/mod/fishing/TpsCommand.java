package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import com.moonsworth.lunar.client.framework.Ref;

public class TpsCommand extends SkyBlockChatCommand {
   public TpsCommand() {
   }

   @Override
   public String getCommand() {
      return "tps";
   }

   @Override
   public boolean isEnabledByDefault() {
      return false;
   }

   @Override
   public void method6(String text1, String text2, String[] items3, FishingChatType gui2extension4) {
      String text5 = Ref.method4().method40().method82().method187().method14().method5();
      this.method6(gui2extension4, text5);
   }
}
