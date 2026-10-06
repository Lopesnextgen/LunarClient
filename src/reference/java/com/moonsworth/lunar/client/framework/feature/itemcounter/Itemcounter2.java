package com.moonsworth.lunar.client.framework.feature.itemcounter;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.calculator.Calculator2;
import com.moonsworth.lunar.client.util.ThreadModuleDump33;

public class Itemcounter2 extends Itemcounter_2 implements Calculator2 {
   public Itemcounter2(int var1) {
      super("minecraft:potion");
      this.field2.bridge$setItemDamage(var1);
   }

   @Override
   public String method1() {
      String var1 = this.method3() ? "_splash " : "";
      return ThreadModuleDump33.method1(this.field2) + var1;
   }

   @Override
   public boolean method2(ItemStackBridge var1) {
      int var2 = this.field2.bridge$getItemDamage();
      int var3 = var1.bridge$getItemDamage();
      return var1.bridge$getItem().bridge$isItemPotion()
         && ThreadModuleDump33.method2(this.field2).equals(ThreadModuleDump33.method2(var1))
         && ThreadModuleDump33.method5(var2, var3);
   }

   @Override
   public String toString() {
      String var1 = "";
      int var2 = this.field2.bridge$getItemDamage();
      if (ThreadModuleDump33.method3(var2)) {
         var1 = " " + this.method1("suffix_strong", new Object[0]);
      } else if (ThreadModuleDump33.method4(var2)) {
         var1 = " " + this.method1("suffix_long", new Object[0]);
      }

      return this.field2.bridge$getDisplayName() + var1;
   }

   private boolean method3() {
      return (this.field2.bridge$getItemDamage() & 16384) == 16384;
   }

   public String getLanguagePath() {
      return "features.ITEM_COUNTER.potions";
   }
}
