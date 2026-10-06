package com.moonsworth.lunar.client.ui.menu;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.render.font.CachedFontImpl;
import com.moonsworth.lunar.client.ui.AnimatedValue;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import lombok.Generated;

public class EditableLabelWidget extends GuiWidget {
   protected String text;
   protected final CachedFontImpl field16;
   protected AnimatedValue field17 = new AnimatedValue(540752699, 553648127);
   protected boolean active;
   protected float offset;

   public EditableLabelWidget(GuiWidget var1, String var2) {
      this(var1, var2, FontRegistry.method11());
   }

   public EditableLabelWidget(GuiWidget var1, String var2, CachedFontImpl var3) {
      super(var1);
      this.text = var2;
      this.field16 = var3;
      this.width = var3.method4(this.method1(this.text, new Object[0]).replace("", " ").trim()) + 16.0F;
   }

   public void method1(float var1, float var2) {
      super.method2(var1, var2, this.field16.method4(this.method1(this.text, new Object[0]).replace("", " ").trim()) + 16.0F, 16.0F);
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
      com.moonsworth.lunar.client.ui.LcuiScreen.method53(
         var1, this.x, this.y, this.width, this.height, 4.0F, 0, 547529378, this.field17.method2(this.active || var3 && this.method3(var2))
      );
      float var4 = this.field16.getHeight();
      String var5 = this.method1(this.text, new Object[0]).replace("", " ").trim();
      this.field16.method14(var1, var5, this.x + this.width / 2.0F + 1.0F, this.y + this.height / 2.0F - var4 + 1.0F - this.offset, 536870912);
      this.field16
         .method14(
            var1, var5, this.x + this.width / 2.0F, this.y + this.height / 2.0F - var4 - this.offset, this.method3(var2) && var3 ? -4275267 : -1765882947
         );
   }

   @Generated
   public String getText() {
      return this.text;
   }

   @Generated
   public void setActive(boolean var1) {
      this.active = var1;
   }

   @Generated
   public void setOffset(float var1) {
      this.offset = var1;
   }
}
