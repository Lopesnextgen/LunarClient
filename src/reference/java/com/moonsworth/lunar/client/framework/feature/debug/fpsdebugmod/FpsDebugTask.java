package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod;

import java.time.Duration;
import java.util.concurrent.Future;

public interface FpsDebugTask {
   String name();

   boolean method1();

   Duration method2();

   FpsDebugPhase method3();

   Future<DebugArchive> method4();
}
