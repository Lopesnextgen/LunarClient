package com.moonsworth.lunar.client.framework.feature.directionhud;

import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.markers.Markers2;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.memory.Memory;
import com.moonsworth.lunar.client.mod.hud.directionhud.DirectionHud;
import com.moonsworth.lunar.client.mod.player.teamview.TeamView;
import com.moonsworth.lunar.client.mod.render.markers.Markers;
import com.moonsworth.lunar.client.util.ThreadModuleDump38;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import org.jetbrains.annotations.NotNull;

class Directionhud2 implements DirectionHud.Extension {
   private static final ResourceLocationBridge field1 = ResourceLocationBridge.create("lunar", "icons/round-compass.png");
   private static final ResourceLocationBridge field2 = ResourceLocationBridge.create("lunar", "icons/round-compass-pointer.png");

   @Override
   public void render(DirectionHud var1, MixinHelper_4 var2, float var3, float var4, double var5) {
      MixinCore9Extension var7 = (MixinCore9Extension)var1.method7(Framework.field1);
      float var8 = var7.getWidth();
      float var9 = var7.getHeight();
      var2.push();
      var2.method38(var3, var4, 0.0F);
      LcuiScreen.method31(var2, field1, 0.0F, 0.0F, var8, var9, -1);
      var2.method38(var8 / 2.0F, var9 / 2.0F, 0.0F);
      var2.method42((float)var5);
      var2.method38(-var8 / 2.0F, -var9 / 2.0F, 0.0F);
      LcuiScreen.method31(var2, field2, 0.0F, 0.0F, var8, var9, -1);
      var2.pop();
      BridgeExtension var10 = ThreadModuleDump63.method3().bridge$getRenderViewEntity();
      if (var10 != null) {
         var2.push();
         var2.method38(var3, var4, 0.0F);
         var2.method38(var8 / 2.0F, var9 / 2.0F, 0.0F);
         if (var1.showWaypoints.get()) {
            var1.registerOptions(var4x -> {
               Directionhud2.Data var5x = this.method4(var1, var10, var4x.method35().bridge$xCoord(), var4x.method35().bridge$zCoord());
               LcuiScreen.method78(var2, var5x.field1, var5x.field2, 3.0, var4x.method46().method4().method14(var5x.field1 + var5x.field2));
            });
         }

         if (var1.showTeammates.get()) {
            var1.forEachTeamMember(var4x -> this.method2(var4x, var1, var10, var2));
            TeamView var11 = ThreadModuleDump63.method4().method40().method88();
            if (var11.isEnabled()) {
               var11.method4(var5x -> this.method3(var5x, var11.method14(), var1, var10, var2));
               var11.method5(var5x -> this.method3(var5x, var11.method15(), var1, var10, var2));
            }
         }

         Markers var16 = ThreadModuleDump63.method4().method40().method87();
         if (var16.isEnabled() && var1.showExternalMarkers.get()) {
            List var12 = var16.getMarkerManager().method10();

            for (int var13 = 0; var13 < var12.size(); var13++) {
               Markers2 var14 = (Markers2)var12.get(var13);
               Directionhud2.Data var15 = this.method4(var1, var10, var14.getPos().x, var14.getPos().z);
               var2.push();
               var2.method38(var15.field1, var15.field2, 0.0F);
               var14.method6().method5(var2, 2.5F);
               var2.pop();
            }
         }

         var2.pop();
      }
   }

   private void method2(DirectionHud.Data2 var1, DirectionHud var2, BridgeExtension var3, MixinHelper_4 var4) {
      Bridge6_10 var5 = var1.registerOptions();
      Memory var6 = var1.method1();
      double var7;
      double var9;
      if (var5 != null) {
         var7 = var5.bridge$getPosX();
         var9 = var5.bridge$getPosZ();
      } else {
         Vec3Bridge var11 = var6.method20();
         if (var11 == null) {
            return;
         }

         var7 = var11.bridge$xCoord();
         var9 = var11.bridge$zCoord();
      }

      Directionhud2.Data var13 = this.method4(var2, var3, var7, var9);
      if (var5 == null) {
         ThreadModuleDump63.method4().method50().method13(var4, var13.field1, var13.field2, var6.method10(), 4, var6.getColor(), 1.0F);
      } else {
         ResourceLocationBridge var12 = var5.bridge$getServerSkinTexture();
         if (var12 != null) {
            LcuiScreen.method94(var4, var13.field1, var13.field2, 10.0F, 10.0F, var6.getColor());
            LcuiScreen.method50(var4, var12, var13.field1 + 1.0F, var13.field2 + 1.0F, -1, true);
         }
      }
   }

   private void method3(@NotNull Bridge6_10 var1, ColorOption var2, DirectionHud var3, BridgeExtension var4, MixinHelper_4 var5) {
      double var6 = var1.bridge$getPosX();
      double var8 = var1.bridge$getPosZ();
      Directionhud2.Data var10 = this.method4(var3, var4, var6, var8);
      ResourceLocationBridge var11 = var1.bridge$getServerSkinTexture();
      if (var11 != null) {
         LcuiScreen.method94(var5, var10.field1, var10.field2, 10.0F, 10.0F, var2.method14(var10.field1 + var10.field2));
         LcuiScreen.method50(var5, var11, var10.field1 + 1.0F, var10.field2 + 1.0F, -1, true);
      }
   }

   private Directionhud2.Data method4(DirectionHud var1, BridgeExtension var2, double var3, double var5) {
      MixinCore9Extension var7 = (MixinCore9Extension)var1.method7(Framework.field1);
      double var8 = var7.getWidth() / 2.0F - 2.0F;
      double var10 = var7.getHeight() / 2.0F - 2.0F;
      double var12 = var2.bridge$getPosX() - var3;
      double var14 = var2.bridge$getPosZ() - var5;
      double var16 = Math.atan2(var14, var12) - (Math.PI / 2);
      return new Directionhud2.Data((float)(var8 * ThreadModuleDump38.sin(var16)), (float)(var10 * -ThreadModuleDump38.method1(var16)));
   }

   private class Data {
      private final float field1;
      private final float field2;

      private Data(float var1, float var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      public float x() {
         return this.field1;
      }

      public float y() {
         return this.field2;
      }
   }
}
