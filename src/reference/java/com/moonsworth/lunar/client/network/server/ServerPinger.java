package com.moonsworth.lunar.client.network.server;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.google.gson.annotations.SerializedName;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_19;
import com.moonsworth.lunar.bridge.Bridge3_30;
import com.moonsworth.lunar.client.framework.LoadableHandler;
import com.moonsworth.lunar.client.util.ThreadModuleDump37;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerPinger implements LoadableHandler {
   private static final Pattern field1 = Pattern.compile("(?i)§[0-9A-FK-OR]");
   private static final Pattern field2 = Pattern.compile("(\\d{1,9})\\s*/\\s*(\\d{1,9})");
   private static final int field3 = 5;
   private final Map<String, ServerPinger.Data2> field4 = new ConcurrentHashMap<>();
   private Bridge3_30 field5;
   private Future<Void> field6;
   private ExecutorService field7;
   private volatile boolean closed;

   @Override
   public void init() {
      this.field5 = Bridge.method8().method27();
      this.field7 = Executors.newCachedThreadPool(new ThreadFactoryBuilder().setNameFormat("lunar-server-ping-%d").setDaemon(true).build());
      this.field6 = ThreadModuleDump37.method15(() -> {
         if (!this.closed && this.field5 != null && !this.field4.isEmpty()) {
            this.field5.bridge$tick();
         }
      }, 0, 1);
   }

   @Override
   public void close() {
      this.closed = true;
      if (this.field6 != null) {
         this.field6.cancel(true);
      }

      if (this.field7 != null) {
         this.field7.shutdownNow();
      }

      for (ServerPinger.Data2 var2 : this.field4.values()) {
         var2.method1(ServerPinger.Data.method3(var2.address, "Client is shutting down."));
      }

      this.field4.clear();
   }

   public CompletableFuture<ServerPinger.Data> method1(String var1) {
      String var2 = method3(var1);
      if (var2 == null) {
         return CompletableFuture.completedFuture(ServerPinger.Data.method3("", "Enter a server address."));
      }

      if (this.closed) {
         return CompletableFuture.completedFuture(ServerPinger.Data.method3(var2, "Server pinger is closed."));
      }

      ServerPinger.Data2 var3 = new ServerPinger.Data2(var2);
      ServerPinger.Data2 var4 = this.field4.putIfAbsent(var2, var3);
      if (var4 != null) {
         return var4.field1;
      }

      try {
         this.field7.execute(() -> this.method2(var3));
      } catch (RejectedExecutionException var6) {
         var3.method1(ServerPinger.Data.method3(var2, "Server pinger is closed."));
      }

      return var3.field1;
   }

   private void method2(ServerPinger.Data2 var1) {
      if (this.closed) {
         var1.method1(ServerPinger.Data.method3(var1.address, "Server pinger is closed."));
      } else {
         var1.field2 = ThreadModuleDump37.method5().schedule(() -> var1.method1(ServerPinger.Data.method2(var1.address)), 5L, TimeUnit.SECONDS);
         Bridge3_19 var2 = Bridge.method8().method50(var1.address, var1.address, false);
         var1.field3 = var2;
         var2.bridge$setPingCallback(var2x -> var1.method1(ServerPinger.Data.method1(var1.address, var2)));

         try {
            this.field5.bridge$ping(var2);
         } catch (UnknownHostException var4) {
            var1.method1(ServerPinger.Data.method3(var1.address, "Unknown host."));
         } catch (Throwable var5) {
            var1.method1(ServerPinger.Data.method3(var1.address, "Unable to ping server."));
         }
      }
   }

   private static String method3(String var0) {
      if (var0 == null) {
         return null;
      }

      String var1 = var0.trim();
      return var1.isEmpty() ? null : var1.toLowerCase(Locale.ROOT);
   }

   public static final class Data {
      private static final String field1 = "success";
      private static final String field2 = "timeout";
      private static final String field3 = "error";
      @SerializedName("status")
      private final String field4;
      @SerializedName("address")
      private final String field5;
      @SerializedName("ping")
      private final long ping;
      @SerializedName("playersOnline")
      private final int field6;
      @SerializedName("playersMax")
      private final int field7;
      @SerializedName("lunarServer")
      private final String field8;
      @SerializedName("error")
      private final String error;

      private Data(String var1, String var2, long var3, int var5, int var6, String var7, String var8) {
         this.field4 = var1;
         this.field5 = var2;
         this.ping = var3;
         this.field6 = var5;
         this.field7 = var6;
         this.field8 = var7;
         this.error = var8;
      }

      private static ServerPinger.Data method1(String var0, Bridge3_19 var1) {
         String var2 = var1.bridge$getPopulationInfo();
         int var3 = 0;
         int var4 = 0;
         if (var2 != null) {
            Matcher var5 = ServerPinger.field2.matcher(ServerPinger.field1.matcher(var2).replaceAll(""));
            if (var5.find()) {
               var3 = Integer.parseInt(var5.group(1));
               var4 = Integer.parseInt(var5.group(2));
            }
         }

         return new ServerPinger.Data("success", var0, var1.bridge$getPingToServer(), var3, var4, var1.getLunarServer(), null);
      }

      private static ServerPinger.Data method2(String var0) {
         return new ServerPinger.Data("timeout", var0, -1L, -1, -1, null, "Server ping timed out.");
      }

      private static ServerPinger.Data method3(String var0, String var1) {
         return new ServerPinger.Data("error", var0, -1L, -1, -1, null, var1);
      }
   }

   private final class Data2 {
      private final String address;
      private final CompletableFuture<ServerPinger.Data> field1 = new CompletableFuture<>();
      private volatile ScheduledFuture<?> field2;
      private volatile Bridge3_19 field3;

      private Data2(String var2) {
         this.address = var2;
      }

      private void method1(ServerPinger.Data var1) {
         if (this.field1.complete(var1)) {
            if (this.field2 != null) {
               this.field2.cancel(false);
            }

            if (this.field3 != null) {
               this.field3.bridge$setPingCallback(null);
            }

            ServerPinger.this.field4.remove(this.address, this);
         }
      }
   }
}
