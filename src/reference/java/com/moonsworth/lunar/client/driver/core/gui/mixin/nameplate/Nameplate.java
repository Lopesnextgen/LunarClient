package com.moonsworth.lunar.client.driver.core.gui.mixin.nameplate;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lunarclient.websocket.conversation.v1.ConversationImage;
import com.lunarclient.websocket.conversation.v1.ConversationReference;
import com.lunarclient.websocket.conversation.v1.GetUploadUrlsRequest;
import com.lunarclient.websocket.conversation.v1.GetUploadUrlsResponse;
import com.lunarclient.websocket.conversation.v1.PresignedUpload;
import com.lunarclient.websocket.conversation.v1.UpdateConversationIconRequest;
import com.lunarclient.websocket.conversation.v1.UploadRequestItem;
import com.lunarclient.websocket.conversation.v1.UploadUsage;
import com.lunarclient.websocket.conversation.v1.GetUploadUrlsRequest.Builder;
import com.lunarclient.websocket.conversation.v1.GetUploadUrlsResponse.Status;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui4;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump37;
import com.moonsworth.lunar.client.util.ThreadModuleDump55;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import lombok.Generated;

public final class Nameplate {
   private static final long field1 = 5242880L;
   private static final int field2 = 10;
   private static final ConcurrentHashMap<UUID, Nameplate.Data7> field3 = new ConcurrentHashMap<>();
   private static final ConcurrentHashMap<UUID, List<Nameplate.Data7>> field4 = new ConcurrentHashMap<>();
   private static final String field5 = "conversation:iconPicked";
   private static final String field6 = "conversation:iconUpload";
   private static final String field7 = "conversation:chatImagesPicked";
   private static final String field8 = "conversation:chatImagesUploaded";

   public static void method1(UUID var0, ConversationReference var1) {
      if (var1 == null) {
         method17(var0, "conversation:iconPicked", method15("INVALID_CONVERSATION"));
      } else {
         ThreadModuleDump37.method4(() -> method2(var0));
      }
   }

   private static void method2(UUID var0) {
      File var1 = Gui4.method7("Select Group Icon", null, "Image Files", "png", "jpg", "jpeg", "gif", "webp");
      if (var1 == null) {
         method13(var0);
         method17(var0, "conversation:iconPicked", method16());
      } else {
         Nameplate.Data7 var2 = method10(var1, var0, "conversation:iconPicked");
         if (var2 == null) {
            method13(var0);
         } else {
            field3.put(var0, var2);
            JsonObject var3 = new JsonObject();
            var3.addProperty("success", true);
            var3.addProperty("dataUrl", method11(var2));
            method17(var0, "conversation:iconPicked", var3);
         }
      }
   }

   public static void method3(UUID var0, ConversationReference var1) {
      if (var1 == null) {
         method17(var0, "conversation:iconUpload", method15("INVALID_CONVERSATION"));
      } else {
         Nameplate.Data7 var2 = field3.get(var0);
         if (var2 == null) {
            method17(var0, "conversation:iconUpload", method15("NO_PENDING_ICON"));
         } else {
            ThreadModuleDump37.method4(() -> method4(var0, var1, var2));
         }
      }
   }

   private static void method4(UUID var0, ConversationReference var1, Nameplate.Data7 var2) {
      GetUploadUrlsRequest var3 = GetUploadUrlsRequest.newBuilder()
         .setConversationReference(var1)
         .setUsage(UploadUsage.UPLOAD_USAGE_CONVERSATION_ICON)
         .addItems(UploadRequestItem.newBuilder().setContentType(var2.field2).setExpectedSizeBytes(var2.field1.length).build())
         .build();
      GetUploadUrlsResponse var4 = method12(var3, var0, "conversation:iconUpload");
      if (var4 != null) {
         if (var4.getStatus() == Status.STATUS_OK && var4.getUploadsCount() != 0) {
            PresignedUpload var5 = var4.getUploads(0);

            try {
               if (!method9(var5, var2)) {
                  method17(var0, "conversation:iconUpload", method15("FILE_UPLOAD_FAILED"));
                  return;
               }
            } catch (Exception var9) {
               Slayer.method4("Conversation", "Group icon upload failed: " + var9.getMessage());
               method17(var0, "conversation:iconUpload", method15("FILE_UPLOAD_FAILED"));
               return;
            }

            method13(var0);
            String var6 = var5.getFinishedUrl();
            UpdateConversationIconRequest var7 = UpdateConversationIconRequest.newBuilder()
               .setConversationReference(var1)
               .setImage(ConversationImage.newBuilder().setUrl(var6).build())
               .build();
            ThreadModuleDump63.method4().method52().method36(var7, var0x -> {});
            JsonObject var8 = new JsonObject();
            var8.addProperty("success", true);
            var8.addProperty("iconUrl", var6);
            method17(var0, "conversation:iconUpload", var8);
         } else {
            method17(var0, "conversation:iconUpload", method15("UPLOAD_URLS_FAILED"));
         }
      }
   }

