package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class Markers6 implements SExtension_2<ItemStackBridge, ItemStackBridge> {
   @NotNull
   @Override
   public String method4() {
      return "banners";
   }

   public boolean method2(@NotNull ItemStackBridge var1) {
      String var2 = var1.bridge$getItemRegistryName();
      return "minecraft:banner".equals(var2)
         || "minecraft:wall_banner".equals(var2)
         || "minecraft:standing_banner".equals(var2)
         || var2.endsWith("_banner")
         || var2.endsWith("_wall_banner")
         || var2.endsWith("_standing_banner");
   }

   @NotNull
   public Markers2_3 method3(@NotNull ItemStackBridge var1) {
      return Markers2_3.method3((String)var1.bridge$getBannerColor().orElse(null));
   }

   @Override
   public Optional<ItemStackBridge> method7(@NotNull Markers2_3 var1) {
      if (ThreadModuleDump63.MC_VERSION > 5) {
         Optional var3 = SIterator_2.method8("minecraft:%s_banner".formatted(var1.value()));
         return var3.isPresent() ? var3 : SIterator_2.method8("minecraft:white_banner");
      } else if (ThreadModuleDump63.MC_VERSION > 0) {
         ItemStackBridge var2 = SIterator_2.method9("minecraft:banner");
         var2.bridge$setBannerColor(var1.value());
         return SIterator_2.method7(var2);
      } else {
         return Optional.empty();
      }
   }
}
