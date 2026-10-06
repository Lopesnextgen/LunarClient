package com.moonsworth.lunar.bridge;

public interface MixinHelper2 {
   @com.moonsworth.lunar.ichor.Annotation2(min = 5)
   int bridge$renderSeed();

   int bridge$renderCount();

   ItemStackRenderStateBridge bridge$getItemState();
}
