package com.moonsworth.lunar.client.framework.feature.markers;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap.Builder;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import org.jetbrains.annotations.NotNull;

public class DyeColorIconMapping extends SuffixIconMapping {
   public static final BiMap<String, Integer> field1 = new Builder()
      .put("white", 15)
      .put("orange", 14)
      .put("magenta", 13)
      .put("light_blue", 12)
      .put("yellow", 11)
      .put("lime", 10)
      .put("pink", 9)
      .put("gray", 8)
      .put("silver", 7)
      .put("cyan", 6)
      .put("purple", 5)
      .put("blue", 4)
      .put("brown", 3)
      .put("green", 2)
      .put("red", 1)
      .put("black", 0)
      .build();
   private final String field2;
   private final boolean field3;

   public DyeColorIconMapping(String text1, boolean flag2) {
      this.field2 = text1;
      this.field3 = flag2;
   }

   public DyeColorIconMapping(String text1) {
      this(text1, true);
   }

   @Override
   protected String method1(int number1, ItemStackBridge bridgeextension_42) {
      return (String)field1.inverse().getOrDefault(this.field3 ? 15 - number1 : number1, "black");
   }

   @Override
   protected int method2(String text1) {
      int number2 = (Integer)field1.getOrDefault(text1, 15);
      return this.field3 ? 15 - number2 : number2;
   }

   @Override
   protected String method3() {
      return "white";
   }

   @NotNull
   @Override
   public String method4() {
      return this.field2;
   }
}
