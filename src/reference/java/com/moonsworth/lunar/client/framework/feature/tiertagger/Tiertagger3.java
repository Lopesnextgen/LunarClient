package com.moonsworth.lunar.client.framework.feature.tiertagger;

import com.moonsworth.lunar.client.framework.feature.tiertagger.rewindhandlers.Tiertagger6Iterator;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.mod.render.tiertagger.TierTagger;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;

class Tiertagger3 {
   private final TierTagger field1;

   public Component method1(int var1, int var2) {
      if (this.field1.field10.method9().isTierTests() && !(Boolean)this.field1.field15.get()) {
         String var3 = Tiertagger6Iterator.method7().method2(var1, var2);
         if (var3 != null) {
            return Component.text(var3).color(this.method2(var1, var2));
         }
      }

      return Component.text(this.method3(var1, var2)).color(this.method2(var1, var2));
   }

   public TextColor method2(int var1, int var2) {
      if (var1 == -1 || var2 == -1) {
         return TextColor.color(16777215);
      }

      if (ThreadModuleDump63.MC_VERSION <= 1) {
         return switch (var1) {
            case 1 -> NamedTextColor.YELLOW;
            case 2 -> var2 == 0 ? NamedTextColor.WHITE : NamedTextColor.GRAY;
            case 3 -> NamedTextColor.GOLD;
            case 4, 5 -> NamedTextColor.DARK_GRAY;
            default -> NamedTextColor.BLACK;
         };
      } else {
         ColorOption var3 = (ColorOption)this.field1.field38.get(this.method3(var1, var2));
         return var3 == null ? TextColor.color(16777215) : TextColor.color(var3.method13());
      }
   }

   public String method3(int var1, int var2) {
      String var3 = switch (var2) {
         case 0 -> "H";
         case 2 -> "M";
         default -> "L";
      };
      return var3 + "T" + var1;
   }

   @Generated
   public Tiertagger3(TierTagger var1) {
      this.field1 = var1;
   }
}
