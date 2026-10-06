package com.moonsworth.lunar.bridge;

import com.google.gson.JsonObject;

public interface Bridge6_8 {
   Bridge5_13 bridge$getShaderUniform(String var1);

   @com.moonsworth.lunar.ichor.Annotation2(min = 30)
   default void bridge$processPassData(JsonObject var1) {
      throw new AbstractMethodErrorImpl();
   }

   default void bridge$bindSampler(String var1, Bridge8Extension bridge8) {
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
