package com.moonsworth.lunar.genesis;

import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.files.Files4Base4;
import com.moonsworth.lunar.files.Files4Impl;
import com.moonsworth.lunar.files.Files5;
import com.moonsworth.lunar.files.Files7;
import com.moonsworth.lunar.files.Files_5;
import com.moonsworth.lunar.ichor.Ichor3;
import com.moonsworth.lunar.ichor.Ichor4;
import com.moonsworth.lunar.ichor.Ichor7;
import com.moonsworth.lunar.ichor.MixinExtra;
import com.moonsworth.lunar.ichor.MixinSupport;
import com.moonsworth.lunar.ichor.api.IchorAPI;
import com.moonsworth.lunar.ichor.util.FatalIchorError5;
import com.moonsworth.lunar.loader.Ichor4Type;
import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Stream;
import org.jetbrains.annotations.Nullable;

@com.moonsworth.lunar.ichor.util.Annotation4
public class ClientGameBootstrap implements Function<ClientGameBootstrap.Data, Void> {
   public static final Path LUNARCLIENT_DATA = Paths.get(System.getProperty("lunar.dataDir", System.getProperty("user.home") + File.separator + ".lunarclient"));
   public static final FatalIchorError5 LOGGER = new FatalIchorError5("Genesis/GameBootstrap");

   public Void apply(ClientGameBootstrap.Data var1) {
      try {
         ClassLoader var2 = ClientGameBootstrap.class.getClassLoader();
         Config.method1(null);
         Config var3 = Config.get(var1.mcVersionName()).orElseThrow(() -> new IllegalArgumentException("Invalid Minecraft version: " + var1.mcVersionName()));
         LOGGER.info("Launching Minecraft " + var3.method45() + " " + var3.getId() + " (parsed from " + var1.mcVersionName() + ")");
         Files7 var4 = new Files7(new Files4Base4(new Files4Impl(var1.mxCacheDir())));
         var4.method6(new Files5("mcVer", var3.method45()));
         var4.method6(new Files5("mcId", var3.getId()));
         ArrayList var5 = new ArrayList();
         if (var1.partialJarPath() != null) {
            LOGGER.info("Using partial jar " + var1.partialJarPath());
            MixinExtra var6 = MixinExtra.method2(
               Ichor4Type.EXTERNAL_REMAP, var1.partialJarPath(), var0 -> var0.startsWith("net.minecraft.") || var0.startsWith("com.mojang.")
            );
            var5.add(var6);
         }

         ArrayList var33 = new ArrayList();

         try (Ichor7 var7 = new Ichor7(
               Arrays.asList(Ichor4Type.values()),
               new Ichor3(
                  var1.classpathDir(),
                  var1.workingDirectory(),
                  var1.overridesDirectory(),
                  var4,
                  var5,
                  var1.classesToDump(),
                  var1.ichorExternalFiles() == null ? null : List.of(var1.ichorExternalFiles()),
                  Map.of(
                     "ichor.fabric.localModPath",
                     "./mods/",
                     "ichor.testing",
                     Boolean.toString(var1.runIntegrationTests()),
                     "mcVer",
                     var3.method45(),
                     "computeFrames",
                     var3.method26()
                  )
               ),
               var2
            )) {
            if (var1.runIntegrationTests()) {
               MixinSupport.method1(var7);
            }

            ArrayList var8 = new ArrayList();
            var8.add(var3.method28());
            var8.addAll(var3.method33());
            var8.addAll(var7.method14());
            URL[] var9 = new URL[var8.size()];

            try {
               int var10 = 0;

               for (Path var12 : var8) {
                  if (Files.notExists(var12)) {
                     throw new IllegalStateException("Failed to download dependency? " + var12);
                  }

                  var33.add(var12);
                  var9[var10++] = var12.toUri().toURL();
               }
            } catch (MalformedURLException var28) {
               throw new IllegalStateException("Couldn't build URL array", var28);
            }

            URLClassLoader2 var34 = new URLClassLoader2(var9, var7);
            Thread.currentThread().setContextClassLoader(var34);
            Path var35 = null;
            if (IchorAPI.canCacheClasses()) {
               Ichor4 var36 = IchorAPI.getClassCacheLevel();
               ArrayList var13 = new ArrayList();
               var13.add(var3);
               var13.addAll(new TreeSet<>(var7.method31().keySet()));
               if (var36 != null) {
                  var13.add(var36);
               }

               String var14 = hash(var13.toArray());
               TreeSet var15;
               if (var1.ichorClassPath() != null) {
                  var15 = new TreeSet();

                  for (String var19 : var1.ichorClassPath()) {
                     var15.add(var1.classpathDir().resolve(var19));
                  }

                  if (var1.ichorExternalFiles() != null) {
                     for (String var55 : var1.ichorExternalFiles()) {
                        if (var55.endsWith(".jar")) {
                           var15.add(var1.classpathDir().resolve(var55));
                        }
                     }
                  }
               } else {
                  var15 = new TreeSet(var33);

                  try (Stream var44 = Files.list(var1.classpathDir())) {
                     var44.forEach(var15::add);
                  }
               }

               if (Files.exists(var1.overridesDirectory()) && Files.isDirectory(var1.overridesDirectory())) {
                  try (Stream var45 = Files.list(var1.overridesDirectory())) {
                     var45.forEach(var15::add);
                  }
               }

               if (Boolean.parseBoolean(System.getProperty("ichor.debug.bakerhash", "false"))) {
                  var15.forEach(var0 -> LOGGER.warn("Found file for baker hash %s: %s", var0, Files_5.method4(var0)));
               }

               String var46 = hash(var15.stream().map(Files_5::method4).toArray());
               Path var49 = var1.classpathDir().resolve("cache").resolve(var14).resolve(var46);
               Files.createDirectories(var49);
               var7.method41(var49);
               var35 = var49.resolve("bake.zip");
               LOGGER.info("Config hash: " + var14 + "  File hash: " + var46);
               ConcurrentMap var52 = var34.method7();
               Genesis2.method1(var7, var34, var35, var52, var36);
            }

            try (var34) {
               System.out.println("LUNARCLIENT_STATUS_PREINIT");
               LOGGER.info("Starting game!");
               LOGGER.info("Branch: " + Genesis3.field1);
               LOGGER.info("Commit Hash: " + Genesis3.field3);
               Class var39 = Class.forName(var3.method41(), true, var34);
               Method var41 = var39.getMethod("main", String[].class);
               HashMap var42 = getLaunchArguments(var3);
               ArrayList var47 = new ArrayList();
               String[] var50 = var1.args();

               for (int var53 = 0; var53 < var50.length; var53++) {
                  boolean var56 = var53 == var50.length - 1;
                  String var20 = var50[var53];
                  if (var20.startsWith("--")) {
                     if (var56) {
                        var47.add(var20);
                     } else {
                        String var21 = var50[var53 + 1];
                        if (var21.startsWith("--")) {
                           var47.add(var20);
                        }
                     }
                  } else if (var53 != 0) {
                     String var60 = var50[var53 - 1];
                     if (var60.startsWith("--")) {
                        var42.put(var60, var20);
                        if ("--gameDir".equals(var60)) {
                           var42.put("--assetsDir", var20 + "/assets/");
                        }
                     }
                  }
               }

               String[] var54 = new String[var42.size() * 2 + var47.size()];
               int var57 = 0;

               for (Entry var61 : var42.entrySet()) {
                  var54[var57] = (String)var61.getKey();
                  var54[var57 + 1] = (String)var61.getValue();
                  var57 += 2;
               }

               for (String var62 : var47) {
                  var54[var57] = var62;
                  var57++;
               }

               var41.invoke(null, var54);
            } catch (Throwable var30) {
               if (var30.getCause() instanceof NoSuchMethodError var38 && IchorAPI.canCacheClasses() && Files.exists(var35)) {
                  LOGGER.info("Deleting cache at " + var35 + " because of " + var38.getMessage());
                  Files.delete(var35);
               }

               throw new IllegalStateException("Failed to boot Minecraft", var30);
            }
         }

         return null;
      } catch (Throwable var32) {
         throw var32;
      }
   }

