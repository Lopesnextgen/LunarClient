package com.moonsworth.lunar.client.framework.feature.itemcounter;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.chat.translation.Translatable;

public class PotionVariantItemCounterEntry extends ItemCounterEntry implements Translatable {
   private final String field4;
   private final boolean field5;
   private final boolean field6;
   private final boolean field7;
   private final boolean field8;

   public PotionVariantItemCounterEntry(String text1, String text2) {
      super(text1);
      this.field4 = text2;
      this.field7 = text2.contains("long_");
      this.field8 = text2.contains("strong_");
      this.field5 = this.CCORCIROICIIRICIIRRIIROHIROORI.equals("minecraft:splash_potion");
      this.field6 = !this.field5 && this.CCORCIROICIIRICIIRRIIROHIROORI.equals("minecraft:lingering_potion");
      Bridge.method36().method18(this.IOOCCICCHROROIORCRRRROHIRCHICO, text2);
   }

   @Override
   public boolean method2(ItemStackBridge bridgeextension_41) {
      return HORHROIOIOICIRHIOCOICHHHIHCIIO(this.IOOCCICCHROROIORCRRRROHIRCHICO, bridgeextension_41);
   }

   @Override
   public String toString() {
      String text1 = "";
      if (this.field8) {
         text1 = " " + this.OHROCHICOIOICHOCRROORRCIIICIHO("suffix_strong", new Object[0]);
      } else if (this.field7) {
         text1 = " " + this.OHROCHICOIOICHOCRROORRCIIICIHO("suffix_long", new Object[0]);
      }

      return this.IOOCCICCHROROIORCRRRROHIRCHICO.bridge$getDisplayName() + text1;
   }

   @Override
   public String method1() {
      String text1 = this.field5 ? "_splash" : (this.field6 ? "_lingering" : "");
      return this.field4 + text1;
   }

   public String getLanguagePath() {
      return "features.ITEM_COUNTER.potions";
   }
}
