package com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms;

import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate5;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;

public class Nameplate2Impl10 extends Nameplate2 {
   private int slot;

   @Override
   public void method1(ByteBufLoader var1) {
      this.slot = var1.readVarInt();
   }

   @Override
   public void method2(ByteBufLoader var1) {
      var1.method11(this.slot);
   }

   @Override
   public void method3(Nameplate4 var1) {
      Nameplate5 var2 = var1.method10();
      var2.method1(ThreadModuleDump63.method7().bridge$getCurrentEquippedItemIndex());
      ThreadModuleDump63.method7().bridge$setCurrentEquippedItemIndex(this.slot);
   }

   @Override
   public Nameplate2 method4(Nameplate4 var1) {
      return new Nameplate2Impl10((Integer)var1.method10().method2()[0]);
   }

   @Generated
   public Nameplate2Impl10(int var1) {
      this.slot = var1;
   }

   @Generated
   public Nameplate2Impl10() {
   }
}
