package com.moonsworth.lunar.client.framework.feature.potioneffects;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.fog.Fog;
import com.moonsworth.lunar.client.click.holograms.Bridge7Iterator;
import com.moonsworth.lunar.client.glintcolorizer.Glintcolorizer5;
import com.moonsworth.lunar.client.mod.render.PotionEffects;
import com.moonsworth.lunar.client.mod.render.PotionEffects.Type2;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDumpType;

class Potioneffects2 {
   private final PotionEffects field1;

   public Potioneffects2(PotionEffects var1) {
      this.field1 = var1;
   }

   public void method1(MixinHelper_4 var1, Fog var2, float var3, float var4, float var5, float var6) {
      if ((!var2.bridge$getIsAmbient() || !(Boolean)this.field1.field44.get()) && !PotionEffects.method11(var2)) {
         float var7 = this.method2(var2);
         if (!(var7 <= 0.0F)) {
            float var8 = 1.0F;
            switch ((Type2)this.field1.field40.get()) {
               case LEFT:
                  this.method4(var1, var2, var3, var4, var8, var6, var7);
                  break;
               case RIGHT:
                  this.method4(var1, var2, var3 + var5 - var8, var4, var8, var6, var7);
                  break;
               case TOP:
                  this.method3(var1, var2, var3, var4, var5, var8, var7);
                  break;
               case BOTTOM:
                  this.method3(var1, var2, var3, var4 + var6 - var8, var5, var8, var7);
                  break;
               case BORDER:
                  this.method5(var1, var2, var3, var4, var5, var6, var8, var7);
            }
         }
      }
   }

   private float method2(Fog var1) {
      float var2 = var1.bridge$getMaxDuration();
      return var2 <= 0.0F ? 1.0F : Glintcolorizer5.clamp(var1.bridge$getDuration() / var2, 0.0F, 1.0F);
   }

   private void method3(MixinHelper_4 var1, Fog var2, float var3, float var4, float var5, float var6, float var7) {
      this.method6(var1, var2, var3, var4, var5 * var7, var6, ThreadModuleDumpType.LEFT, var7, 0.0F);
   }

   private void method4(MixinHelper_4 var1, Fog var2, float var3, float var4, float var5, float var6, float var7) {
      float var8 = var6 * var7;
      this.method6(var1, var2, var3, var4 + var6 - var8, var5, var8, ThreadModuleDumpType.BOTTOM, var7, 0.0F);
   }

   private void method5(MixinHelper_4 var1, Fog var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      float var9 = var6 - var7 * 2.0F;
      float var10 = (var5 + var9) * 2.0F;
      float var11 = 0.0F;
      float var12 = var5 / var10;
      float var13 = (var5 + var9) / var10;
      float var14 = (var5 * 2.0F + var9) / var10;
      float var15 = var8 * var10;
      float var16 = Math.min(var15, var5);
      if (var16 > 0.0F) {
         this.method6(var1, var2, var3, var4 + var6 - var7, var16, var7, ThreadModuleDumpType.LEFT, var16 / var10, var11);
      }

      var15 -= var5;
      var16 = Math.min(var15, var9);
      if (var16 > 0.0F) {
         this.method6(var1, var2, var3 + var5 - var7, var4 + var6 - var7 - var16, var7, var16, ThreadModuleDumpType.BOTTOM, var12 + var16 / var10, var12);
      }

      var15 -= var9;
      var16 = Math.min(var15, var5);
      if (var16 > 0.0F) {
         this.method6(var1, var2, var3 + var5 - var16, var4, var16, var7, ThreadModuleDumpType.RIGHT, var13 + var16 / var10, var13);
      }

      var15 -= var5;
      var16 = Math.min(var15, var9);
      if (var16 > 0.0F) {
         this.method6(var1, var2, var3, var4 + var7, var7, var16, ThreadModuleDumpType.TOP, var14 + var16 / var10, var14);
      }
   }

   private void method6(MixinHelper_4 var1, Fog var2, float var3, float var4, float var5, float var6, ThreadModuleDumpType var7, float var8, float var9) {
      int var10 = PotionEffects.method12(var2, var2.bridge$getPotion());
      boolean var11 = (Boolean)this.field1.field46.get();
      boolean var12 = var11 && this.field1.field47.method14();
      boolean var13 = !var12 && (Boolean)this.field1.field45.get();
      if (!var12 && !var13) {
         Bridge7Iterator.method94(var1, var3, var4, var5, var6, var11 ? this.field1.field47.method13() : var10);
      } else {
         boolean var14 = !var7.isVertical();
         float var15 = var14 ? var5 : var6;
         float var16 = var14 ? var6 : var5;
         float var17 = var14 ? var3 : var4;
         float var18 = var14 ? var4 : var3;
         float var19 = var18 + var16 / 2.0F;
         boolean var20 = var7 == ThreadModuleDumpType.LEFT || var7 == ThreadModuleDumpType.TOP;
         float var21 = var20 ? var9 : var8;
         float var22 = var20 ? var8 : var9;
         int var23 = var11 ? this.field1.field47.method14(var19 + var17) : var10;
         int var24 = var23;
         if (var13) {
            var24 = this.method7(var24, var21);
         }

         byte var25 = 6;
         float var26 = var17;

         for (int var27 = 1; var27 <= var25; var27++) {
            float var28 = (float)var27 / var25;
            float var29 = var26;
            var26 = var17 + var15 * var28;
            int var30 = var12 ? this.field1.field47.method14(var19 + var26) : var23;
            if (var13) {
               float var31 = var21 + (var22 - var21) * var28;
               var30 = this.method7(var30, var31);
            }

            this.method8(var1, var14, var29, var26, var18, var16, var30, var24);
            var24 = var30;
         }
      }
   }

   private int method7(int var1, float var2) {
      double var3 = var2 + ThreadModuleDump63.method15() / 2.5E9;
      float var5 = (float)(var3 - Math.floor(var3));
      var5 = 0.4F + 0.6F * (var5 < 0.5F ? var5 * 2.0F : 2.0F - var5 * 2.0F);
      return ThreadModuleDump23.method34(var1, var5);
   }

   private void method8(MixinHelper_4 var1, boolean var2, float var3, float var4, float var5, float var6, int var7, int var8) {
      if (var2) {
         var1.method8(var3, var5, var4, var5 + var6, var8, var7);
      } else {
         var1.method6(var5, var3, var5 + var6, var4, var8, var7);
      }
   }
}
