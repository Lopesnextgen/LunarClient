package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Fishing3Impl6 extends Fishing3_3 {
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
   public void method6(String var1, String var2, String[] var3, Gui2Extension var4) {
      int var5 = 6;
      if (var3.length >= 1) {
         try {
            var5 = Integer.parseInt(var3[0]);
         } catch (NumberFormatException var7) {
         }
      }

      int var6 = ThreadLocalRandom.current().nextInt(var5) + 1;
      this.method6(var4, String.valueOf(var6));
   }
}
