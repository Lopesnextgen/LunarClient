package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class Markers9 implements SExtension_2<ItemStackBridge, ItemStackBridge> {
   private final List<String> field1;

   public Markers9(String... var1) {
      this.field1 = Arrays.asList(var1);
   }

   @NotNull
   @Override
   public String method4() {
      return this.field1.get(0);
   }

   protected String method2() {
      return this.method4();
   }

   public boolean method3(@NotNull ItemStackBridge var1) {
      String var2 = var1.bridge$getItemRegistryName();
      if (("minecraft:" + this.method2()).equals(var2)) {
         return true;
      }

      for (String var4 : this.field1) {
         if (("minecraft:" + var4).equals(var2)) {
            return true;
         }
      }

      return false;
   }

   @NotNull
   public Markers2_3 method4(@NotNull ItemStackBridge var1) {
      String var2 = var1.bridge$getItemRegistryName();
      if (ThreadModuleDump63.MC_VERSION > 5) {
         return ("minecraft:" + this.method2()).equals(var2) ? Markers2_3.method2() : Markers2_3.method3(var2.split(":")[1]);
      }

      int var3 = var1.bridge$getItemDamage();
      return Markers2_3.method3(this.field1.get(var3));
   }

   @Override
   public Optional<ItemStackBridge> method7(@NotNull Markers2_3 var1) {
      if (var1.isEmpty()) {
         return SIterator_2.method8("minecraft:" + this.method2());
      }

      if (ThreadModuleDump63.MC_VERSION > 5) {
         return SIterator_2.method8("minecraft:" + var1.value());
      }

      ItemStackBridge var2 = SIterator_2.method9("minecraft:" + this.method2());
      int var3 = this.field1.indexOf(var1.value());
      if (var3 == -1) {
         return Optional.empty();
      }

      var2.bridge$setItemDamage(var3);
      return SIterator_2.method7(var2);
   }

   @Generated
   public List<String> method6() {
      return this.field1;
   }
}
