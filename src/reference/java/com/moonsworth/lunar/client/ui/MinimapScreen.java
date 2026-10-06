package com.moonsworth.lunar.client.ui;

import com.google.common.collect.Lists;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.itemcounter.mixin.Itemcounter2;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.ui.widget.TextLabelWidget;
import com.moonsworth.lunar.client.ui.widget.ProgressBarWidget;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.driver.DriverRouteRegistryLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.rewindhandlers.ConfigureWaypointPropsLegacy;
import com.moonsworth.lunar.client.mod.render.minimap.Minimap;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import lombok.Generated;

public class MinimapScreen extends com.moonsworth.lunar.client.ui.LcuiScreen {
   private MinimapOptionWidget field19;
   private ProgressBarWidget field20;
   private ProgressBarWidget field21;
   private TextLabelWidget field22;
   private final Minimap field23;
   private boolean field24 = false;
   private boolean field25 = true;

   public MinimapScreen(Minimap var1) {
      this.field23 = var1;
      this.field21.method4((var0, var1x) -> {
         ThreadModuleDump63.method3().bridge$displayScreen(null);
         return true;
      });
      this.field20.method4((var1x, var2) -> {
         this.field24 = !this.field24;
         if (this.field24) {
            this.field20.method4(ResourceLocationBridge.create("lunar", "icons/minimize-16x16.png"));
         } else {
            this.field20.method4(ResourceLocationBridge.create("lunar", "icons/maximize-16x16.png"));
         }

         this.field19.method15();
         return true;
      });
      this.field22
         .method4(
            (var2, var3) -> {
               if (ThreadModuleDump63.method3().bridge$getWorld() == null) {
                  return true;
               }

               this.field25 = false;
               float var4 = this.field19.method6();
               float var5 = this.field19.method7();
               int var6 = Math.round(var4);
               int var7 = Math.round(var5);
               Itemcounter2 var8 = ThreadModuleDump63.method3().bridge$getWorld().bridge$getChunk(var6 >> 4, var7 >> 4);
               DriverViewportLegacy.method50()
                  .method17(
                     DriverRouteRegistryLegacy.field18,
                     ConfigureWaypointPropsLegacy.method2()
                        .method4((double)var4)
                        .method5(
                           var1.getMinimapManager()
                                 .method12(var8, var6, ThreadModuleDump63.method7() == null ? 0 : (int)ThreadModuleDump63.method7().bridge$getPosY() - 1, var7)
                              + 1.0
                        )
                        .method6((double)var5)
                        .method7()
                  );
               return true;
            }
         );
   }

   @Override
   protected List<GuiWidget> method25() {
      return Lists.newArrayList(
         new GuiWidget[]{
            this.field19 = new MinimapOptionWidget(this),
            this.field20 = new ProgressBarWidget(null, ResourceLocationBridge.create("lunar", "icons/maximize-16x16.png")),
            this.field21 = new ProgressBarWidget(null, ResourceLocationBridge.create("lunar", "icons/cosmetics/back-40x40.png")),
            this.field22 = new TextLabelWidget(null, "addWaypoint")
         }
      );
   }

   @Override
   public void init() {
      this.field25 = true;
      this.field11.remove(this.field19);
      this.field11.add(0, this.field19);
      this.field19.method14(this.field23);
      this.field19.method2(0.0F, 0.0F, this.method22(), this.method23());
      float var1 = this.method23() / 1.5F;
      float var2 = (this.method22() - var1) / 2.0F;
      float var3 = (this.method23() - var1) / 2.0F;
      this.field20.method2(var2 + 30.0F, var3 + var1 + 30.0F - 24.0F, 18.0F, 18.0F);
      this.field21.method2(var2 + 6.0F, var3 + var1 + 30.0F - 24.0F, 18.0F, 18.0F);
      this.field22.method2(var2 + var1 - 106.0F, var3 + var1 + 30.0F - 24.0F, 100.0F, 18.0F);
   }

   @Override
   public void update() {
   }

