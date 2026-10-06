package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.UUID;
import org.jetbrains.annotations.Nullable;

public interface NetworkPlayerInfoBridge {
   void bridge$setCapeLocation(ResourceLocationBridge horsestats141);

   @Nullable
   ResourceLocationBridge bridge$getCapeLocation();

   UUID bridge$getUniqueId();
}
