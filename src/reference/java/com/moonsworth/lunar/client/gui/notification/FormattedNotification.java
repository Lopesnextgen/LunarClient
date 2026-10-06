package com.moonsworth.lunar.client.gui.notification;

import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.config.option.KeyCombo;
import com.moonsworth.lunar.client.ui.notification.Notification;

public abstract class FormattedNotification extends Notification {
   protected String field17;

   public FormattedNotification(String var1, String var2) {
      super(var1);
      this.field17 = var2;
      this.updateWidth();
   }

   public FormattedNotification(String var1, String var2, String var3) {
      super(var1, var2);
      this.field17 = var3;
      this.updateWidth();
   }

   @Override
   public void method2(AbstractRenderContext var1, float var2, float var3) {
      super.method2(var1, var2, var3);
      LcuiScreen.method97(var1, var2 + 5.0F, var3 + this.getHeight() - 13.0F, this.getWidth() - 12.0F, 0.5F, 1090519039);
      this.method3(var1, var2, var3);
   }

   protected void method3(AbstractRenderContext var1, float var2, float var3) {
      LcuiScreen.method141(
         var1,
         this.field17,
         this,
         (KeyCombo)Client.method109().method41().method8().method22().get(),
         FontRegistry.method9(),
         var2 + 5.0F,
         var3 + this.getHeight() - 11.0F,
         false,
         1979711487,
         -1090519040,
         -1241513985
      );
   }

   @Override
   protected void method5(AbstractRenderContext var1, float var2, float var3) {
      super.method5(var1, var2, var3 - 5.0F);
   }

   @Override
   protected void updateWidth() {
      super.updateWidth();
      this.width = Math.max(
         this.width,
         FontRegistry.method9()
               .method4(
                  this.method1(
                     this.field17, new Object[]{"  " + LcuiScreen.method139((KeyCombo)Client.method109().method41().method8().method22().get()) + "  "}
                  )
               )
            + 12.0F
      );
   }

   @Override
   protected void method6() {
      super.method6();
      this.height = Math.max(20.0F, this.height + 14.0F);
   }
}
