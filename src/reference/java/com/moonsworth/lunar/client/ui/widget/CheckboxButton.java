package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import lombok.Generated;

public class CheckboxButton extends GuiWidget {
   private Runnable field16;
   private boolean state;
   private String text;

   public CheckboxButton(GuiWidget var1, boolean var2) {
      this(var1, var2, "");
   }

   public CheckboxButton(GuiWidget var1, boolean var2, String var3) {
      super(var1);
      this.method4((var1x, var2x) -> {
         this.state = !this.state;
         if (this.field16 != null) {
            this.field16.run();
         }

         return true;
      });
      this.state = var2;
      this.text = var3;
   }

   @Override
   public void update() {
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      FontRegistry.method17().method13(var1, this.text, this.x + 13.0F, this.y + 1.5F, -4079426);
      LcuiScreen.method101(
         var1, this.x + 3.0F, this.y + 2.0F, 8.0F, 8.0F, 4.0F, var3 && this.method3(var2) ? -11561732 : -1437625092, true, true, true, true
      );
      LcuiScreen.method51(var1, this.x + 4.0F, this.y + 3.0F, 6.0F, 6.0F, 2.0F, 905969663, true, true, true, true);
      if (this.state) {
         LcuiScreen.method39(var1, ResourceLocationBridge.create("lunar", "icons/settings/checked-14x14.png"), 3.0F, this.x + 4.0F, this.y + 2.6F, -1);
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
      this.field16 = var1;
   }

   @Generated
   public boolean isState() {
      return this.state;
   }
}
