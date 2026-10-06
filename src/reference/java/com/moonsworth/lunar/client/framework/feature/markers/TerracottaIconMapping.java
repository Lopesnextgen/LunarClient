package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class TerracottaIconMapping implements IconMapping<ItemStackBridge, ItemStackBridge> {
   public TerracottaIconMapping() {
   }

   @NotNull
   @Override
   public String method4() {
      return "terracota";
   }

   public boolean method2(@NotNull ItemStackBridge bridgeextension_41) {
      String text2 = bridgeextension_41.bridge$getItemRegistryName();
      return Ref.MC_VERSION <= 5 && text2.equals("minecraft:stained_hardened_clay")
         || !text2.endsWith("_glazed_terracotta") && text2.endsWith("_terracotta");
   }

   @NotNull
   public Markers2_3 method3(@NotNull ItemStackBridge bridgeextension_41) {
      if (Ref.MC_VERSION <= 5) {
         String text2 = (String)DyeColorIconMapping.field1.inverse().get(15 - bridgeextension_41.bridge$getItemDamage());
         return Markers2_3.method3(text2);
      } else {
         return ItemIconRegistry.method5(bridgeextension_41).map(arg0 -> Markers2_3.method3(arg0.replace("_terracotta", ""))).orElse(Markers2_3.method2());
      }
   }

   @Override
   public Optional<ItemStackBridge> method7(@NotNull Markers2_3 markers2_31) {
      if (Ref.MC_VERSION > 5) {
         String text4 = "minecraft:" + (markers2_31.isEmpty() ? "terracotta" : markers2_31.value() + "_terracotta");
         Optional optional3 = ItemIconRegistry.method8(text4);
         return optional3.isPresent() ? optional3 : ItemIconRegistry.method8("minecraft:terracotta");
      }

      ItemStackBridge bridgeextension_42 = ItemIconRegistry.method9("minecraft:stained_hardened_clay");
      if (!markers2_31.isEmpty()) {
         bridgeextension_42.bridge$setItemDamage(15 - (Integer)DyeColorIconMapping.field1.get(markers2_31.value()));
      }

      return ItemIconRegistry.method7(bridgeextension_42);
   }
}
