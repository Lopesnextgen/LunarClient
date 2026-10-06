package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.client.util.ThreadModuleDump63;

public class Markers9Impl extends Markers9 {
   public Markers9Impl() {
      super("stone_bricks", "mossy_stone_bricks", "cracked_stone_bricks", "chiseled_stone_bricks");
   }

   @Override
   protected String method2() {
      return ThreadModuleDump63.MC_VERSION > 5 ? "stone_bricks" : "stonebrick";
   }
}
