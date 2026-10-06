package com.moonsworth.lunar.client.framework.feature.gui;

import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.fog.click.Click.Extension;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.OptionSupplier;
import com.moonsworth.lunar.client.mod.render.overlay.OverlayMod;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import toxi.math.noise.PerlinNoise;

class GuiUpdater3 extends GuiUpdater {
   private final String field15;
   private final int field16;
   private final boolean field17;
   private final boolean field18;
   private final boolean field19;

   public GuiUpdater3(OverlayMod var1, OptionSupplier<? extends ClientOption<Boolean>, Boolean> var2, String var3, int var4, boolean var5) {
      super(var1, var2, "block/" + var3);
      this.field15 = var3;
      this.field16 = var4;
      this.field17 = var5;
      this.field18 = var3.startsWith("nether_") || var3.equals("quartz") || var3.startsWith("ancient_debris");
      this.field19 = var3.equals("coal_ore");
   }

   @Override
   public void updateState() {
      super.updateState();
      if (this.method2()) {
         if (this.field17 && ThreadModuleDump63.MC_VERSION >= 8) {
            this.field13.method71().method7("block/deepslate_" + this.field15, this, true);
         }

         if (ThreadModuleDump63.MC_VERSION >= 6 && this.field15.equals("ancient_debris_side")) {
            this.field13.method71().method7("block/ancient_debris_top", this, true);
         }
      }
   }

   @Override
   protected boolean method2() {
      return super.method2() && (Boolean)this.field13.field42.get();
   }

   public void process(Extension var1) {
      int var2 = var1.method2();
      if (var2 <= 4) {
         Slayer.method5("[Overlay] Skipping ore texture %s, size <=4", new Object[]{var1.method1()});
      } else {
         int var3 = var1.method3();
         if (var2 != var3 && var3 % var2 != 0) {
            Slayer.method5("[Overlay] Skipping ore texture %s, invalid size %dx%d", new Object[]{var1.method1(), var2, var3});
         } else {
            int var4 = (Integer)this.field13.field45.get();
            boolean var5 = (Boolean)this.field13.field44.get();
            if (!(Boolean)this.field13.field43.get()) {
               GuiUpdater.method3(var1, var4, this.field16, var5);
            } else {
               PerlinNoise var6 = var5 ? GuiUpdater.method8(var1) : null;
               boolean var7 = this.field18;
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
                     int var18 = var17 >> 16 & 0xFF;
                     int var19 = var17 >> 8 & 0xFF;
                     int var20 = var17 & 0xFF;
                     int var21 = ((var7 ? var18 / 2 : var18) + var19 + var20) / 3;
                     boolean var22 = var21 <= 165
                        && Math.abs((var7 ? var18 / 2 : var18) - var21) <= 15
                        && Math.abs(var19 - var21) <= 15
                        && Math.abs(var20 - var21) <= 15;
                     if (!var22) {
                        var8 += method9(var18);
                        var9 += method9(var19);
                        var10 += method9(var20);
                        var11++;
                     }
                  }

                  if (var11 == 0) {
                     if (this.field19) {
                        var10 = 32;
                        var9 = 32;
                        var8 = 32;
                     }
                  } else {
                     var8 = method9(var8 / var11);
                     var9 = method9(var9 / var11);
                     var10 = method9(var10 / var11);
                  }

                  int var23 = 0xFF000000 | (var8 & 0xFF) << 16 | (var9 & 0xFF) << 8 | var10 & 0xFF;
                  GuiUpdater.method4(var1, var13 - 1, var2, var4, var23, var6);
               } while (--var13 > 0);
            }
         }
      }
   }
}
