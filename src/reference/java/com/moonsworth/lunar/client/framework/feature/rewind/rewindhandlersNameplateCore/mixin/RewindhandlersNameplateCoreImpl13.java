package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_21;
import com.moonsworth.lunar.bridge.Bridge7_9;
import com.moonsworth.lunar.bridge.BridgeType2_2;
import com.moonsworth.lunar.bridge.PacketDirection;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.feature.rewind.Rewind_4;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.nameplate.Nameplate2Impl3;
import com.moonsworth.lunar.client.event.mixin.gui.PacketEvent;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers5;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class RewindhandlersNameplateCoreImpl13 extends RewindhandlersNameplateCore {
   private boolean field1 = false;

   @Override
   public void method1(PacketEvent var1, RewindHandlers5 var2, Rewind_4 var3) {
      if (var1.method3() == PacketDirection.CLIENTBOUND && (var1.method4() == BridgeType2_2.PLAY || var1.method4() == BridgeType2_2.CONFIGURATION)) {
         Nameplate2Impl3 var4 = this.method2(var1, var2, var3);
         if (var4 != null) {
            ThreadModuleDump63.method3().bridge$submit(() -> {
               try {
                  if (ThreadModuleDump63.MC_VERSION >= 19) {
                     if (!this.field1 && var1.method4() == BridgeType2_2.CONFIGURATION) {
                        this.field1 = true;
                        var3.method7();
                     } else if (var1.method4() != BridgeType2_2.CONFIGURATION) {
                        this.field1 = false;
                     }
                  } else {
                     ResourceLocationBridge var5 = Bridge.method59().method4(var4.method6(), var4.getId());
                     if (Bridge.method59().method5().method2(var5, var4.getId())) {
                        var3.method7();
                     }
                  }

                  var3.method9(var4, var2.getTick());
               } catch (Exception var6) {
                  var6.printStackTrace();
               }
            });
         }
      }
   }

   private Nameplate2Impl3 method2(PacketEvent var1, RewindHandlers5 var2, Rewind_4 var3) {
      ByteBuf var4 = var1.method2();
      Bridge3_21 var5 = var1.method1();
      int var6;
      byte[] var7;
      if (var4 != null) {
         Bridge7_9 var8 = Bridge.method8().method23(var4.slice());
         var6 = var8.bridge$readVarIntFromBuffer();
         var7 = new byte[var8.bridge$readableBytes()];
         var8.bridge$readBytes(var7);
      } else {
         if (var5 == null) {
            return null;
         }

         PacketDirection var15 = var1.method3();
         BridgeType2_2 var9 = var1.method4();
         var6 = Bridge.method59().method2(var15, var9, var5);
         if (var6 == -1) {
            if (ThreadModuleDump63.MC_VERSION >= 6) {
               Bridge.method59().method3(var15, var9, var5, var5x -> {
                  try {
                     this.method1(new PacketEvent(var5x, null, var15, var9), var2, var3);
                  } catch (Exception var7x) {
                     var7x.printStackTrace();
                  }
               });
            }

            return null;
         }

         ByteBuf var10 = Unpooled.buffer();

         try {
            Bridge7_9 var11 = Bridge.method8().method23(var10);
            var5.bridge$write(var11, var15, var9);
            var7 = var10.array();
         } finally {
            var10.release();
         }
      }

      return new Nameplate2Impl3(var6, var7, var1.method3(), var1.method4());
   }
}
