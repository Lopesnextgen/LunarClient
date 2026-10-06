package com.moonsworth.lunar.bridge.horsestats;

import com.moonsworth.lunar.bridge.Bridge;
import org.jetbrains.annotations.Contract;
import org.joml.Vector3i;
import org.joml.Vector3ic;

public interface Horsestats20Extension2 extends Vec3iBridge {
   @Contract("_,_,_ -> new")
   default Horsestats20Extension2 bridge$offset(int value, int value2, int value3) {
      return this.bridge$add(new Vector3i(value, value2, value3));
   }

   @Contract("_ -> new")
   Horsestats20Extension2 bridge$add(Vector3ic vector3ic1);

   @Contract("-> new")
   default Horsestats20Extension2 bridge$above() {
      return Bridge.method8().method4(this.bridge$getX(), this.bridge$getY() + 1, this.bridge$getZ());
   }

   @Contract("-> new")
   default Horsestats20Extension2 bridge$below() {
      return Bridge.method8().method4(this.bridge$getX(), this.bridge$getY() - 1, this.bridge$getZ());
   }

   long bridge$asLong();
}
