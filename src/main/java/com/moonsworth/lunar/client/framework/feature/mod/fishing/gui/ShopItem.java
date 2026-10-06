package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui;

import java.util.List;
import javax.annotation.Nullable;

public class ShopItem {
   private final String field1;
   private final String field2;
   private final double cost;
   private final ShopItemCategory field3;
   @Nullable
   private final String field4;
   @Nullable
   private final String field5;
   @Nullable
   private final ItemSkin field6;
   private final List<RequiredItem> field7;

   public ShopItem(String text, String text2, double value, ShopItemCategory gui3$type5, @Nullable String text6, @Nullable String text7, @Nullable ItemSkin gui_38, List<RequiredItem> list) {
      this.field1 = text;
      this.field2 = text2;
      this.cost = value;
      this.field3 = gui3$type5;
      this.field4 = text6;
      this.field5 = text7;
      this.field6 = gui_38;
      this.field7 = list;
   }

   public String method1() {
      return this.field1;
   }

   public String method2() {
      return this.field2;
   }

   public double getCost() {
      return this.cost;
   }

   public ShopItemCategory method4() {
      return this.field3;
   }

   @Nullable
   public String displayName() {
      return this.field4;
   }

   @Nullable
   public String method5() {
      return this.field5;
   }

   @Nullable
   public ItemSkin method6() {
      return this.field6;
   }

   public List<RequiredItem> method7() {
      return this.field7;
   }
}
