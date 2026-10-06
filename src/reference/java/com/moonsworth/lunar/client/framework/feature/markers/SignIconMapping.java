package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class SignIconMapping implements IconMapping<ItemStackBridge, ItemStackBridge> {
   public SignIconMapping() {
   }

   @NotNull
   @Override
   public String method4() {
      return "signs";
   }

   public boolean method2(@NotNull ItemStackBridge bridgeextension_41) {
      return bridgeextension_41.bridge$getItem().bridge$isItemSign();
   }

   @NotNull
   public Markers2_3 method3(@NotNull ItemStackBridge bridgeextension_41) {
      return Ref.MC_VERSION > 5 ? ItemIconRegistry.method5(bridgeextension_41).map(arg0 -> {
         if (arg0.contains("_hanging_sign")) {
            arg0 = arg0.replace("_hanging_sign", "");
            return Markers2_3.method3(arg0 + ":hanging");
         } else {
            arg0 = arg0.replace("_standing_sign", "");
            arg0 = arg0.replace("_wall_sign", "");
            arg0 = arg0.replace("_sign", "");
            return Markers2_3.method3(arg0);
         }
      }).orElse(Markers2_3.method2()) : Markers2_3.method2();
   }

   @Override
   public Optional<ItemStackBridge> method7(@NotNull Markers2_3 markers2_31) {
      String text2 = markers2_31.method1("oak");
      boolean flag3 = false;
      if (text2.endsWith(":hanging")) {
         text2 = text2.replace(":hanging", "");
         flag3 = true;
      }

      if (Ref.MC_VERSION <= 5) {
         return ItemIconRegistry.method8("minecraft:sign");
      }

      String text4 = text2;
      if (flag3 && Ref.MC_VERSION >= 15) {
         text4 = text4 + "_hanging_sign";
      } else {
         text4 = text4 + "_sign";
      }

      Optional optional5 = ItemIconRegistry.method8("minecraft:" + text4);
      return optional5.isPresent() ? optional5 : ItemIconRegistry.method8("minecraft:oak_sign");
   }
}
