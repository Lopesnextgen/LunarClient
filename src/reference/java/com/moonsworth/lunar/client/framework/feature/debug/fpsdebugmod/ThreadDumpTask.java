package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod;

import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.FpsDebugPhase;
import com.moonsworth.lunar.client.framework.crash.ThreadModuleDump;
import com.moonsworth.lunar.client.framework.OperatingSystem;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class ThreadDumpTask implements com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.FpsDebugTask {
   public ThreadDumpTask() {
   }

   @Override
   public String name() {
      return "thread-dump";
   }

   @Override
   public boolean method1() {
      return OperatingSystem.isWindows();
   }

   @Override
   public Duration method2() {
      return Duration.ofSeconds(1L);
   }

   @Override
   public FpsDebugPhase method3() {
      return FpsDebugPhase.THREAD_DUMP;
   }

   @Override
   public Future<com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.DebugArchive> method4() {
      return CompletableFuture.supplyAsync(
         () -> new com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.DebugArchive().method2("thread-dump.txt", ThreadModuleDump.dump())
      );
   }
}
