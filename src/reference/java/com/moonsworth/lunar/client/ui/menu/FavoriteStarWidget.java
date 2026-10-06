package com.moonsworth.lunar.client.ui.menu;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework10;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;

public class FavoriteStarWidget extends GuiWidget {
   private static final ResourceLocationBridge field16 = ResourceLocationBridge.create("lunar", "icons/star-64x64.png");
   private static final ResourceLocationBridge field17 = ResourceLocationBridge.create("lunar", "icons/star-filled-64x64.png");
   private final Framework7Extension field18;

   public FavoriteStarWidget(GuiWidget var1, ModMenuWidget var2, Framework7Extension var3) {
      super(var1);
      this.field18 = var3;
      this.method4((var1x, var2x) -> {
         Framework10 var3x = (Framework10)this.field18.method1(Framework.field15);
         if (var3x != null) {
            var3x.method7(!var3x.method6());
         }

         return true;
      });
   }

   private boolean method1() {
      Framework10 var1 = (Framework10)this.field18.method1(Framework.field15);
      return var1 != null && var1.method6();
   }

   @Override
   public void update() {
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      boolean var4 = this.method1();
      if (var4 || this.field4.method1(var2)) {
         int var5 = this.method3(var2) ? (var4 ? -8392 : -1) : (var4 ? -14270 : -5131328);
         com.moonsworth.lunar.client.ui.LcuiScreen.method31(var1, var4 ? field17 : field16, this.x + 1.0F, this.y + 1.0F, 9.0F, 9.0F, var5);
      }
   }

   @Override
   public void method4(char var1, KeyCode var2) {
   }

   @Override
   public void close() {
   }
}
