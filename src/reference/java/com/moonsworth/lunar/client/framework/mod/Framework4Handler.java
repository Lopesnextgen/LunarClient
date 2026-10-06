package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.client.framework.mod.Framework4;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import java.util.function.BooleanSupplier;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Framework4Handler implements Framework4 {
   @Nullable
   private final BooleanSupplier field1;
   @NotNull
   private final Framework7Extension field2;

   @NotNull
   @Override
   public <T extends Framework7Extension> T method1() {
      return (T)this.field2;
   }

   @Override
   public boolean method2() {
      return this.field1 == null || this.field1.getAsBoolean();
   }

   @Generated
   public Framework4Handler(@Nullable BooleanSupplier var1, @NotNull Framework7Extension var2) {
      if (var2 == null) {
         throw new NullPointerException("parentFeature is marked non-null but is null");
      }

      this.field1 = var1;
      this.field2 = var2;
   }
}
