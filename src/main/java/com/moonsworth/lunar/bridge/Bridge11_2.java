package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.List;
import java.util.Set;
import org.jetbrains.annotations.Nullable;

public interface Bridge11_2 {
   Set<String> bridge$getResourceDomains();

   @Nullable
   default ResourceBridge bridge$getResource(ResourceLocationBridge horsestats141) {
      return this.bridge$getResource(horsestats141, false);
   }

   @Nullable
   ResourceBridge bridge$getResource(ResourceLocationBridge horsestats141, boolean flag2);

   List<ResourceBridge> bridge$getAllResources(ResourceLocationBridge horsestats141);
}
