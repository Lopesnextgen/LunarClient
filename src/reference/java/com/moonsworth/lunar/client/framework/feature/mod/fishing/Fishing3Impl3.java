package com.moonsworth.lunar.client.framework.feature.mod.fishing;

public class Fishing3Impl3 extends Fishing3_3 {
   @Override
   public String getCommand() {
      return "boop";
   }

   @Override
   public boolean isEnabledByDefault() {
      return false;
   }

   @Override
   public void method6(String text, String text2, String[] items, Gui2Extension gui2) {
      String var5 = items.length >= 1 ? items[0] : text2;
      this.method7("/boop " + var5);
   }
}
