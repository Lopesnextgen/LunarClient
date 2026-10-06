package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate;

import com.moonsworth.lunar.client.framework.feature.mod.mixin.MixinHelper;
import com.moonsworth.lunar.client.mod.skyblock.dungeonroutes.SkyblockDungeonRoutes;
import java.util.function.Function;
import lombok.Generated;

public enum NameplateType {
   CHEST(true, -16711936, arg0 -> !(Boolean)arg0.method34().get(), "routeInteractionTypeChest"),
   SKULL(true, -16711936, arg0 -> !(Boolean)arg0.method34().get(), "routeInteractionTypeSkull"),
   LEVER(true, -256, arg0 -> !(Boolean)arg0.method29().get(), "routeInteractionTypeLever"),
   MUSHROOM(true, -256, arg0 -> !(Boolean)arg0.method35().get(), "routeInteractionTypeMushroom"),
   ITEM_DROP(true, -16711936, arg0 -> !(Boolean)arg0.method34().get(), "routeInteractionTypeItemDrop"),
   BAT(false, -16711936, arg0 -> !(Boolean)arg0.method34().get(), "routeInteractionTypeBat"),
   TNT(false, -65536, arg0 -> !(Boolean)arg0.method27().get(), "routeInteractionTypeTnt"),
   ETHERWARP(false, -16776961, arg0 -> !(Boolean)arg0.method30().get(), "routeInteractionTypeEtherwarp"),
   BREAK_BLOCK(false, -65281, arg0 -> !(Boolean)arg0.method28().get(), "routeInteractionTypeBlock"),
   PEARL(false, -16755456, arg0 -> !(Boolean)arg0.method36().get(), "routeInteractionTypePearl");

   private final boolean seperatesSections;
   private final int color;
   private final Function<SkyblockDungeonRoutes, Boolean> shouldRenderSetting;
   private final String text;

   public boolean isSectionSeparator() {
      return this.seperatesSections;
   }

   public int getColor() {
      return this.color;
   }

   public int getColorAlpha() {
      return this.color & 1157627903;
   }

   public boolean shouldRender() {
      return this.shouldRenderSetting.apply(SkyblockDungeonRoutes.method13());
   }

   public String getText() {
      return MixinHelper.method1(this.text);
   }

   @Generated
   NameplateType(boolean flag, int value, Function<SkyblockDungeonRoutes, Boolean> function5, String text2) {
      this.seperatesSections = flag;
      this.color = value;
      this.shouldRenderSetting = function5;
      this.text = text2;
   }
}
