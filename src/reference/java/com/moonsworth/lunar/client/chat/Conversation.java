package com.moonsworth.lunar.client.chat;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.common.v1.UuidAndUsername;
import com.lunarclient.websocket.conversation.v1.Conversation;
import com.lunarclient.websocket.conversation.v1.ConversationMessage;
import com.lunarclient.websocket.conversation.v1.ConversationParticipant;
import com.lunarclient.websocket.conversation.v1.ConversationReference;
import com.lunarclient.websocket.conversation.v1.ConversationType;
import com.lunarclient.websocket.conversation.v1.IconPolicy;
import com.lunarclient.websocket.conversation.v1.InvitePolicy;
import com.lunarclient.websocket.conversation.v1.MessagePinningPolicy;
import com.lunarclient.websocket.conversation.v1.NamePolicy;
import com.lunarclient.websocket.conversation.v1.PreSendAction;
import com.moonsworth.lunar.client.driver.core.gui.JsonProviderLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump66;
import java.time.Instant;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.account.GuiProfile;
import com.moonsworth.lunar.client.chat.ChatMessage;

public class Conversation implements JsonProviderLegacy {
   @Nullable
   private UUID field1;
   private final List<GuiProfile> field2 = new ArrayList<>();
   private final Deque<ChatMessage> field3 = new ArrayDeque<>();
   private final Set<UUID> field4 = new HashSet<>();
   private final Map<UUID, PreSendAction> field5 = new HashMap<>();
   private ConversationType field6;
   @Nullable
   private Instant field7;
   @Nullable
   private String name;
   @Nullable
   private String field8;
   @Nullable
   private UUID field9;
   private int field10;
   private InvitePolicy field11 = InvitePolicy.INVITE_POLICY_UNSPECIFIED;
   private NamePolicy field12 = NamePolicy.NAME_POLICY_UNSPECIFIED;
   private IconPolicy field13 = IconPolicy.ICON_POLICY_UNSPECIFIED;
   private MessagePinningPolicy field14 = MessagePinningPolicy.MESSAGE_PINNING_POLICY_UNSPECIFIED;
   private int field15;
   private boolean field16 = true;
   @Nullable
   private Instant field17;
   @Nullable
   private GuiProfile field18;
   @Nullable
   private UUID field19;
   @Nullable
   private GuiProfile field20;
   @Nullable
   private Instant field21;

   public Conversation(ConversationReference var1) {
      if (var1.hasConversationReference()) {
         this.field1 = ThreadModuleDump66.method1(var1.getConversationReference());
      }
   }

   public static Conversation method1(Conversation var0) {
      Conversation var1 = new Conversation(var0.getConversationReference());
      var1.method2(var0);
      return var1;
   }

   public void method2(Conversation var1) {
      this.field16 = false;
      if (var1.getConversationReference().hasConversationReference()) {
         this.field1 = ThreadModuleDump66.method1(var1.getConversationReference().getConversationReference());
      }

      this.field6 = var1.getType();
      this.field7 = var1.hasLastActivityTime() ? ThreadModuleDump66.method5(var1.getLastActivityTime()) : null;
      this.name = var1.hasName() ? var1.getName() : null;
      this.field8 = var1.hasConversationImageUrl() ? var1.getConversationImageUrl() : null;
      this.field9 = var1.hasOwnerUuid() ? ThreadModuleDump66.method1(var1.getOwnerUuid()) : null;
      this.field10 = var1.hasParticipantLimit() ? var1.getParticipantLimit() : 0;
      this.field11 = var1.hasInvitePolicy() ? var1.getInvitePolicy() : InvitePolicy.INVITE_POLICY_UNSPECIFIED;
      this.field12 = var1.hasNamePolicy() ? var1.getNamePolicy() : NamePolicy.NAME_POLICY_UNSPECIFIED;
      this.field13 = var1.hasIconPolicy() ? var1.getIconPolicy() : IconPolicy.ICON_POLICY_UNSPECIFIED;
      this.field14 = var1.hasMessagePinningPolicy() ? var1.getMessagePinningPolicy() : MessagePinningPolicy.MESSAGE_PINNING_POLICY_UNSPECIFIED;
      if (var1.hasUserState()) {
         this.field15 = var1.getUserState().getUnreadCount();
         this.field17 = ThreadModuleDump66.method5(var1.getUserState().getLastSeenTime());
      } else {
         this.field15 = 0;
         this.field17 = null;
      }

      this.field2.clear();

      for (ConversationParticipant var3 : var1.getParticipantsList()) {
         this.field2.add(GuiProfile.method1(var3));
      }

      if (var1.hasLatestMessage()) {
         ChatMessage var4 = ChatMessage.method1(var1.getLatestMessage());
         if (var4 != null) {
            this.method3(var4);
         }
      }
   }

