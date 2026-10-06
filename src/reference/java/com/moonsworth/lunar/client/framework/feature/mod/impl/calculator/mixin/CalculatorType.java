package com.moonsworth.lunar.client.framework.feature.mod.impl.calculator.mixin;

import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.bridge.scoreboard.ScorePlayerTeamBridge;
import com.moonsworth.lunar.bridge.scoreboard.ScoreboardBridge;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import net.kyori.adventure.text.format.NamedTextColor;
import org.jetbrains.annotations.Nullable;

public enum CalculatorType {
   BLUE(NamedTextColor.BLUE, "SLICE_OF_BLUEBERRY_CAKE"),
   RED(NamedTextColor.RED, "SLICE_OF_RED_VELVET_CAKE"),
   GREEN(NamedTextColor.GREEN, "SLICE_OF_GREEN_VELVET_CAKE"),
   YELLOW(NamedTextColor.YELLOW, "SLICE_OF_CHEESECAKE"),
   PINK(NamedTextColor.LIGHT_PURPLE, "SLICE_OF_STRAWBERRY_SHORTCAKE");

   private static final Pattern ICON_PATTERN = Pattern.compile("§(?<color>[0-9a-f])⛃");
   private final NamedTextColor color;
   private final String sliceId;

   public int getHighlightColor() {
      return this.color.value() | 0xFF000000;
   }

   @Nullable
   public static CalculatorType byItem(@Nullable ItemStackBridge bridgeextension_40) {
      if (bridgeextension_40 == null) {
         return null;
      }

      String text1 = SkyblockItemUtil.method2(bridgeextension_40);

      for (CalculatorType calculatortype5 : values()) {
         if (calculatortype5.sliceId.equals(text1)) {
            return calculatortype5;
         }
      }

      return null;
   }

   @Nullable
   public static CalculatorType of(Bridge6_10 bridge6_100) {
      if (bridge6_100.bridge$getGameProfile() == null) {
         return null;
      }

      WorldBridgeExtension itemcounter6extension1 = Ref.method8();
      if (itemcounter6extension1 == null) {
         return null;
      }

      ScoreboardBridge lighting42 = itemcounter6extension1.bridge$getScoreBoard();
      if (lighting42 == null) {
         return null;
      }

      ScorePlayerTeamBridge lighting33 = lighting42.bridge$getPlayersTeam(bridge6_100.bridge$getGameProfile().getName());
      if (lighting33 == null) {
         return null;
      }

      Matcher matcher4 = ICON_PATTERN.matcher(TextBridge.asLegacyString(lighting33.bridge$getPrefixAndSuffix()));
      if (!matcher4.find()) {
         return null;
      }

      ChatFormatting horsestatstype85 = ChatFormatting.getByCode(matcher4.group("color").charAt(0));
      return horsestatstype85 == null ? null : byColor(horsestatstype85.getAdventureColor());
   }

   @Nullable
   private static CalculatorType byColor(NamedTextColor namedtextcolor0) {
      for (CalculatorType calculatortype4 : values()) {
         if (calculatortype4.color == namedtextcolor0) {
            return calculatortype4;
         }
      }

      return null;
   }

   @Generated
   public NamedTextColor getColor() {
      return this.color;
   }

   @Generated
   public String getSliceId() {
      return this.sliceId;
   }

   @Generated
   CalculatorType(NamedTextColor namedtextcolor3, String text4) {
      this.color = namedtextcolor3;
      this.sliceId = text4;
   }
}
