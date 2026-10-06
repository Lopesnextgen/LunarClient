package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.AnimatedValue;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.DungeonPlayerTracker;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.HologramTextRenderer;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.LineBatchRenderer;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate.TextRenderHelper;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data4;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.math.MathUtils;
import com.moonsworth.lunar.client.render.color.RewindhandlersExtension;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import net.kyori.adventure.text.Component;

@VersionGate(min = 33)
public class DungeonMapOverlay {
   private static final RewindhandlersExtension field1 = RewindhandlersExtension.method23(-16711936);
   private static final RewindhandlersExtension field2 = RewindhandlersExtension.method23(-12770018);
   private final ArrayList<MapElement> field3;
   private final BossMapElement field4;
   private final DungeonScorePanel field5;
   private final AnimatedValue field6 = new AnimatedValue(0.0, AnimatedValue.Type.SIN_OUT);
   private int field7 = 0;
   public float field8 = 1.0F;
   private long field9 = 0L;
   private List<Component> field10 = null;
   private long field11 = 0L;
   private long field12 = 0L;
   private BettermapSettings field13 = null;
   private DungeonPlayerTracker field14 = null;
   private float field15;
   private float field16;
   private float field17;
   private float field18;

   public DungeonMapOverlay(DungeonStats holograms_61) {
      this.field3 = new ArrayList<>();
      this.field3.add(new DungeonMapElement(this));
      this.field3.add(this.field4 = new BossMapElement(this));
      this.field3.add(new ScoreMapElement(this));
      this.field5 = new DungeonScorePanel(this, holograms_61);
   }

   public void method1(MixinHelper_4 mixinhelper_41, BettermapSettings holograms_92, DungeonStateTracker holograms2_53, float value4, float value5, float value6, MarkerModel<?> markers7) {
      this.method5(holograms_92, holograms2_53);
      if (holograms_92.method41() || !this.method25()) {
         mixinhelper_41.method44(arg0 -> {
            arg0.method29().method33();
            arg0.method29().method11();
         });
         this.field8 = value6;
         value4 /= value6;
         value5 /= value6;
         markers7 = markers7.method17(value6);
         this.field10 = null;
         if (Ref.method3().bridge$getGuiIngame().bridge$getChatGUI().bridge$getChatOpen()) {
            this.field6.animateTo(15.0, 250L);
         } else {
            this.field6.animateTo(0.0, 250L);
            if (!this.field6.isAnimating() && this.field11 != 0L) {
               this.field11 = 0L;
               this.field12 = 0L;
               holograms2_53.method36(0L);
            }
         }

         LineBatchRenderer.method5(mixinhelper_41);
         this.method6(mixinhelper_41, holograms_92, holograms2_53, value4, value5, markers7);
         LineBatchRenderer.method6(mixinhelper_41);
         this.method8(mixinhelper_41, holograms_92, holograms2_53, value4, value5, markers7);
         float value8 = holograms_92.method35();
         this.method13(mixinhelper_41, holograms_92, value4 + value8, value5 + value8, 100.0F - value8 * 2.0F, 100.0F - value8 * 2.0F);
         this.field3.get(this.field7).method1(mixinhelper_41, holograms_92, holograms2_53, value4, value5, markers7);
         this.method14(mixinhelper_41, holograms_92);
         this.method4(mixinhelper_41, holograms_92, holograms2_53, value4, value5, markers7);
         this.method2(mixinhelper_41, holograms_92, holograms2_53, value4, value5, markers7);
         if (holograms_92.method32() != RoomInfoPosition.OFF && holograms2_53.method29().method7() != null) {
            List list9 = holograms2_53.method29().method7().method1();
            float value10 = holograms_92.method43();
            mixinhelper_41.push();
            if (holograms_92.method32() == RoomInfoPosition.RIGHT) {
               mixinhelper_41.method38((value4 + 100.0F) * this.field8, value5 * this.field8, 0.0F);
            } else {
               float value11 = 0.0F;

               for (Component component13 : list9) {
                  float value14 = Ref.method10().bridge$getStringWidth(component13);
                  if (value14 > value11) {
                     value11 = value14;
                  }
               }

               mixinhelper_41.method38(value4 * this.field8 - 8.0F - value11 * value10, value5 * this.field8, 0.0F);
            }

            mixinhelper_41.scale(value10, value10, 0.0F);
            TextRenderHelper.method4(mixinhelper_41, 0.0F, 0.0F, list9);
            mixinhelper_41.pop();
         }

         if (this.field10 != null && !holograms_92.method53()) {
            float value18 = 0.0F;

            for (Component component20 : this.field10) {
               float value21 = Ref.method10().bridge$getStringWidth(component20);
               if (value21 > value18) {
                  value18 = value21;
               }
            }

            if (markers7.method10() * this.field8 + value18 + 12.0 > Ref.method3().bridge$getCurrentScreen().bridge$getWidth()) {
               TextRenderHelper.method3(mixinhelper_41, markers7.method12() * this.field8 - value18 - 24.0F, markers7.method13() * this.field8, this.field10);
            } else {
               TextRenderHelper.method3(mixinhelper_41, markers7.method12() * this.field8, markers7.method13() * this.field8, this.field10);
            }
         }
      }
   }

