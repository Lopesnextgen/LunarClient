package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.client.framework.mod.Framework4Handler;
import java.util.function.BooleanSupplier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Framework4 {
   @NotNull
   <T extends Framework7Extension> T method1();

   boolean method2();

   static Framework4 method3(Framework7Extension var0) {
      return new Framework4Handler(null, var0);
   }

   static Framework4 method4(boolean var0, Framework7Extension var1) {
      return new Framework4Handler(var0 ? null : () -> false, var1);
   }

   static Framework4 method5(@Nullable BooleanSupplier var0, Framework7Extension var1) {
      return new Framework4Handler(var0, var1);
   }
}
