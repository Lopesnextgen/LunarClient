package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.mixin;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.framework.feature.rewind.Rewind_4;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers5;

public class RewindhandlersNameplateCoreImpl4 extends RewindhandlersNameplateCore {
   @Override
   public void method2(EventClientTick highlightImpl2, RewindHandlers5 handler, Rewind_4 rewind_4) {
      if (rewind_4.method5()) {
         Client.method109().method53().method25();
      }
   }
}
