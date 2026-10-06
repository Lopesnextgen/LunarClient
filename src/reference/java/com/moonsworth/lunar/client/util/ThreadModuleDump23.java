package com.moonsworth.lunar.client.util;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import lombok.Generated;
import net.kyori.adventure.text.format.NamedTextColor;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

public final class ThreadModuleDump23 {
   public static final List<NamedTextColor> field1 = Collections.unmodifiableList(
      Arrays.asList(
         NamedTextColor.BLACK,
         NamedTextColor.DARK_BLUE,
         NamedTextColor.DARK_GREEN,
         NamedTextColor.DARK_AQUA,
         NamedTextColor.DARK_RED,
         NamedTextColor.DARK_PURPLE,
         NamedTextColor.GOLD,
         NamedTextColor.GRAY,
         NamedTextColor.DARK_GRAY,
         NamedTextColor.BLUE,
         NamedTextColor.GREEN,
         NamedTextColor.AQUA,
         NamedTextColor.RED,
         NamedTextColor.LIGHT_PURPLE,
         NamedTextColor.YELLOW,
         NamedTextColor.WHITE
      )
   );
   private static final int field2 = 24;
   private static final int field3 = 16;
   private static final int field4 = 8;
   private static final int field5 = 255;
   public static final int field6 = -16777216;
   public static final int field7 = 16711680;
   public static final int field8 = 65280;
   public static final int field9 = 255;
   public static final int field10 = -1;
   public static final int field11 = 0;
   private static final float field12 = 0.16666667F;
   private static final float field13 = 0.003921569F;
   private static final float field14 = 255.0F;

   public static @Range(from = 0L, to = 255L) int method1(int var0) {
      return var0 >> 16 & 0xFF;
   }

   public static @Range(from = 0L, to = 255L) int method2(int var0) {
      return var0 >> 8 & 0xFF;
   }

   public static @Range(from = 0L, to = 255L) int method3(int var0) {
      return var0 & 0xFF;
   }

   public static @Range(from = 0L, to = 255L) int method4(int var0) {
      return var0 >> 24 & 0xFF;
   }

   public static @Range(from = 0L, to = 1L) float method5(int var0) {
      return method1(var0) * 0.003921569F;
   }

   public static @Range(from = 0L, to = 1L) float greenFloat(int var0) {
      return method2(var0) * 0.003921569F;
   }

   public static @Range(from = 0L, to = 1L) float method7(int var0) {
      return method3(var0) * 0.003921569F;
   }

   public static @Range(from = 0L, to = 1L) float method8(int var0) {
      return method4(var0) * 0.003921569F;
   }

   public static @Range(from = 0L, to = 1L) float method9(@Range(from = 0L, to = 255L) int var0) {
      return var0 * 0.003921569F;
   }

   public static int method10(
      @Range(from = 0L, to = 255L) int var0,
      @Range(from = 0L, to = 255L) int var1,
      @Range(from = 0L, to = 255L) int var2,
      @Range(from = 0L, to = 255L) int var3
   ) {
      return (var3 & 0xFF) << 24 | (var0 & 0xFF) << 16 | (var1 & 0xFF) << 8 | var2 & 0xFF;
   }

   public static int method11(
      @Range(from = 0L, to = 1L) float var0,
      @Range(from = 0L, to = 1L) float var1,
      @Range(from = 0L, to = 1L) float var2,
      @Range(from = 0L, to = 1L) float var3
   ) {
      return method10((int)(var0 * 255.0F + 0.5F), (int)(var1 * 255.0F + 0.5F), (int)(var2 * 255.0F + 0.5F), (int)(var3 * 255.0F + 0.5F));
   }

   public static int method12(@Range(from = 0L, to = 255L) int var0, @Range(from = 0L, to = 255L) int var1, @Range(from = 0L, to = 255L) int var2) {
      return method10(var0, var1, var2, 255);
   }

   public static int method13(@Range(from = 0L, to = 1L) float var0, @Range(from = 0L, to = 1L) float var1, @Range(from = 0L, to = 1L) float var2) {
      return method11(var0, var1, var2, 1.0F);
   }

   public static int method14(int var0, @Range(from = 0L, to = 255L) int var1) {
      return method4(var0) < var1 ? method22(var0, var1) : var0;
   }

   public static float[] method15(int var0) {
      return new float[]{method1(var0) * 0.003921569F, method2(var0) * 0.003921569F, method3(var0) * 0.003921569F};
   }

   public static float[] method16(int var0) {
      return new float[]{method1(var0) * 0.003921569F, method2(var0) * 0.003921569F, method3(var0) * 0.003921569F, method4(var0) * 0.003921569F};
   }

   public static int method17(int var0) {
      return var0 & 0xFF000000 | method3(var0) << 16 | method2(var0) << 8 | method1(var0);
   }

