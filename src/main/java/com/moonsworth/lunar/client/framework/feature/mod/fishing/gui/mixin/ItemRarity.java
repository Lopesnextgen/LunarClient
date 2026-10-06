package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin;

import com.lunarclient.adventure.matcher.ComponentMatcher;
import com.lunarclient.adventure.pattern.ComponentPattern;
import com.lunarclient.adventure.pattern.StylePattern;
import java.util.Locale;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import lombok.Generated;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

public enum ItemRarity {
   ADMIN("ADMIN", NamedTextColor.DARK_RED, '4'),
   ULTIMATE("ULTIMATE", NamedTextColor.DARK_RED, '4'),
   VERY_SPECIAL("VERY SPECIAL", NamedTextColor.RED, 'c'),
   SPECIAL("SPECIAL", NamedTextColor.RED, 'c'),
   DIVINE("DIVINE", NamedTextColor.AQUA, 'b'),
   MYTHIC("MYTHIC", NamedTextColor.LIGHT_PURPLE, 'd'),
   LEGENDARY("LEGENDARY", NamedTextColor.GOLD, '6'),
   EPIC("EPIC", NamedTextColor.DARK_PURPLE, '5'),
   RARE("RARE", NamedTextColor.BLUE, '9'),
   UNCOMMON("UNCOMMON", NamedTextColor.GREEN, 'a'),
   COMMON("COMMON", NamedTextColor.WHITE, 'f'),
   NONE(null, null, null);

   private final String tag;
   private final String reverseTag;
   private final Character code;
   private final ComponentMatcher matcher;
   private final NamedTextColor namedTextColor;
   private final Pattern startsWithAnalyzer;

   ItemRarity(String text, NamedTextColor namedtextcolor4, Character character5) {
      this.tag = text;
      this.reverseTag = text != null ? new StringBuilder(text).reverse().toString() : null;
      this.code = character5;
      String text6 = this.reverseTag != null ? "(" + text + "|" + this.reverseTag + ")" : text;
      this.matcher = text == null
         ? null
         : ComponentMatcher.pattern(ComponentPattern.regex(text6, StylePattern.builder().color(namedtextcolor4).decoration(TextDecoration.BOLD)));
      this.startsWithAnalyzer = Pattern.compile("^(a )?(SHINY )?" + text6);
      this.namedTextColor = namedtextcolor4;
   }

   @Nullable
   public static ItemRarity fromRarity(@Nullable String text0) {
      if (text0 == null) {
         return null;
      }

      String text1 = text0.toUpperCase(Locale.ROOT);

      for (ItemRarity guitype35 : values()) {
         if (guitype35.getTag().equals(text1) || guitype35.getReverseTag().equals(text1)) {
            return guitype35;
         }
      }

      return null;
   }

   @Nullable
   public static ItemRarity fromCode(char character0) {
      for (ItemRarity guitype34 : values()) {
         if (guitype34.getCode() == character0) {
            return guitype34;
         }
      }

      return null;
   }

   @Generated
   public String getTag() {
      return this.tag;
   }

   @Generated
   public String getReverseTag() {
      return this.reverseTag;
   }

   @Generated
   public Character getCode() {
      return this.code;
   }

   @Generated
   public ComponentMatcher getMatcher() {
      return this.matcher;
   }

   @Generated
   public NamedTextColor getNamedTextColor() {
      return this.namedTextColor;
   }

   @Generated
   public Pattern getStartsWithAnalyzer() {
      return this.startsWithAnalyzer;
   }
}
