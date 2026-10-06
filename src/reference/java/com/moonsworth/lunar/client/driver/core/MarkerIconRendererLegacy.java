package com.moonsworth.lunar.client.driver.core;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension610;
import com.moonsworth.lunar.bridge.Bridge8Extension34;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BridgeType3_3;
import com.moonsworth.lunar.bridge.BufferBuildMode;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.driver.DriverRouteRegistryLegacy;
import com.moonsworth.lunar.client.driver.TextureHandlerLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump71;
import com.moonsworth.lunar.client.util.alert.Alert5Handler;

public class MarkerIconRendererLegacy {
   private final ResourceLocationBridge field1 = ResourceLocationBridge.create("lunar", "webosr_texture_" + this.hashCode());
   private final MigrationContextLegacy field2;
   private Bridge8Extension34 field3;
   private long field4 = 0L;

   public MarkerIconRendererLegacy(MigrationContextLegacy var1) {
      this.field2 = var1;
   }

   public void method1(MixinHelper_4 var1, float value, float value2, com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data5 data) {
      if (this.field2.method13() != null) {
         DriverViewportLegacy var5 = DriverViewportLegacy.method50();
         if (var5 == null || this.field2 != var5.method55() || !var5.method26()) {
            double var6 = ((Integer)ThreadModuleDump63.method4().method41().method7().method29().get()).intValue();
            if (System.currentTimeMillis() - this.field4 >= 1000.0 / var6) {
               int var8 = this.field2.field26.getTexture();
               if (var8 == 0) {
                  return;
               }

               if (this.field3 == null) {
                  this.field3 = ThreadModuleDump63.method3().bridge$getTextureManager().method3(this.field1, new Alert5Handler());
               }

               this.field3.method2(TextureHandlerLegacy.method2().method1(var8));
               this.field4 = System.currentTimeMillis();
            }

            if (this.field3 != null) {
               ThreadModuleDump71 var20 = new ThreadModuleDump71(ThreadModuleDump63.method3());
               boolean var9 = this.method2();
               boolean var10 = ThreadModuleDump63.method4().method40().method85().method19();
               float var11 = (float)var20.getScaledWidth_double();
               float var12 = (float)var20.getScaledHeight_double();
               float var13 = value + var11;
               float var14 = value2 + var12;
               RenderLayerBridge var15 = LunarRenderTypes.field54.get(this.field1);
               if (!var9 && !var10) {
                  var1.method9(var15, this.field1, value, value2, var11, var12, var4x -> {
                     var4x.method5(value, value2).method10(0.0F, 0.0F).method16();
                     var4x.method5(value, var14).method10(0.0F, 1.0F).method16();
                     var4x.method5(var13, var14).method10(1.0F, 1.0F).method16();
                     var4x.method5(var13, value2).method10(1.0F, 0.0F).method16();
                  });
               } else {
                  float var16 = LcuiScreen.method151().method3();
                  float var17 = LcuiScreen.method135(ThreadModuleDump63.method3().bridge$displayWidth() / var16);
                  float var18 = LcuiScreen.method135(ThreadModuleDump63.method3().bridge$displayHeight() / var16);
                  AbstractRenderContext var19 = var1.method48();
                  var19.method8(BridgeType3_3.GL_PROJECTION);
                  var19.method36();
                  Bridge.method42().bridge$loadIdentity();
                  var19.method35(0.0, var17, var18, 0.0, 1000.0, ThreadModuleDump63.MC_VERSION >= 17 ? 21000.0 : 3000.0);
                  var19.method8(BridgeType3_3.GL_MODELVIEW);
                  var19.method36();
                  Bridge.method42().bridge$loadIdentity();
                  var19.translate(0.0, 0.0, -2000.0);
                  var19.method10(var15)
                     .method1()
                     .method5(value, value2)
                     .method10(0.0F, 0.0F)
                     .method16()
                     .method5(value, var14)
                     .method10(0.0F, 1.0F)
                     .method16()
                     .method5(var13, var14)
                     .method10(1.0F, 1.0F)
                     .method16()
                     .method5(var13, value2)
                     .method10(1.0F, 0.0F)
                     .method16()
                     .method17(BufferBuildMode.BATCHED);
                  var19.method33(var15);
                  var19.method41();
               }
            }
         }
      }
   }

   private boolean method2() {
      return ThreadModuleDump63.method3().bridge$getCurrentScreen() instanceof Bridge5Extension610
         && DriverViewportLegacy.method50() != null
         && (DriverViewportLegacy.method50().method63() == DriverRouteRegistryLegacy.field5 || DriverViewportLegacy.method50().method61() == DriverRouteRegistryLegacy.field5);
   }
}
