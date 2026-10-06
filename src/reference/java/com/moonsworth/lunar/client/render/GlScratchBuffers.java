package com.moonsworth.lunar.client.render;

import java.nio.IntBuffer;
import java.util.function.Consumer;
import org.lwjgl.system.MemoryUtil;

public class GlScratchBuffers {
   public static final GlScratchBuffers.Data field1 = new GlScratchBuffers.Data(10000);
   public static final GlScratchBuffers.Data field2 = new GlScratchBuffers.Data(1000);
   public static final GlScratchBuffers.Data field3 = new GlScratchBuffers.Data(200);

   public static class Data {
      private final int field1;
      private IntBuffer field2 = null;

      private Data(int var1) {
         this.field1 = var1;
      }

      private IntBuffer method1() {
         if (this.field2 == null) {
            this.field2 = MemoryUtil.memCallocInt(this.field1);
            this.field2.position(this.field1);
         }

         return this.field2;
      }

      public void method2(Consumer<IntBuffer> var1, boolean var2) {
         if (!var2 || this.field2 != null) {
            IntBuffer var3 = this.method1();
            int var4 = var3.position();
            if (var4 != 0) {
               var3.limit(var4);
               var3.position(0);
               var1.accept(var3);
               var3.limit(var3.capacity());
               var3.position(0);
            }
         }
      }

      public int method3(Consumer<IntBuffer> var1) {
         IntBuffer var2 = this.method1();
         if (!var2.hasRemaining()) {
            var2.position(0);
            var1.accept(var2);
            var2.position(0);
         }

         return var2.get();
      }
   }
}
