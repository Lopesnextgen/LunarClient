package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Holograms4Updater;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Holograms4Iterator;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Rewindhandlers;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.ichor.Annotation2;

@Annotation2(min = 33)
public class Holograms6Iterator extends Holograms6_3 {
   private final Holograms3 field7;
   private final Holograms2_6 field8;
   public Holograms4_2 field9 = null;

   public Holograms6Iterator(Holograms3_2 var1) {
      super("Dungeon", var1);
      this.field7 = new Holograms3(var1, this);
      this.field8 = new Holograms2_6(var1);
   }

   @Override
   public void method1(MixinHelper_4 var1, Holograms_9 var2, Holograms2_5 var3, float var4, float var5, MarkerModel<?> var6) {
      Holograms4Updater var7 = var3.method29();
      float var8 = var7.method8().method12(var2) + var2.method35();
      float var9 = var7.method8().method13(var2) + var2.method35();
      this.method5(var1, var2, var3, var4, var5, var6, var8, var9, (var4x, var5x, var6x) -> {
         for (Holograms4Iterator var8x : var3.method25()) {
            this.field7.method1(var1, var2, var3, var8x, var4x, var5x, var6x);
         }

         this.method3(var1, var2, var3, var4x, var5x, var6x);
         if (this.field9 != null) {
            this.field2.method26(this.field9.method1());
         }
      });
   }

   @Override
   public void method2(MixinHelper_4 var1, Holograms_9 var2, Holograms2_5 var3, float var4, float var5, MarkerModel<?> var6) {
      Nameplate2.method6(var1);
      float var7 = var2.method35();
      if (var2.method34() || var2.method33()) {
         this.field2.method13(var1, var2, var4 + var7, var5 + var7, 100.0F - var7 * 2.0F, 100.0F - var7 * 2.0F);
      }

      Holograms4Updater var8 = var3.method29();
      float var9 = var8.method8().method12(var2) + var7;
      float var10 = var8.method8().method13(var2) + var7;
      this.method5(var1, var2, var3, var4, var5, var6, var9, var10, (var4x, var5x, var6x) -> {
         Nameplate2.method5(var1);
         this.field9 = null;

         for (Rewindhandlers var8x : var3.method26()) {
            this.field8.method1(var1, var2, var3, var8x, var4x, var5x, var6x);
         }

         for (Holograms4Iterator var10x : var3.method25()) {
            this.field7.method2(var1, var2, var3, var10x, var4x, var5x, var6x);
         }

         Nameplate2.method6(var1);
      });
      if (var2.method34() || var2.method33()) {
         this.field2.method14(var1, var2);
      }

      Nameplate2.method5(var1);
   }

   private void method3(MixinHelper_4 var1, Holograms_9 var2, Holograms2_5 var3, float var4, float var5, MarkerModel<?> var6) {
      float var7 = Float.POSITIVE_INFINITY;
      this.field2.method28(null);
      float var8 = var2.method38();

      for (Holograms4Updater var10 : var3.getPlayers()) {
         if (!var10.method19()) {
            float var11 = var4 + var10.method8().method12(var2) + var8;
            float var12 = var5 + var10.method8().method13(var2) + var8;
            float var13 = (float)(Math.pow(var11 - var6.method10(), 2.0) + Math.pow(var12 - var6.method11(), 2.0));
            if (var13 < var7 && var2.method53()) {
               var7 = var13;
               this.field2.method28(var10);
            }
         }
      }

      for (Holograms4Updater var17 : var3.getPlayers()) {
         float var19 = var4 + var17.method8().method12(var2) + var8;
         float var21 = var5 + var17.method8().method13(var2) + var8;
         boolean var22 = this.method5(var2, var3, var4, var5, var19, var21);
         var17.method10(var1, var2, var19, var21, this.field2, var22);
         float var14 = (float)(Math.pow(var19 - var6.method10(), 2.0) + Math.pow(var21 - var6.method11(), 2.0));
         if (var14 < var17.method17()) {
            this.field9 = var17;
         }
      }

      Holograms4Updater var16 = this.field2.method27();
      if (var16 != null) {
         float var18 = var4 + var16.method8().method12(var2) + var8;
         float var20 = var5 + var16.method8().method13(var2) + var8;
         var16.method11(
            var1, var2, var18, var20, this.field2, var18 > var4 && var18 < var4 + 100.0F && var20 > var5 && var20 < var5 + 100.0F, true
         );
      }
   }

   @Override
   public boolean method3(Holograms_9 var1, Holograms2_5 var2) {
      return true;
   }
}
