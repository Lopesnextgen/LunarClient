package com.moonsworth.lunar.client.driver.core.gui.mixin.nameplate;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import com.lunarclient.common.v1.UuidAndUsername;
import com.lunarclient.websocket.conversation.v1.AddConversationParticipantsRequest;
import com.lunarclient.websocket.conversation.v1.AddPinnedMessageRequest;
import com.lunarclient.websocket.conversation.v1.ConversationImage;
import com.lunarclient.websocket.conversation.v1.ConversationMessageContents;
import com.lunarclient.websocket.conversation.v1.ConversationReference;
import com.lunarclient.websocket.conversation.v1.ConversationType;
import com.lunarclient.websocket.conversation.v1.CreateConversationRequest;
import com.lunarclient.websocket.conversation.v1.DeleteConversationMessageHistoryRequest;
import com.lunarclient.websocket.conversation.v1.DeleteConversationMessageRequest;
import com.lunarclient.websocket.conversation.v1.DeleteConversationRequest;
import com.lunarclient.websocket.conversation.v1.GetConversationsRequest;
import com.lunarclient.websocket.conversation.v1.GetUploadUrlsRequest;
import com.lunarclient.websocket.conversation.v1.IconPolicy;
import com.lunarclient.websocket.conversation.v1.InvitePolicy;
import com.lunarclient.websocket.conversation.v1.LoadConversationRequest;
import com.lunarclient.websocket.conversation.v1.LoadPinnedMessagesRequest;
import com.lunarclient.websocket.conversation.v1.LunarSticker;
import com.lunarclient.websocket.conversation.v1.MessagePinningPolicy;
import com.lunarclient.websocket.conversation.v1.NamePolicy;
import com.lunarclient.websocket.conversation.v1.PreSendAction;
import com.lunarclient.websocket.conversation.v1.PreSendActionRequest;
import com.lunarclient.websocket.conversation.v1.RemoveConversationParticipantRequest;
import com.lunarclient.websocket.conversation.v1.RemovePinnedMessageRequest;
import com.lunarclient.websocket.conversation.v1.SendConversationMessageRequest;
import com.lunarclient.websocket.conversation.v1.SetIconPolicyRequest;
import com.lunarclient.websocket.conversation.v1.SetInvitePolicyRequest;
import com.lunarclient.websocket.conversation.v1.SetMessagePinningPolicyRequest;
import com.lunarclient.websocket.conversation.v1.SetNamePolicyRequest;
import com.lunarclient.websocket.conversation.v1.UnfocusConversationsRequest;
import com.lunarclient.websocket.conversation.v1.UpdateConversationIconRequest;
import com.lunarclient.websocket.conversation.v1.UpdateConversationNameRequest;
import com.lunarclient.websocket.conversation.v1.UpdateConversationOwnerRequest;
import com.lunarclient.websocket.conversation.v1.UploadRequestItem;
import com.lunarclient.websocket.conversation.v1.UploadUsage;
import com.lunarclient.websocket.conversation.v1.GetConversationsRequest.Builder;
import com.lunarclient.websocket.conversation.v1.GetConversationsResponse.Status;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump37;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump66;
import com.moonsworth.webosr.javascript.CallbackJS;
import com.moonsworth.webosr.wrappers.PromiseJS;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.Nullable;

public class GuiExtension implements DriverGuiExtensionLegacy {
   @Override
   public JsonElement provide() {
      return Client.method109().method52().provide();
   }

   @Nullable
   @Override
   public JsonElement method128() {
      return Client.method109().method52().method128();
   }

   @Override
   public boolean method6() {
      return true;
   }

   @CallbackJS("refreshConversations")
   public static void method3() {
      ThreadModuleDump63.method4().method52().method24();
   }

   @CallbackJS("getConversations")
   public static void method4(PromiseJS<Void> var0, long var1, int var3) {
      Builder var4 = GetConversationsRequest.newBuilder();
      if (var1 > 0L) {
         var4.setCursor(ThreadModuleDump66.method14(var1));
      }

      if (var3 > 0) {
         var4.setLimit(var3);
      }

      ThreadModuleDump63.method4().method52().method46(var4.build(), var1x -> {
         if (var1x == null) {
            var0.reject(Status.STATUS_UNSPECIFIED.name());
         } else {
            ThreadModuleDump63.method4().method52().method5(var1x);
            Status var2 = var1x.getStatus();
            if (var2 != Status.STATUS_OK) {
               var0.reject(var2.name());
            } else {
               var0.resolve();
            }
         }
      });
   }