   public void method3(ChatMessage var1) {
      Optional var2 = this.field3.stream().filter(var1x -> var1x.getId().equals(var1.getId())).findFirst();
      if (var2.isPresent()) {
         ChatMessage var3 = (ChatMessage)var2.get();
         var3.method7(var1.method2());
         var3.method8(var1.method3());
         var3.method9(var1.method4());
         var3.method10(var1.getKind());
         var3.method11(var1.method5());
         var3.method12(var1.method6());
         if (var1.method5()) {
            this.field4.add(var1.getId());
         } else {
            this.field4.remove(var1.getId());
         }
      } else {
         this.field3.addFirst(var1);
         if (var1.method5()) {
            this.field4.add(var1.getId());
         }
      }
   }

   public void method4(List<ConversationMessage> var1) {
      for (ConversationMessage var3 : var1) {
         ChatMessage var4 = ChatMessage.method1(var3);
         if (var4 != null) {
            this.method3(var4);
         }
      }
   }

   public void method5(UUID var1) {
      this.field3.removeIf(var1x -> var1x.getId().equals(var1));
      this.field4.remove(var1);
   }

   public void method6(@Nullable UUID var1) {
      HashSet var2 = new HashSet();
      this.field3.removeIf(var2x -> {
         GuiProfile var3 = var2x.method3();
         boolean var4 = var1 == null || var3 != null && var1.equals(var3.method4());
         if (var4) {
            var2.add(var2x.getId());
         }

         return var4;
      });
      this.field4.removeAll(var2);
   }

   public void method7(List<ConversationMessage> var1) {
      this.field4.clear();

      for (ConversationMessage var3 : var1) {
         UUID var4 = ThreadModuleDump66.method1(var3.getId());
         this.field4.add(var4);
         ChatMessage var5 = ChatMessage.method1(var3);
         if (var5 != null) {
            var5.method11(true);
            this.method3(var5);
         }
      }
   }

   public void method8(UuidAndUsername var1, PreSendAction var2) {
      UUID var3 = ThreadModuleDump66.method1(var1.getUuid());
      if (var2 != PreSendAction.PRE_SEND_ACTION_NONE && var2 != PreSendAction.PRE_SEND_ACTION_UNSPECIFIED) {
         this.field5.put(var3, var2);
      } else {
         this.field5.remove(var3);
      }
   }

   public JsonElement provide() {
      JsonObject var1 = new JsonObject();
      if (this.field1 != null) {
         var1.addProperty("conversationId", this.field1.toString());
      }

      var1.addProperty("type", this.field6 != null ? this.field6.name() : ConversationType.CONVERSATION_TYPE_UNSPECIFIED.name());
      if (this.name != null) {
         var1.addProperty("name", this.name);
      }

      if (this.field8 != null) {
         var1.addProperty("conversationImageUrl", this.field8);
      }

      if (this.field9 != null) {
         var1.addProperty("ownerUuid", this.field9.toString());
      }

      var1.addProperty("participantLimit", this.field10);
      var1.addProperty("invitePolicy", this.field11.name());
      var1.addProperty("namePolicy", this.field12.name());
      var1.addProperty("iconPolicy", this.field13.name());
      var1.addProperty("messagePinningPolicy", this.field14.name());
      var1.addProperty("unreadCount", this.field15);
      var1.addProperty("isStub", this.field16);
      if (this.field17 != null) {
         var1.addProperty("lastSeenTimeMs", this.field17.toEpochMilli());
      }

      if (this.field7 != null) {
         var1.addProperty("lastActivityTimeMs", this.field7.toEpochMilli());
      }

      JsonArray var2 = new JsonArray();
      this.field2.forEach(var1x -> var2.add(var1x.provide()));
      var1.add("participants", var2);
      JsonArray var3 = new JsonArray();
      this.field4.forEach(var1x -> var3.add(var1x.toString()));
      var1.add("pinnedMessageIds", var3);
      JsonArray var4 = new JsonArray();
      this.field5.forEach((var1x, var2x) -> {
         JsonObject var3x = new JsonObject();
         var3x.addProperty("userId", var1x.toString());
         var3x.addProperty("action", var2x.name());
         var4.add(var3x);
      });
      var1.add("preSendActions", var4);
      JsonArray var5 = new JsonArray();
      this.field3.forEach(var1x -> var5.add(var1x.provide()));
      var1.add("messages", var5);
      return var1;
   }

