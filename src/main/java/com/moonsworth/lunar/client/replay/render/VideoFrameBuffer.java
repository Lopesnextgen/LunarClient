package com.moonsworth.lunar.client.replay.render;

import java.nio.ByteBuffer;

public class VideoFrameBuffer {
   private final ByteBuffer field1;
   private final int field2;
   private final int field3;

   public VideoFrameBuffer(ByteBuffer buffer1, int value, int value2) {
      this.field1 = buffer1;
      this.field2 = value;
      this.field3 = value2;
   }

   public ByteBuffer buffer() {
      return this.field1;
   }

   public int method1() {
      return this.field2;
   }

   public int method2() {
      return this.field3;
   }
}
