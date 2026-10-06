package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.GLSyncBridge;
import org.lwjgl.opengl.GL32;
import org.lwjgl.opengl.GLSync;

public class BridgeHandler implements GLSyncBridge {
   public Object method1(int var1, int var2) {
      return GL32.glFenceSync(var1, var2);
   }

   public void method2(Object var1) {
      GL32.glDeleteSync((GLSync)var1);
   }

   public int method3(Object var1, int var2, long value) {
      return GL32.glClientWaitSync((GLSync)var1, var2, value);
   }
}
