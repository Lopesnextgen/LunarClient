package com.moonsworth.lunar.bridge;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Generated;

public enum BridgeType3_2 {
   WOOD(0, "wood"),
   STONE(1, "stone"),
   IRON(2, "iron"),
   GOLD(3, "gold"),
   DIAMOND(4, "diamond"),
   NETHERITE(5, "netherite"),
   COPPER(6, "copper"),
   UNKNOWN(-1, "unknown");

   private static final Map<String, BridgeType3_2> registryNameMapping = new ConcurrentHashMap<>();
   private static final Map<String, BridgeType3_2> materialMapping = Arrays.stream(values())
      .collect(Collectors.toMap(BridgeType3_2::getMaterial, Function.identity()));
   private final int id;
   private final String material;

   public static BridgeType3_2 fromRegistryName(String var0) {
      return registryNameMapping.computeIfAbsent(
         var0, var0x -> Arrays.stream(values()).filter(var1 -> var0x.contains(var1.material)).findFirst().orElse(UNKNOWN)
      );
   }

   public static BridgeType3_2 fromMaterial(String var0) {
      return var0.equalsIgnoreCase("EMERALD") ? DIAMOND : materialMapping.get(var0.toLowerCase(Locale.ROOT));
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
   BridgeType3_2(int value, String text) {
      this.id = value;
      this.material = text;
   }
}
