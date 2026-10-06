package com.moonsworth.lunar.client.framework.feature.itemcounter;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.calculator.Calculator2;

public class Itemcounter3 extends Itemcounter_2 implements Calculator2 {
   private final String field4;
   private final boolean field5;
   private final boolean field6;
   private final boolean field7;
   private final boolean field8;

   public Itemcounter3(String var1, String text) {
      super(var1);
      this.field4 = text;
      this.field7 = text.contains("long_");
      this.field8 = text.contains("strong_");
      this.field5 = this.field4.equals("minecraft:splash_potion");
      this.field6 = !this.field5 && this.field4.equals("minecraft:lingering_potion");
      Bridge.method36().method18(this.field2, text);
   }

   @Override
   public boolean method2(ItemStackBridge var1) {
      return method3(this.field2, var1);
   }

   @Override
   public String toString() {
      String var1 = "";
      if (this.field8) {
         var1 = " " + this.method1("suffix_strong", new Object[0]);
      } else if (this.field7) {
         var1 = " " + this.method1("suffix_long", new Object[0]);
      }

      return this.field2.bridge$getDisplayName() + var1;
   }

   @Override
   public String method1() {
      String var1 = this.field5 ? "_splash" : (this.field6 ? "_lingering" : "");
      return this.field4 + var1;
   }

   public String getLanguagePath() {
      return "features.ITEM_COUNTER.potions";
   }
}
