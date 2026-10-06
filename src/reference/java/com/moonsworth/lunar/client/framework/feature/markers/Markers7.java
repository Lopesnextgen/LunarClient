package com.moonsworth.lunar.client.framework.feature.markers;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap.Builder;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import org.jetbrains.annotations.NotNull;

public class Markers7 extends Markers12Base {
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

   public Markers7(String var1, boolean var2) {
      this.field2 = var1;
      this.field3 = var2;
   }

   public Markers7(String var1) {
      this(var1, true);
   }

   @Override
   protected String method1(int var1, ItemStackBridge var2) {
      return (String)field1.inverse().getOrDefault(this.field3 ? 15 - var1 : var1, "black");
   }

   @Override
   protected int method2(String var1) {
      int var2 = (Integer)field1.getOrDefault(var1, 15);
      return this.field3 ? 15 - var2 : var2;
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
