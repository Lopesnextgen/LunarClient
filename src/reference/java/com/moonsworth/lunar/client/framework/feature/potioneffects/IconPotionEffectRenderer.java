package com.moonsworth.lunar.client.framework.feature.potioneffects;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.potion.PotionEffectBridge;
import com.moonsworth.lunar.bridge.potion.PotionBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.gui.notification.NotificationAnchor;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.mod.render.potioneffects.PotionEffects;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.ui.GuiResolution;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.ArrayList;
import java.util.List;

class IconPotionEffectRenderer extends Potioneffects3 {
   @VersionGate(min = 19)
   private static final ResourceLocationBridge field5 = ResourceLocationBridge.create("textures/gui/sprites/hud/effect_background.png");
   @VersionGate(min = 19)
   private static final ResourceLocationBridge field6 = ResourceLocationBridge.create("textures/gui/sprites/hud/effect_background_ambient.png");
   private static final int field7 = 24;
   private static final int field8 = 27;
   private static final int field9 = 25;
   private static final int field10 = 26;
   private static final int field11 = 2;
   private final List<PotionEffectBridge> field12 = new ArrayList<>();
   private final List<PotionEffectBridge> field13 = new ArrayList<>();

   protected IconPotionEffectRenderer(PotionEffects potioneffects1) {
      super(potioneffects1);
   }

   @Override
   protected void method1(List<PotionEffectBridge> list1) {
      super.method1(list1);
      this.field12.clear();
      this.field13.clear();
      boolean flag2 = (Boolean)this.field3.field11.get();

      for (int index3 = 0; index3 < list1.size(); index3++) {
         PotionEffectBridge fog4 = (PotionEffectBridge)list1.get(index3);
         if (fog4.bridge$shouldShowIcon()) {
            PotionBridge fog25 = fog4.bridge$getPotion();
            boolean flag6 = flag2 && fog25 != null && fog25.bridge$isBadEffect();
            (flag6 ? this.field13 : this.field12).add(fog4);
         }
      }

      this.field12.sort(this::method13);
      this.field13.sort(this::method13);
   }

   @Override
   protected void method2(Potioneffects4 potioneffects41, MixinHelper_4 mixinhelper_42, List<PotionEffectBridge> list3, float value4, float value5) {
      if (this.field12.isEmpty() && this.field13.isEmpty()) {
         potioneffects41.method16(0.0F, 0.0F);
      } else {
         boolean flag6 = (Boolean)this.field3.field12.get();
         int number7 = (Integer)this.field3.field13.get();
         int number8 = this.method9(this.field12.size(), number7);
         int number9 = this.method9(this.field13.size(), number7);
         int number10 = Math.max(Math.min(this.field12.size(), number7), Math.min(this.field13.size(), number7));
         float value11 = this.method7(number10);
         float value12 = this.method8(number8 + number9);
         float value13 = flag6 ? value11 : value12;
         float value14 = flag6 ? value12 : value11;
         potioneffects41.method16(value13, value14);
         this.method13(potioneffects41, mixinhelper_42, value4, value5, value13, value14);
         this.method3(potioneffects41, mixinhelper_42, this.field12, flag6, number7, 0, value4, value5, value11);
         this.method3(potioneffects41, mixinhelper_42, this.field13, flag6, number7, number8, value4, value5, value11);
      }
   }

   private void method3(Potioneffects4 potioneffects41, MixinHelper_4 mixinhelper_42, List<PotionEffectBridge> list3, boolean flag4, int number5, int number6, float value7, float value8, float value9) {
      for (int index10 = 0; index10 < list3.size(); index10++) {
         int number11 = index10 / number5;
         int number12 = index10 % number5;
         int number13 = Math.min(list3.size() - number11 * number5, number5);
         float value14 = this.method4(potioneffects41, flag4, number12, number13, value9);
         float value15 = (number6 + number11) * this.method6();
         this.method10(potioneffects41, mixinhelper_42, (PotionEffectBridge)list3.get(index10), value7 + (flag4 ? value14 : value15), value8 + (flag4 ? value15 : value14));
      }
   }

   private float method4(Potioneffects4 potioneffects41, boolean flag2, int number3, int number4, float value5) {
      float value6 = this.method7(number4);
      if (flag2) {
         float value8 = switch (potioneffects41.method26().getHorizontal()) {
            case LEFT -> value6;
            case MIDDLE -> (value5 + value6) / 2.0F;
            default -> value5;
         };
         return value8 - 24.0F - number3 * this.method5();
      } else {
         float value7 = switch (potioneffects41.method26().getVertical()) {
            case MIDDLE -> (value5 - value6) / 2.0F;
            case TOP -> 0.0F;
            default -> value5 - value6;
         };
         return value7 + number3 * this.method5();
      }
   }

   private int method5() {
      return this.field3.field14.get() ? 25 : 27;
   }

   private int method6() {
      return this.field3.field14.get() ? 26 : 27;
   }

   private float method7(int number1) {
      return number1 * this.method5() - (this.method5() - 24);
   }

   private float method8(int number1) {
      return number1 * this.method6() - (this.method6() - 24);
   }

   private int method9(int number1, int number2) {
      return (number1 + number2 - 1) / number2;
   }

