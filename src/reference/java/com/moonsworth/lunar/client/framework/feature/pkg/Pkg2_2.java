package com.moonsworth.lunar.client.framework.feature.pkg;

import com.lunarclient.apollo.module.limb.ArmorPiece;
import com.lunarclient.apollo.module.limb.LimbModule;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge4_6;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.Bridge5_16;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.BridgeExtension2_11;
import com.moonsworth.lunar.bridge.BridgeExtension2_7;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.bridge.BridgeType2_4;
import com.moonsworth.lunar.bridge.MExtension;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.ItemStackRenderStateBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.network.apollo.LimbApolloHandler;
import com.moonsworth.lunar.client.mod.render.skins3d.Skins3d;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.Collection;
import java.util.Optional;
import javax.annotation.Nullable;

public class Pkg2_2 implements MExtension<Bridge5_11, EntityPlayerBridge> {
   public static final Pkg2_2 field1 = new Pkg2_2();

   public int method1(EntityPlayerBridge var1) {
      return Skins3d.method13().isEnabled() ? 1 : 0;
   }

   public Optional<ResourceLocationBridge> method2(EntityPlayerBridge var1, int var2) {
      return Optional.ofNullable(var1.bridge$getLocationSkin());
   }

   public Optional<RenderLayerBridge> method6(ResourceLocationBridge var1) {
      return Optional.of(LunarRenderTypes.field10.get(var1));
   }

   @Nullable
   private com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3 method4(EntityPlayerBridge var1, BridgeExtension2_7 var2) {
      Skins3d var3 = Skins3d.method13();
      if (!var3.shouldRender(var1)) {
         return null;
      }

      ItemStackRenderStateBridge var4 = var1.bridge$getHeadItem();
      if (var4 != null && var4.bridge$getLunarItemType() == BridgeType2_4.SKULL) {
         boolean var5 = false;
         Optional var6 = ThreadModuleDump63.method4().method84().method3(LimbModule.class);
         if (var6.isPresent()) {
            Collection var7 = (Collection)((LimbApolloHandler)var6.get()).method6().get(var1.bridge$getUniqueID());
            if (var7 != null && var7.contains(ArmorPiece.HELMET)) {
               var5 = true;
            }
         }

         if (ThreadModuleDump63.method4().method40().method84().method41(var1)) {
            var5 = true;
         }

         if (!var5) {
            return null;
         }
      }

      return var2.bridge$bipedHead().bridge$isVisible() && var1.bridge$showHat() ? var3.method34().method3(var1) : null;
   }

   @Annotation2(max = 5)
   public void method5(
      BridgeExtension3_5 var1, Bridge5_11 var2, BridgeExtension2_7 var3, float var4, float var5, float var6, float var7, float var8, float var9, float var10
   ) {
      com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3 var11 = this.method4(var2, var3);
      if (var11 != null) {
         var1.push();
         boolean var12 = Bridge.getMinecraftVersion().equals(Config.field1);
         if (var2.bridge$isVisiblyCrouching() && !var12) {
            var1.translate(0.0, 0.2F, 0.0);
         }

         Skins3d var13 = Skins3d.method13();
         float var14 = var13.method3(var13.method25(), var13.method2(var2));
         var11.method1(var3.bridge$bipedHead());
         var11.method6(var1, var10 * var14, var2.bridge$getLocationSkin());
         var1.pop();
      }
   }

   @Annotation2(min = 6)
   public void method6(BridgeExtension2_11 var1, EntityPlayerBridge var2, BridgeExtension2_7 var3, int var4, int var5) {
      com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3 var6 = this.method4(var2, var3);
      if (var6 != null) {
         Bridge4_6 var7 = (Bridge4_6)var1.method2(LunarRenderTypes.field10.get(this.method2(var2, var4).orElseThrow())).orElseThrow();
         this.method7(var6, var2, var1.method51(), var7, (Integer)var1.method46().get(), 655360, var3, var5);
      }
   }

   public void method7(
      com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3 var1,
      EntityPlayerBridge var2,
      Bridge5_16 var3,
      Bridge4_6 var4,
      int var5,
      int var6,
      BridgeExtension2_7 var7,
      int var8
   ) {
      Skins3d var9 = Skins3d.method13();
      float var10 = var9.method3(var9.method25(), var9.method2(var2));
      var1.method1(var7.bridge$bipedHead());
      var3.bridge$scale(var10, var10, var10);
      if (var2.bridge$hasRedOverlay()) {
         var6 = 196608;
      }

      var1.method5(
         var3, var4, var5, var6, (var8 >> 16 & 0xFF) / 255.0F, (var8 >> 8 & 0xFF) / 255.0F, (var8 & 0xFF) / 255.0F, (var8 >> 24 & 0xFF) / 255.0F, 0.0625F
      );
   }

   public boolean method7() {
      return true;
   }
}
