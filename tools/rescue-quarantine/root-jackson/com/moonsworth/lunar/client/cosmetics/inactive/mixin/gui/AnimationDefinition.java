package com.moonsworth.lunar.client.cosmetics.inactive.mixin.gui;

import com.moonsworth.lunar.Annotation27;
import lombok.Generated;

public class AnimationDefinition {
   @Annotation27(value = "name", required = true)
   private String name;
   @Annotation27(value = "states", required = true)
   private GuiImpl[] field1;
   @Annotation27("default_transition_ticks")
   private int field2 = 10;

   public AnimationDefinition() {
   }

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
