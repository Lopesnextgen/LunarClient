package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Holograms4Updater;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump67;
import com.moonsworth.lunar.ichor.Annotation2;

@Annotation2(min = 33)
public class Holograms6Iterator_2 extends Holograms6_3 {
   private long field7 = -1L;
   private HologramsType_2 field8 = null;

   public Holograms6Iterator_2(Holograms3_2 var1) {
      super("Boss", var1);
   }

   @Override
   public void method1(MixinHelper_4 var1, Holograms_9 var2, Holograms2_5 var3, float var4, float var5, MarkerModel<?> var6) {
      HologramsType_2 var7 = this.method4(var2, var3);
      if (var7 != null) {
         Holograms4Updater var8 = var3.method29();
         float var9 = Math.min(var7.getWidthInWorld(), var7.getHeightInWorld());
         if (var7.getRenderSize() != null) {
            var9 = Math.min(var9, var7.getRenderSize());
         }

         float var10 = var7.getRenderSize() == null ? var7.getWidthInWorld() : var7.getRenderSize();
         float var11 = (float)var7.getTextureWidth() / var7.getWidthInWorld() * var10;
         float var12 = (float)var7.getTextureHeight() / var7.getHeightInWorld() * var10;
         float var13 = Math.min(var11, var12);
         float var14 = var2.method35();
         float var15 = (100.0F - 2.0F * var14) / var13;
         float var16 = (var8.method8().method2() - var7.getTopLeft().x()) / var9 * (100.0F - 2.0F * var14) - (100.0F - 2.0F * var14) / 2.0F;
         float var17 = (var8.method8().method3() - var7.getTopLeft().y()) / var9 * (100.0F - 2.0F * var14) - (100.0F - 2.0F * var14) / 2.0F;
         var16 = ThreadModuleDump67.method12(var16, 0.0F, Math.max(0.0F, var7.getTextureWidth() * var15 - (100.0F - 2.0F * var14)));
         var17 = ThreadModuleDump67.method12(var17, 0.0F, Math.max(0.0F, var7.getTextureHeight() * var15 - (100.0F - 2.0F * var14)));
         float var18 = (var8.method8().method2() - var7.getTopLeft().x()) / var9 * (100.0F - 2.0F * var14) - var16;
         float var19 = (var8.method8().method3() - var7.getTopLeft().y()) / var9 * (100.0F - 2.0F * var14) - var17;
         float var20 = var16;
         float var21 = var17;
         float var22 = var9;
         this.method5(
            var1,
            var2,
            var3,
            var4,
            var5,
            var6.method5(),
            var18,
            var19,
            (var13x, var14x, var15x) -> {
               this.field2
                  .method19(
                     var1,
                     var7.getImage(),
                     var13x + var14 - var20,
                     var14x + var14 - var21,
                     var7.getTextureWidth() * var15,
                     var7.getTextureHeight() * var15,
                     0.0F,
                     0.0F,
                     var7.getTextureWidth() * var15,
                     var7.getTextureHeight() * var15
                  );
               double var16x = Double.POSITIVE_INFINITY;
               this.field2.method28(null);
               if (var2.method53()) {
                  for (Holograms4Updater var19x : var3.getPlayers()) {
                     if (!var19x.method19()) {
                        float var20x = (var19x.method8().method2() - var7.getTopLeft().x()) / var22 * (100.0F - 2.0F * var14) - var20;
                        float var21x = (var19x.method8().method3() - var7.getTopLeft().y()) / var22 * (100.0F - 2.0F * var14) - var21;
                        double var22x = Math.pow(var20x + var13x - var15x.method10(), 2.0) + Math.pow(var21x + var14x - var15x.method11(), 2.0);
                        if (var22x < var16x) {
                           var16x = var22x;
                           this.field2.method28(var19x);
                        }
                     }
                  }
               }

               for (Holograms4Updater var26 : var3.getPlayers()) {
                  float var28 = (var26.method8().method2() - var7.getTopLeft().x()) / var22 * (100.0F - 2.0F * var14) - var20;
                  float var30 = (var26.method8().method3() - var7.getTopLeft().y()) / var22 * (100.0F - 2.0F * var14) - var21;
                  var26.method10(
                     var1,
                     var2,
                     var28 + var13x,
                     var30 + var14x,
                     this.field2,
                     var18 > var13x && var18 < var13x + 100.0F && var19 > var14x && var19 < var14x + 100.0F
                  );
                  float var31 = (float)(Math.pow(var18 - var15x.method10() + var13x, 2.0) + Math.pow(var19 - var15x.method11() + var14x, 2.0));
                  if (var31 < var8.method17()) {
                     this.field2.method26(var8.method1());
                  }
               }

               Holograms4Updater var25 = this.field2.method27();
               if (var25 != null) {
                  float var27 = (var25.method8().method2() - var7.getTopLeft().x()) / var22 * (100.0F - 2.0F * var14) - var20;
                  float var29 = (var25.method8().method3() - var7.getTopLeft().y()) / var22 * (100.0F - 2.0F * var14) - var21;
                  var25.method11(
                     var1,
                     var2,
                     var27 + var13x,
                     var29 + var14x,
                     this.field2,
                     var18 > var13x && var18 < var13x + 100.0F && var19 > var14x && var19 < var14x + 100.0F,
                     true
                  );
               }
            }
         );
      }
   }

   @Override
   public void method2(MixinHelper_4 var1, Holograms_9 var2, Holograms2_5 var3, float var4, float var5, MarkerModel<?> var6) {
   }

   @Override
   public boolean method3(Holograms_9 var1, Holograms2_5 var2) {
      return this.method4(var1, var2) != null;
   }

   public HologramsType_2 method4(Holograms_9 var1, Holograms2_5 var2) {
      long var3 = ThreadModuleDump63.method3().bridge$getSystemTime();
      if (var3 - this.field7 < 100L) {
         return this.field8;
      }

      this.field7 = var3;
      Holograms4Updater var5 = var2.method29();
      if (var5 == null) {
         this.field8 = null;
         return null;
      }

      for (HologramsType_2 var9 : HologramsType_2.values()) {
         if (var9.getDungeonFloor() == var2.method34()
            && var9.getTopLeftBound().x() <= var5.method8().method2()
            && var9.getTopLeftBound().y() <= var5.method13()
            && var9.getTopLeftBound().z() <= var5.method8().method3()
            && var9.getBottomRightBound().x() >= var5.method8().method2()
            && var9.getBottomRightBound().y() >= var5.method13()
            && var9.getBottomRightBound().z() >= var5.method8().method3()) {
            this.field8 = var9;
            return var9;
         }
      }

      this.field8 = null;
      return null;
   }
}
