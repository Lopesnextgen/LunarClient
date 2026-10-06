package com.moonsworth.lunar.client.replay.render;

import java.nio.ByteBuffer;

public abstract class FrameBufferPool {
   public FrameBufferPool() {
   }

   public abstract void method1(ByteBuffer buffer1);

   public abstract boolean isFull();

   public abstract boolean method2(com.moonsworth.lunar.client.replay.export.ExportSettings rewindhandlersnameplate1);

   public abstract ByteBuffer method3();

   public abstract void close();
}
