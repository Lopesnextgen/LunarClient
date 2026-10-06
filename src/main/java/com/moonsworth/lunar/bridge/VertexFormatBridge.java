package com.moonsworth.lunar.bridge;

import it.unimi.dsi.fastutil.ints.IntList;

public interface VertexFormatBridge {
   default void bridge$setupBufferState() {
      throw new AbstractMethodErrorImpl();
   }

   default IntList bridge$getTexture0Elements() {
      throw new AbstractMethodErrorImpl();
   }

   default int bridge$getVertexSize() {
      throw new AbstractMethodErrorImpl();
   }

   default int method1() {
      return this.bridge$getVertexSize() / 4;
   }
}
