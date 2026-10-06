package com.moonsworth.lunar.client.replay.render;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_24;
import com.moonsworth.lunar.bridge.GlFenceSyncBridge;
import com.moonsworth.lunar.client.replay.render.ExportTargetHandler;
import com.moonsworth.lunar.client.framework.Ref;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.IdentityHashMap;
import java.util.concurrent.ArrayBlockingQueue;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL44;

public class PersistentPixelBufferPool extends FrameBufferPool {
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

   public PersistentPixelBufferPool() {
      while (this.field12.remainingCapacity() > 0) {
         this.field12.add(BufferUtils.createByteBuffer(0));
      }
   }

   @Override
   public void method1(ByteBuffer buffer1) {
      Integer index2 = this.method6(buffer1);
      if (index2 != null) {
         this.field7[index2] = false;
      } else {
         this.field12.put(buffer1);
      }
   }

   @Override
   public boolean isFull() {
      return this.field12.remainingCapacity() == 0;
   }

   @Override
   public boolean method2(com.moonsworth.lunar.client.replay.export.ExportSettings rewindhandlersnameplate1) {
      ExportTargetHandler rewindhandlers3impl82 = Ref.method4().method40().method85().method35().method48();
      Bridge3_24 bridge3_243 = rewindhandlers3impl82.method24().CHCHHIRHOORIOHCRORICRHIHCOOOII();
      GlFenceSyncBridge bridge_114 = Bridge.method67();
      this.method4(rewindhandlersnameplate1.getWidth(), rewindhandlersnameplate1.getHeight());
      int index5 = this.method7();
      if (index5 < 0) {
         return false;
      }

      GL11.glPixelStorei(3333, 1);
      GL11.glPixelStorei(3330, 0);
      GL11.glPixelStorei(3331, 0);
      GL11.glPixelStorei(3332, 0);
      GL15.glBindBuffer(35051, this.field3[index5]);
      int number6 = GL11.glGetInteger(32873);
      GL11.glBindTexture(3553, bridge3_243.bridge$getFramebufferTexture());
      GL11.glGetTexImage(3553, 0, 32993, 33639, 0L);
      if (this.field4[index5] != null) {
         bridge_114.method2(this.field4[index5]);
      }

      this.field4[index5] = bridge_114.method1(37143, 0);
      GL15.glBindBuffer(35051, 0);
      GL11.glBindTexture(3553, number6);
      this.field8[index5] = true;
      this.enqueue(index5);
      return true;
   }

   @Override
   public ByteBuffer method3() {
      if (this.field11 == 0) {
         return null;
      }

      int index1 = this.peek();
      Object obj2 = this.field4[index1];
      long number3 = Bridge.method67().method3(obj2, 0, 0L);
      if (number3 != 37147L && number3 != 37149L) {
         ByteBuffer buffer6 = this.field6 != null && this.field6[index1] != null ? this.field6[index1] : null;
         ByteBuffer buffer5;
         if (buffer6 != null) {
            ByteBuffer buffer7 = buffer6.duplicate();
            buffer7.clear().limit(this.field5);
            buffer7 = buffer7.slice();
            this.method5(buffer7, index1);
            this.field7[index1] = true;
            buffer5 = buffer7;
         } else {
            GL15.glBindBuffer(35051, this.field3[index1]);

            try {
               ByteBuffer buffer15 = this.field12.take();
               if (buffer15.capacity() < this.field5) {
                  buffer15 = BufferUtils.createByteBuffer(this.field5);
               }

               buffer15.clear();
               GL15.glGetBufferSubData(35051, 0L, buffer15);
               buffer15.limit(this.field5);
               buffer5 = buffer15;
            } catch (InterruptedException interruptedexception12) {
               Thread.currentThread().interrupt();
               return null;
            } finally {
               GL15.glBindBuffer(35051, 0);
            }
         }

         this.method8();
         this.field8[index1] = false;
         return buffer5;
      } else {
         return null;
      }
   }

   private void method4(int number1, int number2) {
      int number3 = number1 * number2 * 4;
      if (number1 != this.width || number2 != this.height || this.field3.length != 5) {
         if (this.field3.length > 0) {
            for (Object obj7 : this.field4) {
               if (obj7 != null) {
                  Bridge.method67().method2(obj7);
               }
            }

            IntBuffer intbuffer9 = BufferUtils.createIntBuffer(this.field3.length);

            for (int index8 : this.field3) {
               intbuffer9.put(index8);
            }

            intbuffer9.flip();
            GL15.glDeleteBuffers(intbuffer9);
         }

         this.width = number1;
         this.height = number2;
         this.field5 = number3;
         this.field3 = new int[5];
         this.field4 = new Object[5];
         this.field6 = new ByteBuffer[5];

         for (int index10 = 0; index10 < 5; index10++) {
            this.field7[index10] = false;
            this.field8[index10] = false;
         }

         this.field10 = 0;
         this.field11 = 0;

         for (int index11 = 0; index11 < 5; index11++) {
            this.field3[index11] = GL15.glGenBuffers();
            GL15.glBindBuffer(35051, this.field3[index11]);
            if (this.field14) {
               byte number13 = 65;
               GL44.glBufferStorage(35051, this.field5, number13);
               this.field6[index11] = GL30.glMapBufferRange(35051, 0L, this.field5, number13, null);
               if (this.field6[index11] == null) {
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
      for (int index1 = 0; index1 < 5; index1++) {
         if (this.field4.length > index1 && this.field4[index1] != null) {
            Bridge.method67().method2(this.field4[index1]);
            this.field4[index1] = null;
         }

         if (this.field3.length > index1 && this.field3[index1] != 0) {
            GL15.glDeleteBuffers(this.field3[index1]);
            this.field3[index1] = 0;
         }

         this.field7[index1] = false;
         this.field8[index1] = false;
      }

      this.width = -1;
      this.height = -1;
      this.field5 = 0;
   }

   private void method5(ByteBuffer buffer1, int number2) {
      synchronized (this.field13) {
         this.field13.put(buffer1, number2);
      }
   }

   private Integer method6(ByteBuffer buffer1) {
      synchronized (this.field13) {
         return this.field13.remove(buffer1);
      }
   }

   private int method7() {
      for (int index1 = 0; index1 < 5; index1++) {
         if (!this.field7[index1] && !this.field8[index1]) {
            return index1;
         }
      }

      return -1;
   }

   private void enqueue(int number1) {
      int index2 = (this.field10 + this.field11) % 5;
      this.field9[index2] = number1;
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
