package com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms;

import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate3;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate5;
import lombok.Generated;

public class Nameplate2Impl12 extends Nameplate2 {
   private float yaw;
   private float pitch;

   @Override
   public void method1(ByteBufLoader var1) {
      this.yaw = var1.readFloat();
      this.pitch = var1.readFloat();
   }

   @Override
   public void method2(ByteBufLoader var1) {
      var1.writeFloat(this.yaw);
      var1.writeFloat(this.pitch);
   }

   @Override
   public void method3(Nameplate4 var1) {
      Nameplate3 var2 = var1.method7();
      var2.method1(this.yaw, this.pitch);
      Nameplate5 var3 = var1.method10();
      var3.method1(var2.method7(), var2.method8());
      if (!var1.method18()) {
         var2.method1(this.yaw, this.pitch);
      }
   }

   @Override
   public Nameplate2 method4(Nameplate4 var1) {
      Nameplate5 var2 = var1.method10();
      Object[] var3 = var2.method2();
      return new Nameplate2Impl12((Float)var3[0], (Float)var3[1]);
   }

   @Generated
   public Nameplate2Impl12(float var1, float var2) {
      this.yaw = var1;
      this.pitch = var2;
   }

   @Generated
   public Nameplate2Impl12() {
   }
}
