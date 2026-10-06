package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import com.moonsworth.lunar.bridge.Bridge3_19;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;

public class Fishing3Impl8 extends Fishing3_3 {
   @Override
   public String getCommand() {
      return "ping";
   }

   @Override
   public boolean isEnabledByDefault() {
      return false;
   }

   @Override
   public void method6(String var1, String var2, String[] var3, Gui2Extension var4) {
      Bridge3_19 var5 = ThreadModuleDump63.method3().bridge$getCurrentServerData();
      if (var5 != null) {
         this.method6(var4, "Ping: " + var5.bridge$getPingToServer() + "ms");
      }
   }
}
