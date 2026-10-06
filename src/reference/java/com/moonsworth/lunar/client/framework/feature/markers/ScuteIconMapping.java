package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class ScuteIconMapping implements IconMapping<ItemStackBridge, ItemStackBridge> {
   public ScuteIconMapping() {
   }

   @NotNull
   @Override
   public String method4() {
      return "scute";
   }

   public boolean method2(@NotNull ItemStackBridge bridgeextension_41) {
      String text2 = bridgeextension_41.bridge$getItemRegistryName();
      return text2.startsWith("minecraft:") && text2.endsWith("_scute") || text2.equals("minecraft:scute");
   }

   @NotNull
   public Markers2_3 method3(@NotNull ItemStackBridge bridgeextension_41) {
      if (Ref.MC_VERSION >= 22) {
         String text2 = bridgeextension_41.bridge$getItemRegistryName();
         text2 = text2.split(":")[1];
         return Markers2_3.method3(text2);
      } else {
         return Markers2_3.method2();
      }
   }

   @Override
   public Optional<ItemStackBridge> method7(@NotNull Markers2_3 markers2_31) {
      String text2 = "minecraft:scute";
      if (Ref.MC_VERSION >= 22) {
         text2 = "minecraft:" + markers2_31.method1("turtle_scute");
      }

      return ItemIconRegistry.method8(text2);
   }
}