   private void method2(MixinHelper_4 mixinhelper_41, BettermapSettings holograms_92, DungeonStateTracker holograms2_53, float value4, float value5, MarkerModel<?> markers6) {
      float value7 = (float)this.field6.getValue();
      if (value7 != 0.0F) {
         byte number8 = 100;
         if (holograms_92.method31() != MapScoreStyle.NONE) {
            number8 += 15;
         }

         long number9 = Ref.method3().bridge$getSystemTime();
         if (this.field13 == holograms_92) {
            if (Bridge.method20().method1(0)) {
               double value11 = MathUtils.method12((markers6.method12() - 11.5F - value4) / 77.0F, 0.0F, 1.0F);
               if (value11 == 1.0) {
                  this.field11 = 0L;
                  this.field12 = 0L;
               } else {
                  this.field11 = (long)(holograms2_53.method47() - value11 * (holograms2_53.method47() - number9));
                  this.field12 = number9;
               }

               holograms2_53.method36(this.field11);
            } else {
               this.field13 = null;
            }
         }

         if (this.field12 != 0L) {
            holograms2_53.method36(this.field11 + number9 - this.field12);
         }

         double value13 = (holograms2_53.method47() - (this.field11 + number9 - this.field12)) / (holograms2_53.method47() - number9);
         if (this.field11 == 0L) {
            value13 = 1.0;
         }

         if (!(value7 - holograms_92.method35() < 0.0F)) {
            this.method13(mixinhelper_41, holograms_92, value4, value5 + number8, 100.0F, value7 - holograms_92.method35());
            LineBatchRenderer.method5(mixinhelper_41);
            this.method17(mixinhelper_41, value4 + 10.0F, value5 + 4.0F + number8, 80.0F, 3.0F, field2);
            this.method17(mixinhelper_41, (float)(value4 + 10.0F + 77.0 * value13), value5 + number8, 3.0F, 10.0F, field1);
            LineBatchRenderer.method6(mixinhelper_41);
            this.method14(mixinhelper_41, holograms_92);
         }
      }
   }

   private void method3(BettermapSettings holograms_91, float value2, float value3, Data4 data44) {
      float value5 = (float)this.field6.getValue();
      if (value5 != 0.0F) {
         byte number6 = 100;
         if (holograms_91.method31() != MapScoreStyle.NONE) {
            number6 += 15;
         }

         if (data44.IIRCROICCRROCOCOIOIHHOCRHOIHIR() >= value2
            && data44.IIRCROICCRROCOCOIOIHHOCRHOIHIR() <= value2 + 100.0F
            && data44.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() >= value3 + number6
            && data44.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() <= value3 + number6 + 10.0F
            && data44.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() <= value3 + number6 + value5 - holograms_91.method35()) {
            this.field13 = holograms_91;
         }
      }
   }

