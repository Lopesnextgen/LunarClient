package com.moonsworth.lunar.client.framework.feature.tiertagger;

import com.moonsworth.lunar.client.framework.feature.tiertagger.rewindhandlers.TiertestsMetadata;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.mod.render.tiertagger.TierTagger;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;

class TierBadgeFormatter {
   private final TierTagger field1;

   public Component method1(int number1, int number2) {
      if (this.field1.field10.method9().isTierTests() && !(Boolean)this.field1.field15.get()) {
         String text3 = TiertestsMetadata.method7().method2(number1, number2);
         if (text3 != null) {
            return Component.text(text3).color(this.method2(number1, number2));
         }
      }

      return Component.text(this.method3(number1, number2)).color(this.method2(number1, number2));
   }

   public TextColor method2(int number1, int number2) {
      if (number1 == -1 || number2 == -1) {
         return TextColor.color(16777215);
      }

      if (Ref.MC_VERSION <= 1) {
         return switch (number1) {
            case 1 -> NamedTextColor.YELLOW;
            case 2 -> number2 == 0 ? NamedTextColor.WHITE : NamedTextColor.GRAY;
            case 3 -> NamedTextColor.GOLD;
            case 4, 5 -> NamedTextColor.DARK_GRAY;
            default -> NamedTextColor.BLACK;
         };
      } else {
         ColorOption lightingextension42223 = (ColorOption)this.field1.field38.get(this.method3(number1, number2));
         return lightingextension42223 == null ? TextColor.color(16777215) : TextColor.color(lightingextension42223.method13());
      }
   }

   public String method3(int number1, int number2) {
      String text3 = switch (number2) {
         case 0 -> "H";
         case 2 -> "M";
         default -> "L";
      };
      return text3 + "T" + number1;
   }

   @Generated
   public TierBadgeFormatter(TierTagger tiertagger1) {
      this.field1 = tiertagger1;
   }
}
