package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CoinflipCommand extends SkyBlockChatCommand {
   public CoinflipCommand() {
   }

   @Override
   public String getCommand() {
      return "coinflip";
   }

   @Override
   public List<String> getAliases() {
      return List.of("cf");
   }

   @Override
   public boolean isEnabledByDefault() {
      return false;
   }

   @Override
   public void method6(String text1, String text2, String[] items3, FishingChatType gui2extension4) {
      String text5 = ThreadLocalRandom.current().nextBoolean() ? "Heads!" : "Tails!";
      this.method6(gui2extension4, text5);
   }
}
