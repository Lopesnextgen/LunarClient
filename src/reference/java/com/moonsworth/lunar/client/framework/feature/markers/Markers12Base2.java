package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class Markers12Base2 extends Markers12Base {
   @NotNull
   @Override
   public String method4() {
      return "leaves";
   }

   @Override
   public boolean method4(@NotNull ItemStackBridge var1) {
      String var2 = var1.bridge$getItemRegistryName();
      return var2.startsWith("minecraft:") && var2.endsWith("_leaves") || var2.equals("minecraft:leaves") || var2.equals("minecraft:leaves2");
   }

   @Override
   protected String method1(int var1, ItemStackBridge var2) {
      int var3 = var2.bridge$getItemDamage();
      if (var2.bridge$getItemRegistryName().endsWith("leaves2")) {
         var3 += 4;
      }

      return (String)Markers2_2.field1.inverse().getOrDefault(var3, "oak");
   }

   @Override
   protected int method2(String var1) {
      return (Integer)Markers2_2.field1.getOrDefault(var1, 0);
   }

   @Override
   protected String method3() {
      return "oak";
   }

   @Override
   public Optional<ItemStackBridge> method7(@NotNull Markers2_3 var1) {
      if (ThreadModuleDump63.MC_VERSION > 5) {
         return super.method7(var1);
      }

      int var2 = this.method2(var1.value());
      String var3 = "minecraft:leaves";
      if (var2 > 3) {
         var3 = var3 + "2";
         var2 -= 4;
      }

      ItemStackBridge var4 = SIterator_2.method9(var3);
      var4.bridge$setItemDamage(var2);
      return SIterator_2.method7(var4);
   }
}
