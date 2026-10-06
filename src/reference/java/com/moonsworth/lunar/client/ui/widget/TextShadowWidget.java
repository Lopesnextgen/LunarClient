package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.AnimatedValue;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class TextShadowWidget extends GuiWidget {
   private AnimatedValue field16 = new AnimatedValue(-1593835521, -1);
   private ResourceLocationBridge field17;
   private final String field18;
   private boolean textShadow = false;

   public TextShadowWidget(GuiWidget var1, String var2, @NotNull ResourceLocationBridge var3) {
      super(var1);
      this.field18 = var2;
      this.field17 = var3;
   }

   @Override
   public void update() {
   }

   @Override
   public void close() {
   }

   @Override
   public void method4(char var1, KeyCode var2) {
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      boolean var4 = var3 && this.method3(var2);
      LcuiScreen.method31(var1, this.field17, this.x, this.y + this.height / 2.0F - 4.0F, 8.0F, 8.0F, this.field16.method2(var4));
      var1.method44(var0 -> {
         var0.method29().method16();
         var0.method29().method14();
      });
      var1.method19(
         ThreadModuleDump63.method10(),
         this.method1(this.field18, new Object[0]),
         this.x + 11.0F,
         this.y + this.height / 2.0F - 4.0F,
         this.field16.method2(var4),
         this.textShadow
      );
      this.method3(var1, var2, var3);
      if (this.field18.isEmpty()) {
         this.width = 8.0F;
      } else {
         this.width = 11.0F + ThreadModuleDump63.method10().bridge$getStringWidth(this.field18);
      }
   }

   @Generated
   public void method3(AnimatedValue var1) {
      this.field16 = var1;
   }

   @Generated
   public void method4(ResourceLocationBridge var1) {
      this.field17 = var1;
   }

   @Generated
   public void setTextShadow(boolean var1) {
      this.textShadow = var1;
   }
}
