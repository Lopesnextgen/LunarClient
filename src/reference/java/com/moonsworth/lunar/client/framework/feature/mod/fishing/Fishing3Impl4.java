package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;

public class Fishing3Impl4 extends Fishing3_3 {
   @Override
   public String getCommand() {
      return "coords";
   }

   @Override
   public List<String> getAliases() {
      return List.of("pos");
   }

   @Override
   public boolean isEnabledByDefault() {
      return false;
   }

   @Override
   public void method6(String var1, String var2, String[] var3, Gui2Extension var4) {
      this.method6(
         var4,
         "x: "
            + ThreadModuleDump63.method7().bridge$getBlockX()
            + ", y: "
            + ThreadModuleDump63.method7().bridge$getBlockY()
            + ", z: "
            + ThreadModuleDump63.method7().bridge$getBlockZ()
      );
   }
}