   @Override
   public void method10(MixinHelper_4 var1, MarkerModel.Data2 var2) {
      var1.push();
      float var3 = this.method23() / 1.5F;
      float var4 = var3;
      float var5 = (this.method22() - var4) / 2.0F;
      float var6 = (this.method23() - var3) / 2.0F;
      var3 += 30.0F;
      if (!this.field24) {
         com.moonsworth.lunar.client.ui.LcuiScreen.method94(var1, var5, var6 + var3 - 30.0F, var4, 0.5F, 553648127);
         com.moonsworth.lunar.client.ui.LcuiScreen.method105(var1, var5, var6 + var3 - 29.5F, var4, 28.5F, 5.0F, 1157627904);
         com.moonsworth.lunar.client.ui.LcuiScreen.method56(var1, var5 - 1.0F, var6, var4 + 2.0F, var3, 4.0F, 1073741824);
         com.moonsworth.lunar.client.ui.LcuiScreen.method56(var1, var5, var6 + 1.0F, var4, var3 - 2.0F, 3.0F, 553648127);
         com.moonsworth.lunar.client.ui.LcuiScreen.method117(var1, var5, var6 + 1.0F, var4, var3 - 2.0F, 5.0F, Integer.MIN_VALUE);
         this.field19.method3(var1, var2, this.method10(this.field19, var2, new GuiWidget[0]));
      } else {
         this.field19.method3(var1, var2, this.method10(this.field19, var2, new GuiWidget[0]));
         float var7 = var6 + var3;
         var3 = 30.0F;
         var6 = var7 - var3;
         com.moonsworth.lunar.client.ui.LcuiScreen.method105(var1, var5, var6 + 1.0F, var4, var3 - 2.0F, 5.0F, 1157627904);
         com.moonsworth.lunar.client.ui.LcuiScreen.method56(var1, var5 - 1.0F, var6, var4 + 2.0F, var3, 4.0F, 1073741824);
         com.moonsworth.lunar.client.ui.LcuiScreen.method56(var1, var5, var6 + 1.0F, var4, var3 - 2.0F, 3.0F, 553648127);
         com.moonsworth.lunar.client.ui.LcuiScreen.method117(var1, var5, var6 + 1.0F, var4, var3 - 2.0F, 5.0F, Integer.MIN_VALUE);
      }

      Bridge5Extension_5 var14 = ThreadModuleDump63.method7();
      String var8 = String.format("%.2f, %.2f, %.2f", this.field19.method6(), var14 == null ? 0.0 : var14.bridge$getPosY(), this.field19.method7());
      float var9 = 10.0F + this.field21.getWidth() + 5.0F + this.field20.getWidth();
      float var10 = var4 - this.field22.getWidth() - 10.0F - var9;
      float var11 = FontRegistry.method13().method4(var8);
      float var12 = (var4 - 20.0F - this.field22.getWidth() - this.field21.getWidth() - 5.0F - this.field20.getWidth()) / var11;
      if (var12 > 0.75) {
         FontRegistry.method12()
            .method14(var1, this.method1("mapCenter", new Object[0]), var5 + var9 + var10 / 2.0F, var6 + var3 - 25.0F, -1);
         var1.method38(var5 + var9 + var10 / 2.0F, var6 + var3 - 15.0F, 0.0F);
         if (var12 < 1.0F) {
            var1.scale(var12, var12, 1.0F);
         }

         FontRegistry.method13().method14(var1, var8, 0.0F, 0.0F, -1);
      }

      var1.pop();
   }

   @Override
   public void method11(MarkerModel.Data2 var1, int var2) {
      this.field19.method6(var1, var2);
   }

   @Override
   public void method12(MarkerModel.Data2 var1, int var2) {
      this.field19.method7(var1, var2);
   }

   @Override
   public void method14(char var1, KeyCode var2) {
      if (var2 == KeyCode.KEY_ESCAPE || var2 == this.field23.getFullViewKeybind().method8()) {
         this.close();
      }
   }

   @Override
   public void close() {
   }

   @Override
   public boolean doesGuiPauseGame() {
      return false;
   }

   @Override
   public String getLanguagePath() {
      return "gui.minimap";
   }

   @Generated
   public boolean method6() {
      return this.field24;
   }

   @Generated
   public boolean method10() {
      return this.field25;
   }
}
