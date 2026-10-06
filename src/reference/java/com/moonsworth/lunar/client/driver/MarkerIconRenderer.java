package com.moonsworth.lunar.client.driver;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderTypeBridge;
import com.moonsworth.lunar.bridge.GuiMainMenuBridge;
import com.moonsworth.lunar.bridge.Bridge8Extension34;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.GlMatrixMode;
import com.moonsworth.lunar.bridge.BufferMode;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.driver.DriverRouteRegistry;
import com.moonsworth.lunar.client.driver.TextureHandler;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.ui.GuiResolution;
import com.moonsworth.lunar.client.render.texture.BaseTexture;
import com.moonsworth.lunar.client.driver.core.MigrationContextLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;

public class MarkerIconRenderer {
   private final ResourceLocationBridge field1 = ResourceLocationBridge.create("lunar", "webosr_texture_" + this.hashCode());
   private final MigrationContextLegacy field2;
   private Bridge8Extension34 field3;
   private long field4 = 0L;

   public MarkerIconRenderer(MigrationContextLegacy markersimpl1) {
      this.field2 = markersimpl1;
   }

   public void method1(MixinHelper_4 mixinhelper_41, float value2, float value3, com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data5 data54) {
      if (this.field2.method13() != null) {
         DriverViewportLegacy highlight3iterator5 = DriverViewportLegacy.method50();
         if (highlight3iterator5 == null || this.field2 != highlight3iterator5.method55() || !highlight3iterator5.method26()) {
            double value6 = ((Integer)Ref.method4().method41().method7().method29().get()).intValue();
            if (System.currentTimeMillis() - this.field4 >= 1000.0 / value6) {
               int number8 = this.field2.field26.getTexture();
               if (number8 == 0) {
                  return;
               }

               if (this.field3 == null) {
                  this.field3 = Ref.method3().bridge$getTextureManager().method3(this.field1, new BaseTexture());
               }

               this.field3.method2(TextureHandler.method2().method1(number8));
               this.field4 = System.currentTimeMillis();
            }

            if (this.field3 != null) {
               GuiResolution threadmoduledump7120 = new GuiResolution(Ref.method3());
               boolean flag9 = this.method2();
               boolean flag10 = Ref.method4().method40().method85().method19();
               float value11 = (float)threadmoduledump7120.method1();
               float value12 = (float)threadmoduledump7120.method2();
               float value13 = value2 + value11;
               float value14 = value3 + value12;
               RenderTypeBridge bridge2015 = LunarRenderTypes.field54.get(this.field1);
               if (!flag9 && !flag10) {
                  mixinhelper_41.method9(bridge2015, this.field1, value2, value3, value11, value12, arg4x -> {
                     arg4x.method5(value2, value3).method10(0.0F, 0.0F).method16();
                     arg4x.method5(value2, value14).method10(0.0F, 1.0F).method16();
                     arg4x.method5(value13, value14).method10(1.0F, 1.0F).method16();
                     arg4x.method5(value13, value3).method10(1.0F, 0.0F).method16();
                  });
               } else {
                  float value16 = LcuiScreen.method151().method3();
                  float value17 = LcuiScreen.method135(Ref.method3().bridge$displayWidth() / value16);
                  float value18 = LcuiScreen.method135(Ref.method3().bridge$displayHeight() / value16);
                  AbstractRenderContext bridgeextension_919 = mixinhelper_41.method48();
                  bridgeextension_919.method8(GlMatrixMode.GL_PROJECTION);
                  bridgeextension_919.method36();
                  Bridge.method42().bridge$loadIdentity();
                  bridgeextension_919.method35(0.0, value17, value18, 0.0, 1000.0, Ref.MC_VERSION >= 17 ? 21000.0 : 3000.0);
                  bridgeextension_919.method8(GlMatrixMode.GL_MODELVIEW);
                  bridgeextension_919.method36();
                  Bridge.method42().bridge$loadIdentity();
                  bridgeextension_919.translate(0.0, 0.0, -2000.0);
                  bridgeextension_919.method10(bridge2015)
                     .method1()
                     .method5(value2, value3)
                     .method10(0.0F, 0.0F)
                     .method16()
                     .method5(value2, value14)
                     .method10(0.0F, 1.0F)
                     .method16()
                     .method5(value13, value14)
                     .method10(1.0F, 1.0F)
                     .method16()
                     .method5(value13, value3)
                     .method10(1.0F, 0.0F)
                     .method16()
                     .method17(BufferMode.BATCHED);
                  bridgeextension_919.method33(bridge2015);
                  bridgeextension_919.method41();
               }
            }
         }
      }
   }

   private boolean method2() {
      return Ref.method3().bridge$getCurrentScreen() instanceof GuiMainMenuBridge
         && DriverViewportLegacy.method50() != null
         && (DriverViewportLegacy.method50().method63() == DriverRouteRegistry.field5 || DriverViewportLegacy.method50().method61() == DriverRouteRegistry.field5);
   }
}
