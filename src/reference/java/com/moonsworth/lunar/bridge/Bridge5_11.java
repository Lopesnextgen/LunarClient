package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import java.util.Optional;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3d;

public interface Bridge5_11 extends Bridge6_10 {
   @Nullable
   ResourceLocationBridge bridge$getCapeLocation();

   void bridge$setCapeLocation(ResourceLocationBridge var1);

   void bridge$setSkinLocation(ResourceLocationBridge var1, String var2);

   void bridge$setCapeLocationOverride(ResourceLocationBridge var1);

   default boolean bridge$isOptifineCape() {
      return false;
   }

   Optional<String> bridge$loadAndGetRealSkinType();

   float bridge$getSwingProgress(float var1);

   void bridge$setSkinLocationOverride(@Nullable ResourceLocationBridge var1, String var2);

   ResourceLocationBridge bridge$getLocationSkinDefault();

   @Override
   Optional<String> bridge$getTeamName();

   default Vec3Bridge bridge$getLastReportedLookAngle() {
      return this.bridge$getLastReportedLookAngle();
   }

   default Vector3d bridge$getLastReportedPos() {
      return this.bridge$getPosition();
   }
}
