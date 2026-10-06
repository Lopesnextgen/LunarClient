package com.moonsworth.lunar.client.framework.feature.itemcounter;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import org.jetbrains.annotations.NotNull;

public class ItemcounterImpl extends Itemcounter_2 {
   private final String field4;

   public ItemcounterImpl(@NotNull String var1, int value, @NotNull String var3) {
      super(var1);
      this.field4 = var3;
      this.field2.bridge$setItemDamage(value);
   }

   @Override
   public String method1() {
      return this.field4;
   }

   @Override
   public boolean method2(ItemStackBridge var1) {
      return var1 != null
         && var1.bridge$getItem() == this.field2.bridge$getItem()
         && var1.bridge$getItemDamage() == this.field2.bridge$getItemDamage();
   }
}
