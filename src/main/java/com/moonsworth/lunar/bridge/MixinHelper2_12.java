package com.moonsworth.lunar.bridge;

import java.util.function.Function;
import org.jetbrains.annotations.Nullable;

public final class MixinHelper2_12 {
   @Nullable
   private static Function<Float, Integer> field1;

   private MixinHelper2_12() {
   }

   public static void method1(Function<Float, Integer> function) {
      field1 = function;
   }

   public static void clear() {
      field1 = null;
   }

   @Nullable
   public static Function<Float, Integer> method2() {
      return field1;
   }
}
