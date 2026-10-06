package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.holograms;

import com.moonsworth.lunar.bridge.Bridge3_24;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.AlertImpl;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.GuiImpl3;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_3;
import com.moonsworth.lunar.client.driver.DriverViewContextLegacy;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data5;
import com.moonsworth.lunar.client.mod.misc.rewind.Rewind;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers4;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump67;

public class Markers3Iterator implements DriverViewContextLegacy {
   private float field1;
   private float field2;
   private long field3 = 0L;

   public void method1(AbstractRenderContext var1, Data5 var2) {
      Rewind var3 = ThreadModuleDump63.method4().method40().method85();
      RewindHandlers var4 = var3.method35();
      if (var4 != null) {
         Highlight_3 var5 = var4.method40().method37();
         AlertImpl var6 = var4.method48().method24();
         if (var6.method11() != null) {
            if (var5 != null) {
               boolean var7 = false;

               for (GuiImpl3 var9 : var5.method11().method3()) {
                  if (var9.isEnabled() && var9.method1(var5.method15()) != null) {
                     var7 = true;
                     break;
                  }
               }

               if (!var7 && !ThreadModuleDump63.method3().bridge$hasOverlay() && var4.method48().method25()) {
                  var6.method11().bridge$framebufferClear();
                  ThreadModuleDump63.method3().method1(var6.method11(), false);
               }
            }

            var4.method48().method7(var1);
            RewindHandlers4 var10 = var4.method57();
            if (!var10.method25() || var10.method29()) {
               Bridge3_24 var11 = ThreadModuleDump63.method3().bridge$getMainRenderTarget();
               com.moonsworth.lunar.client.ui.LcuiScreen.method67(
                  var1, 0.0F, 0.0F, var11.bridge$framebufferTextureWidth(), var11.bridge$framebufferTextureHeight(), -16777216
               );
               if (var5 != null) {
                  var1.push();
                  float var12 = com.moonsworth.lunar.client.ui.LcuiScreen.getScale();
                  var1.scale(var12, var12, 1.0F);
                  this.method2(var4);
                  this.method3(var5, var1, var4.method48().method24(), this.field1, this.field2);
                  var1.pop();
               }
            }
         }
      }
   }

   private void method2(RewindHandlers var1) {
      long var2 = System.currentTimeMillis();
      float var4 = ThreadModuleDump67.method1((float)(var2 - this.field3), 1.0F, 1000.0F) / 1000.0F;
      this.field3 = var2;
      float var5 = var1.method58() / com.moonsworth.lunar.client.ui.LcuiScreen.field6;
      float var6 = var1.method59() / com.moonsworth.lunar.client.ui.LcuiScreen.field6;
      float var7 = var5 - this.field1;
      float var8 = var6 - this.field2;
      float var9 = 50.0F;
      if (this.field1 == 0.0F || this.field2 == 0.0F || !(Math.abs(var7) > 1.0F) && !(Math.abs(var8) > 1.0F)) {
         this.field1 = var5;
         this.field2 = var6;
      } else {
         float var10 = Math.min(0.8F, var9 * var4);
         float var11 = var7 * var10;
         float var12 = var8 * var10;
         this.field1 += var11;
         this.field2 += var12;
      }
   }

   private void method3(Highlight_3 var1, AbstractRenderContext var2, AlertImpl var3, float var4, float var5) {
      if (var3.method11() != null) {
         float var6 = var4;
         float var7 = var5;
         int var8 = var3.method11().bridge$framebufferWidth();
         int var9 = var3.method11().bridge$framebufferHeight();
         if (var6 > var7) {
            var6 = var7 * var8 / var9;
            if (var6 > var4) {
               var6 = var4;
               var7 = var6 * var9 / var8;
            }
         } else {
            var7 = var6 * var9 / var8;
            if (var7 > var5) {
               var7 = var5;
               var6 = var7 * var8 / var9;
            }
         }

         float var10 = com.moonsworth.lunar.client.ui.LcuiScreen.method135((var4 - var6) / 2.0F);
         float var11 = com.moonsworth.lunar.client.ui.LcuiScreen.method135((var5 - var7) / 2.0F);
         var6 = com.moonsworth.lunar.client.ui.LcuiScreen.method135(var6);
         var7 = com.moonsworth.lunar.client.ui.LcuiScreen.method135(var7);
         if (ThreadModuleDump63.MC_VERSION >= 6) {
            ThreadModuleDump63.method3().bridge$getRenderBuffers().bridge$bufferSource().bridge$endBatch();
         }

         var3.method1(var2, var10, var11, var6, var7);
         ThreadModuleDump63.method3().method1(null, true);
         int var12 = -1325400065;
         if (var1.method18()) {
            com.moonsworth.lunar.client.ui.LcuiScreen.method97(var2, var10 + var6 / 3.0F, var11, 1.0F, var7, var12);
            com.moonsworth.lunar.client.ui.LcuiScreen.method97(var2, var10 + var6 * 2.0F / 3.0F, var11, 1.0F, var7, var12);
            com.moonsworth.lunar.client.ui.LcuiScreen.method97(var2, var10, var11 + var7 / 3.0F, var6, 1.0F, var12);
            com.moonsworth.lunar.client.ui.LcuiScreen.method97(var2, var10, var11 + var7 * 2.0F / 3.0F, var6, 1.0F, var12);
         }

         if (var1.method20()) {
            com.moonsworth.lunar.client.ui.LcuiScreen.method97(var2, var10 + var6 / 2.0F, var11, 1.0F, var7, var12);
            com.moonsworth.lunar.client.ui.LcuiScreen.method97(var2, var10, var11 + var7 / 2.0F, var6, 1.0F, var12);
         }
      }
   }
}
