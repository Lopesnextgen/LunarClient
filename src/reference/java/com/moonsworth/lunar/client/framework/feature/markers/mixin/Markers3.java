package com.moonsworth.lunar.client.framework.feature.markers.mixin;

import com.mojang.authlib.GameProfile;
import com.moonsworth.lunar.bridge.Bridge2_33;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@FunctionalInterface
interface Markers3 {
   boolean check(@NotNull Bridge2_33 var1, @NotNull GameProfile var2, @Nullable Markers var3);
}
