package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Optional;

public interface IResourcePackBridge {
   InputStream bridge$getInputStream(ResourceLocationBridge var1);

   default boolean bridge$hasPath(String var1) {
      return false;
   }

   String bridge$getPackName();

   default Bridge2_42 bridge$getDescription() {
      return null;
   }

   default Optional<BufferedImage> bridge$getPackImage() {
      return Optional.empty();
   }

   default void bridge$close() {
   }
}
