package com.moonsworth.lunar.client.framework.feature.pkg;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderLayerBridge;
import com.moonsworth.lunar.bridge.Bridge4_6;
import com.moonsworth.lunar.bridge.Bridge5_11;
import com.moonsworth.lunar.bridge.Bridge5_16;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.BridgeExtension2_11;
import com.moonsworth.lunar.bridge.BridgeExtension2_7;
import com.moonsworth.lunar.bridge.BridgeExtension3_5;
import com.moonsworth.lunar.bridge.MExtension;
import com.moonsworth.lunar.bridge.LunarRenderTypes;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.mod.render.skins3d.Skins3d;
import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;

public class Pkg3_2 implements MExtension<Bridge5_11, EntityPlayerBridge> {
   public static final Pkg3_2 field1 = new Pkg3_2();

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
   private List<Pkg3$Data> method4(EntityPlayerBridge var1, BridgeExtension2_7 var2) {
      Skins3d var3 = Skins3d.method13();
      if (!var3.shouldRender(var1)) {
         return null;
      }

      boolean var4 = var2.bridge$isSlim();
      com.moonsworth.lunar.client.framework.feature.pkg.mixin.Pkg3[] var5 = var3.method34().method4(var1, var4);
      if (var5 == null) {
         return null;
      }

      ArrayList var6 = new ArrayList();
      var6.add(new Pkg3$Data(var5[0], false, Pkg3$Type.LEFT_PANTS_LEG, Pkg3$Type2.LEGS, var2::bridge$bipedLeftLeg, var3.method21()::get));
      var6.add(new Pkg3$Data(var5[1], false, Pkg3$Type.RIGHT_PANTS_LEG, Pkg3$Type2.LEGS, var2::bridge$bipedRightLeg, var3.method22()::get));
      var6.add(
         new Pkg3$Data(var5[2], false, Pkg3$Type.LEFT_SLEEVE, var4 ? Pkg3$Type2.ARMS_SLIM : Pkg3$Type2.ARMS, var2::bridge$bipedLeftArm, var3.method17()::get)
      );
      var6.add(
         new Pkg3$Data(var5[3], true, Pkg3$Type.RIGHT_SLEEVE, var4 ? Pkg3$Type2.ARMS_SLIM : Pkg3$Type2.ARMS, var2::bridge$bipedRightArm, var3.method19()::get)
      );
      var6.add(new Pkg3$Data(var5[4], false, Pkg3$Type.JACKET, Pkg3$Type2.BODY, var2::bridge$bipedBody, var3.method16()::get));
      return var6;
   }

   @Annotation2(max = 5)
   public void method5(
      BridgeExtension3_5 var1, Bridge5_11 var2, BridgeExtension2_7 var3, float var4, float var5, float var6, float var7, float var8, float var9, float var10
   ) {
      List var11 = this.method4(var2, var3);
      if (var11 != null) {
         var1.push();
         boolean var12 = Bridge.getMinecraftVersion().equals(Config.field1);
         if (var2.bridge$isVisiblyCrouching() && !var12) {
            var1.translate(0.0, 0.2F, 0.0);
         }

         this.method9(var2, var11, var1, var10, var2.bridge$getLocationSkin());
         var1.pop();
      }
   }

   @Annotation2(min = 6)
   public void method6(BridgeExtension2_11 var1, EntityPlayerBridge var2, BridgeExtension2_7 var3, int var4, int var5) {
      List var6 = this.method4(var2, var3);
      if (var6 != null) {
         Bridge4_6 var7 = (Bridge4_6)var1.method2(LunarRenderTypes.field10.get(this.method2(var2, var4).orElseThrow())).orElseThrow();
         this.method7(var2, var1.method51(), var7, (Integer)var1.method46().get(), 655360, var6, var5);
      }
   }

   private void method7(EntityPlayerBridge var1, Bridge5_16 var2, Bridge4_6 var3, int var4, int var5, List<Pkg3$Data> var6, int var7) {
      Skins3d var8 = Skins3d.method13();
      boolean var9 = var8.method2(var1);
      float var10 = var8.method3(var8.method23(), var9);
      float var11 = 1.035F;
      float var12 = var8.method3(var8.method23(), var9);
      if (var1.bridge$hasRedOverlay()) {
         var5 = 196608;
      }

      for (Pkg3$Data var14 : var6) {
         if (this.method8(var1, var14) && var14.field5.get().bridge$isVisible() && var14.field6.get()) {
            if (var14.field4 == Pkg3$Type2.ARMS) {
               var14.field1.x = 0.998F;
            } else if (var14.field4 == Pkg3$Type2.ARMS_SLIM) {
               var14.field1.x = 0.499F;
            }

            if (var14.field4 == Pkg3$Type2.BODY) {
               var12 = var8.method3(var8.method24(), var9);
            }

            if (var14.field2) {
               var14.field1.x *= -1.0F;
            }

            var14.field1.y = var14.field4.yOffsetMagicValue;
            var2.bridge$pushPose();
            var14.field5.get().bridge$translateAndRotate(var2);
            var2.bridge$scale(var12, var11, var10);
            var14.field1
               .method5(
                  var2,
                  var3,
                  var4,
                  var5,
                  (var7 >> 16 & 0xFF) / 255.0F,
                  (var7 >> 8 & 0xFF) / 255.0F,
                  (var7 & 0xFF) / 255.0F,
                  (var7 >> 24 & 0xFF) / 255.0F,
                  0.0625F
               );
            var2.bridge$popPose();
         }
      }
   }

   private boolean method8(EntityPlayerBridge var1, Pkg3$Data var2) {
      return switch (var2.field3) {
         case LEFT_PANTS_LEG -> var1.bridge$showLeftPants();
         case RIGHT_PANTS_LEG -> var1.bridge$showRightPants();
         case LEFT_SLEEVE -> var1.bridge$showLeftSleeve();
         case RIGHT_SLEEVE -> var1.bridge$showRightSleeve();
         case JACKET -> var1.bridge$showJacket();
      };
   }

   @Annotation2(max = 5)
   private void method9(Bridge5_11 var1, List<Pkg3$Data> var2, BridgeExtension3_5 var3, float var4, ResourceLocationBridge var5) {
      Skins3d var6 = Skins3d.method13();
      boolean var7 = var6.method2(var1);
      float var8 = var6.method3(var6.method23(), var7);
      float var9 = 1.035F;
      float var10 = var6.method3(var6.method23(), var7);

      for (Pkg3$Data var12 : var2) {
         if (this.method8(var1, var12) && var12.field5.get().bridge$isVisible() && var12.field6.get()) {
            var12.field1.method1(var12.field5.get());
            float var13;
            if (var12.field4 == Pkg3$Type2.ARMS) {
               var13 = 0.998F;
            } else if (var12.field4 == Pkg3$Type2.ARMS_SLIM) {
               var13 = 0.499F;
            } else {
               var13 = 0.0F;
            }

            if (var12.field4 == Pkg3$Type2.BODY) {
               var10 = var6.method3(var6.method24(), var7);
            }

            if (var12.field2) {
               var13 *= -1.0F;
            }

            var3.push();
            var12.field1.method7(var3, var4, var13, var12.field4.yOffsetMagicValue, var10, var9, var8, var5);
            var3.pop();
         }
      }
   }

   public boolean method7() {
      return true;
   }
}
