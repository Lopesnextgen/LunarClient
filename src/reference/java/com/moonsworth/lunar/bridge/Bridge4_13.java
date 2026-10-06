package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import javax.annotation.Nullable;
import net.kyori.adventure.text.Component;

public interface Bridge4_13 {
   Component bridge$getText();

   long bridge$getAliveTime();

   @Nullable
   Vec3Bridge bridge$getLocation();
}
