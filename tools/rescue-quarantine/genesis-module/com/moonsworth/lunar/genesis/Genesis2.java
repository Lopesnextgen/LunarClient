package com.moonsworth.lunar.genesis;

import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.ichor.Ichor4;
import com.moonsworth.lunar.ichor.Ichor7;
import com.moonsworth.lunar.ichor.MixinExtra;
import com.moonsworth.lunar.ichor.api.IchorAPI;
import com.moonsworth.lunar.ichor.util.FatalIchorError14;
import com.moonsworth.lunar.ichor.util.FatalIchorError5;
import com.moonsworth.lunar.ichor.util.FatalIchorError6;
import com.moonsworth.lunar.ichor.util.FatalIchorError9;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ConcurrentHashMap.KeySetView;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import org.jctools.queues.MpmcUnboundedXaddArrayQueue;

public class Genesis2 {
   public static final FatalIchorError5 field1 = new FatalIchorError5("Genesis/Baker").method3(Paths.get(".ichor/genesis.log"));
   static final boolean field2 = Boolean.parseBoolean(System.getProperty("ichor.debugBakedClasses", "false"))
      || Boolean.parseBoolean(System.getProperty("ichor.debugPrebakedClasses", "false"));

   public static void method1(Ichor7 var0, URLClassLoader2 var1, Path var2, Map<String, byte[]> var3, Ichor4 var4) {
      try {
         if (!Files.exists(var2)) {
            method2(var0, var1, var2, var3, var4);
         } else {
            try (ZipFile var5 = new ZipFile(var2.toFile())) {
               field1.info("Found class cache: " + var2);
               field1.info("Loading baked classes...");
               Enumeration var6 = var5.entries();
               long var7 = System.nanoTime();

               while (var6.hasMoreElements()) {
                  ZipEntry var9 = (ZipEntry)var6.nextElement();
                  String var10 = var9.getName();
                  byte[] var11 = var5.getInputStream(var9).readAllBytes();
                  if (field2) {
                     field1.info("Loading baked class " + var10 + " @ " + var11.length + " bytes");
                  }

                  var3.put(var10, var11);
               }

               long var18 = System.nanoTime();
               double var19 = (double)(var18 - var7) / Duration.ofSeconds(1L).toNanos();
               System.out.printf("Loaded baked classes in %.2f seconds \n", var19);
               if (var4 != null && var0.method34().method4() != null) {
                  MixinExtra var13 = new MixinExtra(var4, var3, var0x -> true);
                  var0.method34().method4().add(var13);
               }

               field1.info("Loaded " + var3.size() + " baked classes.");
               if (var4 == null) {
                  var0.method24();
               }
            } catch (ZipException var16) {
               method2(var0, var1, var2, var3, var4);
            }
         }
      } catch (Throwable var17) {
         throw var17;
      }
   }

