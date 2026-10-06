package com.moonsworth.lunar.client.mod.misc;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge2_43;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.BridgeExtension_9;
import com.moonsworth.lunar.bridge.Bridge_28;
import com.moonsworth.lunar.bridge.Bridge_56;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.client.framework.Framework;
import com.moonsworth.lunar.client.framework.Framework3;
import com.moonsworth.lunar.client.framework.Framework4;
import com.moonsworth.lunar.client.framework.Framework7Extension2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.HologramsType8;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.Holograms3;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Holograms4Iterator;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.Rewindhandlers.Data10;
import com.moonsworth.lunar.client.highlight.mixin.fishing.HighlightImpl9;
import com.moonsworth.lunar.client.highlight.mixin.nameplate.HighlightImpl2;
import com.moonsworth.lunar.client.lighting.LightingExtension443;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDumpType;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import org.joml.Vector3i;

public class SkyblockIceFill extends Framework7Extension2 {
   private List<Vector3i> field8 = new ArrayList<>();
   private List<Vector3i> field9 = new ArrayList<>();
   private List<Vector3i> field10 = new ArrayList<>();
   private boolean loaded;
   private Vector3i field11;

   public SkyblockIceFill(SkyblockDungeonPuzzles var1, LightingExtension443 var2) {
      super(true);
      this.method6(Framework.field16, Framework4.method4(false, var1));
      this.method6(Framework.field6, Framework3.method7(var2));
      this.method3(this::method13);
      this.handle(Data10.class, var1x -> this.method13());
      this.handle(HighlightImpl9.class, this::method8);
      this.handle(HighlightImpl2.class, this::method9);
   }

   public String getId() {
      return "SKYBLOCK_ICE_FILL";
   }

   protected void method1(boolean var1) {
   }

