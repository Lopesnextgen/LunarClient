package com.moonsworth.lunar.client.framework.crash;

import com.google.common.collect.EvictingQueue;
import com.google.common.collect.Queues;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.CrashReportBridge;
import com.moonsworth.lunar.bridge.Bridge7_2;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.config.FeatureFlag;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.io.ClipboardUtils;
import com.moonsworth.lunar.client.framework.LaunchOptions;
import com.moonsworth.lunar.files.FileHashUtils;
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

public class CrashReporter {
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

   public CrashReporter() {
   }

   public static void init() {
      Sentry.init(
         arg0 -> {
            arg0.setMaxBreadcrumbs(100);
            arg0.setBeforeSend(CrashReporter::method2);
            arg0.setAttachServerName(false);
            arg0.setDsn(
               LunarConstants.field28
                  ? "https://49db0029d2304d69bd80986185f7fafd@o923049.ingest.sentry.io/6029559"
                  : "https://49c0ccff7276e28e55e9e82de4addd1d@o923049.ingest.sentry.io/6029559"
            );
            arg0.setEnvironment(LunarBuildData.field1.replaceAll("/", "_"));
            arg0.setDebug(!LunarBuildData.field4 && field1);
            arg0.setProguardUuid(LunarBuildData.field5);
            String text1 = LunarBuildData.field1 + "@" + LunarBuildData.field3;
            arg0.setRelease(text1.replace('/', '_'));
            arg0.setEnableUncaughtExceptionHandler(false);
            String text2 = LaunchOptions.field3;
            if (!Set.of("?", "not supplied").contains(text2)) {
               arg0.addTracingOrigin(text2);
            }

            if (LunarConstants.field28) {
               arg0.setTracesSampler(arg0x -> FeatureFlag.SENTRY_TRACE_SAMPLING.isEnabled() ? 0.05 : 0.0);
            } else {
               arg0.setTracesSampleRate(1.0);
            }
         },
         true
      );
      IchorAPI.getPipeline(CrashReporter.class).ifPresent(arg0 -> arg0.method38().method1(arg0x -> {
         arg0x.printStackTrace();
         String text1 = method4(SentryLevel.FATAL, "LCCR", "IchorPipeline Error Handler", arg0x, null);
         LunarLogger.method6("IchorPipeline Error Handler", "Submitting Sentry event with ID " + text1, new Object[0]);
         method1(Breadcrumb.error(arg0x.getClass().getName() + ": " + ExceptionSanitizer.method2(arg0x).getMessage()));
      }));
   }

   public static void method1(Breadcrumb breadcrumb0) {
      field9.add(breadcrumb0);
      Sentry.addBreadcrumb(breadcrumb0);
   }

