package com.moonsworth.lunar.bridge;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Generated;

public enum LunarItemMaterial {
   WOOD(0, "wood"),
   STONE(1, "stone"),
   IRON(2, "iron"),
   GOLD(3, "gold"),
   DIAMOND(4, "diamond"),
   NETHERITE(5, "netherite"),
   COPPER(6, "copper"),
   UNKNOWN(-1, "unknown");

   private static final Map<String, LunarItemMaterial> registryNameMapping = new ConcurrentHashMap<>();
   private static final Map<String, LunarItemMaterial> materialMapping = Arrays.stream(values())
      .collect(Collectors.toMap(LunarItemMaterial::getMaterial, Function.identity()));
   private final int id;
   private final String material;

   public static LunarItemMaterial fromRegistryName(String text0) {
      return registryNameMapping.computeIfAbsent(
         text0, arg0x -> Arrays.stream(values()).filter(arg1 -> arg0x.contains(arg1.material)).findFirst().orElse(UNKNOWN)
      );
   }

   public static LunarItemMaterial fromMaterial(String text0) {
      return text0.equalsIgnoreCase("EMERALD") ? DIAMOND : materialMapping.get(text0.toLowerCase(Locale.ROOT));
   }

   @Override
   public String toString() {
      return this.material;
   }

   @Generated
   public int getId() {
      return this.id;
   }

   @Generated
   public String getMaterial() {
      return this.material;
   }

   @Generated
   LunarItemMaterial(int value, String text) {
      this.id = value;
      this.material = text;
   }
}
