package com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.gui;

import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate5;
import lombok.Generated;

public class Nameplate2Impl2 extends Nameplate2 {
   @Override
   public void method1(ByteBufLoader var1) {
   }

   @Override
   public void method2(ByteBufLoader var1) {
   }

   @Override
   public void method3(Nameplate4 var1) {
      Nameplate5 var2 = var1.method10();
      var2.method1(var1.method8().method5());
      var1.method8().method1(null);
   }

   @Override
   public Nameplate2 method4(Nameplate4 var1) {
      Nameplate5 var2 = var1.method10();
      Bridge5Extension6 var3 = (Bridge5Extension6)var2.method2()[0];
      var1.method8().method1(var3);
      return new com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.highlight.Nameplate2Impl();
   }
}