   private static void method2(Ichor7 var0, URLClassLoader2 var1, Path var2, Map<String, byte[]> var3, Ichor4 var4) {
      Config var5 = Config.method36(var0.method34().method6());
      field1.info("Missing class cache: " + var2);
      field1.info("Baking classes...");
      var1.method10();
      long var6 = System.currentTimeMillis();
      System.out.println("LUNARCLIENT_STATUS_BUILD_CACHE");
      MpmcUnboundedXaddArrayQueue var8 = new MpmcUnboundedXaddArrayQueue(100, 4);
      Set var9 = var0.method25(var1, var5.method28());
      if (var9.isEmpty()) {
         throw new IllegalStateException("No bake candidates found?? There are probably missing jars.");
      }

      for (String var11 : var9) {
         String var12 = var1.method9(var11);
         if (field2) {
            if (var11.equals(var12)) {
               field1.info("Baking " + var11);
            } else {
               field1.info("Baking " + var11 + " -> " + var12);
            }
         }

         var8.offer(var12);
      }

      int var21 = var9.size();
      field1.info("Found " + var21 + " classes to bake.");
      AtomicLong var22 = new AtomicLong();
      KeySetView var23 = ConcurrentHashMap.newKeySet();
      Consumer var13 = var8x -> {
         String var9x = var8x.replace('/', '.');
         if (field2) {
            field1.info("[" + Thread.currentThread().getName() + "] Handling " + var8x);
         }

         if (method3(var1, var9x) && var23.add(var8x)) {
            if (field2) {
               field1.info("[" + Thread.currentThread().getName() + "] Baking " + var8x + " (" + var3.size() + ")");
            }

            try {
               URLClassLoader2.Data var18x = var1.method8(var8x, () -> var0.method4(var8x, null, var1, null, var4));
               if (!var18x.method2()) {
                  if (field2) {
                     field1.info("[" + Thread.currentThread().getName() + "] Baking " + var8x + " already baked by the game classloader");
                  }

                  return;
               }

               FatalIchorError14 var11x = var18x.method1();
               byte[] var12x = var11x.method1();
               if (field2 && var12x == null) {
                  field1.info("[" + Thread.currentThread().getName() + "] Baking " + var8x + " returned null bytes");
               }

               int var13x = var3.size();
               if (var13x % 25 == 0) {
                  long var14x = System.currentTimeMillis();
                  if (var14x - var22.get() >= 1000L) {
                     var22.set(var14x);
                     float var16x = var21 + var8.size();
                     System.out.println("LUNARCLIENT_BAKE_PROGRESS " + Math.min(var13x / var16x * 100.0F, 100.0F));
                  }
               }

               for (String var20x : FatalIchorError6.method15(var12x)) {
                  var20x = var1.method9(var20x);
                  if (!var23.contains(var20x) && !var3.containsKey(var20x) && method3(var1, var20x)) {
                     if (field2) {
                        field1.info("[" + Thread.currentThread().getName() + "] Baking : " + var8x + " offers " + var20x);
                     }

                     var8.offer(var20x);
                  }
               }
            } catch (Exception var17x) {
               if (field2) {
                  field1.warn("Couldn't find class bytes for " + var8x + " : " + var17x.getMessage());
               }

               if (!(var17x instanceof ClassNotFoundException)) {
                  throw var17x;
               }
            }
         } else if (var3.containsKey(var8x) && field2) {
            byte[] var10 = (byte[])var3.get(var8x);
            field1.info("[" + Thread.currentThread().getName() + "] Baking " + var8x + " already baked :" + (var10 == null ? "null" : var10.length + " bytes"));
         }
      };
      AtomicBoolean var14 = new AtomicBoolean(false);
      var0.method38().method1(var1x -> {
         field1.method6(FatalIchorError5.Type.ERROR, "Cancelling prebaking class cache because of an error in the IchorPipeline.", var1x);
         var14.set(true);
      });
      Runnable var15 = () -> {
         if (!var14.get()) {
            for (String var9x : var0.getSyntheticClasses()) {
               var13.accept(var1.method9(var9x));
            }

            var3.putAll(var0.getExtraClassDefinitions());
            field1.info("Baked " + var3.size() + " classes in " + (System.currentTimeMillis() - var6) + "ms.");
            TreeMap var11x = new TreeMap(var3);

            try {
               System.out.println("LUNARCLIENT_STATUS_SAVING_CACHE");
               FatalIchorError9.method4(var11x, var2.toFile());
            } catch (IOException var10) {
               field1.method6(FatalIchorError5.Type.ERROR, "Failed to write class cache: ", var10);
            }
         }
      };
      int var16 = Runtime.getRuntime().availableProcessors();
      ExecutorService var17 = Executors.newFixedThreadPool(var16);
      AtomicInteger var18 = new AtomicInteger(var16);
      ArrayList var19 = new ArrayList();

      for (int var20 = 0; var20 < var16; var20++) {
         var19.add(() -> {
            Object var5x = null;

            while (!var8.isEmpty()) {
               if (var14.get()) {
                  return null;
               }

               var5x = (String)var8.relaxedPoll();
               if (var5x != null) {
                  try {
                     var13.accept(var5x);
                  } catch (Throwable var7) {
                     field1.method6(FatalIchorError5.Type.ERROR, "Failed to bake " + var5x + ": ", var7);
                  }
               }
            }

            if (var18.decrementAndGet() == 0) {
               var15.run();
            }

            return null;
         });
      }

      List var24 = var19.stream().map(var17::submit).toList();
      Runtime.getRuntime().addShutdownHook(new Thread(() -> {
         try {
            for (Future var2x : var24) {
               var2x.get();
            }
         } catch (InterruptedException | ExecutionException var3x) {
            field1.method6(FatalIchorError5.Type.ERROR, "Failed to write class cache: ", var3x);
         }
      }));
   }

   private static boolean method3(URLClassLoader2 var0, String var1) {
      var1 = var1.replace('/', '.');
      return com.moonsworth.lunar.ichor.URLClassLoader.method10(var1, var0.method1()) && !var0.isExcluded(var1) && method4(var1);
   }

   public static boolean method4(String var0) {
      if (IchorAPI.getClassCacheLevel() == null) {
         return !var0.startsWith("srg.net.optifine") && !var0.startsWith("it.unimi.dsi.fastutil");
      }

      boolean var1 = var0.startsWith("net.minecraft.") || var0.startsWith("com.mojang.") || !var0.contains(".") || var0.startsWith("com.moonsworth.");
      return var1 & var0.indexOf(36) == var0.lastIndexOf(36);
   }
}
