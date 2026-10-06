package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class HorseIconMapping implements IconMapping<BridgeExtension, EntityIconRegistry.Data> {
   public HorseIconMapping() {
   }

   @NotNull
   @Override
   public String method4() {
      return "horse";
   }

   public boolean method2(@NotNull BridgeExtension bridgeextension1) {
      String text2 = bridgeextension1.bridge$getEntityString();
      return "minecraft:horse".equals(text2) || "Horse".equals(text2) || "EntityHorse".equals(text2);
   }

   @NotNull
   public Markers2_3 method3(@NotNull BridgeExtension bridgeextension1) {
      return EntityIconRegistry.method5(bridgeextension1);
   }

   @Override
   public Optional<EntityIconRegistry.Data> method7(@NotNull Markers2_3 markers2_31) {
      String text2 = Ref.MC_VERSION > 5 ? "minecraft:horse" : (Ref.MC_VERSION == 5 ? "Horse" : "EntityHorse");
      return EntityIconRegistry.Data.method1(new Markers3_2(text2, markers2_31));
   }
}
