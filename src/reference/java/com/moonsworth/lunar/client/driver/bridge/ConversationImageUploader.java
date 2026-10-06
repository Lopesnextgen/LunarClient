package com.moonsworth.lunar.client.driver.bridge;

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
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui4;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.util.concurrent.BackgroundExecutor;
import com.moonsworth.lunar.client.util.io.MimeTypeUtils;
import com.moonsworth.lunar.client.framework.Ref;
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

public final class ConversationImageUploader {
   private static final long field1 = 5242880L;
   private static final int field2 = 10;
   private static final ConcurrentHashMap<UUID, ConversationImageUploader.PendingImage> field3 = new ConcurrentHashMap<>();
   private static final ConcurrentHashMap<UUID, List<ConversationImageUploader.PendingImage>> field4 = new ConcurrentHashMap<>();
   private static final String field5 = "conversation:iconPicked";
   private static final String field6 = "conversation:iconUpload";
   private static final String field7 = "conversation:chatImagesPicked";
   private static final String field8 = "conversation:chatImagesUploaded";

   public static void method1(UUID uuid0, ConversationReference conversationreference1) {
      if (conversationreference1 == null) {
         method17(uuid0, "conversation:iconPicked", method15("INVALID_CONVERSATION"));
      } else {
         BackgroundExecutor.method4(() -> method2(uuid0));
      }
   }

   private static void method2(UUID uuid0) {
      File file1 = Gui4.method7("Select Group Icon", null, "Image Files", new String[]{"png", "jpg", "jpeg", "gif", "webp"});
      if (file1 == null) {
         method13(uuid0);
         method17(uuid0, "conversation:iconPicked", method16());
      } else {
         ConversationImageUploader.PendingImage data72 = method10(file1, uuid0, "conversation:iconPicked");
         if (data72 == null) {
            method13(uuid0);
         } else {
            field3.put(uuid0, data72);
            JsonObject json3 = new JsonObject();
            json3.addProperty("success", true);
            json3.addProperty("dataUrl", method11(data72));
            method17(uuid0, "conversation:iconPicked", json3);
         }
      }
   }

   public static void method3(UUID uuid0, ConversationReference conversationreference1) {
      if (conversationreference1 == null) {
         method17(uuid0, "conversation:iconUpload", method15("INVALID_CONVERSATION"));
      } else {
         ConversationImageUploader.PendingImage data72 = field3.get(uuid0);
         if (data72 == null) {
            method17(uuid0, "conversation:iconUpload", method15("NO_PENDING_ICON"));
         } else {
            BackgroundExecutor.method4(() -> method4(uuid0, conversationreference1, data72));
         }
      }
   }

   private static void method4(UUID uuid0, ConversationReference conversationreference1, ConversationImageUploader.PendingImage data72) {
      GetUploadUrlsRequest getuploadurlsrequest3 = GetUploadUrlsRequest.newBuilder()
         .setConversationReference(conversationreference1)
         .setUsage(UploadUsage.UPLOAD_USAGE_CONVERSATION_ICON)
         .addItems(UploadRequestItem.newBuilder().setContentType(data72.field2).setExpectedSizeBytes(data72.field1.length).build())
         .build();
      GetUploadUrlsResponse getuploadurlsresponse4 = method12(getuploadurlsrequest3, uuid0, "conversation:iconUpload");
      if (getuploadurlsresponse4 != null) {
         if (getuploadurlsresponse4.getStatus() == Status.STATUS_OK && getuploadurlsresponse4.getUploadsCount() != 0) {
            PresignedUpload presignedupload5 = getuploadurlsresponse4.getUploads(0);

            try {
               if (!method9(presignedupload5, data72)) {
                  method17(uuid0, "conversation:iconUpload", method15("FILE_UPLOAD_FAILED"));
                  return;
               }
            } catch (Exception exception9) {
               LunarLogger.method4("Conversation", "Group icon upload failed: " + exception9.getMessage(), new Object[0]);
               method17(uuid0, "conversation:iconUpload", method15("FILE_UPLOAD_FAILED"));
               return;
            }

            method13(uuid0);
            String text6 = presignedupload5.getFinishedUrl();
            UpdateConversationIconRequest updateconversationiconrequest7 = UpdateConversationIconRequest.newBuilder()
               .setConversationReference(conversationreference1)
               .setImage(ConversationImage.newBuilder().setUrl(text6).build())
               .build();
            Ref.method4().method52().method36(updateconversationiconrequest7, arg0x -> {});
            JsonObject json8 = new JsonObject();
            json8.addProperty("success", true);
            json8.addProperty("iconUrl", text6);
            method17(uuid0, "conversation:iconUpload", json8);
         } else {
            method17(uuid0, "conversation:iconUpload", method15("UPLOAD_URLS_FAILED"));
         }
      }
   }