   @CallbackJS("unfocusConversations")
   public static void method5() {
      ThreadModuleDump63.method4().method52().method45(UnfocusConversationsRequest.getDefaultInstance(), var0 -> {});
   }

   @CallbackJS("sendConversationMessage")
   public static void method6(PromiseJS<GuiExtension.Data2> var0, UUID var1, String var2, String var3, List<String> var4, @Nullable String var5) {
      ConversationReference var6 = method34(var1);
      if (var6 == null) {
         var0.reject(com.lunarclient.websocket.conversation.v1.SendConversationMessageResponse.Status.STATUS_UNKNOWN_CONVERSATION.name());
      } else {
         String var7 = var2 == null ? "" : var2.trim();
         if (var7.isEmpty()) {
            var0.reject(com.lunarclient.websocket.conversation.v1.SendConversationMessageResponse.Status.STATUS_UNSPECIFIED.name());
         } else {
            String var8 = var3 == null ? "" : var3.trim();
            String var9 = var5 == null ? "" : var5.trim();
            com.lunarclient.websocket.conversation.v1.ConversationMessageContents.Builder var10 = ConversationMessageContents.newBuilder();
            boolean var11 = false;
            if (!var8.isEmpty()) {
               var10.setPlainText(var8);
               var11 = true;
            }

            if (!var9.isEmpty()) {
               var10.setSticker(LunarSticker.newBuilder().setId(var9).build());
               var11 = true;
            }

            for (String var13 : var4) {
               if (var13 != null && !var13.isBlank()) {
                  var10.addImages(ConversationImage.newBuilder().setUrl(var13.trim()).build());
                  var11 = true;
               }
            }

            if (!var11) {
               var0.reject(com.lunarclient.websocket.conversation.v1.SendConversationMessageResponse.Status.STATUS_UNSPECIFIED.name());
            } else {
               SendConversationMessageRequest var14 = SendConversationMessageRequest.newBuilder()
                  .setConversationReference(var6)
                  .setMessageContents(var10.build())
                  .build();
               ThreadModuleDump63.method4().method52().method25(var14, var3x -> {
                  if (var3x == null) {
                     var0.reject(com.lunarclient.websocket.conversation.v1.SendConversationMessageResponse.Status.STATUS_UNSPECIFIED.name());
                  } else {
                     com.lunarclient.websocket.conversation.v1.SendConversationMessageResponse.Status var4x = var3x.getStatus();
                     if (var4x != com.lunarclient.websocket.conversation.v1.SendConversationMessageResponse.Status.STATUS_OK) {
                        var0.reject(var4x.name());
                     } else {
                        var0.resolve(new GuiExtension.Data2(var1.toString(), var7, var4x.name()));
                     }
                  }
               });
            }
         }
      }
   }

   @CallbackJS("preSendAction")
   public static String method7(UUID var0, PreSendAction var1) {
      ConversationReference var2 = method34(var0);
      if (var2 != null && var1 != null) {
         PreSendActionRequest var3 = PreSendActionRequest.newBuilder().setConversationReference(var2).setAction(var1).build();
         ThreadModuleDump63.method4().method52().method26(var3, var0x -> {});
         return var0.toString();
      } else {
         return "";
      }
   }

   @CallbackJS("loadConversation")
   public static String method8(UUID var0) {
      return method35(var0, null, null, null);
   }

   @CallbackJS("loadConversationBefore")
   public static String method9(UUID var0, UUID var1) {
      return method35(var0, var1, null, null);
   }

   @CallbackJS("loadConversationAfter")
   public static String method10(UUID var0, UUID var1) {
      return method35(var0, null, var1, null);
   }

   @CallbackJS("loadConversationAnchor")
   public static String method11(UUID var0, UUID var1) {
      return method35(var0, null, null, var1);
   }

