package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.render.font.CachedFontImpl;
import com.moonsworth.lunar.client.ui.AnimatedValue;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import lombok.Generated;

public class TextFieldWidget extends GuiWidget {
   private String text;
   private final CachedFontImpl field16;
   private final AnimatedValue field17 = new AnimatedValue(0, 1157627904);
   private int color = -1;
   private boolean active;
   private ResourceLocationBridge field18 = null;

   public TextFieldWidget(GuiWidget var1, String var2, int var3) {
      this(var1, var2, FontRegistry.method8(), var3);
   }

   public TextFieldWidget(GuiWidget var1, String var2) {
      this(var1, var2, FontRegistry.method8(), -1);
   }

   public TextFieldWidget(GuiWidget var1, String var2, CachedFontImpl var3, int var4) {
      super(var1);
      this.text = var2;
      this.field16 = var3;
      this.color = var4;
   }

   public void setText(String var1) {
      this.text = var1;
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
      String var5 = this.method1(this.text, new Object[0]);
      if (this.field18 != null) {
         LcuiScreen.method31(
            var1,
            this.field18,
            this.x + 1.0F,
            this.y + 1.0F,
            12.0F,
            12.0F,
            ThreadModuleDump23.method11(1.0F, 1.0F, 1.0F, 0.65F + 0.35F * ThreadModuleDump23.method4(this.field17.method2(var4)))
         );
      } else {
         LcuiScreen.method94(var1, this.x, this.y, this.width, this.height, this.field17.method2(this.active || var4));
         float var6 = this.field16.getHeight();
         float var7 = this.x + 5.0F;
         if (this.color != -1) {
            LcuiScreen.method78(var1, var7 + 3.0F, this.y + this.height / 2.0F + 0.5F, 2.0, this.color | 0xFF000000);
            var7 += 10.0F;
         }

         FontRegistry.method17().method13(var1, var5, var7, this.y + this.height / 2.0F - var6, 536870912);
         FontRegistry.method17().method13(var1, var5, var7 - 1.0F, this.y + this.height / 2.0F - var6 - 1.0F, -538778910);
      }

      if (var4) {
         float var8 = FontRegistry.method8().method4(var5);
         LcuiScreen.method54(
            var1, var2.IIRCROICCRROCOCOIOIHHOCRHOIHIR() + 8.0F, var2.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() + 6.0F, var8 + 10.0F, 14.0F, 4.0F, -1879048192
         );
         FontRegistry.method8().method13(var1, var5, var2.IIRCROICCRROCOCOIOIHHOCRHOIHIR() + 12.5F, var2.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() + 9.0F, -1);
      }
   }

   public String getText() {
      return this.method1(this.text, new Object[0]);
   }

   @Generated
   public void setActive(boolean var1) {
      this.active = var1;
   }

   @Generated
   public void method3(ResourceLocationBridge var1) {
      this.field18 = var1;
   }
}
