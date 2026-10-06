package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.mixin;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.client.framework.feature.rewind.Rewind_4;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms.Nameplate2Impl;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms.Nameplate2Impl10;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms.Nameplate2Impl13;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms.Nameplate2Impl14;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms.Nameplate2Impl15;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms.Nameplate2Impl3;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms.Nameplate2Impl4;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms.Nameplate2Impl5;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms.Nameplate2Impl6;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms.Nameplate2Impl7;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms.Nameplate2Impl8;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms.Nameplate2Impl9;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.nameplate.Nameplate2Impl2;
import com.moonsworth.lunar.client.event.combat.PreAttackEntityEvent;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockPick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.event.mixin.fishing.mixin.EventUseItemOnBlockLegacy;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers5;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;

public class RewindhandlersNameplateCoreImpl6 extends RewindhandlersNameplateCore {
   private int field1 = -1;
   private int field2 = -1;
   private boolean field3 = false;
   private boolean field4 = false;
   private boolean field5 = false;
   private int field6 = 0;
   private int field7 = 0;

   @Override
   public void method2(EventClientTick var1, RewindHandlers5 var2, Rewind_4 var3) {
      Bridge5Extension_5 var4 = ThreadModuleDump63.method7();
      if (var4 != null) {
         if (var3.method5() || var4.bridge$getCurrentEquippedItemIndex() != this.field1) {
            this.field1 = var4.bridge$getCurrentEquippedItemIndex();
            var3.method9(new Nameplate2Impl10(this.field1), var2.getTick());
         }

         if (var3.method5() || ThreadModuleDump63.method3().bridge$isGamePaused() != this.field4) {
            this.field4 = ThreadModuleDump63.method3().bridge$isGamePaused();
            var3.method9(new Nameplate2Impl2(this.field4), var2.getTick());
            var2.method10(this.field4);
         }

         if (!this.field4 && var4.bridge$isSwingInProgress() && var4.bridge$getSwingProgress() == 0) {
            var3.method9(new Nameplate2Impl8(var4.bridge$getSwingingArm()), var2.getTick());
         }

         if (var3.method5() || var4.bridge$isSprinting() != this.field3) {
            this.field3 = var4.bridge$isSprinting();
            var3.method9(new Nameplate2Impl3(this.field3), var2.getTick());
         }

         if (var3.method5() || ThreadModuleDump63.method3().bridge$getGameSettings().bridge$getThirdPersonView() != this.field2) {
            this.field2 = ThreadModuleDump63.method3().bridge$getGameSettings().bridge$getThirdPersonView();
            var3.method9(new Nameplate2Impl15(this.field2), var2.getTick());
         }

         if (var3.method5() || var4.bridge$isUsingItem() != this.field5) {
            this.field5 = var4.bridge$isUsingItem();
            if (this.field5) {
               var3.method9(new Nameplate2Impl14(var4.bridge$getSwingingArm()), var2.getTick());
            } else {
               var3.method9(new Nameplate2Impl7(), var2.getTick());
            }
         }

         if (ThreadModuleDump63.MC_VERSION >= 5 && (var3.method5() || var4.bridge$getAttackStrengthTicker() != this.field6)) {
            this.field6 = var4.bridge$getAttackStrengthTicker();
            if (var3.method5() || this.field6 == 1) {
               var3.method9(new Nameplate2Impl(this.field6), var2.getTick());
            }
         }

         if (ThreadModuleDump63.MC_VERSION >= 35 && (var3.method5() || var4.bridge$getItemSwapTicker() != this.field7)) {
            this.field7 = var4.bridge$getItemSwapTicker();
            if (var3.method5() || this.field7 == 1) {
               var3.method9(new Nameplate2Impl9(this.field7), var2.getTick());
            }
         }
      }
   }

   @Override
   public void method12(PreAttackEntityEvent var1, RewindHandlers5 var2, Rewind_4 var3) {
      var3.method9(new Nameplate2Impl4(), var2.getTick());
   }

   @Override
   public void method13(EventUseItemOnBlockLegacy var1, RewindHandlers5 var2, Rewind_4 var3) {
      var3.method9(new com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms.Nameplate2Impl2(var1), var2.getTick());
   }

   @Override
   public void method14(com.moonsworth.lunar.client.event.mixin.fishing.mixin.EventUseItemLegacy var1, RewindHandlers5 var2, Rewind_4 var3) {
      var3.method9(new Nameplate2Impl5(var1), var2.getTick());
   }

   @Override
   public void method18(EventBlockPick var1, RewindHandlers5 var2, Rewind_4 var3) {
      var3.method9(new Nameplate2Impl6(), var2.getTick());
   }

   @Override
   public void method19(com.moonsworth.lunar.client.event.mixin.fishing.RewindFrameEvent var1, RewindHandlers5 var2, Rewind_4 var3) {
      var3.method9(new Nameplate2Impl13(var1.method1()), var2.getTick());
   }
}
