package com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms;

import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;

public class Nameplate2Impl3 extends Nameplate2 {
   private boolean sprinting;

   @Override
   public void method1(ByteBufLoader var1) {
      this.sprinting = var1.readBoolean();
   }

   @Override
   public void method2(ByteBufLoader var1) {
      var1.writeBoolean(this.sprinting);
   }

   @Override
   public void method3(Nameplate4 var1) {
      ThreadModuleDump63.method7().bridge$setSprinting(this.sprinting);
   }

   @Generated
   public Nameplate2Impl3(boolean var1) {
      this.sprinting = var1;
   }

   @Generated
   public Nameplate2Impl3() {
   }
}
