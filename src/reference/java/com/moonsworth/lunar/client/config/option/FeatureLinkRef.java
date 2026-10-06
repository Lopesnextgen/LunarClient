package com.moonsworth.lunar.client.config.option;

import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

public class FeatureLinkRef implements OptionFeatureLink {
   @NotNull
   private final Framework7Extension field1;

   @Override
   public <T extends Framework7Extension> @NonNull T getFeature() {
      return (T)this.field1;
   }

   @Generated
   FeatureLinkRef(@NotNull Framework7Extension framework7extension1) {
      if (framework7extension1 == null) {
         throw new NullPointerException("feature is marked non-null but is null");
      }

      this.field1 = framework7extension1;
   }
}
