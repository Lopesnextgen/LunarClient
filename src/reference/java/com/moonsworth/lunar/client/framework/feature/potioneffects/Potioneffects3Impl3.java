package com.moonsworth.lunar.client.framework.feature.potioneffects;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.fog.Fog;
import com.moonsworth.lunar.bridge.fog.Fog2;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.gui.notification.NotificationAnchor;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.mod.render.potioneffects.PotionEffects;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump71;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.ArrayList;
import java.util.List;

class Potioneffects3Impl3 extends Potioneffects3 {
   @Annotation2(min = 19)
   private static final ResourceLocationBridge field5 = ResourceLocationBridge.create("textures/gui/sprites/hud/effect_background.png");
   @Annotation2(min = 19)
   private static final ResourceLocationBridge field6 = ResourceLocationBridge.create("textures/gui/sprites/hud/effect_background_ambient.png");
   private static final int field7 = 24;
   private static final int field8 = 27;
   private static final int field9 = 25;
   private static final int field10 = 26;
   private static final int field11 = 2;
   private final List<Fog> field12 = new ArrayList<>();
   private final List<Fog> field13 = new ArrayList<>();

   protected Potioneffects3Impl3(PotionEffects var1) {
      super(var1);
   }

   @Override
   protected void method1(List<Fog> var1) {
      super.method1(var1);
      this.field12.clear();
      this.field13.clear();
      boolean var2 = (Boolean)this.field3.field11.get();

      for (int var3 = 0; var3 < var1.size(); var3++) {
         Fog var4 = (Fog)var1.get(var3);
         if (var4.bridge$shouldShowIcon()) {
            Fog2 var5 = var4.bridge$getPotion();
            boolean var6 = var2 && var5 != null && var5.bridge$isBadEffect();
            (var6 ? this.field13 : this.field12).add(var4);
         }
      }

      this.field12.sort(this::method13);
      this.field13.sort(this::method13);
   }

   @Override
   protected void method2(Potioneffects4 var1, MixinHelper_4 var2, List<Fog> var3, float var4, float var5) {
      if (this.field12.isEmpty() && this.field13.isEmpty()) {
         var1.method10(0.0F, 0.0F);
      } else {
         boolean var6 = (Boolean)this.field3.field12.get();
         int var7 = (Integer)this.field3.field13.get();
         int var8 = this.method9(this.field12.size(), var7);
         int var9 = this.method9(this.field13.size(), var7);
         int var10 = Math.max(Math.min(this.field12.size(), var7), Math.min(this.field13.size(), var7));
         float var11 = this.method7(var10);
         float var12 = this.method8(var8 + var9);
         float var13 = var6 ? var11 : var12;
         float var14 = var6 ? var12 : var11;
         var1.method10(var13, var14);
         this.method13(var1, var2, var4, var5, var13, var14);
         this.method3(var1, var2, this.field12, var6, var7, 0, var4, var5, var11);
         this.method3(var1, var2, this.field13, var6, var7, var8, var4, var5, var11);
      }
   }

   private void method3(Potioneffects4 var1, MixinHelper_4 var2, List<Fog> var3, boolean var4, int var5, int var6, float var7, float var8, float var9) {
      for (int var10 = 0; var10 < var3.size(); var10++) {
         int var11 = var10 / var5;
         int var12 = var10 % var5;
         int var13 = Math.min(var3.size() - var11 * var5, var5);
         float var14 = this.method4(var1, var4, var12, var13, var9);
         float var15 = (var6 + var11) * this.method6();
         this.method10(var1, var2, (Fog)var3.get(var10), var7 + (var4 ? var14 : var15), var8 + (var4 ? var15 : var14));
      }
   }

   private float method4(Potioneffects4 var1, boolean var2, int var3, int var4, float var5) {
      float var6 = this.method7(var4);
      if (var2) {
         float var8 = switch (var1.method26().getHorizontal()) {
            case LEFT -> var6;
            case MIDDLE -> (var5 + var6) / 2.0F;
            default -> var5;
         };
         return var8 - 24.0F - var3 * this.method5();
      } else {
         float var7 = switch (var1.method26().getVertical()) {
            case MIDDLE -> (var5 - var6) / 2.0F;
            case TOP -> 0.0F;
            default -> var5 - var6;
         };
         return var7 + var3 * this.method5();
      }
   }

   private int method5() {
      return this.field3.field14.get() ? 25 : 27;
   }

   private int method6() {
      return this.field3.field14.get() ? 26 : 27;
   }

   private float method7(int var1) {
      return var1 * this.method5() - (this.method5() - 24);
   }

   private float method8(int var1) {
      return var1 * this.method6() - (this.method6() - 24);
   }

   private int method9(int var1, int var2) {
      return (var1 + var2 - 1) / var2;
   }

