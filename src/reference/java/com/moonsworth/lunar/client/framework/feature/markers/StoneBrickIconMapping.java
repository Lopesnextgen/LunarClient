package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.client.framework.Ref;

public class StoneBrickIconMapping extends BlockVariantIconMapping {
   public StoneBrickIconMapping() {
      super("stone_bricks", "mossy_stone_bricks", "cracked_stone_bricks", "chiseled_stone_bricks");
   }

   @Override
   protected String method2() {
      return Ref.MC_VERSION > 5 ? "stone_bricks" : "stonebrick";
   }
}
