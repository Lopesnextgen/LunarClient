package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import com.moonsworth.lunar.client.util.ThreadModuleDump63;

public class Fishing3Impl13 extends Fishing3_3 {
   @Override
   public String getCommand() {
      return "tps";
   }

   @Override
   public boolean isEnabledByDefault() {
      return false;
   }

   @Override
   public void method6(String var1, String var2, String[] var3, Gui2Extension var4) {
      String var5 = ThreadModuleDump63.method4().method40().method82().method187().method14().method5();
      this.method6(var4, var5);
   }
}