   public void method3(boolean var1) {
      if (var1) {
         SkyblockDungeonPuzzles var2 = (SkyblockDungeonPuzzles)((Framework4)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field16)).method1();
         var2.method13();
      }
   }

   public void method3(Holograms4Iterator var1) {
      Itemcounter6Extension var2 = ThreadModuleDump63.method8();
      if (var2 != null) {
         Bridge_56 var3 = Bridge.method34();
         Holograms3 var4 = (Holograms3)var1.method23().get();
         this.field11 = var4.method4(new Vector3i(15, 69, 7));
         Bridge3_23 var5 = var2.bridge$getBlockAt(this.field11.x(), this.field11.y(), this.field11.z());
         if (var5 == var3.method38() || var5 == var3.method39()) {
            Vector3i[][] var6 = new Vector3i[3][3];
            Vector3i[][] var7 = new Vector3i[5][5];
            Vector3i[][] var8 = new Vector3i[7][7];

            for (int var9 = 0; var9 < 3; var9++) {
               for (int var10 = 0; var10 < 3; var10++) {
                  var6[var9][var10] = var4.method4(new Vector3i(16 - var9, 70, var10 + 7));
               }
            }

            for (int var11 = 0; var11 < 5; var11++) {
               for (int var13 = 0; var13 < 5; var13++) {
                  var7[var11][var13] = var4.method4(new Vector3i(17 - var11, 71, var13 + 12));
               }
            }

            for (int var12 = 0; var12 < 7; var12++) {
               for (int var14 = 0; var14 < 7; var14++) {
                  var8[var12][var14] = var4.method4(new Vector3i(18 - var12, 72, var14 + 19));
               }
            }

            new Thread(() -> {
               this.field8 = this.method4(var6, 3);
               this.field9 = this.method4(var7, 5);
               this.field10 = this.method4(var8, 7);
               this.loaded = true;
            }).start();
         }
      }
   }

   private List<Vector3i> method4(Vector3i[][] var1, int var2) {
      Itemcounter6Extension var3 = ThreadModuleDump63.method8();
      assert var3 != null;
      ArrayList var4 = new ArrayList();
      int var5 = 0;
      boolean[][] var6 = new boolean[var2][var2];

      for (int var7 = 0; var7 < var2; var7++) {
         for (int var8 = 0; var8 < var2; var8++) {
            Vector3i var9 = var1[var7][var8];
            Bridge3_23 var10 = var3.bridge$getBlockAt(var9.x(), var9.y(), var9.z());
            boolean var11 = !var10.bridge$isAir();
            var6[var7][var8] = var11;
            if (var11) {
               var5++;
            }
         }
      }

      int var14 = var2 * var2 - var5;
      Point var15 = new Point(var2 / 2, 0);
      Point var16 = new Point(var2 / 2, var2 - 1);
      boolean[][] var17 = new boolean[var2][var2];
      if (!this.method5(var17, var6, var15, var16, var2, var14, var4)) {
         return new ArrayList<>();
      }

      ArrayList var18 = new ArrayList();

      for (Point var13 : var4) {
         var18.add(var1[var13.x][var13.y]);
      }

      return var18;
   }

   private boolean method5(boolean[][] var1, boolean[][] var2, Point var3, Point var4, int var5, int var6, List<Point> var7) {
      var1[var3.x][var3.y] = true;
      var7.add(var3);
      if (var7.size() == var6) {
         if (var4.x == var3.x && var4.y == var3.y) {
            return true;
         }

         var1[var3.x][var3.y] = false;
         var7.remove(var7.size() - 1);
         return false;
      } else {
         for (ThreadModuleDumpType var11 : ThreadModuleDumpType.values()) {
            Point var12 = new Point(var11.offsetX() + var3.x, var11.offsetY() + var3.y);
            if ((var4.x != var12.x || var4.y != var12.y || var7.size() == var6 - 1)
               && this.method6(var12, var5)
               && !var1[var12.x][var12.y]
               && !var2[var12.x][var12.y]
               && this.method5(var1, var2, var12, var4, var5, var6, var7)) {
               return true;
            }
         }

         var1[var3.x][var3.y] = false;
         var7.remove(var7.size() - 1);
         return false;
      }
   }

   private boolean method6(Point var1, int var2) {
      return var1.x >= 0 && var1.x < var2 && var1.y >= 0 && var1.y < var2;
   }

   private void method13() {
      this.field8.clear();
      this.field9.clear();
      this.field10.clear();
      this.loaded = false;
      this.field11 = null;
   }

   private void method8(HighlightImpl9 var1) {
      SkyblockDungeonPuzzles var2 = (SkyblockDungeonPuzzles)((Framework4)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field16)).method1();
      Holograms4Iterator var3 = var2.method47();
      if (var3 != null && var3.method25() == HologramsType8.ICE_FILL) {
         if (this.field11 != null && !this.loaded) {
            Vector3i var4 = new Vector3i(var1.method1().bridge$getX(), var1.method1().bridge$getY(), var1.method1().bridge$getZ());
            if (var4.equals(this.field11)) {
               Bridge_56 var5 = Bridge.method34();
               if (var1.method2().bridge$getBlock() == var5.method3()) {
                  if (var1.method3().bridge$getBlock() == var5.method38() || var1.method3().bridge$getBlock() == var5.method39()) {
                     this.method3(var3);
                  }
               }
            }
         }
      }
   }

   private void method9(HighlightImpl2 var1) {
      SkyblockDungeonPuzzles var2 = (SkyblockDungeonPuzzles)((Framework4)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field16)).method1();
      Holograms4Iterator var3 = var2.method47();
      if (var3 != null && var3.method25() == HologramsType8.ICE_FILL) {
         if (!this.field8.isEmpty() && !this.field9.isEmpty() && !this.field10.isEmpty()) {
            BridgeExtension_9 var4 = var1.method3();
            Bridge2_43 var5 = ThreadModuleDump63.method13();
            var4.push();
            var4.translate(-var5.bridge$renderPosX(), -var5.bridge$renderPosY(), -var5.bridge$renderPosZ());
            Bridge_28 var6 = var4.method11((Float)var2.method40().get());
            var6.method1(-65536);

            for (int var7 = 1; var7 < this.field8.size(); var7++) {
               Vector3i var8 = this.field8.get(var7 - 1);
               Vector3i var9 = this.field8.get(var7);
               var6.method3(var8.x() + 0.5, var8.y() + 0.5, var8.z() + 0.5, var9.x() + 0.5, var9.y() + 0.5, var9.z() + 0.5);
            }

            for (int var10 = 1; var10 < this.field9.size(); var10++) {
               Vector3i var12 = this.field9.get(var10 - 1);
               Vector3i var14 = this.field9.get(var10);
               var6.method3(var12.x() + 0.5, var12.y() + 0.5, var12.z() + 0.5, var14.x() + 0.5, var14.y() + 0.5, var14.z() + 0.5);
            }

            for (int var11 = 1; var11 < this.field10.size(); var11++) {
               Vector3i var13 = this.field10.get(var11 - 1);
               Vector3i var15 = this.field10.get(var11);
               var6.method3(var13.x() + 0.5, var13.y() + 0.5, var13.z() + 0.5, var15.x() + 0.5, var15.y() + 0.5, var15.z() + 0.5);
            }

            var6.end();
            var4.pop();
         }
      }
   }
}
