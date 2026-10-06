package com.moonsworth.lunar.client.util.math;

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
import com.moonsworth.lunar.client.framework.Ref;

public final class ColorUtils {
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

   public static @Range(from = 0L, to = 255L) int method1(int number0) {
      return number0 >> 16 & 0xFF;
   }

   public static @Range(from = 0L, to = 255L) int method2(int number0) {
      return number0 >> 8 & 0xFF;
   }

   public static @Range(from = 0L, to = 255L) int method3(int number0) {
      return number0 & 0xFF;
   }

   public static @Range(from = 0L, to = 255L) int method4(int number0) {
      return number0 >> 24 & 0xFF;
   }

   public static @Range(from = 0L, to = 1L) float method5(int number0) {
      return method1(number0) * 0.003921569F;
   }

   public static @Range(from = 0L, to = 1L) float method6(int number0) {
      return method2(number0) * 0.003921569F;
   }

   public static @Range(from = 0L, to = 1L) float method7(int number0) {
      return method3(number0) * 0.003921569F;
   }

   public static @Range(from = 0L, to = 1L) float method8(int number0) {
      return method4(number0) * 0.003921569F;
   }

   public static @Range(from = 0L, to = 1L) float method9(@Range(from = 0L, to = 255L) int number0) {
      return number0 * 0.003921569F;
   }

   public static int method10(
      @Range(from = 0L, to = 255L) int number0,
      @Range(from = 0L, to = 255L) int number1,
      @Range(from = 0L, to = 255L) int number2,
      @Range(from = 0L, to = 255L) int number3
   ) {
      return (number3 & 0xFF) << 24 | (number0 & 0xFF) << 16 | (number1 & 0xFF) << 8 | number2 & 0xFF;
   }

   public static int method11(
      @Range(from = 0L, to = 1L) float value0,
      @Range(from = 0L, to = 1L) float value1,
      @Range(from = 0L, to = 1L) float value2,
      @Range(from = 0L, to = 1L) float value3
   ) {
      return method10((int)(value0 * 255.0F + 0.5F), (int)(value1 * 255.0F + 0.5F), (int)(value2 * 255.0F + 0.5F), (int)(value3 * 255.0F + 0.5F));
   }

   public static int method12(@Range(from = 0L, to = 255L) int number0, @Range(from = 0L, to = 255L) int number1, @Range(from = 0L, to = 255L) int number2) {
      return method10(number0, number1, number2, 255);
   }

   public static int method13(@Range(from = 0L, to = 1L) float value0, @Range(from = 0L, to = 1L) float value1, @Range(from = 0L, to = 1L) float value2) {
      return method11(value0, value1, value2, 1.0F);
   }

   public static int method14(int number0, @Range(from = 0L, to = 255L) int number1) {
      return method4(number0) < number1 ? method22(number0, number1) : number0;
   }

   public static float[] method15(int number0) {
      return new float[]{method1(number0) * 0.003921569F, method2(number0) * 0.003921569F, method3(number0) * 0.003921569F};
   }

   public static float[] method16(int number0) {
      return new float[]{method1(number0) * 0.003921569F, method2(number0) * 0.003921569F, method3(number0) * 0.003921569F, method4(number0) * 0.003921569F};
   }

   public static int method17(int number0) {
      return number0 & 0xFF000000 | method3(number0) << 16 | method2(number0) << 8 | method1(number0);
   }

   public static int method18(int number0, @Range(from = 0L, to = 1L) float value1) {
      if (value1 == 1.0F) {
         return number0;
      }

      int number2 = (int)(method4(number0) * value1);
      return method22(number0, MathUtils.method6(number2, 0, 255));
   }

   public static int method19(int number0, @Range(from = 0L, to = 255L) int number1) {
      return number1 << 16 | number0 & -16711681;
   }

   public static int method20(int number0, @Range(from = 0L, to = 255L) int number1) {
      return number1 << 8 | number0 & -65281;
   }

   public static int method21(int number0, @Range(from = 0L, to = 255L) int number1) {
      return number1 | number0 & -256;
   }

