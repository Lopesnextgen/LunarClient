package com.moonsworth.lunar.client.render.jit;

import com.moonsworth.lunar.bridge.Bridge11_2;
import com.moonsworth.lunar.bridge.IResourceBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.LoadableHandler;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.highlight.AlertUpdateEvent;
import com.moonsworth.lunar.client.util.ThreadModuleDump37;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Future;
import java.util.function.Function;
import lombok.Generated;
import org.apache.commons.io.FilenameUtils;

public class JitAssetIndex implements LoadableHandler {
   private static final ResourceLocationBridge field1 = ResourceLocationBridge.create("lunar", "jit_index");
   private final Map<String, String> indexMap = new HashMap<>();
   private final JitResourceCache field2;
   private final com.moonsworth.lunar.client.render.jit.JitIndexCache field3;
   private Future<Void> field4;

   public JitAssetIndex() {
      this(JitCacheSettings.method1());
   }

   public JitAssetIndex(JitCacheSettings var1) {
      this.field2 = new JitResourceCache(var1);
      this.field3 = new com.moonsworth.lunar.client.render.jit.JitIndexCache(this);
      ClientEventBus.method29().method2(AlertUpdateEvent.class, var1x -> var1x.getFuture().thenRun(this.field2::clear));
   }

   @Override
   public void init() {
      Bridge11_2 var1 = ThreadModuleDump63.method3().bridge$getResourceManager();
      IResourceBridge var2 = var1.bridge$getResource(field1);
      if (var2 == null) {
         throw new RuntimeException("Could not find jit index file: " + field1);
      }

      String var4;
      try (BufferedReader var3 = new BufferedReader(new InputStreamReader(var2.bridge$getInputStream()))) {
         while ((var4 = var3.readLine()) != null) {
            String[] var5 = var4.split(" ", 3);
            if (var5.length >= 2) {
               Path var6 = Paths.get(var5[0]);
               Path var7 = var6.subpath(2, var6.getNameCount());
               String var8 = FilenameUtils.separatorsToUnix(var7.toString());
               this.indexMap.put(var8, var5[1]);
            }
         }
      } catch (IOException var11) {
         throw new RuntimeException(var11);
      }

      this.field4 = ThreadModuleDump37.method15(this.field2::tick, 0, 1);
      this.field3.start();
   }

   @Override
   public void close() {
      this.indexMap.clear();
      this.field4.cancel(true);
      this.field2.clear();
      this.field3.stop();
   }

   public <T, U extends JitResource<T>> Optional<U> method1(ResourceLocationBridge var1) {
      return this.method3(JitAssetKey.method2(var1));
   }

   public <T, U extends JitResource<T>> U method2(ResourceLocationBridge var1, Function<JitAssetKey, U> var2) {
      return this.method4(JitAssetKey.method2(var1), var2);
   }

   public <T, U extends JitResource<T>> Optional<U> method3(JitAssetKey var1) {
      return this.field2.method2(var1);
   }

   public <T, U extends JitResource<T>> U method4(JitAssetKey var1, Function<JitAssetKey, U> var2) {
      Optional var3 = this.method3(var1);
      if (var3.isEmpty()) {
         JitResource var4 = (JitResource)var2.apply(var1);
         this.field2.method3(var1, var4);
         return (U)var4;
      } else {
         return (U)var3.get();
      }
   }

   public Optional<URI> method5(ResourceLocationBridge var1, boolean var2) {
      String var3 = JitPaths.method3(var1, var2);
      return this.method6(var3).map(var0 -> URI.create("https://textures.lunarclientcdn.com/file/" + var0));
   }

   public Optional<String> method6(String var1) {
      return Optional.ofNullable(this.indexMap.get(var1));
   }

   @Generated
   public JitAssetIndex(JitResourceCache var1, com.moonsworth.lunar.client.render.jit.JitIndexCache var2) {
      this.field2 = var1;
      this.field3 = var2;
   }

   @Generated
   public JitResourceCache method7() {
      return this.field2;
   }

   @Generated
   public com.moonsworth.lunar.client.render.jit.JitIndexCache method8() {
      return this.field3;
   }
}