   private static SentryEvent method2(@NotNull SentryEvent sentryevent0, @Nullable Object obj1) {
      if (!field2) {
         LunarLogger.method6("Report Handler", "Dropping Sentry event as error sending is disabled.", new Object[0]);
         return null;
      }

      Throwable exception2 = sentryevent0.getThrowable();
      if (exception2 != null && !ExceptionSanitizer.method1(exception2)) {
         LunarLogger.method6("Report Handler", "Dropping Sentry event as error is not useful.", new Object[0]);
         return null;
      }

      if (sentryevent0.getLevel() != SentryLevel.FATAL) {
         if (field6 == 0L) {
            field6 = Ref.method14();
         }

         if (field7 >= 5) {
            if (Ref.method14() - field6 < 60000L) {
               field6 = Ref.method14();
               LunarLogger.method6("Report Handler", "Dropping Sentry event due to rate limit.", new Object[0]);
               return null;
            }

            field6 = Ref.method14();
            field7 = 0;
         }
      }

      boolean flag3 = Client.method109() != null && Client.method109().method40() != null;
      Optional optional4 = IchorAPI.getPipeline(Client.class.getClassLoader());
      List list5 = optional4.isEmpty() ? List.of() : ((IchorPipeline)optional4.get()).method18().map(arg0x -> arg0x.getId() + "=" + arg0x.getVersion()).sorted().toList();
      sentryevent0.setTag("minecraft_version", Bridge.getMinecraftVersion().method45());
      sentryevent0.setTag("launcher_version", LaunchOptions.field7);
      sentryevent0.setTag("canary_token", LaunchOptions.field5);
      if (LaunchOptions.field8 != null) {
         sentryevent0.setTag("modrinth_modpack_project_id", LaunchOptions.field8);
      }

      if (LaunchOptions.field9 != null) {
         sentryevent0.setTag("modrinth_modpack_version_id", LaunchOptions.field9);
      }

      if (LaunchOptions.field10 != null) {
         sentryevent0.setTag("curseforge_modpack_mod_id", LaunchOptions.field10);
      }

      if (LaunchOptions.field11 != null) {
         sentryevent0.setTag("curseforge_modpack_file_id", LaunchOptions.field11);
      }

      sentryevent0.setTag("lunar_version", Client.method19());
      sentryevent0.setTag("ichor_modules", String.join(",", method8()));
      sentryevent0.setTag("external_mods", list5.isEmpty() ? "unknown" : method9(list5));
      if (Bridge.method42().method1()) {
         sentryevent0.setTag("gpu_vendor", Bridge.method42().method85().field1);
      }

      if (obj1 instanceof Hint hint6) {
         Object obj7 = hint6.get("geckolib_cosmetic");
         if (obj7 != null) {
            sentryevent0.setTag("geckolib_cosmetic", (String)obj7);
         }
      }

      Contexts contexts14 = sentryevent0.getContexts();
      OperatingSystem operatingsystem15 = new OperatingSystem();
      operatingsystem15.setName(LunarConstants.field1);
      operatingsystem15.setVersion(System.getProperty("os.version"));
      contexts14.setOperatingSystem(operatingsystem15);
      Device device8 = new Device();
      device8.setCpuDescription(flag3 ? Bridge.method22().method9() : "unknown");
      device8.setArchs(new String[]{LunarConstants.field3});
      if (Client.method109() != null && Client.method109().method67() != null) {
         device8.setLanguage(Client.method109().method67().method21().getBase());
      } else {
         device8.setLanguage(LaunchOptions.field12.split("_")[0]);
      }

      contexts14.setDevice(device8);
      App app9 = new App();
      HashMap map10 = new HashMap();
      Runtime runtime11 = Runtime.getRuntime();
      String text12 = flag3
         ? (
            Ref.method3().bridge$getCurrentServerData() == null
               ? "None"
               : Ref.method3().bridge$getCurrentServerData().bridge$serverIP()
         )
         : "unknown";
      map10.put("connected_server", text12);
      map10.put("total_memory_mb", (int)(runtime11.totalMemory() / 1048576.0));
      map10.put("free_memory_mb", (int)(runtime11.freeMemory() / 1048576.0));
      map10.put("max_memory_mb", (int)(runtime11.maxMemory() / 1048576.0));
      map10.put("fps", flag3 ? Ref.method3().bridge$getDebugFPS() : -1);
      map10.put("installation_id", LaunchOptions.field1);
      map10.put("overwolf_muid", LaunchOptions.field2);
      map10.put("resource_pack", Ref.method3().bridge$getSelectedResourcePack().bridge$getPackName());
      map10.put("external_mods", list5.isEmpty() ? "unknown" : String.join(",", list5));
      if (Bridge.method42().method1()) {
         Bridge7_2 bridge7_213 = Bridge.method42().method85();
         map10.put("gpu_renderer", bridge7_213.field3);
         map10.put("gpu_vendor", bridge7_213.field1);
      }

      app9.setUnknown(map10);
      contexts14.setApp(app9);
      method3(sentryevent0);
      field7++;
      return sentryevent0;
   }

   private static void method3(SentryEvent sentryevent0) {
      if (!field9.isEmpty()) {
         List list1 = sentryevent0.getBreadcrumbs();
         if (list1 != null) {
            ArrayList list2 = new ArrayList(list1.size() + field9.size());
            int index3 = 0;

            for (Breadcrumb breadcrumb5 : field9) {
               if (!list1.contains(breadcrumb5)) {
                  list2.add(breadcrumb5);
               } else {
                  index3++;
               }
            }

            int number9 = field9.size() - index3;
            if (list1.size() + number9 <= 100) {
               list2.addAll(list1);
            } else {
               int index10 = 100 - number9;
               ArrayList list6 = new ArrayList(100);

               for (int index7 = list1.size() - 1; index7 > 0; index7--) {
                  Breadcrumb breadcrumb8 = (Breadcrumb)list1.get(index7);
                  if (!field9.contains(breadcrumb8)) {
                     if (index10 == 0) {
                        continue;
                     }

                     index10--;
                  }

                  list6.add(0, breadcrumb8);
               }

               list2.addAll(list6);
            }

            sentryevent0.setBreadcrumbs(list2);
         } else {
            sentryevent0.setBreadcrumbs(new ArrayList<>(field9));
         }
      }
   }

