package com.moonsworth.lunar.client.framework.feature.overlay;

import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.render.texture.TextureProcessor.TextureProvider;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.OptionProvider;
import com.moonsworth.lunar.client.mod.render.overlay.OverlayMod;
import com.moonsworth.lunar.client.framework.Ref;
import toxi.math.noise.PerlinNoise;

class OreTextureProcessor extends OverlayTextureProcessor {
   private final String field15;
   private final int field16;
   private final boolean field17;
   private final boolean field18;
   private final boolean field19;

   public OreTextureProcessor(OverlayMod overlaymod1, OptionProvider<? extends ClientOption<Boolean>, Boolean> lighting_72, String text3, int number4, boolean flag5) {
      super(overlaymod1, lighting_72, "block/" + text3);
      this.field15 = text3;
      this.field16 = number4;
      this.field17 = flag5;
      this.field18 = text3.startsWith("nether_") || text3.equals("quartz") || text3.startsWith("ancient_debris");
      this.field19 = text3.equals("coal_ore");
   }

   @Override
   public void updateState() {
      super.updateState();
      if (this.method2()) {
         if (this.field17 && Ref.MC_VERSION >= 8) {
            this.RHOHICICHHCOHRIOHIHCIHOORICCRI.method71().method7("block/deepslate_" + this.field15, this, true);
         }

         if (Ref.MC_VERSION >= 6 && this.field15.equals("ancient_debris_side")) {
            this.RHOHICICHHCOHRIOHIHCIHOORICCRI.method71().method7("block/ancient_debris_top", this, true);
         }
      }
   }

   @Override
   protected boolean method2() {
      return super.method2() && (Boolean)this.RHOHICICHHCOHRIOHIHCIHOORICCRI.field42.get();
   }

   public void process(TextureProvider extension1) {
      int number2 = extension1.method2();
      if (number2 <= 4) {
         LunarLogger.method5("[Overlay] Skipping ore texture %s, size <=4", new Object[]{extension1.method1()});
      } else {
         int number3 = extension1.method3();
         if (number2 != number3 && number3 % number2 != 0) {
            LunarLogger.method5("[Overlay] Skipping ore texture %s, invalid size %dx%d", new Object[]{extension1.method1(), number2, number3});
         } else {
            int number4 = (Integer)this.RHOHICICHHCOHRIOHIHCIHOORICCRI.field45.get();
            boolean flag5 = (Boolean)this.RHOHICICHHCOHRIOHIHCIHOORICCRI.field44.get();
            if (!(Boolean)this.RHOHICICHHCOHRIOHIHCIHOORICCRI.field43.get()) {
               OverlayTextureProcessor.method3(extension1, number4, this.field16, flag5);
            } else {
               PerlinNoise perlinnoise6 = flag5 ? OverlayTextureProcessor.method8(extension1) : null;
               boolean flag7 = this.field18;
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
                     int number18 = number17 >> 16 & 0xFF;
                     int number19 = number17 >> 8 & 0xFF;
                     int number20 = number17 & 0xFF;
                     int number21 = ((flag7 ? number18 / 2 : number18) + number19 + number20) / 3;
                     boolean flag22 = number21 <= 165
                        && Math.abs((flag7 ? number18 / 2 : number18) - number21) <= 15
                        && Math.abs(number19 - number21) <= 15
                        && Math.abs(number20 - number21) <= 15;
                     if (!flag22) {
                        number8 += OIHHIRHCHRHCCOICIOHHHRROICOIRH(number18);
                        number9 += OIHHIRHCHRHCCOICIOHHHRROICOIRH(number19);
                        number10 += OIHHIRHCHRHCCOICIOHHHRROICOIRH(number20);
                        index11++;
                     }
                  }

                  if (index11 == 0) {
                     if (this.field19) {
                        number10 = 32;
                        number9 = 32;
                        number8 = 32;
                     }
                  } else {
                     number8 = OIHHIRHCHRHCCOICIOHHHRROICOIRH(number8 / index11);
                     number9 = OIHHIRHCHRHCCOICIOHHHRROICOIRH(number9 / index11);
                     number10 = OIHHIRHCHRHCCOICIOHHHRROICOIRH(number10 / index11);
                  }

                  int number23 = 0xFF000000 | (number8 & 0xFF) << 16 | (number9 & 0xFF) << 8 | number10 & 0xFF;
                  OverlayTextureProcessor.method4(extension1, number13 - 1, number2, number4, number23, perlinnoise6);
               } while (--number13 > 0);
            }
         }
      }
   }
}
