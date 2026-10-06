package com.moonsworth.lunar.client.framework;

import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.OptionalInt;
import lombok.Generated;

public final class UnfocusedFpsLimiter {
   public static OptionalInt field1 = OptionalInt.empty();
   private static boolean field2;
   private static boolean field3;

   public static void method1() {
      if (ThreadModuleDump63.method2()) {
         if (ThreadModuleDump63.method3().bridge$isWindowFocused() || !(Boolean)ThreadModuleDump63.method4().method41().method7().method27().get()) {
            field2 = false;
            if (!field3 && field1.isPresent()) {
               ThreadModuleDump63.method3().bridge$getGameSettings().bridge$setFrameRateLimit(field1.getAsInt());
               field1 = OptionalInt.empty();
               field3 = true;
               field2 = false;
            }
         } else if (!field2) {
            field1 = OptionalInt.of(ThreadModuleDump63.method3().bridge$getGameSettings().bridge$getFrameRateLimit());
            ThreadModuleDump63.method3()
               .bridge$getGameSettings()
               .bridge$setFrameRateLimit((Integer)ThreadModuleDump63.method4().method41().method7().method28().get());
            field2 = true;
            field3 = false;
         }
      }
   }

   @Generated
   private UnfocusedFpsLimiter() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   @Generated
   public static OptionalInt method2() {
      return field1;
   }

   @Generated
   public static void method3(OptionalInt var0) {
      field1 = var0;
   }

   @Generated
   public static boolean method4() {
      return field3;
   }

   @Generated
   public static void method5(boolean var0) {
      field3 = var0;
   }
}
