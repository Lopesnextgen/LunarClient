package com.moonsworth.lunar.client.replay.render;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_24;
import com.moonsworth.lunar.bridge.TextureFormat;
import com.moonsworth.lunar.client.replay.render.ExportTargetHandler;
import com.moonsworth.lunar.client.framework.Ref;
import java.nio.ByteBuffer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import org.lwjgl.BufferUtils;

public class QueuedFrameBufferPool extends FrameBufferPool {
   private static final int field1 = 4;
   private static final int field2 = 5;
   private int width = -1;
   private int height = -1;
   private int field3 = 0;
   private final ArrayBlockingQueue<ByteBuffer> field4 = new ArrayBlockingQueue<>(5);
   private final ArrayBlockingQueue<ByteBuffer> field5 = new ArrayBlockingQueue<>(5);
   private final AtomicInteger field6 = new AtomicInteger(0);

   public QueuedFrameBufferPool() {
   }

   @Override
   public void method1(ByteBuffer buffer1) {
      if (buffer1 != null && buffer1.capacity() == this.field3) {
         this.field5.put(buffer1);
      }
   }

   @Override
   public boolean isFull() {
      return this.field6.get() + this.field4.size() >= 5;
   }

   @Override
   public boolean method2(com.moonsworth.lunar.client.replay.export.ExportSettings rewindhandlersnameplate1) {
      this.method4(rewindhandlersnameplate1.getWidth(), rewindhandlersnameplate1.getHeight());
      if (this.field6.get() >= 5) {
         return false;
      }

      ByteBuffer buffer2 = this.field5.poll();
      if (buffer2 == null) {
         return false;
      }

      ExportTargetHandler rewindhandlers3impl83 = Ref.method4().method40().method85().method35().method48();
      Bridge3_24 bridge3_244 = rewindhandlers3impl83.method24().CHCHHIRHOORIOHCRORICRHIHCOOOII();
      this.field6.incrementAndGet();
      Bridge.method8().method91(bridge3_244.bridge$getColorTexture(true), 0, 0, this.width, this.height, TextureFormat.RGBA8, arg2x -> {
         method5(arg2x, buffer2);
         this.field4.offer(buffer2);
         this.field6.decrementAndGet();
      });
      return true;
   }

   @Override
   public ByteBuffer method3() {
      return this.field4.poll();
   }

   @Override
   public void close() {
      this.field4.clear();
      this.field5.clear();
      this.field6.set(0);
      this.width = -1;
      this.height = -1;
      this.field3 = 0;
   }

   private void method4(int number1, int number2) {
      int number3 = number1 * number2 * 4;
      if (number1 != this.width || number2 != this.height || this.field3 != number3) {
         this.field4.clear();
         this.field5.clear();
         this.width = number1;
         this.height = number2;
         this.field3 = number3;

         for (int index4 = 0; index4 < 5; index4++) {
            this.field5.offer(BufferUtils.createByteBuffer(this.field3));
         }
      }
   }

   private static void method5(ByteBuffer buffer0, ByteBuffer buffer1) {
      buffer1.clear();
      int index2 = buffer0.position();
      int number3 = buffer0.limit();
      int number4 = number3 - index2;

      for (byte index5 = 0; index5 + 3 < number4; index5 += 4) {
         byte number6 = buffer0.get(index2 + index5);
         byte number7 = buffer0.get(index2 + index5 + 1);
         byte number8 = buffer0.get(index2 + index5 + 2);
         byte number9 = buffer0.get(index2 + index5 + 3);
         buffer1.put(number8).put(number7).put(number6).put(number9);
      }

      buffer1.flip();
   }
}
