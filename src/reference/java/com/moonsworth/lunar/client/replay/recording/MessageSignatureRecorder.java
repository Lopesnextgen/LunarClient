package com.moonsworth.lunar.client.replay.recording;

import com.moonsworth.lunar.bridge.NetHandlerPlayClientBridge;
import com.moonsworth.lunar.client.replay.recording.ReplayHandler;
import com.moonsworth.lunar.client.replay.network.MessageSignaturePacket;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindRecorder;
import com.moonsworth.lunar.client.framework.Ref;

public class MessageSignatureRecorder extends RecorderEventListener {
   private boolean field1 = false;

   public MessageSignatureRecorder() {
   }

   @Override
   public void method2(EventTick event, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
      if (Ref.MC_VERSION >= 15 && (rewind_43.method5() || !this.field1)) {
         NetHandlerPlayClientBridge bridgeextension_74 = Ref.method3().bridge$getClientPacketListener();
         if (bridgeextension_74 != null) {
            MessageSignaturePacket nameplate2impl5 = new MessageSignaturePacket(bridgeextension_74.bridge$serializeMessageSignatureCache());
            rewind_43.method9(nameplate2impl5, rewindhandlers52.getTick());
            this.field1 = true;
         } else {
            this.field1 = false;
         }
      }
   }
}
