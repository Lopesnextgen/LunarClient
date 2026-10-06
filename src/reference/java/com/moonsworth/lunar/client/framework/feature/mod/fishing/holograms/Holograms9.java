package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge2_43;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.horsestats.mixin.HorsestatsType2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.NameplateType;
import com.moonsworth.lunar.client.mod.skyblock.dungeonroutes.SkyblockDungeonRoutes;
import com.moonsworth.lunar.client.util.concurrent.SupplierExtension;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.render.color.RewindhandlersExtension;
import com.moonsworth.lunar.files.Files6_2;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.joml.Vector3d;

public class Holograms9 {
   public static final double[] field1 = new double[]{0.5, 0.2, 0.5};
   private static final RewindhandlersExtension field2 = RewindhandlersExtension.method23(-65536);
   private static final double field3 = 2.0;
   private static final double field4 = 1.0;
   private final Holograms3_3 field5;
   private final SupplierExtension<ItemStackBridge> field6 = SupplierExtension.lazy(() -> Bridge.method8().method38(Bridge.method28().method19()));

   public Holograms9(Holograms3_3 var1) {
      this.field5 = var1;
   }

   public void method1(AbstractRenderContext var1, Holograms9.Type var2, Holograms7 var3, List<int[]> var4) {
      com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms3 var5 = this.field5.method17().orElse(null);
      if (var5 != null) {
         SkyblockDungeonRoutes var6 = SkyblockDungeonRoutes.method13();
         if (var2 == Holograms9.Type.ESP) {
            for (Holograms10$Data var8 : var3.method6().getTextHolograms()) {
               double[] var9 = var5.method11(var8.pos.x, var8.pos.y, var8.pos.z);
               Click.drawStringWithColor(var1, var9[0] + 0.5, var9[1] + 1.7, var9[2] + 0.5, var8.text, true, true, false, 1.0F, RewindhandlersExtension.method23(-1));
            }
         }

         if (var3.getIndex() > 0) {
            Holograms7 var15 = var3.method7().getSections().get(var3.getIndex() - 1);
            if (!var15.method9().isEmpty()) {
               Files6_2 var18 = var15.method9().get(var15.method9().size() - 1);
               if (var18.field1 == NameplateType.ITEM_DROP) {
                  this.method3(var1, var2, var4, var5, var18, false, false);
               }
            }
         }

         if (var2 == Holograms9.Type.ESP && !var3.method8().isEmpty() && (this.field5.method9() || (Boolean)var6.method22().get() && var3.getIndex() == 0)) {
            int[] var16 = var3.method8().get(0);
            double[] var19 = var5.method11(var16[0] - 0.5, var16[1] + 2, var16[2] - 0.5);
            Click.drawStringWithColor(var1, var19[0], var19[1], var19[2], var3.method7().method22(), true, true, true, 1.0F, SkyblockDungeonRoutes.method13().method23());
         }

         if (var2 == Holograms9.Type.DEPTH
            && var6.method26().get() != com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Gui2Extension.PARTICLES
            && var6.method26().get() != com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Gui2Extension.NONE) {
            method5(
               var1,
               var3.method8(),
               var5,
               (com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Gui2Extension)var6.method26().get(),
               field2
            );
         }

         ArrayList var17 = new ArrayList();

         for (Files6_2 var23 : var3.method9()) {
            if (var23.field1 == NameplateType.PEARL) {
               var17.add(var23);
            }
         }

         for (Files6_2 var24 : var3.method9()) {
            if (var24.field1 != NameplateType.PEARL) {
               var17.add(var24);
            }
         }

         HashSet var22 = new HashSet();
         HashSet var25 = new HashSet();
         HashSet var10 = new HashSet();
         ArrayList var11 = new ArrayList();
         ArrayList var12 = new ArrayList();

         for (Files6_2 var14 : var17) {
            if (this.method2(var4, var5, var14, var11, var12)) {
               var22.add(var14);
            }

            if (!var10.add(((int[])var14.field2)[0] + "," + ((int[])var14.field2)[1] + "," + ((int[])var14.field2)[2])) {
               var25.add(var14);
            }
         }

         for (int var26 = var17.size() - 1; var26 >= 0; var26--) {
            Files6_2 var27 = (Files6_2)var17.get(var26);
            this.method3(var1, var2, var4, var5, var27, var22.contains(var27), var25.contains(var27));
         }
      }
   }

