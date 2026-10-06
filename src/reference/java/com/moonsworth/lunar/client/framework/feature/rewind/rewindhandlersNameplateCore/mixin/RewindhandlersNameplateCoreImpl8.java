package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_21;
import com.moonsworth.lunar.client.framework.feature.rewind.Rewind_4;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.Nameplate;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorldEffectRecord;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockBreakingProgress;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers5;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;

public class RewindhandlersNameplateCoreImpl8 extends RewindhandlersNameplateCore {
   private int field1 = -1;
   private int field2 = -1;

   @Override
   public void method10(EventBlockBreakingProgress var1, RewindHandlers5 var2, Rewind_4 var3) {
      if (var1.method1() != this.field1 || var1.getProgress() != this.field2) {
         this.field1 = var1.method1();
         this.field2 = var1.getProgress();
         Bridge3_21 var4 = Bridge.method59().method11().method1(var1.method1(), var1.method2(), var1.getProgress());
         var2.method3(Nameplate.method2(var4));
      }
   }

   @Override
   public void method11(EventWorldEffectRecord var1, RewindHandlers5 var2, Rewind_4 var3) {
      if (var1.method1() == null
         || ThreadModuleDump63.method7() == null
         || var1.method1().bridge$getUniqueID() == ThreadModuleDump63.method7().bridge$getUniqueID()) {
         Bridge3_21 var4 = Bridge.method59().method12().method1(var1.getType(), var1.method2(), var1.getData());
         var2.method3(Nameplate.method2(var4));
      }
   }
}
