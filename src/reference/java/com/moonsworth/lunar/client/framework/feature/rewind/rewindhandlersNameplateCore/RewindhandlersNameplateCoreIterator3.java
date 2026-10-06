package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_21;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.PacketFactoryBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers5;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.NonNull;

public class RewindhandlersNameplateCoreIterator3 implements RewindhandlersNameplateCore_2 {
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

      for (Bridge3_21 var6 : var4.method22().method2()) {
         var1.method3(Nameplate.method2(var6));
      }

      for (Bridge3_21 var8 : var3.bridge$getScoreboardPackets()) {
         var1.method3(Nameplate.method2(var8));
      }

      var1.method3(Nameplate.method2(var4.method27().method1(ThreadModuleDump63.method3().bridge$getGuiIngame())));
   }
}
