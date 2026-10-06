package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.OutputStateShardBridge;

public class MainTargetRenderStateShard extends RunnableRenderStateShard implements OutputStateShardBridge {
   public MainTargetRenderStateShard(Runnable runnable1, Runnable runnable2) {
      super(runnable1, runnable2);
   }
}
