package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.mixin;

import com.moonsworth.lunar.bridge.ClientPacketListenerBridge;
import com.moonsworth.lunar.client.framework.feature.rewind.Rewind_4;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.nameplate.Nameplate2Impl;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers5;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;

public class RewindhandlersNameplateCoreImpl11 extends RewindhandlersNameplateCore {
   private boolean field1 = false;

   @Override
   public void method2(EventClientTick highlightImpl2, RewindHandlers5 handler, Rewind_4 rewind_4) {
      if (ThreadModuleDump63.MC_VERSION >= 15 && (rewind_4.method5() || !this.field1)) {
         ClientPacketListenerBridge var4 = ThreadModuleDump63.method3().bridge$getClientPacketListener();
         if (var4 != null) {
            Nameplate2Impl var5 = new Nameplate2Impl(var4.bridge$serializeMessageSignatureCache());
            rewind_4.method9(var5, handler.getTick());
            this.field1 = true;
         } else {
            this.field1 = false;
         }
      }
   }
}
