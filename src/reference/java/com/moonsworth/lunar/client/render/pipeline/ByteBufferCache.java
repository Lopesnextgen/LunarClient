package com.moonsworth.lunar.client.render.pipeline;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;

public class ByteBufferCache {
   private static ByteBuffer buffer = null;

   public ByteBufferCache() {
   }

   public static ByteBuffer getBuffer(int value) {
      ByteBuffer buffer1 = field1;
      if (buffer1 == null || buffer1.capacity() < value) {
         field1 = buffer1 = BufferUtils.createByteBuffer(value);
      }

      buffer1.position(0);
      buffer1.limit(buffer1.capacity());
      return buffer1;
   }
}
