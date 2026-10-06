package com.moonsworth.lunar.client.framework.feature.mod.impl.chest.mixin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Generated;
import net.kyori.adventure.text.format.NamedTextColor;
import org.jetbrains.annotations.Nullable;

public enum ChestType {
   WOOD(NamedTextColor.WHITE),
   FREE(NamedTextColor.WHITE),
   PAID(NamedTextColor.GOLD),
   GOLD(NamedTextColor.YELLOW),
   DIAMOND(NamedTextColor.AQUA),
   EMERALD(NamedTextColor.DARK_GREEN),
   OBSIDIAN(NamedTextColor.DARK_PURPLE),
   BEDROCK(NamedTextColor.DARK_GRAY);

   private static final Pattern CHEST_PATTERN = Pattern.compile("^(?<tier>[A-Za-z]+)( Chest)?( Chest)?$");
   private final NamedTextColor color;

   @Nullable
   public static ChestType fromString(String text) {
      Matcher matcher1 = CHEST_PATTERN.matcher(text);
      if (!matcher1.matches()) {
         return null;
      }

      String text2 = matcher1.group("tier");

      for (ChestType chesttype6 : values()) {
         if (chesttype6.name().equalsIgnoreCase(text2)) {
            return chesttype6;
         }
      }

      return null;
   }

   @Generated
   public NamedTextColor getColor() {
      return this.color;
   }

   @Generated
   ChestType(NamedTextColor namedtextcolor3) {
      this.color = namedtextcolor3;
   }
}
