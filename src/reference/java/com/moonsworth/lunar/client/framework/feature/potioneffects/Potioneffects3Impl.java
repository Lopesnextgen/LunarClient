package com.moonsworth.lunar.client.framework.feature.potioneffects;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.fog.Fog;
import com.moonsworth.lunar.bridge.fog.Fog2;
import com.moonsworth.lunar.client.ui.hud.HudPlacement;
import com.moonsworth.lunar.client.mod.render.potioneffects.PotionEffects;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;

class Potioneffects3Impl extends Potioneffects3 {
   protected Potioneffects3Impl(PotionEffects var1) {
      super(var1);
   }

   @Override
   protected void method2(Potioneffects4 var1, MixinHelper_4 var2, List<Fog> var3, float var4, float var5) {
      float var6 = this.field3.field30.get() ? (Float)this.field3.field31.get() * 2.0F + 3.0F : 3.0F;
      var4 += Math.max(0.0F, var6 - 6.0F);
      var5 += Math.max(0.0F, var6 - 6.0F);
      boolean var7 = (Boolean)this.field3.field9.get();
      boolean var8 = var7
         ? var1.method26().getVertical() == HudPlacement.BOTTOM
         : var1.method26().getHorizontal() == HudPlacement.RIGHT;
      int var9 = !this.field3.field28.get() && !this.field3.field30.get() ? 30 : 41;
      int var10 = (Integer)this.field3.field10.get();
      float var11 = var9 + var6;
      int var12 = var3.size();
      float var13 = var8 && var12 > var10 ? var12 / var10 * var11 : 0.0F;
      this.method2(var1, var7, var10, var12, var11, var6, var9);

      for (int var14 = 0; var14 < var12; var14++) {
         int var15 = var14 / var10;
         int var16 = var14 % var10;
         float var17 = var16 * var11;
         float var18 = var13 + (var8 ? -var15 : var15) * var11;
         float var19 = var4 + (var7 ? var17 : var18);
         float var20 = var5 + (var7 ? var18 : var17);
         this.method3(var1, var2, (Fog)var3.get(var14), var19, var20, var9);
      }
   }

   private void method2(Potioneffects4 var1, boolean var2, int var3, int var4, float var5, float var6, int var7) {
      int var8 = (var4 - 1) / var3 + 1;
      int var9 = Math.min(var4, var3);
      float var10 = var7 + (var9 - 1) * var5 + var6 - 3.0F;
      float var11 = var7 + (var8 - 1) * var5 + var6 - 3.0F;
      if (var2) {
         var1.method10(var10, var11);
      } else {
         var1.method10(var11, var10);
      }
   }

   private void method3(Potioneffects4 var1, MixinHelper_4 var2, Fog var3, float var4, float var5, int var6) {
      this.method3(var1, var2, var4, var5, var6, var6);
      Fog2 var7 = var3.bridge$getPotion();
      if (this.method8(var3)) {
         if (this.field3.method16()) {
            this.field3.method9(var2, var3, var4, var5, var6, var6);
         }

         if ((Boolean)this.field3.field16.get()) {
            String var8 = this.method6(var3);
            float var9 = ThreadModuleDump63.method10().bridge$getStringWidth(var8);
            this.method3(
               var2,
               var3,
               var7,
               var8,
               var4 + var6 / 2.0F - var9 / 2.0F,
               var5 + var6 - var6 / 3.0F + 1.0F,
               this.field3.field37,
               (Boolean)this.field3.field35.get()
            );
         }
      }

      this.method3(var2, var3, var7, var4 + var6 / 2.0F - 9.5F, var5 + var6 / 2.0F - 13.0F);
   }
}
