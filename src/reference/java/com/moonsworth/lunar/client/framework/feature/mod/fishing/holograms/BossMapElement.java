package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.DungeonPlayerTracker;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.math.MathUtils;
import com.moonsworth.lunar.ichor.VersionGate;

@VersionGate(min = 33)
public class BossMapElement extends MapElement {
   private long field7 = -1L;
   private BossMapFloor field8 = null;

   public BossMapElement(DungeonMapOverlay holograms3_21) {
      super("Boss", holograms3_21);
   }

   @Override
   public void method1(MixinHelper_4 mixinhelper_41, BettermapSettings holograms_92, DungeonStateTracker holograms2_53, float value4, float value5, MarkerModel<?> markers6) {
      BossMapFloor hologramstype_27 = this.method4(holograms_92, holograms2_53);
      if (hologramstype_27 != null) {
         DungeonPlayerTracker holograms4updater8 = holograms2_53.method29();
         float value9 = Math.min(hologramstype_27.getWidthInWorld(), hologramstype_27.getHeightInWorld());
         if (hologramstype_27.getRenderSize() != null) {
            value9 = Math.min(value9, hologramstype_27.getRenderSize());
         }

         float value10 = hologramstype_27.getRenderSize() == null ? hologramstype_27.getWidthInWorld() : hologramstype_27.getRenderSize();
         float value11 = (float)hologramstype_27.getTextureWidth() / hologramstype_27.getWidthInWorld() * value10;
         float value12 = (float)hologramstype_27.getTextureHeight() / hologramstype_27.getHeightInWorld() * value10;
         float value13 = Math.min(value11, value12);
         float value14 = holograms_92.method35();
         float value15 = (100.0F - 2.0F * value14) / value13;
         float value16 = (holograms4updater8.method8().method2() - hologramstype_27.getTopLeft().x()) / value9 * (100.0F - 2.0F * value14) - (100.0F - 2.0F * value14) / 2.0F;
         float value17 = (holograms4updater8.method8().method3() - hologramstype_27.getTopLeft().y()) / value9 * (100.0F - 2.0F * value14) - (100.0F - 2.0F * value14) / 2.0F;
         value16 = MathUtils.method12(value16, 0.0F, Math.max(0.0F, hologramstype_27.getTextureWidth() * value15 - (100.0F - 2.0F * value14)));
         value17 = MathUtils.method12(value17, 0.0F, Math.max(0.0F, hologramstype_27.getTextureHeight() * value15 - (100.0F - 2.0F * value14)));
         float value18 = (holograms4updater8.method8().method2() - hologramstype_27.getTopLeft().x()) / value9 * (100.0F - 2.0F * value14) - value16;
         float value19 = (holograms4updater8.method8().method3() - hologramstype_27.getTopLeft().y()) / value9 * (100.0F - 2.0F * value14) - value17;
         float value20 = value16;
         float value21 = value17;
         float value22 = value9;
         this.method5(
            mixinhelper_41,
            holograms_92,
            holograms2_53,
            value4,
            value5,
            markers6.method5(),
            value18,
            value19,
            (arg13x, arg14x, arg15x) -> {
               this.IHCCORIRRIIICCIOORCORRIHHROHOC
                  .method19(
                     mixinhelper_41,
                     hologramstype_27.getImage(),
                     arg13x + value14 - value20,
                     arg14x + value14 - value21,
                     hologramstype_27.getTextureWidth() * value15,
                     hologramstype_27.getTextureHeight() * value15,
                     0.0F,
                     0.0F,
                     hologramstype_27.getTextureWidth() * value15,
                     hologramstype_27.getTextureHeight() * value15
                  );
               double value16x = Double.POSITIVE_INFINITY;
               this.IHCCORIRRIIICCIOORCORRIHHROHOC.method28(null);
               if (holograms_92.method53()) {
                  for (DungeonPlayerTracker holograms4updater19x : holograms2_53.getPlayers()) {
                     if (!holograms4updater19x.method19()) {
                        float value20x = (holograms4updater19x.method8().method2() - hologramstype_27.getTopLeft().x()) / value22 * (100.0F - 2.0F * value14) - value20;
                        float value21x = (holograms4updater19x.method8().method3() - hologramstype_27.getTopLeft().y()) / value22 * (100.0F - 2.0F * value14) - value21;
                        double value22x = Math.pow(value20x + arg13x - arg15x.method10(), 2.0) + Math.pow(value21x + arg14x - arg15x.method11(), 2.0);
                        if (value22x < value16x) {
                           value16x = value22x;
                           this.IHCCORIRRIIICCIOORCORRIHHROHOC.method28(holograms4updater19x);
                        }
                     }
                  }
               }

               for (DungeonPlayerTracker holograms4updater26 : holograms2_53.getPlayers()) {
                  float value28 = (holograms4updater26.method8().method2() - hologramstype_27.getTopLeft().x()) / value22 * (100.0F - 2.0F * value14) - value20;
                  float value30 = (holograms4updater26.method8().method3() - hologramstype_27.getTopLeft().y()) / value22 * (100.0F - 2.0F * value14) - value21;
                  holograms4updater26.method10(
                     mixinhelper_41,
                     holograms_92,
                     value28 + arg13x,
                     value30 + arg14x,
                     this.IHCCORIRRIIICCIOORCORRIHHROHOC,
                     value18 > arg13x && value18 < arg13x + 100.0F && value19 > arg14x && value19 < arg14x + 100.0F
                  );
                  float value31 = (float)(Math.pow(value18 - arg15x.method10() + arg13x, 2.0) + Math.pow(value19 - arg15x.method11() + arg14x, 2.0));
                  if (value31 < holograms4updater8.method17()) {
                     this.IHCCORIRRIIICCIOORCORRIHHROHOC.method26(holograms4updater8.method1());
                  }
               }

               DungeonPlayerTracker holograms4updater25 = this.IHCCORIRRIIICCIOORCORRIHHROHOC.method27();
               if (holograms4updater25 != null) {
                  float value27 = (holograms4updater25.method8().method2() - hologramstype_27.getTopLeft().x()) / value22 * (100.0F - 2.0F * value14) - value20;
                  float value29 = (holograms4updater25.method8().method3() - hologramstype_27.getTopLeft().y()) / value22 * (100.0F - 2.0F * value14) - value21;
                  holograms4updater25.method11(
                     mixinhelper_41,
                     holograms_92,
                     value27 + arg13x,
                     value29 + arg14x,
                     this.IHCCORIRRIIICCIOORCORRIHHROHOC,
                     value18 > arg13x && value18 < arg13x + 100.0F && value19 > arg14x && value19 < arg14x + 100.0F,
                     true
                  );
               }
            }
         );
      }
   }

