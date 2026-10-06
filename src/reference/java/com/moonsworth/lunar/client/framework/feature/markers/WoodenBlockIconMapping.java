package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Optional;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class WoodenBlockIconMapping implements IconMapping<ItemStackBridge, ItemStackBridge> {
   private final String field1;

   @NotNull
   @Override
   public String method4() {
      return this.field1;
   }

   public boolean method2(@NotNull ItemStackBridge bridgeextension_41) {
      String text2 = bridgeextension_41.bridge$getItemRegistryName();
      return text2.endsWith("_" + this.field1);
   }

   @NotNull
   public Markers2_3 method3(@NotNull ItemStackBridge bridgeextension_41) {
      return ItemIconRegistry.method5(bridgeextension_41).map(arg1x -> {
         arg1x = arg1x.replace("_" + this.field1, "");
         if ("wooden".equals(arg1x)) {
            arg1x = "oak";
         }

         return Markers2_3.method3(arg1x);
      }).orElse(Markers2_3.method2());
   }

   @Override
   public Optional<ItemStackBridge> method7(@NotNull Markers2_3 markers2_31) {
      String text2 = markers2_31.method1("oak");
      if (Ref.MC_VERSION > 5) {
         Optional optional4 = ItemIconRegistry.method8("minecraft:" + text2 + "_" + this.field1);
         return optional4.isPresent() ? optional4 : ItemIconRegistry.method8("minecraft:oak_" + this.field1);
      }

      if ("oak".equals(text2)) {
         text2 = "wooden";
      }

      Optional optional3 = ItemIconRegistry.method8("minecraft:" + text2 + "_" + this.field1);
      return optional3.isPresent() ? optional3 : ItemIconRegistry.method8("minecraft:wooden_" + this.field1);
   }

   @Generated
   public WoodenBlockIconMapping(String text1) {
      this.field1 = text1;
   }
}
