package com.moonsworth.lunar.client.cosmetics.gecko;

import com.moonsworth.lunar.bridge.LunarItemMaterial;
import com.moonsworth.lunar.bridge.ItemStackRenderStateBridge;
import java.util.function.Predicate;
import lombok.Generated;

public enum ItemRenderMaterial {
   ANY(arg0 -> true),
   WOOD(LunarItemMaterial.WOOD),
   COPPER(LunarItemMaterial.COPPER),
   STONE(LunarItemMaterial.STONE),
   IRON(LunarItemMaterial.IRON),
   GOLD(LunarItemMaterial.GOLD),
   DIAMOND(LunarItemMaterial.DIAMOND),
   NETHERITE(LunarItemMaterial.NETHERITE);

   private final Predicate<ItemStackRenderStateBridge> itemMatcher;

   ItemRenderMaterial(LunarItemMaterial bridgetype3_23) {
      this.itemMatcher = arg1x -> arg1x != null && arg1x.bridge$getLunarItemMaterial() == bridgetype3_23;
   }

   @Generated
   public Predicate<ItemStackRenderStateBridge> getItemMatcher() {
      return this.itemMatcher;
   }

   @Generated
   ItemRenderMaterial(Predicate<ItemStackRenderStateBridge> predicate3) {
      this.itemMatcher = predicate3;
   }
}