   public static void method5(UUID var0, ConversationReference var1) {
      if (var1 == null) {
         method17(var0, "conversation:chatImagesPicked", method15("INVALID_CONVERSATION"));
      } else {
         ThreadModuleDump37.method4(() -> method6(var0));
      }
   }

   private static void method6(UUID var0) {
      File[] var1 = Gui4.method8("Select Images", null, "Image Files", "png", "jpg", "jpeg", "gif", "webp");
      if (var1 != null && var1.length != 0) {
         ArrayList var2 = new ArrayList();
         JsonArray var3 = new JsonArray();

         for (File var7 : var1) {
            Nameplate.Data7 var8 = method10(var7, var0, "conversation:chatImagesPicked");
            if (var8 == null) {
               method14(var0);
               return;
            }

            var2.add(var8);
            var3.add(method11(var8));
         }

         field4.put(var0, var2);
         JsonObject var9 = new JsonObject();
         var9.addProperty("success", true);
         var9.add("dataUrls", var3);
         method17(var0, "conversation:chatImagesPicked", var9);
      } else {
         method14(var0);
         method17(var0, "conversation:chatImagesPicked", method16());
      }
   }

   public static void method7(UUID var0, ConversationReference var1) {
      if (var1 == null) {
         method17(var0, "conversation:chatImagesUploaded", method15("INVALID_CONVERSATION"));
      } else {
         List var2 = field4.get(var0);
         if (var2 != null && !var2.isEmpty()) {
            ThreadModuleDump37.method4(() -> {
               try {
                  method8(var0, var1, var2);
               } catch (Exception var4) {
                  Slayer.method4("Conversation", "Failed to upload pending chat images: " + var4.getMessage());
                  method17(var0, "conversation:chatImagesUploaded", method15("UNEXPECTED_ERROR"));
               }
            });
         } else {
            method17(var0, "conversation:chatImagesUploaded", method15("NO_PENDING_IMAGES"));
         }
      }
   }

   private static void method8(UUID var0, ConversationReference var1, List<Nameplate.Data7> var2) {
      Builder var3 = GetUploadUrlsRequest.newBuilder().setConversationReference(var1).setUsage(UploadUsage.UPLOAD_USAGE_CHAT);

      for (Nameplate.Data7 var5 : var2) {
         var3.addItems(UploadRequestItem.newBuilder().setContentType(var5.field2).setExpectedSizeBytes(var5.field1.length).build());
      }

      GetUploadUrlsResponse var11 = method12(var3.build(), var0, "conversation:chatImagesUploaded");
      if (var11 != null) {
         if (var11.getStatus() == Status.STATUS_OK && var11.getUploadsCount() >= var2.size()) {
            JsonArray var12 = new JsonArray();

            for (int var6 = 0; var6 < var2.size(); var6++) {
               PresignedUpload var7 = var11.getUploads(var6);
               Nameplate.Data7 var8 = (Nameplate.Data7)var2.get(var6);

               try {
                  if (!method9(var7, var8)) {
                     method17(var0, "conversation:chatImagesUploaded", method15("FILE_UPLOAD_FAILED"));
                     return;
                  }
               } catch (Exception var10) {
                  Slayer.method4("Conversation", "Failed to upload pending chat images: " + var10.getMessage());
                  method17(var0, "conversation:chatImagesUploaded", method15("FILE_UPLOAD_FAILED"));
                  return;
               }

               var12.add(var7.getFinishedUrl());
            }

            method14(var0);
            JsonObject var13 = new JsonObject();
            var13.addProperty("success", true);
            var13.add("cdnUrls", var12);
            method17(var0, "conversation:chatImagesUploaded", var13);
         } else {
            method17(var0, "conversation:chatImagesUploaded", method15("UPLOAD_URLS_FAILED"));
         }
      }
   }

