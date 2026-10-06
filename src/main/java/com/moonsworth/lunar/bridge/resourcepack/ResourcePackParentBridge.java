package com.moonsworth.lunar.bridge.resourcepack;

import java.nio.file.Path;
import org.jspecify.annotations.Nullable;

public interface ResourcePackParentBridge {
   @Nullable Path bridge$getParentDirPath();

   void bridge$setParentDir(Path path1);
}
