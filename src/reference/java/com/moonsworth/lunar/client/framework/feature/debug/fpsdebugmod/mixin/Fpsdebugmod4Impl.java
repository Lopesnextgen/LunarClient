package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.mixin;

import com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.FpsdebugmodType;
import com.moonsworth.lunar.client.profile.ModProfile;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public class Fpsdebugmod4Impl implements com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.Fpsdebugmod2 {
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
   public FpsdebugmodType method3() {
      return FpsdebugmodType.PROFILER;
   }

   @Override
   public Future<com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.Fpsdebugmod> method4() {
      return CompletableFuture.supplyAsync(
         () -> {
            com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.Fpsdebugmod var0 = new com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.Fpsdebugmod();
            ModProfile var1 = ThreadModuleDump63.method4().method61().method14();
            if (var1 != null && var1.getFile() != null) {
               Path var2 = var1.getFile().toPath();
               if (!Files.isDirectory(var2)) {
                  return var0;
               }

               try (Stream var3 = Files.walk(var2)) {
                  var3.filter(var0x -> Files.isRegularFile(var0x)).forEach(var2x -> {
                     try {
                        String var3x = var2.relativize(var2x).toString().replace('\\', '/');
                        var0.method1("profile/" + var3x, Files.readAllBytes(var2x));
                     } catch (IOException var4) {
                        var0.method4("Failed to read profile file " + var2x, var4);
                     }
                  });
               } catch (IOException var8) {
                  var0.method4("Failed to walk profile directory " + var2, var8);
               }

               return var0;
            } else {
               return var0;
            }
         }
      );
   }
}
