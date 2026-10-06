package com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.nameplate;

import com.moonsworth.lunar.bridge.ClientPacketListenerBridge;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;

public class Nameplate2Impl extends Nameplate2 {
   private byte[] data;

   @Override
   public void method1(ByteBufLoader var1) {
      this.data = var1.readByteArray();
   }

   @Override
   public void method2(ByteBufLoader var1) {
      var1.method2(this.data);
   }

   @Override
   public void method3(Nameplate4 var1) {
      if (ThreadModuleDump63.MC_VERSION >= 15) {
         ClientPacketListenerBridge var2 = ThreadModuleDump63.method3().bridge$getClientPacketListener();
         var2.bridge$deserializeMessageSignatureCache(this.data);
      }
   }

   @Generated
   public Nameplate2Impl(byte[] var1) {
      this.data = var1;
   }

   @Generated
   public Nameplate2Impl() {
   }

   @Generated
   public byte[] getData() {
      return this.data;
   }
}
