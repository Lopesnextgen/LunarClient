package com.moonsworth.lunar.client.mod.misc;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge_69;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.client.render.turbo.TurboEntityManager;
import com.moonsworth.lunar.client.render.turbo.CreeperJumpscareFilter;
import com.moonsworth.lunar.client.render.turbo.FarEntityRelocator;
import com.moonsworth.lunar.client.render.turbo.PathFinderHooks;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Generated;

public class EventChestEntityHook implements PathFinderHooks {
   private static final int field1 = 2400;
   private static final float field2 = 0.4F;
   private final TurboEntityManager field3;
   private boolean field4;
   private int field5 = 0;
   private double lastX;
   private double lastY;
   private double lastZ;

   @Override
   public void method1() {
      this.field4 = true;
   }

   @Override
   public void method2() {
      if (this.field4) {
         this.field4 = false;
         this.method3();
      }

      Bridge5Extension_5 var1 = ThreadModuleDump63.method7();
      if (var1 == null) {
         this.field5 = 0;
      } else {
         Itemcounter6Extension var2 = ThreadModuleDump63.method8();
         if (var2 == null) {
            this.field5 = 0;
         } else {
            if (var1.bridge$isOnGround() && !var1.bridge$isFlying()) {
               if (this.lastX == var1.bridge$getPosX() && this.lastY == var1.bridge$getPosY() && this.lastZ == var1.bridge$getPosZ()) {
                  this.field5++;
                  if (this.field5 > 2400) {
                     if (ThreadLocalRandom.current().nextFloat() <= 0.4F) {
                        this.field3.method1(var2, Bridge.method61().method2(), new CreeperJumpscareFilter());
                     }

                     this.field5 = 0;
                  }
               } else {
                  this.lastX = var1.bridge$getPosX();
                  this.lastY = var1.bridge$getPosY();
                  this.lastZ = var1.bridge$getPosZ();
                  this.field5 = 0;
               }
            } else {
               this.field5 = 0;
            }
         }
      }
   }

   private void method3() {
      Itemcounter6Extension var1 = ThreadModuleDump63.method8();
      if (var1 != null) {
         Bridge_69 var2 = Bridge.method61();
         this.field3.method1(var1, ThreadModuleDump63.MC_VERSION >= 1 ? var2.method1() : var2.method3(), new FarEntityRelocator());
      }
   }

   @Generated
   public EventChestEntityHook(TurboEntityManager var1) {
      this.field3 = var1;
   }
}
