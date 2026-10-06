package com.moonsworth.lunar.client.replay.recording;

import com.moonsworth.lunar.client.replay.recording.ReplayHandler;
import com.moonsworth.lunar.client.replay.network.HypixelLocationPacket;
import com.moonsworth.lunar.client.framework.listener.HypixelLocationListener;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindRecorder;
import com.moonsworth.lunar.client.framework.LunarConstants;

public class ScoreboardRecorder extends RecorderEventListener {
   public ScoreboardRecorder() {
   }

   @Override
   public void method2(EventTick event, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
      if (rewind_43.method5()) {
         String text4 = LunarConstants.field22.toJson(HypixelLocationListener.field7.method7());
         rewind_43.method9(new HypixelLocationPacket(text4), rewindhandlers52.getTick());
      }
   }
}
