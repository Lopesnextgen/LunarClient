package com.moonsworth.lunar.client.cosmetics.skin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderTypeBridge;
import com.moonsworth.lunar.bridge.VertexConsumerBridge;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.Bridge5_16;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.BridgeExtension2_11;
import com.moonsworth.lunar.bridge.ModelPlayerBridge;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.bridge.LayerRendererBridge;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.mod.render.skins3d.Skins3d;
import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;

public class BodyOverlayLayerRenderer implements LayerRendererBridge<Bridge5_11, EntityPlayerBridge> {
   public static final BodyOverlayLayerRenderer field1 = new BodyOverlayLayerRenderer();

   public BodyOverlayLayerRenderer() {
   }

   public int method1(EntityPlayerBridge bridgeextension2221) {
      return Skins3d.method13().isEnabled() ? 1 : 0;
   }

   public Optional<ResourceLocationBridge> method2(EntityPlayerBridge bridgeextension2221, int number2) {
      return Optional.ofNullable(bridgeextension2221.bridge$getLocationSkin());
   }

   public Optional<RenderTypeBridge> method6(ResourceLocationBridge horsestats141) {
      return Optional.of(LunarRenderTypes.field10.get(horsestats141));
   }

   @Nullable
   private List<SkinPartEntry> method4(EntityPlayerBridge bridgeextension2221, ModelPlayerBridge bridgeextension2_72) {
      Skins3d skins3d3 = Skins3d.method13();
      if (!skins3d3.shouldRender(bridgeextension2221)) {
         return null;
      }

      boolean flag4 = bridgeextension2_72.bridge$isSlim();
      com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3[] items5 = skins3d3.method34().method4(bridgeextension2221, flag4);
      if (items5 == null) {
         return null;
      }

      ArrayList list6 = new ArrayList();
      list6.add(new SkinPartEntry(items5[0], false, OverlayGarment.LEFT_PANTS_LEG, BodyPartSpec.LEGS, bridgeextension2_72::bridge$bipedLeftLeg, skins3d3.method21()::get));
      list6.add(new SkinPartEntry(items5[1], false, OverlayGarment.RIGHT_PANTS_LEG, BodyPartSpec.LEGS, bridgeextension2_72::bridge$bipedRightLeg, skins3d3.method22()::get));
      list6.add(
         new SkinPartEntry(items5[2], false, OverlayGarment.LEFT_SLEEVE, flag4 ? BodyPartSpec.ARMS_SLIM : BodyPartSpec.ARMS, bridgeextension2_72::bridge$bipedLeftArm, skins3d3.method17()::get)
      );
      list6.add(
         new SkinPartEntry(items5[3], true, OverlayGarment.RIGHT_SLEEVE, flag4 ? BodyPartSpec.ARMS_SLIM : BodyPartSpec.ARMS, bridgeextension2_72::bridge$bipedRightArm, skins3d3.method19()::get)
      );
      list6.add(new SkinPartEntry(items5[4], false, OverlayGarment.JACKET, BodyPartSpec.BODY, bridgeextension2_72::bridge$bipedBody, skins3d3.method16()::get));
      return list6;
   }

   @VersionGate(max = 5)
   public void method5(
      BridgeExtension3_5 bridgeextension3_51, Bridge5_11 bridge5_112, ModelPlayerBridge bridgeextension2_73, float value4, float value5, float value6, float value7, float value8, float value9, float value10
   ) {
      List list11 = this.method4(bridge5_112, bridgeextension2_73);
      if (list11 != null) {
         bridgeextension3_51.push();
         boolean flag12 = Bridge.getMinecraftVersion().equals(Config.field1);
         if (bridge5_112.bridge$isVisiblyCrouching() && !flag12) {
            bridgeextension3_51.translate(0.0, 0.2F, 0.0);
         }

         this.method9(bridge5_112, list11, bridgeextension3_51, value10, bridge5_112.bridge$getLocationSkin());
         bridgeextension3_51.pop();
      }
   }

   @VersionGate(min = 6)
   public void method6(BridgeExtension2_11 bridgeextension2_111, EntityPlayerBridge bridgeextension2222, ModelPlayerBridge bridgeextension2_73, int number4, int number5) {
      List list6 = this.method4(bridgeextension2222, bridgeextension2_73);
      if (list6 != null) {
         VertexConsumerBridge bridge4_67 = (VertexConsumerBridge)bridgeextension2_111.method2(LunarRenderTypes.field10.get(this.method2(bridgeextension2222, number4).orElseThrow())).orElseThrow();
         this.method7(bridgeextension2222, bridgeextension2_111.method51(), bridge4_67, (Integer)bridgeextension2_111.method46().get(), 655360, list6, number5);
      }
   }

