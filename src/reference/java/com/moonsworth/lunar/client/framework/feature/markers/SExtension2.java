package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class SExtension2 implements SExtension_2<BridgeExtension, SIterator2.Data> {
   @NotNull
   @Override
   public String method4() {
      return "boat";
   }

   public boolean method2(@NotNull BridgeExtension var1) {
      return var1.bridge$isBoat();
   }

   @NotNull
   public Markers2_3 method3(@NotNull BridgeExtension var1) {
      return var1.bridge$isBoat() && ThreadModuleDump63.MC_VERSION >= 5
         ? Markers2_3.method3((String)var1.bridge$getBoatType().orElse(null))
         : Markers2_3.method2();
   }

   @Override
   public Optional<SIterator2.Data> method7(@NotNull Markers2_3 var1) {
      if (ThreadModuleDump63.MC_VERSION > 23) {
         String var2 = var1.method1("oak");
         String var3 = "minecraft:%s_boat".formatted(var2);
         return SIterator2.Data.method1(new Markers3_2(var3, Markers2_3.method2()));
      } else {
         return ThreadModuleDump63.MC_VERSION > 5
            ? SIterator2.Data.method1(new Markers3_2("minecraft:boat", Markers2_3.method2()))
            : SIterator2.Data.method1(new Markers3_2("Boat", Markers2_3.method2()));
      }
   }
}
