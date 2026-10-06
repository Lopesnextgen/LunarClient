package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge2_27;
import com.moonsworth.lunar.bridge.Bridge4_15;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge5Extension612;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ContainerMarker;
import com.moonsworth.lunar.bridge.PacketFactoryBridge;
import com.moonsworth.lunar.bridge.itemcounter.Itemcounter6;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.gui.Nameplate2Impl5;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.gui.Nameplate2Impl6;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers5;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.NonNull;

public class RewindhandlersNameplateCoreHandler implements RewindhandlersNameplateCore_2 {
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
      ContainerMarker var5 = var2.bridge$getOpenContainer();
      Bridge5Extension6 var6 = ThreadModuleDump63.method3().bridge$getCurrentScreen();
      if (var6 instanceof Bridge4_15 || var6 instanceof Bridge2_27) {
         var1.method16().method9(new Nameplate2Impl6(ThreadModuleDump63.method3().bridge$getCreativeTab()), var1.getTick());
      } else if (var6 instanceof Bridge5Extension612 var7) {
         var1.method16().method9(new Nameplate2Impl5(var7.bridge$getInitialText()), var1.getTick());
      } else if (var5 != null) {
         var1.method3(Nameplate.method2(var4.method21().method1(var5)));
         var1.method3(Nameplate.method2(var4.method20().method2(var5)));
      }
   }
}
