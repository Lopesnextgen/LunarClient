package com.moonsworth.lunar.client.ui.widget;

import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import com.moonsworth.lunar.client.config.option.OptionFeatureLink;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.mod.render.crosshair.CrosshairStyle;
import com.moonsworth.lunar.client.util.ThreadModuleDump49;
import com.moonsworth.lunar.client.util.ThreadModuleDump67;
import com.moonsworth.lunar.client.util.ThreadModuleDump49.Type;
import com.moonsworth.lunar.client.ui.LcuiScreen;

public class BiomePreviewWidget extends com.moonsworth.lunar.client.ui.widget.OptionWidget<ClientOption<Integer>> {
   public static final ResourceLocationBridge[] field16 = new ResourceLocationBridge[]{
      ResourceLocationBridge.create("lunar", "previews/biome_0.png"),
      ResourceLocationBridge.create("lunar", "previews/biome_1.png"),
      ResourceLocationBridge.create("lunar", "previews/biome_2.png"),
      ResourceLocationBridge.create("lunar", "previews/biome_3.png")
   };
   private boolean field17 = false;
   private float field18 = 0.0F;
   private float field19 = 0.0F;
   private float field20 = 0.0F;
   private float field21 = 0.0F;

   public BiomePreviewWidget(ClientOption<Integer> var1, GuiWidget var2) {
      super(var1, var2);
      this.method4((var2x, var3) -> {
         if (!(var2x.HHHCHORHIHRCOHIOICICICHCRRICCI() < this.x + this.width - 85.0F) && !(var2x.HHHCHORHIHRCOHIOICICICHCRRICCI() > this.x + this.width)) {
            var1.method10((int)Math.floor((var2x.IHRCCHHROHIRCOOOHRRIHOORRHIOHO() - this.y) * 4.0 / this.height));
            return true;
         } else {
            return false;
         }
      });
   }

   @Override
   public void update() {
      float var1 = this.field17 ? 0.35F : 0.15F;
      this.field18 = ThreadModuleDump67.lerp(this.field18, this.field20, var1);
      this.field19 = ThreadModuleDump67.lerp(this.field19, this.field21, var1);
   }

   @Override
   public void method3(MixinHelper_4 var1, MarkerModel.Data2 var2, boolean var3) {
      float var4 = this.width - 85.0F;
      this.field17 = var3 && this.method3(var2) && var2.HHHCHORHIHRCOHIOICICICHCRRICCI() < this.x + var4;
      int var5 = Math.min(3, Math.max(0, (Integer)this.option.get()));
      if (this.field17) {
         this.field20 = (var2.IIRCROICCRROCOCOIOIHHOCRHOIHIR() - this.x) / var4 * 0.4F - 0.2F;
         this.field21 = (var2.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() - this.y) / this.height * 0.4F - 0.2F;
      } else {
         this.field20 = 0.0F;
         this.field21 = 0.0F;
      }

      LcuiScreen.method35(
         var1, field16[var5], this.x, this.y, var4, this.height, 0.2F + this.field18, 0.2F + this.field19, 0.8F + this.field18, 0.8F + this.field19, -1
      );

      for (int var6 = 0; var6 < 4; var6++) {
         LcuiScreen.method35(
            var1, field16[var6], this.x + this.width - 85.0F, this.y + var6 * this.height / 4.0F, 85.0F, this.height / 4.0F, 0.0F, 0.2F, 1.0F, 0.8F, -1
         );
         if (var5 == var6) {
            this.method3(var1, this.x + var4, this.y + var6 * this.height / 4.0F, 85.0F, this.height / 4.0F - 1.0F, 1.0F, -10496);
         }
      }

      CrosshairStyle var8 = (CrosshairStyle)((OptionFeatureLink)this.option.method1(OptionTraits.field8)).getFeature();
      if (this.field17) {
         ThreadModuleDump49.method2(Type.HIDDEN);
         var8.method15(var1, var2, true);
      } else {
         float var7 = this.method2(var8);
         var8.method15(var1, new MarkerModel.Data2(this.x + var4 / 2.0F - var7, this.y + this.height / 2.0F - var7), true);
      }
   }

   private float method2(CrosshairStyle var1) {
      return var1.method22() ? 0.5F : 0.0F;
   }

   private void method3(MixinHelper_4 var1, float var2, float var3, float var4, float var5, float var6, int var7) {
      LcuiScreen.method94(var1, var2 - var6, var3 - var6, var4 + var6 * 2.0F, var6, var7);
      LcuiScreen.method94(var1, var2 - var6, var3 + var5, var4 + var6 * 2.0F, var6, var7);
      LcuiScreen.method94(var1, var2 - var6, var3, var6, var5, var7);
      LcuiScreen.method94(var1, var2 + var4, var3, var6, var5, var7);
   }

   @Override
   public void method4(char var1, KeyCode var2) {
   }

   @Override
   public void close() {
   }

   @Override
   public float getHeight() {
      return this.getOption().isHidden() ? 0.0F : 96.0F;
   }
}
