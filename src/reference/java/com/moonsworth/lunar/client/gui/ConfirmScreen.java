package com.moonsworth.lunar.client.gui;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.AnimatedValue;
import com.moonsworth.lunar.client.ui.widget.TextLabelWidget;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ConfirmScreen extends com.moonsworth.lunar.client.ui.LcuiScreen {
   private TextLabelWidget field19;
   private TextLabelWidget field20;
   private AnimatedValue field21;
   private final Consumer<Boolean> field22;
   private Supplier<String[]> field23;
   private String field24;
   private Object[] field25 = new Object[0];
   private Object[] field26 = new Object[0];
   private Object[] field27 = new Object[0];
   private boolean field28;

   public ConfirmScreen(Supplier<String[]> var1, String var2, String var3, Consumer<Boolean> var4) {
      this.field23 = var1;
      this.field22 = var4;
      this.field21 = new AnimatedValue(2000L, -1, -52429);
      this.field19 = new TextLabelWidget(null, var2);
      this.field20 = new TextLabelWidget(null, var3);
   }

   public ConfirmScreen(String var1, Consumer<Boolean> var2) {
      this.field24 = var1;
      this.field21 = new AnimatedValue(2000L, -1, -52429);
      this.field19 = new TextLabelWidget(null, "cancel");
      this.field20 = new TextLabelWidget(null, "understood");
      this.field22 = var2;
   }

   public ConfirmScreen(String var1, String var2, String var3, Consumer<Boolean> var4) {
      this.field24 = var1;
      this.field21 = new AnimatedValue(2000L, -1, -52429);
      this.field19 = new TextLabelWidget(null, var2);
      this.field20 = new TextLabelWidget(null, var3);
      this.field22 = var4;
   }

   @Override
   protected List<GuiWidget> method25() {
      return ImmutableList.of();
   }

   @Override
   public void init() {
      this.field28 = true;
      float var1 = 200.0F;
      float var2 = this.method22() / 2.0F;
      float var3 = this.method23() / 2.0F - 50.0F;
      this.field20.method2(var2 - var1 / 2.0F, var3 + 60.0F, var1, 16.0F);
      this.field19.method2(var2 - var1 / 2.0F, var3 + 80.0F, var1, 16.0F);
      this.field19.setActive(true);
   }

   @Override
   public void update() {
   }

   @Override
   public void method10(MixinHelper_4 var1, MarkerModel.Data2 var2) {
      if (this.field28 && this.field21.method6()) {
         this.field28 = false;
      } else if (!this.field28 && this.field21.method6()) {
         this.field28 = true;
      }

      float var3 = this.method22() / 2.0F - 160.0F;
      float var4 = this.method23() / 2.0F - 60.0F;
      float var5 = 320.0F;
      float var6 = 118.0F;
      com.moonsworth.lunar.client.ui.LcuiScreen.method94(var1, var3, var4 + 24.0F, var5, 0.5F, 553648127);
      com.moonsworth.lunar.client.ui.LcuiScreen.method103(var1, var3, var4 + 1.0F, var5, 23.0F, 5.0F, 620756992);
      com.moonsworth.lunar.client.ui.LcuiScreen.method56(var1, var3 - 1.0F, var4, var5 + 2.0F, var6, 4.0F, 1073741824);
      com.moonsworth.lunar.client.ui.LcuiScreen.method56(var1, var3, var4 + 1.0F, var5, var6 - 2.0F, 3.0F, 553648127);
      com.moonsworth.lunar.client.ui.LcuiScreen.method117(var1, var3, var4 + 1.0F, var5, var6 - 2.0F, 5.0F, Integer.MIN_VALUE);
      float var7 = this.method22() / 2.0F;
      float var8 = this.method23() / 2.0F - 54.0F;
      if (this.field24 == null && this.field23 != null) {
         String[] var9 = this.field23.get();
         if (var9.length >= 1) {
            FontRegistry.method19().method14(var1, var9[0], var7, var8, this.field21.method2(this.field28));

            for (int var10 = 1; var10 < var9.length; var10++) {
               if (!var9[var10].isEmpty()) {
                  FontRegistry.method17().method14(var1, var9[var10], var7, var8 + 20.0F + var10 * 10.0F, -1);
               }
            }
         }
      } else {
         FontRegistry.method19().method14(var1, this.method1("warning", this.field25), var7, var8, this.field21.method2(this.field28));
         FontRegistry.method17().method14(var1, this.method1("lineOne", this.field26), var7, var8 + 30.0F, -1);
         if (!this.method1("lineTwo", new Object[0]).equals("lineTwo")) {
            FontRegistry.method17().method14(var1, this.method1("lineTwo", this.field27), var7, var8 + 40.0F, -1);
         }
      }

      this.field19.method3(var1, var2, true);
      this.field20.method3(var1, var2, true);
   }

   @Override
   public String getLanguagePath() {
      return super.getLanguagePath() + "." + this.field24;
   }

   @Override
   public void method11(MarkerModel.Data2 var1, int var2) {
      if (this.field19.method3(var1)) {
         com.moonsworth.lunar.client.ui.LcuiScreen.method15();
         this.field22.accept(false);
      } else if (this.field20.method3(var1)) {
         com.moonsworth.lunar.client.ui.LcuiScreen.method15();
         this.field22.accept(true);
      }
   }

   @Override
   public void method12(MarkerModel.Data2 var1, int var2) {
   }

   @Override
   public void method14(char var1, KeyCode var2) {
   }

   @Override
   public void close() {
   }

   public void method6(Object... var1) {
      this.field25 = var1;
   }

   public void method7(Object... var1) {
      this.field26 = var1;
   }

   public void method8(Object... var1) {
      this.field27 = var1;
   }
}