   public static int method22(int number0, @Range(from = 0L, to = 255L) int number1) {
      return number1 << 24 | number0 & 16777215;
   }

   public static int method23(int number0, @Range(from = 0L, to = 1L) float value1) {
      return (int)(value1 * 255.0F) << 16 | number0 & -16711681;
   }

   public static int method24(int number0, @Range(from = 0L, to = 1L) float value1) {
      return (int)(value1 * 255.0F) << 8 | number0 & -65281;
   }

   public static int method25(int number0, @Range(from = 0L, to = 1L) float value1) {
      return (int)(value1 * 255.0F) | number0 & -256;
   }

   public static int method26(int number0, @Range(from = 0L, to = 1L) float value1) {
      return (int)(value1 * 255.0F) << 24 | number0 & 16777215;
   }

   public static int method27(int number0, int number1) {
      if (number1 == -1) {
         return number0;
      } else {
         return number0 == -1
            ? number1
            : method10(
               method28(method1(number0), method1(number1)),
               method28(method2(number0), method2(number1)),
               method28(method3(number0), method3(number1)),
               method28(method4(number0), method4(number1))
            );
      }
   }

   private static int method28(@Range(from = 0L, to = 255L) int number0, @Range(from = 0L, to = 255L) int number1) {
      return (int)(number0 * number1 * 0.003921569F);
   }

   public static int[] method29(int number0) {
      return new int[]{method1(number0), method2(number0), method3(number0)};
   }

   public static int[] method30(int number0) {
      return new int[]{method1(number0), method2(number0), method3(number0), method4(number0)};
   }

   public static int method31(int number0) {
      return number0 | 0xFF000000;
   }

   public static int method32(int number0) {
      return (number0 & 16579836) >> 2 | number0 & 0xFF000000;
   }

   public static int method33(int number0, @Range(from = 0L, to = 255L) int number1) {
      float value2 = number1 * 0.003921569F;
      int number3 = (int)((number0 >> 16 & 0xFF) * value2);
      int number4 = (int)((number0 >> 8 & 0xFF) * value2);
      int number5 = (int)((number0 & 0xFF) * value2);
      return Ref.MC_VERSION >= 6 ? 0xFF000000 | number3 << 16 | number4 << 8 | number5 : 0xFF000000 | number5 << 16 | number4 << 8 | number3;
   }

   public static int method34(int number0, @Range(from = 0L, to = 1L) float value1) {
      if (value1 >= 1.0F) {
         return number0;
      }

      if (value1 <= 0.0F) {
         return number0 & 0xFF000000;
      }

      int number2 = (int)((number0 >> 16 & 0xFF) * value1);
      int number3 = (int)((number0 >> 8 & 0xFF) * value1);
      int number4 = (int)((number0 & 0xFF) * value1);
      return number0 & 0xFF000000 | number2 << 16 | number3 << 8 | number4;
   }

   public static int method35(int number0, int number1, @Range(from = 0L, to = 1L) float value2) {
      float value3 = 1.0F - value2;
      int number4 = (int)((number0 >> 16 & 0xFF) * value2 + (number1 >> 16 & 0xFF) * value3);
      int number5 = (int)((number0 >> 8 & 0xFF) * value2 + (number1 >> 8 & 0xFF) * value3);
      int number6 = (int)((number0 & 0xFF) * value2 + (number1 & 0xFF) * value3);
      return 0xFF000000 | number4 << 16 | number5 << 8 | number6;
   }

   public static int method36(int number0, int number1) {
      float value2 = method8(number0);
      float value3 = method5(number0);
      float value4 = method6(number0);
      float value5 = method7(number0);
      float value6 = method8(number1);
      float value7 = method5(number1);
      float value8 = method6(number1);
      float value9 = method7(number1);
      float value10 = 1.0F - value6;
      float value11 = value6 + value2 * value10;
      if (value11 == 0.0F) {
         return 0;
      }

      int number12 = (int)((value7 * value6 + value3 * value2 * value10) / value11 * 255.0F);
      int number13 = (int)((value8 * value6 + value4 * value2 * value10) / value11 * 255.0F);
      int number14 = (int)((value9 * value6 + value5 * value2 * value10) / value11 * 255.0F);
      return (int)(value11 * 255.0F) << 24 | number12 << 16 | number13 << 8 | number14;
   }

