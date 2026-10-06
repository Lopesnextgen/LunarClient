package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui;

import java.util.Map;

public class ItemShopPrices {
   private final Map<String, ShopItem> field1;
   private final Map<String, ShopItem> field2;
   private final Map<String, ShopItem> field3;
   private final Map<String, ShopItem> field4;
   private final Map<String, ShopItem> field5;
   private final Map<String, ShopItem> field6;
   private final Map<String, ShopItem> field7;

   public ItemShopPrices(
      Map<String, ShopItem> map,
      Map<String, ShopItem> map2,
      Map<String, ShopItem> map3,
      Map<String, ShopItem> map4,
      Map<String, ShopItem> map5,
      Map<String, ShopItem> map6,
      Map<String, ShopItem> map7
   ) {
      this.field1 = map;
      this.field2 = map2;
      this.field3 = map3;
      this.field4 = map4;
      this.field5 = map5;
      this.field6 = map6;
      this.field7 = map7;
   }

   public Map<String, ShopItem> method1() {
      return this.field1;
   }

   public Map<String, ShopItem> method2() {
      return this.field2;
   }

   public Map<String, ShopItem> method3() {
      return this.field3;
   }

   public Map<String, ShopItem> method4() {
      return this.field4;
   }

   public Map<String, ShopItem> method5() {
      return this.field5;
   }

   public Map<String, ShopItem> method6() {
      return this.field6;
   }

   public Map<String, ShopItem> method7() {
      return this.field7;
   }
}
