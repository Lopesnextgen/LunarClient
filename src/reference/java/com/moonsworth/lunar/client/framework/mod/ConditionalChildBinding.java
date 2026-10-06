package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import java.util.function.BooleanSupplier;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ConditionalChildBinding implements ChildModBinding {
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
   public ConditionalChildBinding(@Nullable BooleanSupplier booleansupplier1, @NotNull Framework7Extension framework7extension2) {
      if (framework7extension2 == null) {
         throw new NullPointerException("parentFeature is marked non-null but is null");
      }

      this.field1 = booleansupplier1;
      this.field2 = framework7extension2;
   }
}
