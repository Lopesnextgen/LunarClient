package com.moonsworth.lunar.client.ui.hud;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.util.math.Vector2f;
import java.util.List;

public class HudRowLayout {
   public static final int field1 = 3;

   public HudRowLayout() {
   }

   public static void method1(MixinHelper_4 mixinhelper_40, float value1, float value2, float value3, HudRowAlignment gui2extension4, List<HudRow> list5) {
      for (HudRow hitbox27 : list5) {
         switch (gui2extension4) {
            case LEFT:
               float value10 = value1 + (hitbox27 instanceof HorizontalHudRow ? ((HorizontalHudRow)hitbox27).padding : 0);
               hitbox27.method1(mixinhelper_40, value10, value2);
               break;
            case CENTER:
               float value9 = value1 + value3 / 2.0F - hitbox27.method2() / 2.0F + (hitbox27 instanceof HorizontalHudRow ? ((HorizontalHudRow)hitbox27).padding / 2 : 0);
               hitbox27.method1(mixinhelper_40, value9, value2);
               break;
            case RIGHT:
               float value8 = value1 + value3 - hitbox27.method2();
               hitbox27.method1(mixinhelper_40, value8, value2);
         }

         value2 += hitbox27.method3();
      }
   }

   public static Vector2f method2(
      MixinHelper_4 mixinhelper_40,
      MixinCore9Extension mixincore9extension1,
      float value2,
      float value3,
      HudRowAlignment gui2extension4,
      boolean flag5,
      ColorOption lightingextension42226,
      boolean flag7,
      float value8,
      ColorOption lightingextension42229,
      List<HudRow> list10
   ) {
      float value11 = 6.0F;
      int number12 = 6;

      for (HudRow hitbox214 : list10) {
         number12 = (int)(number12 + hitbox214.method3());
         float value15 = 6.0F + hitbox214.method2();
         if (value15 > value11) {
            value11 = value15;
         }
      }

      if (flag5) {
         lightingextension42226.method11(mixinhelper_40, value2, value3, value11, number12);
         if (flag7) {
            lightingextension42229.method11(mixinhelper_40, mixincore9extension1, value2, value3, value11, number12, value8);
         }
      }

      method1(mixinhelper_40, value2 + 3.0F, value3 + 3.0F, value11 - 6.0F, gui2extension4, list10);
      Vector2f threadmoduledump6016 = new Vector2f();
      threadmoduledump6016.x = value11;
      threadmoduledump6016.y = number12;
      return threadmoduledump6016;
   }

   public static HudRowAlignment method3(boolean flag0, HudAnchor gui2extension21, HudRowAlignment gui2extension2) {
      if (!flag0) {
         return gui2extension2;
      }

      return switch (gui2extension21.getHorizontal()) {
         case LEFT -> HudRowAlignment.LEFT;
         case RIGHT -> HudRowAlignment.RIGHT;
         default -> HudRowAlignment.CENTER;
      };
   }

   public static HorizontalHudRow method4(int number0, HudRow... items1) {
      return new HorizontalHudRow(items1, number0);
   }

   public static TextHudRow method5(String text0, ColorOption lightingextension42221, boolean flag2) {
      return new TextHudRow(text0, lightingextension42221, flag2);
   }

   public static ItemIconHudRow method6(ItemStackBridge bridgeextension_40) {
      return new ItemIconHudRow(bridgeextension_40);
   }

   public static SpacerHudRow method7(int number0) {
      return new SpacerHudRow(number0);
   }
}
