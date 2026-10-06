package com.moonsworth.lunar.client.framework.feature.rewind;

import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui7;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2;
import io.netty.buffer.Unpooled;
import java.io.DataOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public interface Rewind_4 {
   List<Integer> method1(List<File> var1);

   boolean method2();

   void pause();

   long method3();

   long method4();

   boolean method5();

   void method6(boolean var1);

   void mark();

   void method7();

   Gui7 method8();

   void method9(Nameplate2 var1, int var2);

   void method10();

   boolean method11();

   static void method12(Nameplate2 var0, DataOutputStream var1, RewindType var2) {
      ByteBufLoader var3 = new ByteBufLoader(Unpooled.buffer());

      try {
         int var4 = Nameplate.method1(0, (Class<? extends Nameplate2>)var0.getClass());
         if (var4 == -1) {
            throw new IllegalArgumentException("Packet not registered in protocol");
         }

         var3.method11(var4);
         var0.method2(var3);
         int var5 = var3.array().length;
         var2 = var2.getDynamicCompressor().apply(var5);

         byte[] var6 = switch (var2) {
            case ZLIB -> Rewind2_2.method1(var3.array());
            case ZSTD -> Rewind3_2.method2(var3.array());
            case LZ4 -> Rewind_3.method2(var3.array());
            default -> throw new IllegalStateException("Unexpected value: " + var2);
         };
         int var7 = var1.size();
         ByteBufLoader.method13(var1, var6.length);
         if (var2.isStoresUncompressedSize()) {
            ByteBufLoader.method13(var1, var5);
         }

         var1.write(var6);
         var1.writeShort(var1.size() - var7);
      } finally {
         var3.release();
      }
   }

   static List<Integer> method13(List<File> var0, List<File> var1) {
      ArrayList var2 = new ArrayList();

      for (File var4 : var0) {
         int var5 = var1.indexOf(var4);
         if (var5 == -1) {
            var2.add(var1.size());
            var1.add(var4);
         } else {
            var2.add(var5);
         }
      }

      return var2;
   }
}
