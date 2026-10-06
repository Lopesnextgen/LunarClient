package com.moonsworth.lunar.bridge;

import java.util.function.Consumer;

public interface RenderTypeTrackerBridge {
   void bridge$pushTracker(Consumer<RenderTypeBridge> consumer1);

   void bridge$popTracker();
}
