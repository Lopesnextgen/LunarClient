package com.moonsworth.lunar.bridge;

import java.util.function.Function;
import org.jetbrains.annotations.Nullable;

public final class TextColorFunctionHolder {
   @Nullable
   private static Function<Float, Integer> field1;

   private TextColorFunctionHolder() {
   }

   public static void method1(Function<Float, Integer> function0) {
      field1 = function0;
   }

   public static void clear() {
      field1 = null;
   }

   @Nullable
   public static Function<Float, Integer> method2() {
      return field1;
   }
}
