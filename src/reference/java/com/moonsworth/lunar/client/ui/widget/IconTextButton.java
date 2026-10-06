package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.render.font.CachedFontImpl;
import com.moonsworth.lunar.client.util.Annotation;

public class IconTextButton extends MenuButtonWidget {
   public IconTextButton(
      GuiWidget var1,
      ResourceLocationBridge var2,
      CachedFontImpl var3,
      @Annotation(method1 = Annotation.Type.GUI_COMPONENT) String var4,
      int var5,
      int var6
   ) {
      super(var1, var2, var3, var4, var5, var6);
   }

   public IconTextButton(
      GuiWidget var1, CachedFontImpl var2, @Annotation(method1 = Annotation.Type.GUI_COMPONENT) String var3, int var4, int var5
   ) {
      super(var1, var2, var3, var4, var5);
   }

   @Override
   public void method14(MixinHelper_4 var1) {
      LcuiScreen.method53(var1, this.x, this.y, this.width, this.height, 5.0F, this.method27(), 553648127, this.getColor());
   }
}
