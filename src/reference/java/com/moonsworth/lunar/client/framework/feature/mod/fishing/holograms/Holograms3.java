package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Nameplate;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.Nameplate4;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Holograms4Iterator;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import com.moonsworth.lunar.client.render.color.RewindhandlersExtension;
import com.moonsworth.lunar.files.Files6_2;
import com.moonsworth.lunar.ichor.Annotation2;
import java.util.HashMap;
import java.util.HashSet;

@Annotation2(min = 33)
public class Holograms3 {
   private final Holograms6Iterator field1;
   private final Holograms3_2 field2;
   private MarkerModel<?> field3 = new Data2(0.0, 0.0);
   private Holograms4Iterator field4;

   public Holograms3(Holograms3_2 var1, Holograms6Iterator var2) {
      this.field2 = var1;
      this.field1 = var2;
   }

   public void method1(MixinHelper_4 var1, Holograms_9 var2, Holograms2_5 var3, Holograms4Iterator var4, float var5, float var6, MarkerModel<?> var7) {
      this.field3 = var7;
      this.field4 = var4;
      if (!this.method6(var4, var2)) {
         Files6_2 var8 = this.method4(var2, var3, var4, var5, var6);
         float var9 = (Float)var8.field1;
         float var10 = (Float)var8.field2;
         var1.push();
         HologramsType2 var11 = var2.method23(var4);
         float var12 = var2.method33() && var2.method45() ? var3.method29().method14() : 0.0F;
         if (var11 != null) {
            float var13 = var2.method24(var3);
            this.field2.method21(var1, var11, var9, var10, Math.min(var13, var13 * var11.getTextureWidth() / var11.getTextureHeight()), var13, var12);
         } else {
            Gui2Extension2 var20 = var4.method30().method6() == HologramsType5.PUZZLE ? var2.method46() : var2.method47();
            AdventureChatFormatting var14 = var4.method30().method2().asColor();
            String[] var15 = this.method5(var4, var20);
            Object var16 = var15[0];
            Object var17 = var15[1];
            Object var18 = var15[2];
            var1.method38(var9 * this.field2.field8, var10 * this.field2.field8, 0.0F);
            var1.method42(var12);
            if (var16 != null) {
               float var19 = var2.method48() / 100.0F * 0.4F;
               this.field2
                  .method11(
                     var1, var14 + var16, 0.0F, -(var18 == null ? 0.0F : var19 * 8.0F) - (var17 == null ? 0.0F : var19 * 4.0F), var19, Nameplate.Type.BORDER
                  );
               if (var17 != null) {
                  this.field2.method11(var1, var14 + var17, 0.0F, -(var18 == null ? 0.0F : var19 * 8.0F) + var19 * 4.0F, var19, Nameplate.Type.BORDER);
               }
            }

            if (var18 != null) {
               float var21 = var2.method48() / 100.0F * 0.8F;
               this.field2
                  .method11(
                     var1, var14 + var18, 0.0F, (var16 == null ? 0.0F : var21 * 4.0F) + (var17 == null ? 0.0F : var21 * 2.0F), var21, Nameplate.Type.BORDER
                  );
            }
         }

         var1.pop();
      }
   }

   public void method2(MixinHelper_4 var1, Holograms_9 var2, Holograms2_5 var3, Holograms4Iterator var4, float var5, float var6, MarkerModel<?> var7) {
      if (var4.method30().method6() != null) {
         this.field3 = var7;
         this.field4 = var4;
         HashSet var8 = new HashSet();

         for (Nameplate4 var10 : var4.method28()) {
            var8.add(var10.method8() + var10.method9() * 10);
         }

         RewindhandlersExtension var15 = var2.method20(var4.method30().method6(), false);

         for (Nameplate4 var11 : var4.method28()) {
            float var12 = var5 + var2.method35() + var11.method12(var2);
            float var13 = var6 + var2.method35() + var11.method13(var2);
            this.method3(var1, var12 + var2.method19(var3) / 2.0F, var13 + var2.method19(var3) / 2.0F, var2.method17(var3), var2.method17(var3), var15);
            int var14 = 0;
            if (var8.contains(var11.method8() + 1 + var11.method9() * 10)) {
               this.method3(
                  var1,
                  var12 + var2.method19(var3) / 2.0F + var2.method17(var3),
                  var13 + var2.method19(var3) / 2.0F,
                  var2.method19(var3),
                  var2.method17(var3),
                  var15
               );
               var14++;
            }

            if (var8.contains(var11.method8() + var11.method9() * 10 + 10)) {
               this.method3(
                  var1,
                  var12 + var2.method19(var3) / 2.0F,
                  var13 + var2.method19(var3) / 2.0F + var2.method17(var3),
                  var2.method17(var3),
                  var2.method19(var3),
                  var15
               );
               var14++;
            }

            if (var8.contains(var11.method8() + 1 + var11.method9() * 10 + 10)) {
               var14++;
            }

            if (var14 == 3) {
               this.method3(
                  var1,
                  var12 + var2.method19(var3) / 2.0F + var2.method17(var3),
                  var13 + var2.method19(var3) / 2.0F + var2.method17(var3),
                  var2.method19(var3),
                  var2.method19(var3),
                  var15
               );
            }
         }
      }
   }

