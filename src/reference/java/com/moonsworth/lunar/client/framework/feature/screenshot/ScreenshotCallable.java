package com.moonsworth.lunar.client.framework.feature.screenshot;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.util.net.ServiceEndpoints;
import com.moonsworth.lunar.client.framework.Ref;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.function.IntConsumer;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class ScreenshotCallable implements Callable<String> {
   private static final HttpClient field1 = HttpClient.newBuilder().build();
   private final Path path;
   private final String field2;
   @Nullable
   private final UUID field3;
   private IntConsumer field4;
   private final String field5;
   private final String field6;

   public String method1() {
      try {
         JsonObject json1 = new JsonObject();
         byte[] items2 = Files.readAllBytes(this.path);
         json1.addProperty("imageBase64", Base64.getEncoder().encodeToString(items2));
         if (this.field3 != null) {
            json1.addProperty("localId", this.field3.toString());
         }

         if (this.field5 != null) {
            json1.addProperty("title", this.field5);
         }

         if (this.field6 != null) {
            json1.addProperty("privacy", this.field6);
         }

         byte[] items3 = json1.toString().getBytes(StandardCharsets.UTF_8);
         ByteArrayInputStreamLoader bytearrayinputstreamloader4 = new ByteArrayInputStreamLoader(items3);
         bytearrayinputstreamloader4.method1(arg2x -> {
            int number4x = (int)((float)arg2x / items3.length * 100.0F);
            if (this.field4 != null) {
               this.field4.accept(number4x);
            }
         });
         HttpRequest httprequest5 = HttpRequest.newBuilder()
            .uri(URI.create(ServiceEndpoints.method7() + "/game/screenshot/upload"))
            .POST(BodyPublishers.ofInputStream(() -> bytearrayinputstreamloader4))
            .header("Content-Type", "application/json")
            .header("Authorization", this.field2)
            .build();
         HttpResponse httpresponse6 = field1.send(httprequest5, BodyHandlers.ofString());
         JsonObject json7 = JsonParser.parseString((String)httpresponse6.body()).getAsJsonObject();
         NotificationManager fogimpl8 = Ref.method4().method69();
         if (httpresponse6.statusCode() == 200) {
            return json7.get("link").getAsString();
         } else if (httpresponse6.statusCode() == 403) {
            String text9 = json7.get("code").getAsString();
            fogimpl8.method6(NotificationType.ERROR, "Error while uploading screenshot", text9);
            throw new RuntimeException("Error code " + text9 + " from API.");
         } else {
            fogimpl8.method6(NotificationType.ERROR, "Error while uploading screenshot", "HTTP Status " + httpresponse6.statusCode());
            throw new RuntimeException("HTTP status " + httpresponse6.statusCode() + " from API.");
         }
      } catch (IOException | InterruptedException exception10) {
         throw new RuntimeException("Failed to upload screenshot to Lunar Client backend", exception10);
      }
   }

   @Generated
   public ScreenshotCallable(Path path1, String text2, @Nullable UUID uuid3, IntConsumer intconsumer4, String text5, String text6) {
      this.path = path1;
      this.field2 = text2;
      this.field3 = uuid3;
      this.field4 = intconsumer4;
      this.field5 = text5;
      this.field6 = text6;
   }
}
