package com.moonsworth.lunar.client.framework.feature.overlay;

import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.render.texture.TextureProcessor.TextureProvider;
import com.moonsworth.lunar.client.framework.feature.markers.DyeColorIconMapping;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.OptionProvider;
import com.moonsworth.lunar.client.mod.render.overlay.OverlayMod;
import com.moonsworth.lunar.client.framework.Ref;

class GlassTextureProcessor extends OverlayTextureProcessor {
   public GlassTextureProcessor(OverlayMod overlaymod1, OptionProvider<? extends ClientOption<Boolean>, Boolean> lighting_72) {
      super(overlaymod1, lighting_72, "block/glass");
   }

   @Override
   public void updateState() {
      super.updateState();
      boolean flag1 = this.method2();
      if (flag1) {
         this.RHOHICICHHCOHRIOHIHCIHOORICCRI.method71().method7("block/glass_pane_top", this, true);
         if (Ref.MC_VERSION >= 8) {
            this.RHOHICICHHCOHRIOHIHCIHOORICCRI.method71().method7("block/tinted_glass", this, true);
         }

         if ((Boolean)this.RHOHICICHHCOHRIOHIHCIHOORICCRI.field13.get()) {
            for (String text3 : DyeColorIconMapping.field1.keySet()) {
               this.method1(text3);
            }

            if (Ref.MC_VERSION >= 6) {
               this.method1("light_gray");
            }
         }
      }
   }

   private void method1(String text1) {
      String text2 = Ref.MC_VERSION <= 5 ? "glass_" + text1 : text1 + "_stained_glass";
      this.RHOHICICHHCOHRIOHIHCIHOORICCRI.method71().method7("block/" + text2, this, true);
      text2 = Ref.MC_VERSION <= 5 ? "glass_pane_top_" + text1 : text1 + "_stained_glass_pane_top";
      this.RHOHICICHHCOHRIOHIHCIHOORICCRI.method71().method7("block/" + text2, this, true);
   }

   public void process(TextureProvider extension1) {
      int number2 = extension1.method2();
      int number3 = extension1.method3();
      if (number2 != number3 && number3 % number2 != 0) {
         LunarLogger.method5("[Overlay] Skipping glass texture %s, invalid size %dx%d", new Object[]{extension1.method1(), number2, number3});
      } else {
         boolean flag4 = (Boolean)this.RHOHICICHHCOHRIOHIHCIHOORICCRI.field15.get();
         if (flag4 && number2 <= 4) {
            LunarLogger.method5("[Overlay] Skipping glass texture %s for outline, invalid size", new Object[]{extension1.method1()});
            flag4 = false;
         }

         if (flag4 && extension1.name().contains("glass_pane_")) {
            flag4 = false;
         }

         float value5 = (Float)this.RHOHICICHHCOHRIOHIHCIHOORICCRI.field14.get();
         float value6 = !flag4 ? 0.0F : (Float)this.RHOHICICHHCOHRIOHIHCIHOORICCRI.field17.get();
         int number7 = !flag4 ? 0 : (Integer)this.RHOHICICHHCOHRIOHIHCIHOORICCRI.field16.get();
         int number13 = number3 / number2;

         do {
            int number8 = 0;
            int number9 = 0;
            int number10 = 0;
            int index11 = 0;
            int number12 = (number13 - 1) * number2;

            for (int index14 = 0; index14 < number2 * number2; index14++) {
               int number15 = index14 % number2;
               int number16 = index14 / number2 + number12;
               int number17 = extension1.method4(number15, number16);
               int number18 = number17 >> 24 & 0xFF;
               if (number18 > 25) {
                  number18 = (int)(number18 * value5);
                  extension1.method5(number15, number16, (number18 & 0xFF) << 24 | number17 & 16777215);
                  if (flag4) {
                     number8 += OIHHIRHCHRHCCOICIOHHHRROICOIRH(number17 >> 16 & 0xFF);
                     number9 += OIHHIRHCHRHCCOICIOHHHRROICOIRH(number17 >> 8 & 0xFF);
                     number10 += OIHHIRHCHRHCCOICIOHHHRROICOIRH(number17 & 0xFF);
                     index11++;
                  }
               }
            }

            if (flag4) {
               if (index11 == 0) {
                  number10 = 238;
                  number9 = 238;
                  number8 = 238;
               } else {
                  number8 = OIHHIRHCHRHCCOICIOHHHRROICOIRH(number8 / index11);
                  number9 = OIHHIRHCHRHCCOICIOHHHRROICOIRH(number9 / index11);
                  number10 = OIHHIRHCHRHCCOICIOHHHRROICOIRH(number10 / index11);
               }

               int number22 = (int)(255.0F * value6);
               int number23 = (number22 & 0xFF) << 24 | (number8 & 0xFF) << 16 | (number9 & 0xFF) << 8 | number10 & 0xFF;
               OverlayTextureProcessor.method4(extension1, number13 - 1, number2, number7, number23, null);
            }
         } while (--number13 > 0);
      }
   }
}
