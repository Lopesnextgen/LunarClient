package com.moonsworth.lunar.client.replay.recording;

import com.moonsworth.lunar.client.replay.export.ReplayClipDuration;
import com.moonsworth.lunar.client.replay.recording.ReplayHandlerImpl;
import com.moonsworth.lunar.client.replay.recording.ReplayHandler;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindRecorder;

public class KeyframeRecorder extends RecorderEventListener {
   private long field1 = System.currentTimeMillis();
   public static final long field2 = 300000L;

   public KeyframeRecorder() {
   }

   @Override
   public void method2(EventTick event, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
      if (rewindhandlers52.method22() && rewind_43.method4() >= ((ReplayClipDuration)rewindhandlers52.method15().method29().get()).getMs()) {
         ((ReplayHandlerImpl)rewind_43).method6();
         this.field1 = 0L;
      }

      if (System.currentTimeMillis() - this.field1 >= 300000L) {
         rewindhandlers52.method2(true);
      }

      if (rewind_43.method5()) {
         this.field1 = System.currentTimeMillis();
      }
   }
}