   private void method3(MixinHelper_4 var1, float var2, float var3, float var4, float var5, RewindhandlersExtension var6) {
      this.field2.method17(var1, var2, var3, var4, var5, var6);
      if (this.field3.method10() >= var2 && this.field3.method10() <= var2 + var4 && this.field3.method11() >= var3 && this.field3.method11() <= var3 + var5) {
         this.field1.field9 = this.field4;
      }
   }

   private Files6_2<Float, Float> method4(Holograms_9 var1, Holograms2_5 var2, Holograms4Iterator var3, float var4, float var5) {
      float var8 = var1.method35();
      float var19;
      float var20;
      if (var1.method49()) {
         var19 = 0.0F;
         var20 = 0.0F;
         int var9 = var3.method28().size();

         for (Nameplate4 var11 : var3.method28()) {
            var19 += var4 + var8 + var11.method12(var1) + var1.method17(var2) / 2.0F + var1.method19(var2) / 2.0F;
            var20 += var5 + var8 + var11.method13(var1) + var1.method17(var2) / 2.0F + var1.method19(var2) / 2.0F;
         }

         var19 /= var9;
         var20 /= var9;
         if (var9 == 3) {
            HashSet var22 = new HashSet();
            HashMap var24 = new HashMap();
            int var12 = 0;
            float var13 = 0.0F;

            for (Nameplate4 var15 : var3.method28()) {
               float var16 = var4 + var8 + var15.method12(var1) + var1.method17(var2) / 2.0F + var1.method19(var2) / 2.0F;
               float var17 = var5 + var8 + var15.method13(var1) + var1.method17(var2) / 2.0F + var1.method19(var2) / 2.0F;
               int var18 = var24.containsKey(var17) ? (Integer)var24.get(var17) + 1 : 1;
               var22.add(var16);
               var24.put(var17, var18);
               if (var18 > var12) {
                  var12 = var18;
                  var13 = var17;
               }
            }

            if (var22.size() == 2 && var24.size() == 2) {
               var20 = var13;
            }
         }
      } else {
         var19 = Float.POSITIVE_INFINITY;
         var20 = Float.POSITIVE_INFINITY;

         for (Nameplate4 var23 : var3.method28()) {
            float var25 = var4 + var8 + var23.method12(var1) + var1.method17(var2) / 2.0F + var1.method19(var2) / 2.0F;
            float var26 = var5 + var8 + var23.method13(var1) + var1.method17(var2) / 2.0F + var1.method19(var2) / 2.0F;
            if (!(var19 < var25) && (var19 != var25 || !(var26 > var20))) {
               var19 = var25;
               var20 = var26;
            }
         }
      }

      return Files6_2.method1(var19, var20);
   }

   private String[] method5(Holograms4Iterator var1, Gui2Extension2 var2) {
      String var3 = null;
      String var4 = null;
      String var5 = null;
      String var6 = var1.method11(true);
      if (var2 == Gui2Extension2.ROOM_NAME || var2 == Gui2Extension2.SECRETS_AND_NAME && var6 != null) {
         String[] var7 = (var6 == null ? "???" : var6).split(" ");
         if (var7.length > 1) {
            var4 = var7[var7.length - 1];
            var7[var7.length - 1] = "";
         }

         StringBuilder var8 = new StringBuilder();

         for (String var12 : var7) {
            var8.append(" ").append(var12);
         }

         var3 = var8.toString().trim();
      }

      if ((var2 == Gui2Extension2.SECRETS || var2 == Gui2Extension2.SECRETS_AND_NAME && var1.method14() != -1)
         && (
            var1.method14() != -1
               || var1.method30().method2() != com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.OPENED
         )
         && var1.method14() != 0) {
         var5 = var1.method14() == -1 ? "?/?" : var1.method30().method1() + "/" + var1.method14();
      }

      return new String[]{var3, var4, var5};
   }

   private boolean method6(Holograms4Iterator var1, Holograms_9 var2) {
      HologramsType5 var3 = var1.method30().method6();
      com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2 var4 = var1.method30().method2();
      if (var3 == HologramsType5.SPAWN || var3 == HologramsType5.FAIRY) {
         return true;
      } else {
         return var3 == HologramsType5.UNKNOWN && !var2.method22()
            ? true
            : var4 == com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.holograms.HologramsType2.OPENED
               && !var2.method26(var2.method25(var1));
      }
   }
}