   public static void method5(UUID uuid0, ConversationReference conversationreference1) {
      if (conversationreference1 == null) {
         method17(uuid0, "conversation:chatImagesPicked", method15("INVALID_CONVERSATION"));
      } else {
         BackgroundExecutor.method4(() -> method6(uuid0));
      }
   }

   private static void method6(UUID uuid0) {
      File[] items1 = Gui4.method8("Select Images", null, "Image Files", new String[]{"png", "jpg", "jpeg", "gif", "webp"});
      if (items1 != null && items1.length != 0) {
         ArrayList list2 = new ArrayList();
         JsonArray array3 = new JsonArray();

         for (File file7 : items1) {
            ConversationImageUploader.PendingImage data78 = method10(file7, uuid0, "conversation:chatImagesPicked");
            if (data78 == null) {
               method14(uuid0);
               return;
            }

            list2.add(data78);
            array3.add(method11(data78));
         }

         field4.put(uuid0, list2);
         JsonObject json9 = new JsonObject();
         json9.addProperty("success", true);
         json9.add("dataUrls", array3);
         method17(uuid0, "conversation:chatImagesPicked", json9);
      } else {
         method14(uuid0);
         method17(uuid0, "conversation:chatImagesPicked", method16());
      }
   }

   public static void method7(UUID uuid0, ConversationReference conversationreference1) {
      if (conversationreference1 == null) {
         method17(uuid0, "conversation:chatImagesUploaded", method15("INVALID_CONVERSATION"));
      } else {
         List list2 = field4.get(uuid0);
         if (list2 != null && !list2.isEmpty()) {
            BackgroundExecutor.method4(() -> {
               try {
                  method8(uuid0, conversationreference1, list2);
               } catch (Exception exception4) {
                  LunarLogger.method4("Conversation", "Failed to upload pending chat images: " + exception4.getMessage(), new Object[0]);
                  method17(uuid0, "conversation:chatImagesUploaded", method15("UNEXPECTED_ERROR"));
               }
            });
         } else {
            method17(uuid0, "conversation:chatImagesUploaded", method15("NO_PENDING_IMAGES"));
         }
      }
   }

   private static void method8(UUID uuid0, ConversationReference conversationreference1, List<ConversationImageUploader.PendingImage> list2) {
      Builder builder3 = GetUploadUrlsRequest.newBuilder().setConversationReference(conversationreference1).setUsage(UploadUsage.UPLOAD_USAGE_CHAT);

      for (ConversationImageUploader.PendingImage data75 : list2) {
         builder3.addItems(UploadRequestItem.newBuilder().setContentType(data75.field2).setExpectedSizeBytes(data75.field1.length).build());
      }

      GetUploadUrlsResponse getuploadurlsresponse11 = method12(builder3.build(), uuid0, "conversation:chatImagesUploaded");
      if (getuploadurlsresponse11 != null) {
         if (getuploadurlsresponse11.getStatus() == Status.STATUS_OK && getuploadurlsresponse11.getUploadsCount() >= list2.size()) {
            JsonArray array12 = new JsonArray();

            for (int index6 = 0; index6 < list2.size(); index6++) {
               PresignedUpload presignedupload7 = getuploadurlsresponse11.getUploads(index6);
               ConversationImageUploader.PendingImage data78 = (ConversationImageUploader.PendingImage)list2.get(index6);

               try {
                  if (!method9(presignedupload7, data78)) {
                     method17(uuid0, "conversation:chatImagesUploaded", method15("FILE_UPLOAD_FAILED"));
                     return;
                  }
               } catch (Exception exception10) {
                  LunarLogger.method4("Conversation", "Failed to upload pending chat images: " + exception10.getMessage(), new Object[0]);
                  method17(uuid0, "conversation:chatImagesUploaded", method15("FILE_UPLOAD_FAILED"));
                  return;
               }

               array12.add(presignedupload7.getFinishedUrl());
            }

            method14(uuid0);
            JsonObject json13 = new JsonObject();
            json13.addProperty("success", true);
            json13.add("cdnUrls", array12);
            method17(uuid0, "conversation:chatImagesUploaded", json13);
         } else {
            method17(uuid0, "conversation:chatImagesUploaded", method15("UPLOAD_URLS_FAILED"));
         }
      }
   }