   @CallbackJS("createConversation")
   public static void method12(PromiseJS<String> var0, ConversationType var1, GuiExtension.Data3[] var2) {
      if (var1 == null || var1 == ConversationType.CONVERSATION_TYPE_UNSPECIFIED) {
         var0.reject(com.lunarclient.websocket.conversation.v1.CreateConversationResponse.Status.STATUS_INVALID_PARTICIPANTS.name());
      } else if (var2 != null && var2.length != 0) {
         com.lunarclient.websocket.conversation.v1.CreateConversationRequest.Builder var3 = CreateConversationRequest.newBuilder().setType(var1);

         for (GuiExtension.Data3 var7 : var2) {
            if (var7 == null || var7.field1 == null) {
               var0.reject(com.lunarclient.websocket.conversation.v1.CreateConversationResponse.Status.STATUS_INVALID_PARTICIPANTS.name());
               return;
            }

            var3.addParticipantUuids(ThreadModuleDump66.method3(var7.field1));
         }

         CreateConversationRequest var8 = var3.build();
         ThreadModuleDump63.method4()
            .method52()
            .method28(
               var8,
               var1x -> {
                  if (var1x == null) {
                     var0.reject(com.lunarclient.websocket.conversation.v1.CreateConversationResponse.Status.STATUS_UNSPECIFIED.name());
                  } else {
                     com.lunarclient.websocket.conversation.v1.CreateConversationResponse.Status var2x = var1x.getStatus();
                     if (var2x != com.lunarclient.websocket.conversation.v1.CreateConversationResponse.Status.STATUS_OK
                        && var2x != com.lunarclient.websocket.conversation.v1.CreateConversationResponse.Status.STATUS_CONVERSATION_ALREADY_EXISTS) {
                        var0.reject(var2x.name());
                     } else if (!var1x.hasConversation()) {
                        var0.reject(com.lunarclient.websocket.conversation.v1.CreateConversationResponse.Status.STATUS_UNSPECIFIED.name());
                     } else {
                        ConversationReference var3x = var1x.getConversation().getConversationReference();
                        UUID var4 = ThreadModuleDump66.method1(var3x.getConversationReference());
                        var0.resolve(var4.toString());
                     }
                  }
               }
            );
      } else {
         var0.reject(com.lunarclient.websocket.conversation.v1.CreateConversationResponse.Status.STATUS_INVALID_PARTICIPANTS.name());
      }
   }

   @CallbackJS("deleteConversation")
   public static String method13(UUID var0) {
      ConversationReference var1 = method34(var0);
      if (var1 == null) {
         return "";
      }

      DeleteConversationRequest var2 = DeleteConversationRequest.newBuilder().setConversationReference(var1).build();
      ThreadModuleDump63.method4().method52().method29(var2, var0x -> {});
      return var0.toString();
   }

   @CallbackJS("deleteConversationMessage")
   public static String method14(UUID var0, UUID var1) {
      ConversationReference var2 = method34(var0);
      if (var2 != null && var1 != null) {
         DeleteConversationMessageRequest var3 = DeleteConversationMessageRequest.newBuilder()
            .setConversationReference(var2)
            .setMessageId(ThreadModuleDump66.method3(var1))
            .build();
         ThreadModuleDump63.method4().method52().method30(var3, var0x -> {});
         return var1.toString();
      } else {
         return "";
      }
   }

   @CallbackJS("deleteConversationMessageHistory")
   public static String method15(UUID var0) {
      ConversationReference var1 = method34(var0);
      if (var1 == null) {
         return "";
      }

      DeleteConversationMessageHistoryRequest var2 = DeleteConversationMessageHistoryRequest.newBuilder().setConversationReference(var1).build();
      ThreadModuleDump63.method4().method52().method31(var2, var0x -> {});
      return var0.toString();
   }

   @CallbackJS("updateConversationOwner")
   public static String method16(UUID var0, UUID var1) {
      ConversationReference var2 = method34(var0);
      if (var2 != null && var1 != null) {
         UpdateConversationOwnerRequest var3 = UpdateConversationOwnerRequest.newBuilder()
            .setTargetUuid(ThreadModuleDump66.method3(var1))
            .setConversationReference(var2)
            .build();
         ThreadModuleDump63.method4().method52().method32(var3, var0x -> {});
         return var0.toString();
      } else {
         return "";
      }
   }

   @CallbackJS("addConversationParticipants")
   public static String method17(UUID var0, GuiExtension.Data3[] var1) {
      ConversationReference var2 = method34(var0);
      if (var2 == null) {
         return "";
      }

      com.lunarclient.websocket.conversation.v1.AddConversationParticipantsRequest.Builder var3 = AddConversationParticipantsRequest.newBuilder()
         .setConversationReference(var2);

      for (GuiExtension.Data3 var7 : var1) {
         var3.addTargets(UuidAndUsername.newBuilder().setUuid(ThreadModuleDump66.method3(var7.field1)).setUsername(var7.field2).build());
      }

      ThreadModuleDump63.method4().method52().method33(var3.build(), var0x -> {});
      return var0.toString();
   }

   @CallbackJS("removeConversationParticipant")
   public static String method18(UUID var0, UUID var1, String var2) {
      ConversationReference var3 = method34(var0);
      if (var3 != null && var1 != null) {
         RemoveConversationParticipantRequest var4 = RemoveConversationParticipantRequest.newBuilder()
            .setConversationReference(var3)
            .setTarget(UuidAndUsername.newBuilder().setUuid(ThreadModuleDump66.method3(var1)).setUsername(var2).build())
            .build();
         ThreadModuleDump63.method4().method52().method34(var4, var0x -> {});
         return var0.toString();
      } else {
         return "";
      }
   }

