package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class BlockVariantIconMapping implements IconMapping<ItemStackBridge, ItemStackBridge> {
   private final List<String> field1;

   public BlockVariantIconMapping(String... items1) {
      this.field1 = Arrays.asList(items1);
   }

   @NotNull
   @Override
   public String method4() {
      return this.field1.get(0);
   }

   protected String method2() {
      return this.method4();
   }

   public boolean method3(@NotNull ItemStackBridge bridgeextension_41) {
      String text2 = bridgeextension_41.bridge$getItemRegistryName();
      if (("minecraft:" + this.method2()).equals(text2)) {
         return true;
      }

      for (String text4 : this.field1) {
         if (("minecraft:" + text4).equals(text2)) {
            return true;
         }
      }

      return false;
   }

   @NotNull
   public Markers2_3 method4(@NotNull ItemStackBridge bridgeextension_41) {
      String text2 = bridgeextension_41.bridge$getItemRegistryName();
      if (Ref.MC_VERSION > 5) {
         return ("minecraft:" + this.method2()).equals(text2) ? Markers2_3.method2() : Markers2_3.method3(text2.split(":")[1]);
      }

      int index3 = bridgeextension_41.bridge$getItemDamage();
      return Markers2_3.method3(this.field1.get(index3));
   }

   @Override
   public Optional<ItemStackBridge> method7(@NotNull Markers2_3 markers2_31) {
      if (markers2_31.isEmpty()) {
         return ItemIconRegistry.method8("minecraft:" + this.method2());
      }

      if (Ref.MC_VERSION > 5) {
         return ItemIconRegistry.method8("minecraft:" + markers2_31.value());
      }

      ItemStackBridge bridgeextension_42 = ItemIconRegistry.method9("minecraft:" + this.method2());
      int number3 = this.field1.indexOf(markers2_31.value());
      if (number3 == -1) {
         return Optional.empty();
      }

      bridgeextension_42.bridge$setItemDamage(number3);
      return ItemIconRegistry.method7(bridgeextension_42);
   }

   @Generated
   public List<String> method6() {
      return this.field1;
   }
}
