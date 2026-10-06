package com.moonsworth.webosr.wrappers;

import com.moonsworth.webosr.config.ThreadConfig;
import com.moonsworth.webosr.config.UltralightConfig;
import com.moonsworth.webosr.handler.Logger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class WebOSR {
   public WebOSR() {
   }

   public static void loadLibraries() {
      System.loadLibrary("UltralightCore");
      System.loadLibrary("WebCore");
      System.loadLibrary("Ultralight");
      System.loadLibrary("WebOSR-Binding");
   }

   public static native WebEngine createEngine(Logger logger0, UltralightConfig ultralightconfig1, ThreadConfig threadconfig2);

   public static ByteBuffer allocateByteBuffer(int value) {
      return ByteBuffer.allocateDirect(value).order(ByteOrder.nativeOrder());
   }
}
