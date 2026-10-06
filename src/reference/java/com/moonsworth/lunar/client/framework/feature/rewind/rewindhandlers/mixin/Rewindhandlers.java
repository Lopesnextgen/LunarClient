package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.mixin;

import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.rewindhandlersCore.RewindHandlers3Updater;
import com.moonsworth.lunar.client.event.input.EventMouseDelta;
import com.moonsworth.lunar.client.event.mixin.fishing.EventReplayFrame;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventMouseScrollLegacy;
import lombok.Generated;

public abstract class Rewindhandlers {
   protected final RewindHandlers3Updater field1;

   public abstract void method1(float var1);

   public void method2(EventMouseScrollLegacy var1) {
   }

   public void method3(EventMouseDelta var1) {
   }

   public void method4(EventReplayFrame var1) {
   }

   @Generated
   public Rewindhandlers(RewindHandlers3Updater var1) {
      this.field1 = var1;
   }
}
