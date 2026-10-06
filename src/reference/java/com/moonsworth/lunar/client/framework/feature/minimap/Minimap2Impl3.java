package com.moonsworth.lunar.client.framework.feature.minimap;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import com.moonsworth.lunar.bridge.itemcounter.mixin.Itemcounter2;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.MinimapOptionWidget;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.feature.waypoints.GuiHandler2;
import com.moonsworth.lunar.client.driver.DriverRouteRegistryLegacy;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.rewindhandlers.ConfigureWaypointPropsLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump46;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.LinkedHashMap;

public class Minimap2Impl3 extends Minimap2_2<GuiHandler2> {
   private float field5 = -1.0F;

   public Minimap2Impl3(com.moonsworth.lunar.client.mod.render.minimap.Minimap var1, double var2, double var4, float var6, float var7, GuiHandler2 var8) {
      super(var1, var2, var4, var6, var7, var8);
   }

   @Override
   public void method1(MixinHelper_4 var1, Bridge5Extension_5 var2, float var3, float var4, float var5) {
      String var6 = ThreadModuleDump46.method3(((GuiHandler2)this.method10()).getName());
      var1.push();
      var1.method38(var4, var5, 0.0F);
      var1.method42(-var3);
      var1.method38(-ThreadModuleDump63.method10().bridge$getStringWidth(var6) / 2.0F, -4.0F, 0.0F);
      var1.method18(
         ThreadModuleDump63.method10(),
         var6,
         0,
         0,
         ((GuiHandler2)this.method10()).method46().method4().method13(),
         (Boolean)this.method7().method23().get()
      );
      var1.pop();
   }

   @Override
   public void method2(MinimapOptionWidget var1, MixinHelper_4 var2, Data2 var3) {
      String[] var4 = var1.method1("waypointTooltip", new Object[]{((GuiHandler2)this.method10()).getName()})
         .split("\n");
      float var5 = 0.0F;

      for (String var9 : var4) {
         var5 = Math.max(var5, FontRegistry.method9().method4(var9) + 8.0F);
      }

      float var13 = var3.IIRCROICCRROCOCOIOIHHOCRHOIHIR() + 9.0F;
      float var14 = var3.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() + 9.0F;
      var2.push();
      var2.method38(0.0F, 0.0F, 10.0F);
      LcuiScreen.method117(var2, var13, var14, var5, FontRegistry.method9().getHeight() * var4.length * 2 + 5, 5.0F, Integer.MIN_VALUE);
      int var15 = 0;

      for (String var12 : var4) {
         FontRegistry.method9().method17(var2, var12, var13 + 4.0F, var14 + 2.0F + var15, -1, false);
         var15 += FontRegistry.method9().getHeight() * 2;
      }

      var2.pop();
   }

   @Override
   public void method3() {
      this.field5 = (float)((GuiHandler2)this.method10()).method35().bridge$yCoord();
   }

   @Override
   public void method4(float var1, float var2) {
      Vec3Bridge var3 = ((GuiHandler2)this.method10()).method35();
      double var4 = var3.bridge$xCoord() + var1;
      double var6 = var3.bridge$zCoord() + var2;
      ((GuiHandler2)this.method10())
         .method20(Vec3Bridge.method2(var4, this.method6((int)Math.round(var4), (int)this.field5, (int)Math.round(var6)), var6));
   }

   @Override
   public void method5() {
      Client.method109().method48().OHOOCIIHRRIRCHOIIHHROORHIOIORC();
      Client.method109().method48().method20();
      this.field5 = -1.0F;
   }

   public int method6(int var1, int var2, int var3) {
      Itemcounter6Extension var4 = ThreadModuleDump63.method8();
      if (var4 == null) {
         return var2;
      }

      if (var2 <= var4.bridge$getMinBuildHeight()) {
         var2 = var4.bridge$getMinBuildHeight() + 1;
      }

      Itemcounter2 var5 = var4.bridge$getChunk(var1 >> 4, var3 >> 4);
      if (var5 == null) {
         return var2;
      }

      int var6 = var1 & 15;
      int var7 = 0;
      int var8 = var3 & 15;
      if (var5.bridge$getBlockState(var6, var2, var8).bridge$getBlock().bridge$isAir()
         && var5.bridge$getBlockState(var6, var2 - 1, var8).bridge$getBlock().bridge$isAir()) {
         while (var7 < var2) {
            if (this.method7(var5, var6, var2 - var7, var8)) {
               return var2 - var7;
            }

            var7++;
         }

         var7 = 0;
      }

      while (var7 < var4.bridge$getMaxBuildHeight() || var7 > var4.bridge$getMaxBuildHeight() + 1) {
         if (var7 > var4.bridge$getMaxBuildHeight() + 1 && this.method7(var5, var6, var2 - var7, var8)) {
            return var2 - var7;
         }

         if (var7 < var4.bridge$getMaxBuildHeight() && this.method7(var5, var6, var2 + var7, var8)) {
            return var2 + var7;
         }

         var7++;
      }

      return this.method7().method29().method12(var5, var1, var2 - 1, var3) + 1;
   }

   private boolean method7(Itemcounter2 var1, int var2, int var3, int var4) {
      return var1.bridge$getBlockState(var2, var3, var4).bridge$getBlock().bridge$isAir()
         && !var1.bridge$getBlockState(var2, var3 - 1, var4).bridge$getBlock().bridge$isAir();
   }

   @Override
   public LinkedHashMap<String, Runnable> method6() {
      LinkedHashMap var1 = new LinkedHashMap();
      var1.put(
         "edit",
         () -> DriverViewportLegacy.method50()
            .method17(
               DriverRouteRegistryLegacy.field18,
               ConfigureWaypointPropsLegacy.method2()
                  .method1(((GuiHandler2)this.method10()).getServer())
                  .method2(((GuiHandler2)this.method10()).getWorld())
                  .method3(((GuiHandler2)this.method10()).getName())
                  .method4(((GuiHandler2)this.method10()).method35().bridge$xCoord())
                  .method5(((GuiHandler2)this.method10()).method35().bridge$yCoord())
                  .method6(((GuiHandler2)this.method10()).method35().bridge$zCoord())
                  .method7()
            )
      );
      var1.put("remove", () -> Client.method109().method48().method9((GuiHandler2)this.method10()));
      return var1;
   }
}
