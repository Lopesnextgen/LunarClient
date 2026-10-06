package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod;

import com.moonsworth.lunar.files.ValuePair;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public class FpsDebugCollector {
   private final List<FpsDebugTask> field1 = new ArrayList<>();

   public FpsDebugCollector() {
   }

   public void clear() {
      this.field1.clear();
   }

   public FpsDebugCollector method1(FpsDebugTask fpsdebugmod21) {
      this.field1.add(fpsdebugmod21);
      return this;
   }

   public FpsDebugCollector method2(List<FpsDebugTask> list) {
      this.field1.addAll(list);
      return this;
   }

   public Duration method3() {
      return Arrays.stream(FpsDebugPhase.values()).map(this::method4).reduce(Duration.ZERO, Duration::plus);
   }

   private Duration method4(FpsDebugPhase fpsdebugmodtype1) {
      Duration duration2 = this.method6(fpsdebugmodtype1).map(FpsDebugTask::method2).reduce(null, FpsDebugCollector::method5);
      return duration2 == null ? Duration.ZERO : duration2;
   }

   private static Duration method5(Duration duration0, Duration duration1) {
      if (duration0 == null) {
         return duration1;
      } else if (duration1 == null) {
         return duration0;
      } else {
         return duration0.minus(duration1).isNegative() ? duration1 : duration0;
      }
   }

   private Stream<FpsDebugTask> method6(FpsDebugPhase fpsdebugmodtype1) {
      return this.method7().filter(arg1x -> arg1x.method3() == fpsdebugmodtype1);
   }

   private Stream<FpsDebugTask> method7() {
      return this.field1.stream().filter(FpsDebugTask::method1);
   }

   public Future<DebugArchive> method8() {
      return CompletableFuture.supplyAsync(() -> Arrays.stream(FpsDebugPhase.values()).map(arg1 -> {
         try {
            return this.method9(arg1).get();
         } catch (InterruptedException | ExecutionException interruptedexception3) {
            return new DebugArchive().method4("Failed to collect data for phase " + arg1, interruptedexception3);
         }
      }).reduce(new DebugArchive(), DebugArchive::method3));
   }

   private Future<DebugArchive> method9(FpsDebugPhase fpsdebugmodtype1) {
      return CompletableFuture.supplyAsync(() -> this.method6(fpsdebugmodtype1).map(arg0 -> ValuePair.method1(arg0, arg0.method4())).toList().stream().map(arg0 -> {
         try {
            return (DebugArchive)((Future)arg0.field2).get();
         } catch (InterruptedException | ExecutionException interruptedexception2) {
            return new DebugArchive().method4("Failed to collect data for " + ((FpsDebugTask)arg0.field1).name(), interruptedexception2);
         }
      }).reduce(new DebugArchive(), DebugArchive::method3));
   }
}
