package com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.nameplate;

import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import lombok.Generated;

public class Nameplate2Impl2 extends Nameplate2 {
   private boolean paused;

   @Override
   public void method1(ByteBufLoader var1) {
      this.paused = var1.readBoolean();
   }

   @Override
   public void method2(ByteBufLoader var1) {
      var1.writeBoolean(this.paused);
   }

   @Override
   public void method3(Nameplate4 var1) {
      var1.method33(this.paused);
   }

   @Override
   public Nameplate2 method4(Nameplate4 var1) {
      return new Nameplate2Impl2(!this.paused);
   }

   @Generated
   public Nameplate2Impl2(boolean var1) {
      this.paused = var1;
   }

   @Generated
   public Nameplate2Impl2() {
   }

   @Generated
   public boolean isPaused() {
      return this.paused;
   }
}