   private void method10(Potioneffects4 potioneffects41, MixinHelper_4 mixinhelper_42, PotionEffectBridge fog3, float value4, float value5) {
      boolean flag6 = fog3.bridge$getIsAmbient();
      if ((Boolean)this.field3.field49.get()) {
         if (Ref.MC_VERSION >= 19) {
            LcuiScreen.method31(mixinhelper_42, flag6 ? field6 : field5, value4, value5, 24.0F, 24.0F, -1);
         } else {
            LcuiScreen.method47(
               mixinhelper_42,
               Ref.MC_VERSION >= 5 ? HRHOCCCOHCOCOOHHROHRHIOCCCHCIC : IHORICCICIICOCCRHCROOOCCICOIRH,
               value4,
               value5,
               flag6 ? 165 : 141,
               166,
               24,
               24,
               -1
            );
         }
      }

      PotionBridge fog27 = fog3.bridge$getPotion();
      float value8 = (Float)this.field3.field52.get();
      mixinhelper_42.push();
      mixinhelper_42.method38(value4 + 12.0F - 9.0F * value8, value5 + 12.0F - 9.0F * value8, 0.0F);
      mixinhelper_42.scale(value8, value8, 1.0F);
      this.method13(mixinhelper_42, fog3, fog27, 0.0F, 0.0F);
      mixinhelper_42.pop();
      if (this.method8(fog3)) {
         if (this.field3.method16()) {
            this.field3.method9(mixinhelper_42, fog3, value4 + 2.0F, value5 + 2.0F, 20.0F, 20.0F);
         }

         if ((Boolean)this.field3.field16.get()) {
            boolean flag9 = !flag6 && this.method9(fog3);
            this.method11(
               mixinhelper_42,
               fog3,
               fog27,
               this.method6(fog3),
               (NotificationAnchor)this.field3.field50.get(),
               value4,
               value5,
               flag9 ? this.field3.field55 : this.field3.field37,
               !flag9 && (Boolean)this.field3.field35.get()
            );
         }
      }

      if ((Boolean)this.field3.field17.get()) {
         String text10 = this.method7(fog3);
         if (!text10.isEmpty()) {
            this.method11(
               mixinhelper_42,
               fog3,
               fog27,
               text10,
               (NotificationAnchor)this.field3.field51.get(),
               value4,
               value5,
               this.field3.field37,
               (Boolean)this.field3.field35.get()
            );
         }
      }
   }

   private void method11(
      MixinHelper_4 mixinhelper_41, PotionEffectBridge fog2, PotionBridge fog23, String text4, NotificationAnchor gui2extension5, float value6, float value7, ColorOption lightingextension42228, boolean flag9
   ) {
      float value10 = this.method12();
      float value11 = (Float)this.field3.field53.get() * 0.5F;
      if (value10 > 0.0F) {
         value11 = Math.max(1, Math.round(value11 * value10)) / value10;
      }

      float value12 = Ref.method10().bridge$getStringWidth(text4) * value11;
      float value13 = Ref.method10().method19() * value11;
      boolean flag14 = gui2extension5 == NotificationAnchor.TOP_RIGHT || gui2extension5 == NotificationAnchor.BOTTOM_RIGHT;
      boolean flag15 = gui2extension5 == NotificationAnchor.BOTTOM_LEFT || gui2extension5 == NotificationAnchor.BOTTOM_RIGHT;
      float value16 = value6 + (flag14 ? 21.0F - value12 : 3.25F);
      float value17 = value7 + (flag15 ? 22.0F - value13 : 3.0F);
      mixinhelper_41.push();
      mixinhelper_41.method38(value10 > 0.0F ? LcuiScreen.method137(value16, value10) : value16, value10 > 0.0F ? LcuiScreen.method137(value17, value10) : value17, 0.0F);
      mixinhelper_41.scale(value11, value11, 1.0F);
      this.method13(mixinhelper_41, fog2, fog23, text4, 0.0F, 0.0F, lightingextension42228, flag9);
      mixinhelper_41.pop();
   }

   private float method12() {
      GuiResolution threadmoduledump711 = LcuiScreen.method151();
      return this.field3.method17() && threadmoduledump711 != null ? threadmoduledump711.method3() : 0.0F;
   }

   private int method13(PotionEffectBridge fog1, PotionEffectBridge fog2) {
      boolean flag3 = fog1.bridge$getIsAmbient();
      boolean flag4 = fog2.bridge$getIsAmbient();
      int number5 = (int)fog1.bridge$getDuration();
      int number6 = (int)fog2.bridge$getDuration();
      if ((number5 <= 32147 || number6 <= 32147) && (!flag3 || !flag4)) {
         int number8 = Boolean.compare(flag4, flag3);
         if (number8 == 0) {
            number8 = Boolean.compare(number6 < 0, number5 < 0);
         }

         if (number8 == 0) {
            number8 = Integer.compare(number6, number5);
         }

         if (number8 == 0) {
            number8 = Integer.compare(this.method14(fog2), this.method14(fog1));
         }

         return number8;
      } else {
         int number7 = Boolean.compare(flag4, flag3);
         return number7 != 0 ? number7 : Integer.compare(this.method14(fog2), this.method14(fog1));
      }
   }

   private int method14(PotionEffectBridge fog1) {
      if (Ref.MC_VERSION >= 6) {
         return fog1.bridge$getColor();
      }

      Integer number2 = Potioneffects.get(fog1.bridge$getPotionID());
      return number2 != null ? number2 & 16777215 : 0;
   }
}
