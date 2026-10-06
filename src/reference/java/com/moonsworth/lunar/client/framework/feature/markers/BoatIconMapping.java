package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class BoatIconMapping implements IconMapping<BridgeExtension, EntityIconRegistry.Data> {
   public BoatIconMapping() {
   }

   @NotNull
   @Override
   public String method4() {
      return "boat";
   }

   public boolean method2(@NotNull BridgeExtension bridgeextension1) {
      return bridgeextension1.bridge$isBoat();
   }

   @NotNull
   public Markers2_3 method3(@NotNull BridgeExtension bridgeextension1) {
      return bridgeextension1.bridge$isBoat() && Ref.MC_VERSION >= 5
         ? Markers2_3.method3((String)bridgeextension1.bridge$getBoatType().orElse(null))
         : Markers2_3.method2();
   }

   @Override
   public Optional<EntityIconRegistry.Data> method7(@NotNull Markers2_3 markers2_31) {
      if (Ref.MC_VERSION > 23) {
         String text2 = markers2_31.method1("oak");
         String text3 = "minecraft:%s_boat".formatted(text2);
         return EntityIconRegistry.Data.method1(new Markers3_2(text3, Markers2_3.method2()));
      } else {
         return Ref.MC_VERSION > 5
            ? EntityIconRegistry.Data.method1(new Markers3_2("minecraft:boat", Markers2_3.method2()))
            : EntityIconRegistry.Data.method1(new Markers3_2("Boat", Markers2_3.method2()));
      }
   }
}
