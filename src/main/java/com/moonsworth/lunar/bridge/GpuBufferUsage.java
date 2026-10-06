package com.moonsworth.lunar.bridge;

public enum GpuBufferUsage {
   COPY_DST(8),
   COPY_SRC(16),
   VERTEX(32),
   INDEX(64),
   UNIFORM(128),
   STORAGE_BUFFER(1073741824);

   public final int mask;

   GpuBufferUsage(int value) {
      this.mask = value;
   }
}
