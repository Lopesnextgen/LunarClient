package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;

public abstract class TitledWidget extends com.moonsworth.lunar.client.ui.widget.WidgetPanel<GuiWidget> {
   private String title;
   private boolean field19 = true;
   private boolean field20 = true;
   private int field21 = Integer.MIN_VALUE;

   public TitledWidget(GuiWidget var1, String var2) {
      super(var1);
      this.title = var2;
   }

   @Override
   public void method2(float var1, float var2, float var3, float var4) {
      super.method2(var1, var2, var3, var4);
      this.method3(var1, var2, var3, var4);
   }

   public abstract void method3(float var1, float var2, float var3, float var4);

   @Override
   protected List<GuiWidget> method5() {
      return new ArrayList<>();
   }

   @Override
   public void update() {
      super.update();
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      if (this.field20) {
         LcuiScreen.method94(var1, this.x, this.y + 24.0F, this.width, 0.5F, 553648127);
         LcuiScreen.method106(var1, this.x, this.y + 1.0F, this.width, 23.0F, 5.0F, 620756992);
      }

      if (this.field19) {
         LcuiScreen.method94(var1, this.x, this.y + this.height - 30.0F, this.width, 0.5F, 553648127);
         LcuiScreen.method105(var1, this.x, this.y + this.height - 29.5F, this.width, 28.5F, 5.0F, 1157627904);
      }

      LcuiScreen.method56(var1, this.x - 1.0F, this.y, this.width + 2.0F, this.height, 4.0F, 1073741824);
      LcuiScreen.method56(var1, this.x, this.y + 1.0F, this.width, this.height - 2.0F, 3.0F, 553648127);
      LcuiScreen.method117(var1, this.x, this.y + 1.0F, this.width, this.height - 2.0F, 5.0F, this.field21);
      if (this.field20) {
         FontRegistry.method19().method13(var1, this.method1(this.title, new Object[0]), this.x + 8.0F, this.y + 6.0F, -1);
      }

      super.method3(var1, var2, var3);
   }

   @Override
   public void method4(char var1, KeyCode var2) {
      super.method4(var1, var2);
   }

   @Override
   public void close() {
      super.close();
   }

   public String getTitle() {
      return this.method1(this.title, new Object[0]);
   }

   @Generated
   public void setTitle(String var1) {
      this.title = var1;
   }

   @Generated
   public void method6(boolean var1) {
      this.field19 = var1;
   }

   @Generated
   public void method7(boolean var1) {
      this.field20 = var1;
   }

   @Generated
   public void method8(int var1) {
      this.field21 = var1;
   }
}
