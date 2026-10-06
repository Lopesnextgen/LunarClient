package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class BannerIconMapping implements IconMapping<ItemStackBridge, ItemStackBridge> {
   public BannerIconMapping() {
   }

   @NotNull
   @Override
   public String method4() {
      return "banners";
   }

   public boolean method2(@NotNull ItemStackBridge bridgeextension_41) {
      String text2 = bridgeextension_41.bridge$getItemRegistryName();
      return "minecraft:banner".equals(text2)
         || "minecraft:wall_banner".equals(text2)
         || "minecraft:standing_banner".equals(text2)
         || text2.endsWith("_banner")
         || text2.endsWith("_wall_banner")
         || text2.endsWith("_standing_banner");
   }

   @NotNull
   public Markers2_3 method3(@NotNull ItemStackBridge bridgeextension_41) {
      return Markers2_3.method3((String)bridgeextension_41.bridge$getBannerColor().orElse(null));
   }

   @Override
   public Optional<ItemStackBridge> method7(@NotNull Markers2_3 markers2_31) {
      if (Ref.MC_VERSION > 5) {
         Optional optional3 = ItemIconRegistry.method8("minecraft:%s_banner".formatted(markers2_31.value()));
         return optional3.isPresent() ? optional3 : ItemIconRegistry.method8("minecraft:white_banner");
      } else if (Ref.MC_VERSION > 0) {
         ItemStackBridge bridgeextension_42 = ItemIconRegistry.method9("minecraft:banner");
         bridgeextension_42.bridge$setBannerColor(markers2_31.value());
         return ItemIconRegistry.method7(bridgeextension_42);
      } else {
         return Optional.empty();
      }
   }
}
