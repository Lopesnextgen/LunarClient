package com.moonsworth.lunar.bridge.horsestats;

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

public enum HorsestatsType8 {
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

   private static final Char2ObjectOpenHashMap<HorsestatsType8> codes = new Char2ObjectOpenHashMap();
   private static final Map<NamedTextColor, HorsestatsType8> fromAdventure = new HashMap<>();
   private static final Pattern formattingCodePattern = Pattern.compile("(?i)§[0-9A-FK-OR]");
   private static final Pattern formattingCodeAmpersandToSection = Pattern.compile("(?i)&([0-9A-FK-OR])");
   private final char formattingCode;
   private final int colorIndex;
   @Nullable
   private final NamedTextColor adventureColor;

   HorsestatsType8(char var3) {
      this(var3, null);
   }

   HorsestatsType8(char var3, @Nullable NamedTextColor var4) {
      this(var3, -1, var4);
   }

   @Override
   public String toString() {
      return "§" + this.formattingCode;
   }

   public static String getTextWithoutFormattingCodes(String var0) {
      return var0 == null ? null : formattingCodePattern.matcher(var0).replaceAll("");
   }

   public static String getTextWithFormattingCodesFromAmpersand(String var0) {
      return var0 == null ? null : formattingCodeAmpersandToSection.matcher(var0).replaceAll("§$1");
   }

   public static HorsestatsType8 getByCode(char var0) {
      return (HorsestatsType8)codes.get(Character.toLowerCase(var0));
   }

   @Nullable
   public static HorsestatsType8 getFromAdventure(NamedTextColor var0) {
      return fromAdventure.get(var0);
   }

   @NotNull
   public static HorsestatsType8 nearestTo(int var0) {
      var0 &= 16777215;
      NamedTextColor var1 = NamedTextColor.namedColor(var0);
      if (var1 == null) {
         var1 = NamedTextColor.nearestTo(TextColor.color(var0));
      }

      return Objects.requireNonNull(fromAdventure.get(var1));
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
   HorsestatsType8(char var3, int var4, @Nullable NamedTextColor var5) {
      this.formattingCode = var3;
      this.colorIndex = var4;
      this.adventureColor = var5;
   }

   static {
      for (HorsestatsType8 var3 : values()) {
         codes.put(var3.getFormattingCode(), var3);
         if (var3.colorIndex >= 0) {
            fromAdventure.put(Objects.requireNonNull(var3.getAdventureColor()), var3);
         }
      }
   }
}
