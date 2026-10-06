package com.moonsworth.lunar.client.framework.feature.directionhud;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.horsestats.MathHelperBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.feature.markers.Markers2;
import com.moonsworth.lunar.client.framework.feature.waypoints.GuiHandler2;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.memory.Memory;
import com.moonsworth.lunar.client.mod.hud.directionhud.DirectionHud;
import com.moonsworth.lunar.client.mod.player.teamview.TeamView;
import com.moonsworth.lunar.client.mod.render.markers.Markers;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump67;
import java.util.List;
import javax.annotation.Nullable;

class Directionhud3 implements DirectionHud.Extension {
   private static final int field1 = 0;
   private static final int field2 = 23;
   private static final int field3 = 46;
   private static final int field4 = 69;
   private static final int field5 = 92;
   private static final int field6 = 116;
   private static final int field7 = 0;
   private static final int field8 = 9;
   private static final ResourceLocationBridge field9 = ResourceLocationBridge.create("lunar", "icons/compass-modern.png");

   @Override
   public void render(DirectionHud var1, MixinHelper_4 var2, float var3, float var4, double var5) {
      float var7 = this.method5((float)var5);
      if (var1.showMarkerValue.get()) {
         var2.method29(
            ThreadModuleDump63.method10(),
            String.format("%s", (int)var7),
            var3 + var1.width.get() / 2.0F,
            var4 - ThreadModuleDump63.method10().method19() - 7.0F,
            var1.field21.method14(var3 + var4),
            var1.textShadow.get()
         );
      }

      int var8 = var1.boldDirections.get() ? 23 : 69;
      if (var1.textShadow.get()) {
         var8 -= 23;
      }

      if (var1.showMarker.get()) {
         LcuiScreen.method25(var2, var3 + var1.width.get() / 2.0F, var4 - 6.0F, 6.0F, var1.field21.method14(var3 + var4));
      }

      float var9 = var1.width.get() * 0.25F;
      float var10 = var1.width.get() - var9;
      float var11 = var3 + var9;
      float var12 = ThreadModuleDump67.method16(var7, 0.0F, 360.0F, 218.0F, 966.0F) - (var10 - var9) / 2.0F - var9;
      LcuiScreen.method44(var2, field9, var3, var4 + 3.0F, ++var12, var8, var9, 23.0F, 1186.0F, 122.0F, 16777215, -1, -1, 16777215);
      LcuiScreen.method44(var2, field9, var11 + var10 - var9, var4 + 3.0F, var12 + var10, var8, var9, 23.0F, 1186.0F, 122.0F, -1, 16777215, 16777215, -1);
      LcuiScreen.method44(var2, field9, var11, var4 + 3.0F, var12 + var9, var8, var10 - var9, 23.0F, 1186.0F, 122.0F, -1, -1, -1, -1);
      Bridge5Extension_5 var13 = ThreadModuleDump63.method7();
      Gui2Extension var14 = var1.waypoints.get();
      if (var13 != null && var14 != Gui2Extension.NONE) {
         var1.registerOptions(
            var8x -> this.method2(var1, var2, var13, var8x, var3 + var1.width.get() / 2.0F, var4, var14 == Gui2Extension.ABOVE ? -4.0F : 33.0F, var7)
         );
      }

      Gui2Extension var15 = var1.teammates.get();
      if (var15 != Gui2Extension.NONE) {
         var1.forEachTeamMember(
            var8x -> this.method3(
               var1, var2, var13, var8x.method2(), var8x.method1(), var8x.method1().getColor(), var7, var3, var4, var15 == Gui2Extension.ABOVE ? -10.0F : 29.0F
            )
         );
         TeamView var16 = ThreadModuleDump63.method4().method40().method88();
         if (var16.isEnabled()) {
            var16.method4(
               var9x -> this.method3(
                  var1, var2, var13, var9x, null, var16.method14().method14(var3 + var4), var7, var3, var4, var15 == Gui2Extension.ABOVE ? -10.0F : 29.0F
               )
            );
            var16.method5(
               var9x -> this.method3(
                  var1, var2, var13, var9x, null, var16.method15().method14(var3 + var4), var7, var3, var4, var15 == Gui2Extension.ABOVE ? -10.0F : 29.0F
               )
            );
         }
      }

      Markers var22 = ThreadModuleDump63.method4().method40().method87();
      Gui2Extension var17 = var1.externalMarkers.get();
      if (var22.isEnabled() && var17 != Gui2Extension.NONE) {
         List var18 = var22.getMarkerManager().method10();

         for (int var19 = 0; var19 < var18.size(); var19++) {
            Markers2 var20 = (Markers2)var18.get(var19);
            this.method4(var1, var2, var13, var20, var3 + var1.width.get() / 2.0F, var4, var17 == Gui2Extension.ABOVE ? -4.0F : 33.0F, var7);
         }
      }
   }

