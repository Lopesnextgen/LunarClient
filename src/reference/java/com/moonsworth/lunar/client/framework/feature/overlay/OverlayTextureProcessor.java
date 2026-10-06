package com.moonsworth.lunar.client.framework.feature.overlay;

import com.moonsworth.lunar.bridge.optifine.ConnectedTextureMethod;
import com.moonsworth.lunar.client.render.texture.TextureProcessor;
import com.moonsworth.lunar.client.render.texture.TextureProcessor.TextureProvider;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.OptionProvider;
import com.moonsworth.lunar.client.mod.render.overlay.OverlayMod;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.framework.feature.overlay.ConnectedTexturesListener;
import com.moonsworth.lunar.client.framework.feature.overlay.ConnectedTexturesListener.ConnectedTexture;
import java.util.Locale;
import org.jetbrains.annotations.Nullable;
import toxi.math.noise.PerlinNoise;

abstract class OverlayTextureProcessor implements TextureProcessor {
   public static final int field1 = 255;
   public static final int field2 = 0;
   public static final int field3 = 240;
   public static final int field4 = 15;
   public static final int field5 = 128;
   public static final int field6 = 64;
   public static final int field7 = 32;
   public static final int field8 = 16;
   public static final int field9 = 8;
   public static final int field10 = 4;
   public static final int field11 = 2;
   public static final int field12 = 1;
   protected final OverlayMod field13;
   protected final ClientOption<Boolean> option;
   protected final String field14;

   public OverlayTextureProcessor(OverlayMod overlaymod1, OptionProvider<? extends ClientOption<Boolean>, Boolean> lighting_72, String text3) {
      this.field13 = overlaymod1;
      this.option = lighting_72.method1();
      this.field14 = text3;
      this.option.method9(() -> {
         if (overlaymod1.isEnabled()) {
            overlaymod1.method71().method13();
            this.updateState();
            overlaymod1.method71().method15();
         }
      });
   }

   public void updateState() {
      this.field13.method71().method9(this, true);
      if (this.method2()) {
         this.field13.method71().method7(this.field14, this, true);
      }
   }

   public ClientOption<Boolean> getOption() {
      return this.option;
   }

   protected boolean method2() {
      return this.field13.isEnabled() && (Boolean)this.option.get();
   }

   public static void method3(TextureProvider extension0, int number1, int number2, boolean flag3) {
      method5(extension0, number1, number2, method10(extension0), flag3);
   }

   public static void method4(TextureProvider extension0, int number1, int number2, int number3, int number4, @Nullable PerlinNoise perlinnoise5) {
      method6(extension0, number1, number2, number3, number4, method10(extension0), perlinnoise5);
   }

   public static void method5(TextureProvider extension0, int number1, int number2, int number3, boolean flag4) {
      PerlinNoise perlinnoise5 = flag4 ? method8(extension0) : null;
      int number6 = extension0.method2();
      int number7 = extension0.method3();
      if (number6 != number7 && number7 % number6 == 0) {
         int number8 = number7 / number6;

         do {
            method6(extension0, number8 - 1, number6, number1, number2, number3, perlinnoise5);
         } while (--number8 > 0);
      } else {
         method6(extension0, 0, number6, number1, number2, number3, perlinnoise5);
      }
   }

   public static void method6(TextureProvider extension0, int number1, int number2, int number3, int number4, int number5, @Nullable PerlinNoise perlinnoise6) {
      if ((number5 & 0xFF) != 0) {
         boolean flag7 = (number5 & 240) != 0;
         boolean flag8 = (number5 & 128) != 0;
         boolean flag9 = (number5 & 32) != 0;
         boolean flag10 = (number5 & 64) != 0;
         boolean flag11 = (number5 & 16) != 0;
         boolean flag12 = !flag10 && (number5 & 8) != 0;
         boolean flag13 = !flag10 && (number5 & 4) != 0;
         boolean flag14 = !flag11 && (number5 & 2) != 0;
         boolean flag15 = !flag11 && (number5 & 1) != 0;
         boolean flag16 = flag12 || flag13 || flag14 || flag15;
         int number17 = Math.max(1, number2 / 16) * number3;
         int number18 = number1 * number2;

         for (int index19 = 0; index19 < number17; index19++) {
            if (flag16) {
               for (int index20 = 0; index20 < number17; index20++) {
                  if (flag12) {
                     method7(extension0, index19, index20, number18, number4, perlinnoise6);
                  }

                  if (flag13) {
                     method7(extension0, number2 - 1 - index19, index20, number18, number4, perlinnoise6);
                  }

                  if (flag14) {
                     method7(extension0, index19, number2 - 1 - index20, number18, number4, perlinnoise6);
                  }

                  if (flag15) {
                     method7(extension0, number2 - 1 - index19, number2 - 1 - index20, number18, number4, perlinnoise6);
                  }
               }
            }

            if (flag7) {
               for (int index21 = 0; index21 < number2; index21++) {
                  if (flag8) {
                     method7(extension0, index19, index21, number18, number4, perlinnoise6);
                  }

                  if (flag9) {
                     method7(extension0, number2 - 1 - index19, index21, number18, number4, perlinnoise6);
                  }

                  if (flag10) {
                     method7(extension0, index21, index19, number18, number4, perlinnoise6);
                  }

                  if (flag11) {
                     method7(extension0, index21, number2 - 1 - index19, number18, number4, perlinnoise6);
                  }
               }
            }
         }
      }
   }

