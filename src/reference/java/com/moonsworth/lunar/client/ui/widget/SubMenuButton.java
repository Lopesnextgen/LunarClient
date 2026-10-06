package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.render.font.CachedFontImpl;

public class SubMenuButton extends MenuButtonWidget {
   public SubMenuButton(GuiWidget var1, ResourceLocationBridge resourceLocationBridge, CachedFontImpl fishing2, String text, int value, int value2) {
      super(var1, resourceLocationBridge, fishing2, text, value, value2);
   }

   @Override
   public void method14(MixinHelper_4 var1) {
      LcuiScreen.method94(var1, this.x, this.y, this.width, this.height, this.getColor());
   }
}
