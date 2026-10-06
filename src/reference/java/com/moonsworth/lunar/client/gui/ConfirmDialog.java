package com.moonsworth.lunar.client.gui;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.TextLabelWidget;
import com.moonsworth.lunar.client.coordinates.Coordinates;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump69;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

public class ConfirmDialog extends com.moonsworth.lunar.client.ui.mainmenu.MainMenuScreen {
   private TextLabelWidget field20;
   private static final ResourceLocationBridge field21 = ResourceLocationBridge.create("lunar", "icons/chevron.png");
   private final Consumer<Boolean> field22;
   private String field23;
   private Coordinates field24;
   private float width = 239.0F;
   private float height = 181.0F;
   private final float[] field25 = new float[]{0.1F, 0.1F, 0.1F, 0.1F, 0.1F, 0.1F, 0.25F, 0.5F, 1.0F};

   public ConfirmDialog(String var1, Coordinates var2, Consumer<Boolean> var3) {
      this.field23 = var1;
      this.field24 = var2;
      this.field20 = new TextLabelWidget(null, "cancel");
      this.field22 = var3;
   }

   @Override
   protected List<GuiWidget> method25() {
      return super.method25();
   }

   @Override
   public void init() {
      super.init();
      float var1 = 132.0F;
      float var2 = this.method22() / 2.0F - this.width / 2.0F;
      float var3 = this.method23() / 2.0F - this.height / 2.0F;
      float var4 = this.method22() / 2.0F;
      float var5 = this.method23() / 2.0F - 50.0F;
      this.field20.method2(var4 - var1 / 2.0F, var3 + 155.0F, var1, 16.0F);
   }

   @Override
   public void update() {
   }

   @Override
   public void method10(MixinHelper_4 var1, MarkerModel.Data2 var2) {
      var1.method44(var0 -> var0.method29().method14());
      float var3 = this.method22() / 2.0F - this.width / 2.0F;
      float var4 = this.method23() / 2.0F - this.height / 2.0F;
      com.moonsworth.lunar.client.ui.LcuiScreen.method94(var1, var3, var4 + 24.0F, this.width, 0.5F, 553648127);
      com.moonsworth.lunar.client.ui.LcuiScreen.method103(var1, var3, var4 + 1.0F, this.width, 23.0F, 5.0F, 620756992);
      com.moonsworth.lunar.client.ui.LcuiScreen.method56(var1, var3 - 1.0F, var4, this.width + 2.0F, this.height, 4.0F, 1073741824);
      com.moonsworth.lunar.client.ui.LcuiScreen.method56(var1, var3, var4 + 1.0F, this.width, this.height - 2.0F, 3.0F, 553648127);
      com.moonsworth.lunar.client.ui.LcuiScreen.method117(var1, var3, var4 + 1.0F, this.width, this.height - 2.0F, 5.0F, Integer.MIN_VALUE);
      float var5 = this.method22() / 2.0F;
      float var6 = this.method23() / 2.0F - 84.0F;
      UUID var7 = ThreadModuleDump63.method3().bridge$getSession().bridge$getProfile().getId();
      UUID var8 = this.field24.uuid();
      com.moonsworth.lunar.client.ui.LcuiScreen.method31(var1, ThreadModuleDump69.getWrappedTexture(var7), var3 + 35.0F, var4 + 45.0F, 72.0F, 78.0F, -1);
      com.moonsworth.lunar.client.ui.LcuiScreen.method31(
         var1, ThreadModuleDump69.getWrappedTexture(var8), var3 + 136.0F, var4 + 45.0F, 72.0F, 78.0F, -1
      );
      long var9 = System.currentTimeMillis();
      float var11 = (float)(var9 % 150L) / 150.0F;
      int var12 = this.field25.length - 1 - (int)(var9 / 150L % this.field25.length);
      this.method3(var1, var3 + 109.0F, var4 + 84.0F, var12, var11);
      this.method3(var1, var3 + 117.0F, var4 + 84.0F, var12 + 1, var11);
      this.method3(var1, var3 + 125.0F, var4 + 84.0F, var12 + 2, var11);
      FontRegistry.method19().method14(var1, this.method1("title", new Object[]{this.field24.username()}), var5, var6, -1);
      FontRegistry.method17().method14(var1, this.method1("lineOne", new Object[]{this.field24.username()}), var5, var4 + 137.0F, -1);
      this.field20.method3(var1, var2, true);
      var1.method44(var0 -> var0.method29().method15());
   }

   private void method3(MixinHelper_4 var1, float var2, float var3, int var4, float var5) {
      float var6 = this.field25[(var4 + 1) % this.field25.length];
      float var7 = var6 + (this.field25[var4 % this.field25.length] - var6) * var5;
      com.moonsworth.lunar.client.ui.LcuiScreen.method31(
         var1, field21, var2, var3, 8.0F, 12.5F, ThreadModuleDump23.method11(1.0F, 1.0F, 1.0F, var7)
      );
   }

   @Override
   public String getLanguagePath() {
      return "gui." + this.field23;
   }

   @Override
   public void method11(MarkerModel.Data2 var1, int var2) {
      if (this.field20.method3(var1)) {
         com.moonsworth.lunar.client.ui.LcuiScreen.method15();
         this.field22.accept(false);
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
}
