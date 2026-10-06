package com.moonsworth.lunar.client.framework.feature.mod.impl.alert.mixin;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import java.util.regex.Pattern;
import lombok.Generated;
import net.kyori.adventure.text.format.NamedTextColor;

public enum AlertType {
   MITHRIL("Mithril", NamedTextColor.DARK_GREEN, SkyblockItemUtil.method19(SkyblockItemUtil.DyeColor.LIME)),
   GEMSTONE("Gemstone", NamedTextColor.LIGHT_PURPLE, SkyblockItemUtil.method19(SkyblockItemUtil.DyeColor.PINK)),
   GLACITE("Glacite", NamedTextColor.AQUA, SkyblockItemUtil.method19(SkyblockItemUtil.DyeColor.LIGHT_BLUE));

   private final String id;
   private final NamedTextColor color;
   private final Pattern pattern;
   private final ItemStackBridge icon;

   AlertType(String text, NamedTextColor namedtextcolor4, ItemStackBridge bridgeextension_45) {
      this.id = text;
      this.color = namedtextcolor4;
      this.pattern = Pattern.compile("^ " + text + ": ([\\d,]+)$");
      this.icon = bridgeextension_45;
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
   public ItemStackBridge getIcon() {
      return this.icon;
   }
}
