package com.moonsworth.lunar.client.replay.recording;

import com.lunarclient.apollo.module.beam.BeamModule;
import com.moonsworth.lunar.client.network.apollo.BeamApolloHandler;
import com.moonsworth.lunar.client.replay.recording.ReplayHandler;
import com.moonsworth.lunar.client.replay.network.BeamPacket;
import com.moonsworth.lunar.client.replay.network.WaypointPacket;
import com.moonsworth.lunar.client.replay.network.WorldBorderPacket;
import com.moonsworth.lunar.client.replay.network.ClickHandlerPacket;
import com.moonsworth.lunar.client.replay.network.ServerHologramPacket;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindRecorder;
import com.moonsworth.lunar.client.util.concurrent.ConsumerExtension;
import com.moonsworth.lunar.client.framework.Ref;

public class HudStateRecorder extends RecorderEventListener {
   public HudStateRecorder() {
   }

   @Override
   public void method2(EventTick highlightimpl21, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
      if (rewind_43.method5()) {
         rewind_43.method9(new WorldBorderPacket(Ref.method4().method63().IORHHHROCRRHORHRCHCCHHIHICCRCO()), rewindhandlers52.getTick());
         rewind_43.method9(new ServerHologramPacket(Ref.method4().method57().IORHHHROCRRHORHRCHCCHHIHICCRCO()), rewindhandlers52.getTick());
         rewind_43.method9(new WaypointPacket(Ref.method4().method48().IIORHHIRHIORHRCCCOICCRCHRRCCRH()), rewindhandlers52.getTick());
         rewind_43.method9(new ClickHandlerPacket(Ref.method4().method46().method43()), rewindhandlers52.getTick());
         Ref.method4().method84().method3(BeamModule.class).ifPresent((ConsumerExtension)arg2x -> {
            BeamApolloHandler highlight3iterator63x = (BeamApolloHandler)arg2x;
            rewind_43.method9(new BeamPacket(highlight3iterator63x.method4()), rewindhandlers52.getTick());
         });
      }
   }
}
