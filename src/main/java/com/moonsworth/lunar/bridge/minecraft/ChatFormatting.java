package com.moonsworth.lunar.bridge.minecraft;

import it.unimi.dsi.fastutil.chars.Char2ObjectOpenHashMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import lombok.Generated;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum ChatFormatting {
   BLACK('0', 0, NamedTextColor.BLACK),
   DARK_BLUE('1', 1, NamedTextColor.DARK_BLUE),
   DARK_GREEN('2', 2, NamedTextColor.DARK_GREEN),
   DARK_AQUA('3', 3, NamedTextColor.DARK_AQUA),
   DARK_RED('4', 4, NamedTextColor.DARK_RED),
   DARK_PURPLE('5', 5, NamedTextColor.DARK_PURPLE),
   GOLD('6', 6, NamedTextColor.GOLD),
   GRAY('7', 7, NamedTextColor.GRAY),
   DARK_GRAY('8', 8, NamedTextColor.DARK_GRAY),
   BLUE('9', 9, NamedTextColor.BLUE),
   GREEN('a', 10, NamedTextColor.GREEN),
   AQUA('b', 11, NamedTextColor.AQUA),
   RED('c', 12, NamedTextColor.RED),
   LIGHT_PURPLE('d', 13, NamedTextColor.LIGHT_PURPLE),
   YELLOW('e', 14, NamedTextColor.YELLOW),
   WHITE('f', 15, NamedTextColor.WHITE),
   OBFUSCATED('k'),
   BOLD('l'),
   STRIKETHROUGH('m'),
   UNDERLINE('n'),
   ITALIC('o'),
   RESET('r');

   private static final Char2ObjectOpenHashMap<ChatFormatting> codes = new Char2ObjectOpenHashMap();
   private static final Map<NamedTextColor, ChatFormatting> fromAdventure = new HashMap<>();
   private static final Pattern formattingCodePattern = Pattern.compile("(?i)§[0-9A-FK-OR]");
   private static final Pattern formattingCodeAmpersandToSection = Pattern.compile("(?i)&([0-9A-FK-OR])");
   private final char formattingCode;
   private final int colorIndex;
   @Nullable
   private final NamedTextColor adventureColor;

   ChatFormatting(char character3) {
      this(character3, null);
   }

   ChatFormatting(char character3, @Nullable NamedTextColor namedtextcolor4) {
      this(character3, -1, namedtextcolor4);
   }

   @Override
   public String toString() {
      return "§" + this.formattingCode;
   }

   public static String getTextWithoutFormattingCodes(String text0) {
      return text0 == null ? null : formattingCodePattern.matcher(text0).replaceAll("");
   }

   public static String getTextWithFormattingCodesFromAmpersand(String text0) {
      return text0 == null ? null : formattingCodeAmpersandToSection.matcher(text0).replaceAll("§$1");
   }

   public static ChatFormatting getByCode(char character0) {
      return (ChatFormatting)codes.get(Character.toLowerCase(character0));
   }

   @Nullable
   public static ChatFormatting getFromAdventure(NamedTextColor namedtextcolor0) {
      return fromAdventure.get(namedtextcolor0);
   }

   @NotNull
   public static ChatFormatting nearestTo(int value) {
      value &= 16777215;
      NamedTextColor namedtextcolor1 = NamedTextColor.namedColor(value);
      if (namedtextcolor1 == null) {
         namedtextcolor1 = NamedTextColor.nearestTo(TextColor.color(value));
      }

      return Objects.requireNonNull(fromAdventure.get(namedtextcolor1));
   }

   @Generated
   public char getFormattingCode() {
      return this.formattingCode;
   }

   @Generated
   public int getColorIndex() {
      return this.colorIndex;
   }

   @Nullable
   @Generated
   public NamedTextColor getAdventureColor() {
      return this.adventureColor;
   }

   @Generated
   ChatFormatting(char character3, int value, @Nullable NamedTextColor namedtextcolor5) {
      this.formattingCode = character3;
      this.colorIndex = value;
      this.adventureColor = namedtextcolor5;
   }

   static {
      for (ChatFormatting horsestatstype83 : values()) {
         codes.put(horsestatstype83.getFormattingCode(), horsestatstype83);
         if (horsestatstype83.colorIndex >= 0) {
            fromAdventure.put(Objects.requireNonNull(horsestatstype83.getAdventureColor()), horsestatstype83);
         }
      }
   }
}