   @Nullable
   private static String method4(SentryLevel sentrylevel0, String text1, String text2, Throwable exception3, @Nullable Hint hint4) {
      exception3 = ExceptionSanitizer.method2(exception3);
      SentryId sentryid5 = Sentry.captureException(exception3, hint4, arg2x -> {
         arg2x.setLevel(sentrylevel0);
         arg2x.setTag("capture_location", text2);
      });
      return sentryid5.equals(SentryId.EMPTY_ID) ? null : text1 + "-" + sentryid5;
   }

   public static void method5(Throwable exception0, String text1) {
      method6(exception0, text1, true, null);
   }

   public static void method6(Throwable exception0, String text1, boolean flag, @Nullable Hint hint3) {
      if (flag) {
         exception0.printStackTrace();
      }

      String text4 = method4(SentryLevel.ERROR, "LCEX", text1, exception0, hint3);
      LunarLogger.method6(text1, "Submitting Sentry event with ID " + text4, new Object[0]);
   }

   public static void method7(CrashReportBridge bridge3_220) {
      field8 = true;
      Throwable exception1 = bridge3_220.bridge$getCause();
      String text2 = method4(SentryLevel.FATAL, "LCCR", "Minecraft Crash Report Handler", exception1, null);
      if (text2 != null) {
         ClipboardUtils.method2(text2);
      }

      try {
         UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
      } catch (Exception exception5) {
      }

      if (exception1 instanceof OutOfMemoryError) {
         new Thread(
               () -> JOptionPane.showMessageDialog(
                  null,
                  "Your client has ran out of memory.\nYou can increase memory allocation in the launcher. \n\nPlease use the following code (also copied to your clipboard) when submitting a bug report: \n\n"
                     + text2,
                  "Out of Memory",
                  2
               )
            )
            .start();
      } else {
         new Thread(
               () -> JOptionPane.showMessageDialog(
                  null,
                  "Your client has crashed. \n\nPlease use the following code (also copied to your clipboard) when submitting a bug report: \n\n" + text2,
                  "Something went wrong",
                  2
               )
            )
            .start();
      }

      LunarLogger.method3("==============================", new Object[0]);
      if (text2 != null) {
         LunarLogger.method3("Report Id: " + text2, new Object[0]);
      }

      LunarLogger.method3("MC version: " + Bridge.getMinecraftVersion().method45(), new Object[0]);
      LunarLogger.method3("Branch: " + LunarBuildData.field1, new Object[0]);
      LunarLogger.method3("Commit: " + LunarBuildData.field3 + " (" + LunarBuildData.field2 + ")", new Object[0]);
      LunarLogger.method3("Modules: " + Arrays.toString(method8()), new Object[0]);
      LunarLogger.method3("OS: " + LunarConstants.field1, new Object[0]);
      LunarLogger.method3("Arch: " + LunarConstants.field3, new Object[0]);
      LunarLogger.method3("Launcher Version: " + LaunchOptions.field7, new Object[0]);
      LunarLogger.method3("Launcher ID: " + LaunchOptions.field1, new Object[0]);
      LunarLogger.method3("Overwolf MUID: " + LaunchOptions.field2, new Object[0]);
      LunarLogger.method3("Launch ID: " + LaunchOptions.field4, new Object[0]);
      LunarLogger.method3("Sentry Trace ID: " + LaunchOptions.field3, new Object[0]);
      LunarLogger.method3("==============================", new Object[0]);
      if (LunarBuildData.field4) {
         try {
            Thread.sleep(5000L);
         } catch (InterruptedException interruptedexception4) {
         }
      }
   }

   public static String[] method8() {
      return IchorAPI.getPipeline(Client.class.getClassLoader()).map(arg0 -> arg0.method31().keySet().toArray(String[]::new)).orElse(new String[0]);
   }

   public static String method9(List<String> list) {
      try {
         MessageDigest messagedigest1 = MessageDigest.getInstance("SHA-1");
         messagedigest1.update(String.join(",", list).getBytes(StandardCharsets.UTF_8));
         return FileHashUtils.method5(messagedigest1.digest());
      } catch (Exception exception2) {
         return "unknown";
      }
   }

   @NotNull
   private static Queue<Breadcrumb> createBreadcrumbsList(int value) {
      return Queues.synchronizedQueue(EvictingQueue.create(value));
   }

   @Generated
   public static boolean method10() {
      return field8;
   }
}
