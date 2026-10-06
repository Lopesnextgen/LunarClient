package com.moonsworth.lunar.client.cosmetics.gecko;

import com.moonsworth.lunar.bridge.LunarItemType;
import com.moonsworth.lunar.bridge.ItemStackRenderStateBridge;
import java.util.function.Predicate;
import lombok.Generated;

public enum InactiveType {
   NONE(arg0 -> arg0 == null || arg0.bridge$getLunarItemType() == null || arg0.bridge$getLunarItemType() == LunarItemType.EMPTY),
   SHIELD(LunarItemType.SHIELD),
   SWORD(LunarItemType.SWORD),
   PICKAXE(LunarItemType.PICKAXE),
   AXE(LunarItemType.AXE),
   SHOVEL(LunarItemType.SHOVEL),
   HOE(LunarItemType.HOE);

   private final Predicate<ItemStackRenderStateBridge> itemMatcher;

   InactiveType(LunarItemType bridgetype2_43) {
      this.itemMatcher = arg1x -> arg1x != null && arg1x.bridge$getLunarItemType() == bridgetype2_43;
   }

   @Generated
   public Predicate<ItemStackRenderStateBridge> getItemMatcher() {
      return this.itemMatcher;
   }

   @Generated
   InactiveType(Predicate<ItemStackRenderStateBridge> predicate3) {
      this.itemMatcher = predicate3;
   }
}
