package com.moonsworth.lunar.genesis;

import com.moonsworth.lunar.files.Files_5;
import com.moonsworth.lunar.ichor.util.FatalIchorError5;
import com.moonsworth.lunar.ichor.util.FatalIchorError9;
import com.moonsworth.lunar.ichor.util.PrintStreamImpl;
import io.sentry.Sentry;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import joptsimple.ArgumentAcceptingOptionSpec;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpecBuilder;

public class Genesis {
   public static final FatalIchorError5 LOGGER = new FatalIchorError5("Genesis").method3(Paths.get(".ichor/genesis.log"));
   public static final Path LUNARCLIENT_DATA = Paths.get(System.getProperty("lunar.dataDir", System.getProperty("user.home") + File.separator + ".lunarclient"));
   public static final Path DUMP_CLASSES_WHITELIST = Paths.get("./dump-whitelist.txt");
   public static final Path PRELAUNCH_LIB_DIR = Paths.get(System.getProperty("ichor.prelaunchLibDir", "./.ichor/ichormodule-libs"));
   public static final String logs = System.getProperty("ichor.filteredGenesisSentries", ".*lcqt.*");
   private static final String logs = System.getProperty("ichor.logsFile", "logs/ichor-boot.log");
   public static final long START_TIME = System.nanoTime();

   public static void main(String[] var0) {
      Locale.setDefault(Locale.ROOT);
      System.setOut(new PrintStreamImpl(System.out, logs, false));
      System.setErr(new PrintStreamImpl(System.err, logs, true));
      Genesis4.init();

      try {
         run(var0);
      } catch (Throwable var4) {
         Throwable var1 = var4;
         if (var4 instanceof InvocationTargetException var2) {
            var1 = var2.getCause();
         }

         System.err.println("An error occurred while launching Lunar Client.");
         var1.printStackTrace();
         int var5 = matchesAnyExceptionMessage(var1, var0x -> var0x.contains("lcqt")) ? 58 : 1;
         Pattern var3 = Pattern.compile(logs, 32);
         if (var5 == 1 && !matchesAnyExceptionMessage(var1, var1x -> var3.matcher(var1x).matches())) {
            System.err.println("Sending Sentry with this report...");
            Sentry.captureException(var1);
         } else {
            System.err.println("Skipping Sentry report due to filter regex.");
         }

         System.out.flush();
         System.err.flush();
         System.exit(var5);
      }
   }

   private static boolean matchesAnyExceptionMessage(Throwable var0, Predicate<String> var1) {
      for (Throwable var2 = var0; var2 != null; var2 = var2.getCause()) {
         if (var2.getMessage() != null && var1.test(var2.getMessage())) {
            return true;
         }
      }

      return false;
   }

   public static void run(String[] var0) {
      try {
         OptionParser var1 = new OptionParser();
         var1.allowsUnrecognizedOptions();
         ArgumentAcceptingOptionSpec var2 = var1.accepts("version").withRequiredArg().ofType(String.class);
         ArgumentAcceptingOptionSpec var3 = var1.accepts("classpathDir").withRequiredArg().ofType(String.class);
         ArgumentAcceptingOptionSpec var4 = var1.accepts("workingDirectory").withRequiredArg().ofType(String.class);
         OptionSpecBuilder var5 = var1.accepts("integrationTest");
         ArgumentAcceptingOptionSpec var6 = var1.accepts("ichorClassPath").withRequiredArg().ofType(String.class);
         ArgumentAcceptingOptionSpec var7 = var1.accepts("ichorExternalFiles").withRequiredArg().ofType(String.class);
         OptionSpecBuilder var8 = var1.accepts("dumpClasses");
         ArgumentAcceptingOptionSpec var9 = var1.accepts("partialJar").withRequiredArg().ofType(String.class);
         ArgumentAcceptingOptionSpec var10 = var1.accepts("installationId").withRequiredArg().defaultsTo("not supplied", new String[0]);
         OptionSet var11 = var1.parse(var0);
         boolean var12 = var11.has(var5);
         Path var13 = Paths.get((String)var11.valueOf(var3));
         String var14 = (String)var11.valueOf(var4);
         Path var15 = new File(var14).toPath();
         Path var16 = var15.resolve("overrides");
         String var17 = (String)var11.valueOf(var10);
         Genesis4.setUser(var17);

         try {
            Files.createDirectories(var16);
         } catch (Exception var38) {
            LOGGER.method4(FatalIchorError5.Type.WARN, var38);
         }

         List var18 = null;
         if (var11.has(var8) && Files.exists(DUMP_CLASSES_WHITELIST)) {
            var18 = Files.readAllLines(DUMP_CLASSES_WHITELIST);
            LOGGER.info("Using whitelist of classes to dump: " + var18);
         }

         Path var19 = null;
         if (var11.has(var9)) {
            var19 = Paths.get((String)var11.valueOf(var9));
         }

         String var20 = (String)var11.valueOf(var2);
         Path var21 = LUNARCLIENT_DATA.resolve("mx-cache");
         String[] var22 = null;
         String[] var23 = null;
         if (var11.has(var6)) {
            var22 = ((String)var11.valueOf(var6)).split(",");
         }

         if (var11.has(var7)) {
            var23 = ((String)var11.valueOf(var7)).split(",");

            for (String var27 : var23) {
               LOGGER.info("Found external file: " + var27);
            }
         }

         String[] var41 = getClasspath();
         URL[] var42 = new URL[var41.length];

         for (int var43 = 0; var43 < var41.length; var43++) {
            Path var45 = Paths.get(var41[var43]);
            if (!var17.equals("not supplied")) {
               LOGGER.info("Found classpath URL: " + var45 + " with checksum " + Files_5.method4(var45));
            }

            var42[var43] = var45.toUri().toURL();
         }

         if (var42.length == 0) {
            throw new IllegalStateException("No classpath URLs found from " + System.getProperty("java.class.path"));
         }

         ClassLoader var44 = Genesis.class.getClassLoader();

         try (URLClassLoader var46 = new URLClassLoader("Prelaunch", var42, var44)) {
            PreLaunchLibraryBootstrap.Data var28 = new PreLaunchLibraryBootstrap.Data(var20, var21, var13, var16, PRELAUNCH_LIB_DIR);
            List var29 = var46.method1(PreLaunchLibraryBootstrap.class, var28);
            ArrayList var30 = Arrays.stream(var42).collect(Collectors.toCollection(ArrayList::new));

            for (Path var32 : var29) {
               try {
                  LOGGER.info("Adding pre-launch library to game classpath: " + var32);
                  if (!FatalIchorError9.method9(var32)) {
                     throw new IllegalStateException("Library is not a file: " + var32);
                  }

                  var30.add(var32.toUri().toURL());
               } catch (MalformedURLException var37) {
                  throw new RuntimeException(var37);
               }
            }

            try (URLClassLoader var47 = new URLClassLoader("Game", var30.toArray(new URL[0]), var44)) {
               Thread.currentThread().setContextClassLoader(var47);
               ClientGameBootstrap.Data var48 = new ClientGameBootstrap.Data(
                  var0, var13, var21, var16, var20, var22, var23, var12, var15, PRELAUNCH_LIB_DIR, var19, var18
               );
               var47.method1(ClientGameBootstrap.class, var48);
            }
         }
      } catch (Throwable var40) {
         throw var40;
      }
   }

   public static String[] getClasspath() {
      return System.getProperty("java.class.path").split(File.pathSeparator);
   }
}
