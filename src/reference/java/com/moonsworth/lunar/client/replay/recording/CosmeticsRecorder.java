package com.moonsworth.lunar.client.replay.recording;

import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.replay.recording.ReplayHandler;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindRecorder;

public class CosmeticsRecorder extends RecorderEventListener {
   public CosmeticsRecorder() {
   }

   @Override
   public void method2(EventTick event, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
      if (rewind_43.method5()) {
         Client.method109().method53().method25();
      }
   }
}
