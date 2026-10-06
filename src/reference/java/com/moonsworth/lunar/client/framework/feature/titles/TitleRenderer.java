package com.moonsworth.lunar.client.framework.feature.titles;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.GuiIngameBridge;
import com.moonsworth.lunar.bridge.MinecraftBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.render.particle.ClampUtils;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.HashSet;
import java.util.Set;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.title.Title.Times;

public class TitleRenderer {
   private final Set<Titles> field1 = new HashSet<>();

   public TitleRenderer() {
   }

   public void method1(Titles titles1) {
      this.field1.add(titles1);
   }

   public void method2(MixinHelper_4 mixinhelper_41, Title title2, int value2, float value3, float value4, float value5, float value6, boolean flag, boolean flag2, boolean flag3) {
      if (title2 != null) {
         MinecraftBridge bridge5_1211 = Ref.method3();
         GuiIngameBridge bridge5extension912 = bridge5_1211.bridge$getGuiIngame();
         float value13 = value2 - mixinhelper_41.method43();
         int number14 = 255;
         if (!flag) {
            Times times15 = title2.times();
            int number16 = (int)(times15.fadeIn().toMillis() / 50L);
            int number17 = (int)(times15.stay().toMillis() / 50L);
            int number18 = (int)(times15.fadeOut().toMillis() / 50L);
            if (value2 > number18 + number17) {
               float value19 = number16 + number17 + number18 - value13;
               number14 = (int)(value19 * 255.0F / number16);
            }

            if (value2 <= number18) {
               number14 = (int)(value13 * 255.0F / number18);
            }

            number14 = ClampUtils.clamp(number14, 0, 255);
         }

         if (number14 > 8) {
            Bridge10_2 bridge10_223 = Ref.method10();
            mixinhelper_41.push();
            mixinhelper_41.method38(value3 + value5 / 2.0F, value4 + value6 / 2.0F, 0.0F);
            float value24 = TextBridge.getTextWidth(title2.subtitle(), bridge10_223) / 2.0F;
            mixinhelper_41.scale(2.0F, 2.0F, 2.0F);
            float value26 = 1.0F;
            if (!flag && title2.equals(bridge5extension912.bridge$getTitle())) {
               value26 = bridge5extension912.bridge$getTitleScale();
            }

            mixinhelper_41.scale(value26, value26, value26);
            int number27 = number14 << 24 & 0xFF000000;
            if (flag3) {
               int number28 = 16777215;

               for (Titles titles21 : this.field1) {
                  Integer number22 = titles21.method1(null, title2.subtitle());
                  if (number22 != null) {
                     number28 = number22;
                     break;
                  }
               }

               number28 &= 16777215;
               Object obj32 = title2.subtitle();
               if (number28 != 16777215) {
                  obj32 = Component.text(ChatFormatting.getTextWithoutFormattingCodes(TextBridge.getTextContent((Component)obj32)));
               }

               if (!Bridge.getMinecraftVersion().method21()) {
                  mixinhelper_41.method11(bridge10_223, (Component)obj32, -value24, 7.0F, number28 | number27, true);
               } else {
                  if (number28 == 16777215) {
                     number28 = title2.subtitle().colorIfAbsent(NamedTextColor.WHITE).color().value();
                  }

                  mixinhelper_41.method11(bridge10_223, (Component)obj32, -value24, 7.0F, number28 | number27, true);
               }
            }

            if (flag2) {
               mixinhelper_41.scale(2.0F, 2.0F, 2.0F);
               value24 = TextBridge.getTextWidth(title2.title(), bridge10_223) / 2.0F;
               int number30 = 16777215;

               for (Titles titles35 : this.field1) {
                  Integer number36 = titles35.method1(title2.title(), null);
                  if (number36 != null) {
                     number30 = number36;
                     break;
                  }
               }

               number30 &= 16777215;
               Object obj34 = title2.title();
               if (number30 != 16777215) {
                  obj34 = Component.text(ChatFormatting.getTextWithoutFormattingCodes(TextBridge.getTextContent((Component)obj34)));
               }

               if (!Bridge.getMinecraftVersion().method21()) {
                  mixinhelper_41.method11(bridge10_223, (Component)obj34, -value24, -8.75F, number30 | number27, true);
               } else {
                  if (number30 == 16777215) {
                     number30 = title2.title().colorIfAbsent(NamedTextColor.WHITE).color().value();
                  }

                  mixinhelper_41.method11(bridge10_223, (Component)obj34, -value24, -8.75F, number30 | number27, true);
               }
            }

            mixinhelper_41.pop();
         }
      }
   }
}
