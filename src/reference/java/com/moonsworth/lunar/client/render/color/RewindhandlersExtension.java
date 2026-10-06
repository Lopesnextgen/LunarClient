package com.moonsworth.lunar.client.render.color;

import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.TextColorSource;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.render.font.CachedFontImpl;
import com.moonsworth.lunar.client.framework.feature.Module;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import com.moonsworth.lunar.client.util.rewindhandlers.RewindhandlersExtension2;

public interface RewindhandlersExtension extends TextColorSource {
   default boolean method10() {
      return true;
   }

   default boolean method14() {
      return false;
   }

   int getColor();

   int method1(float var1);

   default @Range(from = 0L, to = 255L) int getRed() {
      return ThreadModuleDump23.method1(this.getColor());
   }

   default @Range(from = 0L, to = 255L) int getGreen() {
      return ThreadModuleDump23.method2(this.getColor());
   }

   default @Range(from = 0L, to = 255L) int getBlue() {
      return ThreadModuleDump23.method3(this.getColor());
   }

   default @Range(from = 0L, to = 255L) int getAlpha() {
      return this.method10() ? ThreadModuleDump23.method4(this.getColor()) : 255;
   }

   default @Range(from = 0L, to = 1L) float method4() {
      return ThreadModuleDump23.method41(this.getColor());
   }

   default @Range(from = 0L, to = 1L) float method5() {
      return ThreadModuleDump23.method43(this.getColor());
   }

   default @Range(from = 0L, to = 1L) float method6() {
      return ThreadModuleDump23.method45(this.getColor());
   }

   default float[] method7() {
      return ThreadModuleDump23.method39(this.getColor());
   }

   default float method8(MixinHelper_4 var1, String var2, float var3, float var4) {
      float var5 = ThreadModuleDump63.method10().bridge$getStringWidth(var2);
      return this.method14(var1, var2, var3 - var5 / 2.0F, var4, true);
   }

   default float method9(MixinHelper_4 var1, String var2, float var3, float var4) {
      return this.method10(var1, var2, var3, var4, false);
   }

   default float method10(MixinHelper_4 var1, String var2, float var3, float var4, boolean var5) {
      float var6 = ThreadModuleDump63.method10().bridge$getStringWidth(var2);
      return this.method14(var1, var2, var3 - var6 / 2.0F, var4, var5);
   }

   default void method11(MixinHelper_4 var1, String var2, float var3, float var4, boolean var5, @Nullable ColorOption var6) {
      if (var6 == null) {
         this.method10(var1, var2, var3, var4, var5);
      } else {
         float var7 = ThreadModuleDump63.method10().bridge$getStringWidth(var2);
         this.method16(var1, var2, var3 - var7 / 2.0F, var4, var5, var6);
      }
   }

   default float method12(MixinHelper_4 var1, String var2, float var3, float var4) {
      return this.method14(var1, var2, var3, var4, false);
   }

   default float method13(MixinHelper_4 var1, CachedFontImpl var2, String var3, float var4, float var5) {
      return this.method18(var1, var2, var3, var4, var5, false);
   }

   default float method14(MixinHelper_4 var1, String var2, float var3, float var4, boolean var5) {
      if (var2 == null) {
         return var3;
      }

      var1.method21(ThreadModuleDump63.method10(), var2, var3, var4, this.method21(var0 -> ThreadModuleDump23.method14(var0, 4)), var5);
      return var3 + ThreadModuleDump63.method10().bridge$getStringWidth(var2);
   }

   default float method15(MixinHelper_4 var1, Component var2, float var3, float var4, boolean var5) {
      if (var2 == null) {
         return var3;
      }

      var1.method15(ThreadModuleDump63.method10(), var2, var3, var4, this.method21(var0 -> ThreadModuleDump23.method14(var0, 4)), var5);
      return var3 + ThreadModuleDump63.method10().bridge$getStringWidth(var2);
   }

