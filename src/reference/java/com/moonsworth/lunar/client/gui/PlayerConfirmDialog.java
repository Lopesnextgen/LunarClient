package com.moonsworth.lunar.client.gui;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.TextLabelWidget;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump69;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

public class PlayerConfirmDialog extends com.moonsworth.lunar.client.ui.LcuiScreen {
   private final TextLabelWidget field19;
   private final TextLabelWidget field20;
   private final Consumer<Boolean> field21;
   private final String field22;
   private final String field23;
   private final UUID field24;
   private final float field25 = 208.0F;
   private final float field26 = 181.0F;

   public PlayerConfirmDialog(String var1, String var2, UUID var3, String var4, Consumer<Boolean> var5) {
      this.field22 = var1;
      this.field23 = var2;
      this.field24 = var3;
      this.field20 = new TextLabelWidget(null, var4);
      this.field19 = new TextLabelWidget(null, "cancel");
      this.field21 = var5;
   }

   @Override
   protected List<GuiWidget> method25() {
      return ImmutableList.of();
   }

   @Override
   public void init() {
      float var1 = 60.0F;
      float var2 = this.method22() / 2.0F - 104.0F;
      float var3 = this.method23() / 2.0F - 90.5F;
      float var4 = this.method22() / 2.0F;
      float var5 = this.method23() / 2.0F - 50.0F;
      this.field19.method2(var2 + 40.0F, var3 + 155.0F, var1, 16.0F);
      this.field20.method2(var2 + 108.0F, var3 + 155.0F, var1, 16.0F);
   }

   @Override
   public void update() {
   }

   @Override
   public void method10(MixinHelper_4 var1, MarkerModel.Data2 var2) {
      var1.method44(var0 -> var0.method29().method14());
      float var3 = this.method22() / 2.0F - 104.0F;
      float var4 = this.method23() / 2.0F - 90.5F;
      com.moonsworth.lunar.client.ui.LcuiScreen.method94(var1, var3, var4 + 24.0F, 208.0F, 0.5F, 553648127);
      com.moonsworth.lunar.client.ui.LcuiScreen.method103(var1, var3, var4 + 1.0F, 208.0F, 23.0F, 5.0F, 620756992);
      com.moonsworth.lunar.client.ui.LcuiScreen.method56(var1, var3 - 1.0F, var4, 210.0F, 181.0F, 4.0F, 1073741824);
      com.moonsworth.lunar.client.ui.LcuiScreen.method56(var1, var3, var4 + 1.0F, 208.0F, 179.0F, 3.0F, 553648127);
      com.moonsworth.lunar.client.ui.LcuiScreen.method117(var1, var3, var4 + 1.0F, 208.0F, 179.0F, 5.0F, Integer.MIN_VALUE);
      float var5 = this.method22() / 2.0F;
      float var6 = this.method23() / 2.0F - 84.0F;
      com.moonsworth.lunar.client.ui.LcuiScreen.method31(
         var1, ThreadModuleDump69.getWrappedTexture(this.field24), var3 + 67.0F, var4 + 45.0F, 72.0F, 78.0F, -1
      );
      FontRegistry.method19()
         .method14(
            var1, this.method1("title", new Object[]{ThreadModuleDump63.method7().bridge$getName(), this.field23}), var5, var6, -1
         );
      FontRegistry.method17()
         .method14(
            var1,
            this.method1("lineOne", new Object[]{ThreadModuleDump63.method7().bridge$getName(), this.field23}),
            var5,
            var4 + 137.0F,
            -1
         );
      this.field19.method3(var1, var2, true);
      this.field20.method3(var1, var2, true);
      var1.method44(var0 -> var0.method29().method15());
   }

   @Override
   public String getLanguagePath() {
      return super.getLanguagePath() + "." + this.field22;
   }

   @Override
   public void method11(MarkerModel.Data2 var1, int var2) {
      if (this.field19.method3(var1)) {
         com.moonsworth.lunar.client.ui.LcuiScreen.method15();
         this.field21.accept(false);
      }

      if (this.field20.method3(var1)) {
         com.moonsworth.lunar.client.ui.LcuiScreen.method15();
         this.field21.accept(true);
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
