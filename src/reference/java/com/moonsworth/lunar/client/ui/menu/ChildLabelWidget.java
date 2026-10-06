package com.moonsworth.lunar.client.ui.menu;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.AnimatedValue;
import com.moonsworth.lunar.client.framework.mod.Calculator2Handler;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import lombok.Generated;

public class ChildLabelWidget extends EditableLabelWidget {
   private final Calculator2Handler field18;

   public ChildLabelWidget(Calculator2Handler var1, com.moonsworth.lunar.client.ui.widget.GuiWidget var2) {
      super(var2, var1.toString().toUpperCase());
      this.field18 = var1;
      this.field17 = new AnimatedValue(-2141746990, -11561732);
      if (var1 == Calculator2Handler.field3) {
         this.field17 = new AnimatedValue(-2142268416, -11561984);
      }

      this.width = this.field16
            .method4(this.method1(this.field18.toString(), new Object[0]).replace("", " ").trim())
         + 16.0F;
   }

   public ChildLabelWidget(String var1, com.moonsworth.lunar.client.ui.widget.GuiWidget var2) {
      super(var2, var1.toUpperCase());
      this.field18 = null;
      this.field17 = new AnimatedValue(-2141746990, -11561732);
      this.width = this.field16.method4(this.method1(var1, new Object[0]).replace("", " ").trim()) + 16.0F;
   }

   @Override
   public void method1(float var1, float var2) {
      super.method2(var1, var2, this.width, 14.0F);
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      com.moonsworth.lunar.client.ui.LcuiScreen.method53(
         var1,
         this.x,
         this.y,
         this.width,
         this.height,
         4.0F,
         0,
         !this.active && !this.method3(var2) ? 547529378 : 1621271202,
         this.field17.method2(this.active || var3 && this.method3(var2))
      );
      float var4 = this.field16.getHeight();
      String var5 = this.method1(this.field18 == null ? this.getText() : this.field18.toString(), new Object[0])
         .toUpperCase()
         .replace("", " ")
         .trim();
      this.field16
         .method14(var1, var5, this.x + this.width / 2.0F + 1.0F, this.y + this.height / 2.0F - var4 + 1.0F - this.offset, 536870912);
      this.field16
         .method14(
            var1,
            var5,
            this.x + this.width / 2.0F,
            this.y + this.height / 2.0F - var4 - this.offset,
            !this.active && (!this.method3(var2) || !var3) ? -1342177281 : -1
         );
   }

   @Generated
   public Calculator2Handler method3() {
      return this.field18;
   }
}