   public static int method18(int var0, @Range(from = 0L, to = 1L) float var1) {
      if (var1 == 1.0F) {
         return var0;
      }

      int var2 = (int)(method4(var0) * var1);
      return method22(var0, ThreadModuleDump67.method6(var2, 0, 255));
   }

   public static int method19(int var0, @Range(from = 0L, to = 255L) int var1) {
      return var1 << 16 | var0 & -16711681;
   }

   public static int method20(int var0, @Range(from = 0L, to = 255L) int var1) {
      return var1 << 8 | var0 & -65281;
   }

   public static int method21(int var0, @Range(from = 0L, to = 255L) int var1) {
      return var1 | var0 & -256;
   }

   public static int method22(int var0, @Range(from = 0L, to = 255L) int var1) {
      return var1 << 24 | var0 & 16777215;
   }

   public static int method23(int var0, @Range(from = 0L, to = 1L) float var1) {
      return (int)(var1 * 255.0F) << 16 | var0 & -16711681;
   }

   public static int method24(int var0, @Range(from = 0L, to = 1L) float var1) {
      return (int)(var1 * 255.0F) << 8 | var0 & -65281;
   }

   public static int method25(int var0, @Range(from = 0L, to = 1L) float var1) {
      return (int)(var1 * 255.0F) | var0 & -256;
   }

   public static int method26(int var0, @Range(from = 0L, to = 1L) float var1) {
      return (int)(var1 * 255.0F) << 24 | var0 & 16777215;
   }

   public static int method27(int var0, int var1) {
      if (var1 == -1) {
         return var0;
      } else {
         return var0 == -1
            ? var1
            : method10(
               method28(method1(var0), method1(var1)),
               method28(method2(var0), method2(var1)),
               method28(method3(var0), method3(var1)),
               method28(method4(var0), method4(var1))
            );
      }
   }

   private static int method28(@Range(from = 0L, to = 255L) int var0, @Range(from = 0L, to = 255L) int var1) {
      return (int)(var0 * var1 * 0.003921569F);
   }

   public static int[] method29(int var0) {
      return new int[]{method1(var0), method2(var0), method3(var0)};
   }

   public static int[] method30(int var0) {
      return new int[]{method1(var0), method2(var0), method3(var0), method4(var0)};
   }

   public static int method31(int var0) {
      return var0 | 0xFF000000;
   }

   public static int method32(int var0) {
      return (var0 & 16579836) >> 2 | var0 & 0xFF000000;
   }

   public static int method33(int var0, @Range(from = 0L, to = 255L) int var1) {
      float var2 = var1 * 0.003921569F;
      int var3 = (int)((var0 >> 16 & 0xFF) * var2);
      int var4 = (int)((var0 >> 8 & 0xFF) * var2);
      int var5 = (int)((var0 & 0xFF) * var2);
      return ThreadModuleDump63.MC_VERSION >= 6 ? 0xFF000000 | var3 << 16 | var4 << 8 | var5 : 0xFF000000 | var5 << 16 | var4 << 8 | var3;
   }

   public static int method34(int var0, @Range(from = 0L, to = 1L) float var1) {
      if (var1 >= 1.0F) {
         return var0;
      }

      if (var1 <= 0.0F) {
         return var0 & 0xFF000000;
      }

      int var2 = (int)((var0 >> 16 & 0xFF) * var1);
      int var3 = (int)((var0 >> 8 & 0xFF) * var1);
      int var4 = (int)((var0 & 0xFF) * var1);
      return var0 & 0xFF000000 | var2 << 16 | var3 << 8 | var4;
   }

   public static int method35(int var0, int var1, @Range(from = 0L, to = 1L) float var2) {
      float var3 = 1.0F - var2;
      int var4 = (int)((var0 >> 16 & 0xFF) * var2 + (var1 >> 16 & 0xFF) * var3);
      int var5 = (int)((var0 >> 8 & 0xFF) * var2 + (var1 >> 8 & 0xFF) * var3);
      int var6 = (int)((var0 & 0xFF) * var2 + (var1 & 0xFF) * var3);
      return 0xFF000000 | var4 << 16 | var5 << 8 | var6;
   }

   public static int method36(int var0, int var1) {
      float var2 = method8(var0);
      float var3 = method5(var0);
      float var4 = greenFloat(var0);
      float var5 = method7(var0);
      float var6 = method8(var1);
      float var7 = method5(var1);
      float var8 = greenFloat(var1);
      float var9 = method7(var1);
      float var10 = 1.0F - var6;
      float var11 = var6 + var2 * var10;
      if (var11 == 0.0F) {
         return 0;
      }

      int var12 = (int)((var7 * var6 + var3 * var2 * var10) / var11 * 255.0F);
      int var13 = (int)((var8 * var6 + var4 * var2 * var10) / var11 * 255.0F);
      int var14 = (int)((var9 * var6 + var5 * var2 * var10) / var11 * 255.0F);
      return (int)(var11 * 255.0F) << 24 | var12 << 16 | var13 << 8 | var14;
   }

