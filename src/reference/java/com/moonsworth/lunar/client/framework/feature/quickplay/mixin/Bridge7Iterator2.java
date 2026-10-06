package com.moonsworth.lunar.client.framework.feature.quickplay.mixin;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import com.moonsworth.lunar.client.mod.misc.quickplay.Quickplay;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Generated;

public class Bridge7Iterator2 extends com.moonsworth.lunar.client.ui.LcuiScreen {
   private boolean field19;

   protected List<GuiWidget> method25() {
      Quickplay var1 = Client.method109().method40().method52();
      if (var1.method14() && var1.method15() != null && !var1.method15().isEmpty()) {
         ArrayList var2 = new ArrayList();

         for (com.moonsworth.lunar.client.framework.feature.quickplay.Quickplay var4 : Client.method109().method40().method52().method15()) {
            var2.add(new Calculator2Updater2(var4));
         }

         for (String var8 : Client.method109().method40().method52().method16()) {
            com.moonsworth.lunar.client.framework.feature.quickplay.Quickplay var5 = Client.method109().method40().method52().method10(var8);
            if (var5 != null) {
               String var6 = var5.method3() == null ? var5.getName() : var5.method3().getName() + ": " + var5.getName();
               var2.add(new Calculator2Updater(this, var5.method3(), var5, var5.method2(), var6));
            }
         }

         return Collections.unmodifiableList(var2);
      } else {
         this.field19 = true;
         return ImmutableList.of();
      }
   }

   public void init() {
      if (!this.field19) {
         byte var1 = 4;
         float var2 = 100.0F;
         float var3 = 22.0F;
         float var4 = 5.0F;
         float var5 = this.method22() / 2.0F - (var2 + var4) * var1 / 2.0F;
         float var6 = this.method23() / 2.0F - 120.0F;
         int var7 = 0;

         for (GuiWidget var9 : this.field11) {
            if (var9 instanceof Calculator2Updater2) {
               var9.method2(var5 + var7 * (var2 + var4), var6, var2, var3);
               if (var7 == var1 - 1) {
                  var7 = 0;
                  var6 += var3 + var4;
               } else {
                  var7++;
               }
            }
         }

         var7 = 0;
         var6 += 44.0F;
         var3 = 16.0F;

         for (GuiWidget var14 : this.field11) {
            if (var14 instanceof Calculator2Updater) {
               var14.method2(var5 + var7 * (var2 + var4), var6, var2, var3);
               if (var7 == var1 - 1) {
                  var7 = 0;
                  var6 += var3 + var4;
               } else {
                  var7++;
               }
            }
         }
      }
   }

   public void update() {
      if (this.field19 && Client.method109().method40().method52().method14()) {
         ThreadModuleDump63.method3().bridge$displayScreen(Bridge.method8().method18(new Bridge7Iterator2()));
      }
   }

   public void method10(MixinHelper_4 var1, Data2 var2) {
      if (this.field19) {
         FontRegistry.method16()
            .method15(
               var1,
               this.method1("loadingGames", new Object[0]),
               this.method22() / 2.0F,
               this.method23() / 2.0F - 10.0F,
               -1
            );
      } else {
         FontRegistry.method16()
            .method15(
               var1,
               this.method1("chooseGame", new Object[0]),
               this.method22() / 2.0F,
               this.method23() / 2.0F - 140.0F,
               -1
            );
         Quickplay var3 = Client.method109().method40().method52();
         if (!var3.method16().isEmpty()) {
            int var4 = var3.method15().size();
            float var5 = this.method23() / 2.0F - 120.0F + var4 / 4.0F * 27.0F + 16.0F;
            FontRegistry.method16().method15(var1, "Favorites", this.method22() / 2.0F, var5, -1);
         }
      }
   }

   public void method11(Data2 var1, int var2) {
   }

   public void method12(Data2 var1, int var2) {
   }

   public void method14(char var1, KeyCode var2) {
   }

   public void close() {
   }

   public String getLanguagePath() {
      return super.getLanguagePath() + ".quickPlay";
   }
}
