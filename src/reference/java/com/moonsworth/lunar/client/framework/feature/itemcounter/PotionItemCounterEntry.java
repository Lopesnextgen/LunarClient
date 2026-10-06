package com.moonsworth.lunar.client.framework.feature.itemcounter;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.chat.translation.Translatable;
import com.moonsworth.lunar.client.util.game.PotionUtils;

public class PotionItemCounterEntry extends ItemCounterEntry implements Translatable {
   public PotionItemCounterEntry(int number1) {
      super("minecraft:potion");
      this.IOOCCICCHROROIORCRRRROHIRCHICO.bridge$setItemDamage(number1);
   }

   @Override
   public String method1() {
      String text1 = this.method3() ? "_splash " : "";
      return PotionUtils.method1(this.IOOCCICCHROROIORCRRRROHIRCHICO) + text1;
   }

   @Override
   public boolean method2(ItemStackBridge bridgeextension_41) {
      int number2 = this.IOOCCICCHROROIORCRRRROHIRCHICO.bridge$getItemDamage();
      int number3 = bridgeextension_41.bridge$getItemDamage();
      return bridgeextension_41.bridge$getItem().bridge$isItemPotion()
         && PotionUtils.method2(this.IOOCCICCHROROIORCRRRROHIRCHICO).equals(PotionUtils.method2(bridgeextension_41))
         && PotionUtils.method5(number2, number3);
   }

   @Override
   public String toString() {
      String text1 = "";
      int number2 = this.IOOCCICCHROROIORCRRRROHIRCHICO.bridge$getItemDamage();
      if (PotionUtils.method3(number2)) {
         text1 = " " + this.OHROCHICOIOICHOCRROORRCIIICIHO("suffix_strong", new Object[0]);
      } else if (PotionUtils.method4(number2)) {
         text1 = " " + this.OHROCHICOIOICHOCRROORRCIIICIHO("suffix_long", new Object[0]);
      }

      return this.IOOCCICCHROROIORCRRRROHIRCHICO.bridge$getDisplayName() + text1;
   }

   private boolean method3() {
      return (this.IOOCCICCHROROIORCRRRROHIRCHICO.bridge$getItemDamage() & 16384) == 16384;
   }

   public String getLanguagePath() {
      return "features.ITEM_COUNTER.potions";
   }
}
