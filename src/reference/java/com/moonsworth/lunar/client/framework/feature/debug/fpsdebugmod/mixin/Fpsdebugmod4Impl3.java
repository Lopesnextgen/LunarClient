package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.mixin;

import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.FpsdebugmodType;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Fpsdebugmod4Impl3 implements com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.Fpsdebugmod2 {
   private static final String[] field1 = new String[]{"options.txt", "optionsof.txt"};

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
   public FpsdebugmodType method3() {
      return FpsdebugmodType.PROFILER;
   }

   @Override
   public Future<com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.Fpsdebugmod> method4() {
      return CompletableFuture.supplyAsync(
         () -> {
            com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.Fpsdebugmod var0 = new com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.Fpsdebugmod();
            File var1 = ThreadModuleDump63.method3().bridge$getMcDataDir();
            if (var1 == null) {
               return var0;
            }

            for (String var5 : field1) {
               File var6 = new File(var1, var5);
               if (var6.isFile()) {
                  try {
                     var0.method1(var5, Files.readAllBytes(var6.toPath()));
                  } catch (IOException var8) {
                     var0.method4("Failed to read " + var5, var8);
                  }
               }
            }

            return var0;
         }
      );
   }
}
