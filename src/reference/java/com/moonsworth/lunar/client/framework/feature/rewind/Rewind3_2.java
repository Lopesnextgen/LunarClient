package com.moonsworth.lunar.client.framework.feature.rewind;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.nio.ByteBuffer;
import org.lwjgl.actually3.BufferUtils;
import org.lwjgl.util.zstd.Zstd;

public class Rewind3_2 {
   private static final ThreadLocal<ByteBuffer> field1 = ThreadLocal.withInitial(() -> BufferUtils.createByteBuffer(0));
   private static final ThreadLocal<ByteBuffer> field2 = ThreadLocal.withInitial(() -> BufferUtils.createByteBuffer(0));
   private static final ThreadLocal<byte[]> field3 = ThreadLocal.withInitial(() -> new byte[0]);

   private static ByteBuffer method1(ThreadLocal<ByteBuffer> var0, int var1) {
      ByteBuffer var2 = (ByteBuffer)var0.get();
      if (var2.capacity() < var1) {
         var2 = BufferUtils.createByteBuffer(var1);
         var0.set(var2);
      } else {
         var2.clear();
      }

      return var2;
   }

   public static byte[] method2(byte[] var0) {
      long var1 = Zstd.ZSTD_compressBound(var0.length);
      ByteBuffer var3 = method1(field1, var0.length);
      var3.put(var0);
      var3.flip();
      ByteBuffer var4 = method1(field2, (int)var1);
      long var5 = Zstd.ZSTD_compress(var4, var3, 10);
      if (Zstd.ZSTD_isError(var5)) {
         throw new RuntimeException(Zstd.ZSTD_getErrorName(var5));
      }

      byte[] var7 = new byte[(int)var5];
      var4.get(var7);
      return var7;
   }

   public static byte[] method3(byte[] var0, int var1) {
      ByteBuffer var2 = method1(field1, var0.length);
      var2.put(var0);
      var2.flip();
      ByteBuffer var3 = method1(field2, var1);
      long var4 = Zstd.ZSTD_decompress(var3, var2);
      if (Zstd.ZSTD_isError(var4)) {
         throw new RuntimeException(Zstd.ZSTD_getErrorName(var4));
      }

      byte[] var6 = new byte[var1];
      var3.get(var6);
      return var6;
   }

   public static ByteBuf method4(byte[] var0, int var1) {
      ByteBuffer var2 = method1(field1, var0.length);
      var2.put(var0);
      var2.flip();
      ByteBuffer var3 = method1(field2, var1);
      long var4 = Zstd.ZSTD_decompress(var3, var2);
      if (Zstd.ZSTD_isError(var4)) {
         throw new RuntimeException(Zstd.ZSTD_getErrorName(var4));
      }

      byte[] var6 = field3.get();
      if (var6.length < var1) {
         var6 = new byte[var1];
         field3.set(var6);
      }

      var3.get(var6, 0, var1);
      return Unpooled.wrappedBuffer(var6, 0, var1);
   }
}
