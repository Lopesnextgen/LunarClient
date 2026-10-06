package com.moonsworth.lunar.client.chat;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.websocket.conversation.v1.ConversationMessage;
import com.lunarclient.websocket.conversation.v1.ConversationMessageKind;
import com.moonsworth.lunar.client.driver.bridge.JsonProvider;
import com.moonsworth.lunar.client.util.text.TimeFormatting;
import com.moonsworth.lunar.client.network.apollo.ProtoConverter;
import java.time.Instant;
import java.util.UUID;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.account.GuiProfile;
import com.moonsworth.lunar.client.chat.MessageContents;

public class ChatMessage implements JsonProvider {
   private final UUID field1;
   private Instant field2;
   @Nullable
   private GuiProfile field3;
   private MessageContents field4;
   private ConversationMessageKind field5;
   private boolean field6;
   private boolean field7;

   public ChatMessage(UUID uuid1, Instant instant2, @Nullable GuiProfile fov_33, MessageContents fov3_24, ConversationMessageKind conversationmessagekind5, boolean flag) {
      this.field1 = uuid1;
      this.field2 = instant2;
      this.field3 = fov_33;
      this.field4 = fov3_24;
      this.field5 = conversationmessagekind5;
      this.field6 = flag;
      this.field7 = false;
   }

   @Nullable
   public static ChatMessage method1(ConversationMessage conversationmessage0) {
      GuiProfile fov_31 = conversationmessage0.hasSender() ? GuiProfile.method2(conversationmessage0.getSender()) : null;
      MessageContents fov3_22 = MessageContents.method1(conversationmessage0.getContents());
      if (fov3_22 == null) {
         return null;
      }

      if (fov_31 == null && fov3_22 instanceof SystemEventContents fov3$data73) {
         SystemEvent fov5_34 = fov3$data73.method4();
         fov_31 = fov5_34.method3();
      }

      return new ChatMessage(ProtoConverter.method1(conversationmessage0.getId()), ProtoConverter.method5(conversationmessage0.getSentAt()), fov_31, fov3_22, conversationmessage0.getKind(), conversationmessage0.getPinned());
   }

   public String getPlainText() {
      return this.field4.method3();
   }

   public JsonElement provide() {
      JsonObject json1 = new JsonObject();
      json1.addProperty("id", this.field1.toString());
      json1.addProperty("sentAtMs", this.field2.toEpochMilli());
      json1.addProperty("sentAt", TimeFormatting.method4(this.field2.toEpochMilli()));
      json1.addProperty("kind", this.field5.name());
      json1.addProperty("pinned", this.field6);
      json1.addProperty("updated", this.field7);
      json1.addProperty("plainText", this.getPlainText());
      if (this.field3 != null) {
         json1.add("sender", this.field3.provide());
      }

      json1.add("content", this.field4.provide());
      return json1;
   }

   @Generated
   public UUID getId() {
      return this.field1;
   }

   @Generated
   public Instant method2() {
      return this.field2;
   }

   @Nullable
   @Generated
   public GuiProfile method3() {
      return this.field3;
   }

   @Generated
   public MessageContents method4() {
      return this.field4;
   }

   @Generated
   public ConversationMessageKind getKind() {
      return this.field5;
   }

   @Generated
   public boolean method5() {
      return this.field6;
   }

   @Generated
   public boolean method6() {
      return this.field7;
   }

   @Generated
   public void method7(Instant instant1) {
      this.field2 = instant1;
   }

   @Generated
   public void method8(@Nullable GuiProfile fov_31) {
      this.field3 = fov_31;
   }

   @Generated
   public void method9(MessageContents fov3_21) {
      this.field4 = fov3_21;
   }

   @Generated
   public void method10(ConversationMessageKind conversationmessagekind1) {
      this.field5 = conversationmessagekind1;
   }

   @Generated
   public void method11(boolean flag1) {
      this.field6 = flag1;
   }

   @Generated
   public void method12(boolean flag1) {
      this.field7 = flag1;
   }
}
