package com.moonsworth.lunar.client.framework.feature.mod.fishing.colorsaturation;

import java.util.Map;

public class Colorsaturation {
   private final Map<String, String> categories;
   private final Map<String, Double> xp;

   public Colorsaturation(Map<String, String> map, Map<String, Double> map2) {
      this.categories = map;
      this.xp = map2;
   }

   public Map<String, String> getCategories() {
      return this.categories;
   }

   public Map<String, Double> getXp() {
      return this.xp;
   }
}