   @Nullable
   public static NamedTextColor method37(char character0) {
      return switch (character0) {
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

   public static int method38(@Range(from = 0L, to = 1L) float value0, @Range(from = 0L, to = 1L) float value1, @Range(from = 0L, to = 1L) float value2) {
      int number3;
      int number4;
      int number5;
      if (value1 == 0.0F) {
         number3 = number4 = number5 = (int)(value2 * 255.0F + 0.5F);
      } else {
         float value6 = (value0 - (float)Math.floor(value0)) * 6.0F;
         float value7 = value6 - (float)Math.floor(value6);
         float value8 = value2 * (1.0F - value1);
         float value9 = value2 * (1.0F - value1 * value7);
         float value10 = value2 * (1.0F - value1 * (1.0F - value7));
         switch ((int)value6) {
            case 0:
               number3 = (int)(value2 * 255.0F + 0.5F);
               number4 = (int)(value10 * 255.0F + 0.5F);
               number5 = (int)(value8 * 255.0F + 0.5F);
               break;
            case 1:
               number3 = (int)(value9 * 255.0F + 0.5F);
               number4 = (int)(value2 * 255.0F + 0.5F);
               number5 = (int)(value8 * 255.0F + 0.5F);
               break;
            case 2:
               number3 = (int)(value8 * 255.0F + 0.5F);
               number4 = (int)(value2 * 255.0F + 0.5F);
               number5 = (int)(value10 * 255.0F + 0.5F);
               break;
            case 3:
               number3 = (int)(value8 * 255.0F + 0.5F);
               number4 = (int)(value9 * 255.0F + 0.5F);
               number5 = (int)(value2 * 255.0F + 0.5F);
               break;
            case 4:
               number3 = (int)(value10 * 255.0F + 0.5F);
               number4 = (int)(value8 * 255.0F + 0.5F);
               number5 = (int)(value2 * 255.0F + 0.5F);
               break;
            case 5:
               number3 = (int)(value2 * 255.0F + 0.5F);
               number4 = (int)(value8 * 255.0F + 0.5F);
               number5 = (int)(value9 * 255.0F + 0.5F);
               break;
            default:
               number3 = 0;
               number4 = 0;
               number5 = 0;
         }
      }

      return 0xFF000000 | number3 << 16 | number4 << 8 | number5;
   }

   public static float[] method39(int number0) {
      return method40(method1(number0), method2(number0), method3(number0));
   }

   public static float[] method40(@Range(from = 0L, to = 255L) int number0, @Range(from = 0L, to = 255L) int number1, @Range(from = 0L, to = 255L) int number2) {
      float[] items3 = new float[3];
      int number4 = Math.max(number0, number1);
      if (number2 > number4) {
         number4 = number2;
      }

      int number5 = Math.min(number0, number1);
      if (number2 < number5) {
         number5 = number2;
      }

      float value8 = number4 * 0.003921569F;
      float value7;
      if (number4 != 0) {
         value7 = (float)(number4 - number5) / number4;
      } else {
         value7 = 0.0F;
      }

      float value6;
      if (value7 == 0.0F) {
         value6 = 0.0F;
      } else {
         float value9 = (float)(number4 - number0) / (number4 - number5);
         float value10 = (float)(number4 - number1) / (number4 - number5);
         float value11 = (float)(number4 - number2) / (number4 - number5);
         if (number0 == number4) {
            value6 = value11 - value10;
         } else if (number1 == number4) {
            value6 = 2.0F + value9 - value11;
         } else {
            value6 = 4.0F + value10 - value9;
         }

         value6 *= 0.16666667F;
         if (value6 < 0.0F) {
            value6++;
         }
      }

      items3[0] = value6;
      items3[1] = value7;
      items3[2] = value8;
      return items3;
   }

   public static @Range(from = 0L, to = 1L) float method41(int number0) {
      return method42(method1(number0), method2(number0), method3(number0));
   }

   public static @Range(from = 0L, to = 1L) float method42(
      @Range(from = 0L, to = 255L) int number0, @Range(from = 0L, to = 255L) int number1, @Range(from = 0L, to = 255L) int number2
   ) {
      int number3 = Math.max(number0, number1);
      if (number2 > number3) {
         number3 = number2;
      }

      int number4 = Math.min(number0, number1);
      if (number2 < number4) {
         number4 = number2;
      }

      float value6;
      if (number3 != 0) {
         value6 = (float)(number3 - number4) / number3;
      } else {
         value6 = 0.0F;
      }

      float value5;
      if (value6 == 0.0F) {
         value5 = 0.0F;
      } else {
         float value7 = (float)(number3 - number0) / (number3 - number4);
         float value8 = (float)(number3 - number1) / (number3 - number4);
         float value9 = (float)(number3 - number2) / (number3 - number4);
         if (number0 == number3) {
            value5 = value9 - value8;
         } else if (number1 == number3) {
            value5 = 2.0F + value7 - value9;
         } else {
            value5 = 4.0F + value8 - value7;
         }

         value5 *= 0.16666667F;
         if (value5 < 0.0F) {
            value5++;
         }
      }

      return value5;
   }

   public static @Range(from = 0L, to = 1L) float method43(int number0) {
      return method44(method1(number0), method2(number0), method3(number0));
   }

   public static @Range(from = 0L, to = 1L) float method44(
      @Range(from = 0L, to = 255L) int number0, @Range(from = 0L, to = 255L) int number1, @Range(from = 0L, to = 255L) int number2
   ) {
      int number3 = Math.max(number0, number1);
      if (number2 > number3) {
         number3 = number2;
      }

      int number4 = Math.min(number0, number1);
      if (number2 < number4) {
         number4 = number2;
      }

      float value5;
      if (number3 != 0) {
         value5 = (float)(number3 - number4) / number3;
      } else {
         value5 = 0.0F;
      }

      return value5;
   }

   public static @Range(from = 0L, to = 1L) float method45(int number0) {
      return method46(method1(number0), method2(number0), method3(number0));
   }

   public static @Range(from = 0L, to = 1L) float method46(
      @Range(from = 0L, to = 255L) int number0, @Range(from = 0L, to = 255L) int number1, @Range(from = 0L, to = 255L) int number2
   ) {
      int number3 = Math.max(number0, number1);
      if (number2 > number3) {
         number3 = number2;
      }

      return number3 * 0.003921569F;
   }

   public static int method47(String text) {
      if (text.startsWith("#")) {
         text = text.substring(1);
      } else if (text.startsWith("0x") || text.startsWith("0X")) {
         text = text.substring(2);
      }

      if (text.length() == 6) {
         text = "FF" + text;
      } else if (text.length() != 8) {
         throw new IllegalArgumentException("Invalid hex string: " + text);
      }

      return (int)Long.parseLong(text, 16);
   }

   public static String method48(int number0, boolean flag) {
      return "#" + StringUtils.leftPad(Integer.toHexString(flag ? number0 : number0 & 16777215), flag ? 8 : 6, "0").toUpperCase(Locale.ROOT);
   }

   public static int method49(float value0, float value1, float value2) {
      return method50(value0, value1, value2, 0);
   }

   public static int method50(float value0, float value1, float value2, int number3) {
      Color color4 = Color.getHSBColor(value0, value1, value2);
      return method22(color4.getRGB(), number3);
   }

   public static int method51(int number0, int number1, float value2) {
      float value3 = method5(number0);
      float value4 = method6(number0);
      float value5 = method7(number0);
      float value6 = method8(number0);
      float value7 = method5(number1);
      float value8 = method6(number1);
      float value9 = method7(number1);
      float value10 = method8(number1);
      float value11 = MathUtils.lerp(value3, value7, value2);
      float value12 = MathUtils.lerp(value4, value8, value2);
      float value13 = MathUtils.lerp(value5, value9, value2);
      float value14 = MathUtils.lerp(value6, value10, value2);
      return method11(value11, value12, value13, value14);
   }

   @Generated
   private ColorUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
