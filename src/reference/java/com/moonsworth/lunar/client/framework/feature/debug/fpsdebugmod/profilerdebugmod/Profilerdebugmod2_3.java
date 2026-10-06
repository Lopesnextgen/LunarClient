package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.profilerdebugmod;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
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

public class Profilerdebugmod2_3 {
   private static final String field1 = "http://soopy.dev:25561/";
   private static final long field2 = 524288L;
   private static final int field3 = 8192;

   public static boolean shouldProfile() {
      return false;
   }

   public static void method1(byte[] var0) {
      String var1 = LunarBuildData.field1;
      String var2 = LunarBuildData.field3;
      String var3 = Bridge.getMinecraftVersion().getDisplayName();
      String var4 = ThreadModuleDump63.method4().method31().method10().toString();
      String var5 = "branch="
         + URLEncoder.encode(var1, StandardCharsets.UTF_8)
         + "&hash="
         + URLEncoder.encode(var2, StandardCharsets.UTF_8)
         + "&mcVersion="
         + URLEncoder.encode(var3, StandardCharsets.UTF_8)
         + "&uuid="
         + URLEncoder.encode(var4, StandardCharsets.UTF_8);
      HttpRequest var6 = HttpRequest.newBuilder()
         .uri(URI.create("http://soopy.dev:25561/uploadProfile?" + var5))
         .header("Content-Type", "application/octet-stream")
         .POST(method2(var0, 524288L, 8192))
         .build();
      HttpResponse var7 = HttpClient.newHttpClient().send(var6, BodyHandlers.ofString());
   }

   private static BodyPublisher method2(byte[] var0, long var1, int var3) {
      Publisher var4 = var4x -> var4x.onSubscribe(new Profilerdebugmod2$Data(var4x, var0, var1, var3));
      return BodyPublishers.fromPublisher(var4, var0.length);
   }
}
