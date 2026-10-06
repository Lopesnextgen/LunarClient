package com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.fishing;

import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import lombok.Generated;

public class Nameplate2Impl2 extends Nameplate2 {
   private boolean field1;

   @Override
   public void method1(ByteBufLoader var1) {
      this.field1 = var1.readBoolean();
   }

   @Override
   public void method2(ByteBufLoader var1) {
      var1.writeBoolean(this.field1);
   }

   @Override
   public void method3(Nameplate4 var1) {
      if (var1.method18() && this.field1) {
         var1.method36(var1.getTick());
      }
   }

   @Generated
   public Nameplate2Impl2(boolean var1) {
      this.field1 = var1;
   }

   @Generated
   public Nameplate2Impl2() {
   }

   @Generated
   public boolean method4() {
      return this.field1;
   }
}
