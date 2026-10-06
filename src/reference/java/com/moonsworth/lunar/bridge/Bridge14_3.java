package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.AxisAlignedBBBridge;
import com.moonsworth.lunar.bridge.horsestats.Horsestats20Extension;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public interface Bridge14_3 {
   int bridge$getMaximumRenderCount();

   int bridge$getUnculledRenderCount();

   int bridge$getRenderedEntityCount();

   int bridge$getRenderedBlockEntityCount();

   int bridge$getRenderedPlayersCount();

   void bridge$setNeedsFullRenderChunkUpdate(boolean flag1);

   void bridge$reloadChunks();

   default boolean bridge$hasRenderedAllChunks() {
      return true;
   }

   List<Bridge9_7> bridge$getRenderChunks();

   boolean bridge$isVisible(AxisAlignedBBBridge horsestats121);

   boolean bridge$isBlockVisible(int number1, int number2, int number3);

   @VersionGate(max = 0)
   default void bridge$makeEntityOutlineShader() {
   }

   @VersionGate(max = 0)
   default void bridge$renderEntityOutlineFramebuffer() {
   }

   @VersionGate(max = 0)
   default void bridge$bindEntityOutlineFbs(int number1, int number2) {
   }

   @VersionGate(max = 5)
   @Nullable
   default Bridge3_24 bridge$entityTarget() {
      throw new AbstractMethodErrorImpl();
   }

   boolean bridge$isInViewDistance(Horsestats20Extension horsestats20extension1, Horsestats20Extension horsestats20extension2);

   @VersionGate(min = 6)
   default boolean bridge$hasDestroyProgress(int number1, int number2, int number3) {
      throw new AbstractMethodErrorImpl();
   }
}