   @Nullable
   public static NamedTextColor method37(char var0) {
      return switch (var0) {
         case '0' -> NamedTextColor.BLACK;
         case '1' -> NamedTextColor.DARK_BLUE;
         case '2' -> NamedTextColor.DARK_GREEN;
         case '3' -> NamedTextColor.DARK_AQUA;
         case '4' -> NamedTextColor.DARK_RED;
         case '5' -> NamedTextColor.DARK_PURPLE;
         case '6' -> NamedTextColor.GOLD;
         case '7' -> NamedTextColor.GRAY;
         case '8' -> NamedTextColor.DARK_GRAY;
         case '9' -> NamedTextColor.BLUE;
         default -> null;
         case 'a' -> NamedTextColor.GREEN;
         case 'b' -> NamedTextColor.AQUA;
         case 'c' -> NamedTextColor.RED;
         case 'd' -> NamedTextColor.LIGHT_PURPLE;
         case 'e' -> NamedTextColor.YELLOW;
         case 'f' -> NamedTextColor.WHITE;
      };
   }

   public static int method38(@Range(from = 0L, to = 1L) float var0, @Range(from = 0L, to = 1L) float var1, @Range(from = 0L, to = 1L) float var2) {
      int var3;
      int var4;
      int var5;
      if (var1 == 0.0F) {
         var3 = var4 = var5 = (int)(var2 * 255.0F + 0.5F);
      } else {
         float var6 = (var0 - (float)Math.floor(var0)) * 6.0F;
         float var7 = var6 - (float)Math.floor(var6);
         float var8 = var2 * (1.0F - var1);
         float var9 = var2 * (1.0F - var1 * var7);
         float var10 = var2 * (1.0F - var1 * (1.0F - var7));
         switch ((int)var6) {
            case 0:
               var3 = (int)(var2 * 255.0F + 0.5F);
               var4 = (int)(var10 * 255.0F + 0.5F);
               var5 = (int)(var8 * 255.0F + 0.5F);
               break;
            case 1:
               var3 = (int)(var9 * 255.0F + 0.5F);
               var4 = (int)(var2 * 255.0F + 0.5F);
               var5 = (int)(var8 * 255.0F + 0.5F);
               break;
            case 2:
               var3 = (int)(var8 * 255.0F + 0.5F);
               var4 = (int)(var2 * 255.0F + 0.5F);
               var5 = (int)(var10 * 255.0F + 0.5F);
               break;
            case 3:
               var3 = (int)(var8 * 255.0F + 0.5F);
               var4 = (int)(var9 * 255.0F + 0.5F);
               var5 = (int)(var2 * 255.0F + 0.5F);
               break;
            case 4:
               var3 = (int)(var10 * 255.0F + 0.5F);
               var4 = (int)(var8 * 255.0F + 0.5F);
               var5 = (int)(var2 * 255.0F + 0.5F);
               break;
            case 5:
               var3 = (int)(var2 * 255.0F + 0.5F);
               var4 = (int)(var8 * 255.0F + 0.5F);
               var5 = (int)(var9 * 255.0F + 0.5F);
               break;
            default:
               var3 = 0;
               var4 = 0;
               var5 = 0;
         }
      }

      return 0xFF000000 | var3 << 16 | var4 << 8 | var5;
   }

   public static float[] method39(int var0) {
      return method40(method1(var0), method2(var0), method3(var0));
   }

   public static float[] method40(@Range(from = 0L, to = 255L) int var0, @Range(from = 0L, to = 255L) int var1, @Range(from = 0L, to = 255L) int var2) {
      float[] var3 = new float[3];
      int var4 = Math.max(var0, var1);
      if (var2 > var4) {
         var4 = var2;
      }

      int var5 = Math.min(var0, var1);
      if (var2 < var5) {
         var5 = var2;
      }

      float var8 = var4 * 0.003921569F;
      float var7;
      if (var4 != 0) {
         var7 = (float)(var4 - var5) / var4;
      } else {
         var7 = 0.0F;
      }

      float var6;
      if (var7 == 0.0F) {
         var6 = 0.0F;
      } else {
         float var9 = (float)(var4 - var0) / (var4 - var5);
         float var10 = (float)(var4 - var1) / (var4 - var5);
         float var11 = (float)(var4 - var2) / (var4 - var5);
         if (var0 == var4) {
            var6 = var11 - var10;
         } else if (var1 == var4) {
            var6 = 2.0F + var9 - var11;
         } else {
            var6 = 4.0F + var10 - var9;
         }

         var6 *= 0.16666667F;
         if (var6 < 0.0F) {
            var6++;
         }
      }

      var3[0] = var6;
      var3[1] = var7;
      var3[2] = var8;
      return var3;
   }

