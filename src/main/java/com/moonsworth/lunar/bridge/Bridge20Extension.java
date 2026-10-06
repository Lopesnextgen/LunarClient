package com.moonsworth.lunar.bridge;

import com.google.common.net.MediaType;
import java.io.File;
import java.util.concurrent.CompletableFuture;

public interface Bridge20Extension extends Bridge8Extension3 {
   default void bridge$setImageFound(boolean var1) {
   }

   File bridge$getFile();

   CompletableFuture<AutoCloseableExtension> bridge$requestContent();

   void setMimeType(MediaType var1);
}
