package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventMouseWheel;
import lombok.Generated;

public class MouseWheelPacket extends ReplayPacket {
   private double field1;

   @Override
   public void method1(ByteBufLoader bytebufloader1) {
      this.field1 = bytebufloader1.readDouble();
   }

   @Override
   public void method2(ByteBufLoader bytebufloader1) {
      bytebufloader1.writeDouble(this.field1);
   }

   @Override
   public void method3(ReplayContext nameplate41) {
      LunarEventBus.method29().method12(EventMouseWheel.class, () -> new EventMouseWheel(this.field1));
   }

   @Override
   public ReplayPacket method4(ReplayContext nameplate41) {
      return new MouseWheelPacket(-this.field1);
   }

   @Generated
   public MouseWheelPacket(double value) {
      this.field1 = value;
   }

   @Generated
   public MouseWheelPacket() {
   }

   @Generated
   public double method5() {
      return this.field1;
   }
}
