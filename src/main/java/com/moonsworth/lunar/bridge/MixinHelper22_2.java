package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.AdventureChatFormatting;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class MixinHelper22_2 implements MixinHelper2_11 {
   private final StringBuilder field1 = new StringBuilder();
   private Style field2 = Style.empty();

   private void method1(AdventureChatFormatting var1) {
      this.field1.append('§');
      this.field1.append(var1.getFormattingCode());
   }

   private void method2(Style var1) {
      TextColor var2 = var1.color();
      if (var2 != null) {
         AdventureChatFormatting var3;
         if (var2 instanceof NamedTextColor var4) {
            var3 = AdventureChatFormatting.getFromAdventure(var4);
         } else {
            var3 = AdventureChatFormatting.nearestTo(var2.value());
         }

         if (var3 != null) {
            this.method1(var3);
         }
      } else {
         this.method1(AdventureChatFormatting.RESET);
      }

      if (var1.hasDecoration(TextDecoration.BOLD)) {
         this.method1(AdventureChatFormatting.BOLD);
      }

      if (var1.hasDecoration(TextDecoration.ITALIC)) {
         this.method1(AdventureChatFormatting.ITALIC);
      }

      if (var1.hasDecoration(TextDecoration.OBFUSCATED)) {
         this.method1(AdventureChatFormatting.OBFUSCATED);
      }

      if (var1.hasDecoration(TextDecoration.STRIKETHROUGH)) {
         this.method1(AdventureChatFormatting.STRIKETHROUGH);
      }

      if (var1.hasDecoration(TextDecoration.UNDERLINED)) {
         this.method1(AdventureChatFormatting.UNDERLINE);
      }
   }

   @Override
   public boolean method1(int var1, Style var2, int var3) {
      if (!var2.equals(this.field2)) {
         this.method2(var2);
         this.field2 = var2;
      }

      this.field1.appendCodePoint(var3);
      return true;
   }

   public String getResult() {
      return this.field1.toString();
   }
}
