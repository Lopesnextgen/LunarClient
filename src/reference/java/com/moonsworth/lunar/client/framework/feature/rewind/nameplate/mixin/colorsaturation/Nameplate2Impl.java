package com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.colorsaturation;

import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import lombok.Generated;

public class Nameplate2Impl extends Nameplate2 {
   private int tick;

   @Override
   public void method1(ByteBufLoader var1) {
      this.tick = var1.readVarInt();
   }

   @Override
   public void method2(ByteBufLoader var1) {
      var1.method11(this.tick);
   }

   @Override
   public void method3(Nameplate4 var1) {
   }

   @Generated
   public Nameplate2Impl(int var1) {
      this.tick = var1;
   }

   @Generated
   public Nameplate2Impl() {
   }

   @Generated
   public int getTick() {
      return this.tick;
   }
}
