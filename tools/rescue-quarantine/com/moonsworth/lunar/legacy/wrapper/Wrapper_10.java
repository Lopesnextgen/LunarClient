package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5;
import com.moonsworth.lunar.bridge.Bridge10.Data;
import com.moonsworth.lunar.bridge.horsestats.Horsestats14;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump86;
import it.unimi.dsi.fastutil.floats.FloatArrayList;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import net.kyori.adventure.text.Component;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class Wrapper_10 {
   private static void method1(float var0, float var1, float var2, float var3, FloatArrayList var4, int var5) {
      float var6 = Float.intBitsToFloat(Integer.reverseBytes(var5 << 8 | var5 >>> 24));
      int var7 = var4.size();
      int var8 = var7 + 24;
      var4.ensureCapacity(var8);
      var4.size(var8);
      float[] var9 = var4.elements();
      var9[var7] = var0;
      var9[var7 + 1] = var1;
      var9[var7 + 2] = var2;
      var9[var7 + 3] = 0.0F;
      var9[var7 + 4] = 0.0F;
      var9[var7 + 5] = var6;
      var9[var7 + 6] = var0 + var3;
      var9[var7 + 7] = var1;
      var9[var7 + 8] = var2;
      var9[var7 + 9] = 0.0F;
      var9[var7 + 10] = 0.0F;
      var9[var7 + 11] = var6;
      var9[var7 + 12] = var0 + var3;
      var9[var7 + 13] = var1 - 1.0F;
      var9[var7 + 14] = var2;
      var9[var7 + 15] = 0.0F;
      var9[var7 + 16] = 0.0F;
      var9[var7 + 17] = var6;
      var9[var7 + 18] = var0;
      var9[var7 + 19] = var1 - 1.0F;
      var9[var7 + 20] = var2;
      var9[var7 + 21] = 0.0F;
      var9[var7 + 22] = 0.0F;
      var9[var7 + 23] = var6;
   }

   private static void method2(float var0, float var1, float var2, Data var3, boolean var4, FloatArrayList var5, int var6) {
      int var7 = var3.index();
      int var8 = var7 % 16 * 8;
      int var9 = var7 / 16 * 8;
      float var10 = var4 ? 1.0F : 0.0F;
      float var11 = var3.method2();
      float var12;
      if (Bridge.method5().isPresent()) {
         var12 = 7.99F;
      } else {
         var12 = var11 - 0.01F;
      }

      float var13 = Float.intBitsToFloat(Integer.reverseBytes(var6 << 8 | var6 >>> 24));
      int var14 = var5.size();
      int var15 = var14 + 24;
      var5.ensureCapacity(var15);
      var5.size(var15);
      float[] var16 = var5.elements();
      var16[var14] = var0 + var10;
      var16[var14 + 1] = var1;
      var16[var14 + 2] = var2;
      var16[var14 + 3] = var8 / 128.0F;
      var16[var14 + 4] = var9 / 128.0F;
      var16[var14 + 5] = var13;
      var16[var14 + 6] = var0 - var10;
      var16[var14 + 7] = var1 + 7.99F;
      var16[var14 + 8] = var2;
      var16[var14 + 9] = var8 / 128.0F;
      var16[var14 + 10] = (var9 + 7.99F) / 128.0F;
      var16[var14 + 11] = var13;
      var16[var14 + 12] = var0 + var12 - 1.0F - var10;
      var16[var14 + 13] = var1 + 7.99F;
      var16[var14 + 14] = var2;
      var16[var14 + 15] = (var8 + var12 - 1.0F) / 128.0F;
      var16[var14 + 16] = (var9 + 7.99F) / 128.0F;
      var16[var14 + 17] = var13;
      var16[var14 + 18] = var0 + var12 - 1.0F + var10;
      var16[var14 + 19] = var1;
      var16[var14 + 20] = var2;
      var16[var14 + 21] = (var8 + var12 - 1.0F) / 128.0F;
      var16[var14 + 22] = var9 / 128.0F;
      var16[var14 + 23] = var13;
   }

   public static void method3(float var0, float var1, float var2, int var3, Data var4, boolean var5, FloatArrayList var6, int var7) {
      float var8 = var4.method1();
      float var9 = var4.method2() + 1.0F;
      float var10 = var3 % 16 * 16 + var8;
      float var11 = (var3 & 0xFF) / 16 * 16;
      float var12 = var9 - var8 - 0.02F;
      float var13 = var5 ? 1.0F : 0.0F;
      float var14 = Float.intBitsToFloat(Integer.reverseBytes(var7 << 8 | var7 >>> 24));
      int var15 = var6.size();
      int var16 = var15 + 24;
      var6.ensureCapacity(var16);
      var6.size(var16);
      float[] var17 = var6.elements();
      var17[var15] = var0 + var13;
      var17[var15 + 1] = var1;
      var17[var15 + 2] = var2;
      var17[var15 + 3] = var10 / 256.0F;
      var17[var15 + 4] = var11 / 256.0F;
      var17[var15 + 5] = var14;
      var17[var15 + 6] = var0 - var13;
      var17[var15 + 7] = var1 + 7.99F;
      var17[var15 + 8] = var2;
      var17[var15 + 9] = var10 / 256.0F;
      var17[var15 + 10] = (var11 + 15.98F) / 256.0F;
      var17[var15 + 11] = var14;
      var17[var15 + 12] = var0 + var12 / 2.0F - var13;
      var17[var15 + 13] = var1 + 7.99F;
      var17[var15 + 14] = var2;
      var17[var15 + 15] = (var10 + var12) / 256.0F;
      var17[var15 + 16] = (var11 + 15.98F) / 256.0F;
      var17[var15 + 17] = var14;
      var17[var15 + 18] = var0 + var12 / 2.0F + var13;
      var17[var15 + 19] = var1;
      var17[var15 + 20] = var2;
      var17[var15 + 21] = (var10 + var12) / 256.0F;
      var17[var15 + 22] = var11 / 256.0F;
      var17[var15 + 23] = var14;
   }

   private static void method4(float var0, float var1, float var2, char var3, Data var4, boolean var5, FloatArrayList var6, int var7, boolean var8) {
      if (var3 != ' ' && var3 != 160) {
         if (var8) {
            method3(var0, var1, var2, var3, var4, var5, var6, var7);
         } else {
            method2(var0, var1, var2, var4, var5, var6, var7);
         }
      }
   }

   public static List<Wrapper$Data3> method5(
      FloatBuffer var0, float var1, float var2, Component var3, boolean var4, int var5, int var6, boolean var7, boolean var8
   ) {
      var6 = (var6 & 0xFF) << 24;
      String var9 = Bridge5.asLegacyString(var3);
      if (var8) {
         var9 = ThreadModuleDump86.getTextWithoutFormattingCodes(var9);
      }

      Data[] var10 = ThreadModuleDump63.method3().bridge$getFontRenderer().method20(var9);
      HashMap var11 = new HashMap();
      FloatArrayList var12 = null;
      FloatArrayList var13 = null;
      boolean var14 = Minecraft.getMinecraft().fontRendererObj.unicodeFlag;
      int[] var15 = Minecraft.getMinecraft().fontRendererObj.colorCode;
      int var16 = Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT;
      boolean var17 = false;
      boolean var18 = false;
      boolean var19 = false;
      boolean var20 = false;
      boolean var21 = false;
      int var22 = 16777215 & var5 | var6;
      int var23 = (16579836 & var5) >> 2 | var6;
      int var24 = 0;

      for (int var25 = var9.length(); var24 < var25; var24++) {
         char var26 = (char)var9.codePointAt(var24);
         if (var26 == 167 && var24 != var25 - 1) {
            char var48 = (char)var9.codePointAt(var24 + 1);
            int var50 = "0123456789abcdefklmnor".indexOf(var48);
            if (var50 < 16) {
               var20 = false;
               var17 = false;
               var18 = false;
               var21 = false;
               var19 = false;
               if (var50 < 0) {
                  var50 = 15;
               }

               var22 = var15[var50] & 16777215 | var6;
               var23 = var15[var50 + 16] & 16777215 | var6;
            } else if (var50 == 16) {
               var20 = true;
            } else if (var50 == 17) {
               var17 = true;
            } else if (var50 == 18) {
               var18 = true;
            } else if (var50 == 19) {
               var21 = true;
            } else if (var50 == 20) {
               var19 = true;
            } else {
               var20 = false;
               var17 = false;
               var18 = false;
               var21 = false;
               var19 = false;
               var22 = 16777215 & var5 | var6;
               var23 = (16579836 & var5) >> 2 | var6;
            }

            var24++;
         } else {
            Data var27 = var10[var24];
            float var28;
            if (!Bridge.method5().isPresent()) {
               var28 = var14 ? 0.5F : 1.0F;
            } else {
               var28 = var27.method3() && !var14 ? ThreadModuleDump63.method3().bridge$getFontRenderer().bridge$boldWidth() : 0.5F;
            }

            boolean var29 = (var26 == 0 || !var27.method3() || var14) && var4;
            float var30 = var29 ? -var28 : 0.0F;
            boolean var32 = false;
            FloatArrayList var31;
            if (var27.method3()) {
               if (var13 == null) {
                  var13 = new FloatArrayList();
               }

               var31 = var13;
            } else {
               var31 = var11.computeIfAbsent(var26 / 256, var0x -> new FloatArrayList());
               var32 = true;
            }

            float var33 = var7 && var4 ? -0.5F : 0.0F;
            if (var4) {
               method4(var1 + 1.0F + var30, var2 + 1.0F + var30, 0.0F, var26, var27, var19, var31, var23, var32);
               if (var17) {
                  method4(var1 + 1.0F + var28 + var30, var2 + 1.0F + var30, 0.0F, var26, var27, var19, var31, var23, var32);
               }
            }

            method4(var1, var2, var33, var26, var27, var19, var31, var22, var32);
            if (var17) {
               method4(var1 + var28, var2, var33, var26, var27, var19, var31, var22, var32);
            }

            if ((var21 || var18) && var12 == null) {
               var12 = new FloatArrayList();
            }

            float var34;
            if (var27.method3()) {
               var34 = var27.method2();
            } else {
               var34 = (var27.method2() - var27.method1() + 1.0F) / 2.0F + 1.0F;
            }

            float var35 = var17 ? 1.0F : 0.0F;
            if (Bridge.method5().isPresent()) {
               var35 = var17 ? var28 : 0.0F;
            }

            if (var18) {
               float var36 = var16 / 2;
               if (var4) {
                  method1(var1 + 1.0F, var2 + 1.0F + var36, 0.0F, var34 + var35, var12, var23);
               }

               method1(var1, var2 + var36, var33, var34 + var35, var12, var22);
            }

            if (var21) {
               if (var4) {
                  method1(var1, var2 + 1.0F + var16, 0.0F, var34 + var35 + 1.0F, var12, var23);
               }

               method1(var1 - 1.0F, var2 + var16, var33, var34 + var35 + 1.0F, var12, var22);
            }

            if (Bridge.method5().isPresent()) {
               var1 += var34 + var35;
            } else {
               var1 += (int)(var34 + var35);
            }
         }
      }

      ArrayList var41 = new ArrayList();
      if (var13 != null) {
         int var42 = var0.position() / 6;
         int var45 = var13.size() / 6;
         var0.put(var13.elements(), 0, var13.size());
         var41.add(new Wrapper$Data3(var42, var45, (Horsestats14)Minecraft.getMinecraft().fontRendererObj.locationFontTexture));
      }

      for (Entry var46 : var11.entrySet()) {
         int var49 = (Integer)var46.getKey();
         FloatArrayList var51 = (FloatArrayList)var46.getValue();
         ResourceLocation var52 = Minecraft.getMinecraft().fontRendererObj.getUnicodePageLocation(var49);
         int var53 = var0.position() / 6;
         int var54 = var51.size() / 6;
         var0.put(var51.elements(), 0, var51.size());
         var41.add(new Wrapper$Data3(var53, var54, (Horsestats14)var52));
      }

      if (var12 != null) {
         int var44 = var0.position() / 6;
         int var47 = var12.size() / 6;
         var0.put(var12.elements(), 0, var12.size());
         var41.add(new Wrapper$Data3(var44, var47, null));
      }

      return var41;
   }
}
