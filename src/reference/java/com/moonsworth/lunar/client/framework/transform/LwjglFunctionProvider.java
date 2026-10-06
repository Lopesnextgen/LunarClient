package com.moonsworth.lunar.client.framework.transform;

import com.moonsworth.lunar.ichor.URLClassLoader;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

public class LwjglFunctionProvider implements org.lwjgl.system.FunctionProvider {
   private static Class<?> field1;
   private final Method field2;

   public LwjglFunctionProvider() {
      try {
         this.field2 = field1.getDeclaredMethod("getFunctionAddress", String.class);
         this.field2.setAccessible(true);
      } catch (Exception var2) {
         throw new RuntimeException(var2);
      }
   }

   public long getFunctionAddress(CharSequence var1) {
      try {
         return (Long)this.field2.invoke(null, var1.toString());
      } catch (Exception var3) {
         throw new RuntimeException(var3);
      }
   }

   public long getFunctionAddress(ByteBuffer var1) {
      throw new UnsupportedOperationException();
   }

   static {
      if (!(LwjglFunctionProvider.class.getClassLoader() instanceof URLClassLoader)) {
         try {
            field1 = Class.forName("org.lwjgl.opengl.GLContext", true, LwjglFunctionProvider.class.getClassLoader());
         } catch (ReflectiveOperationException var1) {
            throw new RuntimeException(var1);
         }
      }
   }
}
