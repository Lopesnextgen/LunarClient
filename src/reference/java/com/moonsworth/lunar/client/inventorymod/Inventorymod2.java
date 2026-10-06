package com.moonsworth.lunar.client.inventorymod;

import com.google.common.collect.EvictingQueue;
import com.google.common.collect.Queues;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_22;
import com.moonsworth.lunar.bridge.Bridge7_2;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.config.FeatureFlag;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump68;
import com.moonsworth.lunar.client.util.ThreadModuleDump80;
import com.moonsworth.lunar.files.Files_5;
import com.moonsworth.lunar.ichor.IchorPipeline;
import com.moonsworth.lunar.ichor.api.IchorAPI;
import io.sentry.Breadcrumb;
import io.sentry.Hint;
import io.sentry.Sentry;
import io.sentry.SentryEvent;
import io.sentry.SentryLevel;
import io.sentry.protocol.App;
import io.sentry.protocol.Contexts;
import io.sentry.protocol.Device;
import io.sentry.protocol.OperatingSystem;
import io.sentry.protocol.SentryId;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Inventorymod2 {
   private static final boolean field1 = Boolean.parseBoolean(System.getProperty("lunar.sentryDebug", "false"));
   private static final boolean field2 = Boolean.parseBoolean(System.getProperty("lunar.sentrySendErrors", Boolean.toString(LunarBuildData.field4)));
   private static final String field3 = "https://49db0029d2304d69bd80986185f7fafd@o923049.ingest.sentry.io/6029559";
   private static final String field4 = "https://49c0ccff7276e28e55e9e82de4addd1d@o923049.ingest.sentry.io/6029559";
   private static final int field5 = 100;
   private static long field6 = 0L;
   private static int field7 = 0;
   private static volatile boolean field8 = false;
   @NotNull
   private static final Queue<Breadcrumb> field9 = createBreadcrumbsList(50);

   public static void init() {
      Sentry.init(
         var0 -> {
            var0.setMaxBreadcrumbs(100);
            var0.setBeforeSend(Inventorymod2::method2);
            var0.setAttachServerName(false);
            var0.setDsn(
               ThreadModuleDump48.field28
                  ? "https://49db0029d2304d69bd80986185f7fafd@o923049.ingest.sentry.io/6029559"
                  : "https://49c0ccff7276e28e55e9e82de4addd1d@o923049.ingest.sentry.io/6029559"
            );
            var0.setEnvironment(LunarBuildData.field1.replaceAll("/", "_"));
            var0.setDebug(!LunarBuildData.field4 && field1);
            var0.setProguardUuid(LunarBuildData.field5);
            String var1 = LunarBuildData.field1 + "@" + LunarBuildData.field3;
            var0.setRelease(var1.replace('/', '_'));
            var0.setEnableUncaughtExceptionHandler(false);
            String var2 = ThreadModuleDump80.sentryTraceId;
            if (!Set.of("?", "not supplied").contains(var2)) {
               var0.addTracingOrigin(var2);
            }

            if (ThreadModuleDump48.field28) {
               var0.setTracesSampler(var0x -> FeatureFlag.SENTRY_TRACE_SAMPLING.isEnabled() ? 0.05 : 0.0);
            } else {
               var0.setTracesSampleRate(1.0);
            }
         },
         true
      );
      IchorAPI.getPipeline(Inventorymod2.class).ifPresent(var0 -> var0.method38().method1(var0x -> {
         var0x.printStackTrace();
         String var1 = method4(SentryLevel.FATAL, "LCCR", "IchorPipeline Error Handler", var0x, null);
         Slayer.method6("IchorPipeline Error Handler", "Submitting Sentry event with ID " + var1);
         method1(Breadcrumb.error(var0x.getClass().getName() + ": " + Inventorymod3.method2(var0x).getMessage()));
      }));
   }

   public static void method1(Breadcrumb var0) {
      field9.add(var0);
      Sentry.addBreadcrumb(var0);
   }

   private static SentryEvent method2(@NotNull SentryEvent var0, @Nullable Object var1) {
      if (!field2) {
         Slayer.method6("Report Handler", "Dropping Sentry event as error sending is disabled.");
         return null;
      }

      Throwable var2 = var0.getThrowable();
      if (var2 != null && !Inventorymod3.method1(var2)) {
         Slayer.method6("Report Handler", "Dropping Sentry event as error is not useful.");
         return null;
      }

      if (var0.getLevel() != SentryLevel.FATAL) {
         if (field6 == 0L) {
            field6 = ThreadModuleDump63.method14();
         }

         if (field7 >= 5) {
            if (ThreadModuleDump63.method14() - field6 < 60000L) {
               field6 = ThreadModuleDump63.method14();
               Slayer.method6("Report Handler", "Dropping Sentry event due to rate limit.");
               return null;
            }

            field6 = ThreadModuleDump63.method14();
            field7 = 0;
         }
      }

      boolean var3 = Client.method109() != null && Client.method109().method40() != null;
      Optional var4 = IchorAPI.getPipeline(Client.class.getClassLoader());
      List var5 = var4.isEmpty() ? List.of() : ((IchorPipeline)var4.get()).method18().map(var0x -> var0x.getId() + "=" + var0x.getVersion()).sorted().toList();
      var0.setTag("minecraft_version", Bridge.getMinecraftVersion().method45());
      var0.setTag("launcher_version", ThreadModuleDump80.launcherVersion);
      var0.setTag("canary_token", ThreadModuleDump80.canaryToken);
      if (ThreadModuleDump80.modrinthModpackProjectId != null) {
         var0.setTag("modrinth_modpack_project_id", ThreadModuleDump80.modrinthModpackProjectId);
      }

      if (ThreadModuleDump80.modrinthModpackVersionId != null) {
         var0.setTag("modrinth_modpack_version_id", ThreadModuleDump80.modrinthModpackVersionId);
      }

      if (ThreadModuleDump80.curseforgeModpackModId != null) {
         var0.setTag("curseforge_modpack_mod_id", ThreadModuleDump80.curseforgeModpackModId);
      }

      if (ThreadModuleDump80.curseforgeModpackFileId != null) {
         var0.setTag("curseforge_modpack_file_id", ThreadModuleDump80.curseforgeModpackFileId);
      }

      var0.setTag("lunar_version", Client.method19());
      var0.setTag("ichor_modules", String.join(",", method8()));
      var0.setTag("external_mods", var5.isEmpty() ? "unknown" : method9(var5));
      if (Bridge.method42().method1()) {
         var0.setTag("gpu_vendor", Bridge.method42().method85().field1);
      }

      if (var1 instanceof Hint var6) {
         Object var7 = var6.get("geckolib_cosmetic");
         if (var7 != null) {
            var0.setTag("geckolib_cosmetic", (String)var7);
         }
      }

      Contexts var14 = var0.getContexts();
      OperatingSystem var15 = new OperatingSystem();
      var15.setName(ThreadModuleDump48.field1);
      var15.setVersion(System.getProperty("os.version"));
      var14.setOperatingSystem(var15);
      Device var8 = new Device();
      var8.setCpuDescription(var3 ? Bridge.method22().method9() : "unknown");
      var8.setArchs(new String[]{ThreadModuleDump48.field3});
      if (Client.method109() != null && Client.method109().method67() != null) {
         var8.setLanguage(Client.method109().method67().method21().getBase());
      } else {
         var8.setLanguage(ThreadModuleDump80.language.split("_")[0]);
      }

      var14.setDevice(var8);
      App var9 = new App();
      HashMap var10 = new HashMap();
      Runtime var11 = Runtime.getRuntime();
      String var12 = var3
         ? (
            ThreadModuleDump63.method3().bridge$getCurrentServerData() == null
               ? "None"
               : ThreadModuleDump63.method3().bridge$getCurrentServerData().bridge$serverIP()
         )
         : "unknown";
      var10.put("connected_server", var12);
      var10.put("total_memory_mb", (int)(var11.totalMemory() / 1048576.0));
      var10.put("free_memory_mb", (int)(var11.freeMemory() / 1048576.0));
      var10.put("max_memory_mb", (int)(var11.maxMemory() / 1048576.0));
      var10.put("fps", var3 ? ThreadModuleDump63.method3().bridge$getDebugFPS() : -1);
      var10.put("installation_id", ThreadModuleDump80.installationId);
      var10.put("overwolf_muid", ThreadModuleDump80.overwolfMuid);
      var10.put("resource_pack", ThreadModuleDump63.method3().bridge$getSelectedResourcePack().bridge$getPackName());
      var10.put("external_mods", var5.isEmpty() ? "unknown" : String.join(",", var5));
      if (Bridge.method42().method1()) {
         Bridge7_2 var13 = Bridge.method42().method85();
         var10.put("gpu_renderer", var13.field3);
         var10.put("gpu_vendor", var13.field1);
      }

      var9.setUnknown(var10);
      var14.setApp(var9);
      method3(var0);
      field7++;
      return var0;
   }

   private static void method3(SentryEvent var0) {
      if (!field9.isEmpty()) {
         List var1 = var0.getBreadcrumbs();
         if (var1 != null) {
            ArrayList var2 = new ArrayList(var1.size() + field9.size());
            int var3 = 0;

            for (Breadcrumb var5 : field9) {
               if (!var1.contains(var5)) {
                  var2.add(var5);
               } else {
                  var3++;
               }
            }

            int var9 = field9.size() - var3;
            if (var1.size() + var9 <= 100) {
               var2.addAll(var1);
            } else {
               int var10 = 100 - var9;
               ArrayList var6 = new ArrayList(100);

               for (int var7 = var1.size() - 1; var7 > 0; var7--) {
                  Breadcrumb var8 = (Breadcrumb)var1.get(var7);
                  if (!field9.contains(var8)) {
                     if (var10 == 0) {
                        continue;
                     }

                     var10--;
                  }

                  var6.add(0, var8);
               }

               var2.addAll(var6);
            }

            var0.setBreadcrumbs(var2);
         } else {
            var0.setBreadcrumbs(new ArrayList<>(field9));
         }
      }
   }

   @Nullable
   private static String method4(SentryLevel var0, String var1, String var2, Throwable var3, @Nullable Hint var4) {
      var3 = Inventorymod3.method2(var3);
      SentryId var5 = Sentry.captureException(var3, var4, var2x -> {
         var2x.setLevel(var0);
         var2x.setTag("capture_location", var2);
      });
      return var5.equals(SentryId.EMPTY_ID) ? null : var1 + "-" + var5;
   }

   public static void method5(Throwable var0, String var1) {
      method6(var0, var1, true, null);
   }

   public static void method6(Throwable var0, String var1, boolean var2, @Nullable Hint var3) {
      if (var2) {
         var0.printStackTrace();
      }

      String var4 = method4(SentryLevel.ERROR, "LCEX", var1, var0, var3);
      Slayer.method6(var1, "Submitting Sentry event with ID " + var4);
   }

   public static void method7(Bridge3_22 var0) {
      field8 = true;
      Throwable var1 = var0.bridge$getCause();
      String var2 = method4(SentryLevel.FATAL, "LCCR", "Minecraft Crash Report Handler", var1, null);
      if (var2 != null) {
         ThreadModuleDump68.setClipboardString(var2);
      }

      try {
         UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
      } catch (Exception var5) {
      }

      if (var1 instanceof OutOfMemoryError) {
         new Thread(
               () -> JOptionPane.showMessageDialog(
                  null,
                  "Your client has ran out of memory.\nYou can increase memory allocation in the launcher. \n\nPlease use the following code (also copied to your clipboard) when submitting a bug report: \n\n"
                     + var2,
                  "Out of Memory",
                  2
               )
            )
            .start();
      } else {
         new Thread(
               () -> JOptionPane.showMessageDialog(
                  null,
                  "Your client has crashed. \n\nPlease use the following code (also copied to your clipboard) when submitting a bug report: \n\n" + var2,
                  "Something went wrong",
                  2
               )
            )
            .start();
      }

      Slayer.method3("==============================");
      if (var2 != null) {
         Slayer.method3("Report Id: " + var2);
      }

      Slayer.method3("MC version: " + Bridge.getMinecraftVersion().method45());
      Slayer.method3("Branch: " + LunarBuildData.field1);
      Slayer.method3("Commit: " + LunarBuildData.field3 + " (" + LunarBuildData.field2 + ")");
      Slayer.method3("Modules: " + Arrays.toString(method8()));
      Slayer.method3("OS: " + ThreadModuleDump48.field1);
      Slayer.method3("Arch: " + ThreadModuleDump48.field3);
      Slayer.method3("Launcher Version: " + ThreadModuleDump80.launcherVersion);
      Slayer.method3("Launcher ID: " + ThreadModuleDump80.installationId);
      Slayer.method3("Overwolf MUID: " + ThreadModuleDump80.overwolfMuid);
      Slayer.method3("Launch ID: " + ThreadModuleDump80.launchId);
      Slayer.method3("Sentry Trace ID: " + ThreadModuleDump80.sentryTraceId);
      Slayer.method3("==============================");
      if (LunarBuildData.field4) {
         try {
            Thread.sleep(5000L);
         } catch (InterruptedException var4) {
         }
      }
   }

   public static String[] method8() {
      return IchorAPI.getPipeline(Client.class.getClassLoader()).map(var0 -> var0.method31().keySet().toArray(String[]::new)).orElse(new String[0]);
   }

   public static String method9(List<String> var0) {
      try {
         MessageDigest var1 = MessageDigest.getInstance("SHA-1");
         var1.update(String.join(",", var0).getBytes(StandardCharsets.UTF_8));
         return Files_5.method5(var1.digest());
      } catch (Exception var2) {
         return "unknown";
      }
   }

   @NotNull
   private static Queue<Breadcrumb> createBreadcrumbsList(int var0) {
      return Queues.synchronizedQueue(EvictingQueue.create(var0));
   }

   @Generated
   public static boolean method10() {
      return field8;
   }
}
