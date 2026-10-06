package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod;

import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.FpsDebugPhase;
import com.moonsworth.lunar.client.framework.Ref;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class McSettingsTask implements com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.FpsDebugTask {
   private static final String[] field1 = new String[]{"options.txt", "optionsof.txt"};

   public McSettingsTask() {
   }

   @Override
   public String name() {
      return "mc-settings";
   }

   @Override
   public boolean method1() {
      return true;
   }

   @Override
   public Duration method2() {
      return Duration.ofMillis(100L);
   }

   @Override
   public FpsDebugPhase method3() {
      return FpsDebugPhase.PRE_COLLECT;
   }

   @Override
   public Future<com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.DebugArchive> method4() {
      return CompletableFuture.supplyAsync(
         () -> {
            com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.DebugArchive fpsdebugmod0 = new com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.DebugArchive();
            File file1 = Ref.method3().bridge$getMcDataDir();
            if (file1 == null) {
               return fpsdebugmod0;
            }

            for (String text5 : field1) {
               File file6 = new File(file1, text5);
               if (file6.isFile()) {
                  try {
                     fpsdebugmod0.method1(text5, Files.readAllBytes(file6.toPath()));
                  } catch (IOException exception8) {
                     fpsdebugmod0.method4("Failed to read " + text5, exception8);
                  }
               }
            }

            return fpsdebugmod0;
         }
      );
   }
}
