package com.moonsworth.lunar.client.framework.feature.mod.fishing.rewindhandlers;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.ItemRarity;
import java.util.Set;

public class SeaCreature {
   private final String field1;
   private final String field2;
   private final String field3;
   private final ItemRarity field4;
   private final Set<RewindhandlersType> field5;

   public SeaCreature(String text, String text2, String text3, ItemRarity itemRarity, Set<RewindhandlersType> set) {
      this.field1 = text;
      this.field2 = text2;
      this.field3 = text3;
      this.field4 = itemRarity;
      this.field5 = set;
   }

   public String id() {
      return this.field1;
   }

   public String name() {
      return this.field2;
   }

   public String method1() {
      return this.field3;
   }

   public ItemRarity method2() {
      return this.field4;
   }

   public Set<RewindhandlersType> method3() {
      return this.field5;
   }
}
