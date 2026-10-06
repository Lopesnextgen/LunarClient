package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.rewindhandlers.Rewindhandlers;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.render.color.RewindhandlersExtension;
import com.moonsworth.lunar.ichor.Annotation2;

@Annotation2(min = 33)
public class Holograms2_6 {
   private final Holograms3_2 field1;

   public Holograms2_6(Holograms3_2 var1) {
      this.field1 = var1;
   }

   public void method1(MixinHelper_4 var1, Holograms_9 holograms_9, Holograms2_5 holograms2_5, Rewindhandlers rewindhandlers, float value, float value2, MarkerModel<?> markers) {
      if (rewindhandlers.method8() != null) {
         RewindhandlersExtension var8 = holograms_9.method20(rewindhandlers.method8(), true);
         boolean var9 = rewindhandlers.method6().method9() == rewindhandlers.method7().method9();
         float var10 = holograms_9.method35();
         float var11 = rewindhandlers.method6().method12(holograms_9);
         float var12 = rewindhandlers.method6().method13(holograms_9);
         float var13 = holograms_9.method17(holograms2_5);
         float var14;
         float var15;
         float var16;
         float var17;
         if (var9) {
            var16 = holograms_9.method19(holograms2_5);
            var17 = holograms_9.method18(holograms2_5);
            var14 = value + var10 + var11 + var13;
            var15 = value2 + var10 + var12 + var13 / 2.0F - var17 / 2.0F;
         } else {
            var16 = holograms_9.method18(holograms2_5);
            var17 = holograms_9.method19(holograms2_5);
            var14 = value + var10 + var11 + var13 / 2.0F - var16 / 2.0F;
            var15 = value2 + var10 + var12 + var13;
         }

         float var18 = holograms_9.method19(holograms2_5);
         this.field1.method17(var1, var14 + var18 / 2.0F, var15 + var18 / 2.0F, var16, var17, var8);
      }
   }
}
