package com.moonsworth.lunar.bridge.potion;

import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.ichor.VersionGate;
import org.jetbrains.annotations.Nullable;

public interface PotionEffectBridge extends com.moonsworth.lunar.bridge.fog.Fog {
   boolean bridge$getIsPotionDurationMax();

   int bridge$getPotionID();

   @Nullable
   PotionBridge bridge$getPotion();

   float bridge$getDuration();

   int bridge$getMaxDuration();

   @VersionGate(min = 6)
   default void bridge$inheritMaxDuration(PotionEffectBridge fog1) {
   }

   int bridge$getAmplifier();

   String bridge$getEffectName();

   boolean bridge$getIsAmbient();

   default boolean bridge$shouldShowIcon() {
      return true;
   }

   @VersionGate(min = 6)
   default void method1(MixinHelper_4 mixinhelper_41, float value2, float value3) {
      this.bridge$renderEffectIcon(mixinhelper_41, value2, value3, -1);
   }

   @VersionGate(min = 6)
   default void bridge$renderEffectIcon(MixinHelper_4 mixinhelper_41, float value2, float value3, int value) {
   }

   @VersionGate(min = 6)
   default int bridge$getColor() {
      return -1;
   }
}
