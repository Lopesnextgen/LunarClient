package com.moonsworth.lunar.client.replay.recording;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityLivingBridge;
import com.moonsworth.lunar.bridge.EntityItemFrameBridge;
import com.moonsworth.lunar.bridge.PacketFactory;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindRecorder;
import com.moonsworth.lunar.client.util.concurrent.ConsumerExtension;
import lombok.NonNull;

public class EntityPairingCapture implements RecorderCapture {
   public EntityPairingCapture() {
   }

   @Override
   public void method1(@NonNull RewindRecorder rewindhandlers51, @NonNull Bridge5Extension_5 bridge5extension_52, @NonNull Itemcounter6 itemcounter63) {
      if (rewindhandlers51 == null) {
         throw new NullPointerException("recorder is marked non-null but is null");
      }

      if (bridge5extension_52 == null) {
         throw new NullPointerException("player is marked non-null but is null");
      }

      if (itemcounter63 == null) {
         throw new NullPointerException("world is marked non-null but is null");
      }

      PacketFactory bridge_164 = Bridge.method59();

      for (BridgeExtension bridgeextension6 : itemcounter63.bridge$getEntities()) {
         this.method2(bridgeextension6, rewindhandlers51, bridge_164, bridge5extension_52, itemcounter63, true);
      }

      for (BridgeExtension bridgeextension8 : itemcounter63.bridge$getEntities()) {
         this.method2(bridgeextension8, rewindhandlers51, bridge_164, bridge5extension_52, itemcounter63, false);
      }
   }

   private void method2(
      BridgeExtension bridgeextension1, @NonNull RewindRecorder rewindhandlers52, PacketFactory bridge_163, @NonNull Bridge5Extension_5 bridge5extension_54, @NonNull Itemcounter6 itemcounter65, boolean flag6
   ) {
      if (rewindhandlers52 == null) {
         throw new NullPointerException("recorder is marked non-null but is null");
      }

      if (bridge5extension_54 == null) {
         throw new NullPointerException("player is marked non-null but is null");
      }

      if (itemcounter65 == null) {
         throw new NullPointerException("world is marked non-null but is null");
      }

      bridgeextension1.bridge$sendPairingData(
         (ConsumerExtension)arg5x -> {
            if (arg5x != null) {
               boolean flag6x = bridge_163.method18().HHRROIIHRRICIIHIIHICRHHRHOHHOO(arg5x.getClass())
                  || bridge_163.method13().HHRROIIHRRICIIHIIHICRHHRHOHHOO(arg5x.getClass())
                  || bridge_163.method31().HHRROIIHRRICIIHIIHICRHHRHOHHOO(arg5x.getClass())
                  || bridge_163.method32().HHRROIIHRRICIIHIIHICRHHRHOHHOO(arg5x.getClass())
                  || bridge_163.method33().HHRROIIHRRICIIHIIHICRHHRHOHHOO(arg5x.getClass());
               if ((!flag6 || bridgeextension1 != bridge5extension_54) && flag6 == flag6x) {
                  rewindhandlers52.method3(ClientboundPacketEventFactory.method2(arg5x));
               }
            }
         }
      );
      if (!flag6) {
         if (bridgeextension1 instanceof EntityLivingBridge bridgeextension2_57) {
            rewindhandlers52.method3(ClientboundPacketEventFactory.method2(bridge_163.method19().method1(bridgeextension2_57)));
         }

         if (bridgeextension1 instanceof EntityItemFrameBridge bridgeextension58) {
            rewindhandlers52.method3(ClientboundPacketEventFactory.method2(bridgeextension58.bridge$getMapPacket(itemcounter65, bridge5extension_54)));
         }
      }
   }
}
