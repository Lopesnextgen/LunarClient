package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod;

import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.FpsDebugPhase;
import com.moonsworth.lunar.client.config.profile.ModProfile;
import com.moonsworth.lunar.client.framework.Ref;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public class ProfileDataTask implements com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.FpsDebugTask {
   public ProfileDataTask() {
   }

   @Override
   public String name() {
      return "profile-data";
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
            ModProfile horsestats1 = Ref.method4().method61().method14();
            if (horsestats1 != null && horsestats1.getFile() != null) {
               Path path2 = horsestats1.getFile().toPath();
               if (!Files.isDirectory(path2)) {
                  return fpsdebugmod0;
               }

               try (Stream stream3 = Files.walk(path2)) {
                  stream3.filter(arg0x -> Files.isRegularFile(arg0x)).forEach(arg2x -> {
                     try {
                        String text3x = path2.relativize(arg2x).toString().replace('\\', '/');
                        fpsdebugmod0.method1("profile/" + text3x, Files.readAllBytes(arg2x));
                     } catch (IOException exception4) {
                        fpsdebugmod0.method4("Failed to read profile file " + arg2x, exception4);
                     }
                  });
               } catch (IOException exception8) {
                  fpsdebugmod0.method4("Failed to walk profile directory " + path2, exception8);
               }

               return fpsdebugmod0;
            } else {
               return fpsdebugmod0;
            }
         }
      );
   }
}
