package com.moonsworth.lunar.client.mod.misc;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge2_17;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockModified;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import org.joml.Vector3f;

public class AnniversaryChest extends EventChestSpawner {
   public static final String field3 = "Anniversary";
   private static final Vector3f[] field4 = new Vector3f[7];

   public AnniversaryChest() {
      if (ThreadModuleDump63.MC_VERSION >= 1) {
         this.handle(EventBlockModified.class, var0 -> {
            Bridge2_17 var1 = var0.method2();
            if (var1.bridge$getBlock().bridge$isCake()) {
               Bridge2_17 var2 = var0.method3();
               Bridge3_23 var3 = var2.bridge$getBlock();
               if (var3.bridge$isCake() && var1.bridge$getBites() < var2.bridge$getBites() || var3.bridge$isAir() && var1.bridge$getBites() == 6) {
                  Bridge.method63().method1(var0.method1(), field4);
               }
            }
         });
      }
   }

   @Override
   public String id() {
      return "Anniversary";
   }

   @Override
   public boolean method1() {
      return ThreadModuleDump63.MC_VERSION >= 1 && (Boolean)ThreadModuleDump63.method4().method41().method6().method19().get();
   }

   @Override
   public void method2() {
      this.method6();
      super.method2();
   }

   public void method6() {
      field4[0] = new Vector3f(0.0F, 0.0F, 1.0F);
      field4[1] = new Vector3f(0.0F, 1.0F, 0.0F);
      field4[2] = new Vector3f(0.0F, 1.0F, 1.0F);
      field4[3] = new Vector3f(0.0F, 0.5F, 1.0F);
      field4[4] = new Vector3f(0.0F, 0.5F, 0.5F);
      field4[5] = new Vector3f(0.0F, 1.0F, 0.5F);
      field4[6] = new Vector3f(1.0F, 1.0F, 1.0F);
   }
}
