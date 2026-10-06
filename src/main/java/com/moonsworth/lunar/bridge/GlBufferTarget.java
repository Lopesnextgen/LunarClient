package com.moonsworth.lunar.bridge;

import lombok.Generated;

public enum GlBufferTarget implements GlEnum {
   VERTICES(34962, true),
   INDICES(34963, true),
   PIXEL_PACK(35051, true),
   COPY_READ(36662, true),
   COPY_WRITE(36663, true),
   PIXEL_UNPACK(35052, true),
   UNIFORM(35345, true),
   TRANSFORM_FEEDBACK(35982, false),
   TEXTURE(35882, false),
   DRAW_INDIRECT(36671, false),
   ATOMIC_COUNTER(37568, false),
   DISPATCH_INDIRECT(37102, false),
   SHADER_STORAGE(37074, false),
   QUERY(37266, false),
   PARAMETER(33006, false);

   public static int USAGE_MAP_READ;
   public static int USAGE_MAP_WRITE;
   public static int USAGE_HINT_CLIENT_STORAGE;
   public static int USAGE_COPY_DST;
   public static int USAGE_COPY_SRC;
   public static int USAGE_VERTEX;
   public static int USAGE_INDEX;
   public static int USAGE_UNIFORM;
   public static int USAGE_UNIFORM_TEXEL_BUFFER;
   private final int glId;
   private final boolean supported;

   @Generated
   public int getGlId() {
      return this.glId;
   }

   @Generated
   public boolean isSupported() {
      return this.supported;
   }

   @Generated
   GlBufferTarget(int value, boolean flag) {
      this.glId = value;
      this.supported = flag;
   }
}
