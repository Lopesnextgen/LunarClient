package com.moonsworth.lunar.client.cosmetics.gecko;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Generated;
import com.moonsworth.lunar.client.inactive.mixin.gui.GuiImpl;

public class AnimationStateMachine {
   @JsonProperty(value = "name", required = true)
   private String name;
   @JsonProperty(value = "states", required = true)
   private GuiImpl[] field1;
   @JsonProperty("default_transition_ticks")
   private int field2 = 10;

   @Generated
   public String getName() {
      return this.name;
   }

   @Generated
   public GuiImpl[] method1() {
      return this.field1;
   }

   @Generated
   public int method2() {
      return this.field2;
   }
}
