package com.moonsworth.lunar.client.framework.feature.mod.fishing;

import com.moonsworth.lunar.client.framework.feature.mod.holograms.rewindhandlers.Rewindhandlers;
import java.util.HashSet;
import java.util.Set;
import lombok.Generated;

public enum Gui2Extension implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   ISLAND_VISITOR(Rewindhandlers.Data7.class, "/ac", "islandVisitor"),
   ALL(Rewindhandlers.Data.class, "/ac", "all"),
   PARTY(Rewindhandlers.Data3.class, "/pc", "party"),
   GUILD(Rewindhandlers.Data5.class, "/gc", "guild"),
   GUILD_OFFICER(Rewindhandlers.Data6.class, "/oc", "guildOfficer"),
   DM_RECEIVE(Rewindhandlers.Data4.class, "/r", "dmReceive"),
   DM_SEND(Rewindhandlers.Data8.class, "/r", "dmSend"),
   COOP(Rewindhandlers.Data2.class, "/cc", "coop");

   private final Class<? extends Rewindhandlers.Extension> chatType;
   private final String command;
   private final String id;

   public boolean is(Rewindhandlers.Extension extension) {
      return this.getChatType().equals(extension.getClass());
   }

   public String id() {
      return this.id;
   }

   public static Set<String> ids() {
      HashSet var0 = new HashSet();

      for (Gui2Extension var4 : values()) {
         var0.add(var4.id());
      }

      return var0;
   }

   public static Gui2Extension of(Rewindhandlers.Extension var0) {
      for (Gui2Extension var4 : values()) {
         if (var0.getClass() == var4.getChatType()) {
            return var4;
         }
      }

      return null;
   }

   @Generated
   Gui2Extension(Class<? extends Rewindhandlers.Extension> type, String var4, String text) {
      this.chatType = type;
      this.command = var4;
      this.id = text;
   }

   @Generated
   public Class<? extends Rewindhandlers.Extension> getChatType() {
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
