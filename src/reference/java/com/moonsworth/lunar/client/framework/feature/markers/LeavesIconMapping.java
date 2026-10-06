package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class LeavesIconMapping extends SuffixIconMapping {
   public LeavesIconMapping() {
   }

   @NotNull
   @Override
   public String method4() {
      return "leaves";
   }

   @Override
   public boolean method4(@NotNull ItemStackBridge bridgeextension_41) {
      String text2 = bridgeextension_41.bridge$getItemRegistryName();
      return text2.startsWith("minecraft:") && text2.endsWith("_leaves") || text2.equals("minecraft:leaves") || text2.equals("minecraft:leaves2");
   }

   @Override
   protected String method1(int number1, ItemStackBridge bridgeextension_42) {
      int number3 = bridgeextension_42.bridge$getItemDamage();
      if (bridgeextension_42.bridge$getItemRegistryName().endsWith("leaves2")) {
         number3 += 4;
      }

      return (String)WoodTypeIconMapping.field1.inverse().getOrDefault(number3, "oak");
   }

   @Override
   protected int method2(String text1) {
      return (Integer)WoodTypeIconMapping.field1.getOrDefault(text1, 0);
   }

   @Override
   protected String method3() {
      return "oak";
   }

   @Override
   public Optional<ItemStackBridge> method7(@NotNull Markers2_3 markers2_31) {
      if (Ref.MC_VERSION > 5) {
         return super.method7(markers2_31);
      }

      int number2 = this.method2(markers2_31.value());
      String text3 = "minecraft:leaves";
      if (number2 > 3) {
         text3 = text3 + "2";
         number2 -= 4;
      }

      ItemStackBridge bridgeextension_44 = ItemIconRegistry.method9(text3);
      bridgeextension_44.bridge$setItemDamage(number2);
      return ItemIconRegistry.method7(bridgeextension_44);
   }
}
