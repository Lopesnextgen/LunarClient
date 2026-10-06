package com.moonsworth.lunar.genesis;

import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.files.FileHashUtils;
import com.moonsworth.lunar.ichor.api.IchorAPI;
import io.sentry.SentryEvent;
import io.sentry.protocol.App;
import io.sentry.protocol.Contexts;
import io.sentry.protocol.OperatingSystem;
import io.sentry.protocol.User;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Sentry {
   private static final String field1 = "https://49db0029d2304d69bd80986185f7fafd@o923049.ingest.sentry.io/6029559";
   private static final String field2 = "https://49c0ccff7276e28e55e9e82de4addd1d@o923049.ingest.sentry.io/6029559";

   public Sentry() {
   }

   public static void init() {
      if (Genesis3.field4) {
         method1(Genesis3.field1, Genesis3.field3, Genesis3.field5);
      }
   }

   public static void setUser(String text0) {
      User user1 = new User();
      user1.setUsername(text0);
      io.sentry.Sentry.setUser(user1);
   }

   private static void method1(String text0, String text1, String text2) {
      io.sentry.Sentry.init(
         arg3 -> {
            arg3.setBeforeSend(Sentry::method2);
            arg3.setAttachServerName(false);
            arg3.setDsn(
               text0.equals("master")
                  ? "https://49db0029d2304d69bd80986185f7fafd@o923049.ingest.sentry.io/6029559"
                  : "https://49c0ccff7276e28e55e9e82de4addd1d@o923049.ingest.sentry.io/6029559"
            );
            arg3.setEnvironment(text0.replaceAll("/", "_"));
            arg3.setProguardUuid(text2);
            String text4 = text0 + "@" + text1;
            arg3.setRelease(text4.replace('/', '_'));
            arg3.setEnableUncaughtExceptionHandler(false);
            if (text0.equals("master")) {
               arg3.setTracesSampleRate(0.1);
            } else {
               arg3.setTracesSampleRate(1.0);
            }
         },
         true
      );
   }

   private static SentryEvent method2(@NotNull SentryEvent sentryevent0, @Nullable Object obj1) {
      String text2 = "Sending event to Sentry: " + sentryevent0.getEventId();
      Throwable exception3 = sentryevent0.getThrowable();
      if (exception3 != null) {
         text2 = text2 + " (" + exception3.getClass().getName() + ": " + exception3.getMessage() + ")";
      }

      System.err.println(text2);
      HashMap map4 = new HashMap();
      sentryevent0.setTag("lunar_version", method3());
      IchorAPI.getPipeline(Genesis.class.getClassLoader()).ifPresentOrElse(arg2x -> {
         List list3x = arg2x.method18().map(arg0xx -> arg0xx.getId() + "=" + arg0xx.getVersion()).sorted().toList();
         sentryevent0.setTag("minecraft_version", Config.method36(arg2x.method34().method6()).method45());
         sentryevent0.setTag("ichor_modules", String.join(",", arg2x.method31().keySet().toArray(String[]::new)));
         sentryevent0.setTag("external_mods", method4(list3x));
         map4.put("external_mods", String.join(",", list3x));
      }, () -> {
         sentryevent0.setTag("minecraft_version", "unknown");
         sentryevent0.setTag("ichor_modules", "unknown");
         sentryevent0.setTag("external_mods", "unknown");
         map4.put("external_mods", "unknown");
      });
      Contexts contexts5 = sentryevent0.getContexts();
      OperatingSystem operatingsystem6 = new OperatingSystem();
      operatingsystem6.setName(System.getProperty("os.name"));
      operatingsystem6.setVersion(System.getProperty("os.version"));
      contexts5.setOperatingSystem(operatingsystem6);
      App app7 = new App();
      Runtime runtime8 = Runtime.getRuntime();
      map4.put("total_memory_mb", (int)(runtime8.totalMemory() / 1048576.0));
      map4.put("free_memory_mb", (int)(runtime8.freeMemory() / 1048576.0));
      map4.put("max_memory_mb", (int)(runtime8.maxMemory() / 1048576.0));
      app7.setUnknown(map4);
      contexts5.setApp(app7);
      return sentryevent0;
   }

   private static String method3() {
      return Genesis3.field1.equals("master") ? "v" + Genesis3.field6 : Genesis3.field2 + "/" + Genesis3.field1;
   }

   public static String method4(List<String> list0) {
      try {
         MessageDigest messagedigest1 = MessageDigest.getInstance("SHA-1");
         messagedigest1.update(String.join(",", list0).getBytes(StandardCharsets.UTF_8));
         return FileHashUtils.method5(messagedigest1.digest());
      } catch (Exception exception2) {
         return "unknown";
      }
   }
}
