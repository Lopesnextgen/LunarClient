package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.profilerdebugmod;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.framework.Ref;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Flow.Publisher;

public class ProfileUploader {
   private static final String field1 = "http://soopy.dev:25561/";
   private static final long field2 = 524288L;
   private static final int field3 = 8192;

   public ProfileUploader() {
   }

   public static boolean shouldProfile() {
      return false;
   }

   public static void method1(byte[] items0) {
      String text1 = LunarBuildData.field1;
      String text2 = LunarBuildData.field3;
      String text3 = Bridge.getMinecraftVersion().getDisplayName();
      String text4 = Ref.method4().method31().method10().toString();
      String text5 = "branch="
         + URLEncoder.encode(text1, StandardCharsets.UTF_8)
         + "&hash="
         + URLEncoder.encode(text2, StandardCharsets.UTF_8)
         + "&mcVersion="
         + URLEncoder.encode(text3, StandardCharsets.UTF_8)
         + "&uuid="
         + URLEncoder.encode(text4, StandardCharsets.UTF_8);
      HttpRequest httprequest6 = HttpRequest.newBuilder()
         .uri(URI.create("http://soopy.dev:25561/uploadProfile?" + text5))
         .header("Content-Type", "application/octet-stream")
         .POST(method2(items0, 524288L, 8192))
         .build();
      HttpResponse httpresponse7 = HttpClient.newHttpClient().send(httprequest6, BodyHandlers.ofString());
   }

   private static BodyPublisher method2(byte[] items0, long number1, int number3) {
      Publisher publisher4 = arg4x -> arg4x.onSubscribe(new ProfileUploadThrottle(arg4x, items0, number1, number3));
      return BodyPublishers.fromPublisher(publisher4, items0.length);
   }
}