   @Override
   public void method2(MixinHelper_4 mixinhelper_41, BettermapSettings holograms_92, DungeonStateTracker holograms2_53, float value4, float value5, MarkerModel<?> markers6) {
   }

   @Override
   public boolean method3(BettermapSettings holograms_91, DungeonStateTracker holograms2_52) {
      return this.method4(holograms_91, holograms2_52) != null;
   }

   public BossMapFloor method4(BettermapSettings holograms_91, DungeonStateTracker holograms2_52) {
      long number3 = Ref.method3().bridge$getSystemTime();
      if (number3 - this.field7 < 100L) {
         return this.field8;
      }

      this.field7 = number3;
      DungeonPlayerTracker holograms4updater5 = holograms2_52.method29();
      if (holograms4updater5 == null) {
         this.field8 = null;
         return null;
      }

      for (BossMapFloor hologramstype_29 : BossMapFloor.values()) {
         if (hologramstype_29.getDungeonFloor() == holograms2_52.method34()
            && hologramstype_29.getTopLeftBound().x() <= holograms4updater5.method8().method2()
            && hologramstype_29.getTopLeftBound().y() <= holograms4updater5.method13()
            && hologramstype_29.getTopLeftBound().z() <= holograms4updater5.method8().method3()
            && hologramstype_29.getBottomRightBound().x() >= holograms4updater5.method8().method2()
            && hologramstype_29.getBottomRightBound().y() >= holograms4updater5.method13()
            && hologramstype_29.getBottomRightBound().z() >= holograms4updater5.method8().method3()) {
            this.field8 = hologramstype_29;
            return hologramstype_29;
         }
      }

      this.field8 = null;
      return null;
   }
}
