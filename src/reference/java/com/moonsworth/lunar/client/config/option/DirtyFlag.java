package com.moonsworth.lunar.client.config.option;

import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.framework.Ref;

public class DirtyFlag {
   private static boolean dirty;

   public DirtyFlag() {
   }

   public static void method1() {
      dirty = true;
   }

   public static void method2() {
      if (dirty) {
         dirty = false;
         Ref.method28(null);
      }
   }

   static {
      LunarEventBus.method29().method2(EventTick.class, arg0 -> method2());
   }
}
