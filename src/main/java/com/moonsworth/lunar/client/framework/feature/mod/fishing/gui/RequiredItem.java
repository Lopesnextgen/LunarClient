package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui;

public class RequiredItem {
   private final String field1;
   private final int field2;

   public RequiredItem(String text, int value) {
      this.field1 = text;
      this.field2 = value;
   }

   public String id() {
      return this.field1;
   }

   public int amount() {
      return this.field2;
   }
}
