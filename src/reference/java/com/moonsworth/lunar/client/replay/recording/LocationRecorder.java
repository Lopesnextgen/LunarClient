package com.moonsworth.lunar.client.replay.recording;

import com.moonsworth.lunar.client.replay.recording.ReplayHandler;
import com.moonsworth.lunar.client.replay.recording.ReplayLocation;
import com.moonsworth.lunar.client.replay.network.LocationPacket;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindRecorder;

public class LocationRecorder extends RecorderEventListener {
   private ReplayLocation field1;

   public LocationRecorder() {
   }

   @Override
   public void method2(EventTick event, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
      ReplayLocation gui74 = rewindhandlers52.method16().method8();
      if (gui74 != null && (rewind_43.method5() || this.field1 == null || !this.field1.equals(gui74))) {
         rewind_43.method9(new LocationPacket(gui74.method1(), gui74.value()), rewindhandlers52.getTick());
      }

      this.field1 = gui74;
   }
}
