package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Optional;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class FenceIconMapping implements IconMapping<ItemStackBridge, ItemStackBridge> {
   private final String field1;

   @NotNull
   @Override
   public String method4() {
      return this.field1;
   }

   public boolean method2(@NotNull ItemStackBridge bridgeextension_41) {
      String text2 = bridgeextension_41.bridge$getItemRegistryName();
      return text2.startsWith("minecraft:") && text2.endsWith("_" + this.method4()) || text2.equals("minecraft:" + this.method4());
   }

   @NotNull
   public Markers2_3 method3(@NotNull ItemStackBridge bridgeextension_41) {
      if (Ref.MC_VERSION == 0) {
         return Markers2_3.method2();
      }

      Optional optional2 = ItemIconRegistry.method5(bridgeextension_41);
      return optional2.isPresent() && ((String)optional2.get()).contains("_") ? Markers2_3.method3((String)optional2.orElse(null)) : Markers2_3.method2();
   }

   @Override
   public Optional<ItemStackBridge> method7(@NotNull Markers2_3 markers2_31) {
      if (Ref.MC_VERSION > 5) {
         Optional optional3 = ItemIconRegistry.method8("minecraft:" + markers2_31.method1("oak_" + this.method4()));
         return optional3.isPresent() ? optional3 : ItemIconRegistry.method8("minecraft:oak_" + this.method4());
      }

      if (Ref.MC_VERSION == 0) {
         return ItemIconRegistry.method8("minecraft:" + this.method4());
      }

      Optional optional2 = ItemIconRegistry.method8("minecraft:" + markers2_31.method1(this.method4()));
      return optional2.isPresent() ? optional2 : ItemIconRegistry.method8("minecraft:" + this.method4());
   }

   @Generated
   public FenceIconMapping(String text1) {
      this.field1 = text1;
   }
}
