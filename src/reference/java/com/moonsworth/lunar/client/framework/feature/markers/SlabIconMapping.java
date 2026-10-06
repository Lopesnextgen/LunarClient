package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class SlabIconMapping implements IconMapping<ItemStackBridge, ItemStackBridge> {
   private static final BlockVariantIconMapping field1 = new BlockVariantIconMapping(
      "stone_slab", "sandstone_slab", "wood_old", "cobblestone_slab", "brick_slab", "stone_brick_slab", "nether_brick_slab", "quartz_slab"
   );
   private static final WoodTypeIconMapping field2 = new WoodTypeIconMapping("slab");
   private static final WoodTypeIconMapping field3 = new WoodTypeIconMapping("wooden_slab");
   private static final WoodenBlockIconMapping field4 = new WoodenBlockIconMapping("slab");

   public SlabIconMapping() {
   }

   @NotNull
   @Override
   public String method4() {
      return "slabs";
   }

   public boolean method2(@NotNull ItemStackBridge bridgeextension_41) {
      String text2 = bridgeextension_41.bridge$getItemRegistryName();
      return text2.endsWith("_slab") || text2.endsWith("_slab2");
   }

   @NotNull
   public Markers2_3 method3(@NotNull ItemStackBridge bridgeextension_41) {
      String text2 = bridgeextension_41.bridge$getItemRegistryName();
      if (text2.endsWith("_slab2")) {
         return Markers2_3.method3("red_sandstone_slab");
      }

      Optional optional3 = ItemIconRegistry.method5(bridgeextension_41);
      if ((!optional3.isPresent() || !WoodTypeIconMapping.field1.containsKey(((String)optional3.get()).replace("_slab", ""))) && !"minecraft:wooden_slab".equals(text2)) {
         return field1.method4(bridgeextension_41);
      }

      Markers2_3 markers2_34 = field2.method5(bridgeextension_41);
      return markers2_34.isEmpty() ? Markers2_3.method2() : Markers2_3.method3("wood~" + markers2_34.value().replace("_slab", ""));
   }

   @Override
   public Optional<ItemStackBridge> method7(@NotNull Markers2_3 markers2_31) {
      if ("red_sandstone_slab".equals(markers2_31.value())) {
         if (Ref.MC_VERSION > 5) {
            return ItemIconRegistry.method8("minecraft:red_sandstone_slab");
         }

         ItemStackBridge bridgeextension_44 = ItemIconRegistry.method9("minecraft:stone_slab2");
         int number3 = field1.method6().indexOf(markers2_31.value());
         if (number3 != -1) {
            bridgeextension_44.bridge$setItemDamage(number3);
         }

         return ItemIconRegistry.method7(bridgeextension_44);
      } else if (!markers2_31.isEmpty() && markers2_31.value().startsWith("wood~")) {
         Markers2_3 markers2_32 = Markers2_3.method3(markers2_31.value().replace("wood~", ""));
         return Ref.MC_VERSION > 5 ? field4.method7(markers2_32) : field3.method1(markers2_32);
      } else {
         return field1.method7(markers2_31);
      }
   }
}