   private void method7(EntityPlayerBridge bridgeextension2221, Bridge5_16 bridge5_162, VertexConsumerBridge bridge4_63, int number4, int number5, List<SkinPartEntry> list6, int number7) {
      Skins3d skins3d8 = Skins3d.method13();
      boolean flag9 = skins3d8.method2(bridgeextension2221);
      float value10 = skins3d8.method3(skins3d8.method23(), flag9);
      float value11 = 1.035F;
      float value12 = skins3d8.method3(skins3d8.method23(), flag9);
      if (bridgeextension2221.bridge$hasRedOverlay()) {
         number5 = 196608;
      }

      for (SkinPartEntry pkg3$data14 : list6) {
         if (this.method8(bridgeextension2221, pkg3$data14) && pkg3$data14.field5.get().bridge$isVisible() && pkg3$data14.field6.get()) {
            if (pkg3$data14.field4 == BodyPartSpec.ARMS) {
               pkg3$data14.field1.x = 0.998F;
            } else if (pkg3$data14.field4 == BodyPartSpec.ARMS_SLIM) {
               pkg3$data14.field1.x = 0.499F;
            }

            if (pkg3$data14.field4 == BodyPartSpec.BODY) {
               value12 = skins3d8.method3(skins3d8.method24(), flag9);
            }

            if (pkg3$data14.field2) {
               pkg3$data14.field1.x *= -1.0F;
            }

            pkg3$data14.field1.y = pkg3$data14.field4.yOffsetMagicValue;
            bridge5_162.bridge$pushPose();
            pkg3$data14.field5.get().bridge$translateAndRotate(bridge5_162);
            bridge5_162.bridge$scale(value12, value11, value10);
            pkg3$data14.field1
               .method5(
                  bridge5_162,
                  bridge4_63,
                  number4,
                  number5,
                  (number7 >> 16 & 0xFF) / 255.0F,
                  (number7 >> 8 & 0xFF) / 255.0F,
                  (number7 & 0xFF) / 255.0F,
                  (number7 >> 24 & 0xFF) / 255.0F,
                  0.0625F
               );
            bridge5_162.bridge$popPose();
         }
      }
   }

   private boolean method8(EntityPlayerBridge bridgeextension2221, SkinPartEntry pkg3$data2) {
      return switch (pkg3$data2.field3) {
         case LEFT_PANTS_LEG -> bridgeextension2221.bridge$showLeftPants();
         case RIGHT_PANTS_LEG -> bridgeextension2221.bridge$showRightPants();
         case LEFT_SLEEVE -> bridgeextension2221.bridge$showLeftSleeve();
         case RIGHT_SLEEVE -> bridgeextension2221.bridge$showRightSleeve();
         case JACKET -> bridgeextension2221.bridge$showJacket();
      };
   }

   @VersionGate(max = 5)
   private void method9(Bridge5_11 bridge5_111, List<SkinPartEntry> list2, BridgeExtension3_5 bridgeextension3_53, float value4, ResourceLocationBridge horsestats145) {
      Skins3d skins3d6 = Skins3d.method13();
      boolean flag7 = skins3d6.method2(bridge5_111);
      float value8 = skins3d6.method3(skins3d6.method23(), flag7);
      float value9 = 1.035F;
      float value10 = skins3d6.method3(skins3d6.method23(), flag7);

      for (SkinPartEntry pkg3$data12 : list2) {
         if (this.method8(bridge5_111, pkg3$data12) && pkg3$data12.field5.get().bridge$isVisible() && pkg3$data12.field6.get()) {
            pkg3$data12.field1.method1(pkg3$data12.field5.get());
            float value13;
            if (pkg3$data12.field4 == BodyPartSpec.ARMS) {
               value13 = 0.998F;
            } else if (pkg3$data12.field4 == BodyPartSpec.ARMS_SLIM) {
               value13 = 0.499F;
            } else {
               value13 = 0.0F;
            }

            if (pkg3$data12.field4 == BodyPartSpec.BODY) {
               value10 = skins3d6.method3(skins3d6.method24(), flag7);
            }

            if (pkg3$data12.field2) {
               value13 *= -1.0F;
            }

            bridgeextension3_53.push();
            pkg3$data12.field1.method7(bridgeextension3_53, value4, value13, pkg3$data12.field4.yOffsetMagicValue, value10, value9, value8, horsestats145);
            bridgeextension3_53.pop();
         }
      }
   }

   public boolean method7() {
      return true;
   }
}
