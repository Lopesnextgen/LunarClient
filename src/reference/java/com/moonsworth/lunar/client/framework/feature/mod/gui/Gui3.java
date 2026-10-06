package com.moonsworth.lunar.client.framework.feature.mod.gui;

import com.google.common.collect.ImmutableList;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.widget.GuiWidget;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing4;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.Fishing_2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.JsonDeserializerIterator$Data;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Gui3 extends com.moonsworth.lunar.client.ui.LcuiScreen {
   private boolean field19;

   protected List<GuiWidget> method25() {
      Skyblock var1 = ThreadModuleDump63.method4().method40().method82();
      Fishing4 var2 = var1.method222();
      JsonDeserializerIterator$Data var3 = var1.method15().method11();
      if (var3 != null && !var3.method1().isEmpty()) {
         ArrayList var4 = new ArrayList();

         for (Fishing_2 var6 : var3.method1()) {
            var4.add(new Calculator2Updater(var6));
         }

         for (String var9 : var2.method16()) {
            Fishing_2 var7 = var2.method10(var9);
            if (var7 != null) {
               var4.add(new Calculator2Updater2(this, var7.method7(), var7));
            }
         }

         return Collections.unmodifiableList(var4);
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
            if (var9 instanceof Calculator2Updater) {
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
            if (var14 instanceof Calculator2Updater2) {
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
      if (this.field19 && Client.method109().method40().method82().method15().method8()) {
         ThreadModuleDump63.method3().bridge$displayScreen(Bridge.method8().method18(new Gui3()));
      }
   }

   public void method10(MixinHelper_4 var1, Data2 var2) {
      if (this.field19) {
         FontRegistry.method16()
            .method15(
               var1,
               this.method1("loadingCommands", new Object[0]),
               this.method22() / 2.0F,
               this.method23() / 2.0F - 10.0F,
               -1
            );
      } else {
         FontRegistry.method16()
            .method15(
               var1,
               this.method1("chooseCommand", new Object[0]),
               this.method22() / 2.0F,
               this.method23() / 2.0F - 140.0F,
               -1
            );
         FontRegistry.method17()
            .method15(
               var1,
               this.method1("keyBindHint", new Object[0]),
               this.method22() / 2.0F,
               this.method23() / 2.0F - 130.0F,
               -536870913
            );
         Skyblock var3 = Client.method109().method40().method82();
         JsonDeserializerIterator$Data var4 = var3.method15().method11();
         if (var4 != null && !var4.method1().isEmpty() && !var3.method222().method16().isEmpty()) {
            int var5 = var4.method1().size();
            float var6 = this.method23() / 2.0F - 120.0F + var5 / 4.0F * 27.0F + 16.0F;
            FontRegistry.method16().method15(var1, "Favorites", this.method22() / 2.0F, var6, -1);
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
      return super.getLanguagePath() + ".skyBlockCommands";
   }
}
