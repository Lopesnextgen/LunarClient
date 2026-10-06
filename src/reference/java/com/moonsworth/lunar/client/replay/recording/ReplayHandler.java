package com.moonsworth.lunar.client.replay.recording;

import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.recording.ReplayLocation;
import com.moonsworth.lunar.client.replay.network.ReplayPacketRegistry;
import com.moonsworth.lunar.client.replay.network.ReplayPacket;
import io.netty.buffer.Unpooled;
import java.io.DataOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.moonsworth.lunar.client.framework.feature.rewind.Rewind_3;
import com.moonsworth.lunar.client.framework.feature.rewind.Rewind3_2;
import com.moonsworth.lunar.client.replay.project.RewindType;
import com.moonsworth.lunar.client.replay.project.ReplayCompression;

public interface ReplayHandler {
   List<Integer> method1(List<File> list1);

   boolean method2();

   void pause();

   long method3();

   long method4();

   boolean method5();

   void method6(boolean flag1);

   void mark();

   void method7();

   ReplayLocation method8();

   void method9(ReplayPacket nameplate21, int number2);

   void method10();

   boolean method11();

   static void method12(ReplayPacket nameplate20, DataOutputStream output1, RewindType rewindtype2) {
      ByteBufLoader bytebufloader3 = new ByteBufLoader(Unpooled.buffer());

      try {
         int number4 = ReplayPacketRegistry.method1(0, (Class<? extends ReplayPacket>)nameplate20.getClass());
         if (number4 == -1) {
            throw new IllegalArgumentException("Packet not registered in protocol");
         }

         bytebufloader3.method11(number4);
         nameplate20.method2(bytebufloader3);
         int number5 = bytebufloader3.array().length;
         rewindtype2 = rewindtype2.getDynamicCompressor().apply(number5);

         byte[] items6 = switch (rewindtype2) {
            case ZLIB -> ReplayCompression.method1(bytebufloader3.array());
            case ZSTD -> Rewind3_2.method2(bytebufloader3.array());
            case LZ4 -> Rewind_3.method2(bytebufloader3.array());
            default -> throw new IllegalStateException("Unexpected value: " + rewindtype2);
         };
         int number7 = output1.size();
         ByteBufLoader.method13(output1, items6.length);
         if (rewindtype2.isStoresUncompressedSize()) {
            ByteBufLoader.method13(output1, number5);
         }

         output1.write(items6);
         output1.writeShort(output1.size() - number7);
      } finally {
         bytebufloader3.release();
      }
   }

   static List<Integer> method13(List<File> list0, List<File> list1) {
      ArrayList list2 = new ArrayList();

      for (File file4 : list0) {
         int index5 = list1.indexOf(file4);
         if (index5 == -1) {
            list2.add(list1.size());
            list1.add(file4);
         } else {
            list2.add(index5);
         }
      }

      return list2;
   }
}
