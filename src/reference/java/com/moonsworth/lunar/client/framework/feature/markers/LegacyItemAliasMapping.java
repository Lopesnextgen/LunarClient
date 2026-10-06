package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import java.util.Optional;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class LegacyItemAliasMapping implements IconMapping<ItemStackBridge, ItemStackBridge> {
   private final String field1;
   private final String field2;

   @NotNull
   @Override
   public String method4() {
      return this.field2.split(":")[1];
   }

   public boolean method2(@NotNull ItemStackBridge bridgeextension_41) {
      String text2 = bridgeextension_41.bridge$getItemRegistryName();
      return this.field2.equals(text2) || this.field1.equals(text2);
   }

   @NotNull
   public Markers2_3 method3(@NotNull ItemStackBridge bridgeextension_41) {
      return Markers2_3.method2();
   }

   @Override
   public Optional<ItemStackBridge> method7(@NotNull Markers2_3 markers2_31) {
      return ItemIconRegistry.method8(this.field2);
   }

   @Generated
   public LegacyItemAliasMapping(String text1, String text2) {
      this.field1 = text1;
      this.field2 = text2;
   }
}