   @Nullable
   @Generated
   public UUID method9() {
      return this.field1;
   }

   @Generated
   public List<GuiProfile> method10() {
      return this.field2;
   }

   @Generated
   public Deque<ChatMessage> method11() {
      return this.field3;
   }

   @Generated
   public Set<UUID> method12() {
      return this.field4;
   }

   @Generated
   public Map<UUID, PreSendAction> method13() {
      return this.field5;
   }

   @Generated
   public ConversationType getType() {
      return this.field6;
   }

   @Nullable
   @Generated
   public Instant method14() {
      return this.field7;
   }

   @Nullable
   @Generated
   public String getName() {
      return this.name;
   }

   @Nullable
   @Generated
   public String getConversationImageUrl() {
      return this.field8;
   }

   @Nullable
   @Generated
   public UUID method15() {
      return this.field9;
   }

   @Generated
   public int getParticipantLimit() {
      return this.field10;
   }

   @Generated
   public InvitePolicy getInvitePolicy() {
      return this.field11;
   }

   @Generated
   public NamePolicy getNamePolicy() {
      return this.field12;
   }

   @Generated
   public IconPolicy getIconPolicy() {
      return this.field13;
   }

   @Generated
   public MessagePinningPolicy getMessagePinningPolicy() {
      return this.field14;
   }

   @Generated
   public int getUnreadCount() {
      return this.field15;
   }

   @Generated
   public boolean method16() {
      return this.field16;
   }

   @Nullable
   @Generated
   public Instant method17() {
      return this.field17;
   }

   @Nullable
   @Generated
   public GuiProfile method18() {
      return this.field18;
   }

   @Nullable
   @Generated
   public UUID method19() {
      return this.field19;
   }

   @Nullable
   @Generated
   public GuiProfile method20() {
      return this.field20;
   }

   @Nullable
   @Generated
   public Instant method21() {
      return this.field21;
   }

   @Generated
   public void method22(ConversationType var1) {
      this.field6 = var1;
   }

   @Generated
   public void method23(@Nullable Instant var1) {
      this.field7 = var1;
   }

   @Generated
   public void setName(@Nullable String var1) {
      this.name = var1;
   }

   @Generated
   public void method24(@Nullable String var1) {
      this.field8 = var1;
   }

   @Generated
   public void method25(@Nullable UUID var1) {
      this.field9 = var1;
   }

   @Generated
   public void method26(int var1) {
      this.field10 = var1;
   }

   @Generated
   public void method27(InvitePolicy var1) {
      this.field11 = var1;
   }

   @Generated
   public void method28(NamePolicy var1) {
      this.field12 = var1;
   }

   @Generated
   public void method29(IconPolicy var1) {
      this.field13 = var1;
   }

   @Generated
   public void method30(MessagePinningPolicy var1) {
      this.field14 = var1;
   }

   @Generated
   public void method31(int var1) {
      this.field15 = var1;
   }

   @Generated
   public void method32(boolean var1) {
      this.field16 = var1;
   }

   @Generated
   public void method33(@Nullable Instant var1) {
      this.field17 = var1;
   }

   @Generated
   public void method34(@Nullable GuiProfile var1) {
      this.field18 = var1;
   }

   @Generated
   public void method35(@Nullable UUID var1) {
      this.field19 = var1;
   }

   @Generated
   public void method36(@Nullable GuiProfile var1) {
      this.field20 = var1;
   }

   @Generated
   public void method37(@Nullable Instant var1) {
      this.field21 = var1;
   }
}