   private static boolean method9(PresignedUpload presignedupload0, ConversationImageUploader.PendingImage data71) {
      byte[] items2 = data71.field1;
      String text3 = data71.field2;
      HttpURLConnection httpurlconnection4 = (HttpURLConnection)new URL(presignedupload0.getUploadUrl()).openConnection();
      httpurlconnection4.setRequestMethod("PUT");
      httpurlconnection4.setDoOutput(true);
      httpurlconnection4.setRequestProperty("Content-Type", text3);
      httpurlconnection4.setRequestProperty("Content-Length", String.valueOf(items2.length));

      for (Entry entry6 : presignedupload0.getRequiredHeadersMap().entrySet()) {
         httpurlconnection4.setRequestProperty((String)entry6.getKey(), (String)entry6.getValue());
      }

      try (OutputStream output10 = httpurlconnection4.getOutputStream()) {
         output10.write(items2);
      }

      int number11 = httpurlconnection4.getResponseCode();
      httpurlconnection4.disconnect();
      return number11 >= 200 && number11 < 300;
   }

   private static ConversationImageUploader.PendingImage method10(File file0, UUID uuid1, String text2) {
      if (file0.length() > 5242880L) {
         method17(uuid1, text2, method15("FILE_TOO_LARGE"));
         return null;
      }

      String text3 = MimeTypeUtils.method2(file0.getName());
      if (text3 == null) {
         method17(uuid1, text2, method15("UNSUPPORTED_FILE_TYPE"));
         return null;
      }

      try {
         byte[] items4 = Files.readAllBytes(file0.toPath());
         return new ConversationImageUploader.PendingImage(items4, text3);
      } catch (IOException exception5) {
         method17(uuid1, text2, method15("FILE_READ_FAILED"));
         return null;
      }
   }

   private static String method11(ConversationImageUploader.PendingImage data70) {
      return "data:" + data70.field2 + ";base64," + Base64.getEncoder().encodeToString(data70.field1);
   }

   private static GetUploadUrlsResponse method12(GetUploadUrlsRequest getuploadurlsrequest0, UUID uuid1, String text2) {
      CompletableFuture completablefuture3 = new CompletableFuture();
      Ref.method4().method52().method44(getuploadurlsrequest0, completablefuture3::complete);

      try {
         return (GetUploadUrlsResponse)completablefuture3.get(10L, TimeUnit.SECONDS);
      } catch (TimeoutException timeoutexception5) {
         LunarLogger.method4("Conversation", "Timed out waiting for upload URLs: " + timeoutexception5.getMessage(), new Object[0]);
         method17(uuid1, text2, method15("UPLOAD_URLS_TIMEOUT"));
         return null;
      } catch (InterruptedException interruptedexception6) {
         Thread.currentThread().interrupt();
         LunarLogger.method4("Conversation", "Interrupted while waiting for upload URLs: " + interruptedexception6.getMessage(), new Object[0]);
         method17(uuid1, text2, method15("UPLOAD_URLS_FAILED"));
         return null;
      } catch (Exception exception7) {
         LunarLogger.method4("Conversation", "Failed waiting for upload URLs: " + exception7.getMessage(), new Object[0]);
         method17(uuid1, text2, method15("UPLOAD_URLS_FAILED"));
         return null;
      }
   }

   private static void method13(UUID uuid0) {
      field3.remove(uuid0);
   }

   private static void method14(UUID uuid0) {
      field4.remove(uuid0);
   }

   private static JsonObject method15(String text0) {
      JsonObject json1 = new JsonObject();
      json1.addProperty("success", false);
      json1.addProperty("error", text0);
      return json1;
   }

   private static JsonObject method16() {
      JsonObject json0 = new JsonObject();
      json0.addProperty("success", false);
      json0.addProperty("cancelled", true);
      return json0;
   }

   private static void method17(UUID uuid0, String text1, JsonObject json2) {
      json2.addProperty("conversationId", uuid0.toString());
      DriverViewportLegacy.method50().method23(DriverViewportLegacy.method50().method55().method13(), text1, json2);
   }

   @Generated
   private ConversationImageUploader() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   private class PendingImage {
      private final byte[] field1;
      private final String field2;

      private PendingImage(byte[] items1, String text2) {
         this.field1 = items1;
         this.field2 = text2;
      }

      public byte[] bytes() {
         return this.field1;
      }

      public String method1() {
         return this.field2;
      }
   }
}
