package com.moonsworth.lunar.client.config.option;

import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import org.jetbrains.annotations.NotNull;

public interface OptionFeatureLink {
   @NotNull
   <T extends Framework7Extension> T getFeature();

   static OptionFeatureLink method1(@NotNull Framework7Extension framework7extension0) {
      return new FeatureLinkRef(framework7extension0);
   }
}
