package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.GlFenceSyncBridge;
import org.lwjgl.opengl.GL32;
import org.lwjgl.opengl.GLSync;

public class GlFenceSyncBridgeImpl implements GlFenceSyncBridge {
   public GlFenceSyncBridgeImpl() {
   }

   public Object method1(int value, int number2) {
      return GL32.glFenceSync(value, number2);
   }

   public void method2(Object obj1) {
      GL32.glDeleteSync((GLSync)obj1);
   }

   public int method3(Object obj1, int number2, long value) {
      return GL32.glClientWaitSync((GLSync)obj1, number2, value);
   }
}
