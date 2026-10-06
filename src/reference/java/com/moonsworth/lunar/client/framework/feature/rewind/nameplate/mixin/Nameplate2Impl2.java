package com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin;

import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler23;
import com.moonsworth.lunar.client.guiRewindhandlers.rewindhandlers.Rewindhandlers2;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import lombok.Generated;

public class Nameplate2Impl2 extends Nameplate2 {
   private String value;

   @Override
   public void method1(ByteBufLoader var1) {
      this.value = var1.readString();
   }

   @Override
   public void method2(ByteBufLoader var1) {
      var1.method1(this.value);
   }

   @Override
   public void method3(Nameplate4 var1) {
      Rewindhandlers2 var2 = (Rewindhandlers2)ThreadModuleDump48.field22.fromJson(this.value, Rewindhandlers2.class);
      GuiRewindhandlersHandler23.field7.method8(var2);
   }

   @Generated
   public Nameplate2Impl2(String var1) {
      this.value = var1;
   }

   @Generated
   public Nameplate2Impl2() {
   }
}
