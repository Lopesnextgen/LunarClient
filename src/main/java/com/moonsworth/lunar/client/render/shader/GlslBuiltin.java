package com.moonsworth.lunar.client.render.shader;

import com.moonsworth.lunar.bridge.GlslUniformType;
import lombok.Generated;

public enum GlslBuiltin {
   OUT_SIZE("OutSize", GlslUniformType.VEC2, false, false),
   TIME_SECONDS("TimeSeconds", GlslUniformType.FLOAT, false, false),
   GAME_TIME("GameTime", GlslUniformType.FLOAT, false, false),
   VELOCITY("Velocity", GlslUniformType.VEC3, true, false),
   VELOCITY_SMOOTH("VelocitySmooth", GlslUniformType.VEC3, true, false),
   LUNAR_PLUS_COLOR("LunarPlusColor", GlslUniformType.VEC3, true, false),
   CAMERA_ROTATION("CameraRot", GlslUniformType.VEC3, true, true),
   BIOME_COLOR("BiomeColor", GlslUniformType.VEC3, true, false);

   private final String varName;
   private final GlslUniformType type;
   private final boolean differentPerPlayer;
   private final boolean needsFrameRender;

   GlslBuiltin(String text, GlslUniformType glslUniformType, boolean flag, boolean flag2) {
      this.varName = text;
      this.type = glslUniformType;
      this.differentPerPlayer = flag;
      this.needsFrameRender = flag2;
   }

   @Generated
   public String getVarName() {
      return this.varName;
   }

   @Generated
   public GlslUniformType getType() {
      return this.type;
   }

   @Generated
   public boolean isDifferentPerPlayer() {
      return this.differentPerPlayer;
   }

   @Generated
   public boolean isNeedsFrameRender() {
      return this.needsFrameRender;
   }
}
