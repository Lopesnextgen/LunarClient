package com.moonsworth.lunar.client.framework.feature.gui;

import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.fog.click.Click.Extension;
import com.moonsworth.lunar.client.framework.feature.markers.Markers7;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.OptionSupplier;
import com.moonsworth.lunar.client.mod.render.overlay.OverlayMod;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;

class GuiUpdater2 extends GuiUpdater {
   public GuiUpdater2(OverlayMod var1, OptionSupplier<? extends ClientOption<Boolean>, Boolean> var2) {
      super(var1, var2, "block/glass");
   }

   @Override
   public void updateState() {
      super.updateState();
      boolean var1 = this.method2();
      if (var1) {
         this.field13.method71().method7("block/glass_pane_top", this, true);
         if (ThreadModuleDump63.MC_VERSION >= 8) {
            this.field13.method71().method7("block/tinted_glass", this, true);
         }

         if ((Boolean)this.field13.field13.get()) {
            for (String var3 : Markers7.field1.keySet()) {
               this.method1(var3);
            }

            if (ThreadModuleDump63.MC_VERSION >= 6) {
               this.method1("light_gray");
            }
         }
      }
   }

   private void method1(String var1) {
      String var2 = ThreadModuleDump63.MC_VERSION <= 5 ? "glass_" + var1 : var1 + "_stained_glass";
      this.field13.method71().method7("block/" + var2, this, true);
      var2 = ThreadModuleDump63.MC_VERSION <= 5 ? "glass_pane_top_" + var1 : var1 + "_stained_glass_pane_top";
      this.field13.method71().method7("block/" + var2, this, true);
   }

   public void process(Extension var1) {
      int var2 = var1.method2();
      int var3 = var1.method3();
      if (var2 != var3 && var3 % var2 != 0) {
         Slayer.method5("[Overlay] Skipping glass texture %s, invalid size %dx%d", new Object[]{var1.method1(), var2, var3});
      } else {
         boolean var4 = (Boolean)this.field13.field15.get();
         if (var4 && var2 <= 4) {
            Slayer.method5("[Overlay] Skipping glass texture %s for outline, invalid size", new Object[]{var1.method1()});
            var4 = false;
         }

         if (var4 && var1.name().contains("glass_pane_")) {
            var4 = false;
         }

         float var5 = (Float)this.field13.field14.get();
         float var6 = !var4 ? 0.0F : (Float)this.field13.field17.get();
         int var7 = !var4 ? 0 : (Integer)this.field13.field16.get();
         int var13 = var3 / var2;

         do {
            int var8 = 0;
            int var9 = 0;
            int var10 = 0;
            int var11 = 0;
            int var12 = (var13 - 1) * var2;

            for (int var14 = 0; var14 < var2 * var2; var14++) {
               int var15 = var14 % var2;
               int var16 = var14 / var2 + var12;
               int var17 = var1.method4(var15, var16);
               int var18 = var17 >> 24 & 0xFF;
               if (var18 > 25) {
                  var18 = (int)(var18 * var5);
                  var1.method5(var15, var16, (var18 & 0xFF) << 24 | var17 & 16777215);
                  if (var4) {
                     var8 += method9(var17 >> 16 & 0xFF);
                     var9 += method9(var17 >> 8 & 0xFF);
                     var10 += method9(var17 & 0xFF);
                     var11++;
                  }
               }
            }

            if (var4) {
               if (var11 == 0) {
                  var10 = 238;
                  var9 = 238;
                  var8 = 238;
               } else {
                  var8 = method9(var8 / var11);
                  var9 = method9(var9 / var11);
                  var10 = method9(var10 / var11);
               }

               int var22 = (int)(255.0F * var6);
               int var23 = (var22 & 0xFF) << 24 | (var8 & 0xFF) << 16 | (var9 & 0xFF) << 8 | var10 & 0xFF;
               GuiUpdater.method4(var1, var13 - 1, var2, var7, var23, null);
            }
         } while (--var13 > 0);
      }
   }
}
