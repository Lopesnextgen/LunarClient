package com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms;

import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.event.mixin.fishing.mixin.EventUseItemLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;

public class Nameplate2Impl5 extends Nameplate2 {
   private EventUseItemLegacy field1;

   @Override
   public void method1(ByteBufLoader var1) {
      this.field1 = new EventUseItemLegacy(var1.readVarInt());
   }

   @Override
   public void method2(ByteBufLoader var1) {
      var1.method11(this.field1.method1());
   }

   @Override
   public void method3(Nameplate4 var1) {
      ThreadModuleDump63.method3().bridge$getPlayerController().bridge$useItem(this.field1.method1());
   }

   @Generated
   public Nameplate2Impl5(EventUseItemLegacy var1) {
      this.field1 = var1;
   }

   @Generated
   public Nameplate2Impl5() {
   }
}
