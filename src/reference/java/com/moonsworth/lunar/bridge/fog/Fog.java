package com.moonsworth.lunar.bridge.fog;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.ichor.Annotation2;
import org.jetbrains.annotations.Nullable;

public interface Fog {
   boolean bridge$getIsPotionDurationMax();

   int bridge$getPotionID();

   @Nullable
   Fog2 bridge$getPotion();

   float bridge$getDuration();

   int bridge$getMaxDuration();

   @Annotation2(min = 6)
   default void bridge$inheritMaxDuration(Fog var1) {
   }

   int bridge$getAmplifier();

   String bridge$getEffectName();

   boolean bridge$getIsAmbient();

   default boolean bridge$shouldShowIcon() {
      return true;
   }

   @Annotation2(min = 6)
   default void method1(MixinHelper_4 var1, float var2, float var3) {
      this.bridge$renderEffectIcon(var1, var2, var3, -1);
   }

   @Annotation2(min = 6)
   default void bridge$renderEffectIcon(MixinHelper_4 var1, float var2, float var3, int value) {
   }

   @Annotation2(min = 6)
   default int bridge$getColor() {
      return -1;
   }
}
