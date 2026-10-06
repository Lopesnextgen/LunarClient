package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class Markers12Impl5 implements SExtension_2<ItemStackBridge, ItemStackBridge> {
   private static final Markers9 field1 = new Markers9(
      "stone_slab", "sandstone_slab", "wood_old", "cobblestone_slab", "brick_slab", "stone_brick_slab", "nether_brick_slab", "quartz_slab"
   );
   private static final Markers2_2 field2 = new Markers2_2("slab");
   private static final Markers2_2 field3 = new Markers2_2("wooden_slab");
   private static final Markers4 field4 = new Markers4("slab");

   @NotNull
   @Override
   public String method4() {
      return "slabs";
   }

   public boolean method2(@NotNull ItemStackBridge var1) {
      String var2 = var1.bridge$getItemRegistryName();
      return var2.endsWith("_slab") || var2.endsWith("_slab2");
   }

   @NotNull
   public Markers2_3 method3(@NotNull ItemStackBridge var1) {
      String var2 = var1.bridge$getItemRegistryName();
      if (var2.endsWith("_slab2")) {
         return Markers2_3.method3("red_sandstone_slab");
      }

      Optional var3 = SIterator_2.method5(var1);
      if ((!var3.isPresent() || !Markers2_2.field1.containsKey(((String)var3.get()).replace("_slab", ""))) && !"minecraft:wooden_slab".equals(var2)) {
         return field1.method4(var1);
      }

      Markers2_3 var4 = field2.method5(var1);
      return var4.isEmpty() ? Markers2_3.method2() : Markers2_3.method3("wood~" + var4.value().replace("_slab", ""));
   }

   @Override
   public Optional<ItemStackBridge> method7(@NotNull Markers2_3 var1) {
      if ("red_sandstone_slab".equals(var1.value())) {
         if (ThreadModuleDump63.MC_VERSION > 5) {
            return SIterator_2.method8("minecraft:red_sandstone_slab");
         }

         ItemStackBridge var4 = SIterator_2.method9("minecraft:stone_slab2");
         int var3 = field1.method6().indexOf(var1.value());
         if (var3 != -1) {
            var4.bridge$setItemDamage(var3);
         }

         return SIterator_2.method7(var4);
      } else if (!var1.isEmpty() && var1.value().startsWith("wood~")) {
         Markers2_3 var2 = Markers2_3.method3(var1.value().replace("wood~", ""));
         return ThreadModuleDump63.MC_VERSION > 5 ? field4.method7(var2) : field3.method1(var2);
      } else {
         return field1.method7(var1);
      }
   }
}
