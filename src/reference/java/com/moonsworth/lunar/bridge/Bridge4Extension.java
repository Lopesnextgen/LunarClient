package com.moonsworth.lunar.bridge;

import org.jetbrains.annotations.Range;

@Annotation(
   OIRHICCHORIRORCCOOCRRRHRRCRCCI = @BridgeVersionMapping(version = 6, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("com/mojang/blaze3d/vertex/BufferBuilder"))
)
@com.moonsworth.lunar.ichor.Annotation2(min = 6)
public interface Bridge4Extension extends Bridge4_6 {
   long bridge$getCapacity();

   long bridge$getBufferAllocatedSize();

   void bridge$resize(@Range(from = 1L, to = Long.MAX_VALUE) long var1);

   void bridge$close();

   @com.moonsworth.lunar.ichor.Annotation2(min = 24)
   Bridge2_44 bridge$buildOrThrow();

   default boolean method1() {
      long var1 = this.bridge$getBufferAllocatedSize();
      if (var1 <= 0L) {
         this.bridge$close();
         return false;
      } else {
         this.bridge$resize(var1);
         if (this.bridge$getCapacity() <= 0L) {
            this.bridge$close();
            return false;
         } else {
            return true;
         }
      }
   }
}
