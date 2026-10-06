package com.moonsworth.lunar.client.replay.recording;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.PacketBridge;
import com.moonsworth.lunar.client.replay.recording.ReplayHandler;
import com.moonsworth.lunar.client.replay.recording.ClientboundPacketEventFactory;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorldEffect;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockBreakProgress;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindRecorder;
import com.moonsworth.lunar.client.framework.Ref;

public class WorldEffectRecorder extends RecorderEventListener {
   private int field1 = -1;
   private int field2 = -1;

   public WorldEffectRecorder() {
   }

   @Override
   public void method10(EventBlockBreakProgress event, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
      if (event.method1() != this.field1 || event.getProgress() != this.field2) {
         this.field1 = event.method1();
         this.field2 = event.getProgress();
         PacketBridge bridge3_214 = Bridge.method59().method11().method1(event.method1(), event.method2(), event.getProgress());
         rewindhandlers52.method3(ClientboundPacketEventFactory.method2(bridge3_214));
      }
   }

   @Override
   public void method11(EventWorldEffect event, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
      if (event.method1() == null
         || Ref.method7() == null
         || event.method1().bridge$getUniqueID() == Ref.method7().bridge$getUniqueID()) {
         PacketBridge bridge3_214 = Bridge.method59().method12().method1(event.getType(), event.method2(), event.getData());
         rewindhandlers52.method3(ClientboundPacketEventFactory.method2(bridge3_214));
      }
   }
}
