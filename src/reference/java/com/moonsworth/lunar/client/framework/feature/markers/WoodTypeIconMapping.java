package com.moonsworth.lunar.client.framework.feature.markers;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap.Builder;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class WoodTypeIconMapping extends SuffixIconMapping {
   public static final BiMap<String, Integer> field1 = new Builder()
      .put("oak", 0)
      .put("spruce", 1)
      .put("birch", 2)
      .put("jungle", 3)
      .put("acacia", 4)
      .put("dark_oak", 5)
      .build();
   private final String field2;

   @Override
   protected String method1(int number1, ItemStackBridge bridgeextension_42) {
      return (String)field1.inverse().getOrDefault(number1, "oak");
   }

   @Override
   protected int method2(String text1) {
      return (Integer)field1.getOrDefault(text1, 0);
   }

   @Override
   protected String method3() {
      return "oak";
   }

   @NotNull
   @Override
   public String method4() {
      return this.field2;
   }

   @Generated
   public WoodTypeIconMapping(String text1) {
      this.field2 = text1;
   }
}
