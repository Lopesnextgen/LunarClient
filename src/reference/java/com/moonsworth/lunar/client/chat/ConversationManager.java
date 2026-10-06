package com.moonsworth.lunar.client.chat;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.common.v1.UuidAndUsername;
import com.lunarclient.websocket.conversation.v1.AddConversationParticipantsRequest;
import com.lunarclient.websocket.conversation.v1.AddConversationParticipantsResponse;
import com.lunarclient.websocket.conversation.v1.AddPinnedMessageRequest;
import com.lunarclient.websocket.conversation.v1.AddPinnedMessageResponse;
import com.lunarclient.websocket.conversation.v1.Conversation;
import com.lunarclient.websocket.conversation.v1.ConversationAddParticipantsPush;
import com.lunarclient.websocket.conversation.v1.ConversationAddPinnedMessagePush;
import com.lunarclient.websocket.conversation.v1.ConversationAddedPush;
import com.lunarclient.websocket.conversation.v1.ConversationMessage;
import com.lunarclient.websocket.conversation.v1.ConversationMessageHistoryDeletedPush;
import com.lunarclient.websocket.conversation.v1.ConversationMessagePush;
import com.lunarclient.websocket.conversation.v1.ConversationOwnerUpdatePush;
import com.lunarclient.websocket.conversation.v1.ConversationPreSendActionPush;
import com.lunarclient.websocket.conversation.v1.ConversationReference;
import com.lunarclient.websocket.conversation.v1.ConversationRemoveMessagePush;
import com.lunarclient.websocket.conversation.v1.ConversationRemoveParticipantPush;
import com.lunarclient.websocket.conversation.v1.ConversationRemovePinnedMessagePush;
import com.lunarclient.websocket.conversation.v1.ConversationRemovedPush;
import com.lunarclient.websocket.conversation.v1.ConversationStub;
import com.lunarclient.websocket.conversation.v1.ConversationType;
import com.lunarclient.websocket.conversation.v1.ConversationUpdateIconPolicyPush;
import com.lunarclient.websocket.conversation.v1.ConversationUpdateIconPush;
import com.lunarclient.websocket.conversation.v1.ConversationUpdateInvitePolicyPush;
import com.lunarclient.websocket.conversation.v1.ConversationUpdateMessagePinningPolicyPush;
import com.lunarclient.websocket.conversation.v1.ConversationUpdateNamePolicyPush;
import com.lunarclient.websocket.conversation.v1.ConversationUpdateNamePush;
import com.lunarclient.websocket.conversation.v1.CreateConversationRequest;
import com.lunarclient.websocket.conversation.v1.CreateConversationResponse;
import com.lunarclient.websocket.conversation.v1.DeleteConversationMessageHistoryRequest;
import com.lunarclient.websocket.conversation.v1.DeleteConversationMessageHistoryResponse;
import com.lunarclient.websocket.conversation.v1.DeleteConversationMessageRequest;
import com.lunarclient.websocket.conversation.v1.DeleteConversationMessageResponse;
import com.lunarclient.websocket.conversation.v1.DeleteConversationRequest;
import com.lunarclient.websocket.conversation.v1.DeleteConversationResponse;
import com.lunarclient.websocket.conversation.v1.GetConversationsRequest;
import com.lunarclient.websocket.conversation.v1.GetConversationsResponse;
import com.lunarclient.websocket.conversation.v1.GetUploadUrlsRequest;
import com.lunarclient.websocket.conversation.v1.GetUploadUrlsResponse;
import com.lunarclient.websocket.conversation.v1.LoadConversationRequest;
import com.lunarclient.websocket.conversation.v1.LoadConversationResponse;
import com.lunarclient.websocket.conversation.v1.LoadPinnedMessagesRequest;
import com.lunarclient.websocket.conversation.v1.LoadPinnedMessagesResponse;
import com.lunarclient.websocket.conversation.v1.LoginResponse;
import com.lunarclient.websocket.conversation.v1.PreSendActionRequest;
import com.lunarclient.websocket.conversation.v1.PreSendActionResponse;
import com.lunarclient.websocket.conversation.v1.RemoveConversationParticipantRequest;
import com.lunarclient.websocket.conversation.v1.RemoveConversationParticipantResponse;
import com.lunarclient.websocket.conversation.v1.RemovePinnedMessageRequest;
import com.lunarclient.websocket.conversation.v1.RemovePinnedMessageResponse;
import com.lunarclient.websocket.conversation.v1.SendConversationMessageRequest;
import com.lunarclient.websocket.conversation.v1.SendConversationMessageResponse;
import com.lunarclient.websocket.conversation.v1.SetIconPolicyRequest;
import com.lunarclient.websocket.conversation.v1.SetIconPolicyResponse;
import com.lunarclient.websocket.conversation.v1.SetInvitePolicyRequest;
import com.lunarclient.websocket.conversation.v1.SetInvitePolicyResponse;
import com.lunarclient.websocket.conversation.v1.SetMessagePinningPolicyRequest;
import com.lunarclient.websocket.conversation.v1.SetMessagePinningPolicyResponse;
import com.lunarclient.websocket.conversation.v1.SetNamePolicyRequest;
import com.lunarclient.websocket.conversation.v1.SetNamePolicyResponse;
import com.lunarclient.websocket.conversation.v1.UnfocusConversationsRequest;
import com.lunarclient.websocket.conversation.v1.UnfocusConversationsResponse;
import com.lunarclient.websocket.conversation.v1.UpdateConversationIconRequest;
import com.lunarclient.websocket.conversation.v1.UpdateConversationIconResponse;
import com.lunarclient.websocket.conversation.v1.UpdateConversationNameRequest;
import com.lunarclient.websocket.conversation.v1.UpdateConversationNameResponse;
import com.lunarclient.websocket.conversation.v1.UpdateConversationOwnerRequest;
import com.lunarclient.websocket.conversation.v1.UpdateConversationOwnerResponse;
import com.lunarclient.websocket.conversation.v1.ConversationSender.SenderCase;
import com.lunarclient.websocket.conversation.v1.ConversationService.Interface;
import com.lunarclient.websocket.conversation.v1.GetConversationsResponse.Status;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.chat.ChatMessage;
import com.moonsworth.lunar.client.chat.StickerStore;
import com.moonsworth.lunar.client.chat.Conversation;
import com.moonsworth.lunar.client.account.GuiProfile;
import com.moonsworth.lunar.client.driver.core.gui.Gui;
import com.moonsworth.lunar.client.driver.core.gui.JsonProviderLegacy;
import com.moonsworth.lunar.client.mixin.EntityRenderer4;
import com.moonsworth.lunar.client.mixin.EntityRendererType2;
import com.moonsworth.lunar.client.util.ThreadModuleDump66;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class ConversationManager extends com.moonsworth.lunar.client.framework.loading.ItemMapHandler<UUID, Conversation> implements Gui, JsonProviderLegacy {
   private final StickerStore field2 = new StickerStore();
   private final ConcurrentLinkedQueue<JsonObject> field3 = new ConcurrentLinkedQueue<>();
   private static final String field4 = "conversation.upsert";
   private static final String field5 = "conversation.remove";
   private static final String field6 = "conversation.service.update";
   private static final String field7 = "conversation.message.upsert";
   private static final String field8 = "conversation.message.remove";
   private static final String field9 = "conversation.messageHistory.clear";
   private static final String field10 = "conversation.messages.sync";
   private static final String field11 = "conversation.presend.set";
   private static final String field12 = "conversation.presend.clear";
   private static final String field13 = "conversation.owner.update";
   private static final String field14 = "conversation.participants.add";
   private static final String field15 = "conversation.participants.remove";
   private static final String field16 = "conversation.name.update";
   private static final String field17 = "conversation.icon.update";
   private static final String field18 = "conversation.pinned.add";
   private static final String field19 = "conversation.pinned.remove";
   private static final String field20 = "conversation.policy.invite.update";
   private static final String field21 = "conversation.policy.name.update";
   private static final String field22 = "conversation.policy.icon.update";
   private static final String field23 = "conversation.policy.pinning.update";

   @Override
   protected Map<UUID, Conversation> method3() {
      return new ConcurrentHashMap<>();
   }

   public void method4() {
      this.clear();
      this.field2.clear();
      this.field3.clear();
   }

   public boolean method5() {
      return this.method3().values().stream().anyMatch(var0 -> var0.getUnreadCount() > 0);
   }

   public void method4(LoginResponse var1) {
      this.field2
         .method1(
            var1.getParticipantLimit(), var1.getTotalConversations(), var1.getMaxMessageLength(), var1.getStickerPacksList(), var1.getEmojiCategoriesList()
         );
      this.clear();

      for (ConversationStub var3 : var1.getConversationsList()) {
         Conversation var4 = this.method51(var3.getConversationReference());
         if (var4 != null && var3.hasUserState()) {
            var4.method31(var3.getUserState().getUnreadCount());
            var4.method33(ThreadModuleDump66.method5(var3.getUserState().getLastSeenTime()));
         }
      }

      this.method48();
   }

   public void method5(GetConversationsResponse var1) {
      if (var1 != null) {
         this.field2.method9(var1.getTotalConversations());
         this.method48();
         if (var1.getStatus() == Status.STATUS_OK) {
            for (Conversation var3 : var1.getConversationsList()) {
               this.method6(var3);
            }
         }
      }
   }

   public void method6(Conversation var1) {
      UUID var2 = this.method52(var1.getConversationReference());
      if (var2 != null) {
         Conversation var3 = (Conversation)this.method3().get(var2);
         if (var3 == null) {
            var3 = Conversation.method1(var1);
            this.method3().put(var2, var3);
         } else {
            var3.method2(var1);
         }

         ConversationReference var4 = this.method53(var3);
         if (var4 != null) {
            this.method49("conversation.upsert", var4, var3.provide().getAsJsonObject());
         }
      }
   }

   public void method7(ConversationMessagePush var1) {
      this.method50(var1.getConversationReference(), (var2, var3) -> {
         ChatMessage var4 = ChatMessage.method1(var1.getMessage());
         if (var4 != null) {
            var4.method12(var1.hasUpdated() && var1.getUpdated());
            var2.method3(var4);
            if (!var4.method6() && (var2.method14() == null || var4.method2().isAfter(var2.method14()))) {
               var2.method23(var4.method2());
            }

            JsonObject var5 = new JsonObject();
            var5.add("message", var4.provide());
            this.method49("conversation.message.upsert", var3, var5);
         }
      });
   }

   public void method8(ConversationPreSendActionPush var1) {
      if (var1.getSender().getSenderCase() == SenderCase.PLAYER) {
         this.method50(var1.getConversationReference(), (var2, var3) -> {
            UuidAndUsername var4 = var1.getSender().getPlayer();
            var2.method8(var4, var1.getAction());
            JsonObject var5 = new JsonObject();
            var5.addProperty("userId", ThreadModuleDump66.method1(var4.getUuid()).toString());
            var5.addProperty("action", var1.getAction().name());
            this.method49("conversation.presend.set", var3, var5);
         });
      }
   }

   public void method9(ConversationOwnerUpdatePush var1) {
      this.method50(var1.getConversationReference(), (var2, var3) -> {
         var2.method34(GuiProfile.method3(var1.getInvoker()));
         var2.method35(ThreadModuleDump66.method1(var1.getPreviousOwnerUuid()));
         var2.method25(ThreadModuleDump66.method1(var1.getOwnerUuid()));
         JsonObject var4 = new JsonObject();
         if (var2.method15() != null) {
            var4.addProperty("ownerUuid", var2.method15().toString());
         }

         if (var2.method19() != null) {
            var4.addProperty("previousOwnerUuid", var2.method19().toString());
         }

         this.method49("conversation.owner.update", var3, var4);
      });
   }

   public void method10(ConversationAddParticipantsPush var1) {
      this.method50(var1.getConversationReference(), (var2, var3) -> {
         var2.method34(GuiProfile.method3(var1.getInvoker()));
         HashSet var4 = new HashSet();
         var2.method10().forEach(var1xx -> {
            if (var1xx.method4() != null) {
               var4.add(var1xx.method4());
            }
         });
         JsonObject var5 = new JsonObject();
         JsonArray var6 = new JsonArray();
         var1.getParticipantsList().forEach(var3x -> {
            GuiProfile var4x = GuiProfile.method1(var3x);
            UUID var5x = var4x.method4();
            if (var5x == null || var4.add(var5x)) {
               var2.method10().add(var4x);
               var6.add(var4x.provide());
            }
         });
         if (!var6.isEmpty()) {
            var5.add("participants", var6);
            this.method49("conversation.participants.add", var3, var5);
         }
      });
   }

   public void method11(ConversationRemoveParticipantPush var1) {
      this.method50(var1.getConversationReference(), (var2, var3) -> {
         var2.method34(GuiProfile.method3(var1.getInvoker()));
         UUID var4 = ThreadModuleDump66.method1(var1.getParticipant().getUuid());
         var2.method10().removeIf(var1xx -> var1xx.method4().equals(var4));
         JsonObject var5 = new JsonObject();
         var5.addProperty("participantId", var4.toString());
         this.method49("conversation.participants.remove", var3, var5);
      });
   }

   public void method12(ConversationUpdateNamePush var1) {
      this.method50(var1.getConversationReference(), (var2, var3) -> {
         var2.method34(GuiProfile.method3(var1.getInvoker()));
         var2.setName(var1.getName());
         JsonObject var4 = new JsonObject();
         var4.addProperty("name", var1.getName());
         this.method49("conversation.name.update", var3, var4);
      });
   }

   public void method13(ConversationUpdateIconPush var1) {
      this.method50(var1.getConversationReference(), (var2, var3) -> {
         var2.method34(GuiProfile.method3(var1.getInvoker()));
         var2.method24(var1.getImageUrl());
         JsonObject var4 = new JsonObject();
         var4.addProperty("conversationImageUrl", var1.getImageUrl());
         this.method49("conversation.icon.update", var3, var4);
      });
   }

   public void method14(ConversationAddPinnedMessagePush var1) {
      this.method50(var1.getConversationReference(), (var2, var3) -> {
         var2.method34(GuiProfile.method3(var1.getInvoker()));
         UUID var4 = ThreadModuleDump66.method1(var1.getMessageId());
         var2.method12().add(var4);
         JsonObject var5 = new JsonObject();
         var5.addProperty("messageId", var4.toString());
         this.method49("conversation.pinned.add", var3, var5);
      });
   }

   public void method15(ConversationRemovePinnedMessagePush var1) {
      this.method50(var1.getConversationReference(), (var2, var3) -> {
         var2.method34(GuiProfile.method3(var1.getInvoker()));
         UUID var4 = ThreadModuleDump66.method1(var1.getMessageId());
         var2.method12().remove(var4);
         JsonObject var5 = new JsonObject();
         var5.addProperty("messageId", var4.toString());
         this.method49("conversation.pinned.remove", var3, var5);
      });
   }

   public void method16(ConversationUpdateInvitePolicyPush var1) {
      this.method50(var1.getConversationReference(), (var2, var3) -> {
         var2.method27(var1.getPolicy());
         JsonObject var4 = new JsonObject();
         var4.addProperty("policy", var1.getPolicy().name());
         this.method49("conversation.policy.invite.update", var3, var4);
      });
   }

   public void method17(ConversationUpdateNamePolicyPush var1) {
      this.method50(var1.getConversationReference(), (var2, var3) -> {
         var2.method28(var1.getPolicy());
         JsonObject var4 = new JsonObject();
         var4.addProperty("policy", var1.getPolicy().name());
         this.method49("conversation.policy.name.update", var3, var4);
      });
   }

   public void method18(ConversationUpdateIconPolicyPush var1) {
      this.method50(var1.getConversationReference(), (var2, var3) -> {
         var2.method29(var1.getPolicy());
         JsonObject var4 = new JsonObject();
         var4.addProperty("policy", var1.getPolicy().name());
         this.method49("conversation.policy.icon.update", var3, var4);
      });
   }

   public void method19(ConversationUpdateMessagePinningPolicyPush var1) {
      this.method50(var1.getConversationReference(), (var2, var3) -> {
         var2.method30(var1.getPolicy());
         JsonObject var4 = new JsonObject();
         var4.addProperty("policy", var1.getPolicy().name());
         this.method49("conversation.policy.pinning.update", var3, var4);
      });
   }

   public void method20(ConversationAddedPush var1) {
      this.method6(var1.getConversation());
      UUID var2 = this.method52(var1.getConversation().getConversationReference());
      if (var2 != null) {
         Conversation var3 = (Conversation)this.method3().get(var2);
         if (var3 != null) {
            ConversationReference var4 = this.method53(var3);
            if (var4 != null) {
               var3.method36(GuiProfile.method3(var1.getSender()));
               var3.method37(ThreadModuleDump66.method5(var1.getAddedAt()));
               this.method49("conversation.upsert", var4, var3.provide().getAsJsonObject());
            }
         }
      }
   }

   public void method21(ConversationRemovedPush var1) {
      UUID var2 = this.method52(var1.getConversationReference());
      if (var2 != null) {
         this.method3().remove(var2);
         this.method49("conversation.remove", var1.getConversationReference(), null);
      }
   }

   public void method22(ConversationRemoveMessagePush var1) {
      UUID var2 = this.method52(var1.getConversationReference());
      if (var2 != null) {
         Conversation var3 = (Conversation)this.method3().get(var2);
         if (var3 != null) {
            UUID var4 = ThreadModuleDump66.method1(var1.getId());
            var3.method5(var4);
            JsonObject var5 = new JsonObject();
            var5.addProperty("messageId", var4.toString());
            ConversationReference var6 = this.method53(var3);
            if (var6 != null) {
               this.method49("conversation.message.remove", var6, var5);
            }
         }
      }
   }

   public void method23(ConversationMessageHistoryDeletedPush var1) {
      UUID var2 = this.method52(var1.getConversationReference());
      if (var2 != null) {
         Conversation var3 = (Conversation)this.method3().get(var2);
         if (var3 != null) {
            UUID var4 = var1.hasSenderUuid() ? ThreadModuleDump66.method1(var1.getSenderUuid()) : null;
            var3.method6(var4);
            JsonObject var5 = new JsonObject();
            if (var4 != null) {
               var5.addProperty("senderUuid", var4.toString());
            }

            ConversationReference var6 = this.method53(var3);
            if (var6 != null) {
               this.method49("conversation.messageHistory.clear", var6, var5);
            }
         }
      }
   }

   public void method24() {
      this.method46(GetConversationsRequest.getDefaultInstance(), this::method5);
   }

   public void method25(SendConversationMessageRequest var1, Consumer<SendConversationMessageResponse> var2) {
      if (var1.getConversationReference().hasConversationReference() && var1.hasMessageContents()) {
         this.method54(var2, var2x -> var2x.sendConversationMessage(null, var1, var2::accept));
      } else {
         var2.accept(null);
      }
   }

   public void method26(PreSendActionRequest var1, Consumer<PreSendActionResponse> var2) {
      if (!var1.getConversationReference().hasConversationReference()) {
         var2.accept(null);
      } else {
         this.method54(var2, var2x -> var2x.preSendAction(null, var1, var2::accept));
      }
   }

   public void method27(LoadConversationRequest var1, Consumer<LoadConversationResponse> var2) {
      if (!var1.getConversationReference().hasConversationReference()) {
         var2.accept(null);
      } else {
         this.method54(var2, var3 -> var3.loadConversation(null, var1, var3x -> {
            Conversation var4 = this.method51(var1.getConversationReference());
            if (var4 == null) {
               var2.accept(var3x);
            } else {
               var4.method4(var3x.getMessagesList());
               if (!var3x.getMessagesList().isEmpty()) {
                  JsonObject var5 = new JsonObject();
                  JsonArray var6 = new JsonArray();

                  for (ConversationMessage var8 : var3x.getMessagesList()) {
                     ChatMessage var9 = ChatMessage.method1(var8);
                     if (var9 != null) {
                        var6.add(var9.provide());
                     }
                  }

                  var5.add("messages", var6);
                  ConversationReference var10 = this.method53(var4);
                  if (var10 != null) {
                     this.method49("conversation.messages.sync", var10, var5);
                  }
               }

               var2.accept(var3x);
            }
         }));
      }
   }

   public void method28(CreateConversationRequest var1, Consumer<CreateConversationResponse> var2) {
      if (var1.getType() == ConversationType.CONVERSATION_TYPE_UNSPECIFIED) {
         var2.accept(null);
      } else {
         this.method54(
            var2,
            var3 -> var3.createConversation(
               null,
               var1,
               var2xx -> {
                  if (var2xx.hasConversation()) {
                     com.lunarclient.websocket.conversation.v1.CreateConversationResponse.Status var3x = var2xx.getStatus();
                     if (var3x == com.lunarclient.websocket.conversation.v1.CreateConversationResponse.Status.STATUS_OK
                        || var3x == com.lunarclient.websocket.conversation.v1.CreateConversationResponse.Status.STATUS_OK) {
                        this.method6(var2xx.getConversation());
                     }
                  }

                  var2.accept(var2xx);
               }
            )
         );
      }
   }

   public void method29(DeleteConversationRequest var1, Consumer<DeleteConversationResponse> var2) {
      if (!var1.getConversationReference().hasConversationReference()) {
         var2.accept(null);
      } else {
         this.method54(var2, var3 -> var3.deleteConversation(null, var1, var3x -> {
            if (var3x.getStatus() == com.lunarclient.websocket.conversation.v1.DeleteConversationResponse.Status.STATUS_OK) {
               UUID var4 = this.method52(var1.getConversationReference());
               if (var4 != null) {
                  this.method3().remove(var4);
               }

               this.method49("conversation.remove", var1.getConversationReference(), null);
            }

            var2.accept(var3x);
         }));
      }
   }

   public void method30(DeleteConversationMessageRequest var1, Consumer<DeleteConversationMessageResponse> var2) {
      if (!var1.getConversationReference().hasConversationReference()) {
         var2.accept(null);
      } else {
         this.method54(var2, var3 -> var3.deleteConversationMessage(null, var1, var3x -> {
            if (var3x.getStatus() == com.lunarclient.websocket.conversation.v1.DeleteConversationMessageResponse.Status.STATUS_OK) {
               UUID var4 = ThreadModuleDump66.method1(var1.getMessageId());
               UUID var5 = this.method52(var1.getConversationReference());
               if (var5 != null) {
                  Conversation var6 = (Conversation)this.method3().get(var5);
                  if (var6 != null) {
                     var6.method5(var4);
                  }
               }

               JsonObject var7 = new JsonObject();
               var7.addProperty("messageId", var4.toString());
               this.method49("conversation.message.remove", var1.getConversationReference(), var7);
            }

            var2.accept(var3x);
         }));
      }
   }

   public void method31(DeleteConversationMessageHistoryRequest var1, Consumer<DeleteConversationMessageHistoryResponse> var2) {
      if (!var1.getConversationReference().hasConversationReference()) {
         var2.accept(null);
      } else {
         this.method54(var2, var2x -> var2x.deleteConversationMessageHistory(null, var1, var2::accept));
      }
   }

   public void method32(UpdateConversationOwnerRequest var1, Consumer<UpdateConversationOwnerResponse> var2) {
      if (!var1.getConversationReference().hasConversationReference()) {
         var2.accept(null);
      } else {
         this.method54(var2, var3 -> var3.updateConversationOwner(null, var1, var3x -> {
            if (var3x.getStatus() == com.lunarclient.websocket.conversation.v1.UpdateConversationOwnerResponse.Status.STATUS_OK) {
               this.method47(var1.getConversationReference());
            }

            var2.accept(var3x);
         }));
      }
   }

   public void method33(AddConversationParticipantsRequest var1, Consumer<AddConversationParticipantsResponse> var2) {
      if (!var1.getConversationReference().hasConversationReference()) {
         var2.accept(null);
      } else {
         this.method54(var2, var3 -> var3.addConversationParticipants(null, var1, var3x -> {
            if (var3x.getStatus() == com.lunarclient.websocket.conversation.v1.AddConversationParticipantsResponse.Status.STATUS_OK) {
               this.method47(var1.getConversationReference());
            }

            var2.accept(var3x);
         }));
      }
   }

   public void method34(RemoveConversationParticipantRequest var1, Consumer<RemoveConversationParticipantResponse> var2) {
      if (!var1.getConversationReference().hasConversationReference()) {
         var2.accept(null);
      } else {
         this.method54(var2, var3 -> var3.removeConversationParticipant(null, var1, var3x -> {
            if (var3x.getStatus() == com.lunarclient.websocket.conversation.v1.RemoveConversationParticipantResponse.Status.STATUS_OK) {
               this.method47(var1.getConversationReference());
            }

            var2.accept(var3x);
         }));
      }
   }

   public void method35(UpdateConversationNameRequest var1, Consumer<UpdateConversationNameResponse> var2) {
      if (var1.getConversationReference().hasConversationReference() && !var1.getName().trim().isEmpty()) {
         this.method54(var2, var3 -> var3.updateConversationName(null, var1, var3x -> {
            if (var3x.getStatus() == com.lunarclient.websocket.conversation.v1.UpdateConversationNameResponse.Status.STATUS_OK) {
               this.method47(var1.getConversationReference());
            }

            var2.accept(var3x);
         }));
      } else {
         var2.accept(null);
      }
   }

   public void method36(UpdateConversationIconRequest var1, Consumer<UpdateConversationIconResponse> var2) {
      if (var1.getConversationReference().hasConversationReference() && var1.hasImage()) {
         this.method54(var2, var3 -> var3.updateConversationIcon(null, var1, var3x -> {
            if (var3x.getStatus() == com.lunarclient.websocket.conversation.v1.UpdateConversationIconResponse.Status.STATUS_OK) {
               this.method47(var1.getConversationReference());
            }

            var2.accept(var3x);
         }));
      } else {
         var2.accept(null);
      }
   }

   public void method37(LoadPinnedMessagesRequest var1, Consumer<LoadPinnedMessagesResponse> var2) {
      if (!var1.getConversationReference().hasConversationReference()) {
         var2.accept(null);
      } else {
         this.method54(var2, var3 -> var3.loadPinnedMessages(null, var1, var3x -> {
            if (var3x.getStatus() == com.lunarclient.websocket.conversation.v1.LoadPinnedMessagesResponse.Status.STATUS_OK) {
               Conversation var4 = this.method51(var1.getConversationReference());
               if (var4 != null) {
                  var4.method7(var3x.getMessagesList());
                  this.method49("conversation.upsert", var1.getConversationReference(), var4.provide().getAsJsonObject());
               }
            }

            var2.accept(var3x);
         }));
      }
   }

   public void method38(AddPinnedMessageRequest var1, Consumer<AddPinnedMessageResponse> var2) {
      if (!var1.getConversationReference().hasConversationReference()) {
         var2.accept(null);
      } else {
         this.method54(var2, var3 -> var3.addPinnedMessage(null, var1, var3x -> {
            if (var3x.getStatus() == com.lunarclient.websocket.conversation.v1.AddPinnedMessageResponse.Status.STATUS_OK) {
               UUID var4 = ThreadModuleDump66.method1(var1.getMessageId());
               Conversation var5 = this.method51(var1.getConversationReference());
               if (var5 != null) {
                  var5.method12().add(var4);
               }

               JsonObject var6 = new JsonObject();
               var6.addProperty("messageId", var4.toString());
               this.method49("conversation.pinned.add", var1.getConversationReference(), var6);
            }

            var2.accept(var3x);
         }));
      }
   }

   public void method39(RemovePinnedMessageRequest var1, Consumer<RemovePinnedMessageResponse> var2) {
      if (!var1.getConversationReference().hasConversationReference()) {
         var2.accept(null);
      } else {
         this.method54(var2, var3 -> var3.removePinnedMessage(null, var1, var3x -> {
            if (var3x.getStatus() == com.lunarclient.websocket.conversation.v1.RemovePinnedMessageResponse.Status.STATUS_OK) {
               UUID var4 = ThreadModuleDump66.method1(var1.getMessageId());
               Conversation var5 = this.method51(var1.getConversationReference());
               if (var5 != null) {
                  var5.method12().remove(var4);
               }

               JsonObject var6 = new JsonObject();
               var6.addProperty("messageId", var4.toString());
               this.method49("conversation.pinned.remove", var1.getConversationReference(), var6);
            }

            var2.accept(var3x);
         }));
      }
   }

   public void method40(SetInvitePolicyRequest var1, Consumer<SetInvitePolicyResponse> var2) {
      if (!var1.getConversationReference().hasConversationReference()) {
         var2.accept(null);
      } else {
         this.method54(var2, var3 -> var3.setInvitePolicy(null, var1, var3x -> {
            if (var3x.getStatus() == com.lunarclient.websocket.conversation.v1.SetInvitePolicyResponse.Status.STATUS_OK) {
               Conversation var4 = this.method51(var1.getConversationReference());
               if (var4 != null) {
                  var4.method27(var1.getPolicy());
               }

               JsonObject var5 = new JsonObject();
               var5.addProperty("policy", var1.getPolicy().name());
               this.method49("conversation.policy.invite.update", var1.getConversationReference(), var5);
            }

            var2.accept(var3x);
         }));
      }
   }

   public void method41(SetNamePolicyRequest var1, Consumer<SetNamePolicyResponse> var2) {
      if (!var1.getConversationReference().hasConversationReference()) {
         var2.accept(null);
      } else {
         this.method54(var2, var3 -> var3.setNamePolicy(null, var1, var3x -> {
            if (var3x.getStatus() == com.lunarclient.websocket.conversation.v1.SetNamePolicyResponse.Status.STATUS_OK) {
               Conversation var4 = this.method51(var1.getConversationReference());
               if (var4 != null) {
                  var4.method28(var1.getPolicy());
               }

               JsonObject var5 = new JsonObject();
               var5.addProperty("policy", var1.getPolicy().name());
               this.method49("conversation.policy.name.update", var1.getConversationReference(), var5);
            }

            var2.accept(var3x);
         }));
      }
   }

   public void method42(SetIconPolicyRequest var1, Consumer<SetIconPolicyResponse> var2) {
      if (!var1.getConversationReference().hasConversationReference()) {
         var2.accept(null);
      } else {
         this.method54(var2, var3 -> var3.setIconPolicy(null, var1, var3x -> {
            if (var3x.getStatus() == com.lunarclient.websocket.conversation.v1.SetIconPolicyResponse.Status.STATUS_OK) {
               Conversation var4 = this.method51(var1.getConversationReference());
               if (var4 != null) {
                  var4.method29(var1.getPolicy());
               }

               JsonObject var5 = new JsonObject();
               var5.addProperty("policy", var1.getPolicy().name());
               this.method49("conversation.policy.icon.update", var1.getConversationReference(), var5);
            }

            var2.accept(var3x);
         }));
      }
   }

   public void method43(SetMessagePinningPolicyRequest var1, Consumer<SetMessagePinningPolicyResponse> var2) {
      if (!var1.getConversationReference().hasConversationReference()) {
         var2.accept(null);
      } else {
         this.method54(var2, var3 -> var3.setMessagePinningPolicy(null, var1, var3x -> {
            if (var3x.getStatus() == com.lunarclient.websocket.conversation.v1.SetMessagePinningPolicyResponse.Status.STATUS_OK) {
               Conversation var4 = this.method51(var1.getConversationReference());
               if (var4 != null) {
                  var4.method30(var1.getPolicy());
               }

               JsonObject var5 = new JsonObject();
               var5.addProperty("policy", var1.getPolicy().name());
               this.method49("conversation.policy.pinning.update", var1.getConversationReference(), var5);
            }

            var2.accept(var3x);
         }));
      }
   }

   public void method44(GetUploadUrlsRequest var1, Consumer<GetUploadUrlsResponse> var2) {
      if (var1.getConversationReference().hasConversationReference() && var1.getItemsCount() != 0) {
         this.method54(var2, var2x -> var2x.getUploadUrls(null, var1, var2::accept));
      } else {
         var2.accept(null);
      }
   }

   public void method45(UnfocusConversationsRequest var1, Consumer<UnfocusConversationsResponse> var2) {
      this.method54(var2, var3 -> var3.unfocusConversations(null, var1, var2xx -> {
         for (Conversation var4 : this.method3().values()) {
            var4.method13().clear();
         }

         this.method49("conversation.presend.clear", null, null);
         var2.accept(var2xx);
      }));
   }

   public void method46(GetConversationsRequest var1, Consumer<GetConversationsResponse> var2) {
      this.method54(var2, var2x -> var2x.getConversations(null, var1, var2::accept));
   }

   private void method47(ConversationReference var1) {
      GetConversationsRequest var2 = GetConversationsRequest.newBuilder().setConversationReference(var1).setLimit(1).build();
      this.method46(var2, this::method5);
   }

   private void method48() {
      this.method49("conversation.service.update", null, this.field2.provide().getAsJsonObject());
   }

   private void method49(String var1, @Nullable ConversationReference var2, @Nullable JsonObject var3) {
      JsonObject var4 = new JsonObject();
      var4.addProperty("action", var1);
      if (var2 != null && var2.hasConversationReference()) {
         var4.addProperty("threadRef", ThreadModuleDump66.method1(var2.getConversationReference()).toString());
      }

      if (var3 != null) {
         var4.add("payload", var3);
      }

      this.field3.add(var4);
   }

   private void method50(ConversationReference var1, BiConsumer<Conversation, ConversationReference> var2) {
      Conversation var3 = this.method51(var1);
      if (var3 != null) {
         ConversationReference var4 = this.method53(var3);
         if (var4 != null) {
            var2.accept(var3, var4);
         }
      }
   }

   @Nullable
   private Conversation method51(ConversationReference var1) {
      UUID var2 = this.method52(var1);
      return var2 == null ? null : this.method3().computeIfAbsent(var2, var1x -> new Conversation(var1));
   }

   @Nullable
   private UUID method52(ConversationReference var1) {
      return var1.hasConversationReference() ? ThreadModuleDump66.method1(var1.getConversationReference()) : null;
   }

   @Nullable
   private ConversationReference method53(Conversation var1) {
      return var1.method9() == null ? null : ConversationReference.newBuilder().setConversationReference(ThreadModuleDump66.method3(var1.method9())).build();
   }

   private void method54(Consumer<?> var1, Consumer<Interface> var2) {
      EntityRenderer4 var3 = Client.method109().method35();
      if (var3 != null && var3.method112() == EntityRendererType2.READY) {
         var2.accept(var3.method86());
      } else {
         var1.accept(null);
      }
   }

   public JsonElement provide() {
      JsonObject var1 = new JsonObject();
      var1.add("service", this.field2.provide());
      JsonArray var2 = new JsonArray();

      for (Conversation var4 : this.method3().values()) {
         var2.add(var4.provide());
      }

      var1.add("threads", var2);
      return var1;
   }

   @Nullable
   public JsonElement method128() {
      JsonArray var1 = new JsonArray();

      JsonObject var2;
      while ((var2 = this.field3.poll()) != null) {
         var1.add(var2);
      }

      return var1.isEmpty() ? null : var1;
   }

   @Generated
   public StickerStore method56() {
      return this.field2;
   }

   @Generated
   public ConcurrentLinkedQueue<JsonObject> method57() {
      return this.field3;
   }
}
