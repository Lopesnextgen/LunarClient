package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.rewindhandlers.ChatMessageParser;
import java.util.HashSet;
import java.util.Set;
import lombok.Generated;

public enum FishingChatType implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   ISLAND_VISITOR(ChatMessageParser.IslandVisitorChatMessage.class, "/ac", "islandVisitor"),
   ALL(ChatMessageParser.Data.class, "/ac", "all"),
   PARTY(ChatMessageParser.PartyChatMessage.class, "/pc", "party"),
   GUILD(ChatMessageParser.GuildChatMessage.class, "/gc", "guild"),
   GUILD_OFFICER(ChatMessageParser.GuildOfficerChatMessage.class, "/oc", "guildOfficer"),
   DM_RECEIVE(ChatMessageParser.DirectMessageReceived.class, "/r", "dmReceive"),
   DM_SEND(ChatMessageParser.DirectMessageSent.class, "/r", "dmSend"),
   COOP(ChatMessageParser.CoopChatMessage.class, "/cc", "coop");

   private final Class<? extends ChatMessageParser.Extension> chatType;
   private final String command;
   private final String id;

   public boolean is(ChatMessageParser.Extension extension1) {
      return this.getChatType().equals(extension1.getClass());
   }

   public String id() {
      return this.id;
   }

   public static Set<String> ids() {
      HashSet set0 = new HashSet();

      for (FishingChatType gui2extension4 : values()) {
         set0.add(gui2extension4.id());
      }

      return set0;
   }

   public static FishingChatType of(ChatMessageParser.Extension extension0) {
      for (FishingChatType gui2extension4 : values()) {
         if (extension0.getClass() == gui2extension4.getChatType()) {
            return gui2extension4;
         }
      }

      return null;
   }

   @Generated
   FishingChatType(Class<? extends ChatMessageParser.Extension> clazz3, String text, String text2) {
      this.chatType = clazz3;
      this.command = text;
      this.id = text2;
   }

   @Generated
   public Class<? extends ChatMessageParser.Extension> getChatType() {
      return this.chatType;
   }

   @Generated
   public String getCommand() {
      return this.command;
   }

   @Generated
   public String getId() {
      return this.id;
   }
}