   @CallbackJS("updateConversationName")
   public static String method19(UUID var0, String var1) {
      ConversationReference var2 = method34(var0);
      String var3 = var1 == null ? "" : var1.trim();
      if (var2 != null && !var3.isEmpty()) {
         UpdateConversationNameRequest var4 = UpdateConversationNameRequest.newBuilder().setConversationReference(var2).setName(var3).build();
         ThreadModuleDump63.method4().method52().method35(var4, var0x -> {});
         return var0.toString();
      } else {
         return "";
      }
   }

   @CallbackJS("updateConversationIcon")
   public static String method20(UUID var0, String var1) {
      ConversationReference var2 = method34(var0);
      if (var2 != null && var1 != null && !var1.isBlank()) {
         UpdateConversationIconRequest var3 = UpdateConversationIconRequest.newBuilder()
            .setConversationReference(var2)
            .setImage(ConversationImage.newBuilder().setUrl(var1.trim()).build())
            .build();
         ThreadModuleDump63.method4().method52().method36(var3, var0x -> {});
         return var0.toString();
      } else {
         return "";
      }
   }

   @CallbackJS("loadPinnedMessages")
   public static String method21(UUID var0) {
      ConversationReference var1 = method34(var0);
      if (var1 == null) {
         return "";
      }

      LoadPinnedMessagesRequest var2 = LoadPinnedMessagesRequest.newBuilder().setConversationReference(var1).build();
      ThreadModuleDump63.method4().method52().method37(var2, var0x -> {});
      return var0.toString();
   }

   @CallbackJS("addPinnedMessage")
   public static String method22(UUID var0, UUID var1) {
      ConversationReference var2 = method34(var0);
      if (var2 != null && var1 != null) {
         AddPinnedMessageRequest var3 = AddPinnedMessageRequest.newBuilder()
            .setConversationReference(var2)
            .setMessageId(ThreadModuleDump66.method3(var1))
            .build();
         ThreadModuleDump63.method4().method52().method38(var3, var0x -> {});
         return var1.toString();
      } else {
         return "";
      }
   }

   @CallbackJS("removePinnedMessage")
   public static String method23(UUID var0, UUID var1) {
      ConversationReference var2 = method34(var0);
      if (var2 != null && var1 != null) {
         RemovePinnedMessageRequest var3 = RemovePinnedMessageRequest.newBuilder()
            .setConversationReference(var2)
            .setMessageId(ThreadModuleDump66.method3(var1))
            .build();
         ThreadModuleDump63.method4().method52().method39(var3, var0x -> {});
         return var1.toString();
      } else {
         return "";
      }
   }

   @CallbackJS("setInvitePolicy")
   public static String method24(UUID var0, InvitePolicy var1) {
      ConversationReference var2 = method34(var0);
      if (var2 != null && var1 != null) {
         SetInvitePolicyRequest var3 = SetInvitePolicyRequest.newBuilder().setConversationReference(var2).setPolicy(var1).build();
         ThreadModuleDump63.method4().method52().method40(var3, var0x -> {});
         return var0.toString();
      } else {
         return "";
      }
   }

   @CallbackJS("setNamePolicy")
   public static String method25(UUID var0, NamePolicy var1) {
      ConversationReference var2 = method34(var0);
      if (var2 != null && var1 != null) {
         SetNamePolicyRequest var3 = SetNamePolicyRequest.newBuilder().setConversationReference(var2).setPolicy(var1).build();
         ThreadModuleDump63.method4().method52().method41(var3, var0x -> {});
         return var0.toString();
      } else {
         return "";
      }
   }

   @CallbackJS("setIconPolicy")
   public static String method26(UUID var0, IconPolicy var1) {
      ConversationReference var2 = method34(var0);
      if (var2 != null && var1 != null) {
         SetIconPolicyRequest var3 = SetIconPolicyRequest.newBuilder().setConversationReference(var2).setPolicy(var1).build();
         ThreadModuleDump63.method4().method52().method42(var3, var0x -> {});
         return var0.toString();
      } else {
         return "";
      }
   }

   @CallbackJS("setMessagePinningPolicy")
   public static String method27(UUID var0, MessagePinningPolicy var1) {
      ConversationReference var2 = method34(var0);
      if (var2 != null && var1 != null) {
         SetMessagePinningPolicyRequest var3 = SetMessagePinningPolicyRequest.newBuilder().setConversationReference(var2).setPolicy(var1).build();
         ThreadModuleDump63.method4().method52().method43(var3, var0x -> {});
         return var0.toString();
      } else {
         return "";
      }
   }

