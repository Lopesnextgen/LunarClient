package com.moonsworth.lunar.bridge;

import org.jetbrains.annotations.Nullable;

public interface Bridge4_8 {
   String bridge$getName();

   @com.moonsworth.lunar.ichor.Annotation2(min = 15)
   default Bridge9_6 bridge$getContents() {
      throw new AbstractMethodErrorImpl();
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 8, max = 14)
   default void bridge$setShouldAlwaysAnimate(boolean var1) {
      throw new AbstractMethodErrorImpl();
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 8, max = 14)
   default boolean bridge$shouldAlwaysAnimate() {
      throw new AbstractMethodErrorImpl();
   }

   @com.moonsworth.lunar.ichor.Annotation2(min = 6)
   default Bridge4_6 bridge$wrap(Bridge4_6 var1) {
      throw new AbstractMethodErrorImpl();
   }

   @Nullable
   CoreMixinMarker.Bridge4$Data bridge$getSourceImage();

   void bridge$setSourceImage(Bridge4$Data var1);
}