   public static @Range(from = 0L, to = 1L) float method41(int var0) {
      return method42(method1(var0), method2(var0), method3(var0));
   }

   public static @Range(from = 0L, to = 1L) float method42(
      @Range(from = 0L, to = 255L) int var0, @Range(from = 0L, to = 255L) int var1, @Range(from = 0L, to = 255L) int var2
   ) {
      int var3 = Math.max(var0, var1);
      if (var2 > var3) {
         var3 = var2;
      }

      int var4 = Math.min(var0, var1);
      if (var2 < var4) {
         var4 = var2;
      }

      float var6;
      if (var3 != 0) {
         var6 = (float)(var3 - var4) / var3;
      } else {
         var6 = 0.0F;
      }

      float var5;
      if (var6 == 0.0F) {
         var5 = 0.0F;
      } else {
         float var7 = (float)(var3 - var0) / (var3 - var4);
         float var8 = (float)(var3 - var1) / (var3 - var4);
         float var9 = (float)(var3 - var2) / (var3 - var4);
         if (var0 == var3) {
            var5 = var9 - var8;
         } else if (var1 == var3) {
            var5 = 2.0F + var7 - var9;
         } else {
            var5 = 4.0F + var8 - var7;
         }

         var5 *= 0.16666667F;
         if (var5 < 0.0F) {
            var5++;
         }
      }

      return var5;
   }

   public static @Range(from = 0L, to = 1L) float method43(int var0) {
      return method44(method1(var0), method2(var0), method3(var0));
   }

   public static @Range(from = 0L, to = 1L) float method44(
      @Range(from = 0L, to = 255L) int var0, @Range(from = 0L, to = 255L) int var1, @Range(from = 0L, to = 255L) int var2
   ) {
      int var3 = Math.max(var0, var1);
      if (var2 > var3) {
         var3 = var2;
      }

      int var4 = Math.min(var0, var1);
      if (var2 < var4) {
         var4 = var2;
      }

      float var5;
      if (var3 != 0) {
         var5 = (float)(var3 - var4) / var3;
      } else {
         var5 = 0.0F;
      }

      return var5;
   }

   public static @Range(from = 0L, to = 1L) float method45(int var0) {
      return method46(method1(var0), method2(var0), method3(var0));
   }

   public static @Range(from = 0L, to = 1L) float method46(
      @Range(from = 0L, to = 255L) int var0, @Range(from = 0L, to = 255L) int var1, @Range(from = 0L, to = 255L) int var2
   ) {
      int var3 = Math.max(var0, var1);
      if (var2 > var3) {
         var3 = var2;
      }

      return var3 * 0.003921569F;
   }

   public static int method47(String var0) {
      if (var0.startsWith("#")) {
         var0 = var0.substring(1);
      } else if (var0.startsWith("0x") || var0.startsWith("0X")) {
         var0 = var0.substring(2);
      }

      if (var0.length() == 6) {
         var0 = "FF" + var0;
      } else if (var0.length() != 8) {
         throw new IllegalArgumentException("Invalid hex string: " + var0);
      }

      return (int)Long.parseLong(var0, 16);
   }

   public static String method48(int var0, boolean var1) {
      return "#" + StringUtils.leftPad(Integer.toHexString(var1 ? var0 : var0 & 16777215), var1 ? 8 : 6, "0").toUpperCase(Locale.ROOT);
   }

   public static int method49(float var0, float var1, float var2) {
      return method50(var0, var1, var2, 0);
   }

   public static int method50(float var0, float var1, float var2, int var3) {
      Color var4 = Color.getHSBColor(var0, var1, var2);
      return method22(var4.getRGB(), var3);
   }

   public static int method51(int var0, int var1, float var2) {
      float var3 = method5(var0);
      float var4 = greenFloat(var0);
      float var5 = method7(var0);
      float var6 = method8(var0);
      float var7 = method5(var1);
      float var8 = greenFloat(var1);
      float var9 = method7(var1);
      float var10 = method8(var1);
      float var11 = ThreadModuleDump67.lerp(var3, var7, var2);
      float var12 = ThreadModuleDump67.lerp(var4, var8, var2);
      float var13 = ThreadModuleDump67.lerp(var5, var9, var2);
      float var14 = ThreadModuleDump67.lerp(var6, var10, var2);
      return method11(var11, var12, var13, var14);
   }

   @Generated
   private ThreadModuleDump23() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
