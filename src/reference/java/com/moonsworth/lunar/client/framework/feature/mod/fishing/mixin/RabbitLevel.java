package com.moonsworth.lunar.client.framework.feature.mod.fishing.mixin;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.util.math.NumberUtils;
import com.moonsworth.lunar.client.util.text.RomanNumeralParser;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;

public class RabbitLevel {
   private static final Pattern field1 = Pattern.compile("^Rabbit \\w+ - \\[(?<level>\\d{1,3})].*$");
   private final int field2;
   private final boolean field3;

   public String method1() {
      return this.field3 ? this.method2(this.field2) + this.field2 : this.field2 + "";
   }

   private String method2(int number1) {
      if (number1 >= 220) {
         return ChatFormatting.AQUA.toString();
      } else if (number1 >= 200) {
         return ChatFormatting.LIGHT_PURPLE.toString();
      } else if (number1 >= 175) {
         return ChatFormatting.GOLD.toString();
      } else if (number1 >= 125) {
         return ChatFormatting.DARK_PURPLE.toString();
      } else if (number1 >= 75) {
         return ChatFormatting.BLUE.toString();
      } else {
         return number1 >= 10 ? ChatFormatting.GREEN.toString() : ChatFormatting.WHITE.toString();
      }
   }

   public static Optional<RabbitLevel> method3(ItemStackBridge bridgeextension_40) {
      if (bridgeextension_40 != null && !bridgeextension_40.bridge$isEmpty()) {
         String text1 = ChatFormatting.getTextWithoutFormattingCodes(bridgeextension_40.bridge$getDisplayName());
         Matcher matcher2 = field1.matcher(text1);
         if (matcher2.matches()) {
            int number5 = NumberUtils.method3(matcher2.group("level"));
            return Optional.of(new RabbitLevel(number5, true));
         } else {
            String text3 = text1.substring(text1.lastIndexOf(" ") + 1);
            if (RomanNumeralParser.isRomanNumeral(text3)) {
               int number4 = RomanNumeralParser.romanToInt(text3);
               return Optional.of(new RabbitLevel(number4, false));
            } else {
               return Optional.empty();
            }
         }
      } else {
         return Optional.empty();
      }
   }

   @Generated
   public RabbitLevel(int number1, boolean flag) {
      this.field2 = number1;
      this.field3 = flag;
   }

   @Generated
   public int getLevel() {
      return this.field2;
   }

   @Generated
   public boolean method4() {
      return this.field3;
   }
}
