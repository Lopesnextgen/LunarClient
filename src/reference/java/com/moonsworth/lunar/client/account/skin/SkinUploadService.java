package com.moonsworth.lunar.client.account.skin;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.account.AccountSession;
import com.moonsworth.lunar.client.account.AccountManager;
import com.moonsworth.lunar.client.util.net.ThreadModuleDump74;
import com.moonsworth.lunar.client.util.net.ThreadModuleDump74.Data;
import com.moonsworth.lunar.files.Files3_2;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Map;
import java.util.function.Consumer;
import com.moonsworth.lunar.client.account.skin.SavedSkin;
import com.moonsworth.lunar.client.account.skin.SkinType;

public class SkinUploadService {
   private static final URI field1;

   public static void method1(SavedSkin var0, boolean var1, boolean var2, Runnable var3, Consumer<String> var4) {
      method2(var0.method2(), var0.getUrl(), var1, var2, var3, var4);
   }

   public static void method2(SkinType var0, String var1, boolean var2, boolean var3, Runnable var4, Consumer<String> var5) {
      AccountManager var6 = Client.method109().method43();
      AccountSession var7 = var6.method10();
      if (var7 == null) {
         System.out.println("Cannot change skin: no account selected.");
      } else {
         String var8 = var7.getAccessToken();
         if (var8 != null && var7.method3()) {
            method4(var8, var0, var1, var2, var3, var4, var5);
         } else {
            System.out.println("Cannot change skin: access token is not valid.");
         }
      }
   }

   public static void method3(SkinType var0, byte[] var1, boolean var2, Consumer<JsonObject> var3, Consumer<String> var4) {
      AccountManager var5 = Client.method109().method43();
      AccountSession var6 = var5.method10();
      if (var6 == null) {
         System.out.println("Cannot change skin: no account selected.");
      } else {
         String var7 = var6.getAccessToken();
         if (var7 != null && var6.method3()) {
            method5(var7, var0, var1, var2, var3, var4);
         } else {
            System.out.println("Cannot change skin: access token is not valid.");
         }
      }
   }

   public static void method4(String var0, SkinType var1, String var2, boolean var3, boolean var4, Runnable var5, Consumer<String> var6) {
      try {
         Builder var7 = HttpRequest.newBuilder();
         var7.uri(field1);
         var7.header("Authorization", "Bearer " + var0);
         if (var4) {
            byte[] var8 = Files3_2.method3(var2);
            if (var8 == null) {
               var6.accept("Invalid skin URL. Please provide a direct link to the skin's image.");
               return;
            }

            String var9 = ThreadModuleDump74.randomBoundary();
            var7.header("Content-Type", "multipart/form-data; boundary=" + var9 + "; charset=utf-8");
            var7.POST(ThreadModuleDump74.multipartBody(var9, Map.of("variant", var1.getName(), "file", new Data("skin.png", "image/png", var8))));
         } else {
            var7.header("Content-Type", "application/json; charset=utf-8");
            var7.POST(ThreadModuleDump74.jsonBody(Map.of("variant", var1.getName(), "url", var2)));
         }

         HttpRequest var12 = var7.build();
         HttpClient var13 = HttpClient.newBuilder().build();
         Consumer var10 = var2x -> {
            String var3x = (String)var2x.body();
            if (var2x.statusCode() != 200) {
               if (var2x.statusCode() == 429) {
                  var6.accept("Rate limited by Mojang. Please try again later.");
               } else {
                  try {
                     JsonElement var8x = new JsonParser().parse(new StringReader((String)var2x.body()));
                     String var10x = var8x.getAsJsonObject().get("errorMessage").getAsString();
                     if (var10x.equals("Could not read image data.") || var10x.equals("Invalid skin image")) {
                        var6.accept("Invalid skin URL. Please provide a direct link to the skin's image.");
                        return;
                     }
                  } catch (Exception var7x) {
                     var7x.printStackTrace();
                  }

                  String var9x = "";
                  if (!var3x.isEmpty()) {
                     var9x = " (body: " + var3x + ")";
                  }

                  var6.accept("Non-200 status code " + var2x.statusCode() + " received from server" + var9x);
               }
            } else {
               if (!var3x.isEmpty()) {
                  try {
                     JsonElement var4x = new JsonParser().parse(new StringReader((String)var2x.body()));
                     if (var4x.isJsonObject()) {
                        JsonElement var5x = var4x.getAsJsonObject().get("message");
                        if (var5x != null && var5x.isJsonPrimitive()) {
                           var6.accept(var5x.getAsString());
                           return;
                        }
                     }
                  } catch (Exception var6x) {
                     var6.accept("Unexpected exception: " + var6x.getMessage());
                     return;
                  }
               }

               var5.run();
            }
         };
         if (var3) {
            var13.sendAsync(var12, BodyHandlers.ofString()).thenAccept(var10);
         } else {
            var10.accept(var13.send(var12, BodyHandlers.ofString()));
         }
      } catch (Exception var11) {
         var11.printStackTrace();
         var6.accept("Unexpected error: " + var11.getMessage());
      }
   }

