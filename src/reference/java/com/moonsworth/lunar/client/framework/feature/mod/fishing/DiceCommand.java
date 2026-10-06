package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DiceCommand extends SkyBlockChatCommand {
   public DiceCommand() {
   }

   @Override
   public String getCommand() {
      return "dice";
   }

   @Override
   public List<String> getAliases() {
      return List.of("roll");
   }

   @Override
   public boolean isEnabledByDefault() {
      return false;
   }

   @Override
   public void method6(String text1, String text2, String[] items3, FishingChatType gui2extension4) {
      int number5 = 6;
      if (items3.length >= 1) {
         try {
            number5 = Integer.parseInt(items3[0]);
         } catch (NumberFormatException numberformatexception7) {
         }
      }

      int number6 = ThreadLocalRandom.current().nextInt(number5) + 1;
      this.method6(gui2extension4, String.valueOf(number6));
   }
}
