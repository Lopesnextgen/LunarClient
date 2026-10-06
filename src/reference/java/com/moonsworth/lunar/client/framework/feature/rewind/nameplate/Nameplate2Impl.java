package com.moonsworth.lunar.client.framework.feature.rewind.nameplate;

import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.GuiType3;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import lombok.Generated;

public class Nameplate2Impl extends Nameplate2 {
   private GuiType3 field1;
   private boolean state;

   @Override
   public void method1(ByteBufLoader var1) {
      this.field1 = var1.method9(GuiType3.class);
      this.state = var1.readBoolean();
   }

   @Override
   public void method2(ByteBufLoader var1) {
      var1.method10(this.field1);
      var1.writeBoolean(this.state);
   }

   @Override
   public void method3(Nameplate4 var1) {
      var1.getProvider().put(this.field1, this.state);
   }

   @Override
   public Nameplate2 method4(Nameplate4 var1) {
      return new Nameplate2Impl(this.field1, !this.state);
   }

   @Generated
   public Nameplate2Impl() {
   }

   @Generated
   public Nameplate2Impl(GuiType3 var1, boolean flag) {
      this.field1 = var1;
      this.state = flag;
   }
}
