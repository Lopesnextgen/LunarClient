package com.moonsworth.lunar.client.ui.menu;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.AnimatedValue;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import java.util.Locale;

public class OptionsButtonWidget extends GuiWidget {
   private final AnimatedValue field16 = new AnimatedValue(553648127, 1358954495);
   private static final ResourceLocationBridge field17 = ResourceLocationBridge.create("lunar", "icons/mainmenu/cog-20x20.png");

   public OptionsButtonWidget(GuiWidget var1, ModMenuWidget var2, Framework7Extension var3) {
      super(var1);
      this.method4((var2x, var3x) -> {
         try {
            var2.method2(new FeatureSettingsWidget(var2, var3));
            var2.method3(0);
         } catch (Exception var5) {
            var5.printStackTrace();
         }

         return true;
      });
   }

   @Override
   public void update() {
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      com.moonsworth.lunar.client.ui.LcuiScreen.method94(
         var1, this.x, this.y, this.width, this.height, this.field16.method2(var3 && this.method3(var2))
      );
      com.moonsworth.lunar.client.ui.LcuiScreen.method94(var1, this.x, this.y + 1.0F, 1.0F, this.height - 2.0F, 553648127);
      com.moonsworth.lunar.client.ui.LcuiScreen.method94(var1, this.x + this.width - 1.0F, this.y + 1.0F, 1.0F, this.height - 2.0F, 553648127);
      com.moonsworth.lunar.client.ui.LcuiScreen.method94(
         var1, this.x + this.width - 21.0F, this.y + 1.0F, 1.0F, this.height - 2.0F, 553648127
      );
      com.moonsworth.lunar.client.ui.LcuiScreen.method94(var1, this.x, this.y, this.width, 1.0F, 553648127);
      com.moonsworth.lunar.client.ui.LcuiScreen.method94(var1, this.x, this.y + this.height - 1.0F, this.width, 1.0F, 553648127);
      com.moonsworth.lunar.client.ui.LcuiScreen.method31(var1, field17, this.x + this.width - 15.0F, this.y + 5.5F, 9.0F, 9.0F, -1);
      String var4 = this.method1("options", new Object[0]).toUpperCase(Locale.ROOT).replace("", " ").trim();
      FontRegistry.method11().method14(var1, var4, this.x + (this.width - 21.0F) / 2.0F, this.y + 6.0F, -1);
   }

   @Override
   public void method4(char var1, KeyCode var2) {
   }

   @Override
   public void close() {
   }
}
