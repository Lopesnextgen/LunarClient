package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_21;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.PacketFactoryBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers5;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.NonNull;

public class RewindhandlersNameplateCoreIterator5 implements RewindhandlersNameplateCore_2 {
   @Override
   public void method1(@NonNull RewindHandlers5 var1, @NonNull Bridge5Extension_5 var2, @NonNull Itemcounter6 var3) {
      if (var1 == null) {
         throw new NullPointerException("recorder is marked non-null but is null");
      }

      if (var2 == null) {
         throw new NullPointerException("player is marked non-null but is null");
      }

      if (var3 == null) {
         throw new NullPointerException("world is marked non-null but is null");
      }

      PacketFactoryBridge var4 = Bridge.method59();
      if (ThreadModuleDump63.MC_VERSION >= 19) {
         var1.method3(Nameplate.method1(var4.method25().method2()));

         for (Bridge3_21 var6 : var4.method24().method2()) {
            var1.method3(Nameplate.method1(var6));
         }

         var1.method3(Nameplate.method1(var4.method30().method2()));
         var1.method3(Nameplate.method1(var4.method23().method2()));
      }

      var1.method3(Nameplate.method2(var4.method5().method1(var3, var2)));
      if (ThreadModuleDump63.MC_VERSION <= 18) {
         var1.method3(Nameplate.method2(var4.method30().method2()));
      }

      var1.method3(Nameplate.method2(var4.method34().method1(var2.bridge$getSendQueue())));
   }
}
