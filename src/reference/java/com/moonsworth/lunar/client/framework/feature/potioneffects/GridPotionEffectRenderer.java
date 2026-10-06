package com.moonsworth.lunar.client.framework.feature.potioneffects;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.potion.PotionEffectBridge;
import com.moonsworth.lunar.bridge.potion.PotionBridge;
import com.moonsworth.lunar.client.ui.hud.HudAlignment;
import com.moonsworth.lunar.client.mod.render.potioneffects.PotionEffects;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;

class GridPotionEffectRenderer extends Potioneffects3 {
   protected GridPotionEffectRenderer(PotionEffects potioneffects1) {
      super(potioneffects1);
   }

   @Override
   protected void method2(Potioneffects4 potioneffects41, MixinHelper_4 mixinhelper_42, List<PotionEffectBridge> list3, float value4, float value5) {
      float value6 = this.field3.field30.get() ? (Float)this.field3.field31.get() * 2.0F + 3.0F : 3.0F;
      value4 += Math.max(0.0F, value6 - 6.0F);
      value5 += Math.max(0.0F, value6 - 6.0F);
      boolean flag7 = (Boolean)this.field3.field9.get();
      boolean flag8 = flag7
         ? potioneffects41.method26().getVertical() == HudAlignment.BOTTOM
         : potioneffects41.method26().getHorizontal() == HudAlignment.RIGHT;
      int number9 = !this.field3.field28.get() && !this.field3.field30.get() ? 30 : 41;
      int number10 = (Integer)this.field3.field10.get();
      float value11 = number9 + value6;
      int number12 = list3.size();
      float value13 = flag8 && number12 > number10 ? number12 / number10 * value11 : 0.0F;
      this.method2(potioneffects41, flag7, number10, number12, value11, value6, number9);

      for (int index14 = 0; index14 < number12; index14++) {
         int number15 = index14 / number10;
         int number16 = index14 % number10;
         float value17 = number16 * value11;
         float value18 = value13 + (flag8 ? -number15 : number15) * value11;
         float value19 = value4 + (flag7 ? value17 : value18);
         float value20 = value5 + (flag7 ? value18 : value17);
         this.method3(potioneffects41, mixinhelper_42, (PotionEffectBridge)list3.get(index14), value19, value20, number9);
      }
   }

   private void method2(Potioneffects4 potioneffects41, boolean flag2, int number3, int number4, float value5, float value6, int number7) {
      int number8 = (number4 - 1) / number3 + 1;
      int number9 = Math.min(number4, number3);
      float value10 = number7 + (number9 - 1) * value5 + value6 - 3.0F;
      float value11 = number7 + (number8 - 1) * value5 + value6 - 3.0F;
      if (flag2) {
         potioneffects41.method16(value10, value11);
      } else {
         potioneffects41.method16(value11, value10);
      }
   }

   private void method3(Potioneffects4 potioneffects41, MixinHelper_4 mixinhelper_42, PotionEffectBridge fog3, float value4, float value5, int number6) {
      this.method3(potioneffects41, mixinhelper_42, value4, value5, number6, number6);
      PotionBridge fog27 = fog3.bridge$getPotion();
      if (this.method8(fog3)) {
         if (this.field3.method16()) {
            this.field3.method9(mixinhelper_42, fog3, value4, value5, number6, number6);
         }

         if ((Boolean)this.field3.field16.get()) {
            String text8 = this.method6(fog3);
            float value9 = Ref.method10().bridge$getStringWidth(text8);
            this.method3(
               mixinhelper_42,
               fog3,
               fog27,
               text8,
               value4 + number6 / 2.0F - value9 / 2.0F,
               value5 + number6 - number6 / 3.0F + 1.0F,
               this.field3.field37,
               (Boolean)this.field3.field35.get()
            );
         }
      }

      this.method3(mixinhelper_42, fog3, fog27, value4 + number6 / 2.0F - 9.5F, value5 + number6 / 2.0F - 13.0F);
   }
}
