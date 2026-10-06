package com.moonsworth.lunar.client.util.performance;

import com.lunarclient.websocket.performance.v1.RecordPerfSnapshotRequest.Builder;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.event.EventBusAccess;
import com.moonsworth.lunar.client.event.mixin.fishing.EventRenderTick.EventRenderTickEnd;
import com.moonsworth.lunar.client.framework.Ref;
import java.time.Duration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PerfSnapshotRecorder implements EventBusAccess {
   private static final PerfSnapshotRecorder field1 = new PerfSnapshotRecorder();
   private static final long field2 = Duration.ofSeconds(30L).toNanos();
   private long field3 = -1L;
   private final List<PerfSnapshotRecorder.Data> frames = new LinkedList<>();

   public PerfSnapshotRecorder() {
   }

   public void init() {
      this.handle(EventRenderTickEnd.class, this::method1);
   }

   private void method1(EventRenderTickEnd event) {
      if (this.field3 == -1L) {
         this.field3 = System.nanoTime();
      } else {
         long number2 = System.nanoTime();
         long number4 = number2 - this.field3;
         double value6 = number4 / 1000000.0;
         PerfSnapshotRecorder.Data data8 = new PerfSnapshotRecorder.Data(number2, value6);
         this.frames.add(data8);
         this.field3 = number2;
         this.method2(number2);
      }
   }

   private void method2(long number1) {
      Iterator iterator3 = this.frames.iterator();

      while (iterator3.hasNext()) {
         PerfSnapshotRecorder.Data data4 = (PerfSnapshotRecorder.Data)iterator3.next();
         if (number1 - data4.method1() <= field2) {
            break;
         }

         iterator3.remove();
      }
   }

   public void method3(Builder builder1) {
      if (!this.frames.isEmpty()) {
         double value2 = 0.0;
         double value4 = Double.MIN_VALUE;
         double value6 = Double.MAX_VALUE;

         for (PerfSnapshotRecorder.Data data9 : this.frames) {
            value2 += data9.method2();
            value4 = Math.max(value4, data9.method2());
            value6 = Math.min(value6, data9.method2());
         }

         double value10 = value2 / this.frames.size();
         builder1.setFrameTimeAverage((float)value10)
            .setFrameTimeFastest((float)value6)
            .setFrameTimeSlowest((float)value4)
            .setGraphicsBackend(Bridge.method42().method85().field2)
            .setSodiumPresent(Ref.hasModule("sodium"));
         this.frames.clear();
      }
   }

   public static PerfSnapshotRecorder method4() {
      return field1;
   }

   class Data {
      private final long field1;
      private final double field2;

      Data(long number1, double value) {
         this.field1 = number1;
         this.field2 = value;
      }

      public long method1() {
         return this.field1;
      }

      public double method2() {
         return this.field2;
      }
   }
}
