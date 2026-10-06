package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public abstract class SuffixIconMapping implements IconMapping<ItemStackBridge, ItemStackBridge> {
   public SuffixIconMapping() {
   }

   protected abstract String method1(int number1, ItemStackBridge bridgeextension_42);

   protected abstract int method2(String text1);

   protected abstract String method3();

   public boolean method4(@NotNull ItemStackBridge bridgeextension_41) {
      String text2 = bridgeextension_41.bridge$getItemRegistryName();
      return text2.startsWith("minecraft:") && text2.endsWith("_" + this.ROHCRHCRIHHRIIHICHRORORIOHCHRC())
         || text2.equals("minecraft:" + this.ROHCRHCRIHHRIIHICHRORORIOHCHRC());
   }

   @NotNull
   public Markers2_3 method5(@NotNull ItemStackBridge bridgeextension_41) {
      if (Ref.MC_VERSION <= 5) {
         int number2 = bridgeextension_41.bridge$getItemDamage();
         return Markers2_3.method3(this.method1(number2, bridgeextension_41));
      } else {
         return ItemIconRegistry.method5(bridgeextension_41)
            .map(arg1x -> Markers2_3.method3(arg1x.replace("_" + this.ROHCRHCRIHHRIIHICHRORORIOHCHRC(), "")))
            .orElse(Markers2_3.method2());
      }
   }

   @Override
   public Optional<ItemStackBridge> method7(@NotNull Markers2_3 markers2_31) {
      if (Ref.MC_VERSION > 5) {
         Optional optional4 = ItemIconRegistry.method8("minecraft:" + markers2_31.method1(this.method3()) + "_" + this.ROHCRHCRIHHRIIHICHRORORIOHCHRC());
         return optional4.isPresent() ? optional4 : ItemIconRegistry.method8("minecraft:" + this.method3() + "_" + this.ROHCRHCRIHHRIIHICHRORORIOHCHRC());
      } else {
         int number2 = this.method2(markers2_31.value());
         ItemStackBridge bridgeextension_43 = ItemIconRegistry.method9("minecraft:" + this.ROHCRHCRIHHRIIHICHRORORIOHCHRC());
         bridgeextension_43.bridge$setItemDamage(number2);
         return ItemIconRegistry.method7(bridgeextension_43);
      }
   }
}
