package com.moonsworth.lunar.client.framework.feature.rewind.nameplate;

import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventMouseWheelLegacy;
import lombok.Generated;

public class Nameplate2Impl5 extends Nameplate2 {
   private double field1;

   @Override
   public void method1(ByteBufLoader var1) {
      this.field1 = var1.readDouble();
   }

   @Override
   public void method2(ByteBufLoader var1) {
      var1.writeDouble(this.field1);
   }

   @Override
   public void method3(Nameplate4 var1) {
      ClientEventBus.method29().method12(EventMouseWheelLegacy.class, () -> new EventMouseWheelLegacy(this.field1));
   }

   @Override
   public Nameplate2 method4(Nameplate4 var1) {
      return new Nameplate2Impl5(-this.field1);
   }

   @Generated
   public Nameplate2Impl5(double var1) {
      this.field1 = var1;
   }

   @Generated
   public Nameplate2Impl5() {
   }

   @Generated
   public double method5() {
      return this.field1;
   }
}