   public static void method5(String var0, SkinType var1, byte[] var2, boolean var3, Consumer<JsonObject> var4, Consumer<String> var5) {
      try {
         Builder var6 = HttpRequest.newBuilder();
         var6.uri(field1);
         var6.header("Authorization", "Bearer " + var0);
         if (var2 == null) {
            var5.accept("Invalid skin URL. Please provide a direct link to the skin's image.");
            return;
         }

         String var7 = ThreadModuleDump74.randomBoundary();
         var6.header("Content-Type", "multipart/form-data; boundary=" + var7 + "; charset=utf-8");
         var6.POST(ThreadModuleDump74.multipartBody(var7, Map.of("variant", var1.getName(), "file", new Data("skin.png", "image/png", var2))));
         HttpRequest var8 = var6.build();
         HttpClient var9 = HttpClient.newBuilder().build();
         Consumer var10 = var2x -> {
            String var3x = (String)var2x.body();
            if (var2x.statusCode() != 200) {
               if (var2x.statusCode() == 429) {
                  var5.accept("Rate limited by Mojang. Please try again later.");
               } else {
                  try {
                     JsonElement var8x = new JsonParser().parse(new StringReader((String)var2x.body()));
                     String var10x = var8x.getAsJsonObject().get("errorMessage").getAsString();
                     if (var10x.equals("Could not read image data.") || var10x.equals("Invalid skin image")) {
                        var5.accept("Invalid skin URL. Please provide a direct link to the skin's image.");
                        return;
                     }
                  } catch (Exception var7x) {
                     var7x.printStackTrace();
                  }

                  String var9x = "";
                  if (!var3x.isEmpty()) {
                     var9x = " (body: " + var3x + ")";
                  }

                  var5.accept("Non-200 status code " + var2x.statusCode() + " received from server" + var9x);
               }
            } else {
               if (!var3x.isEmpty()) {
                  try {
                     JsonElement var4x = new JsonParser().parse(new StringReader((String)var2x.body()));
                     if (var4x.isJsonObject()) {
                        JsonElement var5x = var4x.getAsJsonObject().get("message");
                        if (var5x != null && var5x.isJsonPrimitive()) {
                           var5.accept(var5x.getAsString());
                           return;
                        }

                        var4.accept(var4x.getAsJsonObject());
                     }
                  } catch (Exception var6x) {
                     var5.accept("Unexpected exception: " + var6x.getMessage());
                  }
               }
            }
         };
         if (var3) {
            var9.sendAsync(var8, BodyHandlers.ofString()).thenAccept(var10);
         } else {
            var10.accept(var9.send(var8, BodyHandlers.ofString()));
         }
      } catch (Exception var11) {
         var11.printStackTrace();
         var5.accept("Unexpected error: " + var11.getMessage());
      }
   }

   static {
      try {
         field1 = new URI("https://api.minecraftservices.com/minecraft/profile/skins");
      } catch (URISyntaxException var1) {
         throw new AssertionError(var1);
      }
   }
}
