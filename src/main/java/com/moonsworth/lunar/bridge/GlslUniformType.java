package com.moonsworth.lunar.bridge;

import lombok.Generated;

public enum GlslUniformType {
   BOOL(1, "bool", BridgeType$Type.BOOL, false),
   BVEC2(2, "bvec2", BridgeType$Type.BOOL, false),
   BVEC3(3, "bvec3", BridgeType$Type.BOOL, false),
   BVEC4(4, "bvec4", BridgeType$Type.BOOL, false),
   INT(1, "int", BridgeType$Type.INT, true),
   IVEC2(2, "ivec2", BridgeType$Type.INT, false),
   IVEC3(3, "ivec3", BridgeType$Type.INT, true),
   IVEC4(4, "ivec4", BridgeType$Type.INT, false),
   UINT(1, "uint", BridgeType$Type.UINT, false),
   UVEC2(2, "uvec2", BridgeType$Type.UINT, false),
   UVEC3(3, "uvec3", BridgeType$Type.UINT, false),
   UVEC4(4, "uvec4", BridgeType$Type.UINT, false),
   FLOAT(1, "float", BridgeType$Type.FLOAT, true),
   VEC2(2, "vec2", BridgeType$Type.FLOAT, true),
   VEC3(3, "vec3", BridgeType$Type.FLOAT, true),
   VEC4(4, "vec4", BridgeType$Type.FLOAT, true),
   DOUBLE(1, "double", BridgeType$Type.DOUBLE, false),
   DVEC2(2, "dvec2", BridgeType$Type.DOUBLE, false),
   DVEC3(3, "dvec3", BridgeType$Type.DOUBLE, false),
   DVEC4(4, "dvec4", BridgeType$Type.DOUBLE, false),
   MATRIX2X2(4, "matrix2x2", BridgeType$Type.FLOAT, false),
   MATRIX3X2(6, "matrix3x2", BridgeType$Type.FLOAT, false),
   MATRIX4X2(8, "matrix4x2", BridgeType$Type.FLOAT, false),
   MATRIX2X3(6, "matrix2x3", BridgeType$Type.FLOAT, false),
   MATRIX3X3(9, "matrix3x3", BridgeType$Type.FLOAT, false),
   MATRIX4X3(12, "matrix4x3", BridgeType$Type.FLOAT, false),
   MATRIX2X4(8, "matrix2x4", BridgeType$Type.FLOAT, false),
   MATRIX3X4(12, "matrix3x4", BridgeType$Type.FLOAT, false),
   MATRIX4X4(16, "matrix4x4", BridgeType$Type.FLOAT, true),
   TEXEL_BUFFER(16, "texel_buffer", BridgeType$Type.FLOAT, false),
   UNIFORM_BUFFER(16, "uniform_buffer", BridgeType$Type.FLOAT, false);

   private final int count;
   private final String name;
   private final BridgeType$Type scalarType;
   private final boolean supportedByMojang;

   public boolean isMatrix() {
      return this.ordinal() >= 20 && this.ordinal() <= 28;
   }

   public String asMojangType() {
      if (!this.supportedByMojang) {
         throw new UnsupportedOperationException("Can't get mojang type for uniform type: " + this.name);
      } else {
         return this.isMatrix() ? this.name : this.scalarType.name().toLowerCase();
      }
   }

   public String asGlslType() {
      if (this.isMatrix()) {
         return switch (this) {
            case MATRIX2X2 -> "mat2";
            case MATRIX3X3 -> "mat3";
            case MATRIX4X4 -> "mat4";
            default -> this.name.replace("matrix", "mat");
         };
      } else {
         return this.name;
      }
   }

   @Generated
   public int getCount() {
      return this.count;
   }

   @Generated
   public String getName() {
      return this.name;
   }

   @Generated
   public BridgeType$Type getScalarType() {
      return this.scalarType;
   }

   @Generated
   public boolean isSupportedByMojang() {
      return this.supportedByMojang;
   }

   @Generated
   GlslUniformType(int value, String text, BridgeType$Type type, boolean flag) {
      this.count = value;
      this.name = text;
      this.scalarType = type;
      this.supportedByMojang = flag;
   }
}
