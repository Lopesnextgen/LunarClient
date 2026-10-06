package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import com.moonsworth.lunar.client.util.ThreadModuleDump63;

public class Fishing3Impl9 extends Fishing3_3 {
   @Override
   public String getCommand() {
      return "fps";
   }

   @Override
   public boolean isEnabledByDefault() {
      return false;
   }

   @Override
   public void method6(String var1, String var2, String[] var3, Gui2Extension var4) {
      this.method6(var4, "FPS: " + ThreadModuleDump63.method3().bridge$getDebugFPS());
   }
}
