package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Optional;

public interface ResourcePackBridge {
   InputStream bridge$getInputStream(ResourceLocationBridge horsestats141);

   default boolean bridge$hasPath(String text) {
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
