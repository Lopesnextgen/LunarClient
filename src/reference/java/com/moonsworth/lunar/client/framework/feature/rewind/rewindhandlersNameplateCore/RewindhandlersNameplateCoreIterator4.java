package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_21;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.PacketFactoryBridge;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter2_3;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers5;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Map.Entry;
import lombok.NonNull;

public class RewindhandlersNameplateCoreIterator4 implements RewindhandlersNameplateCore_2 {
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
      var1.method3(Nameplate.method2(var4.method26().method1(var3)));
      if (ThreadModuleDump63.MC_VERSION > 0) {
         var1.method3(Nameplate.method2(var4.method6().method1(var3)));
      }

      var1.method3(Nameplate.method2(var4.method7().method1(var3)));
      if (ThreadModuleDump63.MC_VERSION > 0) {
         var1.method3(Nameplate.method2(var4.method8().method1(var3)));
      }

      var1.method3(Nameplate.method2(var4.method9().method1(var3)));

      for (Bridge3_21 var6 : var4.method10().method1(var3)) {
         var1.method3(Nameplate.method2(var6));
      }

      if (var3 instanceof Itemcounter6Extension var8) {
         for (Entry var7 : var8.bridge$getAllMapData().entrySet()) {
            var1.method3(Nameplate.method2(((Itemcounter2_3)var7.getValue()).bridge$getMapPacket(var7.getKey())));
         }
      }
   }
}
