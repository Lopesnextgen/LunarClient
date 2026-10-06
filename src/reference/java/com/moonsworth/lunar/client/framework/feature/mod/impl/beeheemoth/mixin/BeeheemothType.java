package com.moonsworth.lunar.client.framework.feature.mod.impl.beeheemoth.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemBridge;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.regex.Pattern;
import lombok.Generated;
import net.kyori.adventure.text.format.NamedTextColor;

public enum BeeheemothType {
   FOREST_WHISPER("Forest Whispers", NamedTextColor.DARK_GREEN, Ref.MC_VERSION >= 33 ? Bridge.method28().method58() : null),
   DESERT_WHISPER("Desert Whispers", NamedTextColor.GOLD, Ref.MC_VERSION >= 33 ? Bridge.method28().method59() : null);

   private final String id;
   private final NamedTextColor color;
   private final Pattern pattern;
   private final ItemBridge icon;

   BeeheemothType(String text, NamedTextColor namedtextcolor4, ItemBridge bridge6_45) {
      this.id = text;
      this.color = namedtextcolor4;
      this.pattern = Pattern.compile("^" + text + ": (.+)$");
      this.icon = bridge6_45;
   }

   @Generated
   public String getId() {
      return this.id;
   }

   @Generated
   public NamedTextColor getColor() {
      return this.color;
   }

   @Generated
   public Pattern getPattern() {
      return this.pattern;
   }

   @Generated
   public ItemBridge getIcon() {
      return this.icon;
   }
}