   private void method4(MixinHelper_4 mixinhelper_41, BettermapSettings holograms_92, DungeonStateTracker holograms2_53, float value4, float value5, MarkerModel<?> markers6) {
      if (holograms_92.method31() == MapScoreStyle.SIMPLIFIED) {
         this.field5.method1(mixinhelper_41, holograms_92, holograms2_53, value4, value5 + 100.0F, markers6);
      }

      if (holograms_92.method31() == MapScoreStyle.LEGAL_MAP) {
         this.field5.method2(mixinhelper_41, holograms_92, holograms2_53, value4, value5 + 100.0F, markers6);
      }
   }

   private void method5(BettermapSettings holograms_91, DungeonStateTracker holograms2_52) {
      long number3 = Ref.method3().bridge$getSystemTime();
      if (number3 - this.field9 > 1000L) {
         for (MapElement holograms6_36 : this.field3) {
            holograms6_36.method7(holograms_91, holograms2_52);
         }

         this.field9 = number3;
      }
   }

   private void method6(MixinHelper_4 mixinhelper_41, BettermapSettings holograms_92, DungeonStateTracker holograms2_53, float value4, float value5, MarkerModel<?> markers6) {
      int number7 = 100 + (int)this.field6.getValue();
      if (holograms_92.method31() != MapScoreStyle.NONE) {
         number7 += 15;
      }

      float value8 = holograms_92.method35();
      this.method17(mixinhelper_41, value4 + value8, value5 + value8, 100.0F - value8 * 2.0F, number7 - value8 * 2.0F, holograms_92.method29());
      this.field3.get(this.field7).method2(mixinhelper_41, holograms_92, holograms2_53, value4, value5, markers6);
      this.method17(mixinhelper_41, value4, value5 + value8, value8, number7 - value8, holograms_92.method30());
      this.method17(mixinhelper_41, value4 + 100.0F - value8, value5 + value8, value8, number7 - value8, holograms_92.method30());
      this.method7(mixinhelper_41, holograms_92, holograms2_53, value4, value5, markers6);
      this.method17(mixinhelper_41, value4 + value8, value5 + number7 - value8, 100.0F - value8 * 2.0F, value8, holograms_92.method30());
   }

   private void method7(MixinHelper_4 mixinhelper_41, BettermapSettings holograms_92, DungeonStateTracker holograms2_53, float value4, float value5, MarkerModel<?> markers6) {
      float value7 = holograms_92.method35();
      if (!holograms_92.method28()) {
         this.method17(mixinhelper_41, value4, value5, 100.0F, value7, holograms_92.method30());
      } else {
         float value8 = 0.0F;

         for (MapElement holograms6_310 : this.field3) {
            value8 += holograms6_310.method6();
         }

         float value17 = (100.0F - value8) / (this.field3.size() - 1);
         float value18 = 0.0F;
         int index11 = 0;

         for (MapElement holograms6_313 : this.field3) {
            boolean flag14 = this.field7 == index11;
            if (value18 > 0.0F) {
               this.method17(mixinhelper_41, value4 + value18, value5, value17, value7, holograms_92.method30());
               value18 += value17;
            }

            float value15 = holograms6_313.method6();
            float value16 = holograms6_313.method5();
            this.method17(mixinhelper_41, value4 + value18 + value7, value5 - value16 + value7, value15 - value7 * 2.0F, value16 - (flag14 ? 0.0F : value7), holograms_92.method29());
            this.method17(mixinhelper_41, value4 + value18, value5 - value16 + value7, value7, value16, holograms_92.method30());
            this.method17(mixinhelper_41, value4 + value18, value5 - value16, value15, value7, holograms_92.method30());
            if (!flag14) {
               this.method17(mixinhelper_41, value4 + value18 + value7, value5, value15 - value7 * 2.0F, value7, holograms_92.method30());
            }

            value18 += value15;
            this.method17(mixinhelper_41, value4 + value18 - value7, value5 - value16 + value7, value7, value16, holograms_92.method30());
            index11++;
         }

         this.method17(mixinhelper_41, value4 + value18, value5, 100.0F - value18, value7, holograms_92.method30());
      }
   }

