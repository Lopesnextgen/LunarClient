package com.moonsworth.lunar.client.framework.feature.titles;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge10_2;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension9;
import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import com.moonsworth.lunar.client.render.particle.ClampUtils;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.HashSet;
import java.util.Set;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.title.Title.Times;

public class Titles2 {
   private final Set<Titles> field1 = new HashSet<>();

   public void method1(Titles var1) {
      this.field1.add(var1);
   }

   public void method2(MixinHelper_4 var1, Title title2, int value2, float value3, float value4, float value5, float value6, boolean flag, boolean flag2, boolean flag3) {
      if (title2 != null) {
         Bridge5_12 var11 = ThreadModuleDump63.method3();
         Bridge5Extension9 var12 = var11.bridge$getGuiIngame();
         float var13 = value2 - var1.method43();
         int var14 = 255;
         if (!flag) {
            Times var15 = title2.times();
            int var16 = (int)(var15.fadeIn().toMillis() / 50L);
            int var17 = (int)(var15.stay().toMillis() / 50L);
            int var18 = (int)(var15.fadeOut().toMillis() / 50L);
            if (value2 > var18 + var17) {
               float var19 = var16 + var17 + var18 - var13;
               var14 = (int)(var19 * 255.0F / var16);
            }

            if (value2 <= var18) {
               var14 = (int)(var13 * 255.0F / var18);
            }

            var14 = ClampUtils.clamp(var14, 0, 255);
         }

         if (var14 > 8) {
            Bridge10_2 var23 = ThreadModuleDump63.method10();
            var1.push();
            var1.method38(value3 + value5 / 2.0F, value4 + value6 / 2.0F, 0.0F);
            float var24 = AdventureTextBridge.getTextWidth(title2.subtitle(), var23) / 2.0F;
            var1.scale(2.0F, 2.0F, 2.0F);
            float var26 = 1.0F;
            if (!flag && title2.equals(var12.bridge$getTitle())) {
               var26 = var12.bridge$getTitleScale();
            }

            var1.scale(var26, var26, var26);
            int var27 = var14 << 24 & 0xFF000000;
            if (flag3) {
               int var28 = 16777215;

               for (Titles var21 : this.field1) {
                  Integer var22 = var21.method1(null, title2.subtitle());
                  if (var22 != null) {
                     var28 = var22;
                     break;
                  }
               }

               var28 &= 16777215;
               Object var32 = title2.subtitle();
               if (var28 != 16777215) {
                  var32 = Component.text(AdventureChatFormatting.getTextWithoutFormattingCodes(AdventureTextBridge.getTextContent((Component)var32)));
               }

               if (!Bridge.getMinecraftVersion().method21()) {
                  var1.method11(var23, (Component)var32, -var24, 7.0F, var28 | var27, true);
               } else {
                  if (var28 == 16777215) {
                     var28 = title2.subtitle().colorIfAbsent(NamedTextColor.WHITE).color().value();
                  }

                  var1.method11(var23, (Component)var32, -var24, 7.0F, var28 | var27, true);
               }
            }

            if (flag2) {
               var1.scale(2.0F, 2.0F, 2.0F);
               var24 = AdventureTextBridge.getTextWidth(title2.title(), var23) / 2.0F;
               int var30 = 16777215;

               for (Titles var35 : this.field1) {
                  Integer var36 = var35.method1(title2.title(), null);
                  if (var36 != null) {
                     var30 = var36;
                     break;
                  }
               }

               var30 &= 16777215;
               Object var34 = title2.title();
               if (var30 != 16777215) {
                  var34 = Component.text(AdventureChatFormatting.getTextWithoutFormattingCodes(AdventureTextBridge.getTextContent((Component)var34)));
               }

               if (!Bridge.getMinecraftVersion().method21()) {
                  var1.method11(var23, (Component)var34, -var24, -8.75F, var30 | var27, true);
               } else {
                  if (var30 == 16777215) {
                     var30 = title2.title().colorIfAbsent(NamedTextColor.WHITE).color().value();
                  }

                  var1.method11(var23, (Component)var34, -var24, -8.75F, var30 | var27, true);
               }
            }

            var1.pop();
         }
      }
   }
}
