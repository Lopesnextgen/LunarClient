package com.moonsworth.lunar.client.mod.misc;

import com.moonsworth.lunar.client.render.turbo.TurboEntityManager;
import com.moonsworth.lunar.client.mod.misc.EventChestEntityHook;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;

public class AprilFoolsChest implements EventChestDefinition {
   public static final String field1 = "AprilFools";
   private EventChestEntityHook field2;

   @Override
   public String id() {
      return "AprilFools";
   }

   @Override
   public boolean method1() {
      return (Boolean)ThreadModuleDump63.method4().method41().method6().method20().get();
   }

   @Override
   public void method2() {
      TurboEntityManager var1 = ThreadModuleDump63.method4().method88();
      var1.method21(this.field2 = new EventChestEntityHook(var1));
   }

   @Override
   public void method3() {
      if (this.field2 != null) {
         TurboEntityManager var1 = ThreadModuleDump63.method4().method88();
         var1.method22(this.field2);
         var1.method5();
      }
   }
}