   private void method10(Potioneffects4 var1, MixinHelper_4 var2, Fog var3, float var4, float var5) {
      boolean var6 = var3.bridge$getIsAmbient();
      if ((Boolean)this.field3.field49.get()) {
         if (ThreadModuleDump63.MC_VERSION >= 19) {
            LcuiScreen.method31(var2, var6 ? field6 : field5, var4, var5, 24.0F, 24.0F, -1);
         } else {
            LcuiScreen.method47(
               var2,
               ThreadModuleDump63.MC_VERSION >= 5 ? HRHOCCCOHCOCOOHHROHRHIOCCCHCIC : IHORICCICIICOCCRHCROOOCCICOIRH,
               var4,
               var5,
               var6 ? 165 : 141,
               166,
               24,
               24,
               -1
            );
         }
      }

      Fog2 var7 = var3.bridge$getPotion();
      float var8 = (Float)this.field3.field52.get();
      var2.push();
      var2.method38(var4 + 12.0F - 9.0F * var8, var5 + 12.0F - 9.0F * var8, 0.0F);
      var2.scale(var8, var8, 1.0F);
      this.method13(var2, var3, var7, 0.0F, 0.0F);
      var2.pop();
      if (this.method8(var3)) {
         if (this.field3.method16()) {
            this.field3.method9(var2, var3, var4 + 2.0F, var5 + 2.0F, 20.0F, 20.0F);
         }

         if ((Boolean)this.field3.field16.get()) {
            boolean var9 = !var6 && this.method9(var3);
            this.method11(
               var2,
               var3,
               var7,
               this.method6(var3),
               (NotificationAnchor)this.field3.field50.get(),
               var4,
               var5,
               var9 ? this.field3.field55 : this.field3.field37,
               !var9 && (Boolean)this.field3.field35.get()
            );
         }
      }

      if ((Boolean)this.field3.field17.get()) {
         String var10 = this.method7(var3);
         if (!var10.isEmpty()) {
            this.method11(
               var2,
               var3,
               var7,
               var10,
               (NotificationAnchor)this.field3.field51.get(),
               var4,
               var5,
               this.field3.field37,
               (Boolean)this.field3.field35.get()
            );
         }
      }
   }

   private void method11(
      MixinHelper_4 var1, Fog var2, Fog2 var3, String var4, NotificationAnchor var5, float var6, float var7, ColorOption var8, boolean var9
   ) {
      float var10 = this.method12();
      float var11 = (Float)this.field3.field53.get() * 0.5F;
      if (var10 > 0.0F) {
         var11 = Math.max(1, Math.round(var11 * var10)) / var10;
      }

      float var12 = ThreadModuleDump63.method10().bridge$getStringWidth(var4) * var11;
      float var13 = ThreadModuleDump63.method10().method19() * var11;
      boolean var14 = var5 == NotificationAnchor.TOP_RIGHT || var5 == NotificationAnchor.BOTTOM_RIGHT;
      boolean var15 = var5 == NotificationAnchor.BOTTOM_LEFT || var5 == NotificationAnchor.BOTTOM_RIGHT;
      float var16 = var6 + (var14 ? 21.0F - var12 : 3.25F);
      float var17 = var7 + (var15 ? 22.0F - var13 : 3.0F);
      var1.push();
      var1.getScaleFactor8(var10 > 0.0F ? LcuiScreen.method137(var16, var10) : var16, var10 > 0.0F ? LcuiScreen.method137(var17, var10) : var17, 0.0F);
      var1.scale(var11, var11, 1.0F);
      this.method13(var1, var2, var3, var4, 0.0F, 0.0F, var8, var9);
      var1.pop();
   }

   private float method12() {
      ThreadModuleDump71 var1 = LcuiScreen.method151();
      return this.field3.method17() && var1 != null ? var1.getScaleFactor() : 0.0F;
   }

   private int method13(Fog var1, Fog var2) {
      boolean var3 = var1.bridge$getIsAmbient();
      boolean var4 = var2.bridge$getIsAmbient();
      int var5 = (int)var1.bridge$getDuration();
      int var6 = (int)var2.bridge$getDuration();
      if ((var5 <= 32147 || var6 <= 32147) && (!var3 || !var4)) {
         int var8 = Boolean.compare(var4, var3);
         if (var8 == 0) {
            var8 = Boolean.compare(var6 < 0, var5 < 0);
         }

         if (var8 == 0) {
            var8 = Integer.compare(var6, var5);
         }

         if (var8 == 0) {
            var8 = Integer.compare(this.method14(var2), this.method14(var1));
         }

         return var8;
      } else {
         int var7 = Boolean.compare(var4, var3);
         return var7 != 0 ? var7 : Integer.compare(this.method14(var2), this.method14(var1));
      }
   }

   private int method14(Fog var1) {
      if (ThreadModuleDump63.MC_VERSION >= 6) {
         return var1.bridge$getColor();
      }

      Integer var2 = Potioneffects.get(var1.bridge$getPotionID());
      return var2 != null ? var2 & 16777215 : 0;
   }
}
