package com.moonsworth.lunar.client.chat;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.websocket.conversation.v1.SystemMessage;
import com.lunarclient.websocket.conversation.v1.ConversationSender.SenderCase;
import com.moonsworth.lunar.client.driver.bridge.JsonProvider;
import com.moonsworth.lunar.client.network.apollo.ProtoConverter;
import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.account.GuiProfile;

public abstract class SystemEvent implements JsonProvider {
   public SystemEvent() {
   }

   @Nullable
   public static SystemEvent method1(SystemMessage systemmessage0) {
      GuiProfile fov_31 = null;
      if (systemmessage0.hasActor() && systemmessage0.getActor().getSenderCase() == SenderCase.PLAYER) {
         fov_31 = GuiProfile.method2(systemmessage0.getActor());
      }

      switch (systemmessage0.getEventCase()) {
         case PINNED:
            return new PinnedEvent(fov_31, ProtoConverter.method1(systemmessage0.getPinned().getPinnedMessageId()));
         case INVITE:
            ArrayList list2 = new ArrayList();
            systemmessage0.getInvite().getInviteesList().forEach(arg1x -> list2.add(GuiProfile.method3(arg1x)));
            return new InviteEvent(fov_31, list2);
         case LEAVE:
            return new LeaveEvent(fov_31, GuiProfile.method3(systemmessage0.getLeave().getPlayer()));
         case NAME_CHANGE:
            return new NameChangeEvent(fov_31, systemmessage0.getNameChange().getNewName());
         case ICON_CHANGE:
            return new IconChangeEvent(fov_31, systemmessage0.getIconChange().getNewIconUrl());
         case EVENT_NOT_SET:
         default:
            return null;
      }
   }

   public abstract SystemEventType method2();

   @Nullable
   public abstract GuiProfile method3();

   public abstract String method4();

   public JsonElement provide() {
      JsonObject json1 = new JsonObject();
      json1.addProperty("type", this.method2().name());
      json1.addProperty("plainText", this.method4());
      if (this.method3() != null) {
         json1.add("actor", this.method3().provide());
      }

      return json1;
   }
}
