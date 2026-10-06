package com.moonsworth.lunar.client.framework.feature.toggle;

import com.moonsworth.lunar.client.calculator.Calculator2;
import com.moonsworth.lunar.client.mod.movement.togglesneak.ToggleSneakHud;
import java.util.Optional;
import lombok.Generated;

public enum Calculator2Type implements Calculator2 {
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

   Calculator2Type(String var3) {
      this(var3, null, false);
   }

   Calculator2Type(String var3, String var4) {
      this(var3, var4, false);
   }

   public String getLanguagePath() {
      return "settings";
   }

   public String getMainText(ToggleSneakHud var1) {
      if (!var1.sprintingText.isEmpty() && "sprinting".equals(this.mainText)) {
         return (String)var1.sprintingText.get();
      } else if (!var1.sneakingText.isEmpty() && "sneaking".equals(this.mainText)) {
         return (String)var1.sneakingText.get();
      } else {
         return !var1.flyingText.isEmpty() && "flying".equals(this.mainText)
            ? (String)var1.flyingText.get()
            : this.method1(this.mainText, new Object[0]);
      }
   }

   public Optional<String> getPartition(Object... var1) {
      return this.partition != null && var1 != null && (!this.hasExtraArguments || var1.length != 0)
         ? Optional.of(this.method1(this.partition, var1))
         : Optional.empty();
   }

   @Generated
   Calculator2Type(String var3, String var4, boolean var5) {
      this.mainText = var3;
      this.partition = var4;
      this.hasExtraArguments = var5;
   }
}
