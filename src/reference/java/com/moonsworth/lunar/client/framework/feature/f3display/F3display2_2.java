package com.moonsworth.lunar.client.framework.feature.f3display;

import com.google.common.collect.ImmutableMap;
import com.moonsworth.lunar.bridge.Bridge2_17;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.Bridge_59;
import com.moonsworth.lunar.bridge.horsestats.Vector3iBridge;
import com.moonsworth.lunar.bridge.horsestats.MissResult;
import com.moonsworth.lunar.client.mod.hud.f3display.F3DisplayModule;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.chest.SExtension;
import com.moonsworth.lunar.client.util.chest.SImpl;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public class F3display2_2 extends F3display$Data {
   private final String field2;
   private final boolean field3;
   private String field4;
   private Bridge2_17 field5;
   private Bridge_59 field6;

   public F3display2_2(String var1, boolean var2) {
      this.field2 = var1;
      this.field3 = var2;
   }

   @Override
   protected void method1(F3display_3 var1, @Nullable F3DisplayModule var2) {
      Bridge5Extension_5 var3 = ThreadModuleDump63.method7();
      if (var3 != null && var3.bridge$getWorld() != null) {
         Vector3iBridge var4 = this.method2(var3);
         if (var4 != null) {
            this.field4 = var4.bridge$getX() + " " + var4.bridge$getY() + " " + var4.bridge$getZ();
            if (this.field3 && ThreadModuleDump63.MC_VERSION >= 6) {
               this.field6 = var3.bridge$getWorld().bridge$getFluidState$v1_16(var4);
               if (this.field6 != null && this.field6.bridge$isEmpty() && var2 != null && !(Boolean)var2.method21().get()) {
                  this.field6 = null;
               }
            } else {
               Bridge2_17 var5 = var3.bridge$getWorld().method2(var4);
               if (!this.field3 || var5.bridge$isFluid()) {
                  this.field5 = var5;
               }
            }
         }
      }
   }

   private Vector3iBridge method2(Bridge6_10 var1) {
      if (ThreadModuleDump63.MC_VERSION >= 6) {
         return var1.bridge$pick$v1_16(20.0F, ThreadModuleDump63.method3().bridge$getTimer().method1(), this.field3);
      }

      MissResult var2 = (MissResult)SExtension.builder(this.field3 ? SImpl.BLOCK_OR_MISS : SImpl.BLOCK)
         .method15(var1x -> var1x.bridge$getBlock().bridge$isAir() ? false : this.field3 || !var1x.bridge$isFluid())
         .method8(var1, 20.0, ThreadModuleDump63.method3().bridge$getTimer().method1())
         .method18()
         .method8(var1.bridge$getWorld());
      return var2.CCCOHROOIHRCCROOICCOIROHHIHROI() ? null : var2.method6();
   }

   @Override
   protected void method2(F3display_3 var1, @Nullable F3DisplayModule var2) {
      if (var1.method7()) {
         String var6 = this.field3 ? "0 101 0" : "0 100 0";
         String var7 = this.field3 ? "minecraft:water" : "minecraft:diamond_block";
         var1.method2(this.field2 + ": ", var6);
         var1.method1(var7);
         if (this.field3 && ThreadModuleDump63.MC_VERSION >= 1 && this.method4(var2)) {
            var1.method2("level: ", "0");
         }
      } else if (this.field4 != null) {
         boolean var3 = this.field3 && ThreadModuleDump63.MC_VERSION >= 6;
         if (var3) {
            if (this.field6 == null) {
               return;
            }
         } else if (this.field5 == null) {
            return;
         }

         var1.method2(this.field2 + ": ", this.field4);
         String var4 = var3 ? this.field6.bridge$getFluidId() : this.field5.bridge$getBlock().bridge$getRegistryName();
         var1.method1(var4);
         if (ThreadModuleDump63.MC_VERSION >= 1 && this.method4(var2)) {
            ImmutableMap var5 = var3 ? this.field6.bridge$getStringProperties() : this.field5.bridge$getStringProperties();
            var5.forEach((var1x, var2x) -> var1.method2(var1x + ": ", var2x.toString()));
         }

         if (ThreadModuleDump63.MC_VERSION >= 6 && this.method5(var2)) {
            List var8 = var3 ? this.field6.bridge$getTags() : this.field5.bridge$getTags();
            var8.forEach(var1x -> var1.method1("#" + var1x.toString()));
         }
      }
   }

   private boolean method4(@Nullable F3DisplayModule var1) {
      if (var1 == null) {
         return true;
      } else {
         return this.field3 ? (Boolean)var1.method17().get() : (Boolean)var1.method15().get();
      }
   }

   private boolean method5(@Nullable F3DisplayModule var1) {
      if (var1 == null) {
         return true;
      } else {
         return this.field3 ? (Boolean)var1.method19().get() : (Boolean)var1.method16().get();
      }
   }

   @Override
   protected void clear() {
      this.field4 = null;
      this.field5 = null;
      this.field6 = null;
   }
}
