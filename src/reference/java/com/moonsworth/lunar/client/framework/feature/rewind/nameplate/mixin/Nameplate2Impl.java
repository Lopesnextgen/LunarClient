package com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin;

import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui7;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.GuiType4;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import lombok.Generated;

public class Nameplate2Impl extends Nameplate2 {
   private GuiType4 field1;
   private String value;

   @Override
   public void method1(ByteBufLoader var1) {
      this.field1 = var1.method9(GuiType4.class);
      this.value = var1.readString();
   }

   @Override
   public void method2(ByteBufLoader var1) {
      var1.method10(this.field1);
      var1.method1(this.value);
   }

   @Override
   public void method3(Nameplate4 var1) {
      var1.method30(new Gui7(this.field1, this.value));
   }

   @Generated
   public Nameplate2Impl(GuiType4 var1, String text) {
      this.field1 = var1;
      this.value = text;
   }

   @Generated
   public Nameplate2Impl() {
   }
}
