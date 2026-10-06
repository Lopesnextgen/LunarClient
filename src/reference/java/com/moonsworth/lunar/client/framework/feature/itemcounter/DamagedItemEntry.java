package com.moonsworth.lunar.client.framework.feature.itemcounter;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import org.jetbrains.annotations.NotNull;

public class DamagedItemEntry extends ItemCounterEntry {
   private final String field4;

   public DamagedItemEntry(@NotNull String text1, int number2, @NotNull String text3) {
      super(text1);
      this.field4 = text3;
      this.IOOCCICCHROROIORCRRRROHIRCHICO.bridge$setItemDamage(number2);
   }

   @Override
   public String method1() {
      return this.field4;
   }

   @Override
   public boolean method2(ItemStackBridge bridgeextension_41) {
      return bridgeextension_41 != null
         && bridgeextension_41.bridge$getItem() == this.IOOCCICCHROROIORCRRRROHIRCHICO.bridge$getItem()
         && bridgeextension_41.bridge$getItemDamage() == this.IOOCCICCHROROIORCRRRROHIRCHICO.bridge$getItemDamage();
   }
}
