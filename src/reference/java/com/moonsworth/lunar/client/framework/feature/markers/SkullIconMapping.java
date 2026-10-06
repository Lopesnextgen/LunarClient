package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class SkullIconMapping implements IconMapping<ItemStackBridge, ItemStackBridge> {
   private static final List<String> field1 = Arrays.asList("skeleton", "wither_skeleton", "zombie", "player", "creeper", "dragon");

   public SkullIconMapping() {
   }

   @NotNull
   @Override
   public String method4() {
      return "skulls";
   }

   public boolean method2(@NotNull ItemStackBridge bridgeextension_41) {
      return bridgeextension_41.bridge$getItem().bridge$isItemSkull();
   }

   @NotNull
   public Markers2_3 method3(@NotNull ItemStackBridge bridgeextension_41) {
      if (Ref.MC_VERSION > 5) {
         return ItemIconRegistry.method5(bridgeextension_41).map(arg0 -> {
            if (arg0.contains("_wall_")) {
               arg0 = arg0.replace("_wall_", "");
            }

            if (arg0.contains("_skull")) {
               return Markers2_3.method3(arg0.replace("_skull", ""));
            } else {
               return arg0.contains("_head") ? Markers2_3.method3(arg0.replace("_head", "")) : Markers2_3.method2();
            }
         }).orElse(Markers2_3.method2());
      }

      int index2 = bridgeextension_41.bridge$getItemDamage();
      if (index2 >= field1.size()) {
         return Markers2_3.method2();
      }

      String text3 = field1.get(index2);
      return Markers2_3.method3(text3);
   }

   @Override
   public Optional<ItemStackBridge> method7(@NotNull Markers2_3 markers2_31) {
      String text2 = markers2_31.method1("player");
      if (Ref.MC_VERSION > 5) {
         return !"skeleton".equals(text2) && !"wither_skeleton".equals(text2)
            ? ItemIconRegistry.method8("minecraft:" + text2 + "_head")
            : ItemIconRegistry.method8("minecraft:" + text2 + "_skull");
      }

      ItemStackBridge bridgeextension_43 = ItemIconRegistry.method9("minecraft:skull");
      int number4 = field1.indexOf(text2);
      if (number4 != -1) {
         bridgeextension_43.bridge$setItemDamage(number4);
      }

      return ItemIconRegistry.method7(bridgeextension_43);
   }
}
