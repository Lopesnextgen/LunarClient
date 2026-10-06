package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import java.util.List;

public class Fishing3Impl5 extends Fishing3_3 {
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
   public void method6(String text, String text2, String[] items, Gui2Extension gui2) {
      String var5 = items.length >= 1 ? items[0] : text2;
      this.method7("/p invite " + var5);
   }
}
