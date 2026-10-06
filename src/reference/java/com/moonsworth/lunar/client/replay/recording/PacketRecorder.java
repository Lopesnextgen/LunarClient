package com.moonsworth.lunar.client.replay.recording;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.PacketBridge;
import com.moonsworth.lunar.bridge.Bridge7_9;
import com.moonsworth.lunar.bridge.ConnectionProtocol;
import com.moonsworth.lunar.bridge.PacketDirectionBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.replay.recording.ReplayHandler;
import com.moonsworth.lunar.client.replay.network.RawNetworkPacket;
import com.moonsworth.lunar.client.event.mixin.gui.EventPacket;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindRecorder;
import com.moonsworth.lunar.client.framework.Ref;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class PacketRecorder extends RecorderEventListener {
   private boolean field1 = false;

   public PacketRecorder() {
   }

   @Override
   public void method1(EventPacket highlightimpl131, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
      if (highlightimpl131.method3() == PacketDirectionBridge.CLIENTBOUND && (highlightimpl131.method4() == ConnectionProtocol.PLAY || highlightimpl131.method4() == ConnectionProtocol.CONFIGURATION)) {
         RawNetworkPacket nameplate2impl34 = this.method2(highlightimpl131, rewindhandlers52, rewind_43);
         if (nameplate2impl34 != null) {
            Ref.method3().bridge$submit(() -> {
               try {
                  if (Ref.MC_VERSION >= 19) {
                     if (!this.field1 && highlightimpl131.method4() == ConnectionProtocol.CONFIGURATION) {
                        this.field1 = true;
                        rewind_43.method7();
                     } else if (highlightimpl131.method4() != ConnectionProtocol.CONFIGURATION) {
                        this.field1 = false;
                     }
                  } else {
                     ResourceLocationBridge horsestats145 = Bridge.method59().method4(nameplate2impl34.method6(), nameplate2impl34.getId());
                     if (Bridge.method59().method5().HHRROIIHRRICIIHIIHICRHHRHOHHOO(horsestats145, nameplate2impl34.getId())) {
                        rewind_43.method7();
                     }
                  }

                  rewind_43.method9(nameplate2impl34, rewindhandlers52.getTick());
               } catch (Exception exception6) {
                  exception6.printStackTrace();
               }
            });
         }
      }
   }

   private RawNetworkPacket method2(EventPacket highlightimpl131, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
      ByteBuf buffer4 = highlightimpl131.method2();
      PacketBridge bridge3_215 = highlightimpl131.method1();
      int number6;
      byte[] items7;
      if (buffer4 != null) {
         Bridge7_9 bridge7_98 = Bridge.method8().method23(buffer4.slice());
         number6 = bridge7_98.bridge$readVarIntFromBuffer();
         items7 = new byte[bridge7_98.bridge$readableBytes()];
         bridge7_98.bridge$readBytes(items7);
      } else {
         if (bridge3_215 == null) {
            return null;
         }

         PacketDirectionBridge bridgetype_415 = highlightimpl131.method3();
         ConnectionProtocol bridgetype2_29 = highlightimpl131.method4();
         number6 = Bridge.method59().method2(bridgetype_415, bridgetype2_29, bridge3_215);
         if (number6 == -1) {
            if (Ref.MC_VERSION >= 6) {
               Bridge.method59().method3(bridgetype_415, bridgetype2_29, bridge3_215, arg5x -> {
                  try {
                     this.method1(new EventPacket(arg5x, null, bridgetype_415, bridgetype2_29), rewindhandlers52, rewind_43);
                  } catch (Exception exception7x) {
                     exception7x.printStackTrace();
                  }
               });
            }

            return null;
         }

         ByteBuf buffer10 = Unpooled.buffer();

         try {
            Bridge7_9 bridge7_911 = Bridge.method8().method23(buffer10);
            bridge3_215.bridge$write(bridge7_911, bridgetype_415, bridgetype2_29);
            items7 = buffer10.array();
         } finally {
            buffer10.release();
         }
      }

      return new RawNetworkPacket(number6, items7, highlightimpl131.method3(), highlightimpl131.method4());
   }
}
