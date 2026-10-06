package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class Markers5 implements SExtension_2<ItemStackBridge, ItemStackBridge> {
   private static final List<String> field1 = Arrays.asList("skeleton", "wither_skeleton", "zombie", "player", "creeper", "dragon");

   @NotNull
   @Override
   public String method4() {
      return "skulls";
   }

   public boolean method2(@NotNull ItemStackBridge var1) {
      return var1.bridge$getItem().bridge$isItemSkull();
   }

   @NotNull
   public Markers2_3 method3(@NotNull ItemStackBridge var1) {
      if (ThreadModuleDump63.MC_VERSION > 5) {
         return SIterator_2.method5(var1).map(var0 -> {
            if (var0.contains("_wall_")) {
               var0 = var0.replace("_wall_", "");
            }

            if (var0.contains("_skull")) {
               return Markers2_3.method3(var0.replace("_skull", ""));
            } else {
               return var0.contains("_head") ? Markers2_3.method3(var0.replace("_head", "")) : Markers2_3.method2();
            }
         }).orElse(Markers2_3.method2());
      }

      int var2 = var1.bridge$getItemDamage();
      if (var2 >= field1.size()) {
         return Markers2_3.method2();
      }

      String var3 = field1.get(var2);
      return Markers2_3.method3(var3);
   }

   @Override
   public Optional<ItemStackBridge> method7(@NotNull Markers2_3 var1) {
      String var2 = var1.method1("player");
      if (ThreadModuleDump63.MC_VERSION > 5) {
         return !"skeleton".equals(var2) && !"wither_skeleton".equals(var2)
            ? SIterator_2.method8("minecraft:" + var2 + "_head")
            : SIterator_2.method8("minecraft:" + var2 + "_skull");
      }

      ItemStackBridge var3 = SIterator_2.method9("minecraft:skull");
      int var4 = field1.indexOf(var2);
      if (var4 != -1) {
         var3.bridge$setItemDamage(var4);
      }

      return SIterator_2.method7(var3);
   }
}
