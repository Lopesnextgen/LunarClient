package com.moonsworth.lunar.client.framework.feature.rewind;

import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui;
import java.util.function.Function;
import lombok.Generated;

public enum RewindType {
   ZLIB("zlib", false),
   ZSTD("zstd", true),
   LZ4("lz4", true),
   DYNAMIC("dynamic", true, var0 -> var0 >= Gui.field14 ? ZSTD : LZ4);

   private final String name;
   private final boolean storesUncompressedSize;
   private final Function<Integer, RewindType> dynamicCompressor;

   RewindType(String var3, boolean var4) {
      this.name = var3;
      this.storesUncompressedSize = var4;
      this.dynamicCompressor = var1x -> this;
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
   RewindType(String var3, boolean var4, Function<Integer, RewindType> function) {
      this.name = var3;
      this.storesUncompressedSize = var4;
      this.dynamicCompressor = function;
   }
}
