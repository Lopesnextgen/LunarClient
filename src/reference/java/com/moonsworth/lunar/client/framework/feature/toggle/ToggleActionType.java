package com.moonsworth.lunar.client.framework.feature.toggle;

import com.moonsworth.lunar.client.chat.translation.Translatable;
import com.moonsworth.lunar.client.mod.movement.togglesneak.ToggleSneakHud;
import java.util.Optional;
import lombok.Generated;

public enum ToggleActionType implements Translatable {
   FLYING("flying"),
   FLYING_BOOST("flying", "boost", true),
   RIDING("riding"),
   DESCENDING("descending"),
   DISMOUNTING("dismounting"),
   SNEAKING_TOGGLED("sneaking", "toggled"),
   SNEAKING_HELD("sneaking", "held"),
   SPRINTING_TOGGLED("sprinting", "toggled"),
   SPRINTING_HELD("sprinting", "held"),
   SPRINTING_VANILLA("sprinting", "vanilla"),
   HELD("held", null),
   TOGGLED("toggled", null),
   VANILLA("vanilla", null);

   private final String mainText;
   private final String partition;
   private final boolean hasExtraArguments;

   ToggleActionType(String text3) {
      this(text3, null, false);
   }

   ToggleActionType(String text3, String text4) {
      this(text3, text4, false);
   }

   public String getLanguagePath() {
      return "settings";
   }

   public String getMainText(ToggleSneakHud togglesneakhudchild1) {
      if (!togglesneakhudchild1.sprintingText.isEmpty() && "sprinting".equals(this.mainText)) {
         return (String)togglesneakhudchild1.sprintingText.get();
      } else if (!togglesneakhudchild1.sneakingText.isEmpty() && "sneaking".equals(this.mainText)) {
         return (String)togglesneakhudchild1.sneakingText.get();
      } else {
         return !togglesneakhudchild1.flyingText.isEmpty() && "flying".equals(this.mainText)
            ? (String)togglesneakhudchild1.flyingText.get()
            : this.OHROCHICOIOICHOCRROORRCIIICIHO(this.mainText, new Object[0]);
      }
   }

   public Optional<String> getPartition(Object... items1) {
      return this.partition != null && items1 != null && (!this.hasExtraArguments || items1.length != 0)
         ? Optional.of(this.OHROCHICOIOICHOCRROORRCIIICIHO(this.partition, items1))
         : Optional.empty();
   }

   @Generated
   ToggleActionType(String text3, String text4, boolean flag5) {
      this.mainText = text3;
      this.partition = text4;
      this.hasExtraArguments = flag5;
   }
}
