package com.moonsworth.lunar.client.util.click;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;

public class Click10 {
   private static ByteBuffer field1 = null;

   public static ByteBuffer method1(int value) {
      ByteBuffer var1 = field1;
      if (var1 == null || var1.capacity() < value) {
         field1 = var1 = BufferUtils.createByteBuffer(value);
      }

      var1.position(0);
      var1.limit(var1.capacity());
      return var1;
   }
}
