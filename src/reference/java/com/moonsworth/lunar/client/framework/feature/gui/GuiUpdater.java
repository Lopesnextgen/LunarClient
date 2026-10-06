package com.moonsworth.lunar.client.framework.feature.gui;

import com.moonsworth.lunar.bridge.slayer.SlayerType;
import com.moonsworth.lunar.client.render.texture.TextureProcessor;
import com.moonsworth.lunar.client.render.texture.TextureProcessor.Extension;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.OptionSupplier;
import com.moonsworth.lunar.client.mod.render.overlay.OverlayMod;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.alert.GuiRewindhandlersHandler2;
import com.moonsworth.lunar.client.util.alert.GuiRewindhandlersHandler2.Data6;
import java.util.Locale;
import org.jetbrains.annotations.Nullable;
import toxi.math.noise.PerlinNoise;

abstract class GuiUpdater implements TextureProcessor {
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

   public GuiUpdater(OverlayMod var1, OptionSupplier<? extends ClientOption<Boolean>, Boolean> var2, String var3) {
      this.field13 = var1;
      this.option = var2.method1();
      this.field14 = var3;
      this.option.method9(() -> {
         if (var1.isEnabled()) {
            var1.method71().method13();
            this.updateState();
            var1.method71().method15();
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

   public static void method3(Extension var0, int var1, int var2, boolean var3) {
      method5(var0, var1, var2, method10(var0), var3);
   }

   public static void method4(Extension var0, int var1, int var2, int var3, int var4, @Nullable PerlinNoise var5) {
      method6(var0, var1, var2, var3, var4, method10(var0), var5);
   }

   public static void method5(Extension var0, int var1, int var2, int var3, boolean var4) {
      PerlinNoise var5 = var4 ? method8(var0) : null;
      int var6 = var0.method2();
      int var7 = var0.method3();
      if (var6 != var7 && var7 % var6 == 0) {
         int var8 = var7 / var6;

         do {
            method6(var0, var8 - 1, var6, var1, var2, var3, var5);
         } while (--var8 > 0);
      } else {
         method6(var0, 0, var6, var1, var2, var3, var5);
      }
   }

   public static void method6(Extension var0, int var1, int var2, int var3, int var4, int var5, @Nullable PerlinNoise var6) {
      if ((var5 & 0xFF) != 0) {
         boolean var7 = (var5 & 240) != 0;
         boolean var8 = (var5 & 128) != 0;
         boolean var9 = (var5 & 32) != 0;
         boolean var10 = (var5 & 64) != 0;
         boolean var11 = (var5 & 16) != 0;
         boolean var12 = !var10 && (var5 & 8) != 0;
         boolean var13 = !var10 && (var5 & 4) != 0;
         boolean var14 = !var11 && (var5 & 2) != 0;
         boolean var15 = !var11 && (var5 & 1) != 0;
         boolean var16 = var12 || var13 || var14 || var15;
         int var17 = Math.max(1, var2 / 16) * var3;
         int var18 = var1 * var2;

         for (int var19 = 0; var19 < var17; var19++) {
            if (var16) {
               for (int var20 = 0; var20 < var17; var20++) {
                  if (var12) {
                     method7(var0, var19, var20, var18, var4, var6);
                  }

                  if (var13) {
                     method7(var0, var2 - 1 - var19, var20, var18, var4, var6);
                  }

                  if (var14) {
                     method7(var0, var19, var2 - 1 - var20, var18, var4, var6);
                  }

                  if (var15) {
                     method7(var0, var2 - 1 - var19, var2 - 1 - var20, var18, var4, var6);
                  }
               }
            }

            if (var7) {
               for (int var21 = 0; var21 < var2; var21++) {
                  if (var8) {
                     method7(var0, var19, var21, var18, var4, var6);
                  }

                  if (var9) {
                     method7(var0, var2 - 1 - var19, var21, var18, var4, var6);
                  }

                  if (var10) {
                     method7(var0, var21, var19, var18, var4, var6);
                  }

                  if (var11) {
                     method7(var0, var21, var2 - 1 - var19, var18, var4, var6);
                  }
               }
            }
         }
      }
   }

   private static void method7(Extension var0, int var1, int var2, int var3, int var4, @Nullable PerlinNoise var5) {
      if (var5 != null) {
         int var6 = var4 >> 16 & 0xFF;
         int var7 = var4 >> 8 & 0xFF;
         int var8 = var4 & 0xFF;
         float var9 = 0.4F + var5.noise(var1 / 8.0F, var2 / 8.0F);
         var6 = (int)(var6 * var9);
         var7 = (int)(var7 * var9);
         var8 = (int)(var8 * var9);
         var4 = var4 & 0xFF000000 | ((var6 > 255 ? 255 : var6) & 0xFF) << 16 | ((var7 > 255 ? 255 : var7) & 0xFF) << 8 | (var8 > 255 ? 255 : var8) & 0xFF;
      }

      var0.method5(var1, var2 + var3, var4);
   }

   public static PerlinNoise method8(Extension var0) {
      PerlinNoise var1 = new PerlinNoise();
      var1.noiseSeed(var0.name().hashCode());
      return var1;
   }

   public static int method9(int var0) {
      var0 = (int)(var0 / 0.9);
      return var0 > 255 ? 255 : var0;
   }

   public static int method10(Extension var0) {
      GuiRewindhandlersHandler2 var1 = (GuiRewindhandlersHandler2)ThreadModuleDump63.method4().method40().method84().method71().method11().orElse(null);
      if (var1 == null) {
         return 240;
      }

      String var2 = var0.method1();
      String var3 = var2.toLowerCase(Locale.ROOT);
      if (var3.contains("ctm/") && (var3.contains("optifine") || var3.contains("mcpatcher"))) {
         Data6 var4 = (Data6)var1.getConnectedTexture(var0.name()).orElse(null);
         if (var4 != null && var4.method2(var2)) {
            SlayerType var5 = var4.method1(var2);
            int var6 = var4.method3(var2);
            return method11(var5, var6);
         }
      }

      return 240;
   }

   public static int method11(SlayerType var0, int var1) {
      switch (var0) {
         case CTM:
         case OVERLAY_CTM:
            return switch (var1) {
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
            return switch (var1) {
               case 0 -> 240;
               case 1 -> 0;
               case 2 -> 160;
               case 3 -> 80;
               case 4 -> 15;
               default -> 0;
            };
         case HORIZONTAL:
            return switch (var1) {
               case 0 -> 208;
               case 1 -> 80;
               case 2 -> 112;
               case 3 -> 240;
               default -> 0;
            };
         case VERTICAL:
            return switch (var1) {
               case 0 -> 176;
               case 1 -> 160;
               case 2 -> 224;
               case 3 -> 240;
               default -> 0;
            };
         case HORIZONTAL_VERTICAL:
            return switch (var1) {
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
            return switch (var1) {
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
            return switch (var1) {
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
