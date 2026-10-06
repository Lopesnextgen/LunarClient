package com.moonsworth.lunar.v1_7.optifine.wrapper;

import com.moonsworth.lunar.bridge.optifine.ShaderProgramBridge;
import lombok.Generated;

public class Slayer7Handler implements ShaderProgramBridge {
   private final int program;

   public int getId() {
      return this.program;
   }

   @Generated
   public Slayer7Handler(int value) {
      this.program = value;
   }
}
