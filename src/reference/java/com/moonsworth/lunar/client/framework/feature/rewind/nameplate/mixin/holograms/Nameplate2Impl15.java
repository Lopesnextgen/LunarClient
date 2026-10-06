package com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms;

import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import lombok.Generated;

public class Nameplate2Impl15 extends Nameplate2 {
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
      var1.method7().method18(this.field1);
   }

   @Generated
   public Nameplate2Impl15(int var1) {
      this.field1 = var1;
   }

   @Generated
   public Nameplate2Impl15() {
   }
}
