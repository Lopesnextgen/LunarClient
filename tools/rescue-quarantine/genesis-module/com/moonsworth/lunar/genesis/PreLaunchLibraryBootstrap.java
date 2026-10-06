package com.moonsworth.lunar.genesis;

import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.config.ConfigType;
import com.moonsworth.lunar.files.Files4Base4;
import com.moonsworth.lunar.files.Files4Impl;
import com.moonsworth.lunar.files.Files5;
import com.moonsworth.lunar.files.Files7;
import com.moonsworth.lunar.ichor.Ichor3;
import com.moonsworth.lunar.ichor.Ichor7;
import com.moonsworth.lunar.loader.Ichor4Type2;
import com.moonsworth.lunar.loader.Loader;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.jetbrains.annotations.Nullable;

@com.moonsworth.lunar.ichor.util.Annotation4
public class PreLaunchLibraryBootstrap implements Function<PreLaunchLibraryBootstrap.Data, List<Path>> {
   public List<Path> apply(PreLaunchLibraryBootstrap.Data var1) {
      Config.method1(null);
      Config var2 = Config.get(var1.mcVersionName()).orElseThrow(() -> new IllegalArgumentException("Invalid Minecraft version: " + var1.mcVersionName()));
      Files7 var3 = new Files7(new Files4Base4(new Files4Impl(var1.mxCacheDir())));
      var3.method6(new Files5("mcVer", var2.method45()));
      var3.method6(new Files5("mcId", var2.getId()));
      Ichor3 var4 = new Ichor3(
         var1.classpathDir(),
         var1.classpathDir(),
         var1.overridesDir(),
         var3,
         null,
         null,
         null,
         Map.of("mcVer", var2.method45(), "computeFrames", var2.method26())
      );
      String var5 = System.getProperty("ichor.launch.libraries", null);
      HashMap var6 = new HashMap();
      if (var5 != null) {
         String[] var7 = var5.split(",");

         for (String var11 : var7) {
            String[] var12 = var11.split("=");
            var6.put(var12[0], var12[1]);
         }
      }

      String var13 = System.getProperty("ichor.fabricLoaderVersion", null);
      if (var13 != null) {
         var6.put("FABRIC_LOADER", var13);
      }

      return download(var4, var6, var1.extraLibsDir());
   }

   public static Path[] download(Ichor4Type2 var0, Ichor3 var1, @Nullable String var2, Path var3, URLClassLoader var4) {
      Path var5 = Config.method34().resolve("libraries");
      String[] var6 = var0.mavenIds(var2);
      Path[] var7 = new Path[var6.length];
      URL[] var8 = Arrays.stream(var6).map(var1x -> {
         try {
            return ConfigType.download(var1x, var5).toUri().toURL();
         } catch (MalformedURLException | FileNotFoundException var3x) {
            throw new RuntimeException(var3x);
         }
      }).toList().toArray(new URL[0]);

      for (URL var12 : var8) {
         var4.addURL(var12);
      }

      Thread.currentThread().setContextClassLoader(var4);

      try (Ichor7 var16 = new Ichor7(List.of(var0), var1, var4)) {
         for (int var17 = 0; var17 < var6.length; var17++) {
            String var18 = var6[var17];
            Path var19 = ConfigType.download(var18, var5);
            if (var19 == null) {
               throw new IllegalStateException("Couldn't download " + var18);
            }

            var7[var17] = var3.resolve(var18.replaceAll("[:.-]+", "_") + "-lunar-" + var0.getTransformerVersion() + ".jar");
            if (Files.notExists(var7[var17]) && Files.isRegularFile(var19)) {
               var16.method7(var19, var7[var17], var4);
            }
         }

         return var7;
      } catch (Exception var15) {
         throw new IllegalStateException("Failed to download and transform library " + var0.name() + " " + var2, var15);
      }
   }

   public static List<Path> download(Ichor3 var0, Map<String, String> var1, Path var2) {
      try {
         Files.createDirectories(var2);
         URLClassLoader var3 = (URLClassLoader)Ichor4Type2.class.getClassLoader();
         HashSet var4 = new HashSet();

         try {
            ServiceLoader.load(Loader.class, var3).iterator().forEachRemaining(var1x -> var4.addAll(Arrays.asList(var1x.method1())));
         } catch (ServiceConfigurationError var6) {
            throw new ServiceConfigurationError(
               "Failed to find PreLaunchLibraryRequests! PreLaunchLibrary is loaded in "
                  + var3.getName()
                  + " and PreLaunchLibraryRequest is loaded in "
                  + Loader.class.getClassLoader().getName()
                  + " (they should be the same).",
               var6
            );
         }

         return var4.isEmpty() ? List.of() : var4.stream().flatMap(var4x -> {
            Path[] var5 = download(var4x, var0, (String)var1.get(var4x.name()), var2, var3);
            return Stream.of(var5);
         }).collect(Collectors.toList());
      } catch (Throwable var7) {
         throw var7;
      }
   }

   @com.moonsworth.lunar.ichor.util.Annotation2
   public class Data {
      private final String mcVersionName;
      private final Path mxCacheDir;
      private final Path classpathDir;
      private final Path overridesDir;
      private final Path extraLibsDir;

      public Data(String var1, Path var2, Path var3, Path var4, Path var5) {
         this.mcVersionName = var1;
         this.mxCacheDir = var2;
         this.classpathDir = var3;
         this.overridesDir = var4;
         this.extraLibsDir = var5;
      }
   }
}
