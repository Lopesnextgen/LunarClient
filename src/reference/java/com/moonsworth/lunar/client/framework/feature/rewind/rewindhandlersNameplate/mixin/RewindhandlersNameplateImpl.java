package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.mixin;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_24;
import com.moonsworth.lunar.bridge.TexturePixelFormat;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.rewindhandlersCore.RewindHandlers3Impl8;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.nio.ByteBuffer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import org.lwjgl.BufferUtils;

public class RewindhandlersNameplateImpl extends RewindhandlersNameplate {
   private static final int field1 = 4;
   private static final int field2 = 5;
   private int width = -1;
   private int height = -1;
   private int field3 = 0;
   private final ArrayBlockingQueue<ByteBuffer> field4 = new ArrayBlockingQueue<>(5);
   private final ArrayBlockingQueue<ByteBuffer> field5 = new ArrayBlockingQueue<>(5);
   private final AtomicInteger field6 = new AtomicInteger(0);

   @Override
   public void method1(ByteBuffer var1) {
      if (var1 != null && var1.capacity() == this.field3) {
         this.field5.put(var1);
      }
   }

   @Override
   public boolean isFull() {
      return this.field6.get() + this.field4.size() >= 5;
   }

   @Override
   public boolean method2(com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.RewindhandlersNameplate var1) {
      this.method4(var1.getWidth(), var1.getHeight());
      if (this.field6.get() >= 5) {
         return false;
      }

      ByteBuffer var2 = this.field5.poll();
      if (var2 == null) {
         return false;
      }

      RewindHandlers3Impl8 var3 = ThreadModuleDump63.method4().method40().method85().method35().method48();
      Bridge3_24 var4 = var3.method24().method11();
      this.field6.incrementAndGet();
      Bridge.method8().method91(var4.bridge$getColorTexture(true), 0, 0, this.width, this.height, TexturePixelFormat.RGBA8, var2x -> {
         method5(var2x, var2);
         this.field4.offer(var2);
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

   private void method4(int var1, int var2) {
      int var3 = var1 * var2 * 4;
      if (var1 != this.width || var2 != this.height || this.field3 != var3) {
         this.field4.clear();
         this.field5.clear();
         this.width = var1;
         this.height = var2;
         this.field3 = var3;

         for (int var4 = 0; var4 < 5; var4++) {
            this.field5.offer(BufferUtils.createByteBuffer(this.field3));
         }
      }
   }

   private static void method5(ByteBuffer var0, ByteBuffer var1) {
      var1.clear();
      int var2 = var0.position();
      int var3 = var0.limit();
      int var4 = var3 - var2;

      for (byte var5 = 0; var5 + 3 < var4; var5 += 4) {
         byte var6 = var0.get(var2 + var5);
         byte var7 = var0.get(var2 + var5 + 1);
         byte var8 = var0.get(var2 + var5 + 2);
         byte var9 = var0.get(var2 + var5 + 3);
         var1.put(var8).put(var7).put(var6).put(var9);
      }

      var1.flip();
   }
}
