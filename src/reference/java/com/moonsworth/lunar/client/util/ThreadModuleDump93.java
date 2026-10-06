package com.moonsworth.lunar.client.util;

import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;

public class ThreadModuleDump93 {
   private static boolean dirty;

   public static void method1() {
      dirty = true;
   }

   public static void method2() {
      if (dirty) {
         dirty = false;
         ThreadModuleDump63.method28(null);
      }
   }

   static {
      ClientEventBus.method29().method2(EventClientTick.class, var0 -> method2());
   }
}
