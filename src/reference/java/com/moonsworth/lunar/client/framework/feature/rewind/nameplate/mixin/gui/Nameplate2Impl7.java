package com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.gui;

import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import lombok.Generated;

public class Nameplate2Impl7 extends Nameplate2 {
   private int x;
   private int y;

   @Override
   public void method1(ByteBufLoader var1) {
      this.x = var1.readVarInt();
      this.y = var1.readVarInt();
   }

   @Override
   public void method2(ByteBufLoader var1) {
      var1.method11(this.x);
      var1.method11(this.y);
   }

   @Override
   public void method3(Nameplate4 var1) {
      com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate2 var2 = var1.method8();
      var2.method2(this.x, this.y);
   }

   @Generated
   public Nameplate2Impl7() {
   }

   @Generated
   public Nameplate2Impl7(int var1, int var2) {
      this.x = var1;
      this.y = var2;
   }
}
