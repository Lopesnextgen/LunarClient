package com.moonsworth.lunar.client.util.performance;

import com.lunarclient.websocket.performance.v1.RecordPerfSnapshotRequest.Builder;
import com.moonsworth.lunar.client.util.LunarLogger;
import it.unimi.dsi.fastutil.longs.LongArrayList;
import it.unimi.dsi.fastutil.longs.LongIterator;
import it.unimi.dsi.fastutil.longs.LongList;
import java.io.File;
import jdk.jfr.consumer.RecordingStream;
import org.apache.commons.io.FileUtils;

public class GcMonitor {
   private static final GcMonitor field1 = new GcMonitor();
   private final LongList field2 = new LongArrayList();

   public GcMonitor() {
   }

   public void init() {
      try {
         FileUtils.deleteDirectory(new File("jfr"));
      } catch (Throwable exception2) {
         LunarLogger.error("Failed to delete jfr directory", exception2);
      }

      RecordingStream recordingstream1 = new RecordingStream();
      recordingstream1.enable("jdk.GarbageCollection");
      recordingstream1.onEvent("jdk.GarbageCollection", arg1x -> {
         long index2x = arg1x.getDuration("sumOfPauses").toNanos() / 1000L;
         this.field2.add(index2x);
      });
      recordingstream1.startAsync();
   }

   public void method1(Builder builder1) {
      if (!this.field2.isEmpty()) {
         long number2 = Long.MIN_VALUE;
         long number4 = Long.MAX_VALUE;
         long number6 = 0L;
         LongIterator longiterator8 = this.field2.longIterator();

         while (longiterator8.hasNext()) {
            long number9 = longiterator8.nextLong();
            number2 = Math.max(number2, number9);
            number4 = Math.min(number4, number9);
            number6 += number9;
         }

         long number11 = this.field2.getLong(this.field2.size() / 2);
         builder1.setGcCycles(this.field2.size());
         builder1.setLongestGcMicro((int)number2);
         builder1.setShortestGcMicro((int)number4);
         builder1.setAvgGcMicro((int)(number6 / this.field2.size()));
         builder1.setMedianGcMicro((int)number11);
         this.field2.clear();
      }
   }

   public static GcMonitor method2() {
      return field1;
   }
}
