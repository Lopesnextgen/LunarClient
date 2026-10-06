package com.moonsworth.lunar.client.framework.feature.screenshot;

import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.util.math.MathUtils;
import lombok.Generated;

public class ProgressNotification extends com.moonsworth.lunar.client.ui.notification.Notification {
   private static final int field17 = 10;
   private int field18;

   public ProgressNotification(String text1) {
      super(text1);
   }

   protected void method6() {
      super.method6();
      this.height += 14.0F;
   }

   public long method7() {
      if (this.field18 < 100) {
         this.IRHORHICICHICHCRIHHHHHHRCROICC = System.currentTimeMillis();
      }

      return super.method7();
   }

   public void method2(AbstractRenderContext bridgeextension_91, float value2, float value3) {
      super.method2(bridgeextension_91, value2, value3);
      int number4 = MathUtils.method6(this.field18, 0, 100);
      float value5 = value2 + 2.0F;
      float value6 = value3 + this.height - 10.0F - 2.0F;
      float value7 = (this.width - 6.0F) * (number4 / 100.0F);
      LcuiScreen.method97(bridgeextension_91, value5, value6, this.width - 4.0F, 10.0F, 1621271202);
      LcuiScreen.method97(bridgeextension_91, value5 + 1.0F, value6 + 1.0F, value7, 8.0F, -11561732);
      FontRegistry.method8().method6(bridgeextension_91, Integer.toString(number4) + "%", value5 + (this.width - 4.0F) / 2.0F, value6 + 1.0F, -1);
   }

   @Generated
   public void method4(int number1) {
      this.field18 = number1;
   }
}
