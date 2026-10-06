package com.moonsworth.lunar.client.driver.waypoint;

import com.moonsworth.lunar.bridge.MinecraftBridge;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.LegacyGuiGraphicsBridge;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.driver.DriverViewContext;
import com.moonsworth.lunar.client.driver.core.Fishing;
import com.moonsworth.lunar.client.driver.waypoint.MinimapJsApi;
import com.moonsworth.lunar.client.mod.render.minimap.MinimapMod;
import com.moonsworth.lunar.client.framework.Ref;

public class MinimapViewContext implements DriverViewContext {
   private MinimapMod field1;
   private MinecraftBridge mc;
   private Fishing field2;

   public MinimapViewContext() {
   }

   @Override
   public void method1(AbstractRenderContext bridgeextension_91, MarkerModel.Data5 data52) {
      com.moonsworth.lunar.client.framework.feature.minimap.MinimapMap minimap3 = this.field1.getMinimapManager();
      int number4 = MinimapJsApi.field1;
      int number5 = MinimapJsApi.field2;
      if (Ref.method4().method40().method94().isEnabled() && Ref.method8() != null) {
         if (this.field2 != null && this.field2.method2(bridgeextension_91, number4, number5)) {
            if (Ref.MC_VERSION >= 39) {
               bridgeextension_91.method35(0.0, number4, number5, 0.0, 21000.0, 1000.0);
            } else {
               bridgeextension_91.method35(0.0, number4, number5, 0.0, 1000.0, Ref.MC_VERSION >= 17 ? 21000.0 : 3000.0);
            }

            BridgeExtension bridgeextension6 = this.mc.bridge$getRenderViewEntity();
            int number7 = Math.max(2, Math.min(14, this.mc.bridge$getGameSettings().bridge$getRenderDistance() * 2));
            minimap3.method9(number7, number7);
            float value8 = 0.0F;
            float value9 = 0.0F;
            float value10 = this.field1.calculateScale(number4, number5, number7);
            value10 += MinimapJsApi.getZoom();
            bridgeextension_91.push();
            float value11 = number4 / 2.0F;
            float value12 = number5 / 2.0F;
            bridgeextension_91.translate(value8 + value11, value9 + value12, 0.0);
            float value13 = MinimapJsApi.method4();
            float value14 = MinimapJsApi.method5();
            bridgeextension_91.translate(value13, value14, 0.0);
            bridgeextension_91.push();
            bridgeextension_91.scale(value10, value10, 1.0F);
            LegacyGuiGraphicsBridge mixinhelper515 = new LegacyGuiGraphicsBridge(bridgeextension_91);
            minimap3.method3(mixinhelper515, bridgeextension6);
            minimap3.method4(mixinhelper515, bridgeextension6, true);
            this.field1.renderOverlays(mixinhelper515, 0.0F, null);
            bridgeextension_91.pop();
            if (this.mc.bridge$getPlayer() != null) {
               this.field1.renderPlayerMarkerSized(bridgeextension_91.method42(), bridgeextension6 == null ? 0.0F : (float)bridgeextension6.bridge$getRotationYaw(), false, 5.0F * value10);
            }

            if (bridgeextension_91.method38()) {
               bridgeextension_91.method30().method48();
            }

            bridgeextension_91.method33();
            bridgeextension_91.method14();
            bridgeextension_91.pop();
            bridgeextension_91.method41();
            this.field2.method3(bridgeextension_91);
         }
      }
   }

   private double method2(double value1) {
      return value1 >= 0.0 ? value1 % 16.0 - 8.0 : value1 % 16.0 + 8.0;
   }

   @Override
   public void method7() {
      this.field1 = Ref.method4().method40().method94();
      this.mc = Ref.method3();
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
