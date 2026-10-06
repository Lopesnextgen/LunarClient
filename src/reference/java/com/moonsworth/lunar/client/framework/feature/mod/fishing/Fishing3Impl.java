package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Fishing3Impl extends Fishing3_3 {
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
   public void method6(String var1, String var2, String[] var3, Gui2Extension var4) {
      String var5 = ThreadLocalRandom.current().nextBoolean() ? "Heads!" : "Tails!";
      this.method6(var4, var5);
   }
}
