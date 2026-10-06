package com.moonsworth.lunar.bridge;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.ichor.VersionGate;

public interface ShaderManagerBridge {
   Bridge5_13 bridge$getShaderUniform(String text1);

   @VersionGate(min = 30)
   default void bridge$processPassData(JsonObject json1) {
      throw new AbstractMethodErrorImpl();
   }

   default void bridge$bindSampler(String text1, Bridge8Extension bridge8) {
      throw new AbstractMethodErrorImpl();
   }

   default int bridge$getProgram() {
      throw new AbstractMethodErrorImpl();
   }

   default void bridge$apply() {
      throw new AbstractMethodErrorImpl();
   }

   default void bridge$clear() {
      throw new AbstractMethodErrorImpl();
   }

   default void bridge$close() {
   }
}
