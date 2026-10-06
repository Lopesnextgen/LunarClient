package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.Ref;
import net.kyori.adventure.text.Component;

public class HologramTextRenderer {
   public HologramTextRenderer() {
   }

   public static void method1(MixinHelper_4 mixinhelper_40, String text1, float value2, float value3, float value4, HologramTextRenderer.Type type5) {
      method3(mixinhelper_40, text1, value2, value3, value4, type5, -1);
   }

   public static void method2(MixinHelper_4 mixinhelper_40, Component component1, float value2, float value3, float value4, HologramTextRenderer.Type type5) {
      mixinhelper_40.push();
      mixinhelper_40.scale(value4, value4, 1.0F);
      if (type5 == HologramTextRenderer.Type.BORDER) {
         String text6 = TextBridge.getTextContent(component1);
         mixinhelper_40.method19(Ref.method10(), text6, value2 / value4 - 1.0F, value3 / value4, -16777216, true);
         mixinhelper_40.method19(Ref.method10(), text6, value2 / value4 + 1.0F, value3 / value4, -16777216, true);
         mixinhelper_40.method19(Ref.method10(), text6, value2 / value4, value3 / value4 - 1.0F, -16777216, true);
         mixinhelper_40.method19(Ref.method10(), text6, value2 / value4, value3 / value4 + 1.0F, -16777216, true);
      }

      mixinhelper_40.method11(Ref.method10(), component1, value2 / value4, value3 / value4, -1, true);
      mixinhelper_40.pop();
      mixinhelper_40.method44(arg0x -> arg0x.method29().method33());
   }

   private static void method3(MixinHelper_4 mixinhelper_40, String text1, float value2, float value3, float value4, HologramTextRenderer.Type type5, int value) {
      mixinhelper_40.push();
      mixinhelper_40.scale(value4, value4, 1.0F);
      if (type5 == HologramTextRenderer.Type.BORDER) {
         String text7 = ChatFormatting.getTextWithoutFormattingCodes(text1);
         mixinhelper_40.method19(Ref.method10(), text7, value2 / value4 - 1.0F, value3 / value4, -16777216, true);
         mixinhelper_40.method19(Ref.method10(), text7, value2 / value4 + 1.0F, value3 / value4, -16777216, true);
         mixinhelper_40.method19(Ref.method10(), text7, value2 / value4, value3 / value4 - 1.0F, -16777216, true);
         mixinhelper_40.method19(Ref.method10(), text7, value2 / value4, value3 / value4 + 1.0F, -16777216, true);
      }

      mixinhelper_40.method19(Ref.method10(), text1, value2 / value4, value3 / value4, value, true);
      mixinhelper_40.pop();
      mixinhelper_40.method44(arg0x -> arg0x.method29().method33());
   }

   public enum Type {
      NORMAL,
      SHADOW,
      BORDER;

      Type() {
      }
   }
}
