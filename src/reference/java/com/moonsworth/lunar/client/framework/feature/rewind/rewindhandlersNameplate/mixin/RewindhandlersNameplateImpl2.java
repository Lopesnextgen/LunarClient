package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_24;
import com.moonsworth.lunar.bridge.GLSyncBridge;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.rewindhandlersCore.RewindHandlers3Impl8;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.IdentityHashMap;
import java.util.concurrent.ArrayBlockingQueue;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL44;

public class RewindhandlersNameplateImpl2 extends RewindhandlersNameplate {
   private static final int field1 = 4;
   private static final int field2 = 5;
   private int[] field3 = new int[0];
   private Object[] field4 = new Object[0];
   private int width = -1;
   private int height = -1;
   private int field5 = 0;
   private ByteBuffer[] field6 = new ByteBuffer[0];
   private final boolean[] field7 = new boolean[5];
   private final boolean[] field8 = new boolean[5];
   private final int[] field9 = new int[5];
   private int field10 = 0;
   private int field11 = 0;
   private final ArrayBlockingQueue<ByteBuffer> field12 = new ArrayBlockingQueue<>(5);
   private final IdentityHashMap<ByteBuffer, Integer> field13 = new IdentityHashMap<>();
   private final boolean field14 = Bridge.method22().method20() || Bridge.method22().method19();

   public RewindhandlersNameplateImpl2() {
      while (this.field12.remainingCapacity() > 0) {
         this.field12.add(BufferUtils.createByteBuffer(0));
      }
   }

   @Override
   public void method1(ByteBuffer var1) {
      Integer var2 = this.method6(var1);
      if (var2 != null) {
         this.field7[var2] = false;
      } else {
         this.field12.put(var1);
      }
   }

   @Override
   public boolean isFull() {
      return this.field12.remainingCapacity() == 0;
   }

   @Override
   public boolean method2(com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.RewindhandlersNameplate var1) {
      RewindHandlers3Impl8 var2 = ThreadModuleDump63.method4().method40().method85().method35().method48();
      Bridge3_24 var3 = var2.method24().method11();
      GLSyncBridge var4 = Bridge.method67();
      this.method4(var1.getWidth(), var1.getHeight());
      int var5 = this.method7();
      if (var5 < 0) {
         return false;
      }

      GL11.glPixelStorei(3333, 1);
      GL11.glPixelStorei(3330, 0);
      GL11.glPixelStorei(3331, 0);
      GL11.glPixelStorei(3332, 0);
      GL15.glBindBuffer(35051, this.field3[var5]);
      int var6 = GL11.glGetInteger(32873);
      GL11.glBindTexture(3553, var3.bridge$getFramebufferTexture());
      GL11.glGetTexImage(3553, 0, 32993, 33639, 0L);
      if (this.field4[var5] != null) {
         var4.method2(this.field4[var5]);
      }

      this.field4[var5] = var4.method1(37143, 0);
      GL15.glBindBuffer(35051, 0);
      GL11.glBindTexture(3553, var6);
      this.field8[var5] = true;
      this.enqueue(var5);
      return true;
   }

   @Override
   public ByteBuffer method3() {
      if (this.field11 == 0) {
         return null;
      }

      int var1 = this.peek();
      Object var2 = this.field4[var1];
      long var3 = Bridge.method67().method3(var2, 0, 0L);
      if (var3 != 37147L && var3 != 37149L) {
         ByteBuffer var6 = this.field6 != null && this.field6[var1] != null ? this.field6[var1] : null;
         ByteBuffer var5;
         if (var6 != null) {
            ByteBuffer var7 = var6.duplicate();
            var7.clear().limit(this.field5);
            var7 = var7.slice();
            this.method5(var7, var1);
            this.field7[var1] = true;
            var5 = var7;
         } else {
            GL15.glBindBuffer(35051, this.field3[var1]);

            try {
               ByteBuffer var15 = this.field12.take();
               if (var15.capacity() < this.field5) {
                  var15 = BufferUtils.createByteBuffer(this.field5);
               }

               var15.clear();
               GL15.glGetBufferSubData(35051, 0L, var15);
               var15.limit(this.field5);
               var5 = var15;
            } catch (InterruptedException var12) {
               Thread.currentThread().interrupt();
               return null;
            } finally {
               GL15.glBindBuffer(35051, 0);
            }
         }

         this.method8();
         this.field8[var1] = false;
         return var5;
      } else {
         return null;
      }
   }

   private void method4(int var1, int var2) {
      int var3 = var1 * var2 * 4;
      if (var1 != this.width || var2 != this.height || this.field3.length != 5) {
         if (this.field3.length > 0) {
            for (Object var7 : this.field4) {
               if (var7 != null) {
                  Bridge.method67().method2(var7);
               }
            }

            IntBuffer var9 = BufferUtils.createIntBuffer(this.field3.length);

            for (int var8 : this.field3) {
               var9.put(var8);
            }

            var9.flip();
            GL15.glDeleteBuffers(var9);
         }

         this.width = var1;
         this.height = var2;
         this.field5 = var3;
         this.field3 = new int[5];
         this.field4 = new Object[5];
         this.field6 = new ByteBuffer[5];

         for (int var10 = 0; var10 < 5; var10++) {
            this.field7[var10] = false;
            this.field8[var10] = false;
         }

         this.field10 = 0;
         this.field11 = 0;

         for (int var11 = 0; var11 < 5; var11++) {
            this.field3[var11] = GL15.glGenBuffers();
            GL15.glBindBuffer(35051, this.field3[var11]);
            if (this.field14) {
               byte var13 = 65;
               GL44.glBufferStorage(35051, this.field5, var13);
               this.field6[var11] = GL30.glMapBufferRange(35051, 0L, this.field5, var13, null);
               if (this.field6[var11] == null) {
                  GL15.glBufferData(35051, this.field5, 35041);
               }
            } else {
               GL15.glBufferData(35051, this.field5, 35041);
            }
         }

         GL15.glBindBuffer(35051, 0);
         GL11.glPixelStorei(3333, 1);
         GL11.glPixelStorei(3317, 1);
      }
   }

   @Override
   public void close() {
      for (int var1 = 0; var1 < 5; var1++) {
         if (this.field4.length > var1 && this.field4[var1] != null) {
            Bridge.method67().method2(this.field4[var1]);
            this.field4[var1] = null;
         }

         if (this.field3.length > var1 && this.field3[var1] != 0) {
            GL15.glDeleteBuffers(this.field3[var1]);
            this.field3[var1] = 0;
         }

         this.field7[var1] = false;
         this.field8[var1] = false;
      }

      this.width = -1;
      this.height = -1;
      this.field5 = 0;
   }

   private void method5(ByteBuffer var1, int var2) {
      synchronized (this.field13) {
         this.field13.put(var1, var2);
      }
   }

   private Integer method6(ByteBuffer var1) {
      synchronized (this.field13) {
         return this.field13.remove(var1);
      }
   }

   private int method7() {
      for (int var1 = 0; var1 < 5; var1++) {
         if (!this.field7[var1] && !this.field8[var1]) {
            return var1;
         }
      }

      return -1;
   }

   private void enqueue(int var1) {
      int var2 = (this.field10 + this.field11) % 5;
      this.field9[var2] = var1;
      if (this.field11 < 5) {
         this.field11++;
      } else {
         this.field10 = (this.field10 + 1) % 5;
      }
   }

   private int peek() {
      return this.field11 == 0 ? 0 : this.field9[this.field10];
   }

   private void method8() {
      if (this.field11 > 0) {
         this.field10 = (this.field10 + 1) % 5;
         this.field11--;
      }
   }
}
