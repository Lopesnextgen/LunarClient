package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.mixin;

import com.moonsworth.lunar.client.framework.feature.rewind.Gui2Extension;
import com.moonsworth.lunar.client.framework.feature.rewind.RewindIterator_2;
import com.moonsworth.lunar.client.framework.feature.rewind.Rewind_4;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers5;

public class RewindhandlersNameplateCoreImpl2 extends RewindhandlersNameplateCore {
   private long field1 = System.currentTimeMillis();
   public static final long field2 = 300000L;

   @Override
   public void method2(EventClientTick highlightImpl2, RewindHandlers5 handler, Rewind_4 rewind_4) {
      if (handler.method22() && rewind_4.method4() >= ((Gui2Extension)handler.method15().method29().get()).getMs()) {
         ((RewindIterator_2)rewind_4).method6();
         this.field1 = 0L;
      }

      if (System.currentTimeMillis() - this.field1 >= 300000L) {
         handler.method2(true);
      }

      if (rewind_4.method5()) {
         this.field1 = System.currentTimeMillis();
      }
   }
}
