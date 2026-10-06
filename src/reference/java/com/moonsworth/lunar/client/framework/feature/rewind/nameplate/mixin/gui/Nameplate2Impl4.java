package com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.gui;

import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.GuiType;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import lombok.Generated;

public class Nameplate2Impl4 extends Nameplate2 {
   private GuiType field1;
   private GuiType field2;

   @Override
   public void method1(ByteBufLoader var1) {
      this.field1 = var1.method9(GuiType.class);
      this.field2 = var1.method9(GuiType.class);
   }

   @Override
   public void method2(ByteBufLoader var1) {
      var1.method10(this.field1);
      var1.method10(this.field2);
   }

   @Override
   public void method3(Nameplate4 var1) {
      com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate2 var2 = var1.method8();
      var2.method17(this.field1);
      var2.method18(this.field2);
   }

   @Generated
   public Nameplate2Impl4() {
   }

   @Generated
   public Nameplate2Impl4(GuiType var1, GuiType var2) {
      this.field1 = var1;
      this.field2 = var2;
   }
}
