package com.moonsworth.lunar.client.config.option;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.calculator.Calculator2;
import com.moonsworth.lunar.client.util.Annotation;
import lombok.Generated;
import com.moonsworth.lunar.client.config.option.OptionCategory;

public enum Calculator2Type implements Calculator2 {
   GENERAL("general", "general-52x52", OptionCategory.GENERAL),
   PERFORMANCE("performance", "performance-52x52", OptionCategory.PERFORMANCE),
   CONTROLS("controls", "controls-52x52", OptionCategory.CONTROLS);

   private final String category;
   private final ResourceLocationBridge resource;
   private final OptionCategory scope;

   Calculator2Type(@Annotation(method1 = Annotation.Type.SETTING) String var3, String var4, OptionCategory var5) {
      this.category = var3;
      this.resource = ResourceLocationBridge.create("lunar", "ui/setting/" + var4 + ".png");
      this.scope = var5;
   }

   @Override
   public String toString() {
      return this.category;
   }

   @Override
   public String getLanguagePath() {
      return "gui.settings";
   }

   @Generated
   public ResourceLocationBridge getResource() {
      return this.resource;
   }

   @Generated
   public OptionCategory getScope() {
      return this.scope;
   }
}
