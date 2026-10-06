package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.BridgeExtension2_5;
import com.moonsworth.lunar.bridge.ItemFrameEntityBridge;
import com.moonsworth.lunar.bridge.PacketFactoryBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers5;
import com.moonsworth.lunar.client.util.concurrent.ConsumerExtension;
import lombok.NonNull;

public class RewindhandlersNameplateCoreIterator implements RewindhandlersNameplateCore_2 {
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

      for (BridgeExtension var6 : var3.bridge$getEntities()) {
         this.method2(var6, var1, var4, var2, var3, true);
      }

      for (BridgeExtension var8 : var3.bridge$getEntities()) {
         this.method2(var8, var1, var4, var2, var3, false);
      }
   }

   private void method2(
      BridgeExtension var1, @NonNull RewindHandlers5 var2, PacketFactoryBridge var3, @NonNull Bridge5Extension_5 var4, @NonNull Itemcounter6 var5, boolean var6
   ) {
      if (var2 == null) {
         throw new NullPointerException("recorder is marked non-null but is null");
      }

      if (var4 == null) {
         throw new NullPointerException("player is marked non-null but is null");
      }

      if (var5 == null) {
         throw new NullPointerException("world is marked non-null but is null");
      }

      var1.bridge$sendPairingData(
         (ConsumerExtension)var5x -> {
            if (var5x != null) {
               boolean var6x = var3.method18().HHRROIIHRRICIIHIIHICRHHRHOHHOO(var5x.getClass())
                  || var3.method13().HHRROIIHRRICIIHIIHICRHHRHOHHOO(var5x.getClass())
                  || var3.method31().HHRROIIHRRICIIHIIHICRHHRHOHHOO(var5x.getClass())
                  || var3.method32().HHRROIIHRRICIIHIIHICRHHRHOHHOO(var5x.getClass())
                  || var3.method33().HHRROIIHRRICIIHIIHICRHHRHOHHOO(var5x.getClass());
               if ((!var6 || var1 != var4) && var6 == var6x) {
                  var2.method3(Nameplate.method2(var5x));
               }
            }
         }
      );
      if (!var6) {
         if (var1 instanceof BridgeExtension2_5 var7) {
            var2.method3(Nameplate.method2(var3.method19().method1(var7)));
         }

         if (var1 instanceof ItemFrameEntityBridge var8) {
            var2.method3(Nameplate.method2(var8.bridge$getMapPacket(var5, var4)));
         }
      }
   }
}
