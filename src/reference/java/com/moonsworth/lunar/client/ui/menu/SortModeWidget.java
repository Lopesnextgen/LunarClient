package com.moonsworth.lunar.client.ui.menu;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.AnimatedValue;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.config.GeneralSettings.Type4;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import java.util.List;
import java.util.Locale;

public class SortModeWidget extends GuiWidget {
   private final AnimatedValue field16 = new AnimatedValue(-1593835521, -1);

   public SortModeWidget(GuiWidget var1) {
      super(var1);
   }

   @Override
   public void update() {
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      Type4 var4 = (Type4)Client.method109().method41().method6().method49().get();
      String var5 = var4.name().toLowerCase(Locale.ROOT);
      ResourceLocationBridge var6 = ResourceLocationBridge.create("lunar", "icons/sort_icons/" + var5 + ".png");
      int var7 = this.field16.method2(var3 && this.method3(var2)) & 0xFF000000 | 16777215;
      com.moonsworth.lunar.client.ui.LcuiScreen.method31(var1, var6, this.x + 1.0F, this.y + 1.0F, 12.0F, 12.0F, var7);
      if (this.method3(var2)) {
         String var8 = var4.getTranslationKey() + "Description";
         String var9 = this.method1(var8, new Object[0]);
         if (!var9.equals(var8)) {
            var1.method38(0.0F, 0.0F, 100.0F);
            List var10 = FontRegistry.method9().method25(var9, 150.0);
            float var11 = var10.size() > 1 ? 150.0F : FontRegistry.method8().method4(var9);
            com.moonsworth.lunar.client.ui.LcuiScreen.method54(
               var1,
               var2.IIRCROICCRROCOCOIOIHHOCRHOIHIR() + 8.0F,
               var2.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() + 6.0F,
               var11 + 10.0F,
               8 * var10.size() + 5,
               4.0F,
               -1879048192
            );
            int var12 = 0;

            for (String var14 : var10) {
               FontRegistry.method8()
                  .method13(var1, var14, var2.IIRCROICCRROCOCOIOIHHOCRHOIHIR() + 12.5F, var2.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() + 9.0F + var12 * 8, -1);
               var12++;
            }

            var1.method38(0.0F, 0.0F, -100.0F);
         }
      }
   }

   @Override
   public String getLanguagePath() {
      return super.getLanguagePath() + ".sortModes";
   }

   @Override
   public void method4(char var1, KeyCode var2) {
   }

   @Override
   public void close() {
   }
}
