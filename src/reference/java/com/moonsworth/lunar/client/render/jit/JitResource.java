package com.moonsworth.lunar.client.render.jit;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump80;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Files;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import lombok.Generated;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.Nullable;

public abstract class JitResource<T> {
   private static final Executor field1 = Executors.newCachedThreadPool(new ThreadFactoryBuilder().setNameFormat("jit-http-thread-%d").setDaemon(true).build());
   private static final HttpClient field2 = HttpClient.newBuilder().executor(field1).connectTimeout(Duration.ofSeconds(10L)).build();
   protected final JitAssetKey field3;
   protected final JitResource.Data6 field4;
   protected final AtomicBoolean field5 = new AtomicBoolean();
   @Nullable
   protected volatile T value;
   private volatile int field6;

   protected JitResource(JitAssetKey var1, JitResource.Data6 var2) {
      this.field3 = var1;
      this.field4 = var2;
   }

   public Optional<T> method1() {
      this.method3();
      return this.method2();
   }

   public Optional<T> method2() {
      return Optional.ofNullable(this.value);
   }

   @CanIgnoreReturnValue
   public boolean method3() {
      if (this.field5.compareAndSet(false, true)) {
         CompletableFuture var1;
         if (this.method5()) {
            var1 = this.method6().thenCompose(var1x -> this.method8());
         } else {
            var1 = this.method8();
         }

         var1.thenAccept(var1x -> this.value = (T)var1x).exceptionally(var1x -> {
            Slayer.method8("JIT", "Failed to compute JIT resource value for '%s': %s", this.field3, var1x.getMessage());
            var1x.printStackTrace();
            return null;
         });
      }

      return this.value != null;
   }

   public void method4(int var1) {
      this.field6 = var1;
      JitAssetIndex var2 = ThreadModuleDump63.method4().method96();
      var2.method7().method4(this.field3);
   }

   private boolean method5() {
      if (!this.field4.method3()) {
         return false;
      } else if (!JitPaths.method1(this.field3.method3())) {
         Slayer.method6("JIT", "Cannot download resource with invalid domain: %s", this.field3.method3());
         return false;
      } else {
         String var1 = this.field3.method3().bridge$getPath();
         return !Files.exists(ThreadModuleDump48.field11.resolve(var1));
      }
   }

   private CompletableFuture<Void> method6() {
      CompletableFuture var1 = this.method7(false);
      if (this.field4.hasMetadata()) {
         CompletableFuture var2 = this.method7(true);
         return var1.thenCombine(var2, (var0, var1x) -> null);
      } else {
         return var1;
      }
   }

   private CompletableFuture<Void> method7(boolean var1) {
      JitAssetIndex var2 = ThreadModuleDump63.method4().method96();
      Optional var3 = var2.method5(this.field3.method3(), var1);
      if (var3.isEmpty()) {
         return CompletableFuture.completedFuture(null);
      }

      HttpRequest var4 = HttpRequest.newBuilder()
         .uri((URI)var3.get())
         .header("User-Agent", "LunarClient/" + com.moonsworth.lunar.client.nameplate.Nameplate.field2)
         .header("X-Installation-Id", ThreadModuleDump80.installationId)
         .GET()
         .build();
      CompletableFuture var5 = field2.sendAsync(var4, BodyHandlers.ofInputStream());
      return var5.thenAccept(var3x -> {
         if (var3x.statusCode() != 200) {
            Slayer.method5("Failed to locate asset: " + this.field3.method3() + " - status: " + var3x.statusCode());
         } else {
            try (InputStream var4x = (InputStream)var3x.body()) {
               String var5x = this.field3.method3().bridge$getPath();
               if (var1) {
                  var5x = var5x + ".mcmeta";
               }

               FileUtils.copyInputStreamToFile(var4x, ThreadModuleDump48.field11.resolve(var5x).toFile());
               var2.method8().method1(this.field3.method3(), var1);
            } catch (IOException var9) {
               Slayer.method7("Failed to download JIT resource: " + this.field3.method3());
               throw new RuntimeException(var9);
            }
         }
      });
   }

   public void tick() {
   }

   public void cleanUp() {
   }

   protected abstract CompletableFuture<T> method8();

   @Generated
   public JitAssetKey method9() {
      return this.field3;
   }

   @Generated
   public JitResource.Data6 method10() {
      return this.field4;
   }

   @Generated
   public int method11() {
      return this.field6;
   }

   public class Data6 {
      private final boolean field1;
      private final boolean field2;
      private final boolean field3;

      public Data6(boolean var1, boolean var2, boolean var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }

      public static JitResource.Data6 method1() {
         return new JitResource.Data6(false, false, true);
      }

      public boolean hasMetadata() {
         return this.field1;
      }

      public boolean method2() {
         return this.field2;
      }

      public boolean method3() {
         return this.field3;
      }
   }
}
