package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class SExtension implements SExtension_2<BridgeExtension, SIterator2.Data> {
   @NotNull
   @Override
   public String method4() {
      return "horse";
   }

   public boolean method2(@NotNull BridgeExtension var1) {
      String var2 = var1.bridge$getEntityString();
      return "minecraft:horse".equals(var2) || "Horse".equals(var2) || "EntityHorse".equals(var2);
   }

   @NotNull
   public Markers2_3 method3(@NotNull BridgeExtension var1) {
      return SIterator2.method5(var1);
   }

   @Override
   public Optional<SIterator2.Data> method7(@NotNull Markers2_3 var1) {
      String var2 = ThreadModuleDump63.MC_VERSION > 5 ? "minecraft:horse" : (ThreadModuleDump63.MC_VERSION == 5 ? "Horse" : "EntityHorse");
      return SIterator2.Data.method1(new Markers3_2(var2, var1));
   }
}
