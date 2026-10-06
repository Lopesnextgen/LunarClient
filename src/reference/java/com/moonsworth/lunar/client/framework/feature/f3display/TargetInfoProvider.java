package com.moonsworth.lunar.client.framework.feature.f3display;

import com.google.common.collect.ImmutableMap;
import com.moonsworth.lunar.bridge.BlockStateBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.FluidStateBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3iBridge;
import com.moonsworth.lunar.bridge.horsestats.MissResult;
import com.moonsworth.lunar.client.mod.hud.f3display.F3DisplayModule;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.raytrace.Ray;
import com.moonsworth.lunar.client.util.raytrace.Raycaster;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public class TargetInfoProvider extends F3DebugEntry {
   private final String field2;
   private final boolean field3;
   private String field4;
   private BlockStateBridge field5;
   private FluidStateBridge field6;

   public TargetInfoProvider(String text1, boolean flag2) {
      this.field2 = text1;
      this.field3 = flag2;
   }

   @Override
   protected void method1(F3DebugWriter f3display_31, @Nullable F3DisplayModule f3modulechildmod2) {
      Bridge5Extension_5 bridge5extension_53 = Ref.method7();
      if (bridge5extension_53 != null && bridge5extension_53.bridge$getWorld() != null) {
         Vec3iBridge horsestats204 = this.method2(bridge5extension_53);
         if (horsestats204 != null) {
            this.field4 = horsestats204.bridge$getX() + " " + horsestats204.bridge$getY() + " " + horsestats204.bridge$getZ();
            if (this.field3 && Ref.MC_VERSION >= 6) {
               this.field6 = bridge5extension_53.bridge$getWorld().bridge$getFluidState$v1_16(horsestats204);
               if (this.field6 != null && this.field6.bridge$isEmpty() && f3modulechildmod2 != null && !(Boolean)f3modulechildmod2.method21().get()) {
                  this.field6 = null;
               }
            } else {
               BlockStateBridge bridge2_175 = bridge5extension_53.bridge$getWorld().method2(horsestats204);
               if (!this.field3 || bridge2_175.bridge$isFluid()) {
                  this.field5 = bridge2_175;
               }
            }
         }
      }
   }

   private Vec3iBridge method2(Bridge6_10 bridge6_101) {
      if (Ref.MC_VERSION >= 6) {
         return bridge6_101.bridge$pick$v1_16(20.0F, Ref.method3().bridge$getTimer().method1(), this.field3);
      }

      MissResult horsestatshandler2 = (MissResult)Ray.method9(this.field3 ? Raycaster.field2 : Raycaster.field1)
         .method15(arg1x -> arg1x.bridge$getBlock().bridge$isAir() ? false : this.field3 || !arg1x.bridge$isFluid())
         .method8(bridge6_101, 20.0, Ref.method3().bridge$getTimer().method1())
         .method18()
         .method8(bridge6_101.bridge$getWorld());
      return horsestatshandler2.CCCOHROOIHRCCROOICCOIROHHIHROI() ? null : horsestatshandler2.method6();
   }

   @Override
   protected void method2(F3DebugWriter f3display_31, @Nullable F3DisplayModule f3modulechildmod2) {
      if (f3display_31.method7()) {
         String text6 = this.field3 ? "0 101 0" : "0 100 0";
         String text7 = this.field3 ? "minecraft:water" : "minecraft:diamond_block";
         f3display_31.method2(this.field2 + ": ", text6);
         f3display_31.method1(text7);
         if (this.field3 && Ref.MC_VERSION >= 1 && this.method4(f3modulechildmod2)) {
            f3display_31.method2("level: ", "0");
         }
      } else if (this.field4 != null) {
         boolean flag3 = this.field3 && Ref.MC_VERSION >= 6;
         if (flag3) {
            if (this.field6 == null) {
               return;
            }
         } else if (this.field5 == null) {
            return;
         }

         f3display_31.method2(this.field2 + ": ", this.field4);
         String text4 = flag3 ? this.field6.bridge$getFluidId() : this.field5.bridge$getBlock().bridge$getRegistryName();
         f3display_31.method1(text4);
         if (Ref.MC_VERSION >= 1 && this.method4(f3modulechildmod2)) {
            ImmutableMap map5 = flag3 ? this.field6.bridge$getStringProperties() : this.field5.bridge$getStringProperties();
            map5.forEach((arg1x, arg2x) -> f3display_31.method2(arg1x + ": ", arg2x.toString()));
         }

         if (Ref.MC_VERSION >= 6 && this.method5(f3modulechildmod2)) {
            List list8 = flag3 ? this.field6.bridge$getTags() : this.field5.bridge$getTags();
            list8.forEach(arg1x -> f3display_31.method1("#" + arg1x.toString()));
         }
      }
   }

   private boolean method4(@Nullable F3DisplayModule f3modulechildmod1) {
      if (f3modulechildmod1 == null) {
         return true;
      } else {
         return this.field3 ? (Boolean)f3modulechildmod1.method17().get() : (Boolean)f3modulechildmod1.method15().get();
      }
   }

   private boolean method5(@Nullable F3DisplayModule f3modulechildmod1) {
      if (f3modulechildmod1 == null) {
         return true;
      } else {
         return this.field3 ? (Boolean)f3modulechildmod1.method19().get() : (Boolean)f3modulechildmod1.method16().get();
      }
   }

   @Override
   protected void clear() {
      this.field4 = null;
      this.field5 = null;
      this.field6 = null;
   }
}
