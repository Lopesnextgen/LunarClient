package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge8Extension33;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.feature.crosshair.Crosshair2;
import com.moonsworth.lunar.client.framework.feature.crosshair.Crosshair3;
import com.moonsworth.lunar.client.framework.feature.crosshair.mixin.Gui2Extension2;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.config.option.OptionFeatureLink;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.mod.render.crosshair.CrosshairStyle;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.moonsworth.lunar.client.ui.LcuiScreen;

public class ClientOptionWidget extends com.moonsworth.lunar.client.ui.widget.OptionWidget<ClientOption<Integer>> {
   private static List<ClientOptionWidget.Data> field16 = null;

   public ClientOptionWidget(ClientOption<Integer> var1, GuiWidget var2) {
      super(var1, var2);
      if (field16 == null) {
         ArrayList var3 = new ArrayList();
         int var4 = 0;

         for (String var8 : Crosshair3.field16) {
            Crosshair2 var9 = Crosshair2.method5(var8);
            var3.add(new ClientOptionWidget.Data(var9, method1(var9, var4++)));
         }

         field16 = Collections.unmodifiableList(var3);
      }

      this.method4((var2x, var3x) -> {
         float var4x = var2x.IIRCROICCRROCOCOIOIHHOCRHOIHIR();
         float var5 = var2x.CRCOHORRCCORCCIIOOIOOCIRRCRHHH();
         int var6 = this.method2(var4x, var5);
         if (var6 >= 0 && var6 < field16.size()) {
            Crosshair2 var7 = field16.get(var6).field1;
            Gui2Extension2 var8x = var7.method12();
            if (var8x == Gui2Extension2.SMALL) {
               var8x = var8x.bigger();
            }

            CrosshairStyle var9x = ((OptionFeatureLink)var1.method1(OptionTraits.field8)).getFeature();
            var9x.method12(var7.method3(var8x, false));
            return true;
         } else {
            return false;
         }
      });
   }

   private static ResourceLocationBridge method1(Crosshair2 var0, int var1) {
      if (var0.isEmpty()) {
         return null;
      }

      int var2 = var0.method12().size();
      BufferedImage var3 = new BufferedImage(var2, var2, 2);
      boolean[] var4 = var0.method13();

      for (int var5 = 0; var5 < var2 * var2; var5++) {
         if (var4[var5]) {
            var3.setRGB(var5 % var3.getWidth(), var5 / var3.getHeight(), -1);
         }
      }

      ResourceLocationBridge var7 = ResourceLocationBridge.create("lunar", "custom_crosshair_preset_" + var1);
      Bridge8Extension33 var6 = Bridge.method8().method22(var3);
      ThreadModuleDump63.method3().bridge$getTextureManager().bridge$loadTexture(var7, var6);
      return var7;
   }

   public int method2(float var1, float var2) {
      if ((var1 - this.x) % 22.75F > 15.0F) {
         return -1;
      }

      if ((var2 - (this.y + 3.0F)) % 25.0F > 15.0F) {
         return -1;
      }

      int var3 = (int)Math.floor((var1 - this.x) / 22.75F);
      int var4 = (int)Math.floor((var2 - (this.y + 3.0F)) / 25.0F);
      return var3 >= 0 && var3 < 14 && var4 == 0 ? var3 : -1;
   }

   @Override
   public void update() {
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      float var4 = var2.IIRCROICCRROCOCOIOIHHOCRHOIHIR();
      float var5 = var2.CRCOHORRCCORCCIIOOIOOCIRRCRHHH();
      int var6 = this.method2(var4, var5);

      for (int var7 = 0; var7 < 14; var7++) {
         float var8 = this.x + 1.0F + var7 * 22.75F;
         LcuiScreen.method53(var1, var8, this.y + 3.0F, 15.0F, 15.0F, 4.0F, 1076176165, 553648127, var6 == var7 ? 1174405119 : 553648127);
         ClientOptionWidget.Data var9 = field16.get(var7);
         if (var9.field2 != null) {
            float var10 = var8 + 6.85F;
            float var11 = this.y + 10.0F;
            int var12 = var9.field1.method12().size();
            int var13 = (int)Math.floor(var12 / 2.0F);
            LcuiScreen.method31(var1, var9.field2, var10 - var13, var11 - var13, var12, var12, -1);
         }
      }
   }

   @Override
   public void method4(char var1, KeyCode var2) {
   }

   @Override
   public void close() {
   }

   @Override
   public float getHeight() {
      return this.getOption().isHidden() ? 0.0F : 30.0F;
   }

   @Override
   public boolean method3() {
      return false;
   }

   @Override
   public String getLanguagePath() {
      return "gui.crosshair_edit";
   }

   private class Data {
      private final Crosshair2 field1;
      private final ResourceLocationBridge field2;

      private Data(Crosshair2 var1, ResourceLocationBridge var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      public Crosshair2 method1() {
         return this.field1;
      }

      public ResourceLocationBridge method2() {
         return this.field2;
      }
   }
}
