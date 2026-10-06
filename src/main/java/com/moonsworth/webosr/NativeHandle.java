package com.moonsworth.webosr;

public abstract class NativeHandle implements AutoCloseable {
   private long handle;

   protected NativeHandle(long value) {
      this.handle = value;
   }

   @Override
   public void close() {
      if (this.handle != 0L) {
         this.destroy();
         this.handle = 0L;
      }
   }

   protected abstract void destroy();
}