   private void method8(MixinHelper_4 mixinhelper_41, BettermapSettings holograms_92, DungeonStateTracker holograms2_53, float value4, float value5, MarkerModel<?> markers6) {
      if (holograms_92.method28()) {
         float value7 = 0.0F;

         for (MapElement holograms6_39 : this.field3) {
            value7 += holograms6_39.method6();
         }

         float value18 = (100.0F - value7) / (this.field3.size() - 1);
         float value19 = 0.0F;
         int index10 = 0;

         for (MapElement holograms6_312 : this.field3) {
            boolean flag13 = this.field7 == index10;
            if (value19 > 0.0F) {
               value19 += value18;
            }

            float value14 = holograms6_312.method6();
            float value15 = holograms6_312.method5();
            boolean flag16 = markers6.method12() > value4 + value19
               && markers6.method12() < value4 + value19 + value14
               && markers6.method13() > value5 - value15
               && markers6.method13() < value5;
            float value17 = holograms_92.method35();
            this.method13(mixinhelper_41, holograms_92, value4 + value19 + value17, value5 - value15 + value17, value14 - value17 * 2.0F, value15);
            this.method11(
               mixinhelper_41,
               (!flag13 && !flag16 ? ChatFormatting.GRAY : "") + holograms6_312.getName(),
               value4 + value19 + value14 / 2.0F,
               value5 + value17 - 5.0F,
               0.65F,
               HologramTextRenderer.Type.BORDER
            );
            this.method14(mixinhelper_41, holograms_92);
            value19 += value14;
            index10++;
         }
      }
   }

   public void method9(BettermapSettings holograms_91, float value2, float value3, float value4, Data4 data45, int number6) {
      if (number6 == 0) {
         value2 /= value4;
         value3 /= value4;
         data45 = (Data4)data45.IHCORIOHOHHOIORHCCOOIIIHOCROOI(value4);
         this.method10(value2, value3, data45);
         this.method3(holograms_91, value2, value3, data45);
      }
   }

   private void method10(float value1, float value2, Data4 data43) {
      float value4 = 0.0F;

      for (MapElement holograms6_36 : this.field3) {
         value4 += holograms6_36.method6();
      }

      float value13 = (100.0F - value4) / (this.field3.size() - 1);
      float value14 = 0.0F;
      int index7 = 0;

      for (MapElement holograms6_39 : this.field3) {
         if (value14 > 0.0F) {
            value14 += value13;
         }

         float value10 = holograms6_39.method6();
         float value11 = holograms6_39.method5();
         boolean flag12 = data43.IIRCROICCRROCOCOIOIHHOCRHOIHIR() > value1 + value14
            && data43.IIRCROICCRROCOCOIOIHHOCRHOIHIR() < value1 + value14 + value10
            && data43.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() > value2 - value11
            && data43.CRCOHORRCCORCCIIOOIOOCIRRCRHHH() < value2;
         if (flag12) {
            this.field7 = index7;
         }

         value14 += value10;
         index7++;
      }
   }

   public void method11(MixinHelper_4 mixinhelper_41, String text2, float value3, float value4, float value5, HologramTextRenderer.Type type6) {
      float value7 = Ref.method10().bridge$getStringWidth(text2) * value5;
      this.method15(mixinhelper_41, text2, value3 - value7 / 2.0F, value4 - 5.0F * value5, value5, type6);
   }

   public void method12(MixinHelper_4 mixinhelper_41, Component component2, float value3, float value4, float value5, HologramTextRenderer.Type type6) {
      float value7 = Ref.method10().bridge$getStringWidth(component2) * value5;
      this.method16(mixinhelper_41, component2, value3 - value7 / 2.0F, value4 - 5.0F * value5, value5, type6);
   }

