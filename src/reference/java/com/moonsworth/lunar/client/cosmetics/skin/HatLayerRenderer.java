package com.moonsworth.lunar.client.cosmetics.skin;

import com.lunarclient.apollo.module.limb.ArmorPiece;
import com.lunarclient.apollo.module.limb.LimbModule;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderTypeBridge;
import com.moonsworth.lunar.bridge.VertexConsumerBridge;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.Bridge5_16;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.BridgeExtension2_11;
import com.moonsworth.lunar.bridge.ModelPlayerBridge;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.bridge.LunarItemType;
import com.moonsworth.lunar.bridge.LayerRendererBridge;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.ItemStackRenderStateBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.network.apollo.LimbApolloHandler;
import com.moonsworth.lunar.client.mod.render.skins3d.Skins3d;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.Collection;
import java.util.Optional;
import javax.annotation.Nullable;

public class HatLayerRenderer implements LayerRendererBridge<Bridge5_11, EntityPlayerBridge> {
   public static final HatLayerRenderer field1 = new HatLayerRenderer();

   public HatLayerRenderer() {
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
   private com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3 method4(EntityPlayerBridge bridgeextension2221, ModelPlayerBridge bridgeextension2_72) {
      Skins3d skins3d3 = Skins3d.method13();
      if (!skins3d3.shouldRender(bridgeextension2221)) {
         return null;
      }

      ItemStackRenderStateBridge mixinhelper_144 = bridgeextension2221.bridge$getHeadItem();
      if (mixinhelper_144 != null && mixinhelper_144.bridge$getLunarItemType() == LunarItemType.SKULL) {
         boolean flag5 = false;
         Optional optional6 = Ref.method4().method84().method3(LimbModule.class);
         if (optional6.isPresent()) {
            Collection list7 = (Collection)((LimbApolloHandler)optional6.get()).method6().get(bridgeextension2221.bridge$getUniqueID());
            if (list7 != null && list7.contains(ArmorPiece.HELMET)) {
               flag5 = true;
            }
         }

         if (Ref.method4().method40().method84().method41(bridgeextension2221)) {
            flag5 = true;
         }

         if (!flag5) {
            return null;
         }
      }

      return bridgeextension2_72.bridge$bipedHead().bridge$isVisible() && bridgeextension2221.bridge$showHat() ? skins3d3.method34().method3(bridgeextension2221) : null;
   }

   @VersionGate(max = 5)
   public void method5(
      BridgeExtension3_5 bridgeextension3_51, Bridge5_11 bridge5_112, ModelPlayerBridge bridgeextension2_73, float value4, float value5, float value6, float value7, float value8, float value9, float value10
   ) {
      com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3 pkg311 = this.method4(bridge5_112, bridgeextension2_73);
      if (pkg311 != null) {
         bridgeextension3_51.push();
         boolean flag12 = Bridge.getMinecraftVersion().equals(Config.field1);
         if (bridge5_112.bridge$isVisiblyCrouching() && !flag12) {
            bridgeextension3_51.translate(0.0, 0.2F, 0.0);
         }

         Skins3d skins3d13 = Skins3d.method13();
         float value14 = skins3d13.method3(skins3d13.method25(), skins3d13.method2(bridge5_112));
         pkg311.method1(bridgeextension2_73.bridge$bipedHead());
         pkg311.method6(bridgeextension3_51, value10 * value14, bridge5_112.bridge$getLocationSkin());
         bridgeextension3_51.pop();
      }
   }

   @VersionGate(min = 6)
   public void method6(BridgeExtension2_11 bridgeextension2_111, EntityPlayerBridge bridgeextension2222, ModelPlayerBridge bridgeextension2_73, int number4, int number5) {
      com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3 pkg36 = this.method4(bridgeextension2222, bridgeextension2_73);
      if (pkg36 != null) {
         VertexConsumerBridge bridge4_67 = (VertexConsumerBridge)bridgeextension2_111.method2(LunarRenderTypes.field10.get(this.method2(bridgeextension2222, number4).orElseThrow())).orElseThrow();
         this.method7(pkg36, bridgeextension2222, bridgeextension2_111.method51(), bridge4_67, (Integer)bridgeextension2_111.method46().get(), 655360, bridgeextension2_73, number5);
      }
   }

   public void method7(
      com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3 pkg31,
      EntityPlayerBridge bridgeextension2222,
      Bridge5_16 bridge5_163,
      VertexConsumerBridge bridge4_64,
      int number5,
      int number6,
      ModelPlayerBridge bridgeextension2_77,
      int number8
   ) {
      Skins3d skins3d9 = Skins3d.method13();
      float value10 = skins3d9.method3(skins3d9.method25(), skins3d9.method2(bridgeextension2222));
      pkg31.method1(bridgeextension2_77.bridge$bipedHead());
      bridge5_163.bridge$scale(value10, value10, value10);
      if (bridgeextension2222.bridge$hasRedOverlay()) {
         number6 = 196608;
      }

      pkg31.method5(
         bridge5_163, bridge4_64, number5, number6, (number8 >> 16 & 0xFF) / 255.0F, (number8 >> 8 & 0xFF) / 255.0F, (number8 & 0xFF) / 255.0F, (number8 >> 24 & 0xFF) / 255.0F, 0.0625F
      );
   }

   public boolean method7() {
      return true;
   }
}
