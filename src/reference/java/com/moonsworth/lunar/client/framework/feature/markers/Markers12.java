package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import java.util.Optional;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class Markers12 implements SExtension_2<ItemStackBridge, ItemStackBridge> {
   private final String field1;
   private final String field2;

   @NotNull
   @Override
   public String method4() {
      return this.field2.split(":")[1];
   }

   public boolean method2(@NotNull ItemStackBridge var1) {
      String var2 = var1.bridge$getItemRegistryName();
      return this.field2.equals(var2) || this.field1.equals(var2);
   }

   @NotNull
   public Markers2_3 method3(@NotNull ItemStackBridge var1) {
      return Markers2_3.method2();
   }

   @Override
   public Optional<ItemStackBridge> method7(@NotNull Markers2_3 var1) {
      return SIterator_2.method8(this.field2);
   }

   @Generated
   public Markers12(String var1, String var2) {
      this.field1 = var1;
      this.field2 = var2;
   }
}
