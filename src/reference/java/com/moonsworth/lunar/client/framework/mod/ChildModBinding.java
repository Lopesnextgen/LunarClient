package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.client.framework.mod.ConditionalChildBinding;
import java.util.function.BooleanSupplier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ChildModBinding {
   @NotNull
   <T extends Framework7Extension> T method1();

   boolean method2();

   static ChildModBinding method3(Framework7Extension framework7) {
      return new ConditionalChildBinding(null, framework7);
   }

   static ChildModBinding method4(boolean flag, Framework7Extension framework7extension1) {
      return new ConditionalChildBinding(flag ? null : () -> false, framework7extension1);
   }

   static ChildModBinding method5(@Nullable BooleanSupplier booleansupplier0, Framework7Extension framework7extension1) {
      return new ConditionalChildBinding(booleansupplier0, framework7extension1);
   }
}
