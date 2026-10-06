package com.moonsworth.lunar.client.framework.feature.potioneffects;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.potion.PotionEffectBridge;
import com.moonsworth.lunar.bridge.potion.PotionBridge;
import com.moonsworth.lunar.client.ui.hud.HudAlignment;
import com.moonsworth.lunar.client.mod.render.potioneffects.PotionEffects;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.List;
import java.util.Locale;

class CompactPotionEffectRenderer extends Potioneffects3 {
   protected CompactPotionEffectRenderer(PotionEffects potioneffects1) {
      super(potioneffects1);
   }

   @Override
   protected void method2(Potioneffects4 potioneffects41, MixinHelper_4 mixinhelper_42, List<PotionEffectBridge> list3, float value4, float value5) {
      this.method2(potioneffects41, mixinhelper_42, value4, value5, potioneffects41.getWidth(), potioneffects41.getHeight());
      float value6 = value4;
      float value7 = value5;
      HudAlignment gui2extension8 = potioneffects41.method26().getHorizontal();
      if ((Boolean)this.field3.field24.get()) {
         if (gui2extension8 == HudAlignment.LEFT) {
            gui2extension8 = HudAlignment.RIGHT;
         } else if (gui2extension8 == HudAlignment.RIGHT) {
            gui2extension8 = HudAlignment.LEFT;
         }
      }

      if (gui2extension8 != HudAlignment.MIDDLE) {
         value4 += gui2extension8 == HudAlignment.RIGHT ? -2.0F : 3.0F;
      }

      value5 += 3.0F;
      float value9 = 0.0F;
      float value10 = 0.0F;

      for (int index11 = 0; index11 < list3.size(); index11++) {
         PotionEffectBridge fog12 = (PotionEffectBridge)list3.get(index11);
         PotionBridge fog213 = fog12.bridge$getPotion();
         if (this.field3.method16() && this.method8(fog12)) {
            this.field3.method9(mixinhelper_42, fog12, value6, value7 + value10, potioneffects41.getWidth(), 22.0F);
         }

         float value14 = 0.0F;
         boolean flag15 = (Boolean)this.field3.field15.get();
         boolean flag16 = (Boolean)this.field3.field17.get();
         if (flag15 || flag16) {
            StringBuilder builder17 = new StringBuilder();
            if (flag15) {
               String text18 = Bridge.method36().method11(fog12.bridge$getEffectName());
               builder17.append(this.field3.field23.get() ? text18.toUpperCase(Locale.ROOT) : text18);
               if (flag16) {
                  builder17.append(" ");
               }
            }

            if (flag16) {
               builder17.append(this.method7(fog12));
            }

            String text23 = builder17.toString();
            value14 = Ref.method10().bridge$getStringWidth(text23) + 20.0F;
            float value19 = value4 + this.method2(potioneffects41, gui2extension8, value14);
            float value20 = value5 + value10;
            this.method2(
               mixinhelper_42, fog12, fog213, text23, value19, value20, this.field3.field36, (Boolean)this.field3.field34.get()
            );
            value9 = Math.max(value9, value14);
         }

         if ((Boolean)this.field3.field16.get()) {
            String text22 = this.method6(fog12);
            float value24 = Ref.method10().bridge$getStringWidth(text22) + 20.0F;
            if ((Boolean)this.field3.field16.get() && this.method8(fog12)) {
               this.method2(
                  mixinhelper_42,
                  fog12,
                  fog213,
                  text22,
                  value4 + this.method2(potioneffects41, gui2extension8, value24),
                  value5 + value10 + (!flag15 && !flag16 ? 5 : 10),
                  this.field3.field37,
                  (Boolean)this.field3.field35.get()
               );
            }

            value9 = Math.max(value9, value24);
         }

         this.method2(mixinhelper_42, fog12, fog213, value4 + this.method3(potioneffects41, gui2extension8, value14), value5 + value10);
         value10 += 23.0F;
      }

      potioneffects41.method16(Math.max(value9, 20.0F) + 7.0F, value10 - 1.0F);
   }

   private float method2(Potioneffects4 potioneffects41, HudAlignment gui2extension2, float value3) {
      return switch (gui2extension2) {
         case RIGHT -> potioneffects41.getWidth() - value3;
         case MIDDLE -> potioneffects41.getWidth() / 2.0F - value3 / 2.0F + 20.0F;
         default -> 20.0F;
      };
   }

   private float method3(Potioneffects4 potioneffects41, HudAlignment gui2extension2, float value3) {
      return switch (gui2extension2) {
         case RIGHT -> potioneffects41.getWidth() - 20.0F;
         case MIDDLE -> potioneffects41.getWidth() / 2.0F - value3 / 2.0F;
         default -> 0.0F;
      };
   }
}
