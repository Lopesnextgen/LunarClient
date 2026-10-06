package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.mixin;

import com.moonsworth.lunar.client.framework.feature.rewind.Rewind_4;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui7;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.Nameplate2Impl;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers5;

public class RewindhandlersNameplateCoreImpl12 extends RewindhandlersNameplateCore {
   private Gui7 field1;

   @Override
   public void method2(EventClientTick highlightImpl2, RewindHandlers5 handler, Rewind_4 rewind_4) {
      Gui7 var4 = handler.method16().method8();
      if (var4 != null && (rewind_4.method5() || this.field1 == null || !this.field1.equals(var4))) {
         rewind_4.method9(new Nameplate2Impl(var4.method1(), var4.value()), handler.getTick());
      }

      this.field1 = var4;
   }
}
