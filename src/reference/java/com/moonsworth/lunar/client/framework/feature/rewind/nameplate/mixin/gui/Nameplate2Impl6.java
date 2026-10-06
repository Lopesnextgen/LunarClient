package com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.gui;

import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;

public class Nameplate2Impl6 extends Nameplate2 {
   private int field1;

   @Override
   public void method1(ByteBufLoader var1) {
      this.field1 = var1.readVarInt();
   }

   @Override
   public void method2(ByteBufLoader var1) {
      var1.method11(this.field1);
   }

   @Override
   public void method3(Nameplate4 var1) {
      ThreadModuleDump63.method3().bridge$setCreativeTab(this.field1);
      ThreadModuleDump63.method7().bridge$openInventory();
   }

   @Override
   public Nameplate2 method4(Nameplate4 var1) {
      return new Nameplate2Impl2();
   }

   @Generated
   public Nameplate2Impl6(int var1) {
      this.field1 = var1;
   }

   @Generated
   public Nameplate2Impl6() {
   }
}
