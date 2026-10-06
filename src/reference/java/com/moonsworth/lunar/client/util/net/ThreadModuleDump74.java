package com.moonsworth.lunar.client.util.net;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import lombok.Generated;
import org.json.JSONObject;
import com.moonsworth.lunar.client.util.Type;

public final class ThreadModuleDump74 {
   public static final int field1 = 10;

   public static void sendAsync(String var0, Builder var1, Consumer<JsonObject> var2) {
      sendAsync(var0, var1, var2, null);
   }

   public static void sendAsync(String var0, Builder var1, Consumer<JsonObject> var2, Consumer<Throwable> var3) {
      try {
         URI var4 = new URI(var0);
         var1.uri(var4).timeout(Duration.ofSeconds(10L));
         HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10L))
            .build()
            .sendAsync(var1.build(), BodyHandlers.ofString())
            .orTimeout(10L, TimeUnit.SECONDS)
            .handleAsync((var2x, var3x) -> {
               if (var3x != null) {
                  if (var3 != null) {
                     var3.accept(var3x);
                  }
               } else {
                  JsonElement var4x = new JsonParser().parse((String)var2x.body());
                  if (var4x.isJsonObject()) {
                     try {
                        var2.accept(var4x.getAsJsonObject());
                     } catch (Exception var6) {
                        if (var3 != null) {
                           var3.accept(var6);
                        }
                     }
                  } else if (var3 != null) {
                     var3.accept(new NullPointerException("object was not a JsonObject"));
                  }
               }

               return null;
            });
      } catch (Exception var5) {
         if (var3 != null) {
            var3.accept(var5);
         }
      }
   }

   public static BodyPublisher jsonBody(Map<Object, Object> var0) {
      return BodyPublishers.ofString(new JSONObject(var0).toString());
   }

   public static BodyPublisher formBody(Map<Object, Object> var0) {
      StringBuilder var1 = new StringBuilder();

      for (Entry var3 : var0.entrySet()) {
         if (var1.length() > 0) {
            var1.append("&");
         }

         var1.append(URLEncoder.encode(var3.getKey().toString(), StandardCharsets.UTF_8));
         var1.append("=");
         var1.append(URLEncoder.encode(var3.getValue().toString(), StandardCharsets.UTF_8));
      }

      return BodyPublishers.ofString(var1.toString());
   }

   public static BodyPublisher multipartBody(String var0, Map<Object, Object> var1) {
      ArrayList var2 = new ArrayList();

      for (Entry var4 : var1.entrySet()) {
         StringBuilder var5 = new StringBuilder();
         var5.append("\r\n--").append(var0).append("\r\n");
         var5.append("Content-Disposition: form-data; name=\"").append(var4.getKey()).append("\"");
         if (var4.getValue() instanceof ThreadModuleDump74.Data var6) {
            var5.append("; filename=\"").append(var6.filename()).append("\"\r\n");
            var5.append("Content-Type: ").append(var6.method1()).append("\r\n\r\n");
            var2.add(var5.toString().getBytes(StandardCharsets.UTF_8));
            var2.add(var6.method2());
         } else {
            var5.append("\r\n\r\n");
            var5.append(var4.getValue());
            var2.add(var5.toString().getBytes(StandardCharsets.UTF_8));
         }
      }

      var2.add(("\r\n--" + var0 + "--\r\n").getBytes(StandardCharsets.UTF_8));
      return BodyPublishers.ofByteArrays(var2);
   }

   public static String randomBoundary() {
      return UUID.randomUUID().toString();
   }

   public static String ensureHttpScheme(String var0) {
      return !var0.startsWith("http://") && !var0.startsWith("https://") ? "http://" + var0 : var0;
   }

   public static String ensureWebSocketScheme(String var0) {
      return var0.startsWith("wss://") ? var0 : "wss://" + var0;
   }

   @Generated
   private ThreadModuleDump74() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   public class Data {
      private final String filename;
      private final String field1;
      private final byte[] field2;

      public Data(String var1, String var2, byte[] var3) {
         this.filename = var1;
         this.TIMEOUT_SECONDS = var2;
         this.field2 = var3;
      }

      public String sendAsync() {
         return this.TIMEOUT_SECONDS;
      }

      public byte[] sendAsync() {
         return this.field2;
      }
   }
}
