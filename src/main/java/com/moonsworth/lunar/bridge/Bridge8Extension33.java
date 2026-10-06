package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.ichor.VersionGate;
import java.io.File;
import java.nio.file.Path;
import org.jetbrains.annotations.Nullable;

public interface Bridge8Extension33 extends Bridge8Extension3 {
   @VersionGate(min = 6)
   NativeImageBridge bridge$getNativeImage();

   @VersionGate(min = 6)
   default void method1(String text, @Nullable Path path2) {
      NativeImageBridge bridge_103 = this.bridge$getNativeImage();
      if (bridge_103 != null) {
         if (path2 != null) {
            bridge_103.bridge$writeToFile(path2.resolve(text + ".png").toFile());
         } else {
            bridge_103.bridge$writeToFile(new File(text + ".png"));
         }
      }
   }
}
