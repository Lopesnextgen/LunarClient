package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.AnimatedValue;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import lombok.Generated;

public class IconWidget extends GuiWidget {
   private final ResourceLocationBridge field16;
   private final float field17;
   private final AnimatedValue field18 = new AnimatedValue(0, -1);
   private boolean field19 = false;

   public IconWidget(GuiWidget var1, ResourceLocationBridge var2, float var3) {
      super(var1);
      this.field16 = var2;
      this.field17 = var3;
   }

   public void method1(float var1, float var2) {
      super.method2(var1, var2, this.field17 * 2.0F + 4.0F, this.field17 * 2.0F + 4.0F);
   }

   @Override
   public void update() {
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      LcuiScreen.method94(var1, this.x, this.y, this.width, this.height, 100876083);
      int var4 = (128 + (int)(this.field18.method1(var3 && this.method3(var2)) * 127.0F) & 0xFF) << 24;
      int var5 = (this.field19 ? 13107 : 65535) | var4;
      LcuiScreen.method39(var1, this.field16, this.field17, this.x + this.field17 / 2.0F, this.y + this.field17 / 2.0F, var5);
   }

   @Override
   public void method4(char var1, KeyCode var2) {
   }

   @Override
   public void close() {
   }

   @Generated
   public ResourceLocationBridge getResource() {
      return this.field16;
   }

   @Generated
   public float getSize() {
      return this.field17;
   }

   @Generated
   public AnimatedValue method4() {
      return this.field18;
   }

   @Generated
   public boolean method5() {
      return this.field19;
   }

   @Generated
   public void method6(boolean var1) {
      this.field19 = var1;
   }
}