   @CallbackJS("pickGroupIconForPreview")
   public static void method28(UUID var0) {
      Nameplate.method1(var0, method34(var0));
   }

   @CallbackJS("uploadPendingGroupIcon")
   public static void method29(UUID var0) {
      Nameplate.method3(var0, method34(var0));
   }

   @CallbackJS("pickChatImagesForPreview")
   public static void method30(UUID var0) {
      Nameplate.method5(var0, method34(var0));
   }

   @CallbackJS("uploadPendingChatImages")
   public static void method31(UUID var0) {
      Nameplate.method7(var0, method34(var0));
   }

   @CallbackJS("getUploadUrls")
   public static String method32(UUID var0, UploadUsage var1, GuiExtension.Data4[] var2) {
      ConversationReference var3 = method34(var0);
      if (var3 == null) {
         return "";
      }

      com.lunarclient.websocket.conversation.v1.GetUploadUrlsRequest.Builder var4 = GetUploadUrlsRequest.newBuilder()
         .setConversationReference(var3)
         .setUsage(var1);

      for (GuiExtension.Data4 var8 : var2) {
         var4.addItems(UploadRequestItem.newBuilder().setContentType(var8.contentType).setExpectedSizeBytes(var8.field1).build());
      }

      ThreadModuleDump63.method4().method52().method44(var4.build(), var0x -> {});
      return var0.toString();
   }

   @CallbackJS("searchGifs")
   public static void method33(PromiseJS<Nameplate2.Data11> var0, @Nullable String var1, int var2) {
      CompletableFuture.<Nameplate2.Data11>supplyAsync(() -> Nameplate2.method1(var1, var2), ThreadModuleDump37.method6()).whenComplete((var1x, var2x) -> {
         if (var2x != null) {
            var0.reject("SEARCH_GIFS_FAILED");
         } else {
            var0.resolve(var1x);
         }
      });
   }

   @Nullable
   private static ConversationReference method34(@Nullable UUID var0) {
      return var0 == null ? null : ConversationReference.newBuilder().setConversationReference(ThreadModuleDump66.method3(var0)).build();
   }

   private static String method35(UUID var0, @Nullable UUID var1, @Nullable UUID var2, @Nullable UUID var3) {
      ConversationReference var4 = method34(var0);
      if (var4 == null) {
         return "";
      }

      com.lunarclient.websocket.conversation.v1.LoadConversationRequest.Builder var5 = LoadConversationRequest.newBuilder().setConversationReference(var4);
      if (var1 != null) {
         var5.setBeforeMessageId(ThreadModuleDump66.method3(var1));
      }

      if (var2 != null) {
         var5.setAfterMessageId(ThreadModuleDump66.method3(var2));
      }

      if (var3 != null) {
         var5.setAnchorMessageId(ThreadModuleDump66.method3(var3));
      }

      ThreadModuleDump63.method4().method52().method27(var5.build(), var0x -> {});
      return var0.toString();
   }

   public class Data2 {
      @SerializedName("conversationId")
      private final String field1;
      @SerializedName("clientMessageId")
      private final String field2;
      @SerializedName("status")
      private final String field3;

      public Data2(String var1, String var2, String var3) {
         this.field1 = var1;
         this.field2 = var2;
         this.field3 = var3;
      }

      @SerializedName("conversationId")
      public String method1() {
         return this.field1;
      }

      @SerializedName("clientMessageId")
      public String method2() {
         return this.field2;
      }

      @SerializedName("status")
      public String status() {
         return this.field3;
      }
   }

   public class Data3 {
      @SerializedName("uuid")
      private final UUID field1;
      @SerializedName("username")
      private final String field2;

      public Data3(UUID var1, String var2) {
         this.field1 = var1;
         this.field2 = var2;
      }

      @SerializedName("uuid")
      public UUID uuid() {
         return this.field1;
      }

      @SerializedName("username")
      public String username() {
         return this.field2;
      }
   }

   public class Data4 {
      @SerializedName("content_type")
      private final String contentType;
      @SerializedName("expected_size_bytes")
      private final int field1;

      public Data4(String var1, int var2) {
         this.contentType = var1;
         this.field1 = var2;
      }

      @SerializedName("content_type")
      public String method1() {
         return this.contentType;
      }

      @SerializedName("expected_size_bytes")
      public int method2() {
         return this.field1;
      }
   }
}