   default void method16(MixinHelper_4 var1, String var2, float var3, float var4, boolean var5, @Nullable ColorOption var6) {
      if (var2 != null) {
         if (var6 == null) {
            this.method17(var1, var2, var3, var4, var5);
         } else {
            boolean var7 = this.method14();
            if (this != var6) {
               float var8 = var3;
               char[] var9 = AdventureChatFormatting.getTextWithoutFormattingCodes(var2).toCharArray();

               for (int var10 = 0; var10 < var9.length; var10++) {
                  char var11 = var9[var10];
                  String var12 = String.valueOf(var11);
                  if (var10 == 0 || var10 == var9.length - 1) {
                     var1.method19(ThreadModuleDump63.method10(), var12, var8, var4, ThreadModuleDump23.method14(var6.method14(var8 + var4), 4), var5);
                     var8 += ThreadModuleDump63.method10().bridge$getStringWidth(var12);
                  } else if (var7) {
                     var1.method19(ThreadModuleDump63.method10(), var12, var8, var4, ThreadModuleDump23.method14(this.method1(var8 + var4), 4), var5);
                     var8 += ThreadModuleDump63.method10().bridge$getStringWidth(var12);
                  } else {
                     String var13 = var2.substring(1, var2.length() - 1);
                     var1.method19(ThreadModuleDump63.method10(), var13, var8, var4, ThreadModuleDump23.method14(this.method1(var8 + var4), 4), var5);
                     var8 += ThreadModuleDump63.method10().bridge$getStringWidth(var13);
                     var10 += var9.length - 3;
                  }
               }
            } else {
               var1.method19(ThreadModuleDump63.method10(), var2, var3, var4, ThreadModuleDump23.method14(this.method1(var3 + var4), 4), var5);
            }
         }
      }
   }

   default void method17(MixinHelper_4 var1, String var2, float var3, float var4, boolean var5) {
      if (var2 != null) {
         if (this.method14()) {
            float var6 = var3;

            for (char var10 : AdventureChatFormatting.getTextWithoutFormattingCodes(var2).toCharArray()) {
               String var11 = String.valueOf(var10);
               var1.method21(ThreadModuleDump63.method10(), var11, var6, var4, this.method21(var0 -> ThreadModuleDump23.method14(var0, 4)), var5);
               var6 += ThreadModuleDump63.method10().bridge$getStringWidth(var11);
            }
         } else {
            var1.method21(ThreadModuleDump63.method10(), var2, var3, var4, this.method21(var0 -> ThreadModuleDump23.method14(var0, 4)), var5);
         }
      }
   }

   default float method18(MixinHelper_4 var1, CachedFontImpl var2, String var3, float var4, float var5, boolean var6) {
      if (var3 == null) {
         return var4;
      }

      if (this.method14()) {
         float var7 = var4;

         for (char var11 : AdventureChatFormatting.getTextWithoutFormattingCodes(var3).toCharArray()) {
            var7 = var2.method17(var1, String.valueOf(var11), var7, var5, this.method1(var7 + var5), var6) + 1.0F;
            if (var6) {
               var7--;
            }
         }

         return var7;
      } else {
         return var2.method17(var1, var3, var4, var5, this.method1(var4 + var5), var6);
      }
   }

   default float method19(MixinHelper_4 var1, Bridge2_42 var2, float var3, float var4, boolean var5) {
      var1.method17(ThreadModuleDump63.method10(), var2, var3, var4, this.method21(var0 -> ThreadModuleDump23.method14(var0, 4)), var5);
      return var3 + ThreadModuleDump63.method10().bridge$getStringWidth(var2);
   }

   default void method20(MixinHelper_4 var1, float var2, float var3, float var4, float var5, boolean var6) {
      if (var6) {
         LcuiScreen.method119(var1, var2 + 1.0F, var3 + 1.0F, var4, var5, this.method21(ThreadModuleDump23::method32));
      }

      LcuiScreen.method119(var1, var2, var3, var4, var5, this);
   }

   default void method21(MixinHelper_4 var1, float var2, float var3, float var4, float var5) {
      LcuiScreen.method119(var1, var2, var3, var4, var5, this);
   }

   default void method22(MixinHelper_4 var1, Module var2, float var3, float var4, float var5, float var6, float var7) {
      LcuiScreen.method118(var1, var3, var4, var5, var6, var7, this);
   }

   static RewindhandlersExtension method23(int var0) {
      return new RewindhandlersExtension2(var0);
   }

   static RewindhandlersExtension method24(AdventureChatFormatting var0) {
      return method25(var0.getAdventureColor());
   }

   static RewindhandlersExtension method25(TextColor var0) {
      return method23(ThreadModuleDump23.method22(var0.value(), 255));
   }
}
