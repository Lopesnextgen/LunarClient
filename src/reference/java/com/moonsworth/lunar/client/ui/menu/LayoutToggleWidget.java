package com.moonsworth.lunar.client.ui.menu;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.AnimatedValue;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;

public class LayoutToggleWidget extends GuiWidget {
   private final AnimatedValue field16 = new AnimatedValue(-1593835521, -1);

   public LayoutToggleWidget(GuiWidget var1) {
      super(var1);
   }

   @Override
   public void update() {
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      int var4 = this.field16.method2(var3 && this.method3(var2)) & 0xFF000000 | 16777215;
      if ((Boolean)Client.method109().method41().method6().method68().get()) {
         com.moonsworth.lunar.client.ui.LcuiScreen.method31(
            var1, ResourceLocationBridge.create("lunar", "icons/large-menu-24x24.png"), this.x + 1.0F, this.y + 1.0F, 12.0F, 12.0F, var4
         );
      } else {
         com.moonsworth.lunar.client.ui.LcuiScreen.method31(
            var1, ResourceLocationBridge.create("lunar", "icons/compact-menu-24x24.png"), this.x + 1.0F, this.y + 1.0F, 12.0F, 12.0F, var4
         );
      }
   }

   @Override
   public void method4(char var1, KeyCode var2) {
   }

   @Override
   public void close() {
   }
}
