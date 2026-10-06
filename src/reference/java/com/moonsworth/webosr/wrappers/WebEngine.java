package com.moonsworth.webosr.wrappers;

import com.moonsworth.webosr.NativeHandle;
import com.moonsworth.webosr.config.BrowserConfig;
import com.moonsworth.webosr.javascript.FunctionBus;
import com.moonsworth.webosr.wrappers.image.TexturedImageSource;

public final class WebEngine extends NativeHandle {
   private final Thread owner = Thread.currentThread();

   private WebEngine(long number1) {
      super(number1);
   }

   public void invokeIteration() {
      if (this.owner.equals(Thread.currentThread())) {
         FunctionBus.consumeMainActorQueue();
      }

      this.invokeIteration0();
   }

   public native TexturedImageSource createImageSourceTextured(String text1);

   public native Session createSession(String text1, boolean flag2);

   public native Browser createBrowser(BrowserConfig browserconfig1);

   public native PlatformInterface getPlatformInterface();

   public native void setFPS(int number1);

   protected native void destroy();

   private native void invokeIteration0();
}