   private boolean method2(
      List<int[]> var1,
      com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms3 var2,
      Files6_2<NameplateType, int[]> var3,
      List<int[]> var4,
      List<int[]> var5
   ) {
      NameplateType var6 = (NameplateType)var3.field1;
      if (!var6.shouldRender()) {
         return false;
      }

      int[] var7 = (int[])var3.field2;
      int[] var8 = var2.method8(var7);
      boolean var9 = var1.stream().anyMatch(var1x -> var1x[0] == var8[0] && var1x[1] == var8[1] && var1x[2] == var8[2]);
      SkyblockDungeonRoutes var10 = SkyblockDungeonRoutes.method13();
      boolean var11 = !(Boolean)var10.method25().get();
      if (var11) {
         for (int[] var13 : var5) {
            if (var13[0] == var8[1] && var13[1] == var8[1] && var13[2] == var8[2]) {
               var11 = false;
               break;
            }
         }
      }

      if (var11 && var6 == NameplateType.BREAK_BLOCK) {
         for (int[] var17 : var4) {
            double var14 = Math.pow(var17[0] - var8[0], 2.0) + Math.pow(var17[1] - var8[1], 2.0) + Math.pow(var17[2] - var8[2], 2.0);
            if (var14 < 100.0) {
               var11 = false;
               break;
            }
         }

         if (var11) {
            var4.add(var8);
         }
      }

      if (!var9 && var11) {
         var5.add(var8);
         return true;
      } else {
         return false;
      }
   }

   private void method3(
      AbstractRenderContext var1,
      Holograms9.Type var2,
      List<int[]> var3,
      com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms3 var4,
      Files6_2<NameplateType, int[]> var5,
      boolean var6,
      boolean var7
   ) {
      NameplateType var8 = (NameplateType)var5.field1;
      if (var8.shouldRender()) {
         int[] var9 = (int[])var5.field2;
         int[] var10 = var4.method8(var9);
         boolean var11 = var3.stream().anyMatch(var1x -> var1x[0] == var10[0] && var1x[1] == var10[1] && var1x[2] == var10[2]);
         double var12 = var10[0] + (var7 ? 0.1 : 0.0);
         double var14 = var10[1] + (var7 ? 0.1 : 0.0);
         double var16 = var10[2] + (var7 ? 0.1 : 0.0);
         double var18 = var7 ? 0.8 : 1.0;
         if (var2 == Holograms9.Type.DEPTH && !var11 && var8 != NameplateType.BREAK_BLOCK) {
            Click.drawBox(var1, var12, var14, var16, var18, var18, var18, var8.getColorAlpha(), true, 1.0F, true);
         }

         if (var2 == Holograms9.Type.ESP) {
            Click.drawBox(var1, var12, var14, var16, var18, var18, var18, var11 ? var8.getColorAlpha() : var8.getColor(), false, 3.0F, true);
         }

         if (var2 == Holograms9.Type.ESP && var6) {
            Click.drawString(var1, var10[0] + 0.5, var10[1] + (var7 ? 0.3 : 0.5), var10[2] + 0.5, var8.getText(), true, true);
         }

         if (var2 == Holograms9.Type.ESP && var8 == NameplateType.PEARL) {
            Bridge5Extension_5 var20 = ThreadModuleDump63.method7();
            Vector3d var21 = new Vector3d(var20.bridge$getPosX(), var20.bridge$getPosY(), var20.bridge$getPosZ());
            Vector3d var22 = new Vector3d(var10[0] + 0.5, var10[1] + 0.5, var10[2] + 0.5);
            boolean var23 = var21.distanceSquared(var22) > 25.0;
            Holograms.Data var24 = null;
            if (var23) {
               var24 = Holograms.method3(new Vector3d(var10[0] + 0.5, var10[1] + 0.5, var10[2] + 0.5), true);
               if (var24.method3() != null && var22.distanceSquared(var24.method3()) > 4.0) {
                  var23 = false;
               }
            }

            if (var23) {
               Vector3d var25 = var24.method1().normalize(2.0);
               Bridge2_43 var26 = ThreadModuleDump63.method13();
               double var27 = var26.bridge$renderPosX() + var25.x * 10.0;
               double var29 = var26.bridge$renderPosY() + var25.y * 10.0 + ThreadModuleDump63.method7().bridge$getEyeHeight();
               double var31 = var26.bridge$renderPosZ() + var25.z * 10.0;
               Click.drawLine(var1, var10[0] + 0.5, var10[1] + 0.5, var10[2] + 0.5, var27, var29, var31, var8.getColor(), 2.0F, true);
               var1.push();
               var1.translate(var25.x * 10.0, var25.y * 10.0 + ThreadModuleDump63.method7().bridge$getEyeHeight(), var25.z * 10.0);
               var1.translate(var26.bridge$renderPosX(), var26.bridge$renderPosY(), var26.bridge$renderPosZ());
               Click.drawBox(var1, -0.25, -0.25, -0.25, 0.5, 0.5, 0.5, var8.getColor(), true, 1.0F, false);
               var1.pop();
            }
         }
      }
   }

