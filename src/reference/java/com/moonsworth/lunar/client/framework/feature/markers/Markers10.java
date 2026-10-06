package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Optional;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class Markers10 implements SExtension_2<ItemStackBridge, ItemStackBridge> {
   private final String field1;

   @NotNull
   @Override
   public String method4() {
      return this.field1;
   }

   public boolean method2(@NotNull ItemStackBridge var1) {
      String var2 = var1.bridge$getItemRegistryName();
      return var2.startsWith("minecraft:") && var2.endsWith("_" + this.method4()) || var2.equals("minecraft:" + this.method4());
   }

   @NotNull
   public Markers2_3 method3(@NotNull ItemStackBridge var1) {
      if (ThreadModuleDump63.MC_VERSION == 0) {
         return Markers2_3.method2();
      }

      Optional var2 = SIterator_2.method5(var1);
      return var2.isPresent() && ((String)var2.get()).contains("_") ? Markers2_3.method3((String)var2.orElse(null)) : Markers2_3.method2();
   }

   @Override
   public Optional<ItemStackBridge> method7(@NotNull Markers2_3 var1) {
      if (ThreadModuleDump63.MC_VERSION > 5) {
         Optional var3 = SIterator_2.method8("minecraft:" + var1.method1("oak_" + this.method4()));
         return var3.isPresent() ? var3 : SIterator_2.method8("minecraft:oak_" + this.method4());
      }

      if (ThreadModuleDump63.MC_VERSION == 0) {
         return SIterator_2.method8("minecraft:" + this.method4());
      }

      Optional var2 = SIterator_2.method8("minecraft:" + var1.method1(this.method4()));
      return var2.isPresent() ? var2 : SIterator_2.method8("minecraft:" + this.method4());
   }

   @Generated
   public Markers10(String var1) {
      this.field1 = var1;
   }
}