   private static void method7(TextureProvider extension0, int number1, int number2, int number3, int number4, @Nullable PerlinNoise perlinnoise5) {
      if (perlinnoise5 != null) {
         int number6 = number4 >> 16 & 0xFF;
         int number7 = number4 >> 8 & 0xFF;
         int number8 = number4 & 0xFF;
         float value9 = 0.4F + perlinnoise5.noise(number1 / 8.0F, number2 / 8.0F);
         number6 = (int)(number6 * value9);
         number7 = (int)(number7 * value9);
         number8 = (int)(number8 * value9);
         number4 = number4 & 0xFF000000 | ((number6 > 255 ? 255 : number6) & 0xFF) << 16 | ((number7 > 255 ? 255 : number7) & 0xFF) << 8 | (number8 > 255 ? 255 : number8) & 0xFF;
      }

      extension0.method5(number1, number2 + number3, number4);
   }

   public static PerlinNoise method8(TextureProvider extension0) {
      PerlinNoise perlinnoise1 = new PerlinNoise();
      perlinnoise1.noiseSeed(extension0.name().hashCode());
      return perlinnoise1;
   }

   public static int method9(int number0) {
      number0 = (int)(number0 / 0.9);
      return number0 > 255 ? 255 : number0;
   }

   public static int method10(TextureProvider extension0) {
      ConnectedTexturesListener guirewindhandlershandler21 = (ConnectedTexturesListener)Ref.method4().method40().method84().method71().method11().orElse(null);
      if (guirewindhandlershandler21 == null) {
         return 240;
      }

      String text2 = extension0.method1();
      String text3 = text2.toLowerCase(Locale.ROOT);
      if (text3.contains("ctm/") && (text3.contains("optifine") || text3.contains("mcpatcher"))) {
         ConnectedTexture data64 = (ConnectedTexture)guirewindhandlershandler21.method2(extension0.name()).orElse(null);
         if (data64 != null && data64.method2(text2)) {
            ConnectedTextureMethod slayertype5 = data64.method1(text2);
            int number6 = data64.method3(text2);
            return method11(slayertype5, number6);
         }
      }

      return 240;
   }

   public static int method11(ConnectedTextureMethod slayertype0, int number1) {
      switch (slayertype0) {
         case CTM:
         case OVERLAY_CTM:
            return switch (number1) {
               case 0 -> 240;
               case 1 -> 208;
               case 2 -> 80;
               case 3 -> 112;
               case 4 -> 193;
               case 5 -> 98;
               case 6 -> 133;
               case 7 -> 67;
               case 8 -> 11;
               case 9 -> 14;
               case 10 -> 5;
               case 11 -> 3;
               case 12 -> 224;
               case 13 -> 192;
               case 14 -> 64;
               case 15 -> 96;
               case 16 -> 148;
               case 17 -> 56;
               case 18 -> 28;
               case 19 -> 42;
               case 20 -> 7;
               case 21 -> 13;
               case 22 -> 12;
               case 23 -> 10;
               case 24 -> 160;
               case 25 -> 128;
               default -> 0;
               case 27 -> 32;
               case 28 -> 132;
               case 29 -> 65;
               case 30 -> 129;
               case 31 -> 66;
               case 32 -> 1;
               case 33 -> 2;
               case 34 -> 9;
               case 35 -> 6;
               case 36 -> 176;
               case 37 -> 144;
               case 38 -> 16;
               case 39 -> 48;
               case 40 -> 24;
               case 41 -> 34;
               case 42 -> 20;
               case 43 -> 40;
               case 44 -> 4;
               case 45 -> 8;
               case 46 -> 15;
            };
         case CTM_COMPACT:
            return switch (number1) {
               case 0 -> 240;
               case 1 -> 0;
               case 2 -> 160;
               case 3 -> 80;
               case 4 -> 15;
               default -> 0;
            };
         case HORIZONTAL:
            return switch (number1) {
               case 0 -> 208;
               case 1 -> 80;
               case 2 -> 112;
               case 3 -> 240;
               default -> 0;
            };
         case VERTICAL:
            return switch (number1) {
               case 0 -> 176;
               case 1 -> 160;
               case 2 -> 224;
               case 3 -> 240;
               default -> 0;
            };
         case HORIZONTAL_VERTICAL:
            return switch (number1) {
               case 0 -> 208;
               case 1 -> 80;
               case 2 -> 112;
               case 3 -> 240;
               case 4 -> 176;
               case 5 -> 160;
               case 6 -> 224;
               default -> 0;
            };
         case VERTICAL_HORIZONTAL:
            return switch (number1) {
               case 0 -> 176;
               case 1 -> 160;
               case 2 -> 224;
               case 3 -> 240;
               case 4 -> 208;
               case 5 -> 80;
               case 6 -> 112;
               default -> 0;
            };
         case OVERLAY:
            return switch (number1) {
               case 0 -> 1;
               case 1 -> 16;
               case 2 -> 2;
               case 3 -> 48;
               case 4 -> 144;
               case 5 -> 176;
               case 6 -> 208;
               case 7 -> 32;
               case 8 -> 240;
               case 9 -> 128;
               case 10 -> 96;
               case 11 -> 192;
               case 12 -> 112;
               case 13 -> 224;
               case 14 -> 4;
               case 15 -> 64;
               case 16 -> 8;
               default -> 0;
            };
         default:
            return 240;
      }
   }
}
