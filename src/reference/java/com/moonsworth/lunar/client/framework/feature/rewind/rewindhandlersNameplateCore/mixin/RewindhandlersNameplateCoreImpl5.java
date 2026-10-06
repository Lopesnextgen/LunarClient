package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.mixin;

import com.moonsworth.lunar.client.framework.feature.rewind.Rewind_4;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.Nameplate2Impl2;
import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler23;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers5;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;

public class RewindhandlersNameplateCoreImpl5 extends RewindhandlersNameplateCore {
   @Override
   public void method2(EventClientTick highlightImpl2, RewindHandlers5 handler, Rewind_4 rewind_4) {
      if (rewind_4.method5()) {
         String var4 = ThreadModuleDump48.field22.toJson(GuiRewindhandlersHandler23.field7.method7());
         rewind_4.method9(new Nameplate2Impl2(var4), handler.getTick());
      }
   }
}
