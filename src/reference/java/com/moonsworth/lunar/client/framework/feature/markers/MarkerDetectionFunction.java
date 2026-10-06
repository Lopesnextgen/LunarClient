package com.moonsworth.lunar.client.framework.feature.markers;

import com.mojang.authlib.GameProfile;
import com.moonsworth.lunar.bridge.PlayerInfoBridge;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@FunctionalInterface
interface MarkerDetectionFunction {
   boolean check(@NotNull PlayerInfoBridge bridge2_331, @NotNull GameProfile gameprofile2, @Nullable MarkerModel markers3);
}