   public void method13(MixinHelper_4 mixinhelper_41, BettermapSettings holograms_92, float value3, float value4, float value5, float value6) {
      if (!holograms_92.method53()) {
         LcuiScreen.method111(mixinhelper_41, value3 * this.field8, value4 * this.field8, value5 * this.field8, value6 * this.field8, 1.0F);
      }
   }

   public void method14(MixinHelper_4 mixinhelper_41, BettermapSettings holograms_92) {
      if (!holograms_92.method53()) {
         LcuiScreen.method112(mixinhelper_41);
      }
   }

   public void method15(MixinHelper_4 mixinhelper_41, String text2, float value3, float value4, float value5, HologramTextRenderer.Type type6) {
      HologramTextRenderer.method1(mixinhelper_41, text2, value3 * this.field8, value4 * this.field8, value5 * this.field8, type6);
   }

   public void method16(MixinHelper_4 mixinhelper_41, Component component2, float value3, float value4, float value5, HologramTextRenderer.Type type6) {
      HologramTextRenderer.method2(mixinhelper_41, component2, value3 * this.field8, value4 * this.field8, value5 * this.field8, type6);
   }

   public void method17(MixinHelper_4 mixinhelper_41, float value2, float value3, float value4, float value5, RewindhandlersExtension rewindhandlersextension6) {
      LineBatchRenderer.method1(value2 * this.field8, value3 * this.field8, value4 * this.field8, value5 * this.field8, rewindhandlersextension6);
   }

   public void method18(
      MixinHelper_4 mixinhelper_41, ResourceLocationBridge horsestats142, float value3, float value4, float value5, float value6, float value7, float value8, float value9, float value10, int number11
   ) {
      value3 *= this.field8;
      value4 *= this.field8;
      value5 *= this.field8;
      value6 *= this.field8;
      value9 *= this.field8;
      value10 *= this.field8;
      LcuiScreen.method46(mixinhelper_41, horsestats142, value3, value4, value7, value8, value5, value6, value9, value10, number11);
   }

   public void method19(MixinHelper_4 mixinhelper_41, ResourceLocationBridge horsestats142, float value3, float value4, float value5, float value6, float value7, float value8, float value9, float value10) {
      this.method18(mixinhelper_41, horsestats142, value3, value4, value5, value6, value7, value8, value9, value10, -1);
   }

   public void method20(MixinHelper_4 mixinhelper_41, MapTickIcon hologramstype22, float value3, float value4, float value5, float value6, float value7, int number8) {
      mixinhelper_41.push();
      mixinhelper_41.method38(value3 * this.field8, value4 * this.field8, 0.0F);
      mixinhelper_41.method42(value7);
      this.method18(mixinhelper_41, hologramstype22.getImage(), -value5 / 2.0F, -value6 / 2.0F, value5, value6, 0.0F, 0.0F, value5, value6, number8);
      mixinhelper_41.pop();
   }

   public void method21(MixinHelper_4 mixinhelper_41, MapTickIcon hologramstype22, float value3, float value4, float value5, float value6, float value7) {
      this.method20(mixinhelper_41, hologramstype22, value3, value4, value5, value6, value7, -1);
   }

   public void method22(MapElement holograms6_31) {
      this.field7 = this.field3.indexOf(holograms6_31);
   }

   public void method23() {
      if (!this.field3.get(this.field7).method4()) {
         int index1 = 0;
         int number2 = 0;

         for (MapElement holograms6_34 : this.field3) {
            if (holograms6_34.method4()) {
               number2 = index1;
            }

            index1++;
         }

         this.field7 = number2;
      }
   }

   public boolean method24() {
      return this.field11 != 0L;
   }

   public boolean method25() {
      return this.field4.CICOHOIHOOOOIROCOICOIIICOCHHCC();
   }

   @Generated
   public void method26(List<Component> list1) {
      this.field10 = list1;
   }

   @Generated
   public DungeonPlayerTracker method27() {
      return this.field14;
   }

   @Generated
   public void method28(DungeonPlayerTracker holograms4updater1) {
      this.field14 = holograms4updater1;
   }
}
