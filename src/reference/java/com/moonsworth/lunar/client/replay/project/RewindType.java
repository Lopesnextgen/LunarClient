package com.moonsworth.lunar.client.replay.project;

import com.moonsworth.lunar.client.replay.project.RewindPaths;
import java.util.function.Function;
import lombok.Generated;

public enum RewindType {
   ZLIB("zlib", false),
   ZSTD("zstd", true),
   LZ4("lz4", true),
   DYNAMIC("dynamic", true, arg0 -> arg0 >= RewindPaths.field14 ? ZSTD : LZ4);

   private final String name;
   private final boolean storesUncompressedSize;
   private final Function<Integer, RewindType> dynamicCompressor;

   RewindType(String text3, boolean flag4) {
      this.name = text3;
      this.storesUncompressedSize = flag4;
      this.dynamicCompressor = arg1x -> this;
   }

   @Generated
   public String getName() {
      return this.name;
   }

   @Generated
   public boolean isStoresUncompressedSize() {
      return this.storesUncompressedSize;
   }

   @Generated
   public Function<Integer, RewindType> getDynamicCompressor() {
      return this.dynamicCompressor;
   }

   @Generated
   RewindType(String text3, boolean flag4, Function<Integer, RewindType> function5) {
      this.name = text3;
      this.storesUncompressedSize = flag4;
      this.dynamicCompressor = function5;
   }
}
