package com.moonsworth.lunar.client.framework.feature.screenshot;

import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.util.ThreadModuleDump67;
import lombok.Generated;

public class Gui2Iterator extends com.moonsworth.lunar.client.gui.notification.Notification {
   private static final int field17 = 10;
   private int field18;

   public Gui2Iterator(String var1) {
      super(var1);
   }

   protected void method6() {
      super.method6();
      this.height += 14.0F;
   }

   public long method7() {
      if (this.field18 < 100) {
         this.field7 = System.currentTimeMillis();
      }

      return super.method7();
   }

   public void method2(AbstractRenderContext var1, float value, float value2) {
      super.method2(var1, value, value2);
      int var4 = ThreadModuleDump67.method6(this.field18, 0, 100);
      float var5 = value + 2.0F;
      float var6 = value2 + this.height - 10.0F - 2.0F;
      float var7 = (this.width - 6.0F) * (var4 / 100.0F);
      LcuiScreen.method97(var1, var5, var6, this.width - 4.0F, 10.0F, 1621271202);
      LcuiScreen.method97(var1, var5 + 1.0F, var6 + 1.0F, var7, 8.0F, -11561732);
      FontRegistry.method8().method6(var1, Integer.toString(var4) + "%", var5 + (this.width - 4.0F) / 2.0F, var6 + 1.0F, -1);
   }

   @Generated
   public void method4(int var1) {
      this.field18 = var1;
   }
}
