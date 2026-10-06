package com.moonsworth.lunar.client.render.jit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.render.jit.JitAssetIndex;
import com.moonsworth.lunar.client.render.jit.JitPaths;
import com.moonsworth.lunar.client.util.ThreadModuleDump37;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump77;
import io.leangen.geantyref.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;
import lombok.Generated;
import org.apache.commons.io.FilenameUtils;

public class JitIndexCache {
   private static final Gson field1 = new GsonBuilder().create();
   private static final ResourceLocationBridge field2 = ResourceLocationBridge.create("lunar-jit", "downloaded_index.json");
   private final Map<String, String> field3 = new ConcurrentHashMap<>();
   private final JitAssetIndex field4;

   public void start() {
      try {
         Path var1 = ThreadModuleDump48.field11.resolve(field2.bridge$getPath());
         if (Files.notExists(var1)) {
            Files.createDirectories(var1.getParent());
            Files.createFile(var1);
         }

         try (BufferedReader var2 = Files.newBufferedReader(var1)) {
            TypeToken var3 = new TypeToken<Map<String, String>>() {};
            this.field3.putAll(Objects.requireNonNullElse((Map)field1.fromJson(var2, var3.getType()), new HashMap<>()));
         }

         ThreadModuleDump37.method4(this::method2);
      } catch (IOException var7) {
         throw var7;
      }
   }

   public void stop() {
      try {
         Path var1 = ThreadModuleDump48.field11.resolve(field2.bridge$getPath());

         try (BufferedWriter var2 = Files.newBufferedWriter(var1, StandardCharsets.UTF_8)) {
            field1.toJson(this.field3, var2);
         }
      } catch (IOException var7) {
         throw var7;
      }
   }

   public void method1(ResourceLocationBridge var1, boolean var2) {
      String var3 = JitPaths.method3(var1, var2);
      this.field4.method6(var3).ifPresent(var2x -> this.field3.put(var3, var2x));
   }

   private void method2() {
      Path var1 = ThreadModuleDump48.field11.resolve(field2.bridge$getPath());

      try (Stream var2 = Files.walk(ThreadModuleDump48.field11)) {
         int var3 = var2.filter(var0 -> Files.isRegularFile(var0))
            .filter(var1x -> !var1x.equals(var1))
            .filter(this::method3)
            .<Boolean>map(ThreadModuleDump77.function(Files::deleteIfExists))
            .mapToInt(var0 -> var0 ? 1 : 0)
            .sum();
         if (var3 > 0) {
            Slayer.method4("JIT", "Cleaned up %d JIT files!", new Object[]{var3});
         }
      } catch (Exception var7) {
         Slayer.method8("JIT", "Could not clean up JIT files!" + var7.getMessage(), new Object[0]);
      }
   }

   private boolean method3(Path var1) {
      Path var2 = ThreadModuleDump48.field11.relativize(var1);
      String var3 = FilenameUtils.separatorsToUnix(var2.toString());
      Optional var4 = this.field4.method6(var3);
      if (var4.isEmpty()) {
         return true;
      }

      String var5 = this.field3.get(var3);
      return var5 == null ? true : var4.<Boolean>map(var1x -> !var1x.equals(var5)).get();
   }

   @Generated
   public JitIndexCache(JitAssetIndex var1) {
      this.field4 = var1;
   }
}
