package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.UUID;
import org.jetbrains.annotations.Nullable;

public interface Bridge_30 {
   void bridge$setCapeLocation(ResourceLocationBridge var1);

   @Nullable
   ResourceLocationBridge bridge$getCapeLocation();

   UUID bridge$getUniqueId();
}