   private static boolean method9(PresignedUpload var0, Nameplate.Data7 var1) {
      byte[] var2 = var1.field1;
      String var3 = var1.field2;
      HttpURLConnection var4 = (HttpURLConnection)new URL(var0.getUploadUrl()).openConnection();
      var4.setRequestMethod("PUT");
      var4.setDoOutput(true);
      var4.setRequestProperty("Content-Type", var3);
      var4.setRequestProperty("Content-Length", String.valueOf(var2.length));

      for (Entry var6 : var0.getRequiredHeadersMap().entrySet()) {
         var4.setRequestProperty((String)var6.getKey(), (String)var6.getValue());
      }

      try (OutputStream var10 = var4.getOutputStream()) {
         var10.write(var2);
      }

      int var11 = var4.getResponseCode();
      var4.disconnect();
      return var11 >= 200 && var11 < 300;
   }

   private static Nameplate.Data7 method10(File var0, UUID var1, String var2) {
      if (var0.length() > 5242880L) {
         method17(var1, var2, method15("FILE_TOO_LARGE"));
         return null;
      }

      String var3 = ThreadModuleDump55.method2(var0.getName());
      if (var3 == null) {
         method17(var1, var2, method15("UNSUPPORTED_FILE_TYPE"));
         return null;
      }

      try {
         byte[] var4 = Files.readAllBytes(var0.toPath());
         return new Nameplate.Data7(var4, var3);
      } catch (IOException var5) {
         method17(var1, var2, method15("FILE_READ_FAILED"));
         return null;
      }
   }

   private static String method11(Nameplate.Data7 var0) {
      return "data:" + var0.field2 + ";base64," + Base64.getEncoder().encodeToString(var0.field1);
   }

   private static GetUploadUrlsResponse method12(GetUploadUrlsRequest var0, UUID var1, String var2) {
      CompletableFuture var3 = new CompletableFuture();
      ThreadModuleDump63.method4().method52().method44(var0, var3::complete);

      try {
         return (GetUploadUrlsResponse)var3.get(10L, TimeUnit.SECONDS);
      } catch (TimeoutException var5) {
         Slayer.method4("Conversation", "Timed out waiting for upload URLs: " + var5.getMessage());
         method17(var1, var2, method15("UPLOAD_URLS_TIMEOUT"));
         return null;
      } catch (InterruptedException var6) {
         Thread.currentThread().interrupt();
         Slayer.method4("Conversation", "Interrupted while waiting for upload URLs: " + var6.getMessage());
         method17(var1, var2, method15("UPLOAD_URLS_FAILED"));
         return null;
      } catch (Exception var7) {
         Slayer.method4("Conversation", "Failed waiting for upload URLs: " + var7.getMessage());
         method17(var1, var2, method15("UPLOAD_URLS_FAILED"));
         return null;
      }
   }

   private static void method13(UUID var0) {
      field3.remove(var0);
   }

   private static void method14(UUID var0) {
      field4.remove(var0);
   }

   private static JsonObject method15(String var0) {
      JsonObject var1 = new JsonObject();
      var1.addProperty("success", false);
      var1.addProperty("error", var0);
      return var1;
   }

   private static JsonObject method16() {
      JsonObject var0 = new JsonObject();
      var0.addProperty("success", false);
      var0.addProperty("cancelled", true);
      return var0;
   }

   private static void method17(UUID var0, String var1, JsonObject var2) {
      var2.addProperty("conversationId", var0.toString());
      DriverViewportLegacy.method50().method23(DriverViewportLegacy.method50().method55().method13(), var1, var2);
   }

   @Generated
   private Nameplate() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   private class Data7 {
      private final byte[] field1;
      private final String field2;

      private Data7(byte[] var1, String var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      public byte[] bytes() {
         return this.field1;
      }

      public String method1() {
         return this.field2;
      }
   }
}
