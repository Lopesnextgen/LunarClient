package com.moonsworth.lunar.client.framework.feature.gui;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderSystemBridge;
import com.moonsworth.lunar.client.mod.render.overlay.OverlayMod;
import com.moonsworth.lunar.client.util.ThreadModuleDump23;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;

public class Gui2 {
   private static final int field1 = 10526880;
   private static boolean field2;
   private static int field3 = -1;
   private static int field4;
   private static int field5;
   private static boolean field6;
   private static boolean field7;
   private static int textColor = -1;
   private static int field8;

   private Gui2() {
   }

   public static void method1(int var0) {
      field2 = true;
      field3 = var0;
      field4 = 0;
   }

   public static void method2() {
      field2 = false;
      field3 = -1;
      field4 = 0;
      if (field5 > 0) {
         field5 = 1;
         method7();
      }
   }

   public static void method3() {
      if (field2) {
         field4++;
      }
   }

   public static void method4() {
      if (field2 && field4 > 0) {
         field4--;
      }
   }

   public static int method5(int var0) {
      return method8() ? ThreadModuleDump23.method27(var0, field3) : var0;
   }

   public static void method6() {
      if (method8()) {
         RenderSystemBridge var0 = Bridge.method42();
         if (++field5 == 1) {
            field6 = ThreadModuleDump23.method4(field3) < 255 && !var0.method37();
            if (field6) {
               var0.method34();
            }
         }

         var0.method7(
            ThreadModuleDump23.method5(field3), ThreadModuleDump23.greenFloat(field3), ThreadModuleDump23.method7(field3), ThreadModuleDump23.method8(field3)
         );
      }
   }

   public static void method7() {
      if (field5 != 0 && --field5 <= 0) {
         RenderSystemBridge var0 = Bridge.method42();
         var0.method7(1.0F, 1.0F, 1.0F, 1.0F);
         if (field6) {
            var0.method35();
            field6 = false;
         }
      }
   }

   private static boolean method8() {
      return field2 && field4 == 0;
   }

   public static void method9(int var0) {
      field7 = true;
      textColor = var0;
      field8 = 0;
   }

   public static void method10() {
      field7 = false;
      textColor = -1;
      field8 = 0;
   }

   public static void method11() {
      if (field7) {
         field8++;
      }
   }

   public static void method12() {
      if (field7 && field8 > 0) {
         field8--;
      }
   }

   public static boolean method13() {
      return field7 && field8 == 0;
   }

   public static int method14(int var0) {
      return method13() && var0 == 10526880 ? ThreadModuleDump23.method27(textColor, -6250336) & 16777215 : -1;
   }

   public static int method15(int var0) {
      if (!method13()) {
         return var0;
      }

      int var1 = method16(var0) ? ThreadModuleDump23.method32(textColor) : textColor;
      return var1 & 16777215 | var0 & 0xFF000000;
   }

   private static boolean method16(int var0) {
      return ThreadModuleDump23.method1(var0) <= 63 && ThreadModuleDump23.method2(var0) <= 63 && ThreadModuleDump23.method3(var0) <= 63;
   }

   public static void method17(Runnable var0) {
      OverlayMod var1 = ThreadModuleDump63.method4().method40().method84();
      if (!field2 && var1.isMenuTintEnabled()) {
         method1(var1.getMenuTint());

         try {
            var0.run();
         } finally {
            method2();
         }
      } else {
         var0.run();
      }
   }
}
