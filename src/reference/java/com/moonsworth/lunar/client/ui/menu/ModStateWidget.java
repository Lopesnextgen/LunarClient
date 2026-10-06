package com.moonsworth.lunar.client.ui.menu;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.alert.mixin.AlertType;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.AnimatedValue;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import java.util.Locale;
import org.jetbrains.annotations.Nullable;

public class ModStateWidget extends GuiWidget {
   @Nullable
   private final ClientOption<Boolean> field16;
   private final AnimatedValue field17 = new AnimatedValue(-1356212614, -14035334);
   private final AnimatedValue field18 = new AnimatedValue(-1344396974, -2219694);
   private final String field19;
   @Nullable
   private final Boolean field20;
   @Nullable
   private final AlertType field21;

   public ModStateWidget(GuiWidget var1, @Nullable ClientOption<Boolean> var2, String var3, @Nullable Boolean var4, @Nullable AlertType var5) {
      super(var1);
      this.field16 = var2;
      this.field19 = var3;
      this.field20 = var4;
      this.field21 = var5;
      if (var2 != null) {
         this.method4((var2x, var3x) -> {
            if (var4 != null) {
               return false;
            }

            var2.method10(!(Boolean)var2.get());
            return true;
         });
      }
   }

   @Override
   public void update() {
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      if (this.method3(var2) && var3 && this.field20 != null) {
         String var4;
         if (this.field21 == AlertType.SERVER) {
            var4 = this.field20 ? "modpackAllowedModDescription" : "modpackDisallowedModDescription";
         } else {
            var4 = this.field20 ? "allowedModDescription" : "disallowedModDescription";
         }

         float var5 = FontRegistry.method9().method4(this.method1(var4, new Object[]{this.field19}).toUpperCase()) + 8.0F;
         float var6 = var2.IIRCROICCRROCOCOIOIHHOCRHOIHIR() + 12.5F;
         float var7 = var2.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() + 9.0F;
         var1.push();
         var1.method44(var0 -> var0.method29().method18());
         var1.method38(0.0F, 0.0F, 10.0F);
         int[] var8 = com.moonsworth.lunar.client.ui.LcuiScreen.method112(var1);
         com.moonsworth.lunar.client.ui.LcuiScreen.method117(
            var1, var6, var7, var5, FontRegistry.method8().getHeight() * 2 + 5, 5.0F, Integer.MIN_VALUE
         );
         FontRegistry.method9()
            .method17(
               var1, this.method1(var4, new Object[]{this.field19}).toUpperCase(), var6 + 4.0F, var7 + 2.0F, Integer.MAX_VALUE, false
            );
         com.moonsworth.lunar.client.ui.LcuiScreen.method110(var1, var8);
         var1.pop();
      }

      int var9 = this.field20 == null
         ? (this.field16 != null && this.field16.get() ? this.field17.method2(var3 && this.method3(var2)) : this.field18.method2(var3 && this.method3(var2)))
         : -1346848584;
      com.moonsworth.lunar.client.ui.LcuiScreen.method101(
         var1, this.x, this.y, this.width, this.height, 10.0F, var9, false, false, true, true
      );
      com.moonsworth.lunar.client.ui.LcuiScreen.method51(
         var1, this.x + 1.0F, this.y + 1.0F, this.width - 2.0F, this.height - 2.0F, 5.0F, 905969663, false, false, true, true
      );
      String var10 = this.method1(
            this.field20 == null ? (this.field16 != null && this.field16.get() ? "enabled" : "disabled") : "locked", new Object[0]
         )
         .toUpperCase(Locale.ROOT)
         .replace("", " ")
         .trim();
      FontRegistry.field8.method14(var1, var10, this.x + this.width / 2.0F, this.y + 6.0F, -1);
   }

   @Override
   public void method4(char var1, KeyCode var2) {
   }

   @Override
   public void close() {
   }
}
