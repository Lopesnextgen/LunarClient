package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class LegacyFormattingSerializer implements StyledCharSink {
   private final StringBuilder field1 = new StringBuilder();
   private Style field2 = Style.empty();

   public LegacyFormattingSerializer() {
   }

   private void method1(ChatFormatting chatFormatting) {
      this.field1.append('§');
      this.field1.append(chatFormatting.getFormattingCode());
   }

   private void method2(Style style1) {
      TextColor textcolor2 = style1.color();
      if (textcolor2 != null) {
         ChatFormatting horsestatstype83;
         if (textcolor2 instanceof NamedTextColor namedtextcolor4) {
            horsestatstype83 = ChatFormatting.getFromAdventure(namedtextcolor4);
         } else {
            horsestatstype83 = ChatFormatting.nearestTo(textcolor2.value());
         }

         if (horsestatstype83 != null) {
            this.method1(horsestatstype83);
         }
      } else {
         this.method1(ChatFormatting.RESET);
      }

      if (style1.hasDecoration(TextDecoration.BOLD)) {
         this.method1(ChatFormatting.BOLD);
      }

      if (style1.hasDecoration(TextDecoration.ITALIC)) {
         this.method1(ChatFormatting.ITALIC);
      }

      if (style1.hasDecoration(TextDecoration.OBFUSCATED)) {
         this.method1(ChatFormatting.OBFUSCATED);
      }

      if (style1.hasDecoration(TextDecoration.STRIKETHROUGH)) {
         this.method1(ChatFormatting.STRIKETHROUGH);
      }

      if (style1.hasDecoration(TextDecoration.UNDERLINED)) {
         this.method1(ChatFormatting.UNDERLINE);
      }
   }

   public boolean method1(int value, Style style2, int value2) {
      if (!style2.equals(this.field2)) {
         this.method2(style2);
         this.field2 = style2;
      }

      this.field1.appendCodePoint(value2);
      return true;
   }

   public String getResult() {
      return this.field1.toString();
   }
}
