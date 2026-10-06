package com.moonsworth.lunar.client.ui.menu;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.KeybindToggleWidget;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.mod.FeatureToggleKeybind;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;

public class KeybindToggleRow extends com.moonsworth.lunar.client.ui.widget.TitledWidget {
   private final Runnable field22;
   private final KeybindToggleWidget field23;
   private final com.moonsworth.lunar.client.ui.widget.TextLabelWidget field24;
   private final float field25;
   private float field26;
   private float field27;
   private float field28;
   private float field29;

   public KeybindToggleRow(GuiWidget var1, FeatureToggleKeybind var2, String var3, Runnable var4) {
      super(var1, "toggleKeybind");
      this.field22 = var4;
      this.field25 = Math.max(180.0F, FontRegistry.method14().method4(var3) + 112.0F);
      this.method8(-268435456);
      this.field23 = new KeybindToggleWidget(var2.method9(), this);
      this.field23.setName(var3);
      this.field24 = new com.moonsworth.lunar.client.ui.widget.TextLabelWidget(this, "done");
      this.field24.method4((var1x, var2x) -> {
         this.close();
         return true;
      });
      this.method14(ImmutableList.of(this.field23, this.field24));
   }

   @Override
   public void method2(float var1, float var2, float var3, float var4) {
      this.field26 = var1;
      this.field27 = var2;
      this.field28 = var3;
      this.field29 = var4;
      super.method2(var1 + var3 / 2.0F - this.field25 / 2.0F, var2 + var4 / 2.0F - 40.0F, this.field25, 80.0F);
   }

   @Override
   public void method3(float var1, float var2, float var3, float var4) {
      this.field23.RIIIOHCCHRRRORICCHIIHHOORIIOIR(var1 + 8.0F, var2 + 34.0F, var3 - 16.0F);
      this.field24.RIIICIRHRCIHOOOORHOICRIICCCRHR(var1 + var3 - 56.0F, var2 + var4 - 24.0F, 50.0F, 18.0F);
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      com.moonsworth.lunar.client.ui.LcuiScreen.method94(var1, this.field26, this.field27, this.field28, this.field29, -805306368);
      super.method3(var1, var2, var3);
      if (var3 && this.field23.method2().method3(var2)) {
         this.field23.method1(var1, var2);
      }
   }

   @Override
   public void method4(char var1, KeyCode var2) {
      if (var2 == KeyCode.KEY_ESCAPE && !this.isEditing()) {
         this.close();
      } else {
         super.method4(var1, var2);
      }
   }

   @Override
   public void close() {
      super.close();
      this.field22.run();
   }
}
