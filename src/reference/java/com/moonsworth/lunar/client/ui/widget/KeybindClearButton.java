package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import lombok.Generated;

public class KeybindClearButton extends GuiWidget {
   private static final ResourceLocationBridge field16 = ResourceLocationBridge.create("lunar", "icons/assets/deny-16x16.png");
   private Runnable field17;
   private boolean state;

   public KeybindClearButton(GuiWidget var1, boolean var2) {
      super(var1);
      this.method4((var1x, var2x) -> {
         this.state = !this.state;
         if (this.field17 != null) {
            this.field17.run();
         }

         return true;
      });
      this.state = var2;
   }

   @Override
   public void update() {
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      LcuiScreen.method94(var1, this.x, this.y, this.width, this.height, var3 & this.method3(var2) ? 1088611042 : 551740130);
      if (this.state) {
         LcuiScreen.method31(var1, field16, this.x + 2.0F, this.y + 2.0F, this.width - 4.0F, this.height - 4.0F, -32640);
      }
   }

   @Override
   public void method4(char var1, KeyCode var2) {
   }

   @Override
   public void close() {
   }

   @Generated
   public void method3(Runnable var1) {
      this.field17 = var1;
   }

   @Generated
   public boolean isState() {
      return this.state;
   }
}
