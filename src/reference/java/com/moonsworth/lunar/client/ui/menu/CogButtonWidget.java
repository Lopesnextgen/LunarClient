package com.moonsworth.lunar.client.ui.menu;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.AnimatedValue;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;

public class CogButtonWidget extends GuiWidget {
   private final AnimatedValue field16 = new AnimatedValue(-1593835521, -1);
   private static final ResourceLocationBridge field17 = ResourceLocationBridge.create("lunar", "icons/mainmenu/cog-20x20.png");

   public CogButtonWidget(GuiWidget var1, ModMenuWidget var2, Framework7Extension var3) {
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
      int var4 = this.field16.method2(var3 && this.method3(var2)) & 0xFF000000 | 16777215;
      com.moonsworth.lunar.client.ui.LcuiScreen.method31(var1, field17, this.x + 6.0F, this.y + 6.0F, 9.0F, 9.0F, var4);
      com.moonsworth.lunar.client.ui.LcuiScreen.method66(var1, this.x, this.y + 6.0F, this.x + 0.5F, this.y + this.height - 6.0F, 536870911);
   }

   @Override
   public void method4(char var1, KeyCode var2) {
   }

   @Override
   public void close() {
   }
}