   private static String hash(Object... var0) {
      return Integer.toHexString(Objects.hash(var0));
   }

   private static HashMap<String, String> getLaunchArguments(Config var0) {
      Path var1 = Config.method34();
      HashMap var2 = new HashMap();
      var2.put("--version", "Lunar Client");
      var2.put("--accessToken", "0");
      var2.put("--assetIndex", var0.method46());
      var2.put("--userProperties", "{}");
      var2.put("--gameDir", var1.toString());
      var2.put("--assetsDir", var1.resolve("assets").toString());
      var2.put("--texturesDir", LUNARCLIENT_DATA.resolve("textures").toString());
      var2.put("--uiDir", LUNARCLIENT_DATA.resolve("ui").toString());
      var2.put("--jitDir", LUNARCLIENT_DATA.resolve("jit").toString());
      String var3 = System.getProperty("java.library.path");
      String[] var4 = var3.split(File.pathSeparator);
      var2.put("--webosrDir", var4[var4.length - 1] + "/web");
      return var2;
   }

   @com.moonsworth.lunar.ichor.util.Annotation2
   public class Data {
      private final String[] args;
      private final Path classpathDir;
      private final Path mxCacheDir;
      private final Path overridesDirectory;
      private final String mcVersionName;
      private final String[] ichorClassPath;
      private final String[] ichorExternalFiles;
      private final boolean runIntegrationTests;
      private final Path workingDirectory;
      private final Path extraLibsDir;
      @Nullable
      private final Path partialJarPath;
      private final List<String> classesToDump;

      public Data(
         String[] var1,
         Path var2,
         Path var3,
         Path var4,
         String var5,
         String[] var6,
         String[] var7,
         boolean var8,
         Path var9,
         Path var10,
         @Nullable Path var11,
         List<String> var12
      ) {
         this.args = var1;
         this.classpathDir = var2;
         this.mxCacheDir = var3;
         this.overridesDirectory = var4;
         this.mcVersionName = var5;
         this.ichorClassPath = var6;
         this.ichorExternalFiles = var7;
         this.runIntegrationTests = var8;
         this.workingDirectory = var9;
         this.extraLibsDir = var10;
         this.partialJarPath = var11;
         this.classesToDump = var12;
      }
   }
}
