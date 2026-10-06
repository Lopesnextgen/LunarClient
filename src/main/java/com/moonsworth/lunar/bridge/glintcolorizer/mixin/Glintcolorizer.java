package com.moonsworth.lunar.bridge.glintcolorizer.mixin;

import java.nio.file.Path;
import org.jspecify.annotations.Nullable;

public interface Glintcolorizer {
   @Nullable Path bridge$getParentDirPath();

   void bridge$setParentDir(Path var1);
}
