package com.moonsworth.lunar.client.framework.feature.overlay;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.RenderSystemBridge;
import com.moonsworth.lunar.client.mod.render.overlay.OverlayMod;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.framework.Ref;

public class HudColorOverride {
   private static final int field1 = 10526880;
   private static boolean field2;
   private static int field3 = -1;
   private static int field4;
   private static int field5;
   private static boolean field6;
   private static boolean field7;
   private static int textColor = -1;
   private static int field8;

   private HudColorOverride() {
   }

   public static void method1(int number0) {
      field2 = true;
      field3 = number0;
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

   public static int method5(int number0) {
      return method8() ? ColorUtils.method27(number0, field3) : number0;
   }

   public static void method6() {
      if (method8()) {
         RenderSystemBridge bridge120 = Bridge.method42();
         if (++field5 == 1) {
            field6 = ColorUtils.method4(field3) < 255 && !bridge120.method37();
            if (field6) {
               bridge120.method34();
            }
         }

         bridge120.method7(
            ColorUtils.method5(field3), ColorUtils.method6(field3), ColorUtils.method7(field3), ColorUtils.method8(field3)
         );
      }
   }

   public static void method7() {
      if (field5 != 0 && --field5 <= 0) {
         RenderSystemBridge bridge120 = Bridge.method42();
         bridge120.method7(1.0F, 1.0F, 1.0F, 1.0F);
         if (field6) {
            bridge120.method35();
            field6 = false;
         }
      }
   }

   private static boolean method8() {
      return field2 && field4 == 0;
   }

   public static void method9(int number0) {
      field7 = true;
      textColor = number0;
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

   public static int method14(int number0) {
      return method13() && number0 == 10526880 ? ColorUtils.method27(textColor, -6250336) & 16777215 : -1;
   }

   public static int method15(int number0) {
      if (!method13()) {
         return number0;
      }

      int number1 = method16(number0) ? ColorUtils.method32(textColor) : textColor;
      return number1 & 16777215 | number0 & 0xFF000000;
   }

   private static boolean method16(int number0) {
      return ColorUtils.method1(number0) <= 63 && ColorUtils.method2(number0) <= 63 && ColorUtils.method3(number0) <= 63;
   }

   public static void method17(Runnable runnable0) {
      OverlayMod overlaymod1 = Ref.method4().method40().method84();
      if (!field2 && overlaymod1.isMenuTintEnabled()) {
         method1(overlaymod1.getMenuTint());

         try {
            runnable0.run();
         } finally {
            method2();
         }
      } else {
         runnable0.run();
      }
   }
}
