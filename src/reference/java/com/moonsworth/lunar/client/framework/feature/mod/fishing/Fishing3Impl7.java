package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import java.util.List;

public class Fishing3Impl7 extends Fishing3_3 {
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
   public void method6(String text, String text2, String[] items, Gui2Extension gui2) {
      this.method7("/p warp");
   }
}