   public void method4(Holograms7 var1) {
      SkyblockDungeonRoutes var2 = SkyblockDungeonRoutes.method13();
      if (var2.method26().get() == com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Gui2Extension.PARTICLES) {
         com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms3 var3 = this.field5.method17().orElse(null);
         if (var3 != null) {
            int[] var4 = null;

            for (int[] var6 : var1.method8()) {
               int[] var7 = var3.method8(var6);
               if (var4 != null) {
                  Holograms_11.method1(var4, var7, field1, HorsestatsType2.FLAME);
               }

               var4 = var7;
            }
         }
      }
   }

   public static void method5(
      AbstractRenderContext var0,
      List<int[]> var1,
      com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms3 var2,
      com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Gui2Extension var3,
      RewindhandlersExtension var4
   ) {
      boolean var5 = var3 == com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Gui2Extension.ARROW
         || var3 == com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Gui2Extension.ARROW_DASHED;
      boolean var6 = var3 == com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Gui2Extension.ARROW_DASHED
         || var3 == com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Gui2Extension.LINE_DASHED;
      int[] var7 = null;
      double var8 = 3.0;
      double var10 = -ThreadModuleDump63.method3().bridge$getSystemTime() / 1000.0 % var8 + var8;
      boolean var12 = false;

      for (int[] var14 : var1) {
         if (var7 != null) {
            var10 -= Math.sqrt(Math.pow(var14[0] - var7[0], 2.0) + Math.pow(var14[1] - var7[1], 2.0) + Math.pow(var14[2] - var7[2], 2.0));
         }

         var7 = var14;
      }

      var10 = var10 % var8 + var8;
      if (var10 > 2.0 && var6) {
         var12 = true;
         var10 -= 2.0;
      }

      var7 = null;

      for (int[] var29 : var1) {
         int[] var15 = var29;
         if (var2 != null) {
            var15 = var2.method8(var29);
         }

         if (var7 != null) {
            Vector3d var16 = new Vector3d(var7[0] + field1[0], var7[1] + field1[1], var7[2] + field1[2]);
            Vector3d var17 = new Vector3d(var15[0] + field1[0], var15[1] + field1[1], var15[2] + field1[2]);
            double var18 = var16.distance(var17);

            for (var10 += var18; var6 && var10 > (var12 ? 1.0 : 2.0); var18 = var16.distance(var17)) {
               double var20 = var12 ? 1.0 : 2.0;
               double var22 = var10 - var20;
               Vector3d var24 = var17.sub(var16, new Vector3d());
               Vector3d var25 = var16.add(var24.normalize(var18 - var22, new Vector3d()), new Vector3d());
               var10 = var22;
               if (!var12) {
                  Click.drawLineWithArrow(var0, var16, var25, var4, 2.0F, true, var5);
               }

               var12 = !var12;
               var16 = var25;
            }

            if (!var12) {
               Click.drawLineWithArrow(var0, var16, var17, var4, 2.0F, true, var5 && !var6);
            }
         }

         var7 = var15;
      }
   }

   public enum Type {
      DEPTH,
      ESP;
   }
}
