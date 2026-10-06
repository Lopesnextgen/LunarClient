package com.moonsworth.lunar.client.driver.core.rewindhandlers;

import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.LegacyGuiGraphicsBridge;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.driver.DriverViewContextLegacy;
import com.moonsworth.lunar.client.driver.core.Fishing;
import com.moonsworth.lunar.client.driver.core.gui.mixin.rewindhandlers.MinimapJsApiLegacy;
import com.moonsworth.lunar.client.mod.render.minimap.Minimap;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;

public class MinimapViewContextLegacy implements DriverViewContextLegacy {
   private Minimap field1;
   private Bridge5_12 mc;
   private Fishing field2;

   @Override
   public void method1(AbstractRenderContext var1, MarkerModel.Data5 data) {
      com.moonsworth.lunar.client.framework.feature.minimap.Minimap var3 = this.field1.getMinimapManager();
      int var4 = MinimapJsApiLegacy.field1;
      int var5 = MinimapJsApiLegacy.field2;
      if (ThreadModuleDump63.method4().method40().method94().isEnabled() && ThreadModuleDump63.method8() != null) {
         if (this.field2 != null && this.field2.method2(var1, var4, var5)) {
            if (ThreadModuleDump63.MC_VERSION >= 39) {
               var1.method35(0.0, var4, var5, 0.0, 21000.0, 1000.0);
            } else {
               var1.method35(0.0, var4, var5, 0.0, 1000.0, ThreadModuleDump63.MC_VERSION >= 17 ? 21000.0 : 3000.0);
            }

            BridgeExtension var6 = this.mc.bridge$getRenderViewEntity();
            int var7 = Math.max(2, Math.min(14, this.mc.bridge$getGameSettings().bridge$getRenderDistance() * 2));
            var3.method9(var7, var7);
            float var8 = 0.0F;
            float var9 = 0.0F;
            float var10 = this.field1.calculateScale(var4, var5, var7);
            var10 += MinimapJsApiLegacy.getZoom();
            var1.push();
            float var11 = var4 / 2.0F;
            float var12 = var5 / 2.0F;
            var1.translate(var8 + var11, var9 + var12, 0.0);
            float var13 = MinimapJsApiLegacy.method4();
            float var14 = MinimapJsApiLegacy.method5();
            var1.translate(var13, var14, 0.0);
            var1.push();
            var1.scale(var10, var10, 1.0F);
            LegacyGuiGraphicsBridge var15 = new LegacyGuiGraphicsBridge(var1);
            var3.method3(var15, var6);
            var3.method4(var15, var6, true);
            this.field1.renderOverlays(var15, 0.0F, null);
            var1.pop();
            if (this.mc.bridge$getPlayer() != null) {
               this.field1.renderPlayerMarkerSized(var1.method42(), var6 == null ? 0.0F : (float)var6.bridge$getRotationYaw(), false, 5.0F * var10);
            }

            if (var1.method38()) {
               var1.method30().method48();
            }

            var1.method33();
            var1.method14();
            var1.pop();
            var1.method41();
            this.field2.method3(var1);
         }
      }
   }

   private double method2(double var1) {
      return var1 >= 0.0 ? var1 % 16.0 - 8.0 : var1 % 16.0 + 8.0;
   }

   @Override
   public void method7() {
      this.field1 = ThreadModuleDump63.method4().method40().method94();
      this.mc = ThreadModuleDump63.method3();
      this.field2 = new Fishing("waypoint-map-texture");
   }

   @Override
   public void onClose() {
      if (this.field2 != null) {
         this.field2.delete();
         this.field2 = null;
      }
   }
}