   private void method2(DirectionHud var1, MixinHelper_4 var2, Bridge5Extension_5 var3, GuiHandler2 var4, float var5, float var6, float var7, float var8) {
      float var9 = (float)(var3.bridge$getPosX() - var4.method35().bridge$xCoord());
      float var10 = (float)(var3.bridge$getPosZ() - var4.method35().bridge$zCoord());
      float var11 = (float)(Math.atan2(var10, var9) * 180.0 / Math.PI + 90.0);
      FloatOption var12 = var1.width;
      float var13 = var12.get() * var11 / 360.0F - var12.get() * var8 / 360.0F;
      if (var13 > var12.get() / 2.0F) {
         var13 -= var12.get();
      }

      if (var13 < -var12.get() / 2.0F) {
         var13 += var12.get();
      }

      short var14 = 255;
      LcuiScreen.method78(var2, var5 + var13, var6 + var7, 3.0, var14 << 24 | var4.method46().method4().method14(var5 + var6) & 16777215);
   }

   private void method3(
      DirectionHud var1,
      MixinHelper_4 var2,
      Bridge5Extension_5 var3,
      @Nullable Bridge6_10 var4,
      Memory var5,
      int var6,
      float var7,
      float var8,
      float var9,
      float var10
   ) {
      double var11;
      double var13;
      if (var4 == null) {
         Vec3Bridge var15 = var5.method20();
         if (var15 == null) {
            return;
         }

         var11 = var15.bridge$xCoord();
         var13 = var15.bridge$zCoord();
      } else {
         var11 = var4.bridge$getPosX();
         var13 = var4.bridge$getPosZ();
      }

      float var24 = (float)(var3.bridge$getPosX() - var11);
      float var16 = (float)(var3.bridge$getPosZ() - var13);
      float var17 = (float)(Math.atan2(var16, var24) * 180.0 / Math.PI + 90.0);
      FloatOption var18 = var1.width;
      float var19 = var18.get() - 4.0F;
      float var20 = var19 * var17 / 360.0F - var19 * var7 / 360.0F;
      if (var20 > var18.get() / 2.0F) {
         var20 -= var19;
      }

      if (var20 < -var18.get() / 2.0F) {
         var20 += var19;
      }

      float var21 = var8 + var18.get() / 2.0F + var20;
      short var22 = 255;
      LcuiScreen.method87(var2, var21 + 4.0F, var9 + 3.0F, var9 + 26.0F, var22 << 24 | var6 & 16777215);
      if (var4 == null) {
         ThreadModuleDump63.method4().method50().method13(var2, var21, var9 + var10, var5.method10(), 4, var6, 1.0F);
      } else {
         ResourceLocationBridge var23 = var4.bridge$getServerSkinTexture();
         if (var23 != null) {
            LcuiScreen.method94(var2, var21, var9 + var10, 10.0F, 10.0F, var6);
            LcuiScreen.method50(var2, var23, var21 + 1.0F, var9 + var10 + 1.0F, ThreadModuleDump23.method22(16777215, var22), true);
         }
      }
   }

   private void method4(DirectionHud var1, MixinHelper_4 var2, Bridge5Extension_5 var3, Markers2 var4, float var5, float var6, float var7, float var8) {
      float var9 = (float)(var3.bridge$getPosX() - var4.getPos().x);
      float var10 = (float)(var3.bridge$getPosZ() - var4.getPos().z);
      float var11 = (float)(Math.atan2(var10, var9) * 180.0 / Math.PI + 90.0);
      FloatOption var12 = var1.width;
      float var13 = var12.get() * var11 / 360.0F - var12.get() * var8 / 360.0F;
      if (var13 > var12.get() / 2.0F) {
         var13 -= var12.get();
      }

      if (var13 < -var12.get() / 2.0F) {
         var13 += var12.get();
      }

      var2.method44(var0 -> var0.method29().method18());
      LcuiScreen.method91(var2);
      var2.push();
      var2.method38(var5 + var13, var6 + var7, 0.0F);
      var4.method6().method5(var2, 2.5F);
      var2.pop();
      LcuiScreen.method92(var2);
   }

   private float method5(float var1) {
      float var2 = var1 - MathHelperBridge.method3(var1 / 360.0F) * 360.0F;
      return var2 == 360.0F ? 0.0F : var2;
   }
}
