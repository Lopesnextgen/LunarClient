package com.moonsworth.lunar.client.framework.feature.potioneffects;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.fog.Fog;
import com.moonsworth.lunar.bridge.fog.Fog2;
import com.moonsworth.lunar.client.ui.hud.HudPlacement;
import com.moonsworth.lunar.client.mod.render.potioneffects.PotionEffects;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.List;
import java.util.Locale;

class Potioneffects3Impl2 extends Potioneffects3 {
   protected Potioneffects3Impl2(PotionEffects var1) {
      super(var1);
   }

   @Override
   protected void method2(Potioneffects4 var1, MixinHelper_4 var2, List<Fog> var3, float var4, float var5) {
      this.method2(var1, var2, var4, var5, var1.getWidth(), var1.getHeight());
      float var6 = var4;
      float var7 = var5;
      HudPlacement var8 = var1.method26().getHorizontal();
      if ((Boolean)this.field3.field24.get()) {
         if (var8 == HudPlacement.LEFT) {
            var8 = HudPlacement.RIGHT;
         } else if (var8 == HudPlacement.RIGHT) {
            var8 = HudPlacement.LEFT;
         }
      }

      if (var8 != HudPlacement.MIDDLE) {
         var4 += var8 == HudPlacement.RIGHT ? -2.0F : 3.0F;
      }

      var5 += 3.0F;
      float var9 = 0.0F;
      float var10 = 0.0F;

      for (int var11 = 0; var11 < var3.size(); var11++) {
         Fog var12 = (Fog)var3.get(var11);
         Fog2 var13 = var12.bridge$getPotion();
         if (this.field3.method16() && this.method8(var12)) {
            this.field3.method9(var2, var12, var6, var7 + var10, var1.getWidth(), 22.0F);
         }

         float var14 = 0.0F;
         boolean var15 = (Boolean)this.field3.field15.get();
         boolean var16 = (Boolean)this.field3.field17.get();
         if (var15 || var16) {
            StringBuilder var17 = new StringBuilder();
            if (var15) {
               String var18 = Bridge.method36().method11(var12.bridge$getEffectName());
               var17.append(this.field3.field23.get() ? var18.toUpperCase(Locale.ROOT) : var18);
               if (var16) {
                  var17.append(" ");
               }
            }

            if (var16) {
               var17.append(this.method7(var12));
            }

            String var23 = var17.toString();
            var14 = ThreadModuleDump63.method10().bridge$getStringWidth(var23) + 20.0F;
            float var19 = var4 + this.method2(var1, var8, var14);
            float var20 = var5 + var10;
            this.method2(
               var2, var12, var13, var23, var19, var20, this.field3.field36, (Boolean)this.field3.field34.get()
            );
            var9 = Math.max(var9, var14);
         }

         if ((Boolean)this.field3.field16.get()) {
            String var22 = this.method6(var12);
            float var24 = ThreadModuleDump63.method10().bridge$getStringWidth(var22) + 20.0F;
            if ((Boolean)this.field3.field16.get() && this.method8(var12)) {
               this.method2(
                  var2,
                  var12,
                  var13,
                  var22,
                  var4 + this.method2(var1, var8, var24),
                  var5 + var10 + (!var15 && !var16 ? 5 : 10),
                  this.field3.field37,
                  (Boolean)this.field3.field35.get()
               );
            }

            var9 = Math.max(var9, var24);
         }

         this.method2(var2, var12, var13, var4 + this.method3(var1, var8, var14), var5 + var10);
         var10 += 23.0F;
      }

      var1.method10(Math.max(var9, 20.0F) + 7.0F, var10 - 1.0F);
   }

   private float method2(Potioneffects4 var1, HudPlacement var2, float var3) {
      return switch (var2) {
         case RIGHT -> var1.getWidth() - var3;
         case MIDDLE -> var1.getWidth() / 2.0F - var3 / 2.0F + 20.0F;
         default -> 20.0F;
      };
   }

   private float method3(Potioneffects4 var1, HudPlacement var2, float var3) {
      return switch (var2) {
         case RIGHT -> var1.getWidth() - 20.0F;
         case MIDDLE -> var1.getWidth() / 2.0F - var3 / 2.0F;
         default -> 0.0F;
      };
   }
}
